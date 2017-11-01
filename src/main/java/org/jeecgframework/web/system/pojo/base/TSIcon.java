/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_icon")
/*    */ public class TSIcon extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String iconName;
/*    */   private Short iconType;
/*    */   private String iconPath;
/*    */   private byte[] iconContent;
/*    */   private String iconClas;
/*    */   private String extend;
/*    */ 
/*    */   @Column(name="name", nullable=false, length=100)
/*    */   public String getIconName()
/*    */   {
/* 25 */     return this.iconName;
/*    */   }
/*    */ 
/*    */   public void setIconName(String iconName) {
/* 29 */     this.iconName = iconName;
/*    */   }
/*    */ 
/*    */   @Column(name="type")
/*    */   public Short getIconType() {
/* 34 */     return this.iconType;
/*    */   }
/*    */ 
/*    */   public void setIconType(Short iconType) {
/* 38 */     this.iconType = iconType;
/*    */   }
/*    */ 
/*    */   @Column(name="path", length=300, precision=300)
/*    */   public String getIconPath() {
/* 43 */     return this.iconPath;
/*    */   }
/*    */ 
/*    */   public void setIconPath(String iconPath) {
/* 47 */     this.iconPath = iconPath;
/*    */   }
/*    */   @Column(name="iconclas", length=200)
/*    */   public String getIconClas() {
/* 51 */     return this.iconClas;
/*    */   }
/*    */   public void setIconClas(String iconClas) {
/* 54 */     this.iconClas = iconClas;
/*    */   }
/*    */ 
/*    */   public void setIconContent(byte[] iconContent) {
/* 58 */     this.iconContent = iconContent;
/*    */   }
/*    */   @Column(name="content", length=1000, precision=3000)
/*    */   public byte[] getIconContent() {
/* 62 */     return this.iconContent;
/*    */   }
/*    */   @Column(name="extend")
/*    */   public String getExtend() {
/* 66 */     return this.extend;
/*    */   }
/*    */ 
/*    */   public void setExtend(String extend) {
/* 70 */     this.extend = extend;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSIcon
 * JD-Core Version:    0.6.2
 */