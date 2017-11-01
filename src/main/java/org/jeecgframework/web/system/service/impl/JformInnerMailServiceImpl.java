/*     */ package org.jeecgframework.web.system.service.impl;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jeecgframework.core.common.dao.ICommonDao;
/*     */ import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
/*     */ import org.jeecgframework.core.util.ContextHolderUtils;
/*     */ import org.jeecgframework.core.util.FileUtils;
/*     */ import org.jeecgframework.web.system.pojo.base.JformInnerMailAttach;
/*     */ import org.jeecgframework.web.system.pojo.base.JformInnerMailEntity;
/*     */ import org.jeecgframework.web.system.service.JformInnerMailServiceI;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service("jformInnerMailService")
/*     */ @Transactional
/*     */ public class JformInnerMailServiceImpl extends CommonServiceImpl
/*     */   implements JformInnerMailServiceI
/*     */ {
/*     */   public void deleteFile(JformInnerMailAttach file)
/*     */   {
/*  26 */     String sql = "select * from t_s_attachment where id = ?";
/*  27 */     Map attachmentMap = this.commonDao.findOneForJdbc(sql, new Object[] { file.getId() });
/*     */ 
/*  29 */     String realpath = (String)attachmentMap.get("realpath");
/*  30 */     String fileName = FileUtils.getFilePrefix2(realpath);
/*     */ 
/*  33 */     String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
/*  34 */     FileUtils.delete(realPath + realpath);
/*  35 */     FileUtils.delete(realPath + fileName + ".pdf");
/*  36 */     FileUtils.delete(realPath + fileName + ".swf");
/*     */ 
/*  38 */     this.commonDao.delete(file);
/*     */   }
/*     */ 
/*     */   public <T> void delete(T entity) {
/*  42 */     super.delete(entity);
/*     */ 
/*  44 */     doDelSql((JformInnerMailEntity)entity);
/*     */   }
/*     */ 
/*     */   public <T> Serializable save(T entity) {
/*  48 */     Serializable t = super.save(entity);
/*     */ 
/*  50 */     doAddSql((JformInnerMailEntity)entity);
/*  51 */     return t;
/*     */   }
/*     */ 
/*     */   public <T> void saveOrUpdate(T entity) {
/*  55 */     super.saveOrUpdate(entity);
/*     */ 
/*  57 */     doUpdateSql((JformInnerMailEntity)entity);
/*     */   }
/*     */ 
/*     */   public boolean doAddSql(JformInnerMailEntity t)
/*     */   {
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doUpdateSql(JformInnerMailEntity t)
/*     */   {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean doDelSql(JformInnerMailEntity t)
/*     */   {
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   public String replaceVal(String sql, JformInnerMailEntity t)
/*     */   {
/*  91 */     sql = sql.replace("#{id}", String.valueOf(t.getId()));
/*  92 */     sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
/*  93 */     sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
/*  94 */     sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
/*  95 */     sql = sql.replace("#{title}", String.valueOf(t.getTitle()));
/*  96 */     sql = sql.replace("#{attachment}", String.valueOf(t.getAttachment()));
/*  97 */     sql = sql.replace("#{content}", String.valueOf(t.getContent()));
/*  98 */     sql = sql.replace("#{status}", String.valueOf(t.getStatus()));
/*  99 */     sql = sql.replace("#{receiver_names}", String.valueOf(t.getReceiverNames()));
/* 100 */     sql = sql.replace("#{receiver_ids}", String.valueOf(t.getReceiverIds()));
/* 101 */     sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
/* 102 */     return sql;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.service.impl.JformInnerMailServiceImpl
 * JD-Core Version:    0.6.2
 */