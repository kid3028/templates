package com.templates.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/user")
public class SysController {

    @RequestMapping("/update")
    public String update(){
        System.out.println("--------update----------");
        return "update";
    }


    @RequestMapping("/delete")
    public String delete(){
        System.out.println("--------delete----------");
        return "delete";
    }

    @RequestMapping("/save")
    public String save(){
        System.out.println("---------save---------");
        return "save";
    }

    @RequestMapping("/list")
    public String listt(){
        System.out.println("---------list---------");
        return "list";
    }





}