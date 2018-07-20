package com.citic.zktd.smart.security.validate.image;


import com.citic.zktd.smart.security.validate.ValidateCodeGenerator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageCodeGenerator implements ValidateCodeGenerator {


    /*
     * (non-Javadoc)
     *
     * @see
     * com.citic.zktd.smart.security.core.validate.code.ValidateCodeGenerator#generate(org.
     * springframework.web.context.request.ServletWebRequest)
     */
    private static Random random = new Random();

    @Override
    public ImageCode generate(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
                60);
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
                20);
        // 图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = buffImg.getGraphics();
        Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设置干扰线
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(12);
            int ye = ys + random.nextInt(12);
            g.drawLine(xs, ys, xe, ye);
        }


        // 添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        Color c = getRandColor(200, 250);
        shear(g, width, height, c);// 使图片扭曲

        int verifySize = 4;
        String code = randomStr(verifySize);// 得到随机字符
        g.setColor(getRandColor(100, 160));
        int fontSize = height - 2;
        // 设置字体
//        Font font = new Font("Times New Roman", Font.BOLD, fontSize);
        Font font = getFont(fontSize);
        System.err.println(font.getFontName());
        g.setFont(font);
        char[] chars = code.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize / 2, height / 2);
            g.setTransform(affine);
            g.drawChars(chars, i, 1, ((width - 8) / verifySize) * i + 5, height / 2 + fontSize / 2 - 5);

        }
        g.dispose();

        System.err.println(code);
        return new ImageCode(buffImg, code, 3600);
    }

    // 得到随机字符
    private String randomStr(int n) {
        String str1 = "1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }


    // 得到随机颜色
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.ITALIC, size);
        font[1] = new Font("Antique Olive Compact", Font.BOLD, size);
        font[2] = new Font("Times New Roman", Font.BOLD, size);
        font[3] = new Font("Candara", Font.ITALIC, size);
        font[4] = new Font("Gill Sans Ultra", Font.ITALIC, size);
        return font[random.nextInt(5)];
    }

    // 扭曲方法
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }


    private void shearX(Graphics g, int w1, int h1, Color color) {


        int period = random.nextInt(2);


        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);


        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }


    }


    private void shearY(Graphics g, int w1, int h1, Color color) {


        int period = random.nextInt(40) + 10; // 50;


        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }


        }


    }


}
