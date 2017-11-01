/*    */ package org.jeecgframework.web.demo.ws.test;
/*    */ 
/*    */ import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
/*    */ 
/*    */ public class JeecgWServiceClient
/*    */ {
/*    */   public static void main11(String[] args)
/*    */   {
/* 16 */     JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
/* 17 */     bean.setServiceClass(JeecgWServiceI.class);
/* 18 */     bean.setAddress("http://localhost:8080/jeecg/cxf/JeecgWService");
/* 19 */     JeecgWServiceI client = (JeecgWServiceI)bean.create();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 24 */     JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
/* 25 */     bean.setServiceClass(JeecgWServiceI.class);
/* 26 */     bean.setAddress("http://localhost:8080/jeecg/cxf/JeecgWDemoService");
/* 27 */     JeecgWServiceI client = (JeecgWServiceI)bean.create();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.ws.test.JeecgWServiceClient
 * JD-Core Version:    0.6.2
 */