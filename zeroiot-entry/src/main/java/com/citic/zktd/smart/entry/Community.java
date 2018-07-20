package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;

@Data
@Entity
@Table(name = "z_community")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Community implements Serializable {
    /**
     * z_community.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_community.community_name
     */
    private String communityName;

    /**
     * z_community.address
     */
    private String address;

    /**
     * z_community.organization_id (所属机构/公司id)
     */
    private Long organizationId;

    /**
     * z_community.create_time
     */
    private Timestamp createTime;

    /**
     * z_community.update_time
     */
    private Timestamp updateTime;

    /**
     * z_community.state
     */
    private Integer state;


}