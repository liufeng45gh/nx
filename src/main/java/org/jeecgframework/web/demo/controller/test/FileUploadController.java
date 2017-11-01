/*     */ package org.jeecgframework.web.demo.controller.test;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.util.FileUtils;
/*     */ import org.jeecgframework.web.demo.entity.test.FileMeta;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.util.FileCopyUtils;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/fileUploadController"})
/*     */ public class FileUploadController extends BaseController
/*     */ {
/*  38 */   private static final Logger logger = Logger.getLogger(FileUploadController.class);
/*     */ 
/*  43 */   private static LinkedList<FileMeta> files = new LinkedList();
/*     */ 
/*  47 */   FileMeta fileMeta = null;
/*     */ 
/*     */   @RequestMapping(params={"fileUploadSample"})
/*     */   public ModelAndView webOfficeSample(HttpServletRequest request)
/*     */   {
/*  59 */     return new ModelAndView("jeecg/demo/test/fileUploadSample");
/*     */   }
/*     */   @RequestMapping(params={"upload"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
/*  65 */     logger.info("upload-》1. build an iterator");
/*  66 */     Iterator itr = request.getFileNames();
/*  67 */     MultipartFile mpf = null;
/*  68 */     logger.info("upload-》2. get each file");
/*  69 */     while (itr.hasNext()) {
/*  70 */       logger.info("upload-》2.1 get next MultipartFile");
/*  71 */       mpf = request.getFile((String)itr.next());
/*  72 */       logger.info(mpf.getOriginalFilename() + " uploaded! " + files.size());
/*     */ 
/*  74 */       logger.info("2.2 if files > 10 remove the first from the list");
/*  75 */       if (files.size() >= 10)
/*  76 */         files.pop();
/*  77 */       logger.info("2.3 create new fileMeta");
/*  78 */       this.fileMeta = new FileMeta();
/*  79 */       this.fileMeta.setFileName(mpf.getOriginalFilename());
/*  80 */       this.fileMeta.setFileSize(mpf.getSize() / 1024L + " Kb");
/*  81 */       this.fileMeta.setFileType(mpf.getContentType());
/*     */       try {
/*  83 */         this.fileMeta.setBytes(mpf.getBytes());
/*  84 */         String path = "upload/";
/*  85 */         String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + path;
/*  86 */         logger.info("upload-》文件的硬盘真实路径" + realPath);
/*  87 */         String savePath = realPath + mpf.getOriginalFilename();
/*  88 */         logger.info("upload-》文件保存全路径" + savePath);
/*  89 */         FileCopyUtils.copy(mpf.getBytes(), new File(savePath));
/*  90 */         logger.info("copy file to local disk (make sure the path e.g. D:/temp/files exists)");
/*     */       } catch (IOException e) {
/*  92 */         e.printStackTrace();
/*     */       }
/*  94 */       logger.info("2.4 add to files");
/*  95 */       files.add(this.fileMeta);
/*  96 */       logger.info("success uploaded=" + files.size());
/*     */     }
/*     */ 
/* 100 */     return files;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"view"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void get(HttpServletResponse response, String index) {
/* 105 */     logger.info("get =》uploaded=" + files.size());
/* 106 */     FileMeta getFile = (FileMeta)files.get(Integer.parseInt(index));
/*     */     try {
/* 108 */       response.setContentType(getFile.getFileType());
/* 109 */       String fileName = StringUtils.trim(getFile.getFileName());
/* 110 */       logger.info("fileUploadController->get—>下载文件名：" + fileName);
/* 111 */       String formatFileName = FileUtils.encodingFileName(fileName);
/* 112 */       logger.info("fileUploadController->get—>处理中文乱码及文件名有空格：" + fileName);
/* 113 */       response.setHeader("Content-disposition", "attachment; filename=\"" + formatFileName + "\"");
/* 114 */       FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
/*     */     } catch (IOException e) {
/* 116 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.controller.test.FileUploadController
 * JD-Core Version:    0.6.2
 */