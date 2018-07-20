package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "z_dept_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class DeptUser implements Serializable {
    /**
     * z_dept_user.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_dept_user.dept_id
     */
    private Long deptId;

    /**
     * z_dept_user.user_id
     */
    private Long userId;

    /**
     * z_dept_user.create_time
     */
    private Timestamp createTime;

    /**
     * z_dept_user.update_time
     */
    private Timestamp updateTime;


}