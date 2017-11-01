/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderCustomEntity;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderMainEntity;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgOrderProductEntity;
/*    */ import org.jeecgframework.web.demo.service.test.JeecgOrderMainServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("jeecgOrderMainService")
/*    */ @Transactional
/*    */ public class JeecgOrderMainServiceImpl extends CommonServiceImpl
/*    */   implements JeecgOrderMainServiceI
/*    */ {
/*    */   public void addMain(JeecgOrderMainEntity jeecgOrderMain, List<JeecgOrderProductEntity> jeecgOrderProducList, List<JeecgOrderCustomEntity> jeecgOrderCustomList)
/*    */   {
/* 23 */     save(jeecgOrderMain);
/*    */ 
/* 25 */     for (JeecgOrderProductEntity product : jeecgOrderProducList)
/*    */     {
/* 27 */       product.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
/* 28 */       save(product);
/*    */     }
/*    */ 
/* 31 */     for (JeecgOrderCustomEntity custom : jeecgOrderCustomList)
/*    */     {
/* 33 */       custom.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
/* 34 */       save(custom);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void updateMain(JeecgOrderMainEntity jeecgOrderMain, List<JeecgOrderProductEntity> jeecgOrderProducList, List<JeecgOrderCustomEntity> jeecgOrderCustomList, boolean jeecgOrderCustomShow)
/*    */   {
/* 44 */     saveOrUpdate(jeecgOrderMain);
/*    */ 
/* 46 */     this.commonDao.deleteAllEntitie(findByProperty(JeecgOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
/*    */ 
/* 48 */     for (JeecgOrderProductEntity product : jeecgOrderProducList)
/*    */     {
/* 50 */       product.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
/* 51 */       save(product);
/*    */     }
/* 53 */     if (jeecgOrderCustomShow)
/*    */     {
/* 55 */       this.commonDao.deleteAllEntitie(findByProperty(JeecgOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
/*    */ 
/* 57 */       for (JeecgOrderCustomEntity custom : jeecgOrderCustomList)
/*    */       {
/* 59 */         custom.setGoOrderCode(jeecgOrderMain.getGoOrderCode());
/* 60 */         save(custom);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void delMain(JeecgOrderMainEntity jeecgOrderMain)
/*    */   {
/* 68 */     delete(jeecgOrderMain);
/*    */ 
/* 70 */     deleteAllEntitie(findByProperty(JeecgOrderProductEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
/*    */ 
/* 72 */     this.commonDao.deleteAllEntitie(findByProperty(JeecgOrderCustomEntity.class, "goOrderCode", jeecgOrderMain.getGoOrderCode()));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.JeecgOrderMainServiceImpl
 * JD-Core Version:    0.6.2
 */