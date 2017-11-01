/*     */ package org.jeecgframework.web.cgform.service.impl.config;
/*     */ 
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
/*     */ 
/*     */ public class TableSQLServerHandleImpl
/*     */   implements DbTableHandleI
/*     */ {
/*     */   public String getAddColumnSql(ColumnMeta columnMeta)
/*     */   {
/*  17 */     return " ADD  " + getAddFieldDesc(columnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getReNameFieldName(ColumnMeta columnMeta)
/*     */   {
/*  23 */     return "  sp_rename '" + columnMeta.getTableName() + "." + columnMeta.getOldColumnName() + "', '" + columnMeta.getColumnName() + "', 'COLUMN';";
/*     */   }
/*     */ 
/*     */   public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/*  29 */     return " ALTER COLUMN  " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getMatchClassTypeByDataType(String dataType, int digits)
/*     */   {
/*  34 */     String result = "";
/*  35 */     if (dataType.equalsIgnoreCase("varchar"))
/*  36 */       result = "string";
/*  37 */     else if (dataType.equalsIgnoreCase("double"))
/*  38 */       result = "double";
/*  39 */     else if (dataType.equalsIgnoreCase("int"))
/*  40 */       result = "int";
/*  41 */     else if (dataType.equalsIgnoreCase("Date"))
/*  42 */       result = "date";
/*  43 */     else if (dataType.equalsIgnoreCase("Datetime"))
/*  44 */       result = "date";
/*  45 */     else if (dataType.equalsIgnoreCase("numeric")) {
/*  46 */       result = "double";
/*     */     }
/*  48 */     return result;
/*     */   }
/*     */ 
/*     */   public String dropTableSQL(String tableName)
/*     */   {
/*  53 */     return " DROP TABLE " + tableName + " ;";
/*     */   }
/*     */ 
/*     */   public String getDropColumnSql(String fieldName)
/*     */   {
/*  58 */     return " DROP COLUMN " + fieldName + ";";
/*     */   }
/*     */ 
/*     */   private String getUpdateFieldDesc(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta) {
/*  62 */     String result = "";
/*  63 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/*  64 */       result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  65 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/*  66 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  67 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/*  68 */       result = cgfromcolumnMeta.getColumnName() + " int" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  69 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
/*  70 */       result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*     */     }
/*  72 */     result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
/*  73 */     return result;
/*     */   }
/*     */ 
/*     */   private String getAddFieldDesc(ColumnMeta cgfromcolumnMeta) {
/*  77 */     String result = "";
/*  78 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/*  79 */       result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  80 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/*  81 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  82 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/*  83 */       result = cgfromcolumnMeta.getColumnName() + " int(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  84 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double"))
/*  85 */       result = cgfromcolumnMeta.getColumnName() + " double(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  86 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal"))
/*  87 */       result = cgfromcolumnMeta.getColumnName() + " decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  88 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text"))
/*  89 */       result = cgfromcolumnMeta.getColumnName() + " text" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*  90 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob")) {
/*  91 */       result = cgfromcolumnMeta.getColumnName() + " varbinary(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*     */     }
/*  93 */     result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
/*  94 */     return result;
/*     */   }
/*     */ 
/*     */   private String getRenameFieldDesc(ColumnMeta cgfromcolumnMeta) {
/*  98 */     String result = "";
/*  99 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/* 100 */       result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/* 101 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/* 102 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/* 103 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/* 104 */       result = cgfromcolumnMeta.getColumnName() + " int(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/* 105 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
/* 106 */       result = cgfromcolumnMeta.getColumnName() + " double(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + (cgfromcolumnMeta.getIsNullable().equals("Y") ? "NULL" : "NOT NULL");
/*     */     }
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */   public String getCommentSql(ColumnMeta columnMeta)
/*     */   {
/* 113 */     StringBuffer commentSql = new StringBuffer("EXECUTE ");
/* 114 */     if (StringUtil.isEmpty(columnMeta.getOldColumnName()))
/* 115 */       commentSql.append("sp_addextendedproperty");
/*     */     else {
/* 117 */       commentSql.append("sp_updateextendedproperty");
/*     */     }
/* 119 */     commentSql.append(" N'MS_Description', '");
/* 120 */     commentSql.append(columnMeta.getComment());
/* 121 */     commentSql.append("', N'SCHEMA', N'dbo', N'TABLE', N'");
/* 122 */     commentSql.append(columnMeta.getTableName());
/* 123 */     commentSql.append("', N'COLUMN', N'");
/* 124 */     commentSql.append(columnMeta.getColumnName() + "'");
/* 125 */     return commentSql.toString();
/*     */   }
/*     */ 
/*     */   public String getSpecialHandle(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/* 131 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.TableSQLServerHandleImpl
 * JD-Core Version:    0.6.2
 */