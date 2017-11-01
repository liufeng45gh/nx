//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JSONHelper {
    private static final Logger logger = LoggerFactory.getLogger(JSONHelper.class);

    public JSONHelper() {
    }

    public static String array2json(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    public static Object json2Array(String json, Class valueClz) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        return JSONArray.toArray(jsonArray, valueClz);
    }

    public static String collection2json(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    public static String map2json(Object object) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        Map classMap = new HashMap();

        for(int i = 0; i < keyArray.length; ++i) {
            classMap.put(keyArray[i], valueClz);
        }

        return (Map)JSONObject.toBean(jsonObject, Map.class, classMap);
    }

    public static String bean2json(Object object) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    public static Object json2Object(String json, Class beanClz) {
        return JSONObject.toBean(JSONObject.fromObject(json), beanClz);
    }

    public static <T> T fromJsonToObject(String json, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException var4) {
            logger.error("JsonParseException: ", var4);
        } catch (JsonMappingException var5) {
            logger.error("JsonMappingException: ", var5);
        } catch (IOException var6) {
            logger.error("IOException: ", var6);
        }

        return null;
    }

    public static String string2json(String key, String value) {
        JSONObject object = new JSONObject();
        object.put(key, value);
        return object.toString();
    }

    public static String json2String(String json, String key) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        return jsonObject.get(key).toString();
    }

    public static <T> String toJSONString(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    public static String toJSONString(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    public static String toJSONString(JSONArray jsonArray) {
        return jsonArray.toString();
    }

    public static String toJSONString(JSONObject jsonObject) {
        return jsonObject.toString();
    }

    public static List toArrayList(Object object) {
        List arrayList = new ArrayList();
        JSONArray jsonArray = JSONArray.fromObject(object);
        Iterator it = jsonArray.iterator();

        while(it.hasNext()) {
            JSONObject jsonObject = (JSONObject)it.next();
            Iterator keys = jsonObject.keys();

            while(keys.hasNext()) {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                arrayList.add(value);
            }
        }

        return arrayList;
    }

    public static JSONArray toJSONArray(Object object) {
        return JSONArray.fromObject(object);
    }

    public static JSONObject toJSONObject(Object object) {
        return JSONObject.fromObject(object);
    }

    public static HashMap toHashMap(Object object) {
        HashMap<String, Object> data = new HashMap();
        JSONObject jsonObject = toJSONObject(object);
        Iterator it = jsonObject.keys();

        while(it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> data = new HashMap();
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        Iterator it = jsonObject.keys();

        while(it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    public static Map<String, List<Map<String, Object>>> json2MapList(String jsonStr) {
        Map<String, List<Map<String, Object>>> data = new HashMap();
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        Iterator it = jsonObject.keys();

        while(it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            List<Map<String, Object>> list = toList(value);
            data.put(key, list);
        }

        return data;
    }

    public static List<Map<String, Object>> toList(Object object) {
        List<Map<String, Object>> list = new ArrayList();
        JSONArray jsonArray = JSONArray.fromObject(object);
        Iterator var4 = jsonArray.iterator();

        while(var4.hasNext()) {
            Object obj = var4.next();
            JSONObject jsonObject = (JSONObject)obj;
            Map<String, Object> map = new HashMap();
            Iterator it = jsonObject.keys();

            while(it.hasNext()) {
                String key = (String)it.next();
                Object value = jsonObject.get(key);
                map.put(key, value);
            }

            list.add(map);
        }

        return list;
    }

    public static List<Map<String, Object>> toList(JSONArray jsonArray) {
        List<Map<String, Object>> list = new ArrayList();
        Iterator var3 = jsonArray.iterator();

        while(var3.hasNext()) {
            Object obj = var3.next();
            JSONObject jsonObject = (JSONObject)obj;
            Map<String, Object> map = new HashMap();
            Iterator it = jsonObject.keys();

            while(it.hasNext()) {
                String key = (String)it.next();
                Object value = jsonObject.get(key);
                map.put(key, value);
            }

            list.add(map);
        }

        return list;
    }

    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
        return JSONArray.toList(jsonArray, objectClass);
    }

    public static <T> List<T> toList(Object object, Class<T> objectClass) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return JSONArray.toList(jsonArray, objectClass);
    }

    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass) {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    public static <T> T toBean(Object object, Class<T> beanClass) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    public static <T, D> T toBean(String jsonString, Class<T> mainClass, String detailName, Class<D> detailClass) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray = (JSONArray)jsonObject.get(detailName);
        T mainEntity = toBean(jsonObject, mainClass);
        List detailList = toList(jsonArray, detailClass);

        try {
            BeanUtils.setProperty(mainEntity, detailName, detailList);
            return mainEntity;
        } catch (Exception var9) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }
    }

    public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray)jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray)jsonObject.get(detailName2);
        T mainEntity = toBean(jsonObject, mainClass);
        List<D1> detailList1 = toList(jsonArray1, detailClass1);
        List detailList2 = toList(jsonArray2, detailClass2);

        try {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
            return mainEntity;
        } catch (Exception var13) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }
    }

    public static <T, D1, D2, D3> T toBean(String jsonString, Class<T> mainClass, String detailName1, Class<D1> detailClass1, String detailName2, Class<D2> detailClass2, String detailName3, Class<D3> detailClass3) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray)jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray)jsonObject.get(detailName2);
        JSONArray jsonArray3 = (JSONArray)jsonObject.get(detailName3);
        T mainEntity = toBean(jsonObject, mainClass);
        List<D1> detailList1 = toList(jsonArray1, detailClass1);
        List<D2> detailList2 = toList(jsonArray2, detailClass2);
        List detailList3 = toList(jsonArray3, detailClass3);

        try {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
            BeanUtils.setProperty(mainEntity, detailName3, detailList3);
            return mainEntity;
        } catch (Exception var17) {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }
    }

    public static <T> T toBean(String jsonString, Class<T> mainClass, HashMap<String, Class> detailClass) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T mainEntity = toBean(jsonObject, mainClass);
        Iterator var6 = detailClass.keySet().iterator();

        while(var6.hasNext()) {
            Object key = var6.next();

            try {
                Class value = (Class)detailClass.get(key);
                BeanUtils.setProperty(mainEntity, key.toString(), value);
            } catch (Exception var8) {
                throw new RuntimeException("主从关系JSON反序列化实体失败！");
            }
        }

        return mainEntity;
    }

    public static String listtojson(String[] fields, int total, List list) throws Exception {
        Object[] values = new Object[fields.length];
        String jsonTemp = "{\"total\":" + total + ",\"rows\":[";

        for(int j = 0; j < list.size(); ++j) {
            jsonTemp = jsonTemp + "{\"state\":\"closed\",";

            for(int i = 0; i < fields.length; ++i) {
                String fieldName = fields[i].toString();
                values[i] = TagUtil.fieldNametoValues(fieldName, list.get(j));
                jsonTemp = jsonTemp + "\"" + fieldName + "\"" + ":\"" + values[i] + "\"";
                if(i != fields.length - 1) {
                    jsonTemp = jsonTemp + ",";
                }
            }

            if(j != list.size() - 1) {
                jsonTemp = jsonTemp + "},";
            } else {
                jsonTemp = jsonTemp + "}";
            }
        }

        jsonTemp = jsonTemp + "]}";
        return jsonTemp;
    }
}
