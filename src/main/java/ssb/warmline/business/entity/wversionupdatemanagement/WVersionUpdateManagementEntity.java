/*     */ package ssb.warmline.business.entity.wversionupdatemanagement;
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
/*     */ @Table(name="w_version_update_management", schema="")
/*     */ public class WVersionUpdateManagementEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="创建日期", format="yyyy-MM-dd")
/*     */   private Date createDate;
/*     */ 
/*     */   @Excel(name="版本名称")
/*     */   private String versionName;
/*     */ 
/*     */   @Excel(name="版本号")
/*     */   private Integer versionNumber;
/*     */ 
/*     */   @Excel(name="版本描述")
/*     */   private String versionDescription;
/*     */ 
/*     */   @Excel(name="下载url")
/*     */   private String uploadUrl;
/*     */ 
/*     */   @Excel(name="下载地址")
/*     */   private String downloadUrl;
/*     */ 
/*     */   @Excel(name="是否是当前字段")
/*     */   private String currentField;
/*     */ 
/*     */   @Excel(name="appId")
/*     */   private String appid;
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
/*     */   @Column(name="CREATE_DATE", nullable=true)
/*     */   public Date getCreateDate()
/*     */   {
/*  85 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  93 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="VERSION_NAME", nullable=true, length=255)
/*     */   public String getVersionName()
/*     */   {
/* 101 */     return this.versionName;
/*     */   }
/*     */ 
/*     */   public void setVersionName(String versionName)
/*     */   {
/* 109 */     this.versionName = versionName;
/*     */   }
/*     */ 
/*     */   @Column(name="VERSION_NUMBER", nullable=true, length=10)
/*     */   public Integer getVersionNumber()
/*     */   {
/* 117 */     return this.versionNumber;
/*     */   }
/*     */ 
/*     */   public void setVersionNumber(Integer versionNumber)
/*     */   {
/* 125 */     this.versionNumber = versionNumber;
/*     */   }
/*     */ 
/*     */   @Column(name="VERSION_DESCRIPTION", nullable=true)
/*     */   public String getVersionDescription()
/*     */   {
/* 133 */     return this.versionDescription;
/*     */   }
/*     */ 
/*     */   public void setVersionDescription(String versionDescription)
/*     */   {
/* 141 */     this.versionDescription = versionDescription;
/*     */   }
/*     */ 
/*     */   @Column(name="UPLOAD_URL", nullable=true, length=500)
/*     */   public String getUploadUrl()
/*     */   {
/* 149 */     return this.uploadUrl;
/*     */   }
/*     */ 
/*     */   public void setUploadUrl(String uploadUrl)
/*     */   {
/* 157 */     this.uploadUrl = uploadUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="DOWNLOAD_URL", nullable=true, length=500)
/*     */   public String getDownloadUrl()
/*     */   {
/* 165 */     return this.downloadUrl;
/*     */   }
/*     */ 
/*     */   public void setDownloadUrl(String downloadUrl)
/*     */   {
/* 173 */     this.downloadUrl = downloadUrl;
/*     */   }
/*     */ 
/*     */   @Column(name="CURRENT_FIELD", nullable=true, length=32)
/*     */   public String getCurrentField()
/*     */   {
/* 181 */     return this.currentField;
/*     */   }
/*     */ 
/*     */   public void setCurrentField(String currentField)
/*     */   {
/* 189 */     this.currentField = currentField;
/*     */   }
/*     */ 
/*     */   @Column(name="APPID", nullable=true, length=32)
/*     */   public String getAppid()
/*     */   {
/* 197 */     return this.appid;
/*     */   }
/*     */ 
/*     */   public void setAppid(String appid)
/*     */   {
/* 205 */     this.appid = appid;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity
 * JD-Core Version:    0.6.2
 */