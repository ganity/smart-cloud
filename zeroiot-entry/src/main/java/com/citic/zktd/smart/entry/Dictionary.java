package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_dictionary")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Dictionary implements Serializable {
    /**
     * z_dictionary.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_dictionary.dict_name (名称(如角色,管理员))
     */
    private String dictName;

    /**
     * z_dictionary.dict_value (值(如role, admin))
     */
    private String dictValue;

    /**
     * z_dictionary.parent_id (父id,值为0的是根节点)
     */
    private Long parentId;

    /**
     * z_dictionary.create_time
     */
    private Timestamp createTime;

    /**
     * z_dictionary.update_time
     */
    private Timestamp updateTime;

    /**
     * z_dictionary.state
     */
    private Integer state;

}