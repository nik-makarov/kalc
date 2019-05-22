package com.nik_makrv.multiplier.client;

import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

public interface AdderClient {

    @RequestLine("GET /adder/add")
    int add(@QueryMap Map<String, Integer> query);

}
