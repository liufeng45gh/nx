/*    */ package ssb.warmline.business.commons.enums;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public enum ReadingState
/*    */ {
/*  4 */   No("未阅读"), 
/*  5 */   Yes("已阅读");
/*    */ 
/*    */   private String readingState;
/*    */ 
/*    */   private ReadingState(String readingState)
/*    */   {
/* 13 */     this.readingState = readingState;
/*    */   }
/*    */   public String getMessageState() {
/* 16 */     return this.readingState;
/*    */   }
/*    */ 
/*    */   public static void main(String[] arg) {
/* 20 */     System.out.println(No.toString());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.enums.ReadingState
 * JD-Core Version:    0.6.2
 */