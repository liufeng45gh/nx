/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.Transaction;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.jeecgframework.web.demo.entity.test.OptimisticLockingEntity;
/*    */ import org.jeecgframework.web.demo.service.test.OptimisticLockingServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("optimisticLockingService")
/*    */ @Transactional
/*    */ public class OptimisticLockingServiceImpl extends CommonServiceImpl
/*    */   implements OptimisticLockingServiceI
/*    */ {
/*    */   public void dd()
/*    */   {
/* 16 */     Session session1 = getSession();
/* 17 */     Session session2 = getSession();
/* 18 */     OptimisticLockingEntity stu1 = (OptimisticLockingEntity)get(OptimisticLockingEntity.class, "2c91992b3f74fd05013f74fda0260001");
/* 19 */     OptimisticLockingEntity stu2 = (OptimisticLockingEntity)get(OptimisticLockingEntity.class, "2c91992b3f74fd05013f74fda0260001");
/*    */ 
/* 22 */     LogUtil.info("v1=" + stu1.getVer() + "--v2=" + stu2.getVer());
/*    */ 
/* 24 */     Transaction tx1 = session1.beginTransaction();
/* 25 */     stu1.setAccount(Integer.valueOf(200));
/* 26 */     tx1.commit();
/*    */ 
/* 28 */     LogUtil.info("v1=" + stu1.getVer() + "--v2=" + stu2.getVer());
/*    */ 
/* 30 */     Transaction tx2 = session2.beginTransaction();
/* 31 */     stu2.setAccount(Integer.valueOf(500));
/*    */ 
/* 33 */     tx2.rollback();
/* 34 */     session2.close();
/* 35 */     session1.close();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.OptimisticLockingServiceImpl
 * JD-Core Version:    0.6.2
 */