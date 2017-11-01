/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum pushCategory
/*    */ {
/*  5 */   WHOLE("全部"), 
/*  6 */   PERSONAL("个人");
/*    */ 
/*    */   private String pushCategory;
/*    */ 
/*    */   private pushCategory(String pushCategory)
/*    */   {
/* 14 */     this.pushCategory = pushCategory;
/*    */   }
/*    */   public String getOrderType() {
/* 17 */     return this.pushCategory;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.pushCategory
 * JD-Core Version:    0.6.2
 */