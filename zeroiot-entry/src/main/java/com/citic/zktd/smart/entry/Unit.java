package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_unit")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Unit implements Serializable {
    /**
     * z_unit.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_unit.unit_name (单元名(一单元,..))
     */
    private String unitName;

    /**
     * z_unit.unit_no (单元编号(1,2,3..))
     */
    private Integer unitNo;

    /**
     * z_unit.building_id (幢编号)
     */
    private Long buildingId;

    /**
     * z_unit.create_time
     */
    private Timestamp createTime;

    /**
     * z_unit.update_time
     */
    private Timestamp updateTime;

    /**
     * z_unit.create_where (创建来源)
     */
    private Integer createWhere;

    /**
     * z_unit.state
     */
    private Integer state;


}