//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.util;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity;
import org.jeecgframework.web.cgreport.service.core.CgreportConfigHeadServiceI;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ssb.warmline.business.commons.utils.DateUtils;

public class AutoFormTemplateParseUtil {
    public static String OP_ADD = "add";
    public static String OP_UPDATE = "update";
    public static String OP_ADD_OR_UPDATE = "addorupdate";
    public static String OP_VIEW = "view";
    private static MutiLangServiceI mutiLangService;
    private static CgreportConfigHeadServiceI cgreportConfigHeadService;

    public AutoFormTemplateParseUtil() {
    }

    public static String parseHtmlForView(String content, Map<String, List<Map<String, Object>>> paras) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        if(!jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String html = (String)jsonObj.get("parse");
            List<Map<String, Object>> list = getAllInputAttr(jsonObj);
            Map atrrMap;
            if(list != null && list.size() > 0) {
                for(Iterator var6 = list.iterator(); var6.hasNext(); html = createHtml(html, atrrMap, paras)) {
                    atrrMap = (Map)var6.next();
                }
            }

            return html;
        } else {
            return "";
        }
    }

    public static String parseHtmlForAdd(String content, Map<String, List<Map<String, Object>>> paras) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        if(!jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String html = (String)jsonObj.get("parse");
            List<Map<String, Object>> list = getAllInputAttr(jsonObj);
            Map atrrMap;
            if(list != null && list.size() > 0) {
                for(Iterator var6 = list.iterator(); var6.hasNext(); html = createHtml(html, atrrMap, paras)) {
                    atrrMap = (Map)var6.next();
                }
            }

            return html;
        } else {
            return "";
        }
    }

    public static String parseHtmlForUpdate(String content, Map<String, List<Map<String, Object>>> paras) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        if(!jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String html = (String)jsonObj.get("parse");
            List<Map<String, Object>> list = getAllInputAttr(jsonObj);
            Map atrrMap;
            if(list != null && list.size() > 0) {
                for(Iterator var6 = list.iterator(); var6.hasNext(); html = createHtml(html, atrrMap, paras)) {
                    atrrMap = (Map)var6.next();
                }
            }

            return html;
        } else {
            return "";
        }
    }

    public static String parseHtmlForAddOrUpdate(String content, Map<String, List<Map<String, Object>>> paras) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        if(!jsonObj.isNullObject() && !jsonObj.isEmpty()) {
            String html = (String)jsonObj.get("parse");
            List<Map<String, Object>> list = getAllInputAttr(jsonObj);
            Map atrrMap;
            if(list != null && list.size() > 0) {
                for(Iterator var6 = list.iterator(); var6.hasNext(); html = createHtml(html, atrrMap, paras)) {
                    atrrMap = (Map)var6.next();
                }
            }

            return html;
        } else {
            return "";
        }
    }

    private static String createHtml(String html, Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String leipiplugins = (String)atrrMap.get("leipiplugins");
        String content = (String)atrrMap.get("content");
        String tempHtml;
        if("listctrl".equals(leipiplugins)) {
            tempHtml = getListctrl(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("text".equals(leipiplugins)) {
            tempHtml = getText(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("textarea".equals(leipiplugins)) {
            tempHtml = getTextarea(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("select".equals(leipiplugins)) {
            tempHtml = getSelect(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("radios".equals(leipiplugins)) {
            tempHtml = getRadio(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("checkboxs".equals(leipiplugins)) {
            tempHtml = getCheckbox(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("popup".equals(leipiplugins)) {
            tempHtml = getPopup(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if("macros".equals(leipiplugins)) {
            tempHtml = getMacros(atrrMap, paras);
            html = html.replace(content, tempHtml);
        } else if(leipiplugins == null) {
            tempHtml = getHiddenText(atrrMap, paras);
            html = html.replace(content, tempHtml);
        }

        return html;
    }

    private static String getListctrl(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String name = (String)atrrMap.get("name");
        String temp_html = "";
        String autofield = (String)atrrMap.get("autofield");
        String orgSum = (String)atrrMap.get("orgsum");
        String orgUnit = (String)atrrMap.get("orgunit");
        String orgTitle = (String)atrrMap.get("orgtitle");
        String title = (String)atrrMap.get("title");
        String style = (String)atrrMap.get("style");
        String orgcolvalue = (String)atrrMap.get("orgcolvalue");
        String orgcoltype = (String)atrrMap.get("orgcoltype");
        String pkid = (String)atrrMap.get("pkid");
        String fkdsid = (String)atrrMap.get("fkdsid");
        String isHides = (String)atrrMap.get("ishide");
        String ruletypes = (String)atrrMap.get("ruletype");
        String dicts = (String)atrrMap.get("dict");
        String lenths = (String)atrrMap.get("length");
        if(autofield == null) {
            autofield = "";
        }

        List<String> listAutofield = Arrays.asList(autofield.split("`"));
        List<String> listTitle = Arrays.asList(orgTitle.split("`"));
        List<String> listSum = Arrays.asList(orgSum.split("`"));
        List<String> listUnit = Arrays.asList(orgUnit.split("`"));
        List<String> listValue = Arrays.asList(orgcolvalue.split("`"));
        List<String> listType = Arrays.asList(orgcoltype.split("`"));
        List<String> listIsHide = Arrays.asList(isHides.split("`"));
        List<String> listruletype = Arrays.asList(ruletypes.split("`"));
        List<String> listdict = Arrays.asList(dicts.split("`"));
        List<String> listlength = Arrays.asList(lenths.split("`"));
        int tdCount = listTitle.size();
        String tableId = name + "_table";
        String dsName = getListDsName(listAutofield);
        boolean isExistSum = false;
        String listfk = "<input type=\"hidden\" name=\"listctrl_fk_" + dsName + "\" value=\"" + pkid + "\">";
        String listfkdsid = "<input type=\"hidden\" name=\"listctrl_fkdsid_" + dsName + "\" value=\"" + fkdsid + "\">";
        String rowTemplet = getListctrlRowTemplet(name, atrrMap);
        String temp = listfk + listfkdsid + rowTemplet + "<table id=\"" + name + "_table\" bindTable=\"true\" cellspacing=\"0\" class=\"table table-bordered table-condensed\" style=\"" + style + "\"><thead>{0}</thead><tbody>{1}</tbody>{2}</table><input type=\"hidden\" id=\"tableId\" name=\"tableId\" value=\"" + tableId + "\">";
        String btnAdd = "<span class=\"pull-right\"><button id='" + name + "_listAdd' class=\"btn btn-small btn-success listAdd\" type=\"button\" tbname=\"" + name + "\">添加一行</button></span>";
        String theader = "<tr><th colspan=\"{0}\">{1}{2}</th></tr>{3}";
        String trTitle = "";

        for(int i = 0; i < tdCount; ++i) {
            String isHide = (String)listIsHide.get(i);
            if(!"1".equals(isHide)) {
                trTitle = trTitle + MessageFormat.format("<th>{0}</th>", new Object[]{listTitle.get(i)});
            } else {
                trTitle = trTitle + MessageFormat.format("<th style=\"display:none\">{0}</th>", new Object[]{listTitle.get(i)});
            }

            if(i + 1 == tdCount) {
                trTitle = trTitle + MessageFormat.format("<th><span style='width:60px;display:block;'>{0}</span></th>", new Object[]{"操作"});
            }
        }

        trTitle = "<tr>" + trTitle + "</tr>";
        List<Map<String, Object>> list = new ArrayList();
        if(StringUtil.isNotEmpty(dsName)) {
            list = (List)paras.get(dsName);
        }

        int rowCount = list != null?((List)list).size():1;
        if(rowCount < 1) {
            rowCount = 1;
        }

        StringBuilder sbTr = new StringBuilder();
        String tdSum = "";
        TreeMap<Integer, Float> SumValueDic = new TreeMap();
        int headerColspan = tdCount;

        for(int row = 0; row < rowCount; ++row) {
            Map<String, Object> dataMap = null;
            if(list != null && ((List)list).size() > 0) {
                dataMap = (Map)((List)list).get(row);
                String tr = "";
                String tdContent = "";

                for(int i = 0; i < tdCount; ++i) {
                    String rowName = (String)listAutofield.get(i);
                    Object rowValue = getListFieldValue(dataMap, (String)listAutofield.get(i));
                    String isHide = (String)listIsHide.get(i);
                    String ruletype = "0".equals(listruletype.get(i))?"":(String)listruletype.get(i);
                    String dict = "0".equals(listdict.get(i))?"":(String)listdict.get(i);
                    String length = "#".equals(listlength.get(i))?"":(String)listlength.get(i);
                    String unit = "";
                    if(listUnit != null && listUnit.size() == tdCount && !"#".equals(listUnit.get(i))) {
                        unit = getTypename("units", (String)listUnit.get(i));
                    }

                    String flag = "0";
                    if("1".equals(isHide)) {
                        flag = "1";
                    }

                    String tdname = rowName + "[" + row + "]";
                    String sum = "1".equals(listSum.get(i))?"sum=\"" + rowName + "\"":"";
                    //Object tdValue = "";
                    Object tdValue = rowValue != null?rowValue:"";
                    if(tdValue instanceof Date) {
                        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        tdValue = DateUtils.date2Str((Date)tdValue, sformat);
                    }

                    String type = (String)listType.get(i);
                    if(sum != "") {
                        float tempTdValue = 0.0F;
                        if(SumValueDic.containsKey(Integer.valueOf(i))) {
                            tempTdValue = ((Float)SumValueDic.get(Integer.valueOf(i))).floatValue();
                        }

                        try {
                            float resultTdTemp = 0.0F;
                            resultTdTemp = Float.parseFloat((String)tdValue);
                            tempTdValue += resultTdTemp;
                        } catch (Exception var64) {
                            tdValue = "0";
                        }

                        if(SumValueDic.containsKey(Integer.valueOf(i))) {
                            SumValueDic.subMap(Integer.valueOf(i), Integer.valueOf(i));
                        } else {
                            SumValueDic.put(Integer.valueOf(i), Float.valueOf(tempTdValue));
                        }
                    }

                    if(!"1".equals(flag)) {
                        if("text".equals(type)) {
                            tdContent = "<td><input class=\"input-medium\" type=\"text\"";
                            if(StringUtils.isNotBlank(ruletype)) {
                                tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                            } else if(StringUtils.isNotBlank(length)) {
                                tdContent = tdContent + " style=\"width:" + length + "px\"";
                            }

                            tdContent = tdContent + "value=\"{0}\" name=\"{1}\" {2}>";
                            if(!"#".equals(unit)) {
                                tdContent = tdContent + unit;
                            }

                            tdContent = tdContent + "</td>";
                            tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                        } else if("int".equals(type)) {
                            tdContent = "<td><input class=\"input-medium\" type=\"text\"";
                            if(StringUtils.isNotBlank(ruletype)) {
                                tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                            } else if(StringUtils.isNotBlank(length)) {
                                tdContent = tdContent + " style=\"width:" + length + "px\"";
                            }

                            tdContent = tdContent + "value=\"{0}\" name=\"{1}\" {2}>";
                            if(!"#".equals(unit)) {
                                tdContent = tdContent + unit;
                            }

                            tdContent = tdContent + "</td>";
                            tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue, tdname, sum});
                        } else if("textarea".equals(type)) {
                            tdContent = "<td><textarea  ";
                            if(StringUtils.isNotBlank(ruletype)) {
                                tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                            } else if(StringUtils.isNotBlank(length)) {
                                tdContent = tdContent + " style=\"width:" + length + "px\"";
                            }

                            tdContent = tdContent + " name=\"{1}\" {2}>{0}</textarea></td>";
                            tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                        } else if("calc".equals(type)) {
                            tdContent = "<td><input class=\"input-medium\" type=\"text\"";
                            if(StringUtils.isNotBlank(ruletype)) {
                                tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                            } else if(StringUtils.isNotBlank(length)) {
                                tdContent = tdContent + " style=\"width:" + length + "px\"";
                            }

                            tdContent = tdContent + "value=\"{0}\" name=\"{1}\" {2}></td>";
                            tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue, tdname, sum});
                        } else {
                            StringBuffer checkboxBuff;
                            if("radio".equals(type)) {
                                checkboxBuff = new StringBuffer();
                                if(StringUtils.isNotBlank(dict)) {
                                    checkboxBuff.append("<td>");
                                    checkboxBuff.append(getDicts(dict, "radio", tdValue));
                                    checkboxBuff.append("</td>");
                                    tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                                } else {
                                    checkboxBuff.append("<td><input class=\"input-medium\" type=\"radio\" value=\"{0}\" name=\"{1}\"></td>");
                                    tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdValue, tdname});
                                }
                            } else if("select".equals(type)) {
                                checkboxBuff = new StringBuffer();
                                if(StringUtils.isNotBlank(dict)) {
                                    checkboxBuff.append("<td><select name=\"{0}\">");
                                    checkboxBuff.append(getDicts(dict, "select", tdValue));
                                    checkboxBuff.append("</select></td>");
                                } else {
                                    checkboxBuff.append("<td><select class=\"input-medium\" name=\"{0}\"></select></td>");
                                }

                                tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                            } else if("checkbox".equals(type)) {
                                checkboxBuff = new StringBuffer();
                                if(StringUtils.isNotBlank(dict)) {
                                    checkboxBuff.append("<td>");
                                    checkboxBuff.append(getDicts(dict, "checkbox", tdValue));
                                    checkboxBuff.append("</td>");
                                    tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                                } else {
                                    checkboxBuff.append("<td><input class=\"input-medium\" type=\"checkbox\" value=\"{0}\" name=\"{1}\"></td>");
                                    tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdValue, tdname});
                                }
                            } else if("popup".equals(type)) {
                                CgreportConfigHeadEntity cgreportConfigHeadEntity = getCgreportConfigHeadEntity(dict);
                                if(cgreportConfigHeadEntity != null) {
                                    StringBuffer radioBuff = new StringBuffer();
                                    radioBuff.append("<td><input id=\"{0}\" name=\"{0}\" type=\"text\"  class=\"input-medium\" style=\"background: url(plug-in/easyui/themes/default/images/searchbox_button.png) 100% 50% no-repeat rgb(255, 255, 255);\" onClick='inputClick(this,\"" + cgreportConfigHeadEntity.getReturnValField() + "\",\"" + dict + "\");'  value=\"{1}\"></td>");
                                    tr = tr + MessageFormat.format(radioBuff.toString(), new Object[]{tdname, tdValue});
                                }
                            }
                        }
                    } else {
                        --headerColspan;
                        tr = tr + MessageFormat.format("<td style=\"display:none\"><input class=\"input-medium\" type=\"hidden\" value=\"{0}\" name=\"{1}\" {2}></td>", new Object[]{tdValue, tdname, sum});
                    }

                    if(i + 1 == tdCount) {
                        tr = tr + "<td><button class=\"btn btn-small btn-success delrow\" type=\"button\">删除</button></td>";
                    }

                    if(row == 0 && !"1".equals(flag)) {
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
        }

        if(!StringUtils.isBlank(tdSum)) {
            for(Iterator var69 = SumValueDic.keySet().iterator(); var69.hasNext(); tdSum = MessageFormat.format("<tbody class=\"sum\"><tr>{0}</tr></tbody>", new Object[]{tdSum})) {
                Integer i = (Integer)var69.next();
                tdSum = tdSum.replace("value" + i, ((Float)SumValueDic.get(i)).toString());
            }
        }

        theader = MessageFormat.format(theader, new Object[]{Integer.valueOf(headerColspan + 1), title, btnAdd, trTitle});
        temp_html = MessageFormat.format(temp, new Object[]{theader, sbTr.toString(), tdSum});
        String divId = name + "_row_templet";
        temp_html = temp_html + "<script type=\"text/javascript\">";
        temp_html = temp_html + "$(function(){";
        temp_html = temp_html + "$(\"#" + name + "_listAdd\").click(function(){";
        temp_html = temp_html + "var tbHtml ='<tr>'+ $(\"#" + divId + "\").val().replace(/#textarea#/g,'textarea')+'</tr>';";
        if(isExistSum) {
            temp_html = temp_html + "$(\"#" + name + "_table" + " tbody\").eq(0).append(tbHtml);";
        } else {
            temp_html = temp_html + "$(\"#" + name + "_table" + " tbody\").eq(0).append(tbHtml);";
        }

        temp_html = temp_html + "$(\".delrow\").die().live(\"click\",function(){$(this).parent().parent().remove();" + name + "_resetTrNum();});" + name + "_resetTrNum();";
        temp_html = temp_html + "});";
        temp_html = temp_html + "$(\".delrow\").click(function(){$(this).parent().parent().remove();" + name + "_resetTrNum();});";
        temp_html = temp_html + "});";
        temp_html = temp_html + "function " + name + "_resetTrNum() {";
        temp_html = temp_html + "$(\"#" + name + "_table tbody tr\").each(function(i) {";
        temp_html = temp_html + "\t$(':input, select,a', this).each(function() {";
        temp_html = temp_html + "\tvar $this = $(this), name = $this.attr('name'), val = $this.val();";
        temp_html = temp_html + "\tif (name != null) {";
        temp_html = temp_html + "\t    if(name.indexOf('[')){";
        temp_html = temp_html + "            var subnames = name.split('[');";
        temp_html = temp_html + "            var newname = subnames[0]+'['+(i)+']';";
        temp_html = temp_html + "            $this.attr('name',newname); ";
        temp_html = temp_html + "}else{";
        temp_html = temp_html + "         var newname = name+  '['+i+']';";
        temp_html = temp_html + "         $this.attr('name',newname);";
        temp_html = temp_html + "      }";
        temp_html = temp_html + "\t }";
        temp_html = temp_html + " });";
        temp_html = temp_html + "});";
        temp_html = temp_html + "}";
        temp_html = temp_html + "</script>";
        return temp_html;
    }

    private static String getListctrlRowTemplet(String name, Map<String, Object> atrrMap) {
        String autofield = (String)atrrMap.get("autofield");
        String orgcoltype = (String)atrrMap.get("orgcoltype");
        String isHides = (String)atrrMap.get("ishide");
        String ruletypes = (String)atrrMap.get("ruletype");
        String dicts = (String)atrrMap.get("dict");
        String lenths = (String)atrrMap.get("length");
        String orgUnit = (String)atrrMap.get("orgunit");
        if(autofield == null) {
            autofield = "";
        }

        List<String> listAutofield = Arrays.asList(autofield.split("`"));
        List<String> listType = Arrays.asList(orgcoltype.split("`"));
        List<String> listIsHide = Arrays.asList(isHides.split("`"));
        List<String> listruletype = Arrays.asList(ruletypes.split("`"));
        List<String> listdict = Arrays.asList(dicts.split("`"));
        List<String> listlength = Arrays.asList(lenths.split("`"));
        List<String> listUnit = Arrays.asList(orgUnit.split("`"));
        StringBuilder rowTempletDiv = new StringBuilder();
        Map<String, Object> dataMap = new HashMap();
        String tr = "";
        int tdCount = listAutofield.size();

        for(int i = 0; i < tdCount; ++i) {
            String rowName = (String)listAutofield.get(i);
            Object rowValue = getListFieldValue(dataMap, (String)listAutofield.get(i));
            String ruletype = "0".equals(listruletype.get(i))?"":(String)listruletype.get(i);
            String isHide = (String)listIsHide.get(i);
            String flag = "0";
            if("1".equals(isHide)) {
                flag = "1";
            }

            String length = "#".equals(listlength.get(i))?"":(String)listlength.get(i);
            String dict = "0".equals(listdict.get(i))?"":(String)listdict.get(i);
            String unit = "";
            if(listUnit != null && listUnit.size() == tdCount && !"#".equals(listUnit.get(i))) {
                unit = getTypename("units", (String)listUnit.get(i));
            }

            String tdname = rowName + "[#index#]";
            String sum = "";
            //Object tdValue = "";
            Object tdValue = rowValue != null?rowValue:"";
            if(tdValue instanceof Date) {
                SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                tdValue = DateUtils.date2Str((Date)tdValue, sformat);
            }

            String type = (String)listType.get(i);
            String tdContent = "";
            if(!"1".equals(flag)) {
                if("text".equals(type)) {
                    tdContent = tdContent + "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}\" {2}";
                    if(StringUtils.isNotBlank(ruletype)) {
                        tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                    } else if(StringUtils.isNotBlank(length)) {
                        tdContent = tdContent + " style=\"width:" + length + "px\"";
                    }

                    tdContent = tdContent + ">";
                    if(!"#".equals(unit)) {
                        tdContent = tdContent + unit;
                    }

                    tdContent = tdContent + "</td>";
                    tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                } else if("int".equals(type)) {
                    tdContent = tdContent + "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}\" {2}";
                    if(StringUtils.isNotBlank(ruletype)) {
                        tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                    } else if(StringUtils.isNotBlank(length)) {
                        tdContent = tdContent + " style=\"width:" + length + "px\"";
                    }

                    tdContent = tdContent + ">";
                    if(!"#".equals(unit)) {
                        tdContent = tdContent + unit;
                    }

                    tdContent = tdContent + "</td>";
                    tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                } else if("textarea".equals(type)) {
                    tdContent = "<td><#textarea#  ";
                    if(StringUtils.isNotBlank(ruletype)) {
                        tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                    } else if(StringUtils.isNotBlank(length)) {
                        tdContent = tdContent + " style=\"width:" + length + "px\"";
                    }

                    tdContent = tdContent + " name=\"{1}\" {2}>{0}</#textarea#></td>";
                    tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                } else if("calc".equals(type)) {
                    tdContent = tdContent + "<td><input class=\"input-medium\" type=\"text\" value=\"{0}\" name=\"{1}\" {2}";
                    if(StringUtils.isNotBlank(ruletype)) {
                        tdContent = tdContent + " datatype=\"" + ruletype + "\" ";
                    } else if(StringUtils.isNotBlank(length)) {
                        tdContent = tdContent + " style=\"width:" + length + "px\"";
                    }

                    tdContent = tdContent + "></td>";
                    tr = tr + MessageFormat.format(tdContent, new Object[]{tdValue.toString(), tdname, sum});
                } else {
                    StringBuffer checkboxBuff;
                    if("radio".equals(type)) {
                        checkboxBuff = new StringBuffer();
                        if(StringUtils.isNotBlank(dict)) {
                            checkboxBuff.append("<td>");
                            checkboxBuff.append(getDicts(dict, "radio", (Object)null));
                            checkboxBuff.append("</td>");
                            tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                        } else {
                            checkboxBuff.append("<td><input class=\"input-medium\" type=\"radio\" value=\"{0}\" name=\"{1}\"></td>");
                            tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdValue, tdname});
                        }
                    } else if("select".equals(type)) {
                        checkboxBuff = new StringBuffer();
                        if(StringUtils.isNotBlank(dict)) {
                            checkboxBuff.append("<td><select name=\"{0}\">");
                            checkboxBuff.append(getDicts(dict, "select", (Object)null));
                            checkboxBuff.append("</select></td>");
                        } else {
                            checkboxBuff.append("<td><select class=\"input-medium\" name=\"{0}\"></select></td>");
                        }

                        tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                    } else if("checkbox".equals(type)) {
                        checkboxBuff = new StringBuffer();
                        if(StringUtils.isNotBlank(dict)) {
                            checkboxBuff.append("<td>");
                            checkboxBuff.append(getDicts(dict, "checkbox", (Object)null));
                            checkboxBuff.append("</td>");
                            tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdname});
                        } else {
                            checkboxBuff.append("<td><input class=\"input-medium\" type=\"checkbox\" value=\"{0}\" name=\"{1}\"></td>");
                            tr = tr + MessageFormat.format(checkboxBuff.toString(), new Object[]{tdValue, tdname});
                        }
                    } else if("popup".equals(type)) {
                        CgreportConfigHeadEntity cgreportConfigHeadEntity = getCgreportConfigHeadEntity(dict);
                        if(cgreportConfigHeadEntity != null) {
                            StringBuffer radioBuff = new StringBuffer();
                            radioBuff.append("<td><input id=\"{0}\" name=\"{0}\" type=\"text\"  class=\"input-medium\" style=\"background: url(plug-in/easyui/themes/default/images/searchbox_button.png) 100% 50% no-repeat rgb(255, 255, 255);\" onClick='inputClick(this,\"" + cgreportConfigHeadEntity.getReturnValField() + "\",\"" + dict + "\");' value=\"\"></td>");
                            tr = tr + MessageFormat.format(radioBuff.toString(), new Object[]{tdname});
                        }
                    }
                }
            } else {
                tr = tr + MessageFormat.format("<td style=\"display:none\"><input class=\"input-medium\" type=\"hidden\" value=\"{0}\" name=\"{1}\" {2}></td>", new Object[]{tdValue, tdname, sum});
            }

            if(i + 1 == tdCount) {
                tr = tr + "<td><button class=\"btn btn-small btn-success delrow\" type=\"button\">删除</button></td>";
            }
        }

        String divId = name + "_row_templet";
        rowTempletDiv.append(MessageFormat.format("<textarea id=\"" + divId + "\" style=\"display:none\" disabled=\"true\" >{0}</textarea>", new Object[]{tr}));
        return rowTempletDiv.toString();
    }

    private static String getText(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String style = (String)atrrMap.get("style");
        String title = (String)atrrMap.get("title");
        String name = (String)atrrMap.get("name");
        String orghide = (String)atrrMap.get("orghide");
        String orgtype = (String)atrrMap.get("orgtype");
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        StringBuilder sb = new StringBuilder();
        sb.append("<input ");
        if("1".equals(orghide)) {
            sb.append(" type=").append("\"").append("hidden").append("\"");
        } else if(!"standardDate".equals(orgtype) && !"fullDate".equals(orgtype)) {
            sb.append(" type=").append("\"").append(orgtype).append("\"");
        } else {
            sb.append(" type=").append("\"text\"");
            if("fullDate".equals(orgtype)) {
                sb.append(" class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});\"");
            } else if("standardDate".equals(orgtype)) {
                sb.append(" class=\"Wdate\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd'});\"");
            }
        }

        sb.append(" style=").append("\"").append(style).append("\"");
        sb.append(" title=").append("\"").append(title).append("\"");
        sb.append(" name=").append("\"").append(name).append("\"");
        sb.append(" value=").append("\"").append(getSingleValue(autofield, paras)).append("\"");
        if(StringUtils.isNotBlank(datatype)) {
            sb.append(" datatype=").append("\"").append(datatype).append("\"");
        }

        sb.append(" />");
        html = sb.toString();
        return html;
    }

    private static String getTextarea(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String style = (String)atrrMap.get("style");
        String title = (String)atrrMap.get("title");
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        StringBuilder sb = new StringBuilder();
        sb.append("<textarea ");
        sb.append(" style=").append("\"").append(style).append("\"");
        sb.append(" title=").append("\"").append(title).append("\"");
        sb.append(" name=").append("\"").append(autofield).append("\"");
        if(StringUtils.isNotBlank(datatype)) {
            sb.append(" datatype=").append("\"").append(datatype).append("\"");
        }

        sb.append(" >");
        sb.append(getSingleValue(autofield, paras));
        sb.append("</textarea>");
        html = sb.toString();
        return html;
    }

    private static String getHiddenText(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String name = (String)atrrMap.get("name");
        String autofield = (String)atrrMap.get("autofield");
        StringBuilder sb = new StringBuilder();
        sb.append("<input ");
        sb.append(" type=").append("\"").append("hidden").append("\"");
        sb.append(" name=").append("\"").append(name).append("\"");
        sb.append(" value=").append("\"").append(getSingleValue(autofield, paras)).append("\"");
        sb.append(" />");
        html = sb.toString();
        return html;
    }

    private static String getPopup(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String style = (String)atrrMap.get("style");
        String title = (String)atrrMap.get("title");
        String name = (String)atrrMap.get("name");
        String orgwidth = (String)atrrMap.get("orgwidth");
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        String dictionary = (String)atrrMap.get("dictionary");
        String[] dicts = dictionary.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("<input ");
        sb.append(" type=").append("\"text\"");
        sb.append(" style=").append("\"").append("width: auto; background: url(plug-in/easyui/themes/default/images/searchbox_button.png) 100% 50% no-repeat rgb(255, 255, 255);").append("\"");
        sb.append(" title=").append("\"").append(title).append("\"");
        sb.append(" name=").append("\"").append(name).append("\"");
        sb.append(" value=").append("\"").append(getSingleValue(autofield, paras)).append("\"");
        if(StringUtils.isNotBlank(datatype)) {
            sb.append(" datatype=").append("\"").append(datatype).append("\"");
        }

        if(dicts.length == 3) {
            sb.append(" onClick=").append("\"").append("inputClick(this,'" + dicts[1] + "','" + dicts[0] + "');").append("\"");
        }

        sb.append(" />");
        html = sb.toString();
        return html;
    }

    private static String getCheckbox(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        StringBuilder sb = new StringBuilder();
        JSONArray options = (JSONArray)atrrMap.get("options");
        new ArrayList();
        List<Map<String, Object>> list = JSONHelper.toList(options);

        String checkedtext;
        for(Iterator var9 = list.iterator(); var9.hasNext(); sb.append(">").append(checkedtext).append("&nbsp;")) {
            Map<String, Object> map = (Map)var9.next();
            checkedtext = (String)map.get("checkedtext");
            String value = (String)map.get("value");
            sb.append("<input type=\"checkbox\"");
            sb.append(" name=\"").append(autofield).append("\"");
            sb.append(" value=\"").append(value).append("\"");
            if(StringUtils.isNotBlank(datatype)) {
                sb.append(" datatype=").append("\"").append(datatype).append("\"");
            }

            Object datavalue = getSingleValue(autofield, paras);
            if(datavalue != null && datavalue.equals(value)) {
                sb.append(" checked=\"checked\"");
            }
        }

        html = sb.toString();
        return html;
    }

    private static String getRadio(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        StringBuilder sb = new StringBuilder();
        JSONArray options = (JSONArray)atrrMap.get("options");
        new ArrayList();
        List<Map<String, Object>> list = JSONHelper.toList(options);

        String checkedtext;
        for(Iterator var9 = list.iterator(); var9.hasNext(); sb.append(">").append(checkedtext).append("&nbsp;")) {
            Map<String, Object> map = (Map)var9.next();
            checkedtext = (String)map.get("checkedtext");
            String value = (String)map.get("value");
            sb.append("<input type=\"radio\"");
            sb.append(" name=\"").append(autofield).append("\"");
            sb.append(" value=\"").append(value).append("\"");
            if(StringUtils.isNotBlank(datatype)) {
                sb.append(" datatype=").append("\"").append(datatype).append("\"");
            }

            Object datavalue = getSingleValue(autofield, paras);
            if(datavalue != null && datavalue.equals(value)) {
                sb.append(" checked=\"checked\"");
            }
        }

        html = sb.toString();
        return html;
    }

    private static String getSelect(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String style = (String)atrrMap.get("style");
        String title = (String)atrrMap.get("title");
        String size = (String)atrrMap.get("size");
        String autofield = (String)atrrMap.get("autofield");
        String datatype = (String)atrrMap.get("datatype");
        StringBuilder sb = new StringBuilder();
        String content = (String)atrrMap.get("content");
        sb.append("<select ");
        sb.append("style=").append("\"").append(style).append("\"");
        sb.append(" size=").append("\"").append(size).append("\"");
        sb.append(" title=").append("\"").append(title).append("\"");
        sb.append(" name=").append("\"").append(autofield).append("\"");
        if(StringUtils.isNotBlank(datatype)) {
            sb.append(" datatype=").append("\"").append(datatype).append("\"");
        }

        sb.append(">");
        sb.append(getSelectOption(content, (String)getSingleValue(autofield, paras)));
        sb.append("</select>");
        html = sb.toString();
        return html;
    }

    private static String getSelectOption(String content, String value) {
        StringBuilder option = new StringBuilder();
        Document doc = Jsoup.parse(content);
        Elements selectElements = doc.getElementsByTag("select").select("option");
        Iterator var6 = selectElements.iterator();

        while(var6.hasNext()) {
            Element el = (Element)var6.next();
            if(el.attr("value").equals(value)) {
                option.append(" <option value=\"").append(el.attr("value")).append("\" selected>").append(el.text()).append("</option>");
            } else {
                option.append(" <option value=\"").append(el.attr("value")).append("\">").append(el.text()).append("</option>");
            }
        }

        return option.toString();
    }

    private static String getMacros(Map<String, Object> atrrMap, Map<String, List<Map<String, Object>>> paras) {
        String html = "";
        String orgtype = (String)atrrMap.get("orgtype");
        String title = (String)atrrMap.get("title");
        String orghide = (String)atrrMap.get("orghide");
        String name = (String)atrrMap.get("name");
        String style = (String)atrrMap.get("style");
        StringBuilder sb = new StringBuilder();
        sb.append("<input ");
        if("1".equals(orghide)) {
            sb.append("type=").append("\"").append("hidden").append("\"");
        } else {
            sb.append("type=").append("\"text\"");
        }

        sb.append("style=").append("\"").append(style).append("\"");
        sb.append("title=").append("\"").append(title).append("\"");
        sb.append("name=").append("\"").append(name).append("\"");
        sb.append("value=").append("\"").append(getFormat(orgtype)).append("\"");
        sb.append(" readonly=\"true\" />");
        html = sb.toString();
        return html;
    }

    private static String getFormat(String orgtype) {
        String orgName = "";
        TSUser user = ClientManager.getInstance().getClient().getUser();
        SimpleDateFormat format = null;
        if("sys_datetime".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orgName = format.format(new Date());
        } else if("sys_date".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy-MM-dd");
            orgName = format.format(new Date());
        } else if("sys_date_cn".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy年MM月dd日");
            orgName = format.format(new Date());
        } else if("sys_date_cn_short1".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy年MM月");
            orgName = format.format(new Date());
        } else if("sys_date_cn_short4".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy");
            orgName = format.format(new Date());
        } else if("sys_date_cn_short3".equals(orgtype)) {
            format = new SimpleDateFormat("yyyy年");
            orgName = format.format(new Date());
        } else if("sys_date_cn_short2".equals(orgtype)) {
            format = new SimpleDateFormat("MM月dd日");
            orgName = format.format(new Date());
        } else if("sys_time".equals(orgtype)) {
            format = new SimpleDateFormat("HH:mm:ss");
            orgName = format.format(new Date());
        } else if("sys_week".equals(orgtype)) {
            orgName = getWeekDay();
        } else if("sys_userid".equals(orgtype)) {
            orgName = user.getUserName();
        } else if("sys_realname".equals(orgtype)) {
            orgName = user.getRealName();
        }

        return orgName;
    }

    private static String getWeekDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(7);
        String day = "";
        switch(dayOfWeek) {
            case 1:
                day = "星期日";
            case 2:
                day = "星期一";
            case 3:
                day = "星期二";
            case 4:
                day = "星期三";
            case 5:
                day = "星期四";
            case 6:
                day = "星期五";
            case 7:
                day = "星期六";
            default:
                return day;
        }
    }

    private static List<Map<String, Object>> getAllInputAttr(JSONObject jsonObj) {
        new ArrayList();
        List<Map<String, Object>> list = JSONHelper.toList(jsonObj.get("data"));
        return list;
    }

    private static Object getSingleValue(String autofield, Map<String, List<Map<String, Object>>> paras) {
        Object value = "";
        if(autofield == null) {
            autofield = "";
        }

        String[] name = autofield.split("\\.");
        if(name.length == 2) {
            List<Map<String, Object>> list = (List)paras.get(name[0]);
            if(list != null && list.size() > 0) {
                Map<String, Object> map = (Map)list.get(0);
                if(map != null) {
                    value = map.get(name[1]);
                    if(value == null) {
                        value = "";
                    }
                }
            }
        }

        return value;
    }

    private static String getListDsName(List<String> listAutofield) {
        String dsName = null;
        if(listAutofield != null && listAutofield.size() > 0) {
            Iterator var3 = listAutofield.iterator();

            while(var3.hasNext()) {
                String dsAtrr = (String)var3.next();
                if(StringUtil.isNotEmpty(dsAtrr)) {
                    String[] dsNames = dsAtrr.split("\\.");
                    if(dsNames.length == 2) {
                        dsName = dsNames[0];
                        break;
                    }
                }
            }
        }

        return dsName;
    }

    private static String getListFieldName(String field) {
        String name = null;
        if(StringUtil.isNotEmpty(field)) {
            String[] names = field.split("\\.");
            if(names.length == 2) {
                name = names[1];
            }
        }

        return name;
    }

    private static Object getListFieldValue(Map<String, Object> dataMap, String field) {
        Object obj = "";
        String fieldName = getListFieldName(field);
        if(fieldName != null) {
            obj = dataMap.get(fieldName);
            if(obj == null) {
                obj = "";
            }
        }

        return obj;
    }

    private static String getDicts(String typeGroupCode, String type, Object tdValue) {
        List<TSType> types = (List)ResourceUtil.allTypes.get(typeGroupCode.toLowerCase());
        StringBuffer content = new StringBuffer();
        if(types.size() > 0) {
            Iterator var6 = types.iterator();

            while(var6.hasNext()) {
                TSType tSType = (TSType)var6.next();
                if("select".equals(type)) {
                    content.append("<option value=\"").append(tSType.getTypecode()).append("\"");
                    if(tdValue != null && tdValue.equals(tSType.getTypecode())) {
                        content.append(" selected=\"selected\"");
                    }

                    content.append(">");
                    content.append(getMutiLang(tSType.getTypename())).append("</option>");
                } else if("radio".equals(type)) {
                    content.append("<input type=\"radio\" name=\"{0}\" value=\"").append(tSType.getTypecode()).append("\"");
                    if(tdValue != null && tdValue.equals(tSType.getTypecode())) {
                        content.append(" checked=\"checked\"");
                    }

                    content.append(">").append(getMutiLang(tSType.getTypename()));
                } else if("checkbox".equals(type)) {
                    content.append("<input type=\"checkbox\" name=\"{0}\" value=\"").append(tSType.getTypecode()).append("\"");
                    if(tdValue != null && tdValue.equals(tSType.getTypecode())) {
                        content.append(" checked=\"checked\"");
                    }

                    content.append(">").append(getMutiLang(tSType.getTypename()));
                }
            }
        }

        return content.toString();
    }

    private static String getMutiLang(String key) {
        if(mutiLangService == null) {
            mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
        }

        String lang_context = mutiLangService.getLang(key);
        return lang_context;
    }

    private static String getTypename(String typeGroupCode, String code) {
        List<TSType> types = (List)ResourceUtil.allTypes.get(typeGroupCode.toLowerCase());
        String codename = "";
        Iterator var5 = types.iterator();

        while(var5.hasNext()) {
            TSType tSType = (TSType)var5.next();
            if(tSType.getTypecode().equals(code)) {
                codename = tSType.getTypename();
                break;
            }
        }

        return codename;
    }

    private static CgreportConfigHeadEntity getCgreportConfigHeadEntity(String code) {
        CgreportConfigHeadEntity cgreportConfigHeadEntity = null;
        if(cgreportConfigHeadService == null) {
            cgreportConfigHeadService = (CgreportConfigHeadServiceI)ApplicationContextUtil.getContext().getBean(CgreportConfigHeadServiceI.class);
        }

        cgreportConfigHeadEntity = (CgreportConfigHeadEntity)cgreportConfigHeadService.findUniqueByProperty(CgreportConfigHeadEntity.class, "code", code);
        return cgreportConfigHeadEntity;
    }
}
