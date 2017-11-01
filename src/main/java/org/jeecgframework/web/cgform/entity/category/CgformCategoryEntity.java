/*    */ package org.jeecgframework.web.cgform.entity.category;
/*    */ 
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
/*    */ import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="cgform_category", schema="")
/*    */ public class CgformCategoryEntity extends IdEntity
/*    */ {
/*    */   private CgFormHeadEntity form;
/*    */   private TSCategoryEntity category;
/*    */ 
/*    */   @ManyToOne(targetEntity=CgFormHeadEntity.class)
/*    */   @JoinColumn(name="cgform_id", referencedColumnName="id")
/*    */   public CgFormHeadEntity getForm()
/*    */   {
/* 33 */     return this.form;
/*    */   }
/*    */ 
/*    */   public void setForm(CgFormHeadEntity form) {
/* 37 */     this.form = form;
/*    */   }
/*    */   @ManyToOne(targetEntity=TSCategoryEntity.class)
/*    */   @JoinColumn(name="category_code", referencedColumnName="code")
/*    */   public TSCategoryEntity getCategory() {
/* 43 */     return this.category;
/*    */   }
/*    */ 
/*    */   public void setCategory(TSCategoryEntity category) {
/* 47 */     this.category = category;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.category.CgformCategoryEntity
 * JD-Core Version:    0.6.2
 */