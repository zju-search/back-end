package com.search.service;

import com.search.model.Price;
import com.search.model.Realtime;

import java.util.List;

public interface RealtimeService {

    List<Realtime> getAll();

    List<Price> getIndex(String tscode);
}
