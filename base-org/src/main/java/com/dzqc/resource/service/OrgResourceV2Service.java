package com.dzqc.resource.service;

import com.dzqc.resource.entity.OrgResourceV2;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 菜单资源事务层
 * @date : 2017/11/30
 **/
public interface OrgResourceV2Service {

    /**
     * 分页查询资源
     * @param name 资源名称
     */
    Page<OrgResourceV2> fingByParams(String name, int page, int size);

    /**
     * 查询所有的菜单资源
     */
    List<OrgResourceV2> findAll();

    /**
     * 查询子系统
     */
    List<OrgResourceV2> findSystem();

    /**
     * 查询模块类型的资源
     */
    List<OrgResourceV2> findModule();

    /**
     * 查询菜单类型的资源
     */
    List<OrgResourceV2> findMenu();

    /**
     * 根据资源ID，查找资源
     * @param id 资源ID
     */
    OrgResourceV2 findOne(String id);

    /**
     * 新增菜单资源
     * @param orgResourceV2 菜单资源
     */
    void add(OrgResourceV2 orgResourceV2);

    /**
     * 修改菜单资源
     * @param orgResourceV2 菜单资源
     */
    void update(OrgResourceV2 orgResourceV2);

    /**
     * 冻结资源
     * @param id 菜单ID
     */
    void frozen(String id);

    /**
     * 解冻资源
     * @param id 菜单ID
     */
    void thaw(String id);

}
