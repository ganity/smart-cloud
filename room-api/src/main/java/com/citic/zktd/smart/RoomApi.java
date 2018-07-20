package com.citic.zktd.smart;

import com.citic.zktd.smart.entry.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:
 * @Description:
 * @Date: Create in 2018/7/18 10:51
 */
@Component
@FeignClient(value = "room-service", fallback = RoomApiHystrix.class)
public interface RoomApi {
    @GetMapping("room/{id}")
    Room getRoom(@PathVariable("id") Long id);
}
