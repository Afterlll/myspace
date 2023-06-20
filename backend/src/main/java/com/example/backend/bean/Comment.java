package com.example.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 保存帖子评论的表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论唯一性id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评论的帖子id
     */
    private Integer postId;

    /**
     * 评论的用户id
     */
    private Integer userId;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 评论的时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String photo;
}