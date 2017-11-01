/*     */ package ssb.warmline.business.entity.wcomment;
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
/*     */ @Table(name="w_comment", schema="")
/*     */ public class WCommentEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="评星个数")
/*     */   private String commentStar;
/*     */ 
/*     */   @Excel(name="评论时间", format="yyyy-MM-dd")
/*     */   private Date commentTime;
/*     */ 
/*     */   @Excel(name="评论内容")
/*     */   private String content;
/*     */ 
/*     */   @Excel(name="发布人")
/*     */   private String issuer;
/*     */ 
/*     */   @Excel(name="发布人id")
/*     */   private String issuerId;
/*     */ 
/*     */   @Excel(name="订单id")
/*     */   private String orderId;
/*     */ 
/*     */   @Excel(name="被评论人id")
/*     */   private String criticId;
/*     */ 
/*     */   @Excel(name="被评论人名称")
/*     */   private String criticName;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  69 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  77 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="COMMENT_STAR", nullable=true, length=50)
/*     */   public String getCommentStar()
/*     */   {
/*  85 */     return this.commentStar;
/*     */   }
/*     */ 
/*     */   public void setCommentStar(String commentStar)
/*     */   {
/*  93 */     this.commentStar = commentStar;
/*     */   }
/*     */ 
/*     */   @Column(name="COMMENT_TIME", nullable=true)
/*     */   public Date getCommentTime()
/*     */   {
/* 101 */     return this.commentTime;
/*     */   }
/*     */ 
/*     */   public void setCommentTime(Date commentTime)
/*     */   {
/* 109 */     this.commentTime = commentTime;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=1024)
/*     */   public String getContent()
/*     */   {
/* 117 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 125 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="ISSUER", nullable=true, length=255)
/*     */   public String getIssuer()
/*     */   {
/* 133 */     return this.issuer;
/*     */   }
/*     */ 
/*     */   public void setIssuer(String issuer)
/*     */   {
/* 141 */     this.issuer = issuer;
/*     */   }
/*     */ 
/*     */   @Column(name="ISSUER_ID", nullable=true, length=36)
/*     */   public String getIssuerId()
/*     */   {
/* 149 */     return this.issuerId;
/*     */   }
/*     */ 
/*     */   public void setIssuerId(String issuerId)
/*     */   {
/* 157 */     this.issuerId = issuerId;
/*     */   }
/*     */ 
/*     */   @Column(name="order_id", nullable=true, length=36)
/*     */   public String getOrderId() {
/* 162 */     return this.orderId;
/*     */   }
/*     */ 
/*     */   public void setOrderId(String orderId) {
/* 166 */     this.orderId = orderId;
/*     */   }
/*     */ 
/*     */   @Column(name="critic_id", nullable=true, length=36)
/*     */   public String getCriticId() {
/* 171 */     return this.criticId;
/*     */   }
/*     */ 
/*     */   public void setCriticId(String criticId) {
/* 175 */     this.criticId = criticId;
/*     */   }
/*     */ 
/*     */   @Column(name="critic_name", nullable=true, length=36)
/*     */   public String getCriticName() {
/* 180 */     return this.criticName;
/*     */   }
/*     */ 
/*     */   public void setCriticName(String criticName) {
/* 184 */     this.criticName = criticName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wcomment.WCommentEntity
 * JD-Core Version:    0.6.2
 */