/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum capitalType
/*    */ {
/*  5 */   income("收入"), 
/*  6 */   expenditure("支出"), 
/*  7 */   Withdrawals("提现");
/*    */ 
/*    */   private String orderType;
/*    */ 
/*    */   private capitalType(String orderType)
/*    */   {
/* 15 */     this.orderType = orderType;
/*    */   }
/*    */   public String getOrderType() {
/* 18 */     return this.orderType;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.capitalType
 * JD-Core Version:    0.6.2
 */