/*    */ package org.jeecgframework.web.cgform.util;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.jeecgframework.core.util.ReflectHelper;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*    */ 
/*    */ public class PublicUtil
/*    */ {
/*    */   public static void setCommonForTable(Object obj, boolean isCreate)
/*    */   {
/* 21 */     ReflectHelper reflectHelper = new ReflectHelper(obj);
/* 22 */     if (isCreate) {
/* 23 */       reflectHelper.setMethodValue("createDate", new Date());
/* 24 */       reflectHelper.setMethodValue("createBy", ResourceUtil.getSessionUserName().getId());
/* 25 */       reflectHelper.setMethodValue("createName", ResourceUtil.getSessionUserName().getUserName());
/*    */     }
/* 27 */     reflectHelper.setMethodValue("updateDate", new Date());
/* 28 */     reflectHelper.setMethodValue("updateBy", ResourceUtil.getSessionUserName().getId());
/* 29 */     reflectHelper.setMethodValue("updateName", ResourceUtil.getSessionUserName().getUserName());
/*    */   }
/*    */ 
/*    */   public static void judgeCheckboxValue(Object obj, String params)
/*    */   {
/* 37 */     ReflectHelper reflectHelper = new ReflectHelper(obj);
/* 38 */     String[] paramsArr = params.split(",");
/* 39 */     for (int i = 0; i < paramsArr.length; i++) {
/* 40 */       String checked = "N";
/* 41 */       if ((reflectHelper.getMethodValue(paramsArr[i]) != null) && 
/* 42 */         (!"N".equalsIgnoreCase((String)reflectHelper.getMethodValue(paramsArr[i])))) {
/* 43 */         checked = "Y";
/*    */       }
/* 45 */       reflectHelper.setMethodValue(paramsArr[i], checked);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static boolean compareValue(Object oldvalue, Object newvalue)
/*    */   {
/* 56 */     if (oldvalue == null) {
/* 57 */       if (newvalue != null)
/* 58 */         return false;
/*    */     }
/*    */     else {
/* 61 */       if (newvalue == null) {
/* 62 */         return false;
/*    */       }
/* 64 */       if (!oldvalue.equals(newvalue)) {
/* 65 */         return false;
/*    */       }
/*    */     }
/*    */ 
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   public static String getTableName(String s)
/*    */   {
/* 78 */     s = s.substring(s.indexOf("from") + 4);
/* 79 */     if (s.indexOf("where") > 1) {
/* 80 */       s = s.substring(0, s.indexOf("where"));
/*    */     }
/* 82 */     return s.trim();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.util.PublicUtil
 * JD-Core Version:    0.6.2
 */