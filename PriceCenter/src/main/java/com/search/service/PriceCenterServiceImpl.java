package com.search.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.mapper.DailyMapper;
import com.search.model.Price;
import com.search.model.StockInfo;
import com.search.provider.RedisProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
public class PriceCenterServiceImpl implements PriceCenterService{

    @Resource
    DailyMapper dailyMapper;
    @Resource
    RedisProvider redisProvider;

    @Override
    public JSONObject GetAllInfo() {
        JSONObject res = new JSONObject();

        String[] marketList = {"IT设备", "白酒", "百货", "半导体", "保险", "玻璃", "仓储物流", "超市连锁", "出版业", "船舶", "电气设备", "电器连锁", "电器仪表", "电信运营", "多元金融", "房产服务", "纺织", "纺织机械", "服饰", "钢加工", "港口", "工程机械", "公共交通", "公路", "供气供热", "广告包装", "航空", "红黄酒", "互联网", "化工机械", "化工原料", "化纤", "化学制药", "环境保护", "黄金", "火力发电", "机场", "机床制造", "机械基件", "家居用品", "家用电器", "建筑工程", "焦炭加工", "酒店餐饮", "空运", "矿物制品", "林业", "路桥", "旅游服务", "旅游景点", "铝", "煤炭开采", "摩托车", "农药化肥", "农业综合", "农用机械", "批发业", "啤酒", "普钢", "其他建材", "其他商业", "汽车服务", "汽车配件", "汽车整车", "铅锌", "轻工机械", "区域地产", "全国地产", "染料涂料", "日用化工", "乳制品", "软件服务", "软饮料", "商贸代理", "商品城", "生物制药", "石油加工", "石油开采", "石油贸易", "食品", "水力发电", "水泥", "水务", "水运", "饲料", "塑料", "陶瓷", "特种钢", "铁路", "通信设备", "铜", "文教休闲", "橡胶", "小金属", "新型电力", "医疗保健", "医药商业", "银行", "影视音像", "渔业", "元器件", "园区开发", "运输设备", "造纸", "证券", "中成药", "种植业", "专用机械", "装修装饰", "综合类"};
        res.put("markets", marketList);

        long startTime=System.currentTimeMillis();


        String maxDate = dailyMapper.GetMaxDate();

        List<StockInfo> stockInfoList = dailyMapper.SelectByDate(maxDate);

        Iterator iterator = stockInfoList.iterator();
        while(iterator.hasNext()) {
            StockInfo temp = (StockInfo) iterator.next();
            String ts_code = temp.getTsCode();

            String price = redisProvider.getNowPrice(ts_code);
            int leftIndex = price.lastIndexOf(", '") + 3;
            int rightIndex = price.lastIndexOf("'");
            String curStrPrice = price.substring(leftIndex, rightIndex);
            double currentPrice = Double.parseDouble(curStrPrice);

            System.out.println(currentPrice);
            temp.setTsCode(ts_code.replace(".", ""));
            temp.setCurrentPrice(currentPrice);
            temp.setChange(currentPrice-temp.getClose());
            Double pctChg = temp.getChange()/temp.getClose() * 100;
            temp.setPctChg(Double.parseDouble(String.format("%.2f", pctChg )));
        }

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

        res.put("stocks", stockInfoList);
        res.put("state", true);

        return res;

    }
}
