/*    */ package org.jeecgframework.core.extend.hqlsearch.parse.vo;
/*    */ 
/*    */ public enum HqlRuleEnum
/*    */ {
/*  9 */   GT(">", "大于"), 
/* 10 */   GE(">=", "大于等于"), 
/* 11 */   LT("<", "小于"), 
/* 12 */   LE("<=", "小于等于"), 
/* 13 */   EQ("=", "等于"), 
/* 14 */   NE("!=", "不等于"), 
/* 15 */   IN("IN", "包含"), 
/* 16 */   LIKE("LIKE", "左右模糊"), 
/* 17 */   LEFT_LIKE("LEFT_LIKE", "左模糊"), 
/* 18 */   RIGHT_LIKE("RIGHT_LIKE", "右模糊");
/*    */ 
/*    */   private String value;
/*    */   private String msg;
/*    */ 
/*    */   private HqlRuleEnum(String value, String msg) {
/* 25 */     this.value = value;
/* 26 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public String getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 34 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 38 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 42 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public static HqlRuleEnum getByValue(String value) {
/* 46 */     for (HqlRuleEnum val : values()) {
/* 47 */       if (val.getValue().equals(value)) {
/* 48 */         return val;
/*    */       }
/*    */     }
/* 51 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum
 * JD-Core Version:    0.6.2
 */