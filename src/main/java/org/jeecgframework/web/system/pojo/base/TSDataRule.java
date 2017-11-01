/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_data_rule")
/*     */ public class TSDataRule extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String ruleName;
/*     */   private String ruleColumn;
/*     */   private String ruleConditions;
/*     */   private String ruleValue;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date createDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*  67 */   private TSFunction TSFunction = new TSFunction();
/*     */   private Date updateDate;
/*     */ 
/*     */   @Column(name="rule_name", nullable=false, length=32)
/*     */   public String getRuleName()
/*     */   {
/*  74 */     return this.ruleName;
/*     */   }
/*     */   public void setRuleName(String ruleName) {
/*  77 */     this.ruleName = ruleName;
/*     */   }
/*     */   @Column(name="rule_column", nullable=false, length=100)
/*     */   public String getRuleColumn() {
/*  81 */     return this.ruleColumn;
/*     */   }
/*     */   public void setRuleColumn(String ruleColumn) {
/*  84 */     this.ruleColumn = ruleColumn;
/*     */   }
/*     */   @Column(name="rule_conditions", nullable=false, length=100)
/*     */   public String getRuleConditions() {
/*  88 */     return this.ruleConditions;
/*     */   }
/*     */   public void setRuleConditions(String ruleConditions) {
/*  91 */     this.ruleConditions = ruleConditions;
/*     */   }
/*     */ 
/*     */   @Column(name="rule_value", nullable=false, length=100)
/*     */   public String getRuleValue() {
/*  96 */     return this.ruleValue;
/*     */   }
/*     */   public void setRuleValue(String ruleValue) {
/*  99 */     this.ruleValue = ruleValue;
/*     */   }
/*     */   @Column(name="create_by", nullable=false, length=32)
/*     */   public String getCreateBy() {
/* 103 */     return this.createBy;
/*     */   }
/*     */   public void setCreateBy(String createBy) {
/* 106 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=false, length=32)
/*     */   public String getCreateName() {
/* 111 */     return this.createName;
/*     */   }
/*     */   public void setCreateName(String createName) {
/* 114 */     this.createName = createName;
/*     */   }
/*     */   @Column(name="create_date", nullable=false)
/*     */   public Date getCreateDate() {
/* 118 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/* 121 */     this.createDate = createDate;
/*     */   }
/*     */   @Column(name="update_by", nullable=false, length=32)
/*     */   public String getUpdateBy() {
/* 125 */     return this.updateBy;
/*     */   }
/*     */   public void setUpdateBy(String updateBy) {
/* 128 */     this.updateBy = updateBy;
/*     */   }
/*     */   @Column(name="update_name", nullable=false, length=32)
/*     */   public String getUpdateName() {
/* 132 */     return this.updateName;
/*     */   }
/*     */   public void setUpdateName(String updateName) {
/* 135 */     this.updateName = updateName;
/*     */   }
/*     */   @Column(name="update_date", nullable=false)
/*     */   public Date getUpdateDate() {
/* 139 */     return this.updateDate;
/*     */   }
/*     */   public void setUpdateDate(Date updateDate) {
/* 142 */     this.updateDate = updateDate;
/*     */   }
/* 147 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="functionId")
/*     */   public TSFunction getTSFunction() { return this.TSFunction; }
/*     */ 
/*     */   public void setTSFunction(TSFunction tSFunction)
/*     */   {
/* 151 */     this.TSFunction = tSFunction;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSDataRule
 * JD-Core Version:    0.6.2
 */