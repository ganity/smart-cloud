package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_systemlog")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Systemlog implements Serializable {
    /**
     * z_systemlog.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_systemlog.resource_id
     */
    private Integer resourceId;

    /**
     * z_systemlog.user_id
     */
    private Long userId;

    /**
     * z_systemlog.room_id (操作的roomid,有就填,没有不填)
     */
    private Long roomId;

    /**
     * z_systemlog.log_type (数据字典)
     */
    private Integer logType;

    /**
     * z_systemlog.log_describe (日志描述信息)
     */
    private String logDescribe;

    /**
     * z_systemlog.create_time
     */
    private Timestamp createTime;



}