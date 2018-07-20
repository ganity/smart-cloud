package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_equipmenttype")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Equipmenttype implements Serializable {
    /**
     * z_equipmenttype.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_equipmenttype.equipmenttype_name
     */
    private String equipmenttypeName;

    /**
     * z_equipmenttype.protocol_type
     */
    private String protocolType;

    /**
     * z_equipmenttype.create_time
     */
    private Timestamp createTime;

    /**
     * z_equipmenttype.update_time
     */
    private Timestamp updateTime;

    /**
     * z_equipmenttype.state
     */
    private Integer state;


}