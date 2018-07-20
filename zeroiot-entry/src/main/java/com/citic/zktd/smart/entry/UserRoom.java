package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_user_room")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class UserRoom implements Serializable {
    /**
     * z_user_room.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_user_room.user_id
     */
    private Long userId;

    /**
     * z_user_room.room_id
     */
    private Long roomId;

    /**
     * z_user_room.ur_type (1普通主账户,2从账户)
     */
    private Integer urType;

    /**
     * z_user_room.create_time
     */
    private Timestamp createTime;

    /**
     * z_user_room.update_time
     */
    private Timestamp updateTime;


}