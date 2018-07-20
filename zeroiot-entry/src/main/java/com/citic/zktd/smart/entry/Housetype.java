package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_housetype")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Housetype implements Serializable {
    /**
     * z_housetype.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_housetype.housetype_name
     */
    private String housetypeName;

    /**
     * z_housetype.community_id
     */
    private Long communityId;

    /**
     * z_housetype.create_time
     */
    private Timestamp createTime;

    /**
     * z_housetype.update_time
     */
    private Timestamp updateTime;

    /**
     * z_housetype.state
     */
    private Integer state;

}