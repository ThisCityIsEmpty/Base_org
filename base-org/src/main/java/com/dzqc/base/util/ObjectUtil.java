package com.dzqc.base.util;

import java.util.UUID;

/**
 * @author : gongfangchao
 * @describe : TODO
 * @date : 2017/12/1
 **/
public class ObjectUtil {

    /**
     * 生成32位的UUID
     */
    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
