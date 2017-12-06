package com.dzqc.resource.service;

import com.dzqc.base.AbstractJUnit;
import com.dzqc.resource.entity.OrgResourceV2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : gongfangchao
 * @describe : 菜单资源测试类
 * @date : 2017/11/30
 **/
public class OrgResourceV2ServiceTest extends AbstractJUnit {

    @Autowired
    private OrgResourceV2Service service;

    @Test
    public void findAll() throws Exception {
        List<OrgResourceV2> list = this.service.findAll();
        assertTrue(list.size() == 0);
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void findModuleAndMenu() throws Exception {
        OrgResourceV2 root = this.service.findModuleAndMenu();
        assertNotNull(root);
    }



}