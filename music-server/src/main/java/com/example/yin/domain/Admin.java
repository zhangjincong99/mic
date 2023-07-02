package com.example.yin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Admin {
    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
//    如果不为空则让this.name = name，并且去除首尾空格（name.trim()），否则设置为空

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
//    @Override注解用于标记toString()方法
//    ToStringBuilder这种方式通常比手动编写toString()方法更方便，因为它可以自动处理对象的所有字段。
}
