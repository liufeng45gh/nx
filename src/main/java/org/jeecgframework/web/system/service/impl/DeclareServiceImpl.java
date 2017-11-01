/*    */ package org.jeecgframework.web.system.service.impl;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.common.dao.ICommonDao;
/*    */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*    */ import org.jeecgframework.web.system.pojo.base.TSAttachment;
/*    */ import org.jeecgframework.web.system.service.DeclareService;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service("declareService")
/*    */ @Transactional
/*    */ public class DeclareServiceImpl extends CommonServiceImpl
/*    */   implements DeclareService
/*    */ {
/*    */   public List<TSAttachment> getAttachmentsByCode(String businessKey, String description)
/*    */   {
/* 19 */     String hql = "from TSAttachment t where t.businessKey='" + businessKey + "' and t.description='" + description + "'";
/* 20 */     return this.commonDao.findByQueryString(hql);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.DeclareServiceImpl
 * JD-Core Version:    0.6.2
 */