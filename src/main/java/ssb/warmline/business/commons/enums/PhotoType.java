/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum PhotoType
/*    */ {
/*  4 */   PHOTOTYPE_000("个性照片", "head"), 
/*  5 */   PHOTOTYPE_001("订单照片", "order");
/*    */ 
/*    */   private String PhotoType;
/*    */   private String type;
/*    */ 
/*    */   private PhotoType(String PhotoType, String type)
/*    */   {
/* 13 */     this.PhotoType = PhotoType;
/*    */   }
/*    */   public String getOrderStatu() {
/* 16 */     return this.PhotoType;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.PhotoType
 * JD-Core Version:    0.6.2
 */