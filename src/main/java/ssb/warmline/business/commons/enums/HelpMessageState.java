/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ public enum HelpMessageState
/*    */ {
/* 11 */   SEEKHELP("求助消息"), 
/* 12 */   HELP("帮助消息"), 
/* 13 */   SYSTEM("系统消息");
/*    */ 
/*    */   private String messageState;
/*    */ 
/*    */   private HelpMessageState(String messageState)
/*    */   {
/* 21 */     this.messageState = messageState;
/*    */   }
/*    */   public String getMessageState() {
/* 24 */     return this.messageState;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.HelpMessageState
 * JD-Core Version:    0.6.2
 */