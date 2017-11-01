/*    */ package ssb.warmline.business.commons.support.exception;
/*    */ 
/*    */ public class DataParseException extends RuntimeException
/*    */ {
/*    */   public DataParseException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DataParseException(Throwable ex)
/*    */   {
/* 10 */     super(ex);
/*    */   }
/*    */ 
/*    */   public DataParseException(String message) {
/* 14 */     super(message);
/*    */   }
/*    */ 
/*    */   public DataParseException(String message, Throwable ex) {
/* 18 */     super(message, ex);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.exception.DataParseException
 * JD-Core Version:    0.6.2
 */