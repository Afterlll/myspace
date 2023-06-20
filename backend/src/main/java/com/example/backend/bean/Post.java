package com.example.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户帖子
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * 帖子唯一性id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 哪个用户发的帖子
     */
    private Integer userId;

    /**
     * 帖子详情
     */
    private String content;

    /**
     * 帖子创建的时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String photo;
    @TableField(exist = false)
    private Integer followerCount;
}