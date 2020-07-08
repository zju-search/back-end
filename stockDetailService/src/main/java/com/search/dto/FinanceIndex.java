package com.search.dto;

import lombok.Data;

@Data
public class FinanceIndex {
    String title;
    double _2019;
    double _2018;
    double _2017;
    double _2016;
    double _2015;
    double _2014;
    double _2013;
    double _2012;
    double _2011;
    double _2010;

    public FinanceIndex(String title,double _2019,double _2018,double _2017,
                        double _2016, double _2015,double _2014,double _2013,
                        double _2012,double _2011,double _2010)
    {
        this.title = title;
        this._2019 = _2019;this._2018 = _2018;this._2017 = _2017;this._2016 = _2016;
        this._2015 = _2015;this._2014 = _2014;this._2013 = _2013;this._2012 = _2012;
        this._2011 = _2011;this._2010 = _2010;
    }
}
