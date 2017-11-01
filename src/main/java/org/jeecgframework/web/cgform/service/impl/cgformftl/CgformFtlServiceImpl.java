/*    */ package org.jeecgframework.web.cgform.service.impl.cgformftl;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.cgform.entity.cgformftl.CgformFtlEntity;
/*    */ import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgformFtlService")
/*    */ @Transactional
/*    */ public class CgformFtlServiceImpl extends CommonServiceImpl
/*    */   implements CgformFtlServiceI
/*    */ {
/*    */   public Map<String, Object> getCgformFtlByTableName(String tableName, String ftlVersion)
/*    */   {
/* 19 */     StringBuilder sql = new StringBuilder("");
/* 20 */     sql.append("select ftl.* from cgform_ftl ftl,cgform_head head");
/* 21 */     sql.append(" where ftl.cgform_id=head.id");
/* 22 */     sql.append(" and ftl.ftl_version=? ");
/* 23 */     sql.append(" and head.table_name=? ");
/* 24 */     List list = findForJdbc(sql.toString(), new Object[] { ftlVersion, tableName });
/* 25 */     if ((list != null) && (list.size() > 0)) {
/* 26 */       return (Map)list.get(0);
/*    */     }
/* 28 */     return null;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getCgformFtlByTableName(String tableName)
/*    */   {
/* 37 */     StringBuilder sql = new StringBuilder("");
/* 38 */     sql.append("select ftl.* from cgform_ftl ftl,cgform_head head");
/* 39 */     sql.append(" where ftl.cgform_id=head.id");
/* 40 */     sql.append(" and ftl.ftl_status='1'");
/* 41 */     sql.append(" and head.table_name=? ");
/* 42 */     List list = findForJdbc(sql.toString(), new Object[] { tableName });
/* 43 */     if ((list != null) && (list.size() > 0)) {
/* 44 */       return (Map)list.get(0);
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */   public int getNextVarsion(String cgformId)
/*    */   {
/* 51 */     StringBuilder sql = new StringBuilder("");
/* 52 */     sql.append("select (max(ftl_version)+1) as varsion from cgform_ftl");
/* 53 */     sql.append(" where cgform_id = ? ");
/* 54 */     Map map = findOneForJdbc(sql.toString(), new Object[] { cgformId });
/* 55 */     if (map != null) {
/* 56 */       int varsion = map.get("varsion") == null ? 1 : Integer.valueOf(map.get("varsion").toString()).intValue();
/* 57 */       return varsion;
/*    */     }
/* 59 */     return 1;
/*    */   }
/*    */ 
/*    */   public boolean hasActive(String cgformId)
/*    */   {
/* 64 */     StringBuilder sql = new StringBuilder("");
/* 65 */     sql.append("select * from cgform_ftl");
/* 66 */     sql.append(" where ftl_status = '1' ");
/* 67 */     sql.append(" and cgform_id = ? ");
/* 68 */     Map map = findOneForJdbc(sql.toString(), new Object[] { cgformId });
/* 69 */     if (map != null) {
/* 70 */       return true;
/*    */     }
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   public String getUserFormFtl(String id)
/*    */   {
/* 77 */     CriteriaQuery cq = new CriteriaQuery(CgformFtlEntity.class);
/* 78 */     cq.eq("cgformId", id);
/* 79 */     cq.eq("ftlStatus", "1");
/* 80 */     cq.add();
/* 81 */     List list = getListByCriteriaQuery(cq, Boolean.valueOf(false));
/* 82 */     if (list.size() == 1) {
/* 83 */       return ((CgformFtlEntity)list.get(0)).getFtlContent();
/*    */     }
/* 85 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.cgformftl.CgformFtlServiceImpl
 * JD-Core Version:    0.6.2
 */