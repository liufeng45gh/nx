/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum CommissionType
/*    */ {
/*  5 */   CANCEL("取消"), 
/*  6 */   COMPLETIONOFORDER("完成订单");
/*    */ 
/*    */   private String CommissionType;
/*    */ 
/*    */   private CommissionType(String commissionType)
/*    */   {
/* 13 */     this.CommissionType = commissionType;
/*    */   }
/*    */   public String getCommissionType() {
/* 16 */     return this.CommissionType;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.CommissionType
 * JD-Core Version:    0.6.2
 */