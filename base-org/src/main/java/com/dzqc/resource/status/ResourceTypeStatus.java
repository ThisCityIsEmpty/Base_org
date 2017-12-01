package com.dzqc.resource.status;

/**
 * @author : gongfangchao
 * @describe : 资源类型枚举
 * @date : 2017/12/1
 **/
public enum ResourceTypeStatus {

    MODULE(1, "模块"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");

    private Integer code;

    private String name;

    ResourceTypeStatus(Integer code, String name){
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
