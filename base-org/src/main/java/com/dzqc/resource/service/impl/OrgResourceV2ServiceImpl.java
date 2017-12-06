package com.dzqc.resource.service.impl;

import com.dzqc.base.exception.BusinessException;
import com.dzqc.resource.dao.OrgResourceV2Dao;
import com.dzqc.resource.entity.OrgResourceV2;
import com.dzqc.resource.service.OrgResourceV2Service;
import com.dzqc.resource.status.ResourceStatus;
import com.dzqc.resource.status.ResourceTypeStatus;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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

    @Override
    public Page<OrgResourceV2> fingByParams(String name, int page, int size) {
        return this.dao.findAll((root, criteriaQuery, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(name)){
                list.add(cb.like(root.get("name"), name));
            }
            return cb.and(list.toArray(new Predicate[list.size()]));
        }, new PageRequest(page, size));
    }

    public List<OrgResourceV2> findAll() {
        List<OrgResourceV2> list = this.dao.findAll();

        List<OrgResourceV2> systems = new ArrayList<>();
        List<OrgResourceV2> modules = new ArrayList<>();
        List<OrgResourceV2> menus = new ArrayList<>();
        List<OrgResourceV2> buttons = new ArrayList<>();

        for (OrgResourceV2 orgResourceV2 : list){
            if (orgResourceV2.getType().equals(ResourceTypeStatus.SYSTEM.getCode())){
                systems.add(orgResourceV2);
            }
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

        menus = this.setChildList(menus, buttons);
        modules = this.setChildList(modules, menus);

        return this.setChildList(systems, modules);
    }

    @Override
    public List<OrgResourceV2> findSystem() {
        return this.dao.findAllByType(ResourceTypeStatus.SYSTEM.getCode());
    }

    public List<OrgResourceV2> findModule() {
        List<OrgResourceV2> list = this.dao.findAllByType(ResourceTypeStatus.MODULE.getCode());
        return list;
    }

    public List<OrgResourceV2> findMenu() {
        return this.dao.findAllByType(ResourceTypeStatus.MENU.getCode());
    }

    @Override
    public OrgResourceV2 findOne(String id) {
        return this.dao.findOne(id);
    }

    /**
     * 根据父资源列表和孩子资源列表，组装数据
     * @param parents 父资源列表
     * @param children 孩子资源列表
     */
    private List<OrgResourceV2> setChildList(List<OrgResourceV2> parents,
                                             List<OrgResourceV2> children){
        for (OrgResourceV2 parent : parents){
            List<OrgResourceV2> childList = new ArrayList<>();
            if (parents.size() > 0){
                for (OrgResourceV2 child : children){
                    if (child.getParentId().equals(parent.getId())){
                        childList.add(child);
                    }
                }
                if (childList.size() > 0){
                    children.removeAll(childList);
                    parent.setChildList(childList);
                }
            }
        }
        return parents;
    }

    public void add(OrgResourceV2 orgResourceV2) {
        if (this.resourceIsExist(orgResourceV2.getName())){
            throw new BusinessException("已经存在名称为【" + orgResourceV2.getName() + "】的资源了");
        }

        orgResourceV2.setDefaultData();

        this.dao.saveAndFlush(orgResourceV2);
    }

    /**
     * 根据资源名称，判断资源是否存在
     * @param name 资源名称
     */
    private boolean resourceIsExist(String name){
        return CollectionUtils.isNotEmpty( this.dao.findByNameIs(name));
    }

    @Override
    public void update(OrgResourceV2 orgResourceV2) {
        if (this.typeIsModify(orgResourceV2.getId(), orgResourceV2.getType())){
            throw new BusinessException("修改资源，不能修改资源类型！");
        }
        this.dao.saveAndFlush(orgResourceV2);
    }

    /**
     * 根据资源ID和类型，查看资源类型是否修改
     * @param id 资源ID
     * @param type 资源类型
     */
    private boolean typeIsModify(String id, Integer type){
        OrgResourceV2 orgResourceV2 = this.dao.findOne(id);
        if (orgResourceV2.getType().equals(type)){
            return false;
        }
        return true;
    }

    @Override
    public void frozen(String id) {
        this.dao.updateStatusById(id, ResourceStatus.FROZEN.getCode());
    }

    @Override
    public void thaw(String id) {
        this.dao.updateStatusById(id, ResourceStatus.NORMAL.getCode());
    }

}
