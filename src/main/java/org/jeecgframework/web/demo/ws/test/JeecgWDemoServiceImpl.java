/*    */ package org.jeecgframework.web.demo.ws.test;
/*    */ 
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ 
/*    */ public class JeecgWDemoServiceImpl
/*    */   implements JeecgWServiceI
/*    */ {
/*    */   public String say(String context)
/*    */   {
/* 10 */     return "you say context demo:" + context;
/*    */   }
/*    */ 
/*    */   public String sayHello()
/*    */   {
/* 16 */     return "sayHello demo";
/*    */   }
/*    */ 
/*    */   public void sayUI()
/*    */   {
/* 22 */     LogUtil.info("UI demo");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.ws.test.JeecgWDemoServiceImpl
 * JD-Core Version:    0.6.2
 */