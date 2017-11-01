//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.generate;

import freemarker.cache.TemplateLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Component("templetLoaderWord")
public class DBTempletLoaderWord implements TemplateLoader {
    public static final String TEMPLET = "org/jeecgframework/web/cgform/engine/jform.ftl";
    public static final String TEMPLET_ONE_MANY = "org/jeecgframework/web/cgform/engine/jformunion.ftl";
    private static final String regEx_attr = "\\#\\{([a-zA-Z_0-9]+)\\}";
    @Autowired
    private CgformFtlServiceI cgformFtlService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private CgformTemplateServiceI cgformTemplateService;

    public DBTempletLoaderWord() {
    }

    public Object findTemplateSource(String name) throws IOException {
        name = name.replace("_zh_cn", "").replace("_ZH_CN", "").replace("_zh_CN", "");
        name = name.replace("_en_us", "").replace("_EN_US", "").replace("_en_US", "");
        LogUtil.info("table name----------->" + name);
        Object obj = this.getObject(name);
        return obj;
    }

    public long getLastModified(Object templateSource) {
        return 0L;
    }

    public Reader getReader(Object templateSource, String encoding) throws IOException {
        new StringReader("");
        Object br;
        if(templateSource instanceof InputStreamReader) {
            br = new BufferedReader((InputStreamReader)templateSource);
        } else {
            StringBuilder str = (StringBuilder)templateSource;
            br = new StringReader(str.toString());
        }

        return (Reader)br;
    }

    private Object getObject(String name) throws IOException {
        String ftlVersion = "";
        String ftlVersionParam = "&ftlVersion=";
        if(name.contains(ftlVersionParam)) {
            ftlVersion = name.substring(name.indexOf(ftlVersionParam) + ftlVersionParam.length());
            name = name.substring(0, name.indexOf(ftlVersionParam));
        }

        TemplateType templateType = null;
        if(name.lastIndexOf(".ftl") == -1 && name.lastIndexOf("_") != -1) {
            templateType = TemplateType.getVal(name.substring(name.lastIndexOf("_") + 1));
            name = name.substring(0, name.lastIndexOf("_"));
        }

        if(templateType == null) {
            templateType = TemplateType.UPDATE;
        }

        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        if(name.lastIndexOf(".ftl") != -1) {
            Resource[] resources = patternResolver.getResources(name);
            InputStreamReader inputStreamReader = null;
            if(resources != null && resources.length > 0) {
                inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
            }

            return inputStreamReader;
        } else {
            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(name);
            if(head == null) {
                return null;
            } else {
                CgformTemplateEntity entity = this.cgformTemplateService.findByCode(head.getFormTemplate());
                if(head.getJformType().intValue() == 2) {
                    Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
                    InputStreamReader inputStreamReader = null;
                    if(resources != null && resources.length > 0) {
                        inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
                    }

                    return inputStreamReader;
                } else {
                    new HashMap();
                    Map cgformFtlEntity;
                    if(ftlVersion != null && ftlVersion.length() > 0) {
                        cgformFtlEntity = this.cgformFtlService.getCgformFtlByTableName(name, ftlVersion);
                    } else {
                        cgformFtlEntity = this.cgformFtlService.getCgformFtlByTableName(name);
                    }

                    if(cgformFtlEntity != null) {
                        String content = (String)(cgformFtlEntity.get("ftl_content") == null?"":cgformFtlEntity.get("ftl_content"));
                        content = this.initFormHtml(content, name);
                        content = content.replace("${id?if_exists?html}", "@@@{onlineCodeGenereateEntityKey.id}");
                        return new StringBuilder(content);
                    } else {
                        Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
                        InputStreamReader inputStreamReader = null;
                        if(resources != null && resources.length > 0) {
                            inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
                        }

                        return inputStreamReader;
                    }
                }
            }
        }
    }

    public void closeTemplateSource(Object templateSource) throws IOException {
    }

    private String initFormHtml(String htmlStr, String tableName) {
        try {
            Map<String, CgFormFieldEntity> fieldMap = this.cgFormFieldService.getAllCgFormFieldByTableName(tableName);
            List<CgFormFieldEntity> hiddenFielList = this.cgFormFieldService.getHiddenCgFormFieldByTableName(tableName);
            Pattern pattern = Pattern.compile("\\#\\{([a-zA-Z_0-9]+)\\}", 2);
            Matcher matcher = pattern.matcher(htmlStr);
            StringBuffer sb = new StringBuffer();
            String thStr = "";
            String inputStr = "";

            for(boolean result = matcher.find(); result; result = matcher.find()) {
                thStr = matcher.group(1);
                inputStr = "";
                if("jform_hidden_field".equals(thStr)) {
                    inputStr = this.getHiddenForm(hiddenFielList);
                } else if(fieldMap.get(thStr) != null) {
                    CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)fieldMap.get(thStr);
                    if("Y".equals(cgFormFieldEntity.getIsShow())) {
                        inputStr = FormHtmlUtilWord.getFormHTML(cgFormFieldEntity);
                        inputStr = inputStr + "<span class=\"Validform_checktip\">&nbsp;</span>";
                    }
                }

                matcher.appendReplacement(sb, inputStr);
            }

            matcher.appendTail(sb);
            htmlStr = sb.toString();
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return htmlStr;
    }

    private String getHiddenForm(List<CgFormFieldEntity> hiddenFielList) {
        StringBuffer html = new StringBuffer("");
        if(hiddenFielList != null && hiddenFielList.size() > 0) {
            Iterator var4 = hiddenFielList.iterator();

            while(var4.hasNext()) {
                CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)var4.next();
                html.append("<input type=\"hidden\" ");
                html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
                html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
                html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
                html.append("\\/>\r\n");
            }
        }

        return html.toString();
    }

    private String replaceAddJSP(String cgformJspHtml) {
        while(cgformJspHtml.indexOf("#{") > 0) {
            String key = cgformJspHtml.substring(cgformJspHtml.indexOf("#{"), cgformJspHtml.indexOf("}", cgformJspHtml.indexOf("#{")) + 1);
            String realKey = key.substring(2, key.length() - 1);
            cgformJspHtml = cgformJspHtml.replace(key, "<input id=\"" + JeecgReadTable.formatField(realKey) + "\" name=\"" + JeecgReadTable.formatField(realKey) + "\" type=\"text\" value=\"@@@{@@{entityName?uncap_first}." + JeecgReadTable.formatField(realKey) + "}\" style=\"width: 150px\" class=\"inputxt\" >");
        }

        return cgformJspHtml;
    }
}
