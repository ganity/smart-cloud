package com.citic.zktd.smart.service.impl;

import com.citic.zktd.smart.entry.Room;
import com.citic.zktd.smart.repository.RoomRepository;
import com.citic.zktd.smart.service.CommonServiceImpl;
import com.citic.zktd.smart.service.RoomService;
import com.citic.zktd.smart.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/18 10:36
 */
@Service
public class RoomServiceImpl extends CommonServiceImpl<RoomRepository, Room, Long> implements RoomService {
//    @Resource
//    private RoomRepository roomRepository;
    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
@Override
@Cacheable(value = "rooms", key = "#id")
public Room findOneById(Long id) {
    return super.findOneById(id);
}

    @Override
    @CacheEvict(value = "rooms", key = "#id")
    public <S extends Room> S updateIgnoreNull(S var1, Long id) {
        log.info("update room with id = {}", id);
        return super.updateIgnoreNull(var1, id);
    }

//    @Override
//    public Room update(Room room) {
//        System.err.println("room service update");
//        Room roomdb = findOneById(room.getId());
//        System.err.println(room);
//        System.err.println(roomdb);
//        BeanCopierUtils.copyPropertiesIgnoreNull(room, roomdb);
//        System.err.println(roomdb);
//        return super.update(roomdb);
//    }
}
