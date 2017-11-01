/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.apache.commons.fileupload.FileItemIterator;
/*     */ import org.apache.commons.fileupload.FileItemStream;
/*     */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*     */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class Uploader
/*     */ {
/*     */   private static final int MAX_SIZE = 512000;
/*  37 */   private String url = "";
/*     */ 
/*  39 */   private String fileName = "";
/*     */ 
/*  41 */   private String state = "";
/*     */ 
/*  43 */   private String type = "";
/*     */ 
/*  45 */   private String originalName = "";
/*     */ 
/*  47 */   private String size = "";
/*     */ 
/*  49 */   private HttpServletRequest request = null;
/*  50 */   private String title = "";
/*     */ 
/*  53 */   private String savePath = "upload";
/*     */ 
/*  55 */   private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", 
/*  56 */     ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
/*     */ 
/*  58 */   private long maxSize = 0L;
/*     */ 
/*  60 */   private HashMap<String, String> errorInfo = new HashMap();
/*  61 */   private Map<String, String> params = null;
/*     */ 
/*  63 */   private InputStream inputStream = null;
/*     */ 
/*  65 */   public static final String ENCODEING = System.getProperties().getProperty(
/*  66 */     "file.encoding");
/*     */ 
/*     */   public Uploader(HttpServletRequest request)
/*     */   {
/*  69 */     this.request = request;
/*  70 */     this.params = new HashMap();
/*     */ 
/*  72 */     setMaxSize(512000L);
/*     */ 
/*  74 */     HashMap tmp = this.errorInfo;
/*  75 */     tmp.put("SUCCESS", "SUCCESS");
/*     */ 
/*  77 */     tmp.put("NOFILE", 
/*  78 */       "\\u672a\\u5305\\u542b\\u6587\\u4ef6\\u4e0a\\u4f20\\u57df");
/*     */ 
/*  80 */     tmp.put("TYPE", 
/*  81 */       "\\u4e0d\\u5141\\u8bb8\\u7684\\u6587\\u4ef6\\u683c\\u5f0f");
/*     */ 
/*  83 */     tmp.put("SIZE", 
/*  84 */       "\\u6587\\u4ef6\\u5927\\u5c0f\\u8d85\\u51fa\\u9650\\u5236");
/*     */ 
/*  86 */     tmp.put("ENTYPE", "\\u8bf7\\u6c42\\u7c7b\\u578b\\u9519\\u8bef");
/*     */ 
/*  88 */     tmp.put("REQUEST", "\\u4e0a\\u4f20\\u8bf7\\u6c42\\u5f02\\u5e38");
/*     */ 
/*  90 */     tmp.put("FILE", "\\u672a\\u627e\\u5230\\u4e0a\\u4f20\\u6587\\u4ef6");
/*     */ 
/*  92 */     tmp.put("IO", "IO\\u5f02\\u5e38");
/*     */ 
/*  94 */     tmp.put("DIR", "\\u76ee\\u5f55\\u521b\\u5efa\\u5931\\u8d25");
/*     */ 
/*  96 */     tmp.put("UNKNOWN", "\\u672a\\u77e5\\u9519\\u8bef");
/*     */ 
/*  98 */     parseParams();
/*     */   }
/*     */ 
/*     */   public void upload() throws Exception
/*     */   {
/* 103 */     boolean isMultipart = 
/* 104 */       ServletFileUpload.isMultipartContent(this.request);
/* 105 */     if (!isMultipart) {
/* 106 */       this.state = ((String)this.errorInfo.get("NOFILE"));
/* 107 */       return;
/*     */     }
/*     */ 
/* 110 */     if (this.inputStream == null) {
/* 111 */       this.state = ((String)this.errorInfo.get("FILE"));
/* 112 */       return;
/*     */     }
/*     */ 
/* 116 */     this.title = getParameter("pictitle");
/*     */     try
/*     */     {
/* 119 */       String savePath = getFolder(this.savePath);
/*     */ 
/* 121 */       if (!checkFileType(this.originalName)) {
/* 122 */         this.state = ((String)this.errorInfo.get("TYPE"));
/* 123 */         return;
/*     */       }
/*     */ 
/* 126 */       this.fileName = getName(this.originalName);
/* 127 */       this.type = getFileExt(this.fileName);
/* 128 */       this.url = (savePath + "/" + this.fileName);
/*     */ 
/* 130 */       FileOutputStream fos = new FileOutputStream(
/* 131 */         getPhysicalPath(this.url));
/* 132 */       BufferedInputStream bis = new BufferedInputStream(this.inputStream);
/* 133 */       byte[] buff = new byte[''];
/* 134 */       int count = -1;
/*     */ 
/* 136 */       while ((count = bis.read(buff)) != -1)
/*     */       {
/* 138 */         fos.write(buff, 0, count);
/*     */       }
/*     */ 
/* 142 */       bis.close();
/* 143 */       fos.close();
/*     */ 
/* 145 */       this.state = ((String)this.errorInfo.get("SUCCESS"));
/*     */     } catch (Exception e) {
/* 147 */       e.printStackTrace();
/* 148 */       this.state = ((String)this.errorInfo.get("IO"));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void uploadBase64(String fieldName)
/*     */   {
/* 159 */     String savePath = getFolder(this.savePath);
/* 160 */     String base64Data = this.request.getParameter(fieldName);
/* 161 */     this.fileName = getName("test.png");
/* 162 */     this.url = (savePath + "/" + this.fileName);
/* 163 */     Base64 decoder = new Base64();
/*     */     try
/*     */     {
/* 166 */       File outFile = new File(getPhysicalPath(this.url));
/* 167 */       OutputStream ro = new FileOutputStream(outFile);
/* 168 */       byte[] b = decoder.encode(base64Data.getBytes());
/* 169 */       for (int i = 0; i < b.length; i++) {
/* 170 */         if (b[i] < 0)
/*     */         {
/*     */           int tmp127_125 = i;
/*     */           byte[] tmp127_123 = b; tmp127_123[tmp127_125] = ((byte)(tmp127_123[tmp127_125] + 256));
/*     */         }
/*     */       }
/* 174 */       ro.write(b);
/* 175 */       ro.flush();
/* 176 */       ro.close();
/* 177 */       this.state = ((String)this.errorInfo.get("SUCCESS"));
/*     */     } catch (Exception e) {
/* 179 */       this.state = ((String)this.errorInfo.get("IO"));
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getParameter(String name)
/*     */   {
/* 185 */     return (String)this.params.get(name);
/*     */   }
/*     */ 
/*     */   private boolean checkFileType(String fileName)
/*     */   {
/* 196 */     Iterator type = Arrays.asList(this.allowFiles).iterator();
/* 197 */     while (type.hasNext()) {
/* 198 */       String ext = (String)type.next();
/* 199 */       if (fileName.toLowerCase().endsWith(ext)) {
/* 200 */         return true;
/*     */       }
/*     */     }
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */   private String getFileExt(String fileName)
/*     */   {
/* 212 */     return fileName.substring(fileName.lastIndexOf("."));
/*     */   }
/*     */ 
/*     */   private void parseParams()
/*     */   {
/* 217 */     DiskFileItemFactory dff = new DiskFileItemFactory();
/*     */     try {
/* 219 */       ServletFileUpload sfu = new ServletFileUpload(dff);
/* 220 */       sfu.setSizeMax(this.maxSize);
/* 221 */       sfu.setHeaderEncoding(ENCODEING);
/*     */ 
/* 223 */       FileItemIterator fii = sfu.getItemIterator(this.request);
/*     */ 
/* 225 */       while (fii.hasNext()) {
/* 226 */         FileItemStream item = fii.next();
/*     */ 
/* 228 */         if (item.isFormField())
/*     */         {
/* 230 */           this.params.put(item.getFieldName(), 
/* 231 */             getParameterValue(item.openStream()));
/*     */         }
/* 236 */         else if (this.inputStream == null) {
/* 237 */           this.inputStream = item.openStream();
/* 238 */           this.originalName = item.getName();
/* 239 */           return;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 247 */       this.state = ((String)this.errorInfo.get("UNKNOWN"));
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getName(String fileName)
/*     */   {
/* 258 */     Random random = new Random();
/* 259 */     return this.fileName = random.nextInt(10000) + 
/* 260 */       System.currentTimeMillis() + getFileExt(fileName);
/*     */   }
/*     */ 
/*     */   private String getFolder(String path)
/*     */   {
/* 270 */     SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
/* 271 */     path = path + "/" + formater.format(new Date());
/* 272 */     File dir = new File(getPhysicalPath(path));
/* 273 */     if (!dir.exists()) {
/*     */       try {
/* 275 */         dir.mkdirs();
/*     */       } catch (Exception e) {
/* 277 */         this.state = ((String)this.errorInfo.get("DIR"));
/* 278 */         return "";
/*     */       }
/*     */     }
/* 281 */     return path;
/*     */   }
/*     */ 
/*     */   private String getPhysicalPath(String path)
/*     */   {
/* 291 */     String servletPath = this.request.getServletPath();
/* 292 */     String realPath = this.request.getSession().getServletContext()
/* 293 */       .getRealPath(servletPath);
/* 294 */     return new File(realPath).getParent() + "/" + path;
/*     */   }
/*     */ 
/*     */   private String getParameterValue(InputStream in)
/*     */   {
/* 306 */     BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/*     */ 
/* 308 */     String result = "";
/* 309 */     String tmpString = null;
/*     */     try
/*     */     {
/* 313 */       while ((tmpString = reader.readLine()) != null) {
/* 314 */         result = result + tmpString;
/*     */       }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */ 
/* 321 */     return result;
/*     */   }
/*     */ 
/*     */   private byte[] getFileOutputStream(InputStream in)
/*     */   {
/*     */     try
/*     */     {
/* 328 */       return IOUtils.toByteArray(in); } catch (IOException e) {
/*     */     }
/* 330 */     return null;
/*     */   }
/*     */ 
/*     */   public void setSavePath(String savePath)
/*     */   {
/* 336 */     this.savePath = savePath;
/*     */   }
/*     */ 
/*     */   public void setAllowFiles(String[] allowFiles) {
/* 340 */     this.allowFiles = allowFiles;
/*     */   }
/*     */ 
/*     */   public void setMaxSize(long size) {
/* 344 */     this.maxSize = (size * 1024L);
/*     */   }
/*     */ 
/*     */   public String getSize() {
/* 348 */     return this.size;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 352 */     return this.url;
/*     */   }
/*     */ 
/*     */   public String getFileName() {
/* 356 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public String getState() {
/* 360 */     return this.state;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/* 364 */     return this.title;
/*     */   }
/*     */ 
/*     */   public String getType() {
/* 368 */     return this.type;
/*     */   }
/*     */ 
/*     */   public String getOriginalName() {
/* 372 */     return this.originalName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.Uploader
 * JD-Core Version:    0.6.2
 */