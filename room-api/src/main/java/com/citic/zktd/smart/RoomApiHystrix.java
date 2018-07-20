package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/18 11:09
 */
@Component
public class RoomApiHystrix implements RoomApi {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Room getRoom(Long id) {
        logger.info("执行RoomApiHystrix中的getRoom方法,id={}", id);
        Room room = new Room();
        room.setId(1l);
        room.setRoomName("101");
        return room;
    }
}
