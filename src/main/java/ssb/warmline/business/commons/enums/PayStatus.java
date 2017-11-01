/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum PayStatus
/*    */ {
/*  9 */   PAY_001(
/* 10 */     "未支付"), 
/* 11 */   PAY_002(
/* 12 */     "支付成功"), 
/* 13 */   PAY_003(
/* 14 */     "支付失败"), 
/* 15 */   PAY_004(
/* 16 */     "已取消");
/*    */ 
/*    */   private String statusDesc;
/*    */ 
/*    */   private PayStatus(String statusDesc)
/*    */   {
/* 25 */     this.statusDesc = statusDesc;
/*    */   }
/*    */ 
/*    */   public String getStatusDesc()
/*    */   {
/* 31 */     return this.statusDesc;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.PayStatus
 * JD-Core Version:    0.6.2
 */