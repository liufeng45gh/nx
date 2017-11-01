/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum Orderstatu
/*    */ {
/*  4 */   ORDERSTATU_000("未支付生成订单"), 
/*  5 */   ORDERSTATU_001("已支付待接单"), 
/*  6 */   ORDERSTATU_002("已确定帮助者服务中"), 
/*  7 */   ORDERSTATU_003("服务完成待评价"), 
/*  8 */   ORDERSTATU_004("已评价订单完成"), 
/*  9 */   ORDERSTATU_006("订单完成"), 
/* 10 */   ORDERSTATU_005("取消订单"), 
/* 11 */   ORDERSTATU_007("订单失效");
/*    */ 
/*    */   private String orderStatu;
/*    */ 
/*    */   private Orderstatu(String orderStatu)
/*    */   {
/* 18 */     this.orderStatu = orderStatu;
/*    */   }
/*    */   public String getOrderStatu() {
/* 21 */     return this.orderStatu;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.Orderstatu
 * JD-Core Version:    0.6.2
 */