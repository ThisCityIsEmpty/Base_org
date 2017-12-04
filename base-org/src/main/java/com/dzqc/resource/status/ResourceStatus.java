package com.dzqc.resource.status;

/**
 * @author : gongfangchao
 * @describe : 资源删除标志
 * @date : 2017/12/4
 **/
public enum ResourceStatus {

    NORMAL(0, "正常"),
    FROZEN(1, "冻结");

    private Integer code;

    private String name;

    ResourceStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
