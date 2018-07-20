package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;

@Data
@Entity
@Table(name = "z_bedroom")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Bedroom implements Serializable {
    /**
     * z_bedroom.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_bedroom.bedroom_name (房间名字)
     */
    private String bedroomName;

    /**
     * z_bedroom.source_id (数据源id(户型,房屋))
     */
    private Long sourceId;

    /**
     * z_bedroom.source_type (数据源类型(1:房屋,2:户型))
     */
    private Integer sourceType;

    /**
     * z_bedroom.image_url (房间的背景图片)
     */
    private String imageUrl;

    /**
     * z_bedroom.create_where (创建来源)
     */
    private Integer createWhere;

    /**
     * z_bedroom.create_time
     */
    private Timestamp createTime;

    /**
     * z_bedroom.update_time
     */
    private Timestamp updateTime;

    /**
     * z_bedroom.state
     */
    private Integer state;
}