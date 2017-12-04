package com.dzqc.resource.dao;

import com.dzqc.base.dao.CommonDao;
import com.dzqc.resource.entity.OrgResourceV2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * 根据资源名称，查找资源
     * @param name 资源名称
     */
    List<OrgResourceV2> findByNameIs(String name);

    /**
     * 根据资源ID，修改资源状态
     * @param id 资源ID
     * @param status 资源状态
     */
    @Modifying
    @Query(value = "UPDATE ORG_RESOURCE_V2 SET DELETE_FLAG = ?2 WHERE ID = ?1", nativeQuery = true)
    void updateStatusById(String id, Integer status);

}
