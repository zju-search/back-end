package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.dto.Message;
import com.search.dto.News;
import com.search.dto.NewsListMessage;
import com.search.service.ESearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class SearchController {
    final ESearchService esearchService;

    public SearchController(ESearchService es) {
        this.esearchService = es;
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public Message search(@RequestParam("param") String param, @RequestParam("query") String value) throws IOException {
        NewsListMessage message = new NewsListMessage();
        List<News> newsList = new ArrayList<>();
        ArrayList<String> search = esearchService.Search("news", param, value);

        for(int i=0;i<search.size();i++){
            String newsStr = search.get(i);
            System.out.println("JsonStr:"+newsStr);
            News news = new News();
            news  = JSONObject.parseObject(newsStr,News.class);
            String currentTitle = news.getTitle();
            boolean redundant = false;
            for(int j = 0;j<newsList.size();j++){
                if(newsList.get(j).getTitle().equals(currentTitle)){
                    redundant = true;
                    break;
                }
            }

            if(!redundant){
                newsList.add(news);
            }
        }

        message.setNewsList(newsList);
        message.setState(true);
        message.setMessage("新闻获取成功");

        return message;
    }
}
