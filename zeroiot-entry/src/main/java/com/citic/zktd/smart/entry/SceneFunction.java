package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_scene_function")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SceneFunction implements Serializable {
    /**
     * z_scene_function.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_scene_function.scene_id (场景id)
     */
    private Long sceneId;

    /**
     * z_scene_function.function_id (控制功能id)
     */
    private Long functionId;

    /**
     * z_scene_function.cmd_value (控制命令的值)
     */
    private String cmdValue;

    /**
     * z_scene_function.create_time
     */
    private Timestamp createTime;

    /**
     * z_scene_function.update_time
     */
    private Timestamp updateTime;


}