package com.search.controller;


import com.search.dto.Message;
import com.search.dto.StockDetailInfo;
import com.search.dto.StockDetailInfoMessage;
import com.search.mapper.StockDetailInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class StockDetailController {
    @Autowired
    StockDetailInfoMapper stockDetailInfoMapper;

    @PostMapping(value = "/getStockDetail")
    public Message getStockDetail(@RequestParam("ts_code") String ts_code,
                                  @RequestParam("token") String token)
    {
        StockDetailInfoMessage message = new StockDetailInfoMessage();
        StockDetailInfo stockDetailInfo ;

        stockDetailInfo = stockDetailInfoMapper.getNewDailyInfos(ts_code);

        String name = stockDetailInfoMapper.getNameByCode(ts_code);
        String symbol = stockDetailInfoMapper.getSymbolByCode(ts_code);
        double pe_ttm = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"pe_ttm");
        double pe = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"pe");
        double circ_mv = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"circ_mv");
        double total_mv = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"total_mv");
        double turnover_rate = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"turnover_rate");
        double dv_ratio = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"dv_ratio");
        double dv_ttm = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"dv_ttm");
        double total_share = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"total_share");
        double float_share = stockDetailInfoMapper.getNewDailyBasicInfo(ts_code,"float_share");



        //获取current_price,计算change,pct_change
        String[] split = ts_code.split("\\.");
        System.out.println("ts_code = "+ts_code);
        System.out.println(split.length);
        String urlStr = "http://hq.sinajs.cn/list=" +split[1].toLowerCase()+split[0];
        System.out.println("urlStr = "+urlStr);
        //String urlStr="http://hq.sinajs.cn/list=sz000001";
        URL url = null;
        //请求的输入流
        BufferedReader in = null;
        //输入流的缓冲
        StringBuffer sb = new StringBuffer();
        try{
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );
            String str = null;
            //一行一行进行读入
            while((str = in.readLine()) != null) {
                sb.append( str );
            }
        } catch (Exception ex) {

        } finally{
            try{
                if(in!=null) {
                    in.close(); //关闭流
                }
            }catch(IOException ex) {

            }
        }
        String result =sb.toString();
        System.out.println(result);
        String[] splitFirst = result.split("\"");
        System.out.println(splitFirst[1]);
        String[] splitSecond = splitFirst[1].split(",");
        System.out.println(splitSecond[3]);
        double current_price = Double.parseDouble(splitSecond[3]);
        double change = current_price - stockDetailInfo.getClose();
        double pct_chg = change / stockDetailInfo.getClose() *100;



        stockDetailInfo.setName(name);
        stockDetailInfo.setSymbol(symbol);
        stockDetailInfo.setPe_ttm(pe_ttm);
        stockDetailInfo.setPe(pe);
        stockDetailInfo.setCirc_mv(circ_mv);
        stockDetailInfo.setTotal_mv(total_mv);
        stockDetailInfo.setTurnover_rate(turnover_rate);
        stockDetailInfo.setDv_ratio(dv_ratio);
        stockDetailInfo.setDv_ttm(dv_ttm);
        stockDetailInfo.setTotal_share(total_share);
        stockDetailInfo.setFloat_share(float_share);
        stockDetailInfo.setCurrent_price(current_price);
        stockDetailInfo.setChange(change);
        stockDetailInfo.setPct_chg(pct_chg);

        String introduction = stockDetailInfoMapper.getIntroduction(ts_code);
        String main_business = stockDetailInfoMapper.getMainBusiness(ts_code);

        message.setStocks(stockDetailInfo);
        message.setIntroduction(introduction);
        message.setMain_business(main_business);
        message.setState(true);
        message.setMessage("股票详细信息查询成功");
        return message;
    }

}
