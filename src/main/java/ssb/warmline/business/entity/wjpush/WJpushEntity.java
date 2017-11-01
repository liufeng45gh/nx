/*     */ package ssb.warmline.business.entity.wjpush;
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
/*     */ @Table(name="w_jpush", schema="")
/*     */ public class WJpushEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="createTime", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="推送类型")
/*     */   private String jpushType;
/*     */ 
/*     */   @Excel(name="内容")
/*     */   private String content;
/*     */ 
/*     */   @Excel(name="发送状态")
/*     */   private String sendStatus;
/*     */ 
/*     */   @Excel(name="页面去向")
/*     */   private String pageWhereabouts;
/*     */ 
/*     */   @Excel(name="主标题")
/*     */   private String mainTitle;
/*     */ 
/*     */   @Excel(name="标题")
/*     */   private String title;
/*     */ 
/*     */   @Excel(name="照片路径")
/*     */   private String photoPath;
/*     */ 
/*     */   @Excel(name="editLink")
/*     */   private String editLink;
/*     */ 
/*     */   @Excel(name="推送内容")
/*     */   private String text;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  75 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  83 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/*  91 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/*  99 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="JPUSH_TYPE", nullable=true, length=50)
/*     */   public String getJpushType()
/*     */   {
/* 107 */     return this.jpushType;
/*     */   }
/*     */ 
/*     */   public void setJpushType(String jpushType)
/*     */   {
/* 115 */     this.jpushType = jpushType;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=500)
/*     */   public String getContent()
/*     */   {
/* 123 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 131 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="SEND_STATUS", nullable=true, length=255)
/*     */   public String getSendStatus()
/*     */   {
/* 139 */     return this.sendStatus;
/*     */   }
/*     */ 
/*     */   public void setSendStatus(String sendStatus)
/*     */   {
/* 147 */     this.sendStatus = sendStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="PAGE_WHEREABOUTS", nullable=true, length=255)
/*     */   public String getPageWhereabouts()
/*     */   {
/* 155 */     return this.pageWhereabouts;
/*     */   }
/*     */ 
/*     */   public void setPageWhereabouts(String pageWhereabouts)
/*     */   {
/* 163 */     this.pageWhereabouts = pageWhereabouts;
/*     */   }
/*     */ 
/*     */   @Column(name="MAIN_TITLE", nullable=true, length=255)
/*     */   public String getMainTitle()
/*     */   {
/* 171 */     return this.mainTitle;
/*     */   }
/*     */ 
/*     */   public void setMainTitle(String mainTitle)
/*     */   {
/* 179 */     this.mainTitle = mainTitle;
/*     */   }
/*     */ 
/*     */   @Column(name="TITLE", nullable=true, length=255)
/*     */   public String getTitle()
/*     */   {
/* 187 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 195 */     this.title = title;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_PATH", nullable=true, length=255)
/*     */   public String getPhotoPath()
/*     */   {
/* 203 */     return this.photoPath;
/*     */   }
/*     */ 
/*     */   public void setPhotoPath(String photoPath)
/*     */   {
/* 211 */     this.photoPath = photoPath;
/*     */   }
/*     */ 
/*     */   @Column(name="EDIT_LINK", nullable=true, length=255)
/*     */   public String getEditLink()
/*     */   {
/* 219 */     return this.editLink;
/*     */   }
/*     */ 
/*     */   public void setEditLink(String editLink)
/*     */   {
/* 227 */     this.editLink = editLink;
/*     */   }
/*     */ 
/*     */   @Column(name="TEXT", nullable=true, length=500)
/*     */   public String getText()
/*     */   {
/* 235 */     return this.text;
/*     */   }
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 243 */     this.text = text;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wjpush.WJpushEntity
 * JD-Core Version:    0.6.2
 */