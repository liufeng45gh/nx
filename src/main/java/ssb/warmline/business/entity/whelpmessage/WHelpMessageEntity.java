/*     */ package ssb.warmline.business.entity.whelpmessage;
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
/*     */ @Table(name="w_help_message", schema="")
/*     */ public class WHelpMessageEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="求助人id")
/*     */   private String seekHelpPeopleId;
/*     */ 
/*     */   @Excel(name="求助人")
/*     */   private String seekHelpPeople;
/*     */ 
/*     */   @Excel(name="帮助人id")
/*     */   private String helpPeopleId;
/*     */ 
/*     */   @Excel(name="帮助人")
/*     */   private String helpPeople;
/*     */ 
/*     */   @Excel(name="内容")
/*     */   private String content;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date createDate;
/*     */ 
/*     */   @Excel(name="消息类型 ")
/*     */   private String messageType;
/*     */ 
/*     */   @Excel(name="阅读状态 ")
/*     */   private String readingState;
/*     */ 
/*     */   @Excel(name="订单id ")
/*     */   private String orderid;
/*     */ 
/*     */   @Excel(name="订单类型 ")
/*     */   private String orderType;
/*     */ 
/*     */   @Excel(name="消息类型 seekHelp 求助   help  帮助 ")
/*     */   private String messageState;
/*     */ 
/*     */   @Excel(name="消息状态  只储存  同意申请 拒绝取消 ")
/*     */   private String messageStatus;
/*     */ 
/*     */   @Excel(name="标题")
/*     */   private String title;
/*     */ 
/*     */   @Excel(name="系统消息的版本更新的路径")
/*     */   private String versionPath;
/*     */   private String pushCategory;
/*     */   private String personalInformation;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 100 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="SEEK_HELP_PEOPLE_ID", nullable=true, length=36)
/*     */   public String getSeekHelpPeopleId()
/*     */   {
/* 108 */     return this.seekHelpPeopleId;
/*     */   }
/*     */ 
/*     */   public void setSeekHelpPeopleId(String seekHelpPeopleId)
/*     */   {
/* 116 */     this.seekHelpPeopleId = seekHelpPeopleId;
/*     */   }
/*     */ 
/*     */   @Column(name="SEEK_HELP_PEOPLE", nullable=true, length=50)
/*     */   public String getSeekHelpPeople()
/*     */   {
/* 124 */     return this.seekHelpPeople;
/*     */   }
/*     */ 
/*     */   public void setSeekHelpPeople(String seekHelpPeople)
/*     */   {
/* 132 */     this.seekHelpPeople = seekHelpPeople;
/*     */   }
/*     */ 
/*     */   @Column(name="HELP_PEOPLE_ID", nullable=true, length=36)
/*     */   public String getHelpPeopleId()
/*     */   {
/* 140 */     return this.helpPeopleId;
/*     */   }
/*     */ 
/*     */   public void setHelpPeopleId(String helpPeopleId)
/*     */   {
/* 148 */     this.helpPeopleId = helpPeopleId;
/*     */   }
/*     */ 
/*     */   @Column(name="HELP_PEOPLE", nullable=true, length=50)
/*     */   public String getHelpPeople()
/*     */   {
/* 156 */     return this.helpPeople;
/*     */   }
/*     */ 
/*     */   public void setHelpPeople(String helpPeople)
/*     */   {
/* 164 */     this.helpPeople = helpPeople;
/*     */   }
/*     */ 
/*     */   @Column(name="CONTENT", nullable=true, length=255)
/*     */   public String getContent()
/*     */   {
/* 172 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 180 */     this.content = content;
/*     */   }
/*     */ 
/*     */   @Column(name="CREATE_DATE", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/* 188 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 196 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="MESSAGE_TYPE", nullable=true, length=20)
/*     */   public String getMessageType()
/*     */   {
/* 204 */     return this.messageType;
/*     */   }
/*     */ 
/*     */   public void setMessageType(String messageType)
/*     */   {
/* 212 */     this.messageType = messageType;
/*     */   }
/*     */ 
/*     */   @Column(name="READING_STATE", nullable=true, length=20)
/*     */   public String getReadingState()
/*     */   {
/* 220 */     return this.readingState;
/*     */   }
/*     */ 
/*     */   public void setReadingState(String readingState)
/*     */   {
/* 228 */     this.readingState = readingState;
/*     */   }
/*     */   @Column(name="order_id", nullable=true, length=20)
/*     */   public String getOrderid() {
/* 232 */     return this.orderid;
/*     */   }
/*     */ 
/*     */   public void setOrderid(String orderid) {
/* 236 */     this.orderid = orderid;
/*     */   }
/*     */ 
/*     */   @Column(name="order_type", nullable=true, length=20)
/*     */   public String getOrderType() {
/* 241 */     return this.orderType;
/*     */   }
/*     */   public void setOrderType(String orderType) {
/* 244 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   @Column(name="message_state", nullable=true, length=50)
/*     */   public String getMessageState() {
/* 249 */     return this.messageState;
/*     */   }
/*     */   public void setMessageState(String messageState) {
/* 252 */     this.messageState = messageState;
/*     */   }
/*     */   @Column(name="message_status", nullable=true, length=50)
/*     */   public String getMessageStatus() {
/* 256 */     return this.messageStatus;
/*     */   }
/*     */ 
/*     */   public void setMessageStatus(String messageStatus) {
/* 260 */     this.messageStatus = messageStatus;
/*     */   }
/*     */   @Column(name="version_path", nullable=true, length=500)
/*     */   public String getVersionPath() {
/* 264 */     return this.versionPath;
/*     */   }
/*     */ 
/*     */   public void setVersionPath(String versionPath) {
/* 268 */     this.versionPath = versionPath;
/*     */   }
/*     */   @Column(name="title", nullable=true, length=500)
/*     */   public String getTitle() {
/* 272 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/* 276 */     this.title = title;
/*     */   }
/*     */   @Column(name="push_category", nullable=true, length=500)
/*     */   public String getPushCategory() {
/* 280 */     return this.pushCategory;
/*     */   }
/*     */ 
/*     */   public void setPushCategory(String pushCategory) {
/* 284 */     this.pushCategory = pushCategory;
/*     */   }
/*     */   @Column(name="personal_information", nullable=true, length=1000)
/*     */   public String getPersonalInformation() {
/* 288 */     return this.personalInformation;
/*     */   }
/*     */ 
/*     */   public void setPersonalInformation(String personalInformation) {
/* 292 */     this.personalInformation = personalInformation;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.whelpmessage.WHelpMessageEntity
 * JD-Core Version:    0.6.2
 */