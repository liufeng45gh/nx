/*    */ package ssb.warmline.business.utils;
/*    */ 
/*    */ public class SuceessMsg
/*    */ {
/*    */   private String sucesscode;
/*    */   private String sucessmsg;
/*    */ 
/*    */   public SuceessMsg(String sucesscode, String sucessmsg)
/*    */   {
/* 14 */     this.sucesscode = sucesscode;
/* 15 */     this.sucessmsg = sucessmsg;
/*    */   }
/*    */ 
/*    */   public String getSucesscode() {
/* 19 */     return this.sucesscode;
/*    */   }
/*    */ 
/*    */   public void setSucesscode(String sucesscode) {
/* 23 */     this.sucesscode = sucesscode;
/*    */   }
/*    */ 
/*    */   public String getSucessmsg() {
/* 27 */     return this.sucessmsg;
/*    */   }
/*    */ 
/*    */   public void setSucessmsg(String sucessmsg) {
/* 31 */     this.sucessmsg = sucessmsg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.utils.SuceessMsg
 * JD-Core Version:    0.6.2
 */