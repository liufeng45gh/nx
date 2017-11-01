/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum PaymentMode
/*    */ {
/*  4 */   alipay("支付宝"), 
/*  5 */   weChat("微信"), 
/*  6 */   wallet("钱包"), 
/*  7 */   bank("银行卡");
/*    */ 
/*    */   private String paymentMode;
/*    */ 
/*    */   private PaymentMode(String paymentMode)
/*    */   {
/* 14 */     this.paymentMode = paymentMode;
/*    */   }
/*    */   public String getPaymentModeStatu() {
/* 17 */     return this.paymentMode;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.PaymentMode
 * JD-Core Version:    0.6.2
 */