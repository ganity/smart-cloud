package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.Room;
import com.citic.zktd.smart.util.BeanCopierUtils;
import org.junit.Test;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/19 18:36
 */
public class BeanCopierUtilsTest {

    @Test
    public void copyPropertiesTest () {
        Room room = new Room();
        room.setId(1l);
        room.setRoomName("hello");
        room.setFloor(90);
        Room room2 = new Room();
        room2.setId(2l);
        room2.setRoomName("hello3");
        room2.setHousetypeId(5l);
//        BeanCopierUtils.copyProperties(room, room2);

//        BeanUtils.copyProperties(room, room2);
        BeanCopierUtils.copyPropertiesIgnoreNull(room, room2);
        System.err.println(room.toString());
        System.err.println(room2.toString());
    }

}
