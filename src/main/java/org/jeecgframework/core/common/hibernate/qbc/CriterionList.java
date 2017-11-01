/*    */ package org.jeecgframework.core.common.hibernate.qbc;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ 
/*    */ public class CriterionList extends ArrayList<Object>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public final Criterion getParas(int index)
/*    */   {
/* 21 */     return (Criterion)super.get(index);
/*    */   }
/*    */ 
/*    */   public final void addPara(int index, Criterion p) {
/* 25 */     super.add(index, p);
/*    */   }
/*    */ 
/*    */   public final void addPara(Criterion p) {
/* 29 */     super.add(p);
/*    */   }
/*    */ 
/*    */   public final int indexofPara(Criterion p) {
/* 33 */     return super.indexOf(p);
/*    */   }
/*    */ 
/*    */   public final void removePara(int index) {
/* 37 */     super.remove(index);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.hibernate.qbc.CriterionList
 * JD-Core Version:    0.6.2
 */