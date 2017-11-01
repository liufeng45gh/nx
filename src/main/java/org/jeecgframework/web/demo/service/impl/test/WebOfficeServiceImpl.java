/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.sql.Blob;
/*    */ import java.util.Date;
/*    */ import org.hibernate.LobHelper;
/*    */ import org.hibernate.Session;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ import org.jeecgframework.web.demo.entity.test.WebOfficeEntity;
/*    */ import org.jeecgframework.web.demo.service.test.WebOfficeServiceI;
/*    */ import org.springframework.beans.BeanUtils;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ @Service("webOfficeService")
/*    */ @Transactional
/*    */ public class WebOfficeServiceImpl extends CommonServiceImpl
/*    */   implements WebOfficeServiceI
/*    */ {
/*    */   public void saveObj(WebOfficeEntity docObj, MultipartFile file)
/*    */   {
/* 23 */     WebOfficeEntity obj = null;
/* 24 */     if (StringUtil.isNotEmpty(docObj.getId())) {
/* 25 */       obj = (WebOfficeEntity)this.commonDao.getEntity(WebOfficeEntity.class, docObj.getId());
/* 26 */       if (obj != null);
/*    */     }
/*    */     else {
/* 30 */       obj = new WebOfficeEntity();
/* 31 */       BeanUtils.copyProperties(docObj, obj);
/* 32 */       String sFileName = file.getOriginalFilename();
/* 33 */       int iPos = sFileName.lastIndexOf('.');
/* 34 */       if (iPos >= 0) {
/* 35 */         obj.setDoctype(sFileName.substring(iPos + 1));
/*    */       }
/*    */     }
/* 38 */     obj.setDocdate(new Date());
/* 39 */     LobHelper lobHelper = this.commonDao.getSession().getLobHelper();
/*    */     try
/*    */     {
/* 42 */       Blob data = lobHelper.createBlob(file.getInputStream(), 0L);
/* 43 */       obj.setDoccontent(data);
/*    */     } catch (IOException e) {
/* 45 */       e.printStackTrace();
/*    */     }
/* 47 */     super.save(obj);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.WebOfficeServiceImpl
 * JD-Core Version:    0.6.2
 */