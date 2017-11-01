/*    */ package org.jeecgframework.web.system.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ import org.jeecgframework.core.util.YouBianCodeUtil;
/*    */ import org.jeecgframework.core.util.oConvertUtils;
/*    */ import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
/*    */ import org.jeecgframework.web.system.service.CategoryServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("tSCategoryService")
/*    */ @Transactional
/*    */ public class CategoryServiceImpl extends CommonServiceImpl
/*    */   implements CategoryServiceI
/*    */ {
/*    */   public void saveCategory(TSCategoryEntity category)
/*    */   {
/* 26 */     String parentCode = null;
/* 27 */     if ((category.getParent() != null) && (oConvertUtils.isNotEmpty(category.getParent().getCode()))) {
/* 28 */       parentCode = category.getParent().getCode();
/* 29 */       String localMaxCode = getMaxLocalCode(parentCode);
/* 30 */       category.setCode(YouBianCodeUtil.getSubYouBianCode(parentCode, localMaxCode));
/*    */     } else {
/* 32 */       String localMaxCode = getMaxLocalCode(null);
/* 33 */       category.setParent(null);
/* 34 */       category.setCode(YouBianCodeUtil.getNextYouBianCode(localMaxCode));
/*    */     }
/*    */ 
/* 37 */     save(category);
/*    */   }
/*    */ 
/*    */   private synchronized String getMaxLocalCode(String parentCode) {
/* 41 */     if (oConvertUtils.isEmpty(parentCode)) {
/* 42 */       parentCode = "";
/*    */     }
/* 44 */     int localCodeLength = parentCode.length() + 3;
/* 45 */     StringBuilder sb = new StringBuilder();
/*    */ 
/* 47 */     if (ResourceUtil.getJdbcUrl().indexOf("sqlserver") != -1) {
/* 48 */       sb.append("SELECT code FROM t_s_category");
/* 49 */       sb.append(" where LEN(code) = ").append(localCodeLength);
/*    */     } else {
/* 51 */       sb.append("SELECT code FROM t_s_category");
/* 52 */       sb.append(" where LENGTH(code) = ").append(localCodeLength);
/*    */     }
/*    */ 
/* 55 */     if (oConvertUtils.isNotEmpty(parentCode)) {
/* 56 */       sb.append(" and  code like '").append(parentCode).append("%'");
/*    */     }
/*    */ 
/* 59 */     sb.append(" ORDER BY code DESC ");
/* 60 */     List objMapList = findForJdbc(sb.toString(), 1, 1);
/* 61 */     String returnCode = null;
/* 62 */     if ((objMapList != null) && (objMapList.size() > 0)) {
/* 63 */       returnCode = (String)((Map)objMapList.get(0)).get("code");
/*    */     }
/*    */ 
/* 66 */     return returnCode;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.CategoryServiceImpl
 * JD-Core Version:    0.6.2
 */