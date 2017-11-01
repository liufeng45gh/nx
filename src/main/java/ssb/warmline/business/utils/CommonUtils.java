/*    */ package ssb.warmline.business.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import ssb.warmline.business.commons.config.ResponseObject;
/*    */ import ssb.warmline.business.commons.config.ResponseStatus;
/*    */ import ssb.warmline.business.commons.enums.Code;
/*    */ import ssb.warmline.business.commons.utils.JSONUtils;
/*    */ 
/*    */ public class CommonUtils
/*    */ {
/*    */   public static void repsonseToClientWithBody(HttpServletResponse response, String code, Object body, String[] args)
/*    */   {
/* 13 */     Code c = Code.getErrorMsg(code, args);
/* 14 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 15 */     ResponseObject responseObj = new ResponseObject();
/* 16 */     responseObj.setStatus(status);
/* 17 */     responseObj.setBody(body);
/*    */     try {
/* 19 */       response.getWriter().println(JSONUtils.obj2json(responseObj));
/*    */     } catch (IOException e) {
/* 21 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 25 */   public static String repsonseToClientWithBody(String code, Object body, String[] args) { Code c = Code.getErrorMsg(code, args);
/* 26 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 27 */     ResponseObject responseObj = new ResponseObject();
/* 28 */     responseObj.setStatus(status);
/* 29 */     responseObj.setBody(body);
/* 30 */     return JSONUtils.obj2json(responseObj); }
/*    */ 
/*    */   public static void repsonseToClient(HttpServletResponse response, String code, String[] args) {
/* 33 */     Code c = Code.getErrorMsg(code, args);
/* 34 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/*    */     try {
/* 36 */       response.getWriter().println(JSONUtils.obj2json(status));
/*    */     } catch (IOException e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String repsonseToClient(String code, String[] args) {
/* 43 */     Code c = Code.getErrorMsg(code, args);
/* 44 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 45 */     return JSONUtils.obj2json(status);
/*    */   }
/*    */   public static ResponseObject repsonseOjbectToClientWithBody(String code, Object body, String[] args) {
/* 48 */     Code c = Code.getErrorMsg(code, args);
/* 49 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 50 */     ResponseObject responseObj = new ResponseObject();
/* 51 */     responseObj.setStatus(status);
/* 52 */     responseObj.setBody(body);
/* 53 */     return responseObj;
/*    */   }
/*    */   public static ResponseStatus repsonseStatusToClient(String code, String[] args) {
/* 56 */     Code c = Code.getErrorMsg(code, args);
/* 57 */     ResponseStatus status = new ResponseStatus(c.getCode(), c.getCodeMsg());
/* 58 */     return status;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.utils.CommonUtils
 * JD-Core Version:    0.6.2
 */