/*    */ package org.jeecgframework.web.cgform.entity.config;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.jeecgframework.poi.excel.annotation.Excel;
/*    */ import org.jeecgframework.poi.excel.annotation.ExcelTarget;
/*    */ 
/*    */ @ExcelTarget("cgFormFieldVO")
/*    */ public class CgFormFieldVO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8248068871232905945L;
/*    */   private String id;
/*    */ 
/*    */   @Excel(name="字段名称", orderNum="1")
/*    */   private String fieldName;
/*    */ 
/*    */   @Excel(name="字段备注", orderNum="2")
/*    */   private String content;
/*    */ 
/*    */   @Excel(name="字段类型", orderNum="3")
/*    */   private String type;
/*    */ 
/*    */   @Excel(name="字段长度", orderNum="4")
/*    */   private String length;
/*    */ 
/*    */   @Excel(name="小数点长度", orderNum="5")
/*    */   private String pointLength;
/*    */ 
/*    */   @Excel(name="默认值", orderNum="6")
/*    */   private String fieldDefault;
/*    */ 
/*    */   @Excel(name="允许空值", orderNum="7")
/*    */   private String isNull;
/*    */ 
/*    */   public String getId()
/*    */   {
/* 44 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 47 */     this.id = id;
/*    */   }
/*    */   public String getFieldName() {
/* 50 */     return this.fieldName;
/*    */   }
/*    */   public void setFieldName(String fieldName) {
/* 53 */     this.fieldName = fieldName;
/*    */   }
/*    */   public String getLength() {
/* 56 */     return this.length;
/*    */   }
/*    */   public void setLength(String length) {
/* 59 */     this.length = length;
/*    */   }
/*    */   public String getPointLength() {
/* 62 */     return this.pointLength;
/*    */   }
/*    */   public void setPointLength(String pointLength) {
/* 65 */     this.pointLength = pointLength;
/*    */   }
/*    */   public String getType() {
/* 68 */     return this.type;
/*    */   }
/*    */   public void setType(String type) {
/* 71 */     this.type = type;
/*    */   }
/*    */   public String getIsNull() {
/* 74 */     return this.isNull;
/*    */   }
/*    */   public void setIsNull(String isNull) {
/* 77 */     this.isNull = isNull;
/*    */   }
/*    */   public String getContent() {
/* 80 */     return this.content;
/*    */   }
/*    */   public void setContent(String content) {
/* 83 */     this.content = content;
/*    */   }
/*    */   public String getFieldDefault() {
/* 86 */     return this.fieldDefault;
/*    */   }
/*    */   public void setFieldDefault(String fieldDefault) {
/* 89 */     this.fieldDefault = fieldDefault;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 93 */     return "CgFormFieldVO [id=" + this.id + ", fieldName=" + this.fieldName + 
/* 94 */       ", content=" + this.content + ", type=" + this.type + ", length=" + 
/* 95 */       this.length + ", pointLength=" + this.pointLength + ", fieldDefault=" + 
/* 96 */       this.fieldDefault + ", isNull=" + this.isNull + "]";
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.config.CgFormFieldVO
 * JD-Core Version:    0.6.2
 */