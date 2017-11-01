/*    */ package org.jeecgframework.web.cgform.engine.tag;
/*    */ 
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import freemarker.template.TemplateScalarModel;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.util.ApplicationContextUtil;
/*    */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component("mutiLangTag")
/*    */ public class MutiLangTag
/*    */   implements TemplateDirectiveModel
/*    */ {
/* 32 */   private static final Logger LOG = LoggerFactory.getLogger(MutiLangTag.class);
/*    */ 
/*    */   @Autowired
/*    */   private static MutiLangServiceI mutiLangService;
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 41 */     String langKey = getAttribute(params, "langKey");
/* 42 */     if (langKey == null) {
/* 43 */       throw new TemplateException(
/* 44 */         "Can not find attribute 'name' in data tag", env);
/*    */     }
/*    */ 
/* 47 */     String langArg = getAttribute(params, "langArg");
/*    */ 
/* 49 */     if (mutiLangService == null)
/*    */     {
/* 51 */       mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
/*    */     }
/*    */ 
/* 54 */     String lang_context = mutiLangService.getLang(langKey, langArg);
/*    */ 
/* 57 */     Writer out = env.getOut();
/* 58 */     out.append(lang_context);
/*    */   }
/*    */ 
/*    */   private String getAttribute(Map params, String name)
/*    */   {
/* 70 */     String value = null;
/* 71 */     if (params.containsKey(name)) {
/* 72 */       TemplateModel paramValue = (TemplateModel)params.get(name);
/*    */       try {
/* 74 */         value = ((TemplateScalarModel)paramValue).getAsString();
/*    */       } catch (TemplateModelException e) {
/* 76 */         LOG.error("get attribute '{}' error", name, e);
/*    */       }
/*    */     }
/* 79 */     return value;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.engine.tag.MutiLangTag
 * JD-Core Version:    0.6.2
 */