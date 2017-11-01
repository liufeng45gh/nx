/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.web.demo.dao.test.JeecgMinidaoDao;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgMinidaoEntity;
/*    */ import org.jeecgframework.web.demo.service.test.JeecgMinidaoServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("jeecgMinidaoService")
/*    */ @Transactional
/*    */ public class JeecgMinidaoServiceImpl
/*    */   implements JeecgMinidaoServiceI
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JeecgMinidaoDao jeecgMinidaoDao;
/*    */ 
/*    */   public List<JeecgMinidaoEntity> listAll(JeecgMinidaoEntity jeecgMinidao, int page, int rows)
/*    */   {
/* 23 */     List entities = this.jeecgMinidaoDao.getAllEntities2(jeecgMinidao, page, rows);
/* 24 */     return entities;
/*    */   }
/*    */ 
/*    */   public Integer getCount()
/*    */   {
/* 52 */     return this.jeecgMinidaoDao.getCount();
/*    */   }
/*    */ 
/*    */   public Integer getSumSalary() {
/* 56 */     return this.jeecgMinidaoDao.getSumSalary();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.JeecgMinidaoServiceImpl
 * JD-Core Version:    0.6.2
 */