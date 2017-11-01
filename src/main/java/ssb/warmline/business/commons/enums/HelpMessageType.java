/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum HelpMessageType
/*    */ {
/*  5 */   APPLICATION("申请中"), 
/*  6 */   CANCEL("取消"), 
/*  7 */   REFUSE("拒绝"), 
/*  8 */   INVALID("失效"), 
/*  9 */   AGREE("同意"), 
/* 10 */   COMPLETE("完成"), 
/* 11 */   SYSTEM("系统"), 
/* 12 */   TEXT("文本"), 
/* 13 */   VERSIONUPDATE("系统更新");
/*    */ 
/*    */   private String orderType;
/*    */ 
/*    */   private HelpMessageType(String orderType)
/*    */   {
/* 21 */     this.orderType = orderType;
/*    */   }
/*    */   public String getOrderType() {
/* 24 */     return this.orderType;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.HelpMessageType
 * JD-Core Version:    0.6.2
 */