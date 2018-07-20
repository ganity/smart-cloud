package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;

@Data
@Entity
@Table(name = "z_protocol")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Protocol implements Serializable {
    /**
     * z_protocol.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_protocol.protocol_addr (协议地址)
     */
    private String protocolAddr;

    /**
     * z_protocol.protocol_name (协议名称(开关,暂停,温度..))
     */
    private String protocolName;

    /**
     * z_protocol.dpid (knx会有dp_id)
     */
    private String dpid;

    /**
     * z_protocol.byte_size (所以发命令占用字节大小)
     */
    private Integer byteSize;

    /**
     * z_protocol.bedroom_id (房间id(三衡空调这种))
     */
    private Long bedroomId;

    /**
     * z_protocol.source_id (数据源id(数据源id(设备,设备类型产品库)))
     */
    private Long sourceId;

    /**
     * z_protocol.source_type (数据源类型(1:设备,2:产品库))
     */
    private Integer sourceType;

    /**
     * z_protocol.create_time
     */
    private Timestamp createTime;

    /**
     * z_protocol.update_time
     */
    private Timestamp updateTime;

    /**
     * z_protocol.state
     */
    private Integer state;

}