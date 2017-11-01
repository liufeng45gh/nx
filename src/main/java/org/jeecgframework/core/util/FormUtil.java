//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import ssb.warmline.business.commons.utils.DateUtils;

public class FormUtil {
    private static String temp_view = "<div style=\"{0}\"/>{1}</div>";

    public FormUtil() {
    }

    public static void main(String[] arg) {
        String test = "都 `发````";
        System.out.print(test.split("`").length);
    }

    public static String GetHtml(String parseHtml, String contentData, String action) {
        Map<String, Object> tableData = new HashMap();
        String html = parseHtml;
        new JSONArray();
        JSONArray jsonArray = JSONArray.fromObject(contentData);

        for(int f = 0; f < jsonArray.size(); ++f) {
            if(jsonArray.getJSONObject(f) != null && !"".equals(jsonArray.getJSONObject(f))) {
                JSONObject json = jsonArray.getJSONObject(f);
                String name = "";
                if(json != null) {
                    String leipiplugins = json.getString("leipiplugins").toString();
                    if("checkboxs".equals(leipiplugins)) {
                        name = json.getString("parse_name").toString();
                    } else {
                        name = json.getString("name").toString();
                    }

                    String temp_html = "";
                    if("text".equals(leipiplugins)) {
                        temp_html = GetTextBox(json, tableData, action);
                    } else if("textarea".equals(leipiplugins)) {
                        temp_html = GetTextArea(json, tableData, action);
                    } else if("radios".equals(leipiplugins)) {
                        temp_html = GetRadios(json, tableData, action);
                    } else if("select".equals(leipiplugins)) {
                        temp_html = GetSelect(json, tableData, action);
                    } else if("checkboxs".equals(leipiplugins)) {
                        temp_html = GetCheckboxs(json, tableData, action);
                    } else if("macros".equals(leipiplugins)) {
                        temp_html = GetMacros(json, tableData, action);
                    } else if("qrcode".equals(leipiplugins)) {
                        temp_html = GetQrcode(json, tableData, action);
                    } else if("listctrl".equals(leipiplugins)) {
                        temp_html = GetListctrl(json, tableData, action);
                    } else if("progressbar".equals(leipiplugins)) {
                        temp_html = json.getString("content").toString();
                    } else if("popup".equals(leipiplugins)) {
                        temp_html = GetPopUp(json, tableData, action);
                    } else {
                        temp_html = json.getString("content").toString();
                    }

                    html = html.replace("{" + name + "}", temp_html);
                }
            }
        }

        return html;
    }

    private static String GetTextBox(JSONObject item, Map<String, Object> formData, String action) {
        String temp = "<input type=\"text\" value=\"{0}\"  name=\"{1}\"  style=\"{2}\"/>";
        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        if(value == null) {
            value = item.getString("value") == null?"":item.getString("value").toString();
        }

        String style = item.getString("style") == null?"":item.getString("style").toString();
        String temp_html = MessageFormat.format(temp, new Object[]{value, name, style});
        return "view".equals(action)?MessageFormat.format(temp_view, new Object[]{style, value}):temp_html;
    }

    private static String GetPopUp(JSONObject item, Map<String, Object> formData, String action) {
        String dictionary = item.getString("dictionary").toString();
        String[] dic = new String[]{"", "", ""};
        if(dictionary.split(",").length > 1) {
            dic = dictionary.split(",");
        }

        String temp = "<input type=\"text\" value=\"{0}\"   class=\"searchbox-inputtext\" value=\"\"  name=\"{1}\"  style=\"{2}\" onClick=\"inputClick(this,''{3}'',''{4}'');\" />";
        String name = item.getString("name").toString();
        String value = "";
        String style = item.getString("style") == null?"":item.getString("style").toString();
        String temp_html = MessageFormat.format(temp, new Object[]{value, name, style, dic[1], dic[0]});
        return "view".equals(action)?MessageFormat.format(temp_view, new Object[]{style, value, dic[1], dic[0]}):temp_html;
    }

    private static String GetTextArea(JSONObject item, Map<String, Object> formData, String action) {
        String script = "";
        if(item.getString("orgrich") != null && "1".equals(item.getString("orgrich").toString())) {
            script = "orgrich=\"true\" ";
        }

        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        if(value == null) {
            value = item.getString("value") == null?"":item.getString("value").toString();
        }

        String style = item.getString("style") == null?"":item.getString("style").toString();
        String temp = "<textarea  name=\"{0}\" id=\"{1}\"  style=\"{2}\" {3}>{4}</textarea>";
        String temp_html = MessageFormat.format(temp, new Object[]{name, name, style, script, value});
        return "view".equals(action)?MessageFormat.format(temp_view, new Object[]{style, value}):temp_html;
    }

    private static String GetRadios(JSONObject item, Map<String, Object> formData, String action) {
        JSONArray radiosOptions = item.getJSONArray("options");
        String temp = "<input type=\"radio\" name=\"{0}\" value=\"{1}\"  {2}>{3}&nbsp;";
        String temp_html = "";
        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        String cvalue_ = "";

        for(int f = 0; f < radiosOptions.size(); ++f) {
            JSONObject json = radiosOptions.getJSONObject(f);
            String cvalue = json.getString("value").toString();
            String Ischecked = "";
            if(value == null) {
                String check = json.has("checked") && json.getString("checked") != null?json.getString("checked").toString():"";
                if("checked".equals(check) || "true".equals(check)) {
                    Ischecked = " checked=\"checked\" ";
                    cvalue_ = cvalue;
                }
            }

            temp_html = temp_html + MessageFormat.format(temp, new Object[]{name, cvalue, Ischecked, cvalue});
        }

        if("view".equals(action)) {
            return MessageFormat.format(temp_view, new Object[]{"&nbsp;", cvalue_});
        } else {
            return temp_html;
        }
    }

    private static String GetCheckboxs(JSONObject item, Map<String, Object> formData, String action) {
        String temp_html = "";
        String temp = "<input type=\"checkbox\" name=\"{0}\" value=\"{1}\" {2}>{3}&nbsp;";
        String view_value = "";
        JSONArray checkOptions = item.getJSONArray("options");

        for(int f = 0; f < checkOptions.size(); ++f) {
            JSONObject json = checkOptions.getJSONObject(f);
            String name = json.getString("name").toString();
            String value = formData.get(name) == null?null:formData.get(name).toString();
            String cvalue = json.getString("value").toString();
            String Ischecked = "";
            if(value != null) {
                if(value != null && value.equals(cvalue)) {
                    Ischecked = " checked=\"checked\" ";
                    view_value = view_value + cvalue + "&nbsp";
                }
            } else {
                String check = json.has("checked") && json.getString("checked") != null?json.getString("checked").toString():"";
                if(check.equals("checked") || check.equals("true")) {
                    Ischecked = " checked=\"checked\" ";
                    view_value = view_value + cvalue + "&nbsp";
                }
            }

            temp_html = temp_html + MessageFormat.format(temp, new Object[]{name, cvalue, Ischecked, cvalue});
        }

        if("view".equals(action)) {
            return MessageFormat.format(temp_view, new Object[]{"&nbsp;", view_value});
        } else {
            return temp_html;
        }
    }

    private static String GetSelect(JSONObject item, Map<String, Object> formData, String action) {
        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        String temp_html = item.getString("content").toString();
        if(value != null) {
            temp_html = temp_html.replace("selected=\"selected\"", "");
            value = "value=\"" + value + "\"";
            String r = value + " selected=\"selected\"";
            temp_html = temp_html.replace(value, r);
        }

        return "view".equals(action)?MessageFormat.format(temp_view, new Object[]{"&nbsp;", value != null?value:item.getString("value").toString().split(",")[0]}):temp_html;
    }

    private static String GetMacros(JSONObject item, Map<String, Object> formData, String action) {
        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        String temp_html = item.getString("content").toString();
        String microtype = "text";
        if(value == null) {
            String type = item.getString("orgtype").toString();
            String date_format = "";
            Date date = new Date();
            if(type.equals("sys_date")) {
                date_format = "yyyy-MM-dd";
                value = DateUtils.formatDate(date, date_format);
                microtype = "date";
            } else if(type.equals("sys_date_cn")) {
                date_format = "yyyy年MM月dd日";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_date_cn_short3")) {
                date_format = "yyyy年";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_date_cn_short4")) {
                date_format = "yyyy";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_date_cn_short1")) {
                date_format = "yyyy年MM月";
                value = DateUtils.formatDate(date, date_format);
                microtype = "month";
            } else if(type.equals("sys_date_cn_short2")) {
                date_format = "MM月dd日";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_time")) {
                date_format = "HH:mm:ss";
                microtype = "time";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_datetime")) {
                date_format = "yyyy-MM-dd'T'HH:mm";
                microtype = "datetime-local";
                value = DateUtils.formatDate(date, date_format);
            } else if(type.equals("sys_week")) {
                value = DateUtils.formatDate(date, "EEEE");
            } else if(type.equals("sys_userid")) {
                value = "${userId}";
            } else if(type.equals("sys_realname")) {
                value = "${userName}";
            }
        }

        if("view".equals(action)) {
            return value.replace("${userId}", ClientManager.getInstance().getClient().getUser().getId()).replace("${userName}", ClientManager.getInstance().getClient().getUser().getUserName());
        } else if(value != null) {
            temp_html = temp_html.replace("type=\"text\"", "type=\"" + microtype + "\" ");
            return temp_html.replace("{macros}", value);
        } else {
            return temp_html;
        }
    }

    private static String GetQrcode(JSONObject item, Map<String, Object> formData, String action) {
        String name = item.getString("name").toString();
        String value = formData.get(name) == null?null:formData.get(name).toString();
        String temp_html = "";
        String temp = "";
        String orgType = item.getString("orgtype").toString();
        String style = item.getString("style").toString();
        if("text".equals(orgType)) {
            orgType = "文本";
        } else if("url".equals(orgType)) {
            orgType = "超链接";
        } else if("tel".equals(orgType)) {
            orgType = "电话";
        }

        String qrcode_value = "";
        if(item.getString("value") != null) {
            qrcode_value = item.getString("value").toString();
        }

        if("edit".equals(action)) {
            temp = orgType + "二维码 <input type=\"text\" name=\"{0}\" value=\"{1}\"/>";
            temp_html = MessageFormat.format(temp, new Object[]{name, value});
        } else if("view".equals(action)) {
            style = "";
            if(item.getString("orgwidth") != null) {
                style = "width:" + item.getString("orgwidth").toString() + "px;";
            }

            if(item.getString("orgheight") != null) {
                style = style + "height:" + item.getString("orgheight").toString() + "px;";
            }

            temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
            temp_html = MessageFormat.format(temp_html, new Object[]{name, value, style});
        } else if("preview".equals(action)) {
            style = "";
            if(item.getString("orgwidth") != null) {
                style = "width:" + item.getString("orgwidth").toString() + "px;";
            }

            if(item.getString("orgheight") != null) {
                style = style + "height:" + item.getString("orgheight").toString() + "px;";
            }

            temp = "<img src=\"{0}\" title=\"{1}\" style=\"{2}\"/>";
            temp_html = MessageFormat.format(temp_html, new Object[]{name, value, style});
        }

        return temp_html;
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
        String temp = "<table id=\"" + name + "_table\" bindTable=\"true\" cellspacing=\"0\" class=\"table table-bordered table-condensed\" style=\"" + style + "\"><thead>{0}</thead><tbody>{1}</tbody>{2}</table>";
        String btnAdd = "<span class=\"pull-right\"><button class=\"btn btn-small btn-success listAdd\" type=\"button\" tbname=\"" + name + "\">添加一行</button></span>";
        String theader = "<tr><th colspan=\"{0}\">{1}{2}</th></tr>{3}";
        String trTitle = "";

        for(int i = 0; i < tdCount; ++i) {
            if(i == tdCount - 1) {
                listTitle.set(i, "操作");
            }

            if(!"view".equals(action) || i != tdCount - 1) {
                trTitle = trTitle + MessageFormat.format("<th>{0}</th>", new Object[]{listTitle.get(i)});
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
                String tdValue = null;
                if(i < listValue.size()) {
                    tdValue = (String)listValue.get(i);
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
                    } catch (Exception var39) {
                        tdValue = "0";
                    }

                    if(SumValueDic.containsKey(Integer.valueOf(i))) {
                        SumValueDic.subMap(Integer.valueOf(i), Integer.valueOf((int)tempTdValue));
                    } else {
                        SumValueDic.put(Integer.valueOf(i), Float.valueOf(tempTdValue));
                    }
                }

                if(i == tdCount - 1) {
                    if("view".equals(action)) {
                        continue;
                    }

                    tr = tr + "<td><a href=\"javascript:void(0);\" class=\"delrow \">删除</a></td>";
                } else if("view".equals(action)) {
                    tr = tr + MessageFormat.format("<td>{0}</td>", new Object[]{tdValue});
                } else if("text".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                } else if("int".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                } else if("textarea".equals(type)) {
                    tr = tr + MessageFormat.format("<td><textarea class=\"input-medium\" name=\"{0}\" >{1}</textarea></td>", new Object[]{tdname, tdValue, sum});
                } else if("calc".equals(type)) {
                    tr = tr + MessageFormat.format("<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}[]\" {2}></td>", new Object[]{tdValue, tdname, sum});
                }

                if(row == 0) {
                    if(sum != "") {
                        if("view".equals(action)) {
                            tdSum = tdSum + MessageFormat.format("<td>合计：value{0}{1}</td>", new Object[]{Integer.valueOf(i), listUnit.get(i)});
                        } else {
                            tdSum = tdSum + MessageFormat.format("<td>合计：<input class=\"input-small\" type=\"text\" value=\"value{0}\" name=\"{1}[total]\" {2}\">{3}</td>", new Object[]{Integer.valueOf(i), tdname, sum, listUnit.get(i)});
                        }
                    } else {
                        tdSum = tdSum + "<td></td>";
                    }
                }
            }

            sbTr.append(MessageFormat.format("<tr class=\"template\">{0}</tr>", new Object[]{tr}));
        }

        if(!StringUtils.isBlank(tdSum)) {
            for(Iterator var42 = SumValueDic.keySet().iterator(); var42.hasNext(); tdSum = MessageFormat.format("<tbody class=\"sum\"><tr>{0}</tr></tbody>", new Object[]{tdSum})) {
                Integer i = (Integer)var42.next();
                tdSum = tdSum.replace("value" + i, ((Float)SumValueDic.get(i)).toString());
            }
        }

        if("view".equals(action)) {
            theader = MessageFormat.format(theader, new Object[]{Integer.valueOf(tdCount), title, "", trTitle});
        } else {
            theader = MessageFormat.format(theader, new Object[]{Integer.valueOf(tdCount), title, btnAdd, trTitle});
        }

        temp_html = MessageFormat.format(temp, new Object[]{theader, sbTr.toString(), tdSum});
        return temp_html;
    }
}
