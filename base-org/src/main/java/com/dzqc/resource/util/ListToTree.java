package com.dzqc.resource.util;

import com.dzqc.resource.entity.OrgResourceV2;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 递归循环List转成Tree
 * @date : 2017/12/6
 **/
public class ListToTree {

    /**
     * 根节点名称
     */
    private final static String ROOT_NAME = "资源";

    /**
     * 根节点ID
     */
    private final static String ROOT_ID = "4FDB348DC387488681D4A9224A4F602E";

    /**
     * 递归建立资源树
     * @param list 模块和菜单资源列表
     */
    public static OrgResourceV2 buildByRecursive(List<OrgResourceV2> list){
        OrgResourceV2 root = new OrgResourceV2(ROOT_NAME);

        List<OrgResourceV2> trees = new ArrayList<>();
        for (OrgResourceV2 treeNode : list) {
            if (StringUtils.isEmpty(treeNode.getParentId()) || treeNode.getParentId().equals(ROOT_ID)) {
                trees.add(findChildren(treeNode, list));
            }
        }
        root.setChildren(trees);

        return root;
    }

    /**
     * 递归查找子节点
     * @param treeNode 递归当前节点
     * @param treeNodes 模块和菜单资源列表
     */
    public static OrgResourceV2 findChildren(OrgResourceV2 treeNode, List<OrgResourceV2> treeNodes) {
        for (OrgResourceV2 it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}
