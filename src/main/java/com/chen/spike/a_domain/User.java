package com.chen.spike.a_domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User{

    /**
     * 用户主键
     */
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * 手机号
     */
    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    /**
     * 用户性别
     */
    @Column(name = "user_garden", nullable = false)
    private Integer userGarden;

    /**
     * 用户年龄
     */
    @Column(name = "user_age", nullable = false)
    private Integer userAge;

    /**
     * 用户密码
     */
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    /**
     * 创建人
     */
    @Column(name = "creator", nullable = false)
    private Integer creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "modify", nullable = false)
    private Integer modify;

    /**
     * 修改时间
     */
    @Column(name = "modify_time", nullable = false)
    private Date modifyTime;

    /**
     * 数据版本
     */
    @Column(name = "version", nullable = false)
    private Integer version;

}
