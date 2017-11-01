/*     */ package org.jeecgframework.web.system.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Inheritance;
/*     */ import javax.persistence.InheritanceType;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_s_base_user")
/*     */ @Inheritance(strategy=InheritanceType.JOINED)
/*     */ public class TSBaseUser extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   @Excel(name="用户名")
/*     */   private String userName;
/*     */ 
/*     */   @Excel(name="真实姓名")
/*     */   private String realName;
/*     */   private String browser;
/*     */ 
/*     */   @Excel(name="角色编码(多个角色编码用逗号分隔，非必填)")
/*     */   private String userKey;
/*     */   private String password;
/*     */   private Short activitiSync;
/*     */   private Short status;
/*     */   private Short deleteFlag;
/*     */   private byte[] signature;
/*     */   private String gender;
/*     */   private String headPortrait;
/*     */   private Integer age;
/*     */   private String documentType;
/*     */   private String identificationNumber;
/*     */   private String phone;
/*     */   private String bankCard;
/*     */   private String occupationScope;
/*     */   private String nationality;
/*     */   private String phoneCountry;
/*     */   private String areaCode;
/*     */   private String city;
/*     */   private String motherTongue;
/*     */   private String secondLanguage;
/*     */   private String occupation;
/*     */   private String vocationalQualificationCertificate;
/*     */   private String singular;
/*     */   private String reportCount;
/*     */   private Date createDate;
/*     */   private String userType;
/*     */   private String auditStatus;
/*     */   private String label;
/*     */   private String subordinateAdmin;
/*     */   private String isLogin;
/*     */   private String token;
/*     */   private Date loginTime;
/*     */   private String balance;
/*     */   private String certification;
/*     */   private String personalityPhoto;
/*     */   private String appType;
/*     */ 
/*     */   @Excel(name="区域id")
/*     */   private String territoryId;
/*     */   private String territoryName;
/*     */ 
/*     */   @Column(name="territoryid", length=32)
/*     */   public String getTerritoryId()
/*     */   {
/*  83 */     return this.territoryId;
/*     */   }
/*     */   public void setTerritoryId(String territoryId) {
/*  86 */     this.territoryId = territoryId;
/*     */   }
/*     */ 
/*     */   @Column(name="signature", length=3000)
/*     */   public byte[] getSignature()
/*     */   {
/*  92 */     return this.signature;
/*     */   }
/*     */   public void setSignature(byte[] signature) {
/*  95 */     this.signature = signature;
/*     */   }
/*     */ 
/*     */   @Column(name="browser", length=20)
/*     */   public String getBrowser() {
/* 100 */     return this.browser;
/*     */   }
/*     */ 
/*     */   public void setBrowser(String browser) {
/* 104 */     this.browser = browser;
/*     */   }
/*     */ 
/*     */   @Column(name="userkey", length=200)
/*     */   public String getUserKey() {
/* 109 */     return this.userKey;
/*     */   }
/*     */ 
/*     */   public void setUserKey(String userKey) {
/* 113 */     this.userKey = userKey;
/*     */   }
/*     */ 
/*     */   @Column(name="status")
/*     */   public Short getStatus() {
/* 118 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Short status) {
/* 122 */     this.status = status;
/*     */   }
/*     */   public Short getActivitiSync() {
/* 125 */     return this.activitiSync;
/*     */   }
/*     */   @Column(name="activitisync")
/*     */   public void setActivitiSync(Short activitiSync) {
/* 129 */     this.activitiSync = activitiSync;
/*     */   }
/*     */ 
/*     */   @Column(name="password", length=100)
/*     */   public String getPassword()
/*     */   {
/* 135 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password) {
/* 139 */     this.password = password;
/*     */   }
/*     */ 
/*     */   @Column(name="username", nullable=false, length=10)
/*     */   public String getUserName()
/*     */   {
/* 153 */     return this.userName;
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 157 */     this.userName = userName;
/*     */   }
/*     */   @Column(name="realname", length=50)
/*     */   public String getRealName() {
/* 161 */     return this.realName;
/*     */   }
/*     */ 
/*     */   public void setRealName(String realName) {
/* 165 */     this.realName = realName;
/*     */   }
/*     */ 
/*     */   public void setDeleteFlag(Short deleteFlag)
/*     */   {
/* 171 */     this.deleteFlag = deleteFlag;
/*     */   }
/*     */ 
/*     */   @Column(name="delete_flag")
/*     */   public Short getDeleteFlag() {
/* 176 */     return this.deleteFlag;
/*     */   }
/*     */ 
/*     */   @Column(name="head_portrait", length=255)
/*     */   public String getHeadPortrait() {
/* 181 */     return this.headPortrait;
/*     */   }
/*     */   public void setHeadPortrait(String headPortrait) {
/* 184 */     this.headPortrait = headPortrait;
/*     */   }
/*     */ 
/*     */   @Column(name="age", length=3)
/*     */   public Integer getAge() {
/* 189 */     return this.age;
/*     */   }
/*     */   public void setAge(Integer age) {
/* 192 */     this.age = age;
/*     */   }
/*     */ 
/*     */   @Column(name="document_type", length=100)
/*     */   public String getDocumentType() {
/* 197 */     return this.documentType;
/*     */   }
/*     */   public void setDocumentType(String documentType) {
/* 200 */     this.documentType = documentType;
/*     */   }
/*     */ 
/*     */   @Column(name="identification_number", length=100)
/*     */   public String getIdentificationNumber() {
/* 205 */     return this.identificationNumber;
/*     */   }
/*     */   public void setIdentificationNumber(String identificationNumber) {
/* 208 */     this.identificationNumber = identificationNumber;
/*     */   }
/*     */ 
/*     */   @Column(name="phone", length=100)
/*     */   public String getPhone() {
/* 213 */     return this.phone;
/*     */   }
/*     */   public void setPhone(String phone) {
/* 216 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   @Column(name="bank_card", length=100)
/*     */   public String getBankCard() {
/* 221 */     return this.bankCard;
/*     */   }
/*     */   public void setBankCard(String bankCard) {
/* 224 */     this.bankCard = bankCard;
/*     */   }
/*     */ 
/*     */   @Column(name="occupation_scope", length=100)
/*     */   public String getOccupationScope() {
/* 229 */     return this.occupationScope;
/*     */   }
/*     */   public void setOccupationScope(String occupationScope) {
/* 232 */     this.occupationScope = occupationScope;
/*     */   }
/*     */ 
/*     */   @Column(name="nationality", length=100)
/*     */   public String getNationality() {
/* 237 */     return this.nationality;
/*     */   }
/*     */   public void setNationality(String nationality) {
/* 240 */     this.nationality = nationality;
/*     */   }
/*     */ 
/*     */   @Column(name="city", length=100)
/*     */   public String getCity() {
/* 245 */     return this.city;
/*     */   }
/*     */   public void setCity(String city) {
/* 248 */     this.city = city;
/*     */   }
/*     */ 
/*     */   @Column(name="mother_tongue", length=100)
/*     */   public String getMotherTongue() {
/* 253 */     return this.motherTongue;
/*     */   }
/*     */   public void setMotherTongue(String motherTongue) {
/* 256 */     this.motherTongue = motherTongue;
/*     */   }
/*     */ 
/*     */   @Column(name="second_language", length=100)
/*     */   public String getSecondLanguage() {
/* 261 */     return this.secondLanguage;
/*     */   }
/*     */   public void setSecondLanguage(String secondLanguage) {
/* 264 */     this.secondLanguage = secondLanguage;
/*     */   }
/*     */ 
/*     */   @Column(name="occupation", length=100)
/*     */   public String getOccupation() {
/* 269 */     return this.occupation;
/*     */   }
/*     */   public void setOccupation(String occupation) {
/* 272 */     this.occupation = occupation;
/*     */   }
/*     */ 
/*     */   @Column(name="vocational_qualification_certificate", length=100)
/*     */   public String getVocationalQualificationCertificate() {
/* 277 */     return this.vocationalQualificationCertificate;
/*     */   }
/*     */   public void setVocationalQualificationCertificate(String vocationalQualificationCertificate) {
/* 280 */     this.vocationalQualificationCertificate = vocationalQualificationCertificate;
/*     */   }
/*     */ 
/*     */   @Column(name="singular", length=100)
/*     */   public String getSingular() {
/* 285 */     return this.singular;
/*     */   }
/*     */   public void setSingular(String singular) {
/* 288 */     this.singular = singular;
/*     */   }
/*     */ 
/*     */   @Column(name="reportCount", length=100)
/*     */   public String getReportCount() {
/* 293 */     return this.reportCount;
/*     */   }
/*     */   public void setReportCount(String reportCount) {
/* 296 */     this.reportCount = reportCount;
/*     */   }
/*     */ 
/*     */   @Column(name="create_date", length=100)
/*     */   public Date getCreateDate() {
/* 301 */     return this.createDate;
/*     */   }
/*     */   public void setCreateDate(Date createDate) {
/* 304 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   @Column(name="user_Type", length=100)
/*     */   public String getUserType() {
/* 309 */     return this.userType;
/*     */   }
/*     */   public void setUserType(String userType) {
/* 312 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   @Column(name="audit_status", length=100)
/*     */   public String getAuditStatus() {
/* 317 */     return this.auditStatus;
/*     */   }
/*     */   public void setAuditStatus(String auditStatus) {
/* 320 */     this.auditStatus = auditStatus;
/*     */   }
/*     */ 
/*     */   @Column(name="label", length=100)
/*     */   public String getLabel() {
/* 325 */     return this.label;
/*     */   }
/*     */   public void setLabel(String label) {
/* 328 */     this.label = label;
/*     */   }
/*     */ 
/*     */   @Column(name="subordinate_admin", length=100)
/*     */   public String getSubordinateAdmin() {
/* 333 */     return this.subordinateAdmin;
/*     */   }
/*     */   public void setSubordinateAdmin(String subordinateAdmin) {
/* 336 */     this.subordinateAdmin = subordinateAdmin;
/*     */   }
/*     */ 
/*     */   @Column(name="isLogin", length=100)
/*     */   public String isLogin() {
/* 341 */     return this.isLogin;
/*     */   }
/*     */ 
/*     */   public void setLogin(String isLogin) {
/* 345 */     this.isLogin = isLogin;
/*     */   }
/*     */ 
/*     */   @Column(name="token", length=100)
/*     */   public String getToken() {
/* 350 */     return this.token;
/*     */   }
/*     */   public void setToken(String token) {
/* 353 */     this.token = token;
/*     */   }
/*     */ 
/*     */   @Column(name="loginTime", length=100)
/*     */   public Date getLoginTime() {
/* 358 */     return this.loginTime;
/*     */   }
/*     */   public void setLoginTime(Date loginTime) {
/* 361 */     this.loginTime = loginTime;
/*     */   }
/*     */ 
/*     */   @Column(name="certification", length=100)
/*     */   public String getCertification() {
/* 366 */     return this.certification;
/*     */   }
/*     */   public void setCertification(String certification) {
/* 369 */     this.certification = certification;
/*     */   }
/*     */   @Column(name="balance", length=100)
/*     */   public String getBalance() {
/* 373 */     return this.balance;
/*     */   }
/*     */   public void setBalance(String balance) {
/* 376 */     this.balance = balance;
/*     */   }
/*     */   @Column(name="gender", length=10)
/*     */   public String getGender() {
/* 380 */     return this.gender;
/*     */   }
/*     */   public void setGender(String gender) {
/* 383 */     this.gender = gender;
/*     */   }
/*     */ 
/*     */   @Column(name="phone_Country", length=100)
/*     */   public String getPhoneCountry() {
/* 388 */     return this.phoneCountry;
/*     */   }
/*     */   public void setPhoneCountry(String phoneCountry) {
/* 391 */     this.phoneCountry = phoneCountry;
/*     */   }
/*     */ 
/*     */   @Column(name="area_Code", length=100)
/*     */   public String getAreaCode() {
/* 396 */     return this.areaCode;
/*     */   }
/*     */   public void setAreaCode(String areaCode) {
/* 399 */     this.areaCode = areaCode;
/*     */   }
/*     */ 
/*     */   @Column(name="personality_photo", length=500)
/*     */   public String getPersonalityPhoto() {
/* 404 */     return this.personalityPhoto;
/*     */   }
/*     */   public void setPersonalityPhoto(String personalityPhoto) {
/* 407 */     this.personalityPhoto = personalityPhoto;
/*     */   }
/*     */ 
/*     */   @Column(name="appType", length=50)
/*     */   public String getAppType() {
/* 412 */     return this.appType;
/*     */   }
/*     */   public void setAppType(String appType) {
/* 415 */     this.appType = appType;
/*     */   }
/*     */   @Transient
/*     */   public String getTerritoryName() {
/* 419 */     return this.territoryName;
/*     */   }
/*     */   public void setTerritoryName(String territoryName) {
/* 422 */     this.territoryName = territoryName;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSBaseUser
 * JD-Core Version:    0.6.2
 */