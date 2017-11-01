/*     */ package ssb.warmline.business.entity.mobileloginlog;
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
/*     */ @Table(name="mobile_login_log", schema="")
/*     */ public class MobileLoginLogEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="用户名")
/*     */   private String username;
/*     */ 
/*     */   @Excel(name="渠道名称")
/*     */   private String departName;
/*     */ 
/*     */   @Excel(name="渠道id")
/*     */   private String departid;
/*     */ 
/*     */   @Excel(name="ip地址")
/*     */   private String ip;
/*     */ 
/*     */   @Excel(name="登录时间", format="yyyy-MM-dd")
/*     */   private Date loginTime;
/*     */ 
/*     */   @Excel(name="来源")
/*     */   private String userAgent;
/*     */ 
/*     */   @Excel(name="真实姓名")
/*     */   private String realname;
/*     */ 
/*     */   @Excel(name="agentType")
/*     */   private String agentType;
/*     */ 
/*     */   @Excel(name="appType")
/*     */   private String appType;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=36)
/*     */   public String getId()
/*     */   {
/*  72 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  80 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="USERNAME", nullable=true, length=255)
/*     */   public String getUsername()
/*     */   {
/*  88 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/*  96 */     this.username = username;
/*     */   }
/*     */ 
/*     */   @Column(name="DEPART_NAME", nullable=true, length=255)
/*     */   public String getDepartName()
/*     */   {
/* 104 */     return this.departName;
/*     */   }
/*     */ 
/*     */   public void setDepartName(String departName)
/*     */   {
/* 112 */     this.departName = departName;
/*     */   }
/*     */ 
/*     */   @Column(name="DEPARTID", nullable=true, length=255)
/*     */   public String getDepartid()
/*     */   {
/* 120 */     return this.departid;
/*     */   }
/*     */ 
/*     */   public void setDepartid(String departid)
/*     */   {
/* 128 */     this.departid = departid;
/*     */   }
/*     */ 
/*     */   @Column(name="IP", nullable=true, length=255)
/*     */   public String getIp()
/*     */   {
/* 136 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 144 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   @Column(name="LOGIN_TIME", nullable=true)
/*     */   public Date getLoginTime()
/*     */   {
/* 152 */     return this.loginTime;
/*     */   }
/*     */ 
/*     */   public void setLoginTime(Date loginTime)
/*     */   {
/* 160 */     this.loginTime = loginTime;
/*     */   }
/*     */ 
/*     */   @Column(name="USER_AGENT", nullable=true, length=255)
/*     */   public String getUserAgent()
/*     */   {
/* 168 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */   public void setUserAgent(String userAgent)
/*     */   {
/* 176 */     this.userAgent = userAgent;
/*     */   }
/*     */ 
/*     */   @Column(name="REALNAME", nullable=true, length=255)
/*     */   public String getRealname()
/*     */   {
/* 184 */     return this.realname;
/*     */   }
/*     */ 
/*     */   public void setRealname(String realname)
/*     */   {
/* 192 */     this.realname = realname;
/*     */   }
/*     */ 
/*     */   @Column(name="AGENT_TYPE", nullable=true, length=255)
/*     */   public String getAgentType()
/*     */   {
/* 200 */     return this.agentType;
/*     */   }
/*     */ 
/*     */   public void setAgentType(String agentType)
/*     */   {
/* 208 */     this.agentType = agentType;
/*     */   }
/*     */ 
/*     */   @Column(name="APP_TYPE", nullable=true, length=255)
/*     */   public String getAppType()
/*     */   {
/* 216 */     return this.appType;
/*     */   }
/*     */ 
/*     */   public void setAppType(String appType)
/*     */   {
/* 224 */     this.appType = appType;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.mobileloginlog.MobileLoginLogEntity
 * JD-Core Version:    0.6.2
 */