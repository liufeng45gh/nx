/*    */ package org.jeecgframework.web.demo.entity.test;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.PrimaryKeyJoinColumn;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.web.system.pojo.base.TSAttachment;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_finance_files", schema="")
/*    */ @PrimaryKeyJoinColumn(name="id")
/*    */ public class TFinanceFilesEntity extends TSAttachment
/*    */   implements Serializable
/*    */ {
/*    */   private TFinanceEntity finance;
/*    */ 
/*    */   @ManyToOne(cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH}, fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="financeId")
/*    */   public TFinanceEntity getFinance()
/*    */   {
/* 33 */     return this.finance;
/*    */   }
/*    */ 
/*    */   public void setFinance(TFinanceEntity finance) {
/* 37 */     this.finance = finance;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.TFinanceFilesEntity
 * JD-Core Version:    0.6.2
 */