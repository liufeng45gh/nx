//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.tag.core.easyui;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

public class DictSelectTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String typeGroupCode;
    private String field;
    private String id;
    private String defaultVal;
    private String divClass;
    private String labelClass;
    private String title;
    private boolean hasLabel = true;
    private String type;
    private String dictTable;
    private String dictField;
    private String dictText;
    private String extendJson;
    private String readonly;
    private String dictCondition;
    private String datatype;
    @Autowired
    private static SystemService systemService;

    public DictSelectTag() {
    }

    public String getReadonly() {
        return this.readonly;
    }

    public void setReadonly(String readonly) {
        this.readonly = readonly;
    }

    public String getDictCondition() {
        return this.dictCondition;
    }

    public void setDictCondition(String dicCondition) {
        this.dictCondition = dicCondition;
    }

    public String getDatatype() {
        return this.datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public int doStartTag() throws JspTagException {
        return 6;
    }

    public int doEndTag() throws JspTagException {
        JspWriter out = null;

        try {
            out = this.pageContext.getOut();
            out.print(this.end().toString());
            out.flush();
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            try {
                out.clear();
                out.close();
            } catch (Exception var10) {
                ;
            }

        }

        return 6;
    }

    public StringBuffer end() {
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isBlank(this.divClass)) {
            this.divClass = "form";
        }

        if(StringUtils.isBlank(this.labelClass)) {
            this.labelClass = "Validform_label";
        }

        if(this.dictTable != null) {
            List<Map<String, Object>> list = this.queryDic();
            Map map;
            Iterator var4;
            if("radio".equals(this.type)) {
                var4 = list.iterator();

                while(var4.hasNext()) {
                    map = (Map)var4.next();
                    this.radio(map.get("text").toString(), map.get("field").toString(), sb);
                }
            } else if("checkbox".equals(this.type)) {
                var4 = list.iterator();

                while(var4.hasNext()) {
                    map = (Map)var4.next();
                    this.checkbox(map.get("text").toString(), map.get("field").toString(), sb);
                }
            } else if("text".equals(this.type)) {
                var4 = list.iterator();

                while(var4.hasNext()) {
                    map = (Map)var4.next();
                    this.text(map.get("text").toString(), map.get("field").toString(), sb);
                }
            } else {
                sb.append("<select name=\"" + this.field + "\"");
                this.readonly(sb);
                if(!StringUtils.isBlank(this.extendJson)) {
                    Gson gson = new Gson();
                    Map<String, String> mp = (Map)gson.fromJson(this.extendJson, Map.class);
                    Iterator var6 = mp.entrySet().iterator();

                    while(var6.hasNext()) {
                        Entry<String, String> entry = (Entry)var6.next();
                        sb.append((String)entry.getKey() + "=\"" + (String)entry.getValue() + "\"");
                    }
                }

                if(!StringUtils.isBlank(this.id)) {
                    sb.append(" id=\"" + this.id + "\"");
                }

                sb.append(">");
                this.select("common.please.select", "", sb);
                var4 = list.iterator();

                while(var4.hasNext()) {
                    map = (Map)var4.next();
                    this.select(map.get("text").toString(), map.get("field").toString(), sb);
                }

                sb.append("</select>");
            }
        } else {
            TSTypegroup typeGroup = (TSTypegroup)ResourceUtil.allTypeGroups.get(this.typeGroupCode.toLowerCase());
            List<TSType> types = (List)ResourceUtil.allTypes.get(this.typeGroupCode.toLowerCase());
            if(this.hasLabel) {
                sb.append("<div class=\"" + this.divClass + "\">");
                sb.append("<label class=\"" + this.labelClass + "\" >");
            }

            if(typeGroup != null) {
                if(this.hasLabel) {
                    if(StringUtils.isBlank(this.title)) {
                        this.title = MutiLangUtil.getMutiLangInstance().getLang(typeGroup.getTypegroupname());
                    }

                    sb.append(this.title + ":");
                    sb.append("</label>");
                }

                Iterator var14;
                TSType type;
                if("radio".equals(this.type)) {
                    var14 = types.iterator();

                    while(var14.hasNext()) {
                        type = (TSType)var14.next();
                        this.radio(type.getTypename(), type.getTypecode(), sb);
                    }
                } else if("checkbox".equals(this.type)) {
                    var14 = types.iterator();

                    while(var14.hasNext()) {
                        type = (TSType)var14.next();
                        this.checkbox(type.getTypename(), type.getTypecode(), sb);
                    }
                } else if("text".equals(this.type)) {
                    var14 = types.iterator();

                    while(var14.hasNext()) {
                        type = (TSType)var14.next();
                        this.text(type.getTypename(), type.getTypecode(), sb);
                    }
                } else {
                    sb.append("<select name=\"" + this.field + "\"");
                    this.readonly(sb);
                    if(!StringUtils.isBlank(this.extendJson)) {
                        Gson gson = new Gson();
                        Map<String, String> mp = (Map)gson.fromJson(this.extendJson, Map.class);
                        Iterator var7 = mp.entrySet().iterator();

                        while(var7.hasNext()) {
                            Entry<String, String> entry = (Entry)var7.next();
                            sb.append(" " + (String)entry.getKey() + "=\"" + (String)entry.getValue() + "\"");
                        }
                    }

                    if(!StringUtils.isBlank(this.id)) {
                        sb.append(" id=\"" + this.id + "\"");
                    }

                    this.datatype(sb);
                    sb.append(">");
                    this.select("common.please.select", "", sb);
                    var14 = types.iterator();

                    while(var14.hasNext()) {
                        type = (TSType)var14.next();
                        this.select(type.getTypename(), type.getTypecode(), sb);
                    }

                    sb.append("</select>");
                }

                if(this.hasLabel) {
                    sb.append("</div>");
                }
            }
        }

        return sb;
    }

    private void text(String name, String code, StringBuffer sb) {
        if(code.equals(this.defaultVal)) {
            sb.append("<input name='" + this.field + "'" + " id='" + this.id + "' value='" + MutiLangUtil.getMutiLangInstance().getLang(name) + "' readOnly = 'readOnly' />");
        }

    }

    private void radio(String name, String code, StringBuffer sb) {
        if(code.equals(this.defaultVal)) {
            sb.append("<input type=\"radio\" name=\"" + this.field + "\" checked=\"checked\" value=\"" + code + "\"");
            if(!StringUtils.isBlank(this.id)) {
                sb.append(" id=\"" + this.id + "\"");
            }

            this.readonly(sb);
            this.datatype(sb);
            sb.append(" />");
        } else {
            sb.append("<input type=\"radio\" name=\"" + this.field + "\" value=\"" + code + "\"");
            if(!StringUtils.isBlank(this.id)) {
                sb.append(" id=\"" + this.id + "\"");
            }

            this.readonly(sb);
            this.datatype(sb);
            sb.append(" />");
        }

        sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
    }

    private void checkbox(String name, String code, StringBuffer sb) {
        String[] values = this.defaultVal.split(",");
        Boolean checked = Boolean.valueOf(false);

        for(int i = 0; i < values.length; ++i) {
            String value = values[i];
            if(code.equals(value)) {
                checked = Boolean.valueOf(true);
                break;
            }

            checked = Boolean.valueOf(false);
        }

        if(checked.booleanValue()) {
            sb.append("<input type=\"checkbox\" name=\"" + this.field + "\" checked=\"checked\" value=\"" + code + "\"");
            if(!StringUtils.isBlank(this.id)) {
                sb.append(" id=\"" + this.id + "\"");
            }

            this.readonly(sb);
            this.datatype(sb);
            sb.append(" />");
        } else {
            sb.append("<input type=\"checkbox\" name=\"" + this.field + "\" value=\"" + code + "\"");
            if(!StringUtils.isBlank(this.id)) {
                sb.append(" id=\"" + this.id + "\"");
            }

            this.readonly(sb);
            this.datatype(sb);
            sb.append(" />");
        }

        sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
    }

    private void select(String name, String code, StringBuffer sb) {
        if(code.equals(this.defaultVal)) {
            sb.append(" <option value=\"" + code + "\" selected=\"selected\">");
        } else {
            sb.append(" <option value=\"" + code + "\">");
        }

        sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
        sb.append(" </option>");
    }

    private List<Map<String, Object>> queryDic() {
        String sql = "select " + this.dictField + " as field," + this.dictText + " as text from " + this.dictTable;
        if(this.dictCondition != null) {
            sql = sql + this.dictCondition;
        }

        systemService = (SystemService)ApplicationContextUtil.getContext().getBean(SystemService.class);
        List<Map<String, Object>> list = systemService.findForJdbc(sql, new Object[0]);
        return list;
    }

    private StringBuffer datatype(StringBuffer sb) {
        if(!StringUtils.isBlank(this.datatype)) {
            sb.append(" datatype=\"" + this.datatype + "\"");
        }

        return sb;
    }

    private StringBuffer readonly(StringBuffer sb) {
        if(!StringUtils.isBlank(this.readonly) && this.readonly.equals("readonly")) {
            if("radio".equals(this.type)) {
                sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
            } else if("checkbox".equals(this.type)) {
                sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
            } else if(!"text".equals(this.type)) {
                sb.append(" disable= \"disabled\" disabled=\"disabled\" ");
            }
        }

        return sb;
    }

    public String getTypeGroupCode() {
        return this.typeGroupCode;
    }

    public void setTypeGroupCode(String typeGroupCode) {
        this.typeGroupCode = typeGroupCode;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefaultVal() {
        return this.defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getDivClass() {
        return this.divClass;
    }

    public void setDivClass(String divClass) {
        this.divClass = divClass;
    }

    public String getLabelClass() {
        return this.labelClass;
    }

    public void setLabelClass(String labelClass) {
        this.labelClass = labelClass;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasLabel() {
        return this.hasLabel;
    }

    public void setHasLabel(boolean hasLabel) {
        this.hasLabel = hasLabel;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDictTable() {
        return this.dictTable;
    }

    public void setDictTable(String dictTable) {
        this.dictTable = dictTable;
    }

    public String getDictField() {
        return this.dictField;
    }

    public void setDictField(String dictField) {
        this.dictField = dictField;
    }

    public String getDictText() {
        return this.dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText;
    }

    public String getExtendJson() {
        return this.extendJson;
    }

    public void setExtendJson(String extendJson) {
        this.extendJson = extendJson;
    }
}
