package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.model.Price;
import com.search.model.Realtime;
import com.search.model.TigerInfo;
import com.search.service.RealtimeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class RealtimePriceController {

    @Resource
    RealtimeService realtimeService;

    @RequestMapping(value = "/getRealtimeAll", method = RequestMethod.GET)
    public List<Realtime> getRealtimeAll() {

        return realtimeService.getAll();
    }

    @RequestMapping(value = "/getIndex/{tscode}", method = RequestMethod.GET)
    public List<Price> getIndex(@PathVariable String tscode) {
        return realtimeService.getIndex(tscode);
    }

    @RequestMapping(value = "/getTigerDragon", method = RequestMethod.GET)
    public List<TigerInfo> getTigerDragon() {
        return realtimeService.getTigerDragon();
    }
}
