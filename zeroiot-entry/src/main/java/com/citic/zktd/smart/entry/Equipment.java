package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_equipment")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Equipment implements Serializable {
    /**
     * z_equipment.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_equipment.equipment_name (设备名称)
     */
    private String equipmentName;

    /**
     * z_equipment.equipmenttype_id (设备类型id,新建设备时选择设备类型,可以自动添加该类型设备相关的一些控制地址等,或者让用户选择)
     */
    private Integer equipmenttypeId;

    /**
     * z_equipment.bedroom_id (房间id没有时为0)
     */
    private Long bedroomId;

    /**
     * z_equipment.source_id (数据源id(户型,房屋))
     */
    private Long sourceId;

    /**
     * z_equipment.source_type (数据源类型(1:房屋,2:户型))
     */
    private Integer sourceType;

    /**
     * z_equipment.keywords (关键字)
     */
    private String keywords;

    /**
     * z_equipment.sn (设备sn编号)
     */
    private String sn;

    /**
     * z_equipment.protocol_type (控制协议类型(knx,485)数据字典获取)
     */
    private String protocolType;

    /**
     * z_equipment.create_time
     */
    private Timestamp createTime;

    /**
     * z_equipment.update_time
     */
    private Timestamp updateTime;

    /**
     * z_equipment.state
     */
    private Integer state;

}