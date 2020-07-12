package com.search.service;

import com.alibaba.fastjson.JSONObject;
import com.search.mapper.RealtimeMapper;
import com.search.mapper.TigerMapper;
import com.search.model.Price;
import com.search.model.Realtime;
import com.search.model.TigerInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RealtimeServiceImpl implements RealtimeService{

    @Resource
    RealtimeMapper realtimeMapper;
    @Resource
    TigerMapper tigerMapper;

    @Override
    public List<Realtime> getAll() {

        return realtimeMapper.selectAll();
    }

    @Override
    public List<Price> getIndex(String tscode) {
        List<Price> res = realtimeMapper.selectIndex(tscode);

        if (res.size() == 0) {
            Price price = new Price();
            price.setDtime("out of date");
            price.setPrice("-1");
            res.add(price);
            return res;
        }

        return res;
    }

    @Override
    public List<TigerInfo> getTigerDragon() {
        List<TigerInfo> chg_list = tigerMapper.selectByPCT();
        chg_list.addAll(tigerMapper.selectByTurnover());

        return chg_list;
    }
}
