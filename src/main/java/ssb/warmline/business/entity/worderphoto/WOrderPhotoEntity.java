/*     */ package ssb.warmline.business.entity.worderphoto;
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
/*     */ @Table(name="w_order_photo", schema="")
/*     */ public class WOrderPhotoEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String orderId;
/*     */ 
/*     */   @Excel(name="photoName")
/*     */   private String photoName;
/*     */ 
/*     */   @Excel(name="照片路径")
/*     */   private String photoUrl;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String uid;
/*     */ 
/*     */   @Excel(name="照片类型")
/*     */   private String photoType;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  72 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="ORDER_ID", nullable=true, length=36)
/*     */   public String getOrderId()
/*     */   {
/*  80 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId)
/*     */   {
/*  88 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_NAME", nullable=true, length=255)
/*     */   public String getPhotoName()
/*     */   {
/*  96 */     return this.photoName;
/*     */   }
/*     */ 
/*     */   public void setPhotoName(String photoName)
/*     */   {
/* 104 */     this.photoName = photoName;
/*     */   }
/*     */ 
/*     */   @Column(name="PHOTO_URL", nullable=true, length=500)
/*     */   public String getPhotoUrl()
/*     */   {
/* 112 */     return this.photoUrl;
/*     */   }
/*     */ 
/*     */   public void setPhotoUrl(String photoUrl)
/*     */   {
/* 120 */     this.photoUrl = photoUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_TIME", nullable=true)
/*     */   public Date getCreateTime()
/*     */   {
/* 128 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 136 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   @Column(name="uid", nullable=true, length=50)
/*     */   public String getUid()
/*     */   {
/* 142 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(String uid) {
/* 146 */     this.uid = uid;
/*     */   }
/*     */   @Column(name="photo_type", nullable=true, length=100)
/*     */   public String getPhotoType() {
/* 150 */     return this.photoType;
/*     */   }
/*     */ 
/*     */   public void setPhotoType(String photoType) {
/* 154 */     this.photoType = photoType;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.worderphoto.WOrderPhotoEntity
 * JD-Core Version:    0.6.2
 */