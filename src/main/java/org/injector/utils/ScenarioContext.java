package org.injector.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ScenarioContext {
    private final Map<String, Object> contextMap = new HashMap<>();

    public void setAttribute(String key, Object value) {
        contextMap.put(key, value);
    }

    public Object getAttribute(String key) {
        return contextMap.get(key);
    }

    public void clearContext() {
        contextMap.clear();
    }

    private static String appUrl;
    private static String tableName;

    public static String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public static String getAppUrl() {
        return appUrl;
    }
}
