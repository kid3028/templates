package com.templates.controller;

import com.alibaba.druid.util.FnvHash;
import com.templates.util.Captcha;
import com.templates.util.Encoder;
import com.templates.util.GifCaptcha;
import com.templates.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
@RequestMapping("/code")
public class CodeController {

    @RequestMapping("/getValidateCode")
    public String getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jepg");
        Map<String, Object> image = ImageUtil.createImage();
        // 将验证码存入session中
        request.getSession().setAttribute("validate", image.get(ImageUtil.RANDOM_CODE));


        OutputStream os = null;
        try {
            // 输出验证码
            os = response.getOutputStream();
            ImageIO.write((BufferedImage) image.get(ImageUtil.IMAGE), "jpg", os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @RequestMapping("/getValidateCode1")
    public void getValidateCode1(HttpSession session, HttpServletResponse response) {
        try {
            response.setDateHeader("Expires", 0L);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/gif");

            /**
             * gif格式动画
             * 宽 高 位数
             */
            Captcha captcha = new GifCaptcha(146, 33, 4);
            //输出

            captcha.out(response.getOutputStream());
            session.setAttribute("validate", captcha);
        } catch (IOException e) {
            System.out.println("获取验证码异常:" + e.getMessage());
        }
    }


    @RequestMapping("/validateCode")
    @ResponseBody
    public Boolean validateCode(@RequestParam("code") String code, HttpSession session, ModelMap model) {
        System.out.println(code + "<---------------");
        String validate = (String) session.getAttribute("validate");
        System.out.println(validate + "--->" + code);
        if(validate != null) {
            if (validate.equalsIgnoreCase(code)) {
                return true;
            } else {
                model.addAttribute("message", "验证码错误!");
                return false;
            }
        }
        return false;


    }


}