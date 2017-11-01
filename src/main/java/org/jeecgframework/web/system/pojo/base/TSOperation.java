/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_operation")
/*     */ public class TSOperation extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String operationname;
/*     */   private String operationcode;
/*     */   private String operationicon;
/*     */   private Short status;
/*  23 */   private TSIcon TSIcon = new TSIcon();
/*  24 */   private TSFunction TSFunction = new TSFunction();
/*     */   private Short operationType;
/*     */ 
/*     */   @Column(name="operationtype")
/*     */   public Short getOperationType()
/*     */   {
/*  30 */     return this.operationType;
/*     */   }
/*     */ 
/*     */   public void setOperationType(Short operationType) {
/*  34 */     this.operationType = operationType;
/*     */   }
/*     */ 
/*     */   @Column(name="operationname", length=50)
/*     */   public String getOperationname() {
/*  39 */     return this.operationname;
/*     */   }
/*     */ 
/*     */   public void setOperationname(String operationname) {
/*  43 */     this.operationname = operationname;
/*     */   }
/*     */ 
/*     */   @Column(name="operationcode", length=50)
/*     */   public String getOperationcode() {
/*  48 */     return this.operationcode;
/*     */   }
/*     */ 
/*     */   public void setOperationcode(String operationcode) {
/*  52 */     this.operationcode = operationcode;
/*     */   }
/*     */ 
/*     */   @Column(name="operationicon", length=100)
/*     */   public String getOperationicon() {
/*  57 */     return this.operationicon;
/*     */   }
/*     */ 
/*     */   public void setOperationicon(String operationicon) {
/*  61 */     this.operationicon = operationicon;
/*     */   }
/*     */ 
/*     */   @Column(name="status")
/*     */   public Short getStatus() {
/*  66 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short status) {
/*  70 */     this.status = status;
/*     */   }
/*  75 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="iconid")
/*     */   public TSIcon getTSIcon() { return this.TSIcon; }
/*     */ 
/*     */   public void setTSIcon(TSIcon tSIcon)
/*     */   {
/*  79 */     this.TSIcon = tSIcon;
/*     */   }
/*     */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="functionid")
/*     */   public TSFunction getTSFunction() {
/*  85 */     return this.TSFunction;
/*     */   }
/*     */ 
/*     */   public void setTSFunction(TSFunction tSFunction) {
/*  89 */     this.TSFunction = tSFunction;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  94 */     if (this == obj)
/*  95 */       return false;
/*  96 */     if (obj == null)
/*  97 */       return false;
/*  98 */     if (getClass() != obj.getClass())
/*  99 */       return false;
/* 100 */     TSOperation other = (TSOperation)obj;
/* 101 */     if (getId().equals(other.getId())) {
/* 102 */       return true;
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSOperation
 * JD-Core Version:    0.6.2
 */