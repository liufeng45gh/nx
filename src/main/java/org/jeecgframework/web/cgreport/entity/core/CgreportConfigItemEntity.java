/*     */ package org.jeecgframework.web.cgreport.entity.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_cgreport_item", schema="")
/*     */ public class CgreportConfigItemEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String fieldName;
/*     */   private Integer orderNum;
/*     */   private String fieldTxt;
/*     */   private String fieldType;
/*     */   private String fieldHref;
/*     */   private String isShow;
/*     */   private String sMode;
/*     */   private String replaceVa;
/*     */   private String dictCode;
/*     */   private String sFlag;
/*     */   private String cgrheadId;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  60 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  68 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_NAME", nullable=true, length=36)
/*     */   public String getFieldName()
/*     */   {
/*  76 */     return this.fieldName;
/*     */   }
/*     */ 
/*     */   public void setFieldName(String fieldName)
/*     */   {
/*  84 */     this.fieldName = fieldName;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_NUM", nullable=true, length=10)
/*     */   public Integer getOrderNum()
/*     */   {
/*  92 */     return this.orderNum;
/*     */   }
/*     */ 
/*     */   public void setOrderNum(Integer orderNum)
/*     */   {
/* 100 */     this.orderNum = orderNum;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_TXT", nullable=true, length=1000)
/*     */   public String getFieldTxt()
/*     */   {
/* 108 */     return this.fieldTxt;
/*     */   }
/*     */ 
/*     */   public void setFieldTxt(String fieldTxt)
/*     */   {
/* 116 */     this.fieldTxt = fieldTxt;
/*     */   }
/*     */ 
/*     */   @Column(name="IS_SHOW", nullable=true, length=5)
/*     */   public String getIsShow()
/*     */   {
/* 125 */     return this.isShow;
/*     */   }
/*     */ 
/*     */   public void setIsShow(String isShow)
/*     */   {
/* 132 */     this.isShow = isShow;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_HREF", nullable=true, length=120)
/*     */   public String getFieldHref()
/*     */   {
/* 140 */     return this.fieldHref;
/*     */   }
/*     */ 
/*     */   public void setFieldHref(String fieldHref)
/*     */   {
/* 147 */     this.fieldHref = fieldHref;
/*     */   }
/*     */ 
/*     */   @Column(name="FIELD_TYPE", nullable=true, length=10)
/*     */   public String getFieldType()
/*     */   {
/* 158 */     return this.fieldType;
/*     */   }
/*     */ 
/*     */   public void setFieldType(String fieldType)
/*     */   {
/* 166 */     this.fieldType = fieldType;
/*     */   }
/*     */ 
/*     */   @Column(name="S_MODE", nullable=true, length=10)
/*     */   public String getSMode()
/*     */   {
/* 174 */     return this.sMode;
/*     */   }
/*     */ 
/*     */   public void setSMode(String sMode)
/*     */   {
/* 182 */     this.sMode = sMode;
/*     */   }
/*     */ 
/*     */   @Column(name="REPLACE_VA", nullable=true, length=36)
/*     */   public String getReplaceVa()
/*     */   {
/* 190 */     return this.replaceVa;
/*     */   }
/*     */ 
/*     */   public void setReplaceVa(String replaceVa)
/*     */   {
/* 198 */     this.replaceVa = replaceVa;
/*     */   }
/*     */ 
/*     */   @Column(name="DICT_CODE", nullable=true, length=36)
/*     */   public String getDictCode()
/*     */   {
/* 206 */     return this.dictCode;
/*     */   }
/*     */ 
/*     */   public void setDictCode(String dictCode)
/*     */   {
/* 214 */     this.dictCode = dictCode;
/*     */   }
/*     */ 
/*     */   @Column(name="S_FLAG", nullable=true, length=2)
/*     */   public String getSFlag()
/*     */   {
/* 222 */     return this.sFlag;
/*     */   }
/*     */ 
/*     */   public void setSFlag(String sFlag)
/*     */   {
/* 230 */     this.sFlag = sFlag;
/*     */   }
/*     */ 
/*     */   @Column(name="CGRHEAD_ID", nullable=true, length=36)
/*     */   public String getCgrheadId()
/*     */   {
/* 238 */     return this.cgrheadId;
/*     */   }
/*     */ 
/*     */   public void setCgrheadId(String cgrheadId)
/*     */   {
/* 246 */     this.cgrheadId = cgrheadId;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity
 * JD-Core Version:    0.6.2
 */