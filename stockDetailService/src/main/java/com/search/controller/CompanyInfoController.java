package com.search.controller;


import com.search.dto.CompanyInfo;
import com.search.dto.CompanyInfoListMessage;
import com.search.dto.Message;
import com.search.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyInfoController {
    @Autowired
    CompanyMapper companyMapper;


    @GetMapping(value = "/api/CompanyInfo")
    public Message getCompanyInfo(@RequestParam("symbol") String symbol)
    {
        CompanyInfoListMessage message = new CompanyInfoListMessage();
        List<CompanyInfo> companyInfoList = new ArrayList<>();
        String[] titles = {"公司名称","法人代表","总经理","董秘","注册资本","注册日期",
                "所在省份","所在城市","公司介绍","公司主页","电子邮件","办公室","员工人数",
                "主要业务及产品","经营范围"};
        String[] parameters = {"name","chairman","manager","secretary","reg_capital",
                "setup_date","province","city","introduction","website","email",
                "office","employees","main_business","business_scope"};
        String[] infos = new String[15];

        /*特殊处理公司名称*/
        infos[0] = companyMapper.getNameBySymbol(symbol)+"股份有限公司";
        companyInfoList.add(new CompanyInfo(titles[0],infos[0]));

        String ts_code = companyMapper.getCodeBySymbol(symbol);

        for(int i=1;i<15;i++){
            infos[i] = companyMapper.getCompanyInfoByCode(ts_code,parameters[i]);
            companyInfoList.add(new CompanyInfo(titles[i],infos[i]));
        }

        message.setCompanyInfoList(companyInfoList);
        message.setState(true);
        message.setMessage("公司信息查询成功");
        return message;
    }

}
