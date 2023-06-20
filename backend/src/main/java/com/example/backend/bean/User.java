package com.example.backend.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户唯一性id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 粉丝数量
     */
    private Integer followerCount;

    /**
     * 头像
     */
    private String photo;

    /**
     * 生日，用于找回密码
     */
    private String birth;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Integer isFollow;

    /**
     * 返回给前端的登录错误信息
     * 用户名不存在 密码错误....
     */
    @TableField(exist = false)
    private String errorMessage;
}