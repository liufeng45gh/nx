/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.Proxy;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_function")
/*     */ @Proxy(lazy=false)
/*     */ public class TSFunction extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private TSFunction TSFunction;
/*     */   private String functionName;
/*     */   private Short functionLevel;
/*     */   private String functionUrl;
/*     */   private Short functionIframe;
/*     */   private String functionOrder;
/*     */   private Short functionType;
/*  37 */   private TSIcon TSIcon = new TSIcon();
/*     */   private TSIcon TSIconDesk;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/* 186 */   private List<TSFunction> TSFunctions = new ArrayList();
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/*  66 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  74 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/*  82 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  90 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/*  98 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 106 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 114 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 122 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 130 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 138 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 146 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 154 */     this.updateName = updateName;
/*     */   }
/*     */   public boolean hasSubFunction(Map<Integer, List<TSFunction>> map) {
/* 157 */     if (map.containsKey(Integer.valueOf(getFunctionLevel().shortValue() + 1))) {
/* 158 */       return hasSubFunction((List)map.get(Integer.valueOf(getFunctionLevel().shortValue() + 1)));
/*     */     }
/* 160 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean hasSubFunction(List<TSFunction> functions) {
/* 164 */     for (TSFunction f : functions) {
/* 165 */       if (f.getTSFunction().getId().equals(getId())) {
/* 166 */         return true;
/*     */       }
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="desk_iconid")
/*     */   public TSIcon getTSIconDesk()
/*     */   {
/* 179 */     return this.TSIconDesk;
/*     */   }
/*     */   public void setTSIconDesk(TSIcon TSIconDesk) {
/* 182 */     this.TSIconDesk = TSIconDesk;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="iconid")
/*     */   public TSIcon getTSIcon()
/*     */   {
/* 192 */     return this.TSIcon;
/*     */   }
/*     */   public void setTSIcon(TSIcon tSIcon) {
/* 195 */     this.TSIcon = tSIcon;
/*     */   }
/*     */ 
/*     */   @ManyToOne(fetch=FetchType.EAGER)
/*     */   @JoinColumn(name="parentfunctionid")
/*     */   public TSFunction getTSFunction() {
/* 202 */     return this.TSFunction;
/*     */   }
/*     */ 
/*     */   public void setTSFunction(TSFunction TSFunction) {
/* 206 */     this.TSFunction = TSFunction;
/*     */   }
/*     */ 
/*     */   @Column(name="functionname", nullable=false, length=50)
/*     */   public String getFunctionName() {
/* 211 */     return this.functionName;
/*     */   }
/*     */ 
/*     */   public void setFunctionName(String functionName) {
/* 215 */     this.functionName = functionName;
/*     */   }
/*     */ 
/*     */   @Column(name="functionlevel")
/*     */   public Short getFunctionLevel() {
/* 220 */     return this.functionLevel;
/*     */   }
/*     */ 
/*     */   public void setFunctionLevel(Short functionLevel) {
/* 224 */     this.functionLevel = functionLevel;
/*     */   }
/*     */ 
/*     */   @Column(name="functiontype")
/*     */   public Short getFunctionType() {
/* 229 */     return this.functionType;
/*     */   }
/*     */ 
/*     */   public void setFunctionType(Short functionType) {
/* 233 */     this.functionType = functionType;
/*     */   }
/*     */ 
/*     */   @Column(name="functionurl", length=100)
/*     */   public String getFunctionUrl() {
/* 238 */     return this.functionUrl;
/*     */   }
/*     */ 
/*     */   public void setFunctionUrl(String functionUrl) {
/* 242 */     this.functionUrl = functionUrl;
/*     */   }
/*     */   @Column(name="functionorder")
/*     */   public String getFunctionOrder() {
/* 246 */     return this.functionOrder;
/*     */   }
/*     */ 
/*     */   public void setFunctionOrder(String functionOrder) {
/* 250 */     this.functionOrder = functionOrder;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TSFunction")
/*     */   public List<TSFunction> getTSFunctions() {
/* 254 */     return this.TSFunctions;
/*     */   }
/*     */   public void setTSFunctions(List<TSFunction> TSFunctions) {
/* 257 */     this.TSFunctions = TSFunctions;
/*     */   }
/*     */   @Column(name="functioniframe")
/*     */   public Short getFunctionIframe() {
/* 261 */     return this.functionIframe;
/*     */   }
/*     */   public void setFunctionIframe(Short functionIframe) {
/* 264 */     this.functionIframe = functionIframe;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSFunction
 * JD-Core Version:    0.6.2
 */