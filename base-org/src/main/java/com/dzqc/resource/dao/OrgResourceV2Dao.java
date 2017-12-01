package com.dzqc.resource.dao;

import com.dzqc.base.dao.CommonDao;
import com.dzqc.resource.entity.OrgResourceV2;

import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 菜单资源DAO层
 * @date : 2017/11/30
 **/

public interface OrgResourceV2Dao extends CommonDao<OrgResourceV2, String> {

    /**
     * 根据类型，查找资源
     * @param type 资源类型
     */
    List<OrgResourceV2> findAllByType(Integer type);
}
