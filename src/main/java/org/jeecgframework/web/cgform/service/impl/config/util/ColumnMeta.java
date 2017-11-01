/*     */ package org.jeecgframework.web.cgform.service.impl.config.util;
/*     */ 
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ColumnMeta
/*     */ {
/*     */   private String tableName;
/*     */   private String columnId;
/*     */   private String columnName;
/*     */   private int columnSize;
/*     */   private String colunmType;
/*     */   private String comment;
/*     */   private String fieldDefault;
/*     */   private int decimalDigits;
/*     */   private String isNullable;
/*     */   private String pkType;
/*     */   private String oldColumnName;
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  20 */     if (obj == this) {
/*  21 */       return true;
/*     */     }
/*  23 */     if (!(obj instanceof ColumnMeta)) {
/*  24 */       return false;
/*     */     }
/*  26 */     ColumnMeta meta = (ColumnMeta)obj;
/*     */ 
/*  28 */     if ((this.colunmType.contains("date")) || (this.colunmType.contains("blob")) || (this.colunmType.contains("text"))) {
/*  29 */       return (this.columnName.equals(meta.getColumnName())) && 
/*  30 */         (this.isNullable.equals(meta.isNullable)) && 
/*  31 */         (isEquals(this.comment, meta.getComment())) && (isEquals(this.fieldDefault, meta.getFieldDefault()));
/*     */     }
/*     */ 
/*  39 */     return (this.colunmType.equals(meta.getColunmType())) && 
/*  40 */       (this.isNullable.equals(meta.isNullable)) && (this.columnSize == meta.getColumnSize()) && 
/*  41 */       (isEquals(this.comment, meta.getComment())) && (isEquals(this.fieldDefault, meta.getFieldDefault()));
/*     */   }
/*     */ 
/*     */   public boolean equalsDefault(ColumnMeta meta)
/*     */   {
/*  46 */     if (meta == this) {
/*  47 */       return true;
/*     */     }
/*  49 */     return isEquals(this.comment, meta.getComment());
/*     */   }
/*     */ 
/*     */   public boolean equalsComment(ColumnMeta meta) {
/*  53 */     if (meta == this) {
/*  54 */       return true;
/*     */     }
/*  56 */     return isEquals(this.comment, meta.getComment());
/*     */   }
/*     */ 
/*     */   private boolean isEquals(String newS, String oldS) {
/*  60 */     boolean booN = StringUtils.isNotEmpty(newS);
/*  61 */     boolean booO = StringUtils.isNotEmpty(oldS);
/*  62 */     if (booN != booO) return false;
/*  63 */     if (booN) return newS.equals(oldS);
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */   public String getColumnName() {
/*  68 */     return this.columnName;
/*     */   }
/*     */   public int getColumnSize() {
/*  71 */     return this.columnSize;
/*     */   }
/*     */   public String getColunmType() {
/*  74 */     return this.colunmType;
/*     */   }
/*     */   public String getComment() {
/*  77 */     return this.comment;
/*     */   }
/*     */   public int getDecimalDigits() {
/*  80 */     return this.decimalDigits;
/*     */   }
/*     */   public String getIsNullable() {
/*  83 */     return this.isNullable;
/*     */   }
/*     */   public String getOldColumnName() {
/*  86 */     return this.oldColumnName;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  91 */     return this.columnSize + this.colunmType.hashCode() * this.columnName.hashCode();
/*     */   }
/*     */   public void setColumnName(String columnName) {
/*  94 */     this.columnName = columnName;
/*     */   }
/*     */   public void setColumnSize(int columnSize) {
/*  97 */     this.columnSize = columnSize;
/*     */   }
/*     */ 
/*     */   public void setColunmType(String colunmType) {
/* 101 */     this.colunmType = colunmType;
/*     */   }
/*     */   public void setComment(String comment) {
/* 104 */     this.comment = comment;
/*     */   }
/*     */   public void setDecimalDigits(int decimalDigits) {
/* 107 */     this.decimalDigits = decimalDigits;
/*     */   }
/*     */   public void setIsNullable(String isNullable) {
/* 110 */     this.isNullable = isNullable;
/*     */   }
/*     */   public void setOldColumnName(String oldColumnName) {
/* 113 */     this.oldColumnName = oldColumnName;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 117 */     return this.columnName + "," + this.colunmType + "," + this.isNullable + "," + this.columnSize;
/*     */   }
/*     */   public String getColumnId() {
/* 120 */     return this.columnId;
/*     */   }
/*     */   public void setColumnId(String columnId) {
/* 123 */     this.columnId = columnId;
/*     */   }
/*     */   public String getTableName() {
/* 126 */     return this.tableName;
/*     */   }
/*     */   public void setTableName(String tableName) {
/* 129 */     this.tableName = tableName;
/*     */   }
/*     */   public String getFieldDefault() {
/* 132 */     return this.fieldDefault;
/*     */   }
/*     */   public void setFieldDefault(String fieldDefault) {
/* 135 */     this.fieldDefault = fieldDefault;
/*     */   }
/*     */ 
/*     */   public String getPkType() {
/* 139 */     return this.pkType;
/*     */   }
/*     */ 
/*     */   public void setPkType(String pkType) {
/* 143 */     this.pkType = pkType;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta
 * JD-Core Version:    0.6.2
 */