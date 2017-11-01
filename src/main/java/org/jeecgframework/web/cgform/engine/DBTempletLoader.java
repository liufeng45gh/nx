/*     */ package org.jeecgframework.web.cgform.engine;
/*     */ 
/*     */ import freemarker.cache.TemplateLoader;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.jeecgframework.core.util.LogUtil;
/*     */ import org.jeecgframework.web.cgform.common.FormHtmlUtil;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
/*     */ import org.jeecgframework.web.cgform.entity.template.CgformTemplateEntity;
/*     */ import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
/*     */ import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
/*     */ import org.jeecgframework.web.cgform.service.template.CgformTemplateServiceI;
/*     */ import org.jeecgframework.web.cgform.util.TemplateUtil;
/*     */ import org.jeecgframework.web.cgform.util.TemplateUtil.TemplateType;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("templetLoader")
/*     */ public class DBTempletLoader
/*     */   implements TemplateLoader
/*     */ {
/*     */   public static final String TEMPLET = "org/jeecgframework/web/cgform/engine/jform.ftl";
/*     */   public static final String TEMPLET_ONE_MANY = "org/jeecgframework/web/cgform/engine/jformunion.ftl";
/*     */   private static final String regEx_attr = "\\#\\{([a-zA-Z_0-9]+)\\}";
/*     */ 
/*     */   @Autowired
/*     */   private CgformFtlServiceI cgformFtlService;
/*     */ 
/*     */   @Autowired
/*     */   private CgFormFieldServiceI cgFormFieldService;
/*     */ 
/*     */   @Autowired
/*     */   private CgformTemplateServiceI cgformTemplateService;
/*     */ 
/*     */   public Object findTemplateSource(String name)
/*     */     throws IOException
/*     */   {
/*  50 */     name = name.replace("_zh_cn", "").replace("_ZH_CN", "").replace("_zh_CN", "");
/*  51 */     name = name.replace("_en_us", "").replace("_EN_US", "").replace("_en_US", "");
/*     */ 
/*  53 */     LogUtil.debug("table name----------->" + name);
/*  54 */     Object obj = getObject(name);
/*  55 */     return obj;
/*     */   }
/*     */ 
/*     */   public long getLastModified(Object templateSource)
/*     */   {
/*  60 */     return 0L;
/*     */   }
/*     */ 
/*     */   public Reader getReader(Object templateSource, String encoding) throws IOException
/*     */   {
/*  65 */     Reader br = new StringReader("");
/*  66 */     if ((templateSource instanceof InputStreamReader)) {
/*  67 */       br = new BufferedReader((InputStreamReader)templateSource);
/*     */     } else {
/*  69 */       StringBuilder str = (StringBuilder)templateSource;
/*  70 */       br = new StringReader(str.toString());
/*     */     }
/*  72 */     return br;
/*     */   }
/*     */ 
/*     */   private Object getObject(String name) throws IOException
/*     */   {
/*  77 */     String ftlVersion = "";
/*  78 */     String ftlVersionParam = "&ftlVersion=";
/*  79 */     if (name.contains(ftlVersionParam)) {
/*  80 */       ftlVersion = name.substring(name.indexOf(ftlVersionParam) + ftlVersionParam.length());
/*  81 */       name = name.substring(0, name.indexOf(ftlVersionParam));
/*     */     }
/*     */ 
/*  84 */     TemplateUtil.TemplateType templateType = null;
/*  85 */     if ((name.lastIndexOf(".ftl") == -1) && (name.lastIndexOf("_") != -1)) {
/*  86 */       templateType = TemplateUtil.TemplateType.getVal(name.substring(name.lastIndexOf("_") + 1));
/*  87 */       name = name.substring(0, name.lastIndexOf("_"));
/*     */     }
/*  89 */     if (templateType == null) {
/*  90 */       templateType = TemplateUtil.TemplateType.UPDATE;
/*     */     }
/*     */ 
/*  94 */     PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
/*  95 */     if (name.lastIndexOf(".ftl") == -1)
/*     */     {
/*  97 */       CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(name);
/*  98 */       if (head == null) return null;
/*     */ 
/* 100 */       CgformTemplateEntity entity = this.cgformTemplateService.findByCode(head.getFormTemplate());
/*     */ 
/* 102 */       if (head.getJformType().intValue() == 2)
/*     */       {
/* 104 */         Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
/*     */ 
/* 106 */         InputStreamReader inputStreamReader = null;
/* 107 */         if ((resources != null) && (resources.length > 0)) {
/* 108 */           inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
/*     */         }
/* 110 */         return inputStreamReader;
/*     */       }
/*     */ 
/* 115 */       Map cgformFtlEntity = new HashMap();
/* 116 */       if ((ftlVersion != null) && (ftlVersion.length() > 0))
/* 117 */         cgformFtlEntity = this.cgformFtlService.getCgformFtlByTableName(name, ftlVersion);
/*     */       else {
/* 119 */         cgformFtlEntity = this.cgformFtlService.getCgformFtlByTableName(name);
/*     */       }
/*     */ 
/* 122 */       if (cgformFtlEntity != null) {
/* 123 */         String content = (String)(cgformFtlEntity.get("ftl_content") == null ? "" : cgformFtlEntity.get("ftl_content"));
/* 124 */         content = initFormHtml(content, name);
/*     */ 
/* 126 */         return new StringBuilder(content);
/*     */       }
/*     */ 
/* 129 */       Resource[] resources = patternResolver.getResources(TemplateUtil.getTempletPath(entity, head.getJformType(), templateType));
/*     */ 
/* 131 */       InputStreamReader inputStreamReader = null;
/* 132 */       if ((resources != null) && (resources.length > 0)) {
/* 133 */         inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
/*     */       }
/* 135 */       return inputStreamReader;
/*     */     }
/*     */ 
/* 138 */     Resource[] resources = patternResolver.getResources(name);
/* 139 */     InputStreamReader inputStreamReader = null;
/* 140 */     if ((resources != null) && (resources.length > 0)) {
/* 141 */       inputStreamReader = new InputStreamReader(resources[0].getInputStream(), "UTF-8");
/*     */     }
/* 143 */     return inputStreamReader;
/*     */   }
/*     */ 
/*     */   public void closeTemplateSource(Object templateSource)
/*     */     throws IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   private String initFormHtml(String htmlStr, String tableName)
/*     */   {
/*     */     try
/*     */     {
/* 181 */       Map fieldMap = this.cgFormFieldService.getAllCgFormFieldByTableName(tableName);
/*     */ 
/* 183 */       List hiddenFielList = this.cgFormFieldService.getHiddenCgFormFieldByTableName(tableName);
/*     */ 
/* 186 */       Pattern pattern = Pattern.compile("\\#\\{([a-zA-Z_0-9]+)\\}", 2);
/* 187 */       Matcher matcher = pattern.matcher(htmlStr);
/*     */ 
/* 189 */       StringBuffer sb = new StringBuffer();
/* 190 */       String thStr = "";
/* 191 */       String inputStr = "";
/*     */ 
/* 193 */       boolean result = matcher.find();
/* 194 */       while (result) {
/* 195 */         thStr = matcher.group(1);
/* 196 */         inputStr = "";
/* 197 */         if ("jform_hidden_field".equals(thStr)) {
/* 198 */           inputStr = getHiddenForm(hiddenFielList);
/*     */         }
/* 200 */         else if (fieldMap.get(thStr) != null) {
/* 201 */           CgFormFieldEntity cgFormFieldEntity = (CgFormFieldEntity)fieldMap.get(thStr);
/* 202 */           if ("Y".equals(cgFormFieldEntity.getIsShow())) {
/* 203 */             inputStr = FormHtmlUtil.getFormHTML(cgFormFieldEntity);
/* 204 */             inputStr = inputStr + "<span class=\"Validform_checktip\">&nbsp;</span>";
/*     */           }
/*     */         }
/*     */ 
/* 208 */         matcher.appendReplacement(sb, inputStr);
/* 209 */         result = matcher.find();
/*     */       }
/* 211 */       matcher.appendTail(sb);
/* 212 */       htmlStr = sb.toString();
/*     */     } catch (Exception e) {
/* 214 */       e.printStackTrace();
/*     */     }
/* 216 */     return htmlStr;
/*     */   }
/*     */ 
/*     */   private String getHiddenForm(List<CgFormFieldEntity> hiddenFielList) {
/* 220 */     StringBuffer html = new StringBuffer("");
/* 221 */     if ((hiddenFielList != null) && (hiddenFielList.size() > 0)) {
/* 222 */       for (CgFormFieldEntity cgFormFieldEntity : hiddenFielList) {
/* 223 */         html.append("<input type=\"hidden\" ");
/* 224 */         html.append("id=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 225 */         html.append("name=\"").append(cgFormFieldEntity.getFieldName()).append("\" ");
/* 226 */         html.append("value=\"\\${").append(cgFormFieldEntity.getFieldName()).append("?if_exists?html}\" ");
/* 227 */         html.append("\\/>\r\n");
/*     */       }
/*     */     }
/* 230 */     return html.toString();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.engine.DBTempletLoader
 * JD-Core Version:    0.6.2
 */