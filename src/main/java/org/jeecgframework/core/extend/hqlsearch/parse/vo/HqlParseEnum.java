/*    */ package org.jeecgframework.core.extend.hqlsearch.parse.vo;
/*    */ 
/*    */ public enum HqlParseEnum
/*    */ {
/*  9 */   SUFFIX_COMMA(",", "多条数据"), 
/* 10 */   SUFFIX_ASTERISK("*", "模糊查询条件"), 
/* 11 */   SUFFIX_REG_ASTERISK("[*]", "模糊查询条件"), 
/* 12 */   SUFFIX_ASTERISK_VAGUE("%", "模糊查询值"), 
/* 13 */   SUFFIX_NOT_EQUAL("!", "不等于"), 
/* 14 */   SUFFIX_NOT_EQUAL_NULL("!NULL", "不等于空"), 
/* 15 */   SUFFIX_KG(" ", "空格"), 
/* 16 */   SUFFIX_NULL_STRING("", "空格");
/*    */ 
/*    */   private String value;
/*    */   private String msg;
/*    */ 
/*    */   private HqlParseEnum(String value, String msg) {
/* 23 */     this.value = value;
/* 24 */     this.msg = msg;
/*    */   }
/*    */ 
/*    */   public String getValue() {
/* 28 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value) {
/* 32 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String getMsg() {
/* 36 */     return this.msg;
/*    */   }
/*    */ 
/*    */   public void setMsg(String msg) {
/* 40 */     this.msg = msg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlParseEnum
 * JD-Core Version:    0.6.2
 */