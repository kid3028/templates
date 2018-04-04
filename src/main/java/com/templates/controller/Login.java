package com.templates.controller;

import com.templates.dao.UserMapper;
import com.templates.entity.User;
import com.templates.shiro.Salt;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


@Controller
public class Login {

    private static final Logger logger = LoggerFactory.getLogger(ResolverUtil.Test.class);

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password, ModelMap model) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        String msg = "";
        try {
            subject.login(usernamePasswordToken);  //完成登录
            return "index";
        } catch (UnknownAccountException unknownAccountException) {
            logger.info("--------->There no user with username of " + usernamePasswordToken.getPrincipal());
            msg = "用户名或密码错误！";
        } catch (IncorrectCredentialsException incorrectCredentialsException) {
            logger.info("--------->Password for account " + usernamePasswordToken.getPrincipal() + " was incorrect!");
            msg = "用户名或密码错误！";
        } catch (LockedAccountException lockedAccountException) {
            logger.info("---------> The account for username " + usernamePasswordToken.getPrincipal() + " is locked. Please contact your administrator to unlock it!");
            msg = "账户被锁定，请联系管理员!";
        } catch (Exception e) {
            logger.info("--------->failed");
            msg = "登录异常！";
        }
        model.put("message", msg);

        return "login";
    }


    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }


    @RequestMapping("/403")
    public String unAuthc() {
        return "403";
    }

    @RequestMapping("/register")
    public String register() {
        System.out.println("register..........");
        return "register";
    }

    @RequestMapping("/registerUser")
    public String register(User user) {
        user.setSalt(Salt.createSalt());
        user.setPassword(Salt.createCredentials(user.getSalt(),user.getPassword()));
        userMapper.insertSelective(user);
        return "login";
    }


}