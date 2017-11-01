/*     */ package org.jeecgframework.core.common.model.common;
/*     */ 
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ 
/*     */ public class UploadFile
/*     */ {
/*  18 */   private String byteField = "attachmentcontent";
/*  19 */   private String titleField = "attachmenttitle";
/*  20 */   private String basePath = "upload";
/*  21 */   private String realPath = "realpath";
/*  22 */   private String extend = "extend";
/*  23 */   private boolean view = false;
/*  24 */   private boolean rename = true;
/*     */   private String swfpath;
/*     */   private String cusPath;
/*     */   private byte[] content;
/*     */   private Object object;
/*     */   private String fileKey;
/*     */   private MultipartHttpServletRequest multipartRequest;
/*     */   private HttpServletRequest request;
/*     */   private HttpServletResponse response;
/*     */ 
/*     */   public void setResponse(HttpServletResponse response)
/*     */   {
/*  39 */     this.response = response;
/*     */   }
/*     */ 
/*     */   public UploadFile(HttpServletRequest request, Object object) {
/*  43 */     String fileKey = oConvertUtils.getString(request.getParameter("fileKey"));
/*  44 */     if (StringUtil.isNotEmpty(fileKey)) {
/*  45 */       this.fileKey = fileKey;
/*  46 */       this.request = request;
/*     */     } else {
/*  48 */       this.multipartRequest = ((MultipartHttpServletRequest)request);
/*     */     }
/*  50 */     this.object = object;
/*     */   }
/*     */ 
/*     */   public UploadFile(HttpServletRequest request) {
/*  54 */     this.multipartRequest = ((MultipartHttpServletRequest)request);
/*     */   }
/*     */ 
/*     */   public UploadFile(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  59 */     this.request = request;
/*  60 */     this.response = response;
/*     */   }
/*     */ 
/*     */   public UploadFile()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getSwfpath() {
/*  68 */     return this.swfpath;
/*     */   }
/*     */ 
/*     */   public void setSwfpath(String swfpath) {
/*  72 */     this.swfpath = swfpath;
/*     */   }
/*     */ 
/*     */   public String getRealPath()
/*     */   {
/*  79 */     return this.realPath;
/*     */   }
/*     */ 
/*     */   public void setRealPath(String realPath)
/*     */   {
/*  87 */     this.realPath = realPath;
/*     */   }
/*     */ 
/*     */   public HttpServletResponse getResponse()
/*     */   {
/*  94 */     return this.response;
/*     */   }
/*     */ 
/*     */   public HttpServletRequest getRequest()
/*     */   {
/* 101 */     return this.request;
/*     */   }
/*     */ 
/*     */   public void setRequest(HttpServletRequest request)
/*     */   {
/* 109 */     this.request = request;
/*     */   }
/*     */ 
/*     */   public MultipartHttpServletRequest getMultipartRequest()
/*     */   {
/* 116 */     return this.multipartRequest;
/*     */   }
/*     */ 
/*     */   public String get(String name) {
/* 120 */     return getMultipartRequest().getParameter(name);
/*     */   }
/*     */ 
/*     */   public void setMultipartRequest(MultipartHttpServletRequest multipartRequest)
/*     */   {
/* 129 */     this.multipartRequest = multipartRequest;
/*     */   }
/*     */ 
/*     */   public Object getObject() {
/* 133 */     return this.object;
/*     */   }
/*     */ 
/*     */   public String getBasePath() {
/* 137 */     return this.basePath;
/*     */   }
/*     */ 
/*     */   public void setBasePath(String basePath) {
/* 141 */     this.basePath = basePath;
/*     */   }
/*     */ 
/*     */   public void setObject(Object object) {
/* 145 */     this.object = object;
/*     */   }
/*     */ 
/*     */   public String getByteField() {
/* 149 */     return this.byteField;
/*     */   }
/*     */ 
/*     */   public void setByteField(String byteField) {
/* 153 */     this.byteField = byteField;
/*     */   }
/*     */ 
/*     */   public String getTitleField() {
/* 157 */     return this.titleField;
/*     */   }
/*     */ 
/*     */   public void setTitleField(String titleField) {
/* 161 */     this.titleField = titleField;
/*     */   }
/*     */ 
/*     */   public String getCusPath() {
/* 165 */     return this.cusPath;
/*     */   }
/*     */ 
/*     */   public void setCusPath(String cusPath) {
/* 169 */     this.cusPath = cusPath;
/*     */   }
/*     */ 
/*     */   public String getExtend() {
/* 173 */     return this.extend;
/*     */   }
/*     */ 
/*     */   public void setExtend(String extend) {
/* 177 */     this.extend = extend;
/*     */   }
/*     */ 
/*     */   public boolean isView() {
/* 181 */     return this.view;
/*     */   }
/*     */ 
/*     */   public void setView(boolean view) {
/* 185 */     this.view = view;
/*     */   }
/*     */ 
/*     */   public byte[] getContent() {
/* 189 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(byte[] content) {
/* 193 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getFileKey() {
/* 197 */     return this.fileKey;
/*     */   }
/*     */ 
/*     */   public void setFileKey(String fileKey) {
/* 201 */     this.fileKey = fileKey;
/*     */   }
/*     */   public boolean isRename() {
/* 204 */     return this.rename;
/*     */   }
/*     */ 
/*     */   public void setRename(boolean rename) {
/* 208 */     this.rename = rename;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.common.UploadFile
 * JD-Core Version:    0.6.2
 */