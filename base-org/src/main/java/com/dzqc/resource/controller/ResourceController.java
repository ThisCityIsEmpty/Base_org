package com.dzqc.resource.controller;

import com.dzqc.base.entity.AjaxResult;
import com.dzqc.resource.entity.OrgResourceV2;
import com.dzqc.resource.service.OrgResourceV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : gongfangchao
 * @describe : 基础控制器
 * @date : 2017/11/29
 **/
@RequestMapping("/resource")
@Controller
public class ResourceController {

    @Autowired
    private OrgResourceV2Service service;

    /**
     * 页面：资源列表
     */
    @RequestMapping("/page/list")
    public ModelAndView toList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resource/list");

        modelAndView.addObject("resources", this.service.findAll());

        return modelAndView;
    }

    /**
     * 页面：新增资源
     */
    @RequestMapping("/page/add")
    public ModelAndView toAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("modules", this.service.findModule());
        modelAndView.addObject("menus", this.service.findMenu());

        return modelAndView;
    }

    /**
     * 数据：模块资源
     */
    @RequestMapping("/data/module")
    @ResponseBody
    public AjaxResult dataModule(){
        return AjaxResult.success(this.service.findModule());
    }

    /**
     * 数据：菜单资源
     */
    @RequestMapping("/data/menu")
    @ResponseBody
    public AjaxResult dataMenu(){
        return AjaxResult.success(this.service.findMenu());
    }

    /**
     * 操作：新增
     * @param orgResourceV2 资源
     */
    @RequestMapping("/operate/add")
    @ResponseBody
    public AjaxResult add(OrgResourceV2 orgResourceV2){
        if (StringUtils.isEmpty(orgResourceV2.getName())){
            return AjaxResult.fail("资源名称不能为空！");
        }
        if (StringUtils.isEmpty(orgResourceV2.getType())){
            return AjaxResult.fail("资源类型不能为空！");
        }
        this.service.add(orgResourceV2);
        return AjaxResult.success("新增资源成功！");
    }

}
