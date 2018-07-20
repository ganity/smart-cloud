package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_user_role")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UserRole implements Serializable {
    /**
     * z_user_role.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_user_role.role_id
     */
    private Long roleId;

    /**
     * z_user_role.user_id
     */
    private Long userId;

    /**
     * z_user_role.create_time
     */
    private Timestamp createTime;

    /**
     * z_user_role.update_time
     */
    private Timestamp updateTime;


}