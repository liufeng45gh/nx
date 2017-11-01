/*    */ package org.jeecgframework.core.interceptors;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.springframework.web.bind.WebDataBinder;
/*    */ import org.springframework.web.bind.support.WebBindingInitializer;
/*    */ import org.springframework.web.context.request.WebRequest;
/*    */ 
/*    */ public class MyWebBinding
/*    */   implements WebBindingInitializer
/*    */ {
/*    */   public void initBinder(WebDataBinder binder, WebRequest request)
/*    */   {
/* 17 */     binder.registerCustomEditor(Date.class, new DateConvertEditor());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.interceptors.MyWebBinding
 * JD-Core Version:    0.6.2
 */