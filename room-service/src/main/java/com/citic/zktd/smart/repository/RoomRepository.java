package com.citic.zktd.smart.repository;

import com.citic.zktd.smart.entry.Room;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/6 18:05
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    /**
     *复杂JPA操作  使用@Query()自定义sql语句  根据业务id UId去更新整个实体
     * 删除和更新操作，需要@Modifying和@Transactional注解的支持
     *
     * 更新操作中 如果某个字段为null则不更新，否则更新【注意符号和空格位置】
     *
     * @param room   传入实体，分别取实体字段进行set
     * @return  更新操作返回sql作用条数
     */
//    @Modifying
//    @Transactional
//    @Query("update Room r set " +
//            "r.roomName = CASE WHEN :#{#room.roomName} IS NULL THEN r.roomName ELSE :#{#room.roomName} END ," +
//            "r.unitId = CASE WHEN :#{#room.unitId} IS NULL THEN r.unitId ELSE :#{#room.unitId} END ," +
//            "r.housetypeId = CASE WHEN :#{#room.housetypeId} IS NULL THEN r.housetypeId ELSE :#{#room.housetypeId} END ," +
//            "r.floor = CASE WHEN :#{#room.floor} IS NULL THEN r.floor ELSE :#{#room.floor} END , " +
//            "r.state = CASE WHEN :#{#room.state} IS NULL THEN r.state ELSE :#{#room.state} END " +
//            "where r.id = :#{#room.id}")
//    int update(@Param("room") Room room);
}
