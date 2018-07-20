package com.citic.zktd.smart.controller;

import com.citic.zktd.smart.entry.Room;
import com.citic.zktd.smart.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/6/26 16:04
 */
@RestController
@RequestMapping("/room2")
//@RefreshScope
public class Room2Controller {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.findOneById(id);
    }


}
