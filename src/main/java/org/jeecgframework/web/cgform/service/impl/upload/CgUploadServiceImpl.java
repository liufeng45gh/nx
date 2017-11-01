/*    */ package org.jeecgframework.web.cgform.service.impl.upload;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.core.util.ContextHolderUtils;
/*    */ import org.jeecgframework.core.util.FileUtils;
/*    */ import org.jeecgframework.web.cgform.dao.upload.CgFormUploadDao;
/*    */ import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
/*    */ import org.jeecgframework.web.cgform.service.upload.CgUploadServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("cgUploadService")
/*    */ @Transactional
/*    */ public class CgUploadServiceImpl extends CommonServiceImpl
/*    */   implements CgUploadServiceI
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CgFormUploadDao cgFormUploadDao;
/*    */ 
/*    */   public void deleteFile(CgUploadEntity file)
/*    */   {
/* 23 */     String sql = "select * from t_s_attachment where id = ?";
/* 24 */     Map attachmentMap = this.commonDao.findOneForJdbc(sql, new Object[] { file.getId() });
/*    */ 
/* 26 */     String realpath = (String)attachmentMap.get("realpath");
/* 27 */     String fileName = FileUtils.getFilePrefix2(realpath);
/*    */ 
/* 30 */     String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
/* 31 */     FileUtils.delete(realPath + realpath);
/* 32 */     FileUtils.delete(realPath + fileName + ".pdf");
/* 33 */     FileUtils.delete(realPath + fileName + ".swf");
/*    */ 
/* 35 */     this.commonDao.delete(file);
/*    */   }
/*    */ 
/*    */   public void writeBack(String cgFormId, String cgFormName, String cgFormField, String fileId, String fileUrl)
/*    */   {
/*    */     try {
/* 41 */       this.cgFormUploadDao.updateBackFileInfo(cgFormId, cgFormName, cgFormField, fileId, fileUrl);
/*    */     } catch (Exception e) {
/* 43 */       e.printStackTrace();
/* 44 */       throw new RuntimeException("文件上传失败：" + e.getMessage());
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.upload.CgUploadServiceImpl
 * JD-Core Version:    0.6.2
 */