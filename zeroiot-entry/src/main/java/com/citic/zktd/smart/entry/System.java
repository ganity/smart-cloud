package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_system")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class System implements Serializable {
    /**
     * z_system.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_system.system_name (子系统名称)
     */
    private String systemName;

    /**
     * z_system.icon (图标)
     */
    private String icon;

    /**
     * z_system.base_path (子系统地址(/user, /image))
     */
    private String basePath;

    /**
     * z_system.create_time
     */
    private Timestamp createTime;

    /**
     * z_system.update_time
     */
    private Timestamp updateTime;

    /**
     * z_system.state
     */
    private Integer state;

}