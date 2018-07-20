package com.citic.zktd.smart.controller;

import com.citic.zktd.smart.entry.Room;
import com.citic.zktd.smart.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/6/26 16:04
 */
@RestController
@RequestMapping("/room")
//@RefreshScope
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable Long id) {
        return roomService.findOneById(id);
    }

    @GetMapping("/name/{roomname}")
    public Room getRoomByName(@PathVariable String roomname) {
        Room ex = new Room();
        ex.setRoomName(roomname);
//        Example example = Example.of(ex);
//        Optional<Room> roomOptional = roomRepository.findOne(example);
        System.err.println("roomname:"+roomname);
        return ex;
    }



    @PostMapping
    public Room insert(Room room) {
        return roomService.insert(room);
    }

    @PutMapping
    public Room update(Room room) {
        return roomService.updateIgnoreNull(room, room.getId());
    }

    @GetMapping("/page")
    public Page<Room> getAllPage(Room room, @PageableDefault Pageable pageable) {
        System.err.println(room.toString());
        Page<Room> rooms = roomService.findByPage(room, pageable);
        return rooms;
    }
    @GetMapping
    public List<Room> getAll(Room room) {
        System.err.println(room.toString());
        List<Room> rooms = roomService.findAll(room);
        return rooms;
    }
    @GetMapping("/one")
    public Room findOneByExample(Room room) {
        System.err.println(room.toString());
        Room rooms = roomService.findOneByExample(room).get();
        return rooms;
    }
//    @DeleteMapping
//    public void delete(@RequestBody Room room) {
//        roomService.delete(room);
//    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        roomService.delete(id);
    }


}
