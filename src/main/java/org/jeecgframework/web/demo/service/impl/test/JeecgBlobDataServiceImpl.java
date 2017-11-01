/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.sql.Blob;
/*    */ import org.hibernate.LobHelper;
/*    */ import org.hibernate.Session;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.demo.entity.test.JeecgBlobDataEntity;
/*    */ import org.jeecgframework.web.demo.service.test.JeecgBlobDataServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ @Service("jeecgBlobDataService")
/*    */ @Transactional
/*    */ public class JeecgBlobDataServiceImpl extends CommonServiceImpl
/*    */   implements JeecgBlobDataServiceI
/*    */ {
/*    */   public void saveObj(String documentTitle, MultipartFile file)
/*    */   {
/* 21 */     JeecgBlobDataEntity obj = new JeecgBlobDataEntity();
/* 22 */     LobHelper lobHelper = this.commonDao.getSession().getLobHelper();
/*    */     try
/*    */     {
/* 25 */       Blob data = lobHelper.createBlob(file.getInputStream(), 0L);
/* 26 */       obj.setAttachmentcontent(data);
/*    */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     }
/* 30 */     obj.setAttachmenttitle(documentTitle);
/* 31 */     String sFileName = file.getOriginalFilename();
/* 32 */     int iPos = sFileName.lastIndexOf('.');
/* 33 */     if (iPos >= 0) {
/* 34 */       obj.setExtend(sFileName.substring(iPos + 1));
/*    */     }
/* 36 */     super.save(obj);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.JeecgBlobDataServiceImpl
 * JD-Core Version:    0.6.2
 */