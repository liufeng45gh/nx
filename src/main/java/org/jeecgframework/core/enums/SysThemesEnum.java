/*    */ package org.jeecgframework.core.enums;
/*    */ 
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ 
/*    */ public enum SysThemesEnum
/*    */ {
/* 12 */   DEFAULT_STYLE("default", "main/main", "default", "经典风格"), 
/* 13 */   SHORTCUT_STYLE("shortcut", "main/shortcut_main", "default", "ShortCut风格"), 
/* 14 */   SLIDING_STYLE("sliding", "main/sliding_main", "default", "Sliding云桌面"), 
/* 15 */   ACE_STYLE("ace", "main/ace_main", "metro", "ACE平面风格"), 
/* 16 */   ACE_LE_STYLE("acele", "main/ace_main", "metrole", "ACE2风格"), 
/* 17 */   DIY("diy", "main/diy", "default", "diy风格"), 
/* 18 */   HPLUS("hplus", "main/hplus_main", "metrole", "H+风格");
/*    */ 
/*    */   private String style;
/*    */   private String indexPath;
/*    */   private String themes;
/*    */   private String desc;
/*    */ 
/*    */   private SysThemesEnum(String style, String indexPath, String themes, String desc)
/*    */   {
/* 41 */     this.style = style;
/* 42 */     this.indexPath = indexPath;
/* 43 */     this.themes = themes;
/* 44 */     this.desc = desc;
/*    */   }
/*    */ 
/*    */   public String getStyle() {
/* 48 */     return this.style;
/*    */   }
/*    */ 
/*    */   public void setStyle(String style) {
/* 52 */     this.style = style;
/*    */   }
/*    */ 
/*    */   public String getThemes() {
/* 56 */     return this.themes;
/*    */   }
/*    */ 
/*    */   public void setThemes(String themes) {
/* 60 */     this.themes = themes;
/*    */   }
/*    */ 
/*    */   public String getDesc() {
/* 64 */     return this.desc;
/*    */   }
/*    */ 
/*    */   public void setDesc(String desc) {
/* 68 */     this.desc = desc;
/*    */   }
/*    */ 
/*    */   public String getIndexPath() {
/* 72 */     return this.indexPath;
/*    */   }
/*    */ 
/*    */   public void setIndexPath(String indexPath) {
/* 76 */     this.indexPath = indexPath;
/*    */   }
/*    */ 
/*    */   public static SysThemesEnum toEnum(String style) {
/* 80 */     if (StringUtil.isEmpty(style))
/*    */     {
/* 82 */       return HPLUS;
/*    */     }
/* 84 */     for (SysThemesEnum item : values()) {
/* 85 */       if (item.getStyle().equals(style)) {
/* 86 */         return item;
/*    */       }
/*    */     }
/*    */ 
/* 90 */     return HPLUS;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 94 */     return "{style: " + this.style + ", indexPath: " + this.indexPath + ", themes: " + this.themes + ", desc: " + this.desc + "}";
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.enums.SysThemesEnum
 * JD-Core Version:    0.6.2
 */