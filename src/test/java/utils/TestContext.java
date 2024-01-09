package utils;
import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private Map<String, Object> contextMap;

    public TestContext() {
        contextMap = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        contextMap.put(key, value);
    }

    public Object getContext(String key) {
        return contextMap.get(key);
    }

    public void removeContext(String key) {
        contextMap.remove(key);
    }
}
