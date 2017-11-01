/*    */ package org.jeecgframework.core.extend.hqlsearch;
/*    */ 
/*    */ import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ import org.jeecgframework.web.system.pojo.base.TSDataRule;
/*    */ 
/*    */ public class SysContextSqlConvert
/*    */ {
/*    */   public static String setSqlModel(TSDataRule dataRule)
/*    */   {
/* 34 */     if (dataRule == null)
/* 35 */       return "";
/* 36 */     String sqlValue = "";
/* 37 */     HqlRuleEnum ruleEnum = HqlRuleEnum.getByValue(dataRule.getRuleConditions());
/*    */ 
/* 39 */     String ValueTemp = dataRule.getRuleValue();
/* 40 */     String moshi = "";
/* 41 */     if (ValueTemp.indexOf("}") != -1) {
/* 42 */       moshi = ValueTemp.substring(ValueTemp.indexOf("}") + 1);
/*    */     }
/* 44 */     String returnValue = null;
/*    */ 
/* 46 */     if (ValueTemp.contains("#{"))
/* 47 */       ValueTemp = ValueTemp.substring(2, ValueTemp.indexOf("}"));
/*    */     else {
/* 49 */       ValueTemp = ValueTemp;
/*    */     }
/* 51 */     String tempValue = null;
/*    */ 
/* 53 */     tempValue = ResourceUtil.converRuleValue(ValueTemp);
/*    */ 
/* 56 */     if (tempValue != null)
/* 57 */       tempValue = tempValue + moshi;
/*    */     else {
/* 59 */       tempValue = ValueTemp;
/*    */     }
/* 61 */     switch (ruleEnum) {
/*    */     case EQ:
/* 63 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " >'" + tempValue + "'";
/* 64 */       break;
/*    */     case GE:
/* 66 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " >='" + tempValue + "'";
/* 67 */       break;
/*    */     case GT:
/* 69 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " <'" + tempValue + "'";
/* 70 */       break;
/*    */     case IN:
/* 72 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " <= '" + tempValue + "'";
/* 73 */       break;
/*    */     case LE:
/* 75 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " ='" + tempValue + "'";
/* 76 */       break;
/*    */     case LT:
/* 78 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " like '" + tempValue + "'";
/* 79 */       break;
/*    */     case LEFT_LIKE:
/* 81 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " !='" + tempValue + "'";
/* 82 */       break;
/*    */     case LIKE:
/* 84 */       sqlValue = sqlValue + " and " + dataRule.getRuleColumn() + " IN('" + tempValue + "')";
/*    */     }
/*    */ 
/* 90 */     return sqlValue;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.hqlsearch.SysContextSqlConvert
 * JD-Core Version:    0.6.2
 */