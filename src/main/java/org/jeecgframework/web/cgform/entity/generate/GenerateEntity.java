/*     */ package org.jeecgframework.web.cgform.entity.generate;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
/*     */ import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
/*     */ 
/*     */ public class GenerateEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7821940124097563556L;
/*     */   private String entityPackage;
/*     */   private String entityName;
/*     */   private String tableName;
/*     */   private String ftlDescription;
/*  31 */   private String primaryKeyPolicy = "uuid";
/*     */   private String[] foreignKeys;
/*     */   private Integer fieldRowNum;
/*     */   private String projectPath;
/*     */   private String packageStyle;
/*     */   private CgFormHeadEntity cgFormHead;
/*     */   private List<CgformButtonEntity> buttons;
/*     */   private Map<String, String[]> buttonSqlMap;
/*     */   private CgformEnhanceJsEntity listJs;
/*     */   private CgformEnhanceJsEntity formJs;
/*     */ 
/*     */   public String getEntityPackage()
/*     */   {
/*  61 */     return this.entityPackage;
/*     */   }
/*     */ 
/*     */   public void setEntityPackage(String entityPackage) {
/*  65 */     this.entityPackage = entityPackage;
/*     */   }
/*     */ 
/*     */   public String getEntityName() {
/*  69 */     return this.entityName;
/*     */   }
/*     */ 
/*     */   public void setEntityName(String entityName) {
/*  73 */     this.entityName = entityName;
/*     */   }
/*     */ 
/*     */   public String getTableName() {
/*  77 */     return this.tableName;
/*     */   }
/*     */ 
/*     */   public void setTableName(String tableName) {
/*  81 */     this.tableName = tableName;
/*     */   }
/*     */ 
/*     */   public String getFtlDescription() {
/*  85 */     return this.ftlDescription;
/*     */   }
/*     */ 
/*     */   public void setFtlDescription(String ftlDescription) {
/*  89 */     this.ftlDescription = ftlDescription;
/*     */   }
/*     */ 
/*     */   public String getPrimaryKeyPolicy() {
/*  93 */     return this.primaryKeyPolicy;
/*     */   }
/*     */ 
/*     */   public void setPrimaryKeyPolicy(String primaryKeyPolicy) {
/*  97 */     this.primaryKeyPolicy = primaryKeyPolicy;
/*     */   }
/*     */ 
/*     */   public String[] getForeignKeys() {
/* 101 */     return this.foreignKeys;
/*     */   }
/*     */ 
/*     */   public void setForeignKeys(String[] foreignKeys) {
/* 105 */     this.foreignKeys = foreignKeys;
/*     */   }
/*     */ 
/*     */   public Integer getFieldRowNum() {
/* 109 */     return this.fieldRowNum;
/*     */   }
/*     */ 
/*     */   public void setFieldRowNum(Integer fieldRowNum) {
/* 113 */     this.fieldRowNum = fieldRowNum;
/*     */   }
/*     */ 
/*     */   public CgFormHeadEntity getCgFormHead() {
/* 117 */     return this.cgFormHead;
/*     */   }
/*     */ 
/*     */   public void setCgFormHead(CgFormHeadEntity cgFormHead) {
/* 121 */     this.cgFormHead = cgFormHead;
/*     */   }
/*     */ 
/*     */   public List<CgformButtonEntity> getButtons() {
/* 125 */     return this.buttons;
/*     */   }
/*     */ 
/*     */   public void setButtons(List<CgformButtonEntity> buttons) {
/* 129 */     this.buttons = buttons;
/*     */   }
/*     */ 
/*     */   public Map<String, String[]> getButtonSqlMap()
/*     */   {
/* 135 */     return this.buttonSqlMap;
/*     */   }
/*     */ 
/*     */   public void setButtonSqlMap(Map<String, String[]> buttonSqlMap) {
/* 139 */     this.buttonSqlMap = buttonSqlMap;
/*     */   }
/*     */ 
/*     */   public CgformEnhanceJsEntity getListJs() {
/* 143 */     return this.listJs == null ? new CgformEnhanceJsEntity() : this.listJs;
/*     */   }
/*     */ 
/*     */   public void setListJs(CgformEnhanceJsEntity listJs) {
/* 147 */     this.listJs = listJs;
/*     */   }
/*     */ 
/*     */   public CgformEnhanceJsEntity getFormJs() {
/* 151 */     return this.formJs == null ? new CgformEnhanceJsEntity() : this.formJs;
/*     */   }
/*     */ 
/*     */   public void setFormJs(CgformEnhanceJsEntity formJs) {
/* 155 */     this.formJs = formJs;
/*     */   }
/*     */ 
/*     */   public String getProjectPath() {
/* 159 */     String pt = this.projectPath;
/* 160 */     if (pt != null) {
/* 161 */       pt = pt.replace("\\", "/");
/* 162 */       if (!pt.endsWith("/")) {
/* 163 */         pt = pt + "/";
/*     */       }
/*     */     }
/* 166 */     return pt;
/*     */   }
/*     */ 
/*     */   public void setProjectPath(String projectPath) {
/* 170 */     this.projectPath = projectPath;
/*     */   }
/*     */ 
/*     */   public String getPackageStyle()
/*     */   {
/* 175 */     return this.packageStyle;
/*     */   }
/*     */ 
/*     */   public void setPackageStyle(String packageStyle) {
/* 179 */     this.packageStyle = packageStyle;
/*     */   }
/*     */ 
/*     */   public Object clone() throws CloneNotSupportedException {
/* 183 */     return super.clone();
/*     */   }
/*     */ 
/*     */   public GenerateEntity deepCopy()
/*     */     throws Exception
/*     */   {
/* 193 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/* 194 */     ObjectOutputStream oos = new ObjectOutputStream(bos);
/* 195 */     oos.writeObject(this);
/*     */ 
/* 198 */     ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
/* 199 */     ObjectInputStream ois = new ObjectInputStream(bis);
/* 200 */     return (GenerateEntity)ois.readObject();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.generate.GenerateEntity
 * JD-Core Version:    0.6.2
 */