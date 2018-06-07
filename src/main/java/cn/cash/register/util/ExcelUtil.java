package cn.cash.register.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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
        LogUtil.info(logger, "开始创建excel文件{0}，所在目录filePath={1}", fileName, filePath);

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

        LogUtil.info(logger, "文件{0}创建成功.所在目录filePath={1}", fileName, filePath);
    }

}
