package com.luxsuen.requestjson.util;

import com.alibaba.fastjson.JSONObject;

public class MapWrapper {
    private JSONObject jsonObject;

    public MapWrapper(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getMapWrapper() {
        return jsonObject;
    }
}
