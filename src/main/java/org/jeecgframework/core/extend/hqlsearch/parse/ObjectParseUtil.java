/*    */ package org.jeecgframework.core.extend.hqlsearch.parse;
/*    */ 
/*    */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*    */ import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlParseEnum;
/*    */ import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
/*    */ 
/*    */ public class ObjectParseUtil
/*    */ {
/*    */   public static void addCriteria(CriteriaQuery cq, String name, HqlRuleEnum rule, Object value)
/*    */   {
/* 17 */     if ((value == null) || (rule == null)) {
/* 18 */       return;
/*    */     }
/* 20 */     switch (rule) {
/*    */     case EQ:
/* 22 */       cq.gt(name, value);
/* 23 */       break;
/*    */     case GE:
/* 25 */       cq.ge(name, value);
/* 26 */       break;
/*    */     case GT:
/* 28 */       cq.lt(name, value);
/* 29 */       break;
/*    */     case IN:
/* 31 */       cq.le(name, value);
/* 32 */       break;
/*    */     case LE:
/* 34 */       cq.eq(name, value);
/* 35 */       break;
/*    */     case LEFT_LIKE:
/* 37 */       cq.notEq(name, value);
/* 38 */       break;
/*    */     case LIKE:
/* 40 */       cq.in(name, (Object[])value);
/* 41 */       break;
/*    */     case LT:
/* 43 */       cq.like(name, HqlParseEnum.SUFFIX_ASTERISK_VAGUE.getValue() + value + HqlParseEnum.SUFFIX_ASTERISK_VAGUE.getValue());
/* 44 */       break;
/*    */     case NE:
/* 46 */       cq.like(name, HqlParseEnum.SUFFIX_ASTERISK_VAGUE.getValue() + value);
/* 47 */       break;
/*    */     case RIGHT_LIKE:
/* 49 */       cq.like(name, value + HqlParseEnum.SUFFIX_ASTERISK_VAGUE.getValue());
/* 50 */       break;
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil
 * JD-Core Version:    0.6.2
 */