/*    */ package org.jeecgframework.core.groovy.impl;
/*    */ 
/*    */ import org.jeecgframework.core.groovy.IScript;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class FormulaImpl
/*    */   implements IScript
/*    */ {
/*    */   public Double add(Double a, Double b)
/*    */   {
/* 12 */     return Double.valueOf(a.doubleValue() * b.doubleValue());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.groovy.impl.FormulaImpl
 * JD-Core Version:    0.6.2
 */