/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletInputStream;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*    */ import org.springframework.web.multipart.MultipartResolver;
/*    */ import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/*    */ import ssb.warmline.business.commons.enums.PhotoType;
/*    */ import ssb.warmline.business.commons.utils.CommonUtils;
/*    */ import ssb.warmline.business.commons.utils.DateUtils;
/*    */ import ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity;
/*    */ import ssb.warmline.business.service.worderphoto.WOrderPhotoServiceI;
/*    */ 
/*    */ public class UploadOrderImageServlet extends HttpServlet
/*    */ {
/* 36 */   protected static final Logger logger = LoggerFactory.getLogger(UploadOrderImageServlet.class);
/*    */ 
/* 39 */   static final String separator = File.separator;
/* 40 */   private ApplicationContext context = null;
/*    */ 
/*    */   public void init() throws ServletException
/*    */   {
/* 44 */     this.context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/*    */   }
/*    */ 
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 49 */     doPost(req, resp);
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 54 */     uploadOrderImage(req, resp);
/*    */   }
/*    */ 
/*    */   public void uploadOrderImage(HttpServletRequest request, HttpServletResponse response)
/*    */     throws IOException
/*    */   {
/* 66 */     Map jsonMap = new HashMap();
/* 67 */     String uid = request.getParameter("uid");
/*    */ 
/* 69 */     String token = request.getParameter("token");
/*    */ 
/* 71 */     String imageType = request.getParameter("type");
/*    */     try {
/* 73 */       if ("ios".equals(imageType)) {
/* 74 */         MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
/* 75 */         MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
/* 76 */         MultipartFile file = multipartRequest.getFile("file");
/*    */ 
/* 78 */         String path_temp = request.getSession().getServletContext().getRealPath("");
/* 79 */         String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/* 80 */         String imgPath = separator + "upload" + separator + "orderImage";
/*    */ 
/* 82 */         String uploadPath = path + separator + "upload";
/*    */ 
/* 84 */         String filePath = path + imgPath;
/*    */ 
/* 86 */         File uploadDir = new File(uploadPath);
/* 87 */         File orderImageDir = new File(filePath);
/* 88 */         if (!uploadDir.exists()) {
/* 89 */           boolean test = uploadDir.mkdir();
/* 90 */           if (!test) {
/* 91 */             response.setCharacterEncoding("utf-8");
/* 92 */             CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/* 93 */           } else if (!orderImageDir.exists()) {
/* 94 */             boolean test1 = orderImageDir.mkdir();
/* 95 */             if (!test1) {
/* 96 */               response.setCharacterEncoding("utf-8");
/* 97 */               CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/*    */             }
/*    */           }
/* 100 */         } else if (!orderImageDir.exists()) {
/* 101 */           boolean test1 = orderImageDir.mkdir();
/* 102 */           if (!test1) {
/* 103 */             response.setCharacterEncoding("utf-8");
/* 104 */             CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/*    */           }
/*    */         }
/*    */ 
/* 108 */         double fileSize = file.getSize() / 1024.0D / 1024.0D;
/* 109 */         String fileName = file.getOriginalFilename();
/* 110 */         String fileSuffix = ".jpg";
/* 111 */         if (!"".equals(fileName)) {
/* 112 */           fileSuffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
/*    */         }
/*    */ 
/* 115 */         String newName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + fileSuffix;
/* 116 */         String fullFile = filePath + separator + newName;
/* 117 */         FileOutputStream fileOS = new FileOutputStream(fullFile);
/* 118 */         fileOS.write(file.getBytes());
/* 119 */         fileOS.close();
/*    */ 
/* 122 */         String domain = request.getServerName() + ":";
/* 123 */         String pathUrlName = "http://" + domain + "80";
/*    */ 
/* 125 */         WOrderPhotoServiceI wOrderPhotoService = (WOrderPhotoServiceI)this.context.getBean(WOrderPhotoServiceI.class);
/*    */ 
/* 127 */         WOrderPhotoEntity wOrderPhotoEntity = new WOrderPhotoEntity();
/* 128 */         wOrderPhotoEntity.setId(UUID.randomUUID().toString());
/* 129 */         wOrderPhotoEntity.setCreateTime(new Date());
/* 130 */         wOrderPhotoEntity.setPhotoUrl(pathUrlName + "/upload/orderImage/" + newName);
/* 131 */         wOrderPhotoEntity.setPhotoName(newName);
/* 132 */         wOrderPhotoEntity.setUid(uid);
/* 133 */         wOrderPhotoEntity.setPhotoType(PhotoType.PHOTOTYPE_001.toString());
/* 134 */         jsonMap.put("paths", "/upload/orderImage/" + newName);
/* 135 */         wOrderPhotoService.save(wOrderPhotoEntity);
/* 136 */         jsonMap.put("photoId", wOrderPhotoEntity.getId());
/*    */ 
/* 138 */         response.setCharacterEncoding("utf-8");
/* 139 */         CommonUtils.repsonseToClientWithBody(response, "10000", jsonMap, new String[] { "success" });
/*    */       }
/*    */       else
/*    */       {
/* 143 */         String fileName_temp = request.getParameter("fileName");
/* 144 */         int start = fileName_temp.indexOf(".");
/*    */ 
/* 146 */         String fileFormat = fileName_temp.substring(start + 1);
/*    */ 
/* 148 */         ServletInputStream bufferIn = request.getInputStream();
/*    */ 
/* 150 */         String path_temp = request.getSession().getServletContext().getRealPath("");
/* 151 */         String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/* 152 */         String imgPath = separator + "upload" + separator + "orderImage";
/*    */ 
/* 154 */         String uploadPath = path + separator + "upload";
/*    */ 
/* 156 */         String filePath = path + imgPath;
/*    */ 
/* 158 */         File uploadDir = new File(uploadPath);
/*    */ 
/* 160 */         File orderImageDir = new File(filePath);
/* 161 */         if (!uploadDir.exists()) {
/* 162 */           boolean test = uploadDir.mkdir();
/* 163 */           if (!test) {
/* 164 */             response.setCharacterEncoding("utf-8");
/* 165 */             CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/* 166 */           } else if (!orderImageDir.exists()) {
/* 167 */             boolean test1 = orderImageDir.mkdir();
/* 168 */             if (!test1) {
/* 169 */               response.setCharacterEncoding("utf-8");
/* 170 */               CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/*    */             }
/*    */           }
/* 173 */         } else if (!orderImageDir.exists()) {
/* 174 */           boolean test1 = orderImageDir.mkdir();
/* 175 */           if (!test1) {
/* 176 */             response.setCharacterEncoding("utf-8");
/* 177 */             CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[] { "error" });
/*    */           }
/*    */         }
/*    */ 
/* 181 */         String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + fileFormat;
/*    */ 
/* 183 */         File file = new File(filePath, fileName);
/*    */ 
/* 185 */         byte[] buffer = new byte[1024];
/*    */ 
/* 187 */         FileOutputStream out = new FileOutputStream(file);
/*    */ 
/* 189 */         int len = bufferIn.read(buffer, 0, 1024);
/* 190 */         while (len != -1) {
/* 191 */           out.write(buffer, 0, len);
/* 192 */           len = bufferIn.read(buffer, 0, 1024);
/*    */         }
/*    */ 
/* 195 */         out.close();
/* 196 */         bufferIn.close();
/*    */ 
/* 199 */         String domain = request.getServerName() + ":";
/* 200 */         String pathUrlName = "http://" + domain + "80";
/*    */ 
/* 203 */         WOrderPhotoServiceI wOrderPhotoService = (WOrderPhotoServiceI)this.context.getBean(WOrderPhotoServiceI.class);
/*    */ 
/* 205 */         WOrderPhotoEntity wOrderPhotoEntity = new WOrderPhotoEntity();
/* 206 */         wOrderPhotoEntity.setId(UUID.randomUUID().toString());
/* 207 */         wOrderPhotoEntity.setCreateTime(new Date());
/* 208 */         wOrderPhotoEntity.setPhotoUrl(pathUrlName + "/upload/orderImage/" + fileName);
/* 209 */         wOrderPhotoEntity.setPhotoName(fileName);
/* 210 */         wOrderPhotoEntity.setUid(uid);
/* 211 */         wOrderPhotoEntity.setPhotoType(PhotoType.PHOTOTYPE_001.toString());
/* 212 */         jsonMap.put("paths", "/upload/orderImage/" + fileName);
/* 213 */         wOrderPhotoService.save(wOrderPhotoEntity);
/* 214 */         jsonMap.put("photoId", wOrderPhotoEntity.getId());
/*    */ 
/* 216 */         response.setCharacterEncoding("utf-8");
/* 217 */         CommonUtils.repsonseToClientWithBody(response, "10000", jsonMap, new String[] { "success" });
/*    */       }
/*    */     } catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       response.setCharacterEncoding("utf-8");
/* 222 */       CommonUtils.repsonseToClientWithBody(response, "20022", jsonMap, new String[] { "error" });
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.UploadOrderImageServlet
 * JD-Core Version:    0.6.2
 */