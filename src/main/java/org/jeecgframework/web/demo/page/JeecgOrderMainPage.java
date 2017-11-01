/*    */ package org.jeecgframework.web.demo.page;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity;
/*    */ 
/*    */ public class JeecgOrderMainPage
/*    */   implements Serializable
/*    */ {
/* 20 */   private List<JeecgOrderCustomEntity> jeecgOrderCustomList = new ArrayList();
/*    */ 
/* 28 */   private List<JeecgOrderProductEntity> jeecgOrderProductList = new ArrayList();
/*    */ 
/*    */   public List<JeecgOrderCustomEntity> getJeecgOrderCustomList()
/*    */   {
/* 22 */     return this.jeecgOrderCustomList;
/*    */   }
/*    */   public void setJeecgOrderCustomList(List<JeecgOrderCustomEntity> jeecgOrderCustomList) {
/* 25 */     this.jeecgOrderCustomList = jeecgOrderCustomList;
/*    */   }
/*    */ 
/*    */   public List<JeecgOrderProductEntity> getJeecgOrderProductList()
/*    */   {
/* 30 */     return this.jeecgOrderProductList;
/*    */   }
/*    */   public void setJeecgOrderProductList(List<JeecgOrderProductEntity> jeecgOrderProductList) {
/* 33 */     this.jeecgOrderProductList = jeecgOrderProductList;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.page.JeecgOrderMainPage
 * JD-Core Version:    0.6.2
 */