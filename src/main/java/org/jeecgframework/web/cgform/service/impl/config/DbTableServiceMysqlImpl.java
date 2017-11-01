/*     */ package org.jeecgframework.web.cgform.service.impl.config;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jeecgframework.codegenerate.util.CodeResourceUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
/*     */ import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
/*     */ import org.jeecgframework.web.cgform.service.config.DbTableServiceI;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.DbTableUtil;
/*     */ import org.jeecgframework.web.cgform.service.impl.config.util.FieldNumComparator;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ 
/*     */ public class DbTableServiceMysqlImpl
/*     */   implements DbTableServiceI
/*     */ {
/*     */   public String createTableSQL(CgFormHeadEntity cgFormHead)
/*     */   {
/*  29 */     StringBuilder sb = new StringBuilder();
/*  30 */     sb.append("CREATE TABLE ");
/*  31 */     sb.append(cgFormHead.getTableName() + " (");
/*  32 */     CgFormFieldEntity agoColumn = null;
/*  33 */     Collections.sort(cgFormHead.getColumns(), new FieldNumComparator());
/*  34 */     String idField = "";
/*  35 */     Collections.sort(cgFormHead.getColumns(), new FieldNumComparator());
/*  36 */     for (int i = 0; i < cgFormHead.getColumns().size(); i++) {
/*  37 */       if (i > 0) agoColumn = (CgFormFieldEntity)cgFormHead.getColumns().get(i - 1);
/*  38 */       CgFormFieldEntity column = (CgFormFieldEntity)cgFormHead.getColumns().get(i);
/*  39 */       sb.append(getColumnPorperty(column, agoColumn, false));
/*  40 */       if (column.getIsKey().equals("Y")) {
/*  41 */         idField = idField + DbTableUtil.translatorToDbField(DbTableUtil.translatorToDbField(column.getFieldName())) + ",";
/*     */       }
/*     */     }
/*  44 */     sb.append(" PRIMARY KEY (" + idField.substring(0, idField.length() - 1) + ")");
/*  45 */     sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
/*  46 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String dropTableSQL(CgFormHeadEntity tableProperty)
/*     */   {
/*  51 */     return " DROP TABLE IF EXISTS " + tableProperty.getTableName() + " ;";
/*     */   }
/*     */ 
/*     */   public String updateTableSQL(CgFormHeadEntity cgFormHead, JdbcTemplate jdbcTemplate)
/*     */   {
/*  56 */     String sql = "select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name =  '" + 
/*  57 */       cgFormHead.getTableName() + "'and table_schema = '" + CodeResourceUtil.DATABASE_NAME + "';";
/*     */ 
/*  59 */     Map fieldMap = DbTableUtil.getColumnMap(jdbcTemplate.queryForList(sql));
/*  60 */     StringBuilder sb = new StringBuilder();
/*  61 */     sb.append(createChangeTableSql(cgFormHead));
/*  62 */     CgFormFieldEntity agoColumn = null;
/*  63 */     String idField = "";
/*     */ 
/*  65 */     Collections.sort(cgFormHead.getColumns(), new FieldNumComparator());
/*  66 */     for (int i = 0; i < cgFormHead.getColumns().size(); i++) {
/*  67 */       if (i > 0) agoColumn = (CgFormFieldEntity)cgFormHead.getColumns().get(i - 1);
/*  68 */       CgFormFieldEntity column = (CgFormFieldEntity)cgFormHead.getColumns().get(i);
/*     */ 
/*  70 */       if (fieldMap.containsKey(DbTableUtil.translatorToDbField(column.getFieldName()))) {
/*  71 */         sb.append(createUpdateColumnSql(column, agoColumn));
/*  72 */         fieldMap.remove(DbTableUtil.translatorToDbField(column.getFieldName()));
/*     */       } else {
/*  74 */         sb.append(createAddColumnSql(column, agoColumn));
/*     */       }
/*  76 */       if (column.getIsKey().equals("Y")) {
/*  77 */         idField = idField + DbTableUtil.translatorToDbField(column.getFieldName()) + ",";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  82 */     Collection c = fieldMap.values();
/*  83 */     Iterator it = c.iterator();
/*  84 */     while (it.hasNext()) {
/*  85 */       Map field = (Map)it.next();
/*  86 */       sb.append(createDropColumn(field.get("column_name").toString()));
/*     */     }
/*  88 */     sb.append("DROP PRIMARY KEY,ADD PRIMARY KEY (" + idField.substring(0, idField.length() - 1) + ")");
/*  89 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private String createAddColumnSql(CgFormFieldEntity column, CgFormFieldEntity agoColumn)
/*     */   {
/*  99 */     return " ADD COLUMN " + getColumnPorperty(column, agoColumn, true);
/*     */   }
/*     */ 
/*     */   private String createUpdateColumnSql(CgFormFieldEntity newColumn, CgFormFieldEntity agoColumn)
/*     */   {
/* 108 */     return " MODIFY COLUMN " + getColumnPorperty(newColumn, agoColumn, true);
/*     */   }
/*     */ 
/*     */   private String createDropColumn(String fieldName)
/*     */   {
/* 116 */     return " DROP COLUMN " + fieldName + ",";
/*     */   }
/*     */ 
/*     */   private String getColumnPorperty(CgFormFieldEntity column, CgFormFieldEntity agoColumn, boolean isUpdate)
/*     */   {
/* 127 */     String result = " " + DbTableUtil.translatorToDbField(column.getFieldName()) + " " + 
/* 128 */       getFieldType(column) + " ";
/* 129 */     result = result + (StringUtil.isEmpty(column.getIsNull()) ? " NOT NULL " : "NULL");
/* 130 */     result = result + " COMMENT '" + column.getContent() + "' ";
/* 131 */     if (isUpdate) {
/* 132 */       String agoFiled = " FIRST ";
/* 133 */       if (agoColumn != null) {
/* 134 */         agoFiled = " AFTER " + DbTableUtil.translatorToDbField(agoColumn.getFieldName());
/*     */       }
/* 136 */       result = result + agoFiled;
/*     */     }
/* 138 */     return result + ", ";
/*     */   }
/*     */ 
/*     */   private String getFieldType(CgFormFieldEntity cloumn)
/*     */   {
/* 148 */     String result = "";
/* 149 */     if (cloumn.getType().equals("string"))
/* 150 */       result = "varchar(" + cloumn.getLength() + ")";
/* 151 */     else if (cloumn.getType().equals("Date"))
/* 152 */       result = "datetime";
/* 153 */     else if (cloumn.getType().equals("int"))
/* 154 */       result = cloumn.getType() + "(" + cloumn.getLength() + ")";
/* 155 */     else if (cloumn.getType().equals("double")) {
/* 156 */       result = cloumn.getType() + "(" + cloumn.getLength() + "," + cloumn.getPointLength() + ")";
/*     */     }
/* 158 */     return result;
/*     */   }
/*     */ 
/*     */   private String createChangeTableSql(CgFormHeadEntity table)
/*     */   {
/* 166 */     return "ALTER TABLE " + table.getTableName() + " ";
/*     */   }
/*     */ 
/*     */   public String createIsExitSql(String tableName)
/*     */   {
/* 171 */     return "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_NAME='" + tableName + "' and table_schema = '" + CodeResourceUtil.DATABASE_NAME + "';";
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.service.impl.config.DbTableServiceMysqlImpl
 * JD-Core Version:    0.6.2
 */