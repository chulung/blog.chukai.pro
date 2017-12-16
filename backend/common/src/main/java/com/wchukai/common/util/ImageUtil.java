package com.wchukai.common.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class ImageUtil {
    private static final String MICROSOFT_YA_HEI = "Microsoft YaHei";
    private static final String WATER_MARK_CONTENT = "wchukai.com";

    /**
     * 图片添加水印
     *
     * @param imgFormat
     * @return
     */
    public static byte[] mark(InputStream is, String imgFormat) {
        Image srcImg;
        try {
            srcImg = ImageIO.read(is);
        } catch (IOException e) {
            return null;
        }
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        // 加水印
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        Font font = new Font(MICROSOFT_YA_HEI, Font.BOLD, 16);
        g.setColor(new Color(91, 192, 222)); // 根据图片的背景设置水印颜色
        g.setFont(font);
        int x = srcImgWidth - getWatermarkLength(WATER_MARK_CONTENT, g) - 3;
        int y = srcImgHeight - 3;
        g.drawString(WATER_MARK_CONTENT, x, y);
        g.dispose();
        // 输出图片
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufImg, imgFormat, imOut); // scaledImage1为BufferedImage，jpg为图像的类型
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return bs.toByteArray();
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent 水印的文字
     * @param g
     * @return 水印文字总长度
     */
    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

}
