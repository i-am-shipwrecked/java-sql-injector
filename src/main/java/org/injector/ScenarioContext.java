package org.injector;

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
}