/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.web.demo.dao.test.JeecgProcedureDao;
/*    */ import org.jeecgframework.web.demo.service.test.JeecgProcedureServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("jeecgProcedureServiceImpl")
/*    */ @Transactional
/*    */ public class JeecgProcedureServiceImpl extends JdbcDaoSupport
/*    */   implements JeecgProcedureServiceI
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JeecgProcedureDao jeecgProcedureDao;
/*    */ 
/*    */   public List queryDataByProcedure(String tableName, String fields, String whereSql)
/*    */   {
/* 23 */     String sql = "call formDataList('" + tableName + "','" + fields + "','" + whereSql + "')";
/* 24 */     return getJdbcTemplate().queryForList(sql);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.JeecgProcedureServiceImpl
 * JD-Core Version:    0.6.2
 */