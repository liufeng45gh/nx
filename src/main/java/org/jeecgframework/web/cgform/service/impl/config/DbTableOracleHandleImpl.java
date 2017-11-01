/*     */ package org.jeecgframework.web.cgform.service.impl.config;
/*     */ 
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
/*     */ 
/*     */ public class DbTableOracleHandleImpl
/*     */   implements DbTableHandleI
/*     */ {
/*     */   public String getAddColumnSql(ColumnMeta columnMeta)
/*     */   {
/*  17 */     return " ADD  " + getAddFieldDesc(columnMeta);
/*     */   }
/*     */ 
/*     */   public String getReNameFieldName(ColumnMeta columnMeta)
/*     */   {
/*  22 */     return "RENAME COLUMN  " + columnMeta.getOldColumnName() + " TO " + columnMeta.getColumnName();
/*     */   }
/*     */ 
/*     */   public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/*  27 */     return " MODIFY   " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta);
/*     */   }
/*     */ 
/*     */   public String getMatchClassTypeByDataType(String dataType, int digits)
/*     */   {
/*  32 */     String result = "";
/*  33 */     if (dataType.equalsIgnoreCase("varchar2"))
/*  34 */       result = "string";
/*  35 */     else if (dataType.equalsIgnoreCase("double"))
/*  36 */       result = "double";
/*  37 */     else if ((dataType.equalsIgnoreCase("number")) && (digits == 0))
/*  38 */       result = "int";
/*  39 */     else if ((dataType.equalsIgnoreCase("number")) && (digits != 0))
/*  40 */       result = "double";
/*  41 */     else if (dataType.equalsIgnoreCase("int"))
/*  42 */       result = "int";
/*  43 */     else if (dataType.equalsIgnoreCase("Date"))
/*  44 */       result = "date";
/*  45 */     else if (dataType.equalsIgnoreCase("Datetime")) {
/*  46 */       result = "date";
/*     */     }
/*  48 */     return result;
/*     */   }
/*     */ 
/*     */   public String dropTableSQL(String tableName)
/*     */   {
/*  53 */     return " DROP TABLE  " + tableName.toLowerCase() + " ";
/*     */   }
/*     */ 
/*     */   public String getDropColumnSql(String fieldName)
/*     */   {
/*  58 */     return " DROP COLUMN " + fieldName.toUpperCase();
/*     */   }
/*     */ 
/*     */   private String getAddFieldDesc(ColumnMeta columnMeta) {
/*  62 */     String result = "";
/*  63 */     if (columnMeta.getColunmType().equalsIgnoreCase("string"))
/*  64 */       result = columnMeta.getColumnName() + " varchar2(" + columnMeta.getColumnSize() + ")";
/*  65 */     else if (columnMeta.getColunmType().equalsIgnoreCase("date"))
/*  66 */       result = columnMeta.getColumnName() + " datetime";
/*  67 */     else if (columnMeta.getColunmType().equalsIgnoreCase("int"))
/*  68 */       result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + ")";
/*  69 */     else if (columnMeta.getColunmType().equalsIgnoreCase("double"))
/*  70 */       result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
/*  71 */     else if (columnMeta.getColunmType().equalsIgnoreCase("bigdecimal"))
/*  72 */       result = columnMeta.getColumnName() + " NUMBER(" + columnMeta.getColumnSize() + "," + columnMeta.getDecimalDigits() + ")";
/*  73 */     else if (columnMeta.getColunmType().equalsIgnoreCase("text"))
/*  74 */       result = columnMeta.getColumnName() + " CLOB ";
/*  75 */     else if (columnMeta.getColunmType().equalsIgnoreCase("blob")) {
/*  76 */       result = columnMeta.getColumnName() + " BLOB ";
/*     */     }
/*  78 */     result = result + (StringUtils.isNotEmpty(columnMeta.getFieldDefault()) ? " DEFAULT " + columnMeta.getFieldDefault() : " ");
/*  79 */     result = result + (columnMeta.getIsNullable().equals("Y") ? " NULL" : " NOT NULL");
/*  80 */     return result;
/*     */   }
/*     */   private String getUpdateFieldDesc(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta) {
/*  83 */     String result = "";
/*  84 */     String isnull = "";
/*     */ 
/*  86 */     if (!datacolumnMeta.getIsNullable().equals(cgformcolumnMeta.getIsNullable())) {
/*  87 */       isnull = cgformcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL";
/*     */     }
/*     */ 
/*  90 */     if ((cgformcolumnMeta.getColunmType().equalsIgnoreCase("string")) || (cgformcolumnMeta.getColunmType().equalsIgnoreCase("text"))) {
/*  91 */       result = cgformcolumnMeta.getColumnName() + " varchar2(" + cgformcolumnMeta.getColumnSize() + ")" + isnull;
/*     */     }
/*  93 */     else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
/*  94 */       result = cgformcolumnMeta.getColumnName() + " date " + isnull;
/*     */     }
/*  96 */     else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
/*  97 */       result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + ") " + isnull;
/*     */     }
/*  99 */     else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("double"))
/* 100 */       result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") " + isnull;
/* 101 */     else if (cgformcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
/* 102 */       result = cgformcolumnMeta.getColumnName() + " NUMBER(" + cgformcolumnMeta.getColumnSize() + "," + cgformcolumnMeta.getDecimalDigits() + ") " + isnull;
/*     */     }
/* 104 */     result = result + (StringUtils.isNotEmpty(cgformcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgformcolumnMeta.getFieldDefault() : " ");
/* 105 */     result = result + isnull;
/* 106 */     return result;
/*     */   }
/*     */ 
/*     */   public String getCommentSql(ColumnMeta columnMeta)
/*     */   {
/* 111 */     return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
/*     */   }
/*     */ 
/*     */   public String getSpecialHandle(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/* 117 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.DbTableOracleHandleImpl
 * JD-Core Version:    0.6.2
 */