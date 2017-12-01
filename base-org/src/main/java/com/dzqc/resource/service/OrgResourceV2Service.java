package com.dzqc.resource.service;

import com.dzqc.resource.entity.OrgResourceV2;

import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 菜单资源事务层
 * @date : 2017/11/30
 **/
public interface OrgResourceV2Service {

    /**
     * 查询所有的菜单资源
     */
    List<OrgResourceV2> findAll();

    /**
     * 查询模块类型的资源
     */
    List<OrgResourceV2> findModule();

    /**
     * 查询菜单类型的资源
     */
    List<OrgResourceV2> findMenu();

    /**
     * 新增菜单资源
     * @param orgResourceV2 菜单资源
     */
    void add(OrgResourceV2 orgResourceV2);

}
