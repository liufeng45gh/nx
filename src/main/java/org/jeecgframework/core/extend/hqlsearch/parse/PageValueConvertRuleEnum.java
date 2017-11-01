/*    */ package org.jeecgframework.core.extend.hqlsearch.parse;
/*    */ 
/*    */ import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlParseEnum;
/*    */ import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
/*    */ 
/*    */ public class PageValueConvertRuleEnum
/*    */ {
/*    */   public static HqlRuleEnum convert(Object value)
/*    */   {
/* 20 */     if (value == null) {
/* 21 */       return null;
/*    */     }
/* 23 */     String val = value.toString().trim();
/* 24 */     if (val.length() == 0) {
/* 25 */       return null;
/*    */     }
/*    */ 
/* 28 */     HqlRuleEnum rule = HqlRuleEnum.getByValue(val.substring(0, 1));
/*    */ 
/* 30 */     if ((rule == null) && (val.length() >= 2)) {
/* 31 */       rule = HqlRuleEnum.getByValue(val.substring(0, 2));
/*    */     }
/*    */ 
/* 34 */     if ((rule == null) && 
/* 35 */       (val.contains(HqlParseEnum.SUFFIX_ASTERISK.getValue()))) {
/* 36 */       if ((val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())) && 
/* 37 */         (val.endsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue())))
/* 38 */         rule = HqlRuleEnum.LIKE;
/* 39 */       else if (val.startsWith(HqlParseEnum.SUFFIX_ASTERISK.getValue()))
/* 40 */         rule = HqlRuleEnum.LEFT_LIKE;
/*    */       else {
/* 42 */         rule = HqlRuleEnum.RIGHT_LIKE;
/*    */       }
/*    */     }
/*    */ 
/* 46 */     if ((rule == null) && (val.contains(HqlParseEnum.SUFFIX_COMMA.getValue()))) {
/* 47 */       rule = HqlRuleEnum.IN;
/*    */     }
/*    */ 
/* 50 */     if ((rule == null) && (val.startsWith(HqlParseEnum.SUFFIX_NOT_EQUAL.getValue()))) {
/* 51 */       rule = HqlRuleEnum.NE;
/*    */     }
/*    */ 
/* 58 */     return rule != null ? rule : HqlRuleEnum.EQ;
/*    */   }
/*    */ 
/*    */   public static Object replaceValue(HqlRuleEnum rule, Object value)
/*    */   {
/* 69 */     if (rule == null) {
/* 70 */       return null;
/*    */     }
/* 72 */     if (!(value instanceof String)) {
/* 73 */       return value;
/*    */     }
/* 75 */     String val = value.toString().trim();
/* 76 */     if (rule == HqlRuleEnum.LIKE)
/* 77 */       value = val.substring(1, val.length() - 1);
/* 78 */     else if ((rule == HqlRuleEnum.LEFT_LIKE) || (rule == HqlRuleEnum.NE))
/* 79 */       value = val.substring(1);
/* 80 */     else if (rule == HqlRuleEnum.RIGHT_LIKE)
/* 81 */       value = val.substring(0, val.length() - 1);
/* 82 */     else if (rule != HqlRuleEnum.IN)
/* 83 */       value = val.replace(rule.getValue(), 
/* 84 */         HqlParseEnum.SUFFIX_NULL_STRING.getValue());
/*    */     else {
/* 86 */       value = val.split(",");
/*    */     }
/* 88 */     return value;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.hqlsearch.parse.PageValueConvertRuleEnum
 * JD-Core Version:    0.6.2
 */