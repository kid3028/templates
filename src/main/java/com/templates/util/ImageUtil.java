package com.templates.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImageUtil {
    public static final String IMAGE = "image";
    public static final String RANDOM_CODE  = "randomCode";
    public static Map<String,Object> createImage() {
        Map<String, Object> map = new HashMap<>(2);
        int width = 80;
        int height = 32;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Color init = g.getColor();
        // 设置背景颜色
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);

        // 边框
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height -1);

        // 随机验证码
        Random random = new Random();
        String hash = Integer.toHexString(random.nextInt());
        // 干扰线
        for(int i=0; i<50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }

        String capstr = hash.substring(0, 4);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(capstr, 8, 24);
        g.dispose();
        map.put(RANDOM_CODE, capstr);
        map.put(IMAGE,image);
        return map;

    }

}