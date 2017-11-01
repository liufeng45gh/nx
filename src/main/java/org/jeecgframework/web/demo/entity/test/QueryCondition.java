/*    */ package org.jeecgframework.web.demo.entity.test;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.core.util.PropertiesUtil;
/*    */ 
/*    */ public class QueryCondition
/*    */ {
/*    */   String field;
/*    */   String type;
/*    */   String condition;
/*    */   String value;
/*    */   String relation;
/*    */   List<QueryCondition> children;
/*    */ 
/*    */   public List<QueryCondition> getChildren()
/*    */   {
/* 17 */     return this.children;
/*    */   }
/*    */   public void setChildren(List<QueryCondition> children) {
/* 20 */     this.children = children;
/*    */   }
/*    */   public String getField() {
/* 23 */     return this.field;
/*    */   }
/*    */   public void setField(String field) {
/* 26 */     this.field = field;
/*    */   }
/*    */   public String getType() {
/* 29 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 32 */     this.type = type;
/*    */   }
/*    */   public String getCondition() {
/* 35 */     return this.condition;
/*    */   }
/*    */   public void setCondition(String condition) {
/* 38 */     this.condition = condition;
/*    */   }
/*    */   public String getValue() {
/* 41 */     return this.value;
/*    */   }
/*    */   public void setValue(String value) {
/* 44 */     this.value = value;
/*    */   }
/*    */   public String getRelation() {
/* 47 */     return this.relation;
/*    */   }
/*    */   public void setRelation(String relation) {
/* 50 */     this.relation = relation;
/*    */   }
/*    */   public String toString() {
/* 53 */     StringBuffer sb = new StringBuffer();
/* 54 */     sb.append(this.relation).append(" ");
/* 55 */     sb.append(this.field).append(" ")
/* 56 */       .append(this.condition).append(" ");
/* 57 */     if (("Integer".equals(this.type)) || 
/* 58 */       ("BigDecimal".equals(this.type)) || 
/* 59 */       ("Double".equals(this.type)) || 
/* 60 */       ("Long".equals(this.type))) {
/* 61 */       sb.append(this.value);
/* 62 */     } else if ("Date".equals(this.type)) {
/* 63 */       PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
/* 64 */       String dbtype = util.readProperty("jdbc.url.jeecg");
/* 65 */       if ("oracle".equalsIgnoreCase(dbtype)) {
/* 66 */         sb.append("to_date(");
/*    */       }
/*    */ 
/* 69 */       sb.append("'").append(this.value).append("'");
/* 70 */       if ("oracle".equalsIgnoreCase(dbtype))
/* 71 */         sb.append(",'yyyy-MM-dd')");
/*    */     }
/*    */     else {
/* 74 */       sb.append("'").append(this.value.replaceAll("'", "'")).append("'");
/*    */     }
/* 76 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.entity.test.QueryCondition
 * JD-Core Version:    0.6.2
 */