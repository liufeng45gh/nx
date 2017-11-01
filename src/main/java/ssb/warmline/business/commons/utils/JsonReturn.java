/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
/*    */ import ssb.warmline.business.utils.SuceessMsg;
/*    */ 
/*    */ public class JsonReturn
/*    */ {
/* 16 */   private String retcode = "1";
/*    */ 
/* 20 */   private Object retmsg = "OK";
/*    */ 
/*    */   public String getRetcode() {
/* 23 */     return this.retcode;
/*    */   }
/*    */   public void setRetcode(String retcode) {
/* 26 */     this.retcode = retcode;
/*    */   }
/*    */   public Object getRetmsg() {
/* 29 */     return this.retmsg;
/*    */   }
/*    */   public void setRetmsg(Object retmsg) {
/* 32 */     this.retmsg = retmsg;
/*    */   }
/*    */ 
/*    */   public static JsonReturn toErrorResult(String errcode, String errMsg) {
/* 36 */     JsonReturn jr = new JsonReturn();
/* 37 */     jr.setRetcode("0");
/* 38 */     jr.setRetmsg(new ErrorMsg(errcode, errMsg));
/* 39 */     return jr;
/*    */   }
/*    */   public static JsonReturn toErrorResult(Object errMsg) {
/* 42 */     JsonReturn jr = new JsonReturn();
/* 43 */     jr.setRetcode("0");
/* 44 */     jr.setRetmsg(errMsg);
/* 45 */     return jr;
/*    */   }
/*    */ 
/*    */   public static JsonReturn toSucResult(Object msg) {
/* 49 */     JsonReturn jr = new JsonReturn();
/* 50 */     jr.setRetcode("1");
/* 51 */     jr.setRetmsg(msg);
/* 52 */     return jr;
/*    */   }
/*    */   public static JsonReturn toSucResult(String sucesscode, String sucessmsg) {
/* 55 */     JsonReturn jr = new JsonReturn();
/* 56 */     jr.setRetcode("1");
/* 57 */     jr.setRetmsg(new SuceessMsg(sucesscode, sucessmsg));
/* 58 */     return jr;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JsonReturn
 * JD-Core Version:    0.6.2
 */