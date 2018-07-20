package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "z_role_resource")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RoleResource implements Serializable {
    /**
     * z_role_resource.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_role_resource.resource_id
     */
    private Long resourceId;

    /**
     * z_role_resource.role_id
     */
    private Long roleId;

    /**
     * z_role_resource.create_time
     */
    private Timestamp createTime;

    /**
     * z_role_resource.update_time
     */
    private Timestamp updateTime;


}