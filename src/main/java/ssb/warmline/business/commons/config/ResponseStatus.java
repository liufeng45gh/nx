/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ResponseStatus
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String respCode;
/*    */   private String respMsg;
/*    */ 
/*    */   public ResponseStatus(String respCode, String respMsg)
/*    */   {
/* 26 */     this.respCode = respCode;
/* 27 */     this.respMsg = respMsg;
/*    */   }
/*    */ 
/*    */   public String getRespCode() {
/* 31 */     return this.respCode;
/*    */   }
/*    */ 
/*    */   public void setRespCode(String respCode) {
/* 35 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   public String getRespMsg() {
/* 39 */     return this.respMsg;
/*    */   }
/*    */ 
/*    */   public void setRespMsg(String respMsg) {
/* 43 */     this.respMsg = respMsg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.ResponseStatus
 * JD-Core Version:    0.6.2
 */