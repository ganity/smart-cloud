package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;

@Data
@Entity
@Table(name = "z_building")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Building implements Serializable {
    /**
     * z_building.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_building.building_name (幢号)
     */
    private String buildingName;

    /**
     * z_building.community_id (小区编号)
     */
    private Long communityId;

    /**
     * z_building.floors (总层数)
     */
    private Integer floors;

    /**
     * z_building.create_time
     */
    private Timestamp createTime;

    /**
     * z_building.update_time
     */
    private Timestamp updateTime;

    /**
     * z_building.create_where
     */
    private Integer createWhere;

    /**
     * z_building.state
     */
    private Integer state;


}