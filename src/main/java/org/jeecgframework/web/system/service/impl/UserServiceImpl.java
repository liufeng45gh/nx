/*    */ package org.jeecgframework.web.system.service.impl;
/*    */ 
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*    */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*    */ import org.jeecgframework.web.system.service.UserService;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("userService")
/*    */ @Transactional
/*    */ public class UserServiceImpl extends CommonServiceImpl
/*    */   implements UserService
/*    */ {
/*    */   public TSUser checkUserExits(TSUser user)
/*    */   {
/* 24 */     return this.commonDao.getUserByUserIdAndUserNameExits(user);
/*    */   }
/*    */   public String getUserRole(TSUser user) {
/* 27 */     return this.commonDao.getUserRole(user);
/*    */   }
/*    */ 
/*    */   public void pwdInit(TSUser user, String newPwd) {
/* 31 */     this.commonDao.pwdInit(user, newPwd);
/*    */   }
/*    */ 
/*    */   public int getUsersOfThisRole(String id) {
/* 35 */     Criteria criteria = getSession().createCriteria(TSRoleUser.class);
/* 36 */     criteria.add(Restrictions.eq("TSRole.id", id));
/* 37 */     int allCounts = ((Long)criteria.setProjection(
/* 38 */       Projections.rowCount()).uniqueResult()).intValue();
/* 39 */     return allCounts;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.UserServiceImpl
 * JD-Core Version:    0.6.2
 */