/*     */ package org.jeecgframework.web.cgreport.entity.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="jform_cgreport_head", schema="")
/*     */ public class CgreportConfigHeadEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String cgrSql;
/*     */   private String content;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */   private String dbSource;
/*     */   private String returnValField;
/*     */   private String returnTxtField;
/*     */   private String popRetype;
/*     */ 
/*     */   @Column(name="pop_retype", length=2)
/*     */   public String getPopRetype()
/*     */   {
/*  60 */     return this.popRetype;
/*     */   }
/*     */   public void setPopRetype(String popRetype) {
/*  63 */     this.popRetype = popRetype;
/*     */   }
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  84 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CODE", nullable=false, length=36)
/*     */   public String getCode()
/*     */   {
/*  92 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 100 */     this.code = code;
/*     */   }
/*     */ 
/*     */   @Column(name="NAME", nullable=false, length=100)
/*     */   public String getName()
/*     */   {
/* 108 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 116 */     this.name = name;
/*     */   }
/*     */ 
/*     */   @Column(name="CGR_SQL", nullable=false, length=2000)
/*     */   public String getCgrSql()
/*     */   {
/* 124 */     return this.cgrSql;
/*     */   }
/*     */ 
/*     */   public void setCgrSql(String cgrSql)
/*     */   {
/* 132 */     this.cgrSql = cgrSql;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=false, length=1000)
/*     */   public String getContent()
/*     */   {
/* 140 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 148 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 156 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 164 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/* 172 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 180 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 188 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 196 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 204 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 212 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 220 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 228 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 236 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 244 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="db_source", length=36)
/*     */   public String getDbSource() {
/* 249 */     return this.dbSource;
/*     */   }
/*     */ 
/*     */   public void setDbSource(String dbSource) {
/* 253 */     this.dbSource = dbSource;
/*     */   }
/*     */ 
/*     */   @Column(name="return_val_field", length=100)
/*     */   public String getReturnValField() {
/* 258 */     return this.returnValField;
/*     */   }
/*     */ 
/*     */   public void setReturnValField(String returnValField) {
/* 262 */     this.returnValField = returnValField;
/*     */   }
/*     */   @Column(name="return_txt_field", length=100)
/*     */   public String getReturnTxtField() {
/* 266 */     return this.returnTxtField;
/*     */   }
/*     */ 
/*     */   public void setReturnTxtField(String returnTxtField) {
/* 270 */     this.returnTxtField = returnTxtField;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity
 * JD-Core Version:    0.6.2
 */