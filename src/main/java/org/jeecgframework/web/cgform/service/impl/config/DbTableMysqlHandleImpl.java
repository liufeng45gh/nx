/*     */ package org.jeecgframework.web.cgform.service.impl.config;
/*     */ 
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
/*     */ 
/*     */ public class DbTableMysqlHandleImpl
/*     */   implements DbTableHandleI
/*     */ {
/*     */   public String getAddColumnSql(ColumnMeta columnMeta)
/*     */   {
/*  16 */     return " ADD COLUMN " + getAddFieldDesc(columnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getReNameFieldName(ColumnMeta columnMeta)
/*     */   {
/*  21 */     return "CHANGE COLUMN  " + columnMeta.getOldColumnName() + " " + getRenameFieldDesc(columnMeta) + " ;";
/*     */   }
/*     */ 
/*     */   public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/*  26 */     return " MODIFY COLUMN  " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getMatchClassTypeByDataType(String dataType, int digits)
/*     */   {
/*  31 */     String result = "";
/*  32 */     if (dataType.equalsIgnoreCase("varchar"))
/*  33 */       result = "string";
/*  34 */     else if (dataType.equalsIgnoreCase("double"))
/*  35 */       result = "double";
/*  36 */     else if (dataType.equalsIgnoreCase("int"))
/*  37 */       result = "int";
/*  38 */     else if (dataType.equalsIgnoreCase("Date"))
/*  39 */       result = "date";
/*  40 */     else if (dataType.equalsIgnoreCase("Datetime"))
/*  41 */       result = "date";
/*  42 */     else if (dataType.equalsIgnoreCase("decimal"))
/*  43 */       result = "bigdecimal";
/*  44 */     else if (dataType.equalsIgnoreCase("text"))
/*  45 */       result = "text";
/*  46 */     else if (dataType.equalsIgnoreCase("blob")) {
/*  47 */       result = "blob";
/*     */     }
/*  49 */     return result;
/*     */   }
/*     */ 
/*     */   public String dropTableSQL(String tableName)
/*     */   {
/*  54 */     return " DROP TABLE IF EXISTS " + tableName + " ;";
/*     */   }
/*     */ 
/*     */   public String getDropColumnSql(String fieldName)
/*     */   {
/*  59 */     return " DROP COLUMN " + fieldName + ";";
/*     */   }
/*     */ 
/*     */   private String getFieldDesc(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/*  68 */     String result = "";
/*  69 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/*  70 */       result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  71 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/*  72 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  73 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/*  74 */       result = cgfromcolumnMeta.getColumnName() + " int(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  75 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double"))
/*  76 */       result = cgfromcolumnMeta.getColumnName() + " double(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  77 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal"))
/*  78 */       result = cgfromcolumnMeta.getColumnName() + " decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  79 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text"))
/*  80 */       result = cgfromcolumnMeta.getColumnName() + " text " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  81 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob")) {
/*  82 */       result = cgfromcolumnMeta.getColumnName() + " blob " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*     */     }
/*  84 */     result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getComment()) ? " COMMENT '" + cgfromcolumnMeta.getComment() + "'" : " ");
/*  85 */     result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
/*  86 */     String pkType = cgfromcolumnMeta.getPkType();
/*  87 */     if (("id".equalsIgnoreCase(cgfromcolumnMeta.getColumnName())) && (pkType != null) && (
/*  88 */       ("SEQUENCE".equalsIgnoreCase(pkType)) || ("NATIVE".equalsIgnoreCase(pkType)))) {
/*  89 */       result = result + " AUTO_INCREMENT ";
/*     */     }
/*  91 */     return result;
/*     */   }
/*     */ 
/*     */   private String getUpdateFieldDesc(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta) {
/*  95 */     String result = getFieldDesc(cgfromcolumnMeta, datacolumnMeta);
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   private String getAddFieldDesc(ColumnMeta cgfromcolumnMeta) {
/* 100 */     String result = getFieldDesc(cgfromcolumnMeta, null);
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */   private String getRenameFieldDesc(ColumnMeta cgfromcolumnMeta) {
/* 105 */     String result = getFieldDesc(cgfromcolumnMeta, null);
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */   public String getCommentSql(ColumnMeta columnMeta)
/*     */   {
/* 113 */     return "";
/*     */   }
/*     */ 
/*     */   public String getSpecialHandle(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/* 119 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.DbTableMysqlHandleImpl
 * JD-Core Version:    0.6.2
 */