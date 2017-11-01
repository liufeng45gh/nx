/*    */ package org.jeecgframework.web.sms.service.impl;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.annotation.Resource;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.sms.entity.TSSmsSqlEntity;
/*    */ import org.jeecgframework.web.sms.service.TSSmsSqlServiceI;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("tSSmsSqlService")
/*    */ @Transactional
/*    */ public class TSSmsSqlServiceImpl extends CommonServiceImpl
/*    */   implements TSSmsSqlServiceI
/*    */ {
/*    */ 
/*    */   @Resource
/*    */   private JdbcTemplate jdbcTemplate;
/*    */ 
/*    */   public boolean doAddSql(TSSmsSqlEntity t)
/*    */   {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean doDelSql(TSSmsSqlEntity t)
/*    */   {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean doUpdateSql(TSSmsSqlEntity t)
/*    */   {
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getMap(String sql, Map<String, Object> map)
/*    */   {
/* 42 */     return this.jdbcTemplate.queryForMap(sql, new Object[] { map });
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.service.impl.TSSmsSqlServiceImpl
 * JD-Core Version:    0.6.2
 */