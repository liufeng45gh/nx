/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jeecgframework.core.enums.SysThemesEnum;
/*     */ 
/*     */ public class SysThemesUtil
/*     */ {
/*     */   public static SysThemesEnum getSysTheme(HttpServletRequest request)
/*     */   {
/*  23 */     String indexStyle = null;
/*     */     try {
/*  25 */       Cookie[] cookies = request.getCookies();
/*  26 */       for (Cookie cookie : cookies)
/*  27 */         if ((cookie != null) && (!StringUtils.isEmpty(cookie.getName())))
/*     */         {
/*  30 */           if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE"))
/*  31 */             indexStyle = cookie.getValue();
/*     */         }
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/*  36 */     return SysThemesEnum.toEnum(indexStyle);
/*     */   }
/*     */ 
/*     */   public static String getEasyUiTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/*  45 */     StringBuffer sb = new StringBuffer("");
/*  46 */     sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/" + sysThemesEnum.getThemes() + "/easyui.css\" type=\"text/css\"></link>");
/*  47 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getEasyUiMainTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/*  56 */     StringBuffer sb = new StringBuffer("");
/*  57 */     if ("metro".equals(sysThemesEnum.getThemes()))
/*  58 */       sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/metro/main.css\" type=\"text/css\"></link>");
/*  59 */     else if ("metrole".equals(sysThemesEnum.getThemes())) {
/*  60 */       sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/metrole/main.css\" type=\"text/css\"></link>");
/*     */     }
/*  62 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getEasyUiIconTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/*  71 */     StringBuffer sb = new StringBuffer("");
/*  72 */     if ("metrole".equals(sysThemesEnum.getThemes()))
/*  73 */       sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/metrole/icon.css\" type=\"text/css\"></link>");
/*     */     else {
/*  75 */       sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
/*     */     }
/*  77 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getCommonTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/*  86 */     StringBuffer sb = new StringBuffer("");
/*  87 */     if ("metro".equals(sysThemesEnum.getThemes()))
/*  88 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/metro/common.css\" type=\"text/css\"></link>");
/*  89 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/*  90 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/metrole/common.css\" type=\"text/css\"></link>");
/*     */     else {
/*  92 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/tools/css/common.css\" type=\"text/css\"></link>");
/*     */     }
/*  94 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getLhgdialogTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/* 103 */     StringBuffer sb = new StringBuffer("");
/* 104 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 105 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js?skin=metro\"></script>");
/* 106 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/* 107 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js?skin=metrole\"></script>");
/*     */     else {
/* 109 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
/*     */     }
/* 111 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getBootstrapTabTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/* 120 */     StringBuffer sb = new StringBuffer("");
/* 121 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 122 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/ace/js/bootstrap-tab.js\"></script>");
/* 123 */     else if ("metrole".equals(sysThemesEnum.getThemes())) {
/* 124 */       sb.append("<script type=\"text/javascript\" src=\"plug-in/ace/js/bootstrap-tab.js\"></script>");
/*     */     }
/* 126 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getReportTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/* 136 */     StringBuffer sb = new StringBuffer("");
/* 137 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 138 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/graphreport/css/metro/report.css\" type=\"text/css\"></link>");
/* 139 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/* 140 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/graphreport/css/metrole/report.css\" type=\"text/css\"></link>");
/*     */     else {
/* 142 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/graphreport/css/report.css\" type=\"text/css\"></link>");
/*     */     }
/* 144 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getValidformDivfromTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/* 153 */     StringBuffer sb = new StringBuffer("");
/* 154 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 155 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metro/divfrom.css\" type=\"text/css\"/>");
/* 156 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/* 157 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metrole/divfrom.css\" type=\"text/css\"/>");
/*     */     else {
/* 159 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/divfrom.css\" type=\"text/css\"/>");
/*     */     }
/* 161 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getValidformStyleTheme(SysThemesEnum sysThemesEnum)
/*     */   {
/* 170 */     StringBuffer sb = new StringBuffer("");
/* 171 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 172 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metro/style.css\" type=\"text/css\"/>");
/* 173 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/* 174 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metrole/style.css\" type=\"text/css\"/>");
/*     */     else {
/* 176 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
/*     */     }
/* 178 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getValidformTablefrom(SysThemesEnum sysThemesEnum)
/*     */   {
/* 187 */     StringBuffer sb = new StringBuffer("");
/* 188 */     if ("metro".equals(sysThemesEnum.getThemes()))
/* 189 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metro/tablefrom.css\" type=\"text/css\"/>");
/* 190 */     else if ("metrole".equals(sysThemesEnum.getThemes()))
/* 191 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"/>");
/*     */     else {
/* 193 */       sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
/*     */     }
/* 195 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.SysThemesUtil
 * JD-Core Version:    0.6.2
 */