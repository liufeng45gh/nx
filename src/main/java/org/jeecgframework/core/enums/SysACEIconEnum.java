/*    */ package org.jeecgframework.core.enums;
/*    */ 
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ 
/*    */ public enum SysACEIconEnum
/*    */ {
/* 12 */   default_icon("default", "icon-list-alt", "默认"), 
/* 13 */   back_icon("back", "icon-briefcase", "返回"), 
/* 14 */   pie_icon("pie", "icon-bar-chart", "小饼状图"), 
/* 15 */   pictures_icon("pictures", "icon-picture", "图片"), 
/* 16 */   pencil_icon("pencil", "icon-edit", "笔"), 
/* 17 */   map_icon("map", "icon-globe", "小地图"), 
/* 18 */   group_add_icon("group_add", "icon-group", "组"), 
/* 19 */   calculator_icon("calculator", "icon-desktop", "计算器"), 
/* 20 */   folder_icon("folder", "icon-list", "文件夹");
/*    */ 
/*    */   private String style;
/*    */   private String themes;
/*    */   private String desc;
/*    */ 
/*    */   private SysACEIconEnum(String style, String themes, String desc)
/*    */   {
/* 39 */     this.style = style;
/* 40 */     this.themes = themes;
/* 41 */     this.desc = desc;
/*    */   }
/*    */ 
/*    */   public String getStyle() {
/* 45 */     return this.style;
/*    */   }
/*    */ 
/*    */   public void setStyle(String style) {
/* 49 */     this.style = style;
/*    */   }
/*    */ 
/*    */   public String getThemes() {
/* 53 */     return this.themes;
/*    */   }
/*    */ 
/*    */   public void setThemes(String themes) {
/* 57 */     this.themes = themes;
/*    */   }
/*    */ 
/*    */   public String getDesc() {
/* 61 */     return this.desc;
/*    */   }
/*    */ 
/*    */   public void setDesc(String desc) {
/* 65 */     this.desc = desc;
/*    */   }
/*    */ 
/*    */   public static SysACEIconEnum toEnum(String style)
/*    */   {
/* 70 */     if (StringUtil.isEmpty(style))
/*    */     {
/* 72 */       return default_icon;
/*    */     }
/* 74 */     for (SysACEIconEnum item : values()) {
/* 75 */       if (item.getStyle().equals(style)) {
/* 76 */         return item;
/*    */       }
/*    */     }
/*    */ 
/* 80 */     return default_icon;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 84 */     return "{style: " + this.style + ", themes: " + this.themes + ", desc: " + this.desc + "}";
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.enums.SysACEIconEnum
 * JD-Core Version:    0.6.2
 */