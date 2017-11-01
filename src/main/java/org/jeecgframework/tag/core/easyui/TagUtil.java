//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.tag.core.easyui;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.web.system.pojo.base.TSRole;

public class TagUtil {
    public TagUtil() {
    }

    public static Field[] getFiled(Class<?> objClass) throws ClassNotFoundException {
        Field[] field = null;
        if(objClass != null) {
            Class<?> class1 = Class.forName(objClass.getName());
            field = class1.getDeclaredFields();
            return field;
        } else {
            return field;
        }
    }

    public static Object fieldNametoValues(String FiledName, Object o) {
        //Object value = "";
        String fieldName = "";
        String childFieldName = null;
        ReflectHelper reflectHelper = new ReflectHelper(o);
        if(FiledName.indexOf("_") == -1) {
            if(FiledName.indexOf(".") == -1) {
                fieldName = FiledName;
            } else {
                fieldName = FiledName.substring(0, FiledName.indexOf("."));
                childFieldName = FiledName.substring(FiledName.indexOf(".") + 1);
            }
        } else {
            fieldName = FiledName.substring(0, FiledName.indexOf("_"));
            childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);
        }

        Object value = reflectHelper.getMethodValue(fieldName) == null?"":reflectHelper.getMethodValue(fieldName);
        if(value != "" && value != null && (FiledName.indexOf("_") != -1 || FiledName.indexOf(".") != -1)) {
            if(value instanceof List) {
                Object tempValue = "";

                Object listValue;
                for(Iterator var8 = ((List)value).iterator(); var8.hasNext(); tempValue = tempValue.toString() + fieldNametoValues(childFieldName, listValue) + ",") {
                    listValue = var8.next();
                }

                value = tempValue;
            } else {
                value = fieldNametoValues(childFieldName, value);
            }
        }

        if(value != "" && value != null) {
            value = converunicode(value.toString());
        }

        return value;
    }

    static Object converunicode(String jsonValue) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < jsonValue.length(); ++i) {
            char c = jsonValue.charAt(i);
            switch(c) {
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\'':
                    sb.append("\\'");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    sb.append(c);
            }
        }

        return sb.toString();
    }

    protected static Object[] field2Values(String[] fields, Object o) throws Exception {
        Object[] values = new Object[fields.length];

        for(int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].toString();
            values[i] = fieldNametoValues(fieldName, o);
        }

        return values;
    }

    private static String listtojson(String[] fields, int total, List<?> list, String[] footers) throws Exception {
        StringBuffer jsonTemp = new StringBuffer();
        jsonTemp.append("{\"total\":" + total + ",\"rows\":[");

        int i;
        Object value;
        for(int j = 0; j < list.size(); ++j) {
            jsonTemp.append("{\"state\":\"closed\",");
            value = null;

            for(i = 0; i < fields.length; ++i) {
                String fieldName = fields[i].toString();
                if(list.get(j) instanceof Map) {
                    value = ((Map)list.get(j)).get(fieldName);
                } else {
                    value = fieldNametoValues(fieldName, list.get(j));
                }

                jsonTemp.append("\"" + fieldName + "\"" + ":\"" + String.valueOf(value).replace("\"", "\\\"") + "\"");
                if(i != fields.length - 1) {
                    jsonTemp.append(",");
                }
            }

            if(j != list.size() - 1) {
                jsonTemp.append("},");
            } else {
                jsonTemp.append("}");
            }
        }

        jsonTemp.append("]");
        if(footers != null && footers.length > 0) {
            jsonTemp.append(",");
            jsonTemp.append("\"footer\":[");
            jsonTemp.append("{");

            for(i = 0; i < footers.length; ++i) {
                String footerFiled = footers[i].split(":")[0];
                value = null;
                if(footers[i].split(":").length == 2) {
                    value = footers[i].split(":")[1];
                } else {
                    value = getTotalValue(footerFiled, list);
                }

                if(i == 0) {
                    jsonTemp.append("\"" + footerFiled + "\":\"" + value + "\"");
                } else {
                    jsonTemp.append(",\"" + footerFiled + "\":\"" + value + "\"");
                }
            }

            jsonTemp.append("}");
            jsonTemp.append("]");
        }

        jsonTemp.append("}");
        return jsonTemp.toString();
    }

    private static Object getTotalValue(String fieldName, List list) {
        Double sum = Double.valueOf(0.0D);

        try {
            for(int j = 0; j < list.size(); ++j) {
                Double v = Double.valueOf(0.0D);
                String vstr = String.valueOf(fieldNametoValues(fieldName, list.get(j)));
                if(!StringUtil.isEmpty(vstr)) {
                    v = Double.valueOf(vstr);
                }

                sum = Double.valueOf(sum.doubleValue() + v.doubleValue());
            }

            return sum;
        } catch (Exception var6) {
            return "";
        }
    }

    public static String getAutoList(Autocomplete autocomplete, List list) throws Exception {
        String field = autocomplete.getLabelField() + "," + autocomplete.getValueField();
        String[] fields = field.split(",");
        Object[] values = new Object[fields.length];
        StringBuffer jsonTemp = new StringBuffer();
        jsonTemp.append("{\"totalResultsCount\":\"1\",\"geonames\":[");
        if(list.size() > 0) {
            for(int j = 0; j < list.size(); ++j) {
                jsonTemp.append("{'nodate':'yes',");

                for(int i = 0; i < fields.length; ++i) {
                    String fieldName = fields[i].toString();
                    values[i] = fieldNametoValues(fieldName, list.get(j));
                    jsonTemp.append("\"").append(fieldName).append("\"").append(":\"").append(values[i]).append("\"");
                    if(i != fields.length - 1) {
                        jsonTemp.append(",");
                    }
                }

                if(j != list.size() - 1) {
                    jsonTemp.append("},");
                } else {
                    jsonTemp.append("}");
                }
            }
        } else {
            jsonTemp.append("{'nodate':'数据不存在'}");
        }

        jsonTemp.append("]}");
        return JSONObject.toJSONString(jsonTemp).toString();
    }

    private static String datatable(String field, int total, List list) throws Exception {
        String[] fields = field.split(",");
        Object[] values = new Object[fields.length];
        StringBuffer jsonTemp = new StringBuffer();
        jsonTemp.append("{\"iTotalDisplayRecords\":" + total + ",\"iTotalRecords\":" + total + ",\"aaData\":[");

        for(int j = 0; j < list.size(); ++j) {
            jsonTemp.append("{");

            for(int i = 0; i < fields.length; ++i) {
                String fieldName = fields[i].toString();
                values[i] = fieldNametoValues(fieldName, list.get(j));
                jsonTemp.append("\"" + fieldName + "\"" + ":\"" + values[i] + "\"");
                if(i != fields.length - 1) {
                    jsonTemp.append(",");
                }
            }

            if(j != list.size() - 1) {
                jsonTemp.append("},");
            } else {
                jsonTemp.append("}");
            }
        }

        jsonTemp.append("]}");
        return jsonTemp.toString();
    }

    private static JSONObject getJson(DataGrid dg) {
        JSONObject jObject = null;

        try {
            if(!StringUtil.isEmpty(dg.getFooter())) {
                jObject = JSONObject.parseObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(), dg.getFooter().split(",")));
            } else {
                jObject = JSONObject.parseObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getResults(), (String[])null));
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return jObject;
    }

    private static JSONObject getJson(DataTableReturn dataTable, String field) {
        JSONObject jObject = null;

        try {
            jObject = JSONObject.parseObject(datatable(field, dataTable.getiTotalDisplayRecords().intValue(), dataTable.getAaData()));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return jObject;
    }

    public static String getColumnType(String fileName, Field[] fields) {
        String type = "";
        if(fields.length > 0) {
            for(int i = 0; i < fields.length; ++i) {
                String name = fields[i].getName();
                String filedType = fields[i].getGenericType().toString();
                if(fileName.equals(name)) {
                    if(filedType.equals("class java.lang.Integer")) {
                        filedType = "int";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.Short")) {
                        filedType = "short";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.Double")) {
                        filedType = "double";
                        type = filedType;
                    } else if(filedType.equals("class java.util.Date")) {
                        filedType = "date";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.String")) {
                        filedType = "string";
                        type = filedType;
                    } else if(filedType.equals("class java.sql.Timestamp")) {
                        filedType = "Timestamp";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.Character")) {
                        filedType = "character";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.Boolean")) {
                        filedType = "boolean";
                        type = filedType;
                    } else if(filedType.equals("class java.lang.Long")) {
                        filedType = "long";
                        type = filedType;
                    }
                }
            }
        }

        return type;
    }

    protected static String getSortColumnIndex(String fileName, String[] fieldString) {
        String index = "";
        if(fieldString.length > 0) {
            for(int i = 0; i < fieldString.length; ++i) {
                if(fileName.equals(fieldString[i])) {
                    int j = i + 1;
                    index = oConvertUtils.getString(j);
                }
            }
        }

        return index;
    }

    public static void ListtoView(HttpServletResponse response, PageList pageList) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        Map<String, Object> map = new HashMap();
        map.put("total", Integer.valueOf(pageList.getCount()));
        map.put("rows", pageList.getResultList());
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(response.getWriter(), map);
        } catch (JsonGenerationException var17) {
            var17.printStackTrace();
        } catch (JsonMappingException var18) {
            var18.printStackTrace();
        } catch (IOException var19) {
            var19.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException var16) {
                ;
            }

        }

    }

    public static void datagrid(HttpServletResponse response, DataGrid dg) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        JSONObject object = getJson(dg);
        PrintWriter pw = null;

        try {
            pw = response.getWriter();
            pw.write(object.toString());
            pw.flush();
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                pw.close();
                object.clear();
            } catch (Exception var12) {
                var12.printStackTrace();
            }

        }

    }

    public static void datagrid(HttpServletResponse response, DataGrid dg, Map<String, Map<String, Object>> extMap) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        JSONObject object = getJson(dg);
        JSONArray r = object.getJSONArray("rows");
        Iterator var6 = r.iterator();

        while(var6.hasNext()) {
            Object object2 = var6.next();
            JSONObject o = (JSONObject)object2;
            o.putAll((Map)extMap.get(o.get("id")));
        }

        PrintWriter pw = null;

        try {
            pw = response.getWriter();
            pw.write(object.toString());
            pw.flush();
        } catch (IOException var15) {
            var15.printStackTrace();
        } finally {
            try {
                pw.close();
                object.clear();
            } catch (Exception var14) {
                ;
            }

        }

    }

    public static void datatable(HttpServletResponse response, DataTableReturn dataTableReturn, String field) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        JSONObject object = getJson(dataTableReturn, field);

        try {
            response.getWriter().write(object.toString());
            response.getWriter().flush();
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (Exception var12) {
                ;
            }

        }

    }

    public static String getComboBoxJson(List<TSRole> list, List<TSRole> roles) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        Iterator var4 = list.iterator();

        while(true) {
            while(var4.hasNext()) {
                TSRole node = (TSRole)var4.next();
                if(roles.size() > 0) {
                    buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"");
                    Iterator var6 = roles.iterator();

                    while(var6.hasNext()) {
                        TSRole node1 = (TSRole)var6.next();
                        if(node.getId() == node1.getId()) {
                            buffer.append(",\"selected\":true");
                        }
                    }

                    buffer.append("},");
                } else {
                    buffer.append("{\"id\":" + node.getId() + ",\"text\":\"" + node.getRoleName() + "\"},");
                }
            }

            buffer.append("]");
            String tmp = buffer.toString();
            tmp = tmp.replaceAll(",]", "]");
            return tmp;
        }
    }

    public static List<ComboBox> getComboBox(List all, List in, ComboBox comboBox) {
        List<ComboBox> comboxBoxs = new ArrayList();
        String[] fields = new String[]{comboBox.getId(), comboBox.getText()};
        Object[] values = new Object[fields.length];

        ComboBox box;
        for(Iterator var7 = all.iterator(); var7.hasNext(); comboxBoxs.add(box)) {
            Object node = var7.next();
            box = new ComboBox();
            ReflectHelper reflectHelper = new ReflectHelper(node);

            for(int i = 0; i < fields.length; ++i) {
                String fieldName = fields[i].toString();
                values[i] = reflectHelper.getMethodValue(fieldName);
            }

            box.setId(values[0].toString());
            box.setText(values[1].toString());
            if(in != null) {
                Iterator var16 = in.iterator();

                while(var16.hasNext()) {
                    Object node1 = var16.next();
                    ReflectHelper reflectHelper2 = new ReflectHelper(node);
                    if(node1 != null) {
                        String fieldName = fields[0].toString();
                        String test = reflectHelper2.getMethodValue(fieldName).toString();
                        if(values[0].toString().equals(test)) {
                            box.setSelected(true);
                        }
                    }
                }
            }
        }

        return comboxBoxs;
    }

    public static String getFunction(String functionname) {
        int index = functionname.indexOf("(");
        return index == -1?functionname:functionname.substring(0, functionname.indexOf("("));
    }

    public static String getFunParams(String functionname) {
        int index = functionname.indexOf("(");
        String param = "";
        if(index != -1) {
            String testparam = functionname.substring(functionname.indexOf("(") + 1, functionname.length() - 1);
            if(StringUtil.isNotEmpty(testparam)) {
                String[] params = testparam.split(",");
                String[] var8 = params;
                int var7 = params.length;

                for(int var6 = 0; var6 < var7; ++var6) {
                    String string = var8[var6];
                    param = param + (string.indexOf("{") != -1?"'\"+" + string.substring(1, string.length() - 1) + "+\"',":"'\"+rec." + string + "+\"',");
                }
            }
        }

        param = param + "'\"+index+\"'";
        return param;
    }

    public static String getJson(List fields, List datas) {
        if(datas != null && datas.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("{\"total\":\"" + datas.size() + "\",\"rows\":[");

            for(int i = 0; i < datas.size(); ++i) {
                Object[] values = (Object[])datas.get(i);
                sb.append("{");

                for(int j = 0; j < values.length; ++j) {
                    sb.append("\"" + fields.get(j) + "\":\"" + (values[j] == null?"":values[j]) + "\"" + (j == values.length - 1?"":","));
                }

                sb.append("}" + (i == datas.size() - 1?"":","));
            }

            sb.append("]}");
            return sb.toString();
        } else {
            return "{\"total\":\"0\",\"rows\":[]}";
        }
    }

    public static String getJsonByMap(List fields, List<Map<String, Object>> datas) {
        if(datas != null && datas.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append("{\"total\":\"" + datas.size() + "\",\"rows\":[");

            for(int i = 0; i < datas.size(); ++i) {
                Map<String, Object> values = (Map)datas.get(i);
                sb.append("{");
                int j = 0;

                for(Iterator var7 = values.values().iterator(); var7.hasNext(); ++j) {
                    Object value = var7.next();
                    sb.append("\"" + fields.get(j) + "\":\"" + (value == null?"":value) + "\"" + (j == values.size() - 1?"":","));
                }

                sb.append("}" + (i == datas.size() - 1?"":","));
            }

            sb.append("]}");
            return sb.toString();
        } else {
            return "{\"total\":\"0\",\"rows\":[]}";
        }
    }
}
