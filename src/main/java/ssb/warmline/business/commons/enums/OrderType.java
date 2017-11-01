/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum OrderType
/*    */ {
/*  4 */   ORDERTYPE_000("实时订单"), 
/*  5 */   ORDERTYPE_001("预订单");
/*    */ 
/*    */   private String orderType;
/*    */ 
/*    */   private OrderType(String orderType)
/*    */   {
/* 13 */     this.orderType = orderType;
/*    */   }
/*    */   public String getOrderStatu() {
/* 16 */     return this.orderType;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.OrderType
 * JD-Core Version:    0.6.2
 */