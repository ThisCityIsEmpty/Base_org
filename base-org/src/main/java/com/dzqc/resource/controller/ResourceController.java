package com.dzqc.resource.controller;

import com.dzqc.base.entity.AjaxResult;
import com.dzqc.base.entity.LayuiPageResult;
import com.dzqc.base.exception.BusinessException;
import com.dzqc.resource.entity.OrgResourceV2;
import com.dzqc.resource.service.OrgResourceV2Service;
import com.dzqc.resource.status.ResourceTypeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : gongfangchao
 * @describe : 资源控制器
 * @date : 2017/11/29
 **/
@RequestMapping("/resource")
@Controller
public class ResourceController {

    @Autowired
    private OrgResourceV2Service service;

    /**
     * 根据业务逻辑，校验表单提交内容
     * @param orgResourceV2 表单提交内容
     */
    private AjaxResult checkResource(OrgResourceV2 orgResourceV2){
        if (StringUtils.isEmpty(orgResourceV2.getName())){
            return AjaxResult.fail("资源名称不能为空！");
        }
        if (StringUtils.isEmpty(orgResourceV2.getType())){
            return AjaxResult.fail("资源类型不能为空！");
        }

        if (orgResourceV2.getType().equals(ResourceTypeStatus.MODULE.getCode())){
            if (StringUtils.isEmpty(orgResourceV2.getParentId())){
                return AjaxResult.fail("父级资源不能为空！");
            }
        }

        if (orgResourceV2.getType().equals(ResourceTypeStatus.MENU.getCode())){
            if (StringUtils.isEmpty(orgResourceV2.getUrl())){
                return AjaxResult.fail("资源链接不能为空！");
            }
            if (StringUtils.isEmpty(orgResourceV2.getParentId())){
                return AjaxResult.fail("父级资源不能为空！");
            }
        }

        if (orgResourceV2.getType().equals(ResourceTypeStatus.BUTTON.getCode())){
            if (StringUtils.isEmpty(orgResourceV2.getAuthCode())){
                return AjaxResult.fail("资源代码不能为空！");
            }
            if (StringUtils.isEmpty(orgResourceV2.getParentId())){
                return AjaxResult.fail("父级资源不能为空！");
            }
        }

        return AjaxResult.success("no problem");
    }

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
        modelAndView.setViewName("resource/add");

        modelAndView.addObject("modules", this.service.findModule());
        modelAndView.addObject("menus", this.service.findMenu());

        return modelAndView;
    }

    /**
     * 页面：编辑资源
     */
    @RequestMapping("/page/update")
    public ModelAndView toUpdate(String id){
        if (StringUtils.isEmpty(id)){
            throw new BusinessException("资源ID不能为空！");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("resource/update");

        OrgResourceV2 res = this.service.findOne(id);

        modelAndView.addObject("res", res);
        if (res.getType().equals(ResourceTypeStatus.MENU.getCode())){
            modelAndView.addObject("parents", this.service.findModule());
        }
        if (res.getType().equals(ResourceTypeStatus.BUTTON.getCode())){
            modelAndView.addObject("parents", this.service.findMenu());
        }

        return modelAndView;
    }

    /**
     * 数据：资源列表
     */
    @RequestMapping("/data/list")
    @ResponseBody
    public LayuiPageResult dataList(String resName,
                                    @RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "limit", defaultValue = "10") int size){
        Page<OrgResourceV2> result = this.service.fingByParams(resName, page - 1, size);
        return LayuiPageResult.success((int)result.getTotalElements(), result.getContent());
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
        AjaxResult result = this.checkResource(orgResourceV2);

        if (!result.isSuccess()){
            return result;
        }

        this.service.add(orgResourceV2);
        return AjaxResult.success("新增资源成功！");
    }

    /**
     * 操作：修改
     * @param orgResourceV2 资源
     */
    @RequestMapping("/operate/update")
    @ResponseBody
    public AjaxResult update(OrgResourceV2 orgResourceV2){
        if (StringUtils.isEmpty(orgResourceV2.getId())){
            return AjaxResult.fail("资源ID不能为空！");
        }
        AjaxResult result = this.checkResource(orgResourceV2);

        if (!result.isSuccess()){
            return result;
        }

        this.service.update(orgResourceV2);
        return AjaxResult.success("修改资源成功！");
    }

    /**
     * 操作：冻结
     * @param id 资源
     */
    @RequestMapping("/operate/frozen")
    @ResponseBody
    public AjaxResult frozen(String id){
        if (StringUtils.isEmpty(id)){
            return AjaxResult.fail("资源ID不能为空！");
        }
        this.service.frozen(id);
        return AjaxResult.success("冻结资源成功！");
    }

    /**
     * 操作：解冻
     * @param id 资源
     */
    @RequestMapping("/operate/thaw")
    @ResponseBody
    public AjaxResult thaw(String id){
        if (StringUtils.isEmpty(id)){
            return AjaxResult.fail("资源ID不能为空！");
        }
        this.service.thaw(id);
        return AjaxResult.success("冻结资源成功！");
    }

}
