/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ResponseObject
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ResponseStatus status;
/*    */   private Object body;
/*    */ 
/*    */   public ResponseStatus getStatus()
/*    */   {
/* 25 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(ResponseStatus status) {
/* 29 */     this.status = status;
/*    */   }
/*    */ 
/*    */   public Object getBody() {
/* 33 */     return this.body;
/*    */   }
/*    */ 
/*    */   public void setBody(Object body) {
/* 37 */     this.body = body;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.ResponseObject
 * JD-Core Version:    0.6.2
 */