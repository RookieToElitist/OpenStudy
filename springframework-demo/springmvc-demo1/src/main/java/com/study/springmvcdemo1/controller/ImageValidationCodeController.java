package com.study.springmvcdemo1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/*
    数字图片验证码
 */

@RestController
public class ImageValidationCodeController {


    /*
    使用RequestMapping+AWT包下的Graphics生成数字图片验证码
     */
    @RequestMapping(value = "/validCode", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
    public BufferedImage validCode() throws IOException {

        //定义一个BufferedImage
        BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();

        //定义颜色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 100);

        //产生4个随机数
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            list.add(random.nextInt(10));
        }

        //设置字体 宋体  加粗 大小为50
        graphics.setFont(new Font("宋体", Font.BOLD, 45));

        //定义一个颜色数组
        Color[] colors = new Color[]{Color.blue, Color.BLACK, Color.GREEN, Color.RED, Color.ORANGE};
        for(int i = 0; i < list.size(); i++) {
            //使用随机颜色
            graphics.setColor(colors[random.nextInt(colors.length)]);
            //画字符 并使字符高度随机
            graphics.drawString("" + list.get(i), i * 50 + 5, 50 + random.nextInt(51) - 10);
        }

        //画上三条随机的线
        for(int i = 0; i < 3; i++) {
            graphics.setColor(colors[random.nextInt(colors.length)]);
            graphics.drawLine(0, random.nextInt(101), 200, random.nextInt(101));
        }

        return image;
    }

}
