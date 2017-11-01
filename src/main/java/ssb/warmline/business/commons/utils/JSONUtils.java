//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JSONUtils() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String obj2json(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return (Map)objectMapper.readValue(jsonStr, Map.class);
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = (Map)objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap();
        Iterator var5 = map.entrySet().iterator();

        while(var5.hasNext()) {
            Entry<String, Map<String, Object>> entry = (Entry)var5.next();
            result.put((String)entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
        }

        return result;
    }

    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        List<Map<String, Object>> list = (List)objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList();
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Map<String, Object> map = (Map)var5.next();
            result.add(map2pojo(map, clazz));
        }

        return result;
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }
}
