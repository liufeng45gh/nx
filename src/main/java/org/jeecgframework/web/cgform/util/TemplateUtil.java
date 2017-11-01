//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;

public class TemplateUtil {
    public static final String BASE_DIR = "online/template/";
    public static final String TEMPLET_CODE_DEFAULT = "ledefault";
    public static final String TEMPLET_ONE_DEFAULT = "jform.ftl";
    public static final String TEMPLET_ONE_MANY_DEFAULT = "jformunion.ftl";
    public static final String TEMPLET_VIEW_DIR_DEFAULT = "html";
    public static final String TEMPLET_LIST = "autolist.ftl";

    public TemplateUtil() {
    }

    public static String getTempletPath(CgformTemplateEntity entity, Integer formType, TemplateUtil.TemplateType type) {
        if(entity == null || StringUtils.isBlank(entity.getTemplateCode())) {
            entity = new CgformTemplateEntity();
            entity.setTemplateCode("ledefault");
            entity.setTemplateListName("autolist.ftl");
            if(2 == formType.intValue()) {
                entity.setTemplateAddName("jformunion.ftl");
                entity.setTemplateUpdateName("jformunion.ftl");
                entity.setTemplateDetailName("jformunion.ftl");
            } else {
                entity.setTemplateAddName("jform.ftl");
                entity.setTemplateUpdateName("jform.ftl");
                entity.setTemplateDetailName("jform.ftl");
            }
        }

        String templateCode = entity.getTemplateCode();
        String templateName = null;
        switch(type.ordinal()) {
            case 1:
                templateName = entity.getTemplateAddName();
                break;
            case 2:
                templateName = entity.getTemplateUpdateName();
                break;
            case 3:
                templateName = entity.getTemplateListName();
                break;
            case 4:
                templateName = entity.getTemplateDetailName();
                break;
            default:
                templateName = entity.getTemplateListName();
        }

        StringBuffer buffer = new StringBuffer("online/template/" + templateCode + "/");
        buffer.append("html/");
        buffer.append(templateName);
        return buffer.toString();
    }

    public Map<String, Object> processor(String content) {
        HashMap map = new HashMap();

        try {
            JSONObject jsonObj = JSONObject.fromObject(content);
            String template = (String)jsonObj.get("template");
            String parseHtml = (String)jsonObj.get("parse");
            new JSONArray();
            JSONArray jsonArray = JSONArray.fromObject(jsonObj.get("data"));
            map.put("template", template);
            String rexEg = "(<input[^>]*>)";
            Pattern p = Pattern.compile(rexEg);
            Matcher m = p.matcher(parseHtml);
            ArrayList result = new ArrayList();

            while(m.find()) {
                result.add(m.group());
            }

            Map<String, Object> tableData = null;
            int index = 0;

            for(int i = 0; i < result.size(); ++i) {
                String ctrlExp = "(listctrl)";
                Pattern ctrlP = Pattern.compile(ctrlExp);
                Matcher ctrlM = ctrlP.matcher((CharSequence)result.get(i));
                if(ctrlM.find()) {
                    tableData = new HashMap();

                    for(int j = index; j < jsonArray.size(); ++j) {
                        JSONObject item = jsonArray.getJSONObject(j);
                        if("listctrl".equals(item.getString("leipiplugins"))) {
                            String tempHtml = GetListctrl(jsonArray.getJSONObject(j), tableData, "");
                            parseHtml = parseHtml.replace((CharSequence)result.get(i), tempHtml);
                            index = j + 1;
                        }
                    }
                }
            }

            map.put("parseHtml", parseHtml);
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        return map;
    }

    private static String GetListctrl(JSONObject item, Map<String, Object> formData, String action) {
        String valuetest = "{\"data_110\":[\"1\",\"2\"],\"data_111\":[\"21\",\"22\",\"22\"]}";
        String name = item.getString("name").toString();
        if(formData.get(name) == null) {
            Object var10000 = null;
        } else {
            formData.get(name).toString();
        }

        String temp_html = "";
        String orgSum = item.getString("orgsum").toString();
        String orgUnit = item.getString("orgunit").toString();
        String orgTitle = item.getString("orgtitle").toString();
        String title = item.getString("title").toString();
        String style = item.getString("style").toString();
        String orgcolvalue = item.getString("orgcolvalue").toString();
        String orgcoltype = item.getString("orgcoltype").toString();
        List<String> listTitle = Arrays.asList(orgTitle.split("`"));
        List<String> listSum = Arrays.asList(orgSum.split("`"));
        List<String> listUnit = Arrays.asList(orgUnit.split("`"));
        List<String> listValue = Arrays.asList(orgcolvalue.split("`"));
        List<String> listType = Arrays.asList(orgcoltype.split("`"));
        int tdCount = listTitle.size();
        String tableNm = name + "_table";
        String temp = "<table id=\"" + tableNm + "\" bindTable=\"true\" cellspacing=\"0\" class=\"table table-bordered table-condensed\" style=\"" + style + "\"><thead>{0}</thead><tbody>{1}</tbody>{2}</table>";
        String btnAdd = "<span class=\"pull-right\"><button id='listAdd' class=\"btn btn-small btn-success listAdd\" type=\"button\" tbname=\"" + name + "\">添加一行</button></span>";
        String theader = "<tr><th colspan=\"{0}\">{1}{2}</th></tr>{3}";
        boolean isExistSum = false;
        String trTitle = "";

        for(int i = 0; i < tdCount; ++i) {
            trTitle = trTitle + MessageFormat.format("<th>{0}</th>", new Object[]{listTitle.get(i)});
            if(i + 1 == tdCount) {
                trTitle = trTitle + MessageFormat.format("<th>{0}</th>", new Object[]{"操作"});
            }
        }

        trTitle = "<tr>" + trTitle + "</tr>";
        JSONObject dataValue = JSONObject.fromObject(valuetest);
        int rowCount = dataValue != null?dataValue.size():1;
        StringBuilder sbTr = new StringBuilder();
        String tdSum = "";
        TreeMap<Integer, Float> SumValueDic = new TreeMap();

        for(int row = 0; row < rowCount; ++row) {
            JSONArray rowValue = dataValue != null && dataValue.has(name + row)?dataValue.getJSONArray(name + row):null;
            String tr = "";

            for(int i = 0; i < tdCount; ++i) {
                String tdname = name + "[" + i + "]";
                String sum = "1".equals(listSum.get(i))?"sum=\"" + tdname + "\"":"";
                String tdValue = "";
                if(i < listValue.size()) {
                    tdValue = StringUtils.isBlank((String)listValue.get(i))?"":(String)listValue.get(i);
                }

                tdValue = rowValue != null && rowValue.size() > i?rowValue.getString(i).toString():tdValue;
                String type = (String)listType.get(i);
                if(sum != "") {
                    float tempTdValue = 0.0F;
                    if(SumValueDic.containsKey(Integer.valueOf(i))) {
                        tempTdValue = ((Float)SumValueDic.get(Integer.valueOf(i))).floatValue();
                    }

                    try {
                        float resultTdTemp = 0.0F;
                        resultTdTemp = Float.parseFloat(tdValue);
                        tempTdValue += resultTdTemp;
                    } catch (Exception var41) {
                        tdValue = "0";
                    }

                    if(SumValueDic.containsKey(Integer.valueOf(i))) {
                        SumValueDic.subMap(Integer.valueOf(i), Integer.valueOf(i));
                    } else {
                        SumValueDic.put(Integer.valueOf(i), Float.valueOf(tempTdValue));
                    }
                }

                if("text".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                } else if("int".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                } else if("textarea".equals(type)) {
                    tr = tr + MessageFormat.format("<td><textarea class=\"input-medium\" name=\"{0}\" >{1}</textarea></td>", new Object[]{tdname, tdValue, sum});
                } else if("calc".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                }

                if(i + 1 == tdCount) {
                    if(row == 0) {
                        tr = tr + "<td></td>";
                    } else {
                        tr = tr + "<td><a href=\"javascript:void(0);\" class=\"delrow \">删除</a></td>";
                    }
                }

                if(row == 0) {
                    if(sum != "") {
                        isExistSum = true;
                        tdSum = tdSum + MessageFormat.format("<td>合计：<input class=\"input-small\" type=\"text\" value=\"value{0}\" name=\"{1}[total]\" {2}\">{3}</td>", new Object[]{Integer.valueOf(i), tdname, sum, ""});
                    } else {
                        tdSum = tdSum + "<td></td>";
                    }
                }
            }

            sbTr.append(MessageFormat.format("<tr class=\"template\">{0}</tr>", new Object[]{tr}));
        }

        if(!StringUtils.isBlank(tdSum)) {
            for(Iterator var44 = SumValueDic.keySet().iterator(); var44.hasNext(); tdSum = MessageFormat.format("<tbody class=\"sum\"><tr>{0}</tr></tbody>", new Object[]{tdSum})) {
                Integer i = (Integer)var44.next();
                tdSum = tdSum.replace("value" + i, ((Float)SumValueDic.get(i)).toString());
            }
        }

        theader = MessageFormat.format(theader, new Object[]{Integer.valueOf(tdCount + 1), title, btnAdd, trTitle});
        temp_html = MessageFormat.format(temp, new Object[]{theader, sbTr.toString(), tdSum});
        temp_html = temp_html + "<script type=\"text/javascript\">";
        temp_html = temp_html + "$(function(){";
        temp_html = temp_html + "$(\"#listAdd\").click(function(){";
        temp_html = temp_html + "var tbHtml ='<tr>'+ $(\"#" + tableNm + " tr\").eq(2).html().replace('<td></td>',\"<td><a href='javascript:void(0);' class='delrow'>删除</a></td>\")+'</tr>';";
        if(isExistSum) {
            temp_html = temp_html + "$(\"#" + tableNm + " tr\").eq(-2).after(tbHtml);";
        } else {
            temp_html = temp_html + "$(\"#" + tableNm + " tr:last\").after(tbHtml);";
        }

        temp_html = temp_html + "$(\".delrow\").die().live(\"click\",function(){$(this).parent().parent().remove();});";
        temp_html = temp_html + "});";
        temp_html = temp_html + "$(\".delrow\").click(function(){$(this).parent().parent().remove();});";
        temp_html = temp_html + "});</script>";
        return temp_html;
    }

    public static enum TemplateType {
        ADD("add"),
        UPDATE("update"),
        LIST("list"),
        DETAIL("detail");

        private String name;

        private TemplateType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static TemplateUtil.TemplateType getVal(String type) {
            return "add".equals(type)?ADD:("update".equals(type)?UPDATE:("detail".equals(type)?DETAIL:("list".equals(type)?LIST:null)));
        }
    }
}
