package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/6/26 15:57
 */
@Data
@Entity
@Table(name = "z_user")

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {
    /**
     * z_user.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_user.user_account (用户登录账户(手机号))
     */
    private String userAccount;

    /**
     * z_user.user_name (用户名)
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * z_user.user_password
     */
    @JsonIgnore
    private String userPassword;

    /**
     * z_user.avatar (用户头像)
     */
    private String avatar;

    /**
     * z_user.user_type (用户类型(1普通用户2管理员3系统用户))
     */
    private Integer userType;

    /**
     * z_user.email
     */
    private String email;

    /**
     * z_user.gender (性别(0:未知;1:男;2:女))
     */
    private Integer gender;

    /**
     * z_user.birthday
     */
    private Date birthday;

    /**
     * z_user.nick_name (姓名)
     */
    private String nickName;

    /**
     * z_user.create_time (注册时间)
     */
    private Timestamp createTime;

    /**
     * z_user.create_where (创建来源(0:未知;1.web 2.android 3.ios 4...))
     */
    private String createWhere;

    /**
     * z_user.update_time
     */
    private Timestamp updateTime;

    /**
     * z_user.last_time (最后登录时间)
     */
    private Timestamp lastTime;

    /**
     * z_user.last_ip (最后登录IP)
     */
    private String lastIp;

    /**
     * z_user.state (账户状态(1：启用  2：冻结  3：删除）)
     */
    private Integer state;

}
