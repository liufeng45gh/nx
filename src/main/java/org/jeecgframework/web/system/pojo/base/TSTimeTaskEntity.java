/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.DynamicInsert;
/*     */ import org.hibernate.annotations.DynamicUpdate;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_timetask", schema="")
/*     */ @DynamicUpdate(true)
/*     */ @DynamicInsert(true)
/*     */ public class TSTimeTaskEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String taskId;
/*     */   private String taskDescribe;
/*     */   private String cronExpression;
/*     */   private String isEffect;
/*     */   private String isStart;
/*     */   private Date createDate;
/*     */   private String createBy;
/*     */   private String createName;
/*     */   private Date updateDate;
/*     */   private String updateBy;
/*     */   private String updateName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=32)
/*     */   public String getId()
/*     */   {
/*  62 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  70 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="TASK_ID", nullable=false, length=100)
/*     */   public String getTaskId()
/*     */   {
/*  78 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   public void setTaskId(String taskId)
/*     */   {
/*  86 */     this.taskId = taskId;
/*     */   }
/*     */ 
/*     */   @Column(name="TASK_DESCRIBE", nullable=false, length=50)
/*     */   public String getTaskDescribe()
/*     */   {
/*  94 */     return this.taskDescribe;
/*     */   }
/*     */ 
/*     */   public void setTaskDescribe(String taskDescribe)
/*     */   {
/* 102 */     this.taskDescribe = taskDescribe;
/*     */   }
/*     */ 
/*     */   @Column(name="CRON_EXPRESSION", nullable=false, length=100)
/*     */   public String getCronExpression()
/*     */   {
/* 110 */     return this.cronExpression;
/*     */   }
/*     */ 
/*     */   public void setCronExpression(String cronExpression)
/*     */   {
/* 118 */     this.cronExpression = cronExpression;
/*     */   }
/*     */ 
/*     */   @Column(name="IS_EFFECT", nullable=false, length=1)
/*     */   public String getIsEffect()
/*     */   {
/* 126 */     return this.isEffect;
/*     */   }
/*     */ 
/*     */   public void setIsEffect(String isEffect)
/*     */   {
/* 134 */     this.isEffect = isEffect;
/*     */   }
/*     */ 
/*     */   @Column(name="IS_START", nullable=false, length=1)
/*     */   public String getIsStart()
/*     */   {
/* 142 */     return this.isStart;
/*     */   }
/*     */ 
/*     */   public void setIsStart(String isStart)
/*     */   {
/* 150 */     this.isStart = isStart;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 158 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 166 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_BY", nullable=true, length=32)
/*     */   public String getCreateBy()
/*     */   {
/* 174 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 182 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_NAME", nullable=true, length=32)
/*     */   public String getCreateName()
/*     */   {
/* 190 */     return this.createName;
/*     */   }
/*     */ 
/*     */   public void setCreateName(String createName)
/*     */   {
/* 198 */     this.createName = createName;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_DATE", nullable=true)
/*     */   public Date getUpdateDate()
/*     */   {
/* 206 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 214 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_BY", nullable=true, length=32)
/*     */   public String getUpdateBy()
/*     */   {
/* 222 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 230 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   @Column(name="UPDATE_NAME", nullable=true, length=32)
/*     */   public String getUpdateName()
/*     */   {
/* 238 */     return this.updateName;
/*     */   }
/*     */ 
/*     */   public void setUpdateName(String updateName)
/*     */   {
/* 246 */     this.updateName = updateName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity
 * JD-Core Version:    0.6.2
 */