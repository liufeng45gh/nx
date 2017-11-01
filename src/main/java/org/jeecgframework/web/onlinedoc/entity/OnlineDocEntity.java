/*     */ package org.jeecgframework.web.onlinedoc.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_online_doc", schema="")
/*     */ public class OnlineDocEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String createName;
/*     */   private String createBy;
/*     */   private Date createDate;
/*     */   private String updateName;
/*     */   private String updateBy;
/*     */   private Date updateDate;
/*     */   private String sysOrgCode;
/*     */   private String sysCompanyCode;
/*     */   private String bpmStatus;
/*     */   private String oldName;
/*     */   private String newName;
/*     */ 
/*     */   @Excel(name="描述")
/*     */   private String description;
/*     */ 
/*     */   @Excel(name="分类节点")
/*     */   private String treeNode;
/*     */   private String path;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  66 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=50)
/*     */   public String getCreateName()
/*     */   {
/*  82 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/*  90 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=50)
/*     */   public String getCreateBy()
/*     */   {
/*  98 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 106 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true, length=20)
/*     */   public Date getCreateDate()
/*     */   {
/* 114 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 122 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=50)
/*     */   public String getUpdateName()
/*     */   {
/* 130 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 138 */     this.updateName = updateName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=50)
/*     */   public String getUpdateBy()
/*     */   {
/* 146 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 154 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true, length=20)
/*     */   public Date getUpdateDate()
/*     */   {
/* 162 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 170 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_ORG_CODE", nullable=true, length=50)
/*     */   public String getSysOrgCode()
/*     */   {
/* 178 */     return this.sysOrgCode;
/*     */   }
/*     */ 
/*     */   public void setSysOrgCode(String sysOrgCode)
/*     */   {
/* 186 */     this.sysOrgCode = sysOrgCode;
/*     */   }
/*     */ 
/*     */   @Column(name="SYS_COMPANY_CODE", nullable=true, length=50)
/*     */   public String getSysCompanyCode()
/*     */   {
/* 194 */     return this.sysCompanyCode;
/*     */   }
/*     */ 
/*     */   public void setSysCompanyCode(String sysCompanyCode)
/*     */   {
/* 202 */     this.sysCompanyCode = sysCompanyCode;
/*     */   }
/*     */ 
/*     */   @Column(name="BPM_STATUS", nullable=true, length=32)
/*     */   public String getBpmStatus()
/*     */   {
/* 210 */     return this.bpmStatus;
/*     */   }
/*     */ 
/*     */   public void setBpmStatus(String bpmStatus)
/*     */   {
/* 218 */     this.bpmStatus = bpmStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="OLD_NAME", nullable=true, length=50)
/*     */   public String getOldName()
/*     */   {
/* 226 */     return this.oldName;
/*     */   }
/*     */ 
/*     */   public void setOldName(String oldName)
/*     */   {
/* 234 */     this.oldName = oldName;
/*     */   }
/*     */ 
/*     */   @Column(name="NEW_NAME", nullable=true, length=50)
/*     */   public String getNewName()
/*     */   {
/* 242 */     return this.newName;
/*     */   }
/*     */ 
/*     */   public void setNewName(String newName)
/*     */   {
/* 250 */     this.newName = newName;
/*     */   }
/*     */ 
/*     */   @Column(name="DESCRIPTION", nullable=true, length=200)
/*     */   public String getDescription()
/*     */   {
/* 258 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 266 */     this.description = description;
/*     */   }
/*     */ 
/*     */   @Column(name="PATH", nullable=true, length=200)
/*     */   public String getPath()
/*     */   {
/* 274 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path)
/*     */   {
/* 282 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public void setTreeNode(String treeNode) {
/* 286 */     this.treeNode = treeNode;
/*     */   }
/*     */ 
/*     */   @Column(name="TREE_NODE", nullable=true, length=200)
/*     */   public String getTreeNode() {
/* 291 */     return this.treeNode;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.onlinedoc.entity.OnlineDocEntity
 * JD-Core Version:    0.6.2
 */