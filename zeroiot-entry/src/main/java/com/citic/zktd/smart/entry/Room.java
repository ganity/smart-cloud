package com.citic.zktd.smart.entry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;;
@Data
@Entity
@Table(name = "z_room")

//@DynamicInsert //动态插入
//@DynamicUpdate //动态更新
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Room implements Serializable {
    /**
     * z_room.id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * z_room.room_name (房屋编号or户号)
     */
//    @Column(name = "room_name")
    private String roomName;

    /**
     * z_room.unit_id (单元编号)
     */
//    @Column(name = "unit_id")
    private Long unitId;

    /**
     * z_room.housetype_id (户型Id)
     */
//    @Column(name = "housetype_id")
    private Long housetypeId;

    /**
     * z_room.floor (所在层)
     */
    private Integer floor;

    /**
     * z_room.area (面积)
     */
    private Integer area;

    /**
     * z_room.notes (附加说明)
     */
    private String notes;

    /**
     * z_room.create_time
     */
    private Timestamp createTime;

    /**
     * z_room.update_time
     */
    private Timestamp updateTime;

    /**
     * z_room.create_where (数据创建来源(1,web 2APP 3...))
     */
    private Integer createWhere;

    /**
     * z_room.state
     */
    private Integer state;

}