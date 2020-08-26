package com.study.springmvcdemo1.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/*


使用@RequestMapping来返回服务端的图片

 */

@RestController
public class ImageController {

    /*
       获取图片,produces（指定返回内容的类型）是必须要加的，不然前端显式的就不是图片
    */
    @RequestMapping(value = "/getImage1",produces = MediaType.IMAGE_JPEG_VALUE)
    //@ResponseBody
    public byte[] getImage1() throws IOException {
        File file = new File("springmvc-demo1/src/main/resources/images/bank.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    /*
    获取图片

    (1)There was an unexpected error (type=Not Acceptable, status=406).Could not find acceptable representation
        解决办法是添加ImageConverterConfig中的bean

 */
    @RequestMapping(value = "/getImage2",produces = MediaType.IMAGE_JPEG_VALUE,method= RequestMethod.GET)
    //@ResponseBody
    public BufferedImage getImage2() throws IOException {

        URL url = getClass().getClassLoader().getResource("images/bank.jpg");
        System.out.println("maven打包后的路径==> "+url);
        return ImageIO.read(new FileInputStream(new File("springmvc-demo1/src/main/resources/images/bank.jpg")));
    }


    /*
    关流
     */
    @RequestMapping(value = "/getImage3",produces = MediaType.IMAGE_JPEG_VALUE,method=RequestMethod.GET)
    @ResponseBody
    public BufferedImage getImage3() throws IOException {

        try (InputStream is = new FileInputStream("springmvc-demo1/src/main/resources/images/bank.jpg")){
            return ImageIO.read(is);
        }

    }
}


