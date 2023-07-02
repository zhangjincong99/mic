package com.example.yin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Singer {

    private Integer id;

    private String name;

    private Byte sex;

    private String pic;

    private Date birth;

    private String location;

    private String introduction;

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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
//    这段代码是一个重写的toString()方法。在toString()方法中，
//    使用ToStringBuilder类的reflectionToString()方法生成对象的字符串表示形式。
//    reflectionToString()方法会根据对象的字段和值自动生成字符串，它会遍历对象的所有非静态字段，
//    并将它们的名称和值拼接成一个字符串。这样可以方便地查看对象的属性值，用于调试和日志记录。
}
