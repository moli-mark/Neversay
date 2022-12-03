package com.never.controller;

import com.never.pojo.Student;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author molimark<br />
 * @date: 2022/11/17 23:14<br/>
 * @description: <br/>
 */

@Controller
@Api(tags = "测试类")
@RequestMapping("/hello")
public class HelloController {
    @ApiOperation(value = "hello测试接口",notes = "hello test")
    @GetMapping("/hi")
    public String hello(@ApiParam(name = "student",value = "学生",required = true)@RequestBody Student student){
        return "hello";
    }

    @PostMapping("/hello")
    @ResponseBody
    public String hi(@RequestBody String name){
        System.out.println(name);
        System.out.println("hi");
        String s = "helloworld";
        return s;
    }
}
