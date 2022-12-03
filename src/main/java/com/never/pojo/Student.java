package com.never.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author molimark<br />
 * @date: 2022/11/18 0:35<br/>
 * @description: <br/>
 */

@ApiModel(value = "Student对象",description = "学生类")
public class Student {
    @ApiModelProperty(value = "姓名",name = "name",example = "liming",required = true)
    public String name;
    @ApiModelProperty(value = "年龄",name = "age",example = "18")
    public Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
