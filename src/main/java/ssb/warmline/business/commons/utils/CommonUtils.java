/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import ssb.warmline.business.commons.config.ResponseObject;
/*    */ import ssb.warmline.business.commons.config.ResponseStatus;
/*    */ import ssb.warmline.business.commons.enums.Code;
/*    */ 
/*    */ public class CommonUtils
/*    */ {
/*    */   public static void repsonseToClientWithBody(HttpServletResponse response, String code, Object body, String[] args)
/*    */   {
/* 16 */     Code c = Code.getErrorMsg(code, args);
/* 17 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 18 */     ResponseObject responseObj = new ResponseObject();
/* 19 */     responseObj.setStatus(status);
/* 20 */     responseObj.setBody(body);
/*    */     try {
/* 22 */       response.getWriter().println(JSONUtils.obj2json(responseObj));
/*    */     } catch (IOException e) {
/* 24 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 28 */   public static String repsonseToClientWithBody(String code, Object body, String[] args) { Code c = Code.getErrorMsg(code, args);
/* 29 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 30 */     ResponseObject responseObj = new ResponseObject();
/* 31 */     responseObj.setStatus(status);
/* 32 */     responseObj.setBody(body);
/* 33 */     return JSONUtils.obj2json(responseObj); }
/*    */ 
/*    */   public static void repsonseToClient(HttpServletResponse response, String code, String[] args) {
/* 36 */     Code c = Code.getErrorMsg(code, args);
/* 37 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/*    */     try {
/* 39 */       response.getWriter().println(JSONUtils.obj2json(status));
/*    */     } catch (IOException e) {
/* 41 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String repsonseToClient(String code, String[] args) {
/* 46 */     Code c = Code.getErrorMsg(code, args);
/* 47 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 48 */     return JSONUtils.obj2json(status);
/*    */   }
/*    */   public static ResponseObject repsonseOjbectToClientWithBody(String code, Object body, String[] args) {
/* 51 */     Code c = Code.getErrorMsg(code, args);
/* 52 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 53 */     ResponseObject responseObj = new ResponseObject();
/* 54 */     responseObj.setStatus(status);
/* 55 */     responseObj.setBody(body);
/* 56 */     return responseObj;
/*    */   }
/*    */ 
/*    */   public static ResponseObject repsonseOjbectToClientWithBodyVerifyWord(String code, Object body, String[] args)
/*    */   {
/* 61 */     Code c = Code.getErrorMsgVerifyWord(code, args);
/* 62 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 63 */     ResponseObject responseObj = new ResponseObject();
/* 64 */     responseObj.setStatus(status);
/* 65 */     responseObj.setBody(body);
/* 66 */     return responseObj;
/*    */   }
/*    */ 
/*    */   public static ResponseStatus repsonseStatusToClient(String code, String[] args) {
/* 70 */     Code c = Code.getErrorMsg(code, args);
/* 71 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 72 */     return status;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.CommonUtils
 * JD-Core Version:    0.6.2
 */