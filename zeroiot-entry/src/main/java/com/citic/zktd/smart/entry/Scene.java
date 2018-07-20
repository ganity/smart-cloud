package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_scene")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Scene implements Serializable {
    /**
     * z_scene.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_scene.scene_name
     */
    private String sceneName;

    /**
     * z_scene.room_id (房屋id)
     */
    private Long roomId;

    /**
     * z_scene.housetype_id (户型id,不存在为0,与room_id不可共存)
     */
    private Long housetypeId;

    /**
     * z_scene.create_time
     */
    private Timestamp createTime;

    /**
     * z_scene.update_time
     */
    private Timestamp updateTime;

    /**
     * z_scene.state
     */
    private Integer state;

}