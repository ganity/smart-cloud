package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_controller")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Controller implements Serializable {
    /**
     * z_controller.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_controller.sn
     */
    private String sn;

    /**
     * z_controller.room_id
     */
    private Long roomId;

    /**
     * z_controller.internel_ip
     */
    private String internelIp;

    /**
     * z_controller.create_time
     */
    private Timestamp createTime;

    /**
     * z_controller.update_time
     */
    private Timestamp updateTime;

    /**
     * z_controller.state
     */
    private Integer state;


}