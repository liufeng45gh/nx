/*     */ package ssb.warmline.business.entity.worderphotomain;
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
/*     */ @Table(name="w_order_photo_main", schema="")
/*     */ public class WOrderPhotoMainEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="照片名称")
/*     */   private String photoName;
/*     */ 
/*     */   @Excel(name="照片路径")
/*     */   private String photoUrl;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String orderId;
/*     */ 
/*     */   @Excel(name="用户id")
/*     */   private String uid;
/*     */ 
/*     */   @Excel(name="创建日期", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="照片类型（0 头像 和 1 普通）")
/*     */   private String photoType;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  63 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  71 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_NAME", nullable=true, length=255)
/*     */   public String getPhotoName()
/*     */   {
/*  79 */     return this.photoName;
/*     */   }
/*     */ 
/*     */   public void setPhotoName(String photoName)
/*     */   {
/*  87 */     this.photoName = photoName;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_URL", nullable=true, length=255)
/*     */   public String getPhotoUrl()
/*     */   {
/*  95 */     return this.photoUrl;
/*     */   }
/*     */ 
/*     */   public void setPhotoUrl(String photoUrl)
/*     */   {
/* 103 */     this.photoUrl = photoUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_ID", nullable=true, length=36)
/*     */   public String getOrderId()
/*     */   {
/* 111 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId)
/*     */   {
/* 119 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   @Column(name="UID", nullable=true, length=36)
/*     */   public String getUid()
/*     */   {
/* 127 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(String uid)
/*     */   {
/* 135 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 143 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 151 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_TYPE", nullable=true, length=255)
/*     */   public String getPhotoType()
/*     */   {
/* 159 */     return this.photoType;
/*     */   }
/*     */ 
/*     */   public void setPhotoType(String photoType)
/*     */   {
/* 167 */     this.photoType = photoType;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity
 * JD-Core Version:    0.6.2
 */