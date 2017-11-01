/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*    */ 
/*    */ public class ImportFile
/*    */ {
/*    */   private Object object;
/*    */   private String fileName;
/*    */   private String entityName;
/*    */   private String field;
/*    */   private Class entityClass;
/*    */   private MultipartHttpServletRequest multipartRequest;
/*    */   private HttpServletRequest request;
/*    */   private HttpServletResponse response;
/*    */ 
/*    */   public Object getObject()
/*    */   {
/* 24 */     return this.object;
/*    */   }
/*    */   public void setObject(Object object) {
/* 27 */     this.object = object;
/*    */   }
/*    */ 
/*    */   public Class getEntityClass() {
/* 31 */     return this.entityClass;
/*    */   }
/*    */   public void setEntityClass(Class entityClass) {
/* 34 */     this.entityClass = entityClass;
/*    */   }
/*    */   public String getFileName() {
/* 37 */     return this.fileName;
/*    */   }
/*    */   public void setFileName(String fileName) {
/* 40 */     this.fileName = fileName;
/*    */   }
/*    */   public String getEntityName() {
/* 43 */     return this.entityName;
/*    */   }
/*    */   public void setEntityName(String entityName) {
/* 46 */     this.entityName = entityName;
/*    */   }
/*    */   public MultipartHttpServletRequest getMultipartRequest() {
/* 49 */     return this.multipartRequest;
/*    */   }
/*    */   public void setMultipartRequest(MultipartHttpServletRequest multipartRequest) {
/* 52 */     this.multipartRequest = multipartRequest;
/*    */   }
/*    */   public HttpServletRequest getRequest() {
/* 55 */     return this.request;
/*    */   }
/*    */   public void setRequest(HttpServletRequest request) {
/* 58 */     this.request = request;
/*    */   }
/*    */   public HttpServletResponse getResponse() {
/* 61 */     return this.response;
/*    */   }
/*    */   public void setResponse(HttpServletResponse response) {
/* 64 */     this.response = response;
/*    */   }
/*    */ 
/*    */   public ImportFile(HttpServletRequest request, HttpServletResponse response) {
/* 68 */     this.request = request;
/* 69 */     this.response = response;
/*    */   }
/*    */   public String getField() {
/* 72 */     return this.field;
/*    */   }
/*    */   public void setField(String field) {
/* 75 */     this.field = field;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.ImportFile
 * JD-Core Version:    0.6.2
 */