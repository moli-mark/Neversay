package com.never;

import com.alibaba.fastjson.JSON;
import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.Map;

/**
 * @author molimark<br />
 * @date: 2022/12/3 11:33<br/>
 * @description: <br/>
 */
public class MyTest {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        Integer[] a = new Integer[10];
        map.put("code",200);
        a[0] = 1;
        a[1] = 2;
        map.put("array",a);
        System.out.println(JSON.toJSONString(map));
    }
}
