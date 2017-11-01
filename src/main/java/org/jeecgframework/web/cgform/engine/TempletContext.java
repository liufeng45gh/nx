/*     */ package org.jeecgframework.web.cgform.engine;
/*     */ 
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import freemarker.template.TemplateDirectiveModel;
/*     */ import freemarker.template.TemplateModel;
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.annotation.Resource;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import org.jeecgframework.core.util.PropertiesUtil;
/*     */ import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("templetContext")
/*     */ public class TempletContext
/*     */ {
/*     */ 
/*     */   @Resource(name="freemarker")
/*     */   private Configuration freemarker;
/*     */ 
/*     */   @Autowired
/*     */   private CgFormFieldServiceI cgFormFieldService;
/*     */   private Map<String, TemplateDirectiveModel> tags;
/*     */   private static final String ENCODING = "UTF-8";
/*     */   private static Cache ehCache;
/*  40 */   private static String _sysMode = null;
/*     */ 
/*  42 */   static { PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
/*  43 */     _sysMode = util.readProperty("sqlReadMode");
/*  44 */     if ("PUB".equalsIgnoreCase(_sysMode))
/*  45 */       ehCache = CacheManager.getInstance().getCache("dictCache");
/*     */   }
/*     */ 
/*     */   @PostConstruct
/*     */   public void init()
/*     */   {
/*  51 */     if (this.tags == null)
/*  52 */       return;
/*  53 */     for (String key : this.tags.keySet())
/*  54 */       this.freemarker.setSharedVariable(key, (TemplateModel)this.tags.get(key));
/*     */   }
/*     */ 
/*     */   public Locale getLocale()
/*     */   {
/*  59 */     return this.freemarker.getLocale();
/*     */   }
/*     */ 
/*     */   public Template getTemplate(String tableName, String ftlVersion) {
/*  63 */     Template template = null;
/*  64 */     if (tableName == null) {
/*  65 */       return null;
/*     */     }
/*  67 */     String oldTableName = tableName;
/*     */ 
/*  69 */     if ((ftlVersion != null) && (ftlVersion.length() > 0)) {
/*  70 */       tableName = tableName + "&ftlVersion=" + ftlVersion;
/*     */     }
/*     */     try
/*     */     {
/*  74 */       if ("DEV".equalsIgnoreCase(_sysMode)) {
/*  75 */         template = this.freemarker.getTemplate(tableName, this.freemarker.getLocale(), "UTF-8");
/*  76 */       } else if ("PUB".equalsIgnoreCase(_sysMode))
/*     */       {
/*  78 */         String version = this.cgFormFieldService.getCgFormVersionByTableName(oldTableName);
/*  79 */         template = getTemplateFromCache(tableName, "UTF-8", version);
/*     */       } else {
/*  81 */         throw new RuntimeException("sysConfig.properties的freeMarkerMode配置错误：(PUB:生产模式，DEV:开发模式)");
/*     */       }
/*  83 */       return template;
/*     */     } catch (IOException e) {
/*  85 */       e.printStackTrace();
/*  86 */     }return null;
/*     */   }
/*     */ 
/*     */   public Template getTemplateFromCache(String tableName, String encoding, String version)
/*     */   {
/*  98 */     Template template = null;
/*     */     try
/*     */     {
/* 101 */       String cacheKey = FreemarkerHelper.class.getName() + ".getTemplateFormCache." + tableName + "." + version;
/* 102 */       Element element = ehCache.get(cacheKey);
/* 103 */       if (element == null) {
/* 104 */         template = this.freemarker.getTemplate(tableName, this.freemarker.getLocale(), "UTF-8");
/* 105 */         element = new Element(cacheKey, template);
/* 106 */         ehCache.put(element);
/*     */       } else {
/* 108 */         template = (Template)element.getObjectValue();
/*     */       }
/*     */     } catch (IOException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/* 113 */     return template;
/*     */   }
/*     */ 
/*     */   public Configuration getFreemarker() {
/* 117 */     return this.freemarker;
/*     */   }
/*     */ 
/*     */   public void setFreemarker(Configuration freemarker) {
/* 121 */     this.freemarker = freemarker;
/*     */   }
/*     */ 
/*     */   public Map<String, TemplateDirectiveModel> getTags() {
/* 125 */     return this.tags;
/*     */   }
/*     */ 
/*     */   public void setTags(Map<String, TemplateDirectiveModel> tags) {
/* 129 */     this.tags = tags;
/*     */   }
/*     */ 
/*     */   public void clearCache() {
/*     */     try {
/* 134 */       ehCache.removeAll();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.engine.TempletContext
 * JD-Core Version:    0.6.2
 */