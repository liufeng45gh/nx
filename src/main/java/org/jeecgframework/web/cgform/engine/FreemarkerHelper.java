/*    */ package org.jeecgframework.web.cgform.engine;
/*    */ 
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.Template;
/*    */ import java.io.StringWriter;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FreemarkerHelper
/*    */ {
/* 17 */   private static Configuration _tplConfig = new Configuration();
/*    */ 
/* 19 */   static { _tplConfig.setClassForTemplateLoading(FreemarkerHelper.class, "/");
/* 20 */     _tplConfig.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
/* 21 */     _tplConfig.setDateFormat("yyyy-MM-dd");
/* 22 */     _tplConfig.setTimeFormat("HH:mm:ss");
/*    */   }
/*    */ 
/*    */   public String parseTemplate(String tplName, String encoding, Map<String, Object> paras)
/*    */   {
/*    */     try
/*    */     {
/* 35 */       StringWriter swriter = new StringWriter();
/* 36 */       Template mytpl = null;
/* 37 */       mytpl = _tplConfig.getTemplate(tplName, encoding);
/* 38 */       mytpl.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
/* 39 */       mytpl.setDateFormat("yyyy-MM-dd");
/* 40 */       mytpl.setTimeFormat("HH:mm:ss");
/* 41 */       mytpl.process(paras, swriter);
/* 42 */       return swriter.toString();
/*    */     } catch (Exception e) {
/* 44 */       e.printStackTrace();
/* 45 */       return e.toString();
/*    */     }
/*    */   }
/*    */ 
/*    */   public String parseTemplate(String tplName, Map<String, Object> paras) {
/* 50 */     return parseTemplate(tplName, "utf-8", paras);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.engine.FreemarkerHelper
 * JD-Core Version:    0.6.2
 */