/*    */ package org.jeecgframework.core.servlet;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.fileupload.FileItem;
/*    */ import org.apache.commons.fileupload.FileUploadException;
/*    */ import org.apache.commons.fileupload.disk.DiskFileItemFactory;
/*    */ import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*    */ 
/*    */ public class UploadTmpPhotoServlet extends HttpServlet
/*    */ {
/*    */   public void doGet(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response)
/*    */     throws ServletException, IOException
/*    */   {
/* 51 */     DiskFileItemFactory factory = new DiskFileItemFactory();
/*    */ 
/* 55 */     factory.setSizeThreshold(10240000);
/*    */ 
/* 59 */     String base = "";
/*    */ 
/* 61 */     File file = new File(base);
/*    */ 
/* 63 */     if (!file.exists())
/*    */     {
/* 65 */       file.mkdirs();
/*    */     }
/* 67 */     factory.setRepository(file);
/*    */ 
/* 69 */     ServletFileUpload upload = new ServletFileUpload(factory);
/*    */ 
/* 73 */     upload.setFileSizeMax(10002400000L);
/*    */ 
/* 77 */     upload.setSizeMax(10002400000L);
/*    */ 
/* 79 */     upload.setHeaderEncoding("UTF-8");
/*    */ 
/* 81 */     request.setCharacterEncoding("utf-8");
/*    */ 
/* 83 */     response.setCharacterEncoding("utf-8");
/*    */ 
/* 85 */     PrintWriter out = response.getWriter();
/*    */     try
/*    */     {
/* 89 */       List items = upload.parseRequest(request);
/*    */ 
/* 91 */       FileItem item = null;
/*    */ 
/* 93 */       String tpmFilePathName = null;
/*    */ 
/* 95 */       String savePath = "";
/*    */ 
/* 97 */       Map fileNames = new HashMap();
/*    */ 
/* 99 */       for (int i = 0; i < items.size(); i++)
/*    */       {
/* 101 */         item = (FileItem)items.get(i);
/*    */ 
/* 105 */         if ((!item.isFormField()) && (item.getName().length() > 0))
/*    */         {
/* 107 */           fileNames.put("oldName", item.getName());
/*    */ 
/* 109 */           String suffixName = item.getName().substring(item.getName().lastIndexOf("."));
/*    */ 
/* 111 */           String newName = "";
/*    */ 
/* 113 */           fileNames.put("newName", newName);
/*    */ 
/* 115 */           fileNames.put("fileSize", "");
/*    */ 
/* 119 */           tpmFilePathName = base + newName;
/*    */ 
/* 121 */           item.write(new File(tpmFilePathName));
/*    */ 
/* 125 */           BufferedImage bufImg = ImageIO.read(new File(tpmFilePathName));
/*    */ 
/* 131 */           out.print(newName + "^" + bufImg.getHeight() + "^" + bufImg.getWidth());
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/*    */     }
/*    */     catch (FileUploadException e)
/*    */     {
/* 144 */       out.print(-1);
/*    */ 
/* 146 */       e.printStackTrace();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 150 */       out.print(-1);
/*    */ 
/* 152 */       e.printStackTrace();
/*    */     }
/*    */     finally
/*    */     {
/* 156 */       out.flush();
/*    */ 
/* 158 */       out.close();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.servlet.UploadTmpPhotoServlet
 * JD-Core Version:    0.6.2
 */