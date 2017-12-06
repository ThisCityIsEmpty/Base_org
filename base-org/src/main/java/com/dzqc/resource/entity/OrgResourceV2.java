package com.dzqc.resource.entity;

import com.dzqc.base.util.ObjectUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : gongfangchao
 * @describe : 资源V2
 * @date : 2017/11/30
 **/
@Entity
@Table(name = "ORG_RESOURCE_V2", schema = "SCOTT")
public class OrgResourceV2 implements Serializable {


    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     *  类型 1：模块 2：菜单 3：按钮
     */
    private Integer type;

    /**
     *  url
     */
    private String url;

    /**
     *  资源代码
     */
    private String authCode;

    /**
     *  图标
     */
    private String icon;

    /**
     *  排序
     */
    private Integer orderNo;

    /**
     *  父ID
     */
    private String parentId;

    /**
     *  是否删除 1：没有删除 0：删除
     */
    private Boolean deleteFlag;

    /**
     *  创建时间
     */
    private Date createTime;

    private List<OrgResourceV2> children;

    public OrgResourceV2() {
    }

    public OrgResourceV2(String name) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.authCode = authCode;
        this.icon = icon;
        this.orderNo = orderNo;
        this.parentId = parentId;
        this.deleteFlag = deleteFlag;
        this.createTime = createTime;
    }

    public OrgResourceV2(String id, String name, Integer type, String url, String authCode,
                         String icon, Integer orderNo, String parentId, Boolean deleteFlag,
                         Date createTime) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.authCode = authCode;
        this.icon = icon;
        this.orderNo = orderNo;
        this.parentId = parentId;
        this.deleteFlag = deleteFlag;
        this.createTime = createTime;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAME", length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "URL", length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "AUTH_CODE", length = 50)
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Column(name = "ICON", length = 50)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "ORDER_NO")
    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "PARENT_ID", length = 32)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "DELETE_FLAG")
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Column(name = "CREATE_TIME", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Transient
    public List<OrgResourceV2> getChildren() {
        return children;
    }

    public void setChildren(List<OrgResourceV2> children) {
        this.children = children;
    }

    public void setDefaultData(){
        this.id = ObjectUtil.createUUID();
        this.deleteFlag = Boolean.FALSE;
        this.createTime = new Date();
    }
}
