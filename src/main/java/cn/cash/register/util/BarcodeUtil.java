/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * 条形码工具类
 * @author HuHui
 * @version $Id: BarcodeUtil.java, v 0.1 2018年5月23日 下午5:07:03 HuHui Exp $
 */
public class BarcodeUtil {

    /**
     * 将条形码生成图片文件
     * @param content  条形码内容
     * @param path     图片文件保存路径
     * @return         条形码图片文件
     */
    public static File generate(String content, String path) {
        File file = new File(path);
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            generate(content, os);
        } catch (Exception e) {
            throw new RuntimeException("创建条码出错", e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                }
            }
        }
        return file;
    }

    public static byte[] genetate(String content) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            generate(content, baos);
        } catch (IOException e) {
            throw new RuntimeException("创建条码出错", e);
        }

        return baos.toByteArray();
    }

    /**
     * 创建条形码
     * @throws IOException 
     */
    private static void generate(String content, OutputStream os) throws IOException {
        if (StringUtils.isEmpty(content) || os == null) {
            return;
        }

        Code39Bean bean = new Code39Bean();

        // 精细度
        final int dpi = 120;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);
        bean.doQuietZone(false);

        String format = "image/png";

        // 输出到流
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(os, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // 生成条形码
        bean.generateBarcode(canvas, content);

        // 结束绘制
        canvas.finish();
    }

}
