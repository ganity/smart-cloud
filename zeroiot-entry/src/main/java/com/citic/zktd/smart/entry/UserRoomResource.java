package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_user_room_resource")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UserRoomResource implements Serializable {
    /**
     * z_user_room_resource.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_user_room_resource.user_room_id (用户房屋关系id)
     */
    private Long userRoomId;

    /**
     * z_user_room_resource.resource_id (资源id)
     */
    private Long resourceId;

    /**
     * z_user_room_resource.create_time
     */
    private Timestamp createTime;

    /**
     * z_user_room_resource.update_time
     */
    private Timestamp updateTime;


}