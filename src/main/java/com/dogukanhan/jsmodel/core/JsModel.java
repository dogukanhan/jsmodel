package com.dogukanhan.jsmodel.core;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class JsModel {

    private Map<String, Object> map;

    public JsModel(HttpServletRequest request) {
        request.setAttribute("jsModel", this);
        this.map = new HashMap<>();
    }

    public JsModel addAttribute(String attributeName, Object attributeValue) {
        map.put(attributeName, attributeValue);
        return this;
    }

    public Map<String, Object> asMap() {
        return this.map;
    }
}
