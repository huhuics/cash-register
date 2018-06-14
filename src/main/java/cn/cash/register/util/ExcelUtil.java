package cn.cash.register.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel工具类
 * 
 * @author 51
 * @version $Id: ExcelUtil.java, v 0.1 2018年6月7日 上午11:01:57 51 Exp $
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 创建Excel文件并写入数据
     * 
     * @param filePath 创建文件目录
     * @param fileName 文件名
     * @param sheetName 工作簿名称
     * @param data 数据
     * @throws IOException
     */
    public static void createExcel(String filePath, String fileName, String sheetName, List<List<String>> data) throws IOException {
        LogUtil.info(logger, "开始创建excel文件[{0}]，所在目录filePath={1}", fileName, filePath);

        File dir = new File(filePath);
        dir.mkdirs();
        File file = new File(dir, fileName);
        AssertUtil.assertFalse(file.exists(), "创建文件时出错：文件" + fileName + "已存在");

        XSSFWorkbook workbook = new XSSFWorkbook(); // 创建文件
        XSSFSheet spreadsheet = workbook.createSheet(sheetName); // 文件中的工作簿

        int rowId = 0;
        for (List<String> rowData : data) {
            XSSFRow row = spreadsheet.createRow(rowId++);
            int cellId = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(cellId++);
                cell.setCellValue(cellData);
            }
        }

        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);

        out.close();
        workbook.close();

        LogUtil.info(logger, "文件[{0}]创建成功.所在目录filePath={1}", fileName, filePath);
    }

    /**
     * 处理Excel时单元格数据对象
     * 
     * @param obj
     * @return
     */
    public static String obj2String(Object obj) {
        if (null == obj) {
            return "";
        }
        if (obj instanceof Date) {
            return DateUtil.format((Date) obj, DateUtil.completeFormat);
        }
        return obj.toString();

    }

    /**
     * 读取Excel文件，将其转化为List<List<String>>
     * <p>要求：单元格格式需要全部为String
     * 
     * @param file
     * @param colLength 从左至右读取的列数
     * @return
     * @throws IOException
     */
    public static List<List<String>> readExcel(File file, int colLength) throws IOException {
        LogUtil.info(logger, "开始读取excel文件[{0}],所在目录filePath={1},读取列数colLength={2}", file.getName(), file.getAbsolutePath(), colLength);

        List<List<String>> result = new ArrayList<List<String>>();

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0); // 只读取第一个sheet
        Iterator<Row> rowIterator = spreadsheet.iterator();
        while (rowIterator.hasNext()) {
            List<String> _rowList = new ArrayList<String>();
            XSSFRow row = (XSSFRow) rowIterator.next();
            for (int i = 0; i < colLength; i++) {
                Cell cell = row.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
                _rowList.add(cell.getStringCellValue());
            }
            result.add(_rowList);
        }
        fis.close();
        workbook.close();

        LogUtil.info(logger, "文件[{0}]读取完毕.读取结果result={1}", file.getName(), result);
        return result;
    }

}
