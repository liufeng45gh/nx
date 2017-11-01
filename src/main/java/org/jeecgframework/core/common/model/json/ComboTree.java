/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ComboTree
/*    */   implements Serializable
/*    */ {
/*    */   private String id;
/*    */   private String text;
/*    */   private String iconCls;
/* 12 */   private Boolean checked = Boolean.valueOf(false);
/*    */   private Map<String, Object> attributes;
/*    */   private List<ComboTree> children;
/* 15 */   private String state = "open";
/*    */ 
/*    */   public String getId() {
/* 18 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(String id) {
/* 22 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getText() {
/* 26 */     return this.text;
/*    */   }
/*    */ 
/*    */   public void setText(String text) {
/* 30 */     this.text = text;
/*    */   }
/*    */ 
/*    */   public Boolean getChecked() {
/* 34 */     return this.checked;
/*    */   }
/*    */ 
/*    */   public void setChecked(Boolean checked) {
/* 38 */     this.checked = checked;
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getAttributes() {
/* 42 */     return this.attributes;
/*    */   }
/*    */ 
/*    */   public void setAttributes(Map<String, Object> attributes) {
/* 46 */     this.attributes = attributes;
/*    */   }
/*    */ 
/*    */   public List<ComboTree> getChildren() {
/* 50 */     return this.children;
/*    */   }
/*    */ 
/*    */   public void setChildren(List<ComboTree> children) {
/* 54 */     this.children = children;
/*    */   }
/*    */ 
/*    */   public String getState() {
/* 58 */     return this.state;
/*    */   }
/*    */ 
/*    */   public void setState(String state) {
/* 62 */     this.state = state;
/*    */   }
/*    */ 
/*    */   public String getIconCls() {
/* 66 */     return this.iconCls;
/*    */   }
/*    */ 
/*    */   public void setIconCls(String iconCls) {
/* 70 */     this.iconCls = iconCls;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.ComboTree
 * JD-Core Version:    0.6.2
 */