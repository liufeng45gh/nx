/*     */ package ssb.warmline.business.entity.fpushclient;
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
/*     */ @Table(name="f_push_client", schema="")
/*     */ public class FPushClientEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date createTime;
/*     */ 
/*     */   @Excel(name="用户id")
/*     */   private String uid;
/*     */ 
/*     */   @Excel(name="用户名")
/*     */   private String username;
/*     */ 
/*     */   @Excel(name="渠道id")
/*     */   private String departId;
/*     */ 
/*     */   @Excel(name="渠道名称")
/*     */   private String departName;
/*     */ 
/*     */   @Excel(name="令牌")
/*     */   private String token;
/*     */ 
/*     */   @Excel(name="客户端id")
/*     */   private String clientId;
/*     */ 
/*     */   @Excel(name="APPID")
/*     */   private String appId;
/*     */ 
/*     */   @Excel(name="平台标识")
/*     */   private String appType;
/*     */ 
/*     */   @Excel(name="devicetoken")
/*     */   private String devicetoken;
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
/*     */   @Column(name="UID", nullable=true, length=64)
/*     */   public String getUid()
/*     */   {
/* 107 */     return this.uid;
/*     */   }
/*     */ 
/*     */   public void setUid(String uid)
/*     */   {
/* 115 */     this.uid = uid;
/*     */   }
/*     */ 
/*     */   @Column(name="USERNAME", nullable=true, length=64)
/*     */   public String getUsername()
/*     */   {
/* 123 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/* 131 */     this.username = username;
/*     */   }
/*     */ 
/*     */   @Column(name="DEPART_ID", nullable=true, length=64)
/*     */   public String getDepartId()
/*     */   {
/* 139 */     return this.departId;
/*     */   }
/*     */ 
/*     */   public void setDepartId(String departId)
/*     */   {
/* 147 */     this.departId = departId;
/*     */   }
/*     */ 
/*     */   @Column(name="DEPART_NAME", nullable=true, length=64)
/*     */   public String getDepartName()
/*     */   {
/* 155 */     return this.departName;
/*     */   }
/*     */ 
/*     */   public void setDepartName(String departName)
/*     */   {
/* 163 */     this.departName = departName;
/*     */   }
/*     */ 
/*     */   @Column(name="TOKEN", nullable=true, length=64)
/*     */   public String getToken()
/*     */   {
/* 171 */     return this.token;
/*     */   }
/*     */ 
/*     */   public void setToken(String token)
/*     */   {
/* 179 */     this.token = token;
/*     */   }
/*     */ 
/*     */   @Column(name="CLIENT_ID", nullable=true, length=64)
/*     */   public String getClientId()
/*     */   {
/* 187 */     return this.clientId;
/*     */   }
/*     */ 
/*     */   public void setClientId(String clientId)
/*     */   {
/* 195 */     this.clientId = clientId;
/*     */   }
/*     */ 
/*     */   @Column(name="APP_ID", nullable=true, length=32)
/*     */   public String getAppId()
/*     */   {
/* 203 */     return this.appId;
/*     */   }
/*     */ 
/*     */   public void setAppId(String appId)
/*     */   {
/* 211 */     this.appId = appId;
/*     */   }
/*     */ 
/*     */   @Column(name="APP_TYPE", nullable=true, length=255)
/*     */   public String getAppType()
/*     */   {
/* 219 */     return this.appType;
/*     */   }
/*     */ 
/*     */   public void setAppType(String appType)
/*     */   {
/* 227 */     this.appType = appType;
/*     */   }
/*     */ 
/*     */   @Column(name="DEVICETOKEN", nullable=true, length=255)
/*     */   public String getDevicetoken()
/*     */   {
/* 235 */     return this.devicetoken;
/*     */   }
/*     */ 
/*     */   public void setDevicetoken(String devicetoken)
/*     */   {
/* 243 */     this.devicetoken = devicetoken;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.fpushclient.FPushClientEntity
 * JD-Core Version:    0.6.2
 */