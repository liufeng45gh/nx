/*    */ package org.jeecgframework.tag.vo.easyui;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ComboTreeModel
/*    */   implements Serializable
/*    */ {
/*    */   private String idField;
/*    */   private String textField;
/*    */   private String iconCls;
/*    */   private String childField;
/*    */   private String srcField;
/*    */ 
/*    */   public ComboTreeModel(String idField, String textField, String childField)
/*    */   {
/* 19 */     this.idField = idField;
/* 20 */     this.textField = textField;
/* 21 */     this.childField = childField;
/*    */   }
/*    */   public ComboTreeModel(String idField, String textField, String childField, String srcField) {
/* 24 */     this.idField = idField;
/* 25 */     this.textField = textField;
/* 26 */     this.childField = childField;
/* 27 */     this.srcField = srcField;
/*    */   }
/*    */   public String getIconCls() {
/* 30 */     return this.iconCls;
/*    */   }
/*    */   public void setIconCls(String iconCls) {
/* 33 */     this.iconCls = iconCls;
/*    */   }
/*    */   public String getChildField() {
/* 36 */     return this.childField;
/*    */   }
/*    */   public void setChildField(String childField) {
/* 39 */     this.childField = childField;
/*    */   }
/*    */   public String getIdField() {
/* 42 */     return this.idField;
/*    */   }
/*    */   public void setIdField(String idField) {
/* 45 */     this.idField = idField;
/*    */   }
/*    */   public String getTextField() {
/* 48 */     return this.textField;
/*    */   }
/*    */   public void setTextField(String textField) {
/* 51 */     this.textField = textField;
/*    */   }
/*    */   public String getSrcField() {
/* 54 */     return this.srcField;
/*    */   }
/*    */   public void setSrcField(String srcField) {
/* 57 */     this.srcField = srcField;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.easyui.ComboTreeModel
 * JD-Core Version:    0.6.2
 */