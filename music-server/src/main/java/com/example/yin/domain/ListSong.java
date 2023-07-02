package com.example.yin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
//由于该类实现了Serializable接口，它可以在网络传输、持久化到磁盘或通过其他方式进行对象的序列化和反序列化操作。
//这在分布式系统、缓存、消息队列等场景中非常有用。
public class ListSong implements Serializable {

    private Integer id;

    private Integer songId;

    private Integer songListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
