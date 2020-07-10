package com.search.service;

import com.search.mapper.RealtimeMapper;
import com.search.model.Price;
import com.search.model.Realtime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RealtimeServiceImpl implements RealtimeService{

    @Resource
    RealtimeMapper realtimeMapper;

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
            res.add(price);
            return res;
        }

        return res;
    }
}
