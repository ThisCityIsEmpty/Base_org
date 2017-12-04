package com.dzqc.base.entity;

/**
 * @author : gongfangchao
 * @describe : TODO
 * @date : 2017/12/4
 **/
public class LayuiPageResult {

    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 总条数
     */
    private Integer count;

    /**
     * 数据
     */
    private Object data;

    public LayuiPageResult() {
    }

    public LayuiPageResult(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static LayuiPageResult success(Integer count, Object data){
        return new LayuiPageResult(0, null, count, data);
    }

}
