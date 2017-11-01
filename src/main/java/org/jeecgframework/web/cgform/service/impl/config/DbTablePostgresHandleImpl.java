/*     */ package org.jeecgframework.web.cgform.service.impl.config;
/*     */ 
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.web.cgform.exception.DBException;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;
/*     */ 
/*     */ public class DbTablePostgresHandleImpl
/*     */   implements DbTableHandleI
/*     */ {
/*     */   public String getAddColumnSql(ColumnMeta columnMeta)
/*     */   {
/*  18 */     return " ADD COLUMN " + getAddFieldDesc(columnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getReNameFieldName(ColumnMeta columnMeta)
/*     */   {
/*  23 */     return " RENAME  COLUMN  " + columnMeta.getOldColumnName() + " to " + columnMeta.getColumnName() + ";";
/*     */   }
/*     */ 
/*     */   public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta) throws DBException
/*     */   {
/*  28 */     return "  ALTER  COLUMN   " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getSpecialHandle(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta)
/*     */   {
/*  34 */     return "  ALTER  COLUMN   " + getUpdateFieldDefault(cgformcolumnMeta, datacolumnMeta) + ";";
/*     */   }
/*     */ 
/*     */   public String getMatchClassTypeByDataType(String dataType, int digits)
/*     */   {
/*  39 */     String result = "";
/*  40 */     if (dataType.equalsIgnoreCase("varchar"))
/*  41 */       result = "string";
/*  42 */     else if (dataType.equalsIgnoreCase("double"))
/*  43 */       result = "double";
/*  44 */     else if (dataType.contains("int"))
/*  45 */       result = "int";
/*  46 */     else if (dataType.equalsIgnoreCase("Date"))
/*  47 */       result = "date";
/*  48 */     else if (dataType.equalsIgnoreCase("timestamp"))
/*  49 */       result = "date";
/*  50 */     else if (dataType.equalsIgnoreCase("bytea"))
/*  51 */       result = "blob";
/*  52 */     else if (dataType.equalsIgnoreCase("text"))
/*  53 */       result = "text";
/*  54 */     else if (dataType.equalsIgnoreCase("decimal"))
/*  55 */       result = "bigdecimal";
/*  56 */     else if (dataType.equalsIgnoreCase("numeric"))
/*     */     {
/*  58 */       result = "bigdecimal";
/*     */     }
/*  60 */     return result;
/*     */   }
/*     */ 
/*     */   public String dropTableSQL(String tableName)
/*     */   {
/*  65 */     return " DROP TABLE  " + tableName + " ;";
/*     */   }
/*     */ 
/*     */   public String getDropColumnSql(String fieldName)
/*     */   {
/*  70 */     return " DROP COLUMN " + fieldName + ";";
/*     */   }
/*     */ 
/*     */   private String getUpdateFieldDesc(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta) throws DBException {
/*  74 */     String result = "";
/*     */ 
/*  76 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/*  77 */       result = cgfromcolumnMeta.getColumnName() + "  type character varying(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/*  78 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/*  79 */       result = cgfromcolumnMeta.getColumnName() + "  type datetime" + " ";
/*  80 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/*     */     {
/*  82 */       result = cgfromcolumnMeta.getColumnName() + " type int4 ";
/*  83 */     } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double"))
/*  84 */       result = cgfromcolumnMeta.getColumnName() + " type  numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " ";
/*  85 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("BigDecimal"))
/*  86 */       result = cgfromcolumnMeta.getColumnName() + " type  decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " ";
/*  87 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text"))
/*  88 */       result = cgfromcolumnMeta.getColumnName() + "  type text(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/*  89 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob"))
/*     */     {
/*  91 */       throw new DBException("blob类型不可修改");
/*     */     }
/*  93 */     return result;
/*     */   }
/*     */ 
/*     */   private String getUpdateFieldDefault(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta) {
/*  97 */     String result = "";
/*     */ 
/*  99 */     if (!cgfromcolumnMeta.equalsDefault(datacolumnMeta)) {
/* 100 */       if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string")) {
/* 101 */         result = cgfromcolumnMeta.getColumnName();
/* 102 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/* 103 */       } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date")) {
/* 104 */         result = cgfromcolumnMeta.getColumnName();
/* 105 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/* 106 */       } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int")) {
/* 107 */         result = cgfromcolumnMeta.getColumnName();
/* 108 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/* 109 */       } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
/* 110 */         result = cgfromcolumnMeta.getColumnName();
/* 111 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/* 112 */       } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal")) {
/* 113 */         result = cgfromcolumnMeta.getColumnName();
/* 114 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/* 115 */       } else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text")) {
/* 116 */         result = cgfromcolumnMeta.getColumnName();
/* 117 */         result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " SET DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " DROP DEFAULT");
/*     */       }
/*     */     }
/*     */ 
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   private String getAddFieldDesc(ColumnMeta cgfromcolumnMeta)
/*     */   {
/* 126 */     String result = "";
/* 127 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/* 128 */       result = cgfromcolumnMeta.getColumnName() + " character varying(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/* 129 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/* 130 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " ";
/* 131 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/* 132 */       result = cgfromcolumnMeta.getColumnName() + " int4";
/* 133 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double"))
/* 134 */       result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " ";
/* 135 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("bigdecimal"))
/* 136 */       result = cgfromcolumnMeta.getColumnName() + " decimal(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " ";
/* 137 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("blob"))
/* 138 */       result = cgfromcolumnMeta.getColumnName() + " bytea(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/* 139 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("text")) {
/* 140 */       result = cgfromcolumnMeta.getColumnName() + " text(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/*     */     }
/* 142 */     result = result + (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault()) ? " DEFAULT " + cgfromcolumnMeta.getFieldDefault() : " ");
/* 143 */     return result;
/*     */   }
/*     */ 
/*     */   private String getRenameFieldDesc(ColumnMeta cgfromcolumnMeta) {
/* 147 */     String result = "";
/* 148 */     if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("string"))
/* 149 */       result = cgfromcolumnMeta.getColumnName() + " character varying(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/* 150 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("date"))
/* 151 */       result = cgfromcolumnMeta.getColumnName() + " datetime" + " ";
/* 152 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("int"))
/* 153 */       result = cgfromcolumnMeta.getColumnName() + " int(" + cgfromcolumnMeta.getColumnSize() + ")" + " ";
/* 154 */     else if (cgfromcolumnMeta.getColunmType().equalsIgnoreCase("double")) {
/* 155 */       result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " ";
/*     */     }
/* 157 */     return result;
/*     */   }
/*     */ 
/*     */   public String getCommentSql(ColumnMeta columnMeta)
/*     */   {
/* 162 */     return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.DbTablePostgresHandleImpl
 * JD-Core Version:    0.6.2
 */