package com.dzqc.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : gongfangchao
 * @describe : 基础控制器
 * @date : 2017/11/29
 **/
@RequestMapping("/base")
@Controller
public class BaseController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        return modelAndView;
    }

}
