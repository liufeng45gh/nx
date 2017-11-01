/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_role")
/*     */ public class TSRole extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */ 
/*     */   @Excel(name="角色名称")
/*     */   private String roleName;
/*     */ 
/*     */   @Excel(name="角色编码")
/*     */   private String roleCode;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */ 
/*     */   @Column(name="rolename", nullable=false, length=100)
/*     */   public String getRoleName()
/*     */   {
/*  41 */     return this.roleName;
/*     */   }
/*     */ 
/*     */   public void setRoleName(String roleName) {
/*  45 */     this.roleName = roleName;
/*     */   }
/*     */   @Column(name="rolecode", length=10)
/*     */   public String getRoleCode() {
/*  49 */     return this.roleCode;
/*     */   }
/*     */ 
/*     */   public void setRoleCode(String roleCode) {
/*  53 */     this.roleCode = roleCode;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/*  61 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  69 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="create_by", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/*  77 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  85 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="create_name", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/*  93 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 101 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="update_date", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 109 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 117 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="update_by", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 125 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 133 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="update_name", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 141 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 149 */     this.updateName = updateName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSRole
 * JD-Core Version:    0.6.2
 */