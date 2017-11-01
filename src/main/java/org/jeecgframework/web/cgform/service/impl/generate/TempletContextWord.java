//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.generate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("templetContextWord")
public class TempletContextWord {
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private CgformFtlServiceI cgformFtlService;
    @Resource(
            name = "freemarkerWord"
    )
    private Configuration freemarker;
    private Map<String, TemplateDirectiveModel> tags;
    private static final String ENCODING = "UTF-8";
    private static Cache ehCache;
    private static String _sysMode = null;

    static {
        PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
        _sysMode = util.readProperty("sqlReadMode");
        if("PUB".equalsIgnoreCase(_sysMode)) {
            ehCache = CacheManager.getInstance().getCache("dictCache");
        }

    }

    public TempletContextWord() {
    }

    @PostConstruct
    public void init() {
        if(this.tags != null) {
            Iterator var2 = this.tags.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                this.freemarker.setSharedVariable(key, (TemplateModel)this.tags.get(key));
            }

        }
    }

    public Locale getLocale() {
        return this.freemarker.getLocale();
    }

    public Template getTemplate(String tableName, String ftlVersion) {
        Template template = null;
        if(tableName == null) {
            return null;
        } else {
            String oldTableName = tableName;
            if(ftlVersion != null && ftlVersion.length() > 0) {
                tableName = tableName + "&ftlVersion=" + ftlVersion;
            }

            try {
                if("DEV".equalsIgnoreCase(_sysMode)) {
                    template = this.freemarker.getTemplate(tableName, this.freemarker.getLocale(), "UTF-8");
                } else {
                    if(!"PUB".equalsIgnoreCase(_sysMode)) {
                        throw new RuntimeException("sysConfig.properties的freeMarkerMode配置错误：(PUB:生产模式，DEV:开发模式)");
                    }

                    String version = this.cgFormFieldService.getCgFormVersionByTableName(oldTableName);
                    template = this.getTemplateFromCache(tableName, "UTF-8", version);
                }

                return template;
            } catch (IOException var6) {
                var6.printStackTrace();
                return null;
            }
        }
    }

    public Template getTemplateFromCache(String tableName, String encoding, String version) {
        Template template = null;

        try {
            String cacheKey = FreemarkerHelper.class.getName() + ".getTemplateFormCache." + tableName + "." + version;
            Element element = ehCache.get(cacheKey);
            if(element == null) {
                template = this.freemarker.getTemplate(tableName, this.freemarker.getLocale(), "UTF-8");
                element = new Element(cacheKey, template);
                ehCache.put(element);
            } else {
                template = (Template)element.getObjectValue();
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return template;
    }

    public Configuration getFreemarker() {
        return this.freemarker;
    }

    public void setFreemarker(Configuration freemarker) {
        this.freemarker = freemarker;
    }

    public Map<String, TemplateDirectiveModel> getTags() {
        return this.tags;
    }

    public void setTags(Map<String, TemplateDirectiveModel> tags) {
        this.tags = tags;
    }

    public String autoFormGenerateHtml(String tableName, String id, String mode) {
        String html = this.autoFormViewGenerateHtml(tableName, id, mode);
        html = html.replace("<html xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\">", "<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%><br><%@include file=\"/context/mytags.jsp\"%>");
        html = html.replace("cgFormBuildController.do?saveOrUpdate", "@@{entityName?uncap_first}Controller.do?doAdd");
        html = html.replace("@@@", "${'$'}");
        html = html.replace("@{onlineCodeGenereateEntityKey@", "${'$'}{${entityName?uncap_first}Page");
        html = html.replace("onlineCodeGenereateEntityKey", "${entityName?uncap_first}Page");
        html = html.replace("@@", "$");
        return html;
    }

    private String autoFormViewGenerateHtml(String tableName, String id, String mode) {
        new HashMap();
        String templateName = tableName + "_";
        TemplateType templateType = TemplateType.LIST;
        if(StringUtils.isBlank(id)) {
            templateName = templateName + TemplateType.ADD.getName();
            templateType = TemplateType.ADD;
        } else if("read".equals(mode)) {
            templateName = templateName + TemplateType.DETAIL.getName();
            templateType = TemplateType.DETAIL;
        } else {
            templateName = templateName + TemplateType.UPDATE.getName();
            templateType = TemplateType.UPDATE;
        }

        String version = this.cgFormFieldService.getCgFormVersionByTableName(tableName);
        Map configData = this.cgFormFieldService.getFtlFormConfig(tableName, version);
        Map<String, Object> data = new HashMap(configData);
        CgFormHeadEntity head = (CgFormHeadEntity)data.get("head");
        Map<String, Object> dataForm = new HashMap();
        if(StringUtils.isNotEmpty(id)) {
            dataForm = this.dataBaseService.findOneForJdbc(tableName, id);
        }

        Iterator it = ((Map)dataForm).entrySet().iterator();

        String subTableStr;
        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            subTableStr = (String)entry.getKey();
            Object ov = entry.getValue();
            data.put(subTableStr, ov);
        }

        Map<String, Object> tableData = new HashMap();
        tableData.put(tableName, dataForm);
        if(StringUtils.isNotEmpty(id) && head.getJformType().intValue() == 2) {
            subTableStr = head.getSubTableStr();
            if(StringUtils.isNotEmpty(subTableStr)) {
                String[] subTables = subTableStr.split(",");
                new ArrayList();
                String[] var19 = subTables;
                int var18 = subTables.length;

                for(int var17 = 0; var17 < var18; ++var17) {
                    String subTable = var19[var17];
                    List<Map<String, Object>> subTableData = this.cgFormFieldService.getSubTableData(tableName, subTable, id);
                    tableData.put(subTable, subTableData);
                }
            }
        }

        data.put("data", tableData);
        data.put("id", id);
        data.put("head", head);
        subTableStr = null;
        subTableStr = this.getTableTemplate(templateName, data);
        return subTableStr;
    }

    private String replaceAddJSP(String cgformJspHtml) {
        while(cgformJspHtml.indexOf("#{") > 0) {
            String key = cgformJspHtml.substring(cgformJspHtml.indexOf("#{"), cgformJspHtml.indexOf("}", cgformJspHtml.indexOf("#{")) + 1);
            String realKey = key.substring(2, key.length() - 1);
            cgformJspHtml = cgformJspHtml.replace(key, "<input id='" + JeecgReadTable.formatField(realKey) + "' name='" + JeecgReadTable.formatField(realKey) + "' type='text' value='${'$'}{${entityName?uncap_first}." + JeecgReadTable.formatField(realKey) + "}' style='width: 150px' class='inputxt' >");
        }

        return cgformJspHtml;
    }

    private String getTableTemplate(String templateName, Map data) {
        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        String ftlVersion = oConvertUtils.getString(data.get("version"));
        Template template = this.getTemplate(templateName, ftlVersion);

        try {
            template.process(data, writer);
        } catch (TemplateException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return stringWriter.toString();
    }
}
