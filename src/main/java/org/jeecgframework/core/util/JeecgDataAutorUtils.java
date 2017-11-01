/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.jeecgframework.web.system.pojo.base.TSDataRule;
/*    */ import org.springframework.util.StringUtils;
/*    */ 
/*    */ public class JeecgDataAutorUtils
/*    */ {
/*    */   public static synchronized void installDataSearchConditon(HttpServletRequest request, List<TSDataRule> MENU_DATA_AUTHOR_RULES)
/*    */   {
/* 30 */     List list = loadDataSearchConditonSQL();
/* 31 */     if (list == null) {
/* 32 */       list = new ArrayList();
/*    */     }
/* 34 */     for (TSDataRule tsDataRule : MENU_DATA_AUTHOR_RULES) {
/* 35 */       list.add(tsDataRule);
/*    */     }
/* 37 */     request.setAttribute("MENU_DATA_AUTHOR_RULES", list);
/*    */   }
/*    */ 
/*    */   public static synchronized List<TSDataRule> loadDataSearchConditonSQL()
/*    */   {
/* 48 */     return (List)ContextHolderUtils.getRequest().getAttribute(
/* 49 */       "MENU_DATA_AUTHOR_RULES");
/*    */   }
/*    */ 
/*    */   public static synchronized String loadDataSearchConditonSQLString()
/*    */   {
/* 59 */     return (String)ContextHolderUtils.getRequest().getAttribute(
/* 60 */       "MENU_DATA_AUTHOR_RULE_SQL");
/*    */   }
/*    */ 
/*    */   public static synchronized void installDataSearchConditon(HttpServletRequest request, String MENU_DATA_AUTHOR_RULE_SQL)
/*    */   {
/* 72 */     String ruleSql = loadDataSearchConditonSQLString();
/* 73 */     if (!StringUtils.hasText(ruleSql)) {
/* 74 */       ruleSql = ruleSql + MENU_DATA_AUTHOR_RULE_SQL;
/*    */     }
/* 76 */     request.setAttribute("MENU_DATA_AUTHOR_RULE_SQL", 
/* 77 */       MENU_DATA_AUTHOR_RULE_SQL);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.JeecgDataAutorUtils
 * JD-Core Version:    0.6.2
 */