package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_resource")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Resource implements Serializable {
    /**
     * z_resource.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_resource.resource_name (菜单名称)
     */
    private String resourceName;

    /**
     * z_resource.resource_type (类型(1:目录,2:菜单,3:按钮))
     */
    private Integer resourceType;

    /**
     * z_resource.system_id
     */
    private Long systemId;

    /**
     * z_resource.parent_id (上级菜单编号)
     */
    private Long parentId;

    /**
     * z_resource.url (请求地址)
     */
    private String url;

    /**
     * z_resource.icon (节点图标(或CSS类名))
     */
    private String icon;

    /**
     * z_resource.sort_no (排序号)
     */
    private Byte sortNo;

    /**
     * z_resource.create_time
     */
    private Timestamp createTime;

    /**
     * z_resource.update_time
     */
    private Timestamp updateTime;

    /**
     * z_resource.state (菜单状态(1:启用   0:不启用, -1:删除))
     */
    private Integer state;

}