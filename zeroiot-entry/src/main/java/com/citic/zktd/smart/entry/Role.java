package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_role")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Role implements Serializable {
    /**
     * z_role.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_role.role_name (角色名称)
     */
    private String roleName;

    /**
     * z_role.role_type (角色类型(1:业务角色;2:管理角色 ;3:系统内置角色))
     */
    private Integer roleType;

    /**
     * z_role.create_time
     */
    private Timestamp createTime;

    /**
     * z_role.update_time
     */
    private Timestamp updateTime;

    /**
     * z_role.state
     */
    private Integer state;
}