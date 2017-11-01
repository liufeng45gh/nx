/*    */ package org.jeecgframework.web.demo.ws.test;
/*    */ 
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ 
/*    */ public class JeecgWServiceImpl
/*    */   implements JeecgWServiceI
/*    */ {
/*    */   public String say(String context)
/*    */   {
/* 10 */     return "you say context:" + context;
/*    */   }
/*    */ 
/*    */   public String sayHello()
/*    */   {
/* 16 */     return "sayHello";
/*    */   }
/*    */ 
/*    */   public void sayUI()
/*    */   {
/* 22 */     LogUtil.info("UI");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.ws.test.JeecgWServiceImpl
 * JD-Core Version:    0.6.2
 */