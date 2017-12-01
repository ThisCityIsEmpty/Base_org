package com.dzqc.resource.service.impl;

import com.dzqc.base.util.ObjectUtil;
import com.dzqc.resource.dao.OrgResourceV2Dao;
import com.dzqc.resource.entity.OrgResourceV2;
import com.dzqc.resource.service.OrgResourceV2Service;
import com.dzqc.resource.status.ResourceTypeStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 菜单资源事务层实现类
 * @date : 2017/11/30
 **/
@Service
public class OrgResourceV2ServiceImpl implements OrgResourceV2Service {

    @Resource
    private OrgResourceV2Dao dao;

    public List<OrgResourceV2> findAll() {
        List<OrgResourceV2> list = this.dao.findAll();

        List<OrgResourceV2> modules = new ArrayList<OrgResourceV2>();
        List<OrgResourceV2> menus = new ArrayList<OrgResourceV2>();
        List<OrgResourceV2> buttons = new ArrayList<OrgResourceV2>();

        for (OrgResourceV2 orgResourceV2 : list){
            if (orgResourceV2.getType().equals(ResourceTypeStatus.MODULE.getCode())){
                modules.add(orgResourceV2);
            }
            if (orgResourceV2.getType().equals(ResourceTypeStatus.MENU.getCode())){
                menus.add(orgResourceV2);
            }
            if (orgResourceV2.getType().equals(ResourceTypeStatus.BUTTON.getCode())){
                buttons.add(orgResourceV2);
            }
        }

        for (OrgResourceV2 menu : menus){
            List<OrgResourceV2> childList = new ArrayList<OrgResourceV2>();
            if (buttons.size() > 0){
                for (OrgResourceV2 button : buttons){
                    if (button.getParentId().endsWith(menu.getId())){
                        childList.add(button);
                    }
                }
                if (childList.size() > 0){
                    buttons.removeAll(childList);
                    menu.setChildList(childList);
                }
            }
        }

        for (OrgResourceV2 module : modules){
            List<OrgResourceV2> childList = new ArrayList<OrgResourceV2>();
            if (modules.size() > 0){
                for (OrgResourceV2 menu : menus){
                    if (menu.getParentId().endsWith(module.getId())){
                        childList.add(menu);
                    }
                }
                if (childList.size() > 0){
                    menus.removeAll(childList);
                    module.setChildList(childList);
                }
            }
        }
        return modules;
    }

    public List<OrgResourceV2> findModule() {
        List<OrgResourceV2> list = this.dao.findAllByType(ResourceTypeStatus.MODULE.getCode());
        return list;
    }

    public List<OrgResourceV2> findMenu() {
        return this.dao.findAllByType(ResourceTypeStatus.MENU.getCode());
    }

    public void add(OrgResourceV2 orgResourceV2) {
        orgResourceV2.setId(ObjectUtil.createUUID());
        orgResourceV2.setCreateTime(new Date());
        this.dao.saveAndFlush(orgResourceV2);
    }

}
