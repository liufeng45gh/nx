/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class BrowserUtils
/*     */ {
/*     */   private static final String IE11 = "rv:11.0";
/*     */   private static final String IE10 = "MSIE 10.0";
/*     */   private static final String IE9 = "MSIE 9.0";
/*     */   private static final String IE8 = "MSIE 8.0";
/*     */   private static final String IE7 = "MSIE 7.0";
/*     */   private static final String IE6 = "MSIE 6.0";
/*     */   private static final String MAXTHON = "Maxthon";
/*     */   private static final String QQ = "QQBrowser";
/*     */   private static final String GREEN = "GreenBrowser";
/*     */   private static final String SE360 = "360SE";
/*     */   private static final String FIREFOX = "Firefox";
/*     */   private static final String OPERA = "Opera";
/*     */   private static final String CHROME = "Chrome";
/*     */   private static final String SAFARI = "Safari";
/*     */   private static final String OTHER = "其它";
/* 154 */   private static Map<String, String> langMap = new HashMap();
/*     */   private static final String ZH = "zh";
/*     */   private static final String ZH_CN = "zh-cn";
/*     */   private static final String EN = "en";
/*     */   private static final String EN_US = "en";
/*     */ 
/*     */   static
/*     */   {
/* 164 */     langMap.put("zh", "zh-cn");
/* 165 */     langMap.put("en", "en");
/*     */   }
/*     */ 
/*     */   public static boolean isIE(HttpServletRequest request)
/*     */   {
/*  19 */     return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0) || 
/*  20 */       (request
/*  20 */       .getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0);
/*     */   }
/*     */ 
/*     */   public static Double getIEversion(HttpServletRequest request)
/*     */   {
/*  31 */     Double version = Double.valueOf(0.0D);
/*  32 */     if (getBrowserType(request, "rv:11.0"))
/*  33 */       version = Double.valueOf(11.0D);
/*  34 */     else if (getBrowserType(request, "MSIE 10.0"))
/*  35 */       version = Double.valueOf(10.0D);
/*  36 */     else if (getBrowserType(request, "MSIE 9.0"))
/*  37 */       version = Double.valueOf(9.0D);
/*  38 */     else if (getBrowserType(request, "MSIE 8.0"))
/*  39 */       version = Double.valueOf(8.0D);
/*  40 */     else if (getBrowserType(request, "MSIE 7.0"))
/*  41 */       version = Double.valueOf(7.0D);
/*  42 */     else if (getBrowserType(request, "MSIE 6.0")) {
/*  43 */       version = Double.valueOf(6.0D);
/*     */     }
/*  45 */     return version;
/*     */   }
/*     */ 
/*     */   public static BrowserType getBrowserType(HttpServletRequest request)
/*     */   {
/*  55 */     BrowserType browserType = null;
/*  56 */     if (getBrowserType(request, "rv:11.0")) {
/*  57 */       browserType = BrowserType.IE11;
/*     */     }
/*  59 */     if (getBrowserType(request, "MSIE 10.0")) {
/*  60 */       browserType = BrowserType.IE10;
/*     */     }
/*  62 */     if (getBrowserType(request, "MSIE 9.0")) {
/*  63 */       browserType = BrowserType.IE9;
/*     */     }
/*  65 */     if (getBrowserType(request, "MSIE 8.0")) {
/*  66 */       browserType = BrowserType.IE8;
/*     */     }
/*  68 */     if (getBrowserType(request, "MSIE 7.0")) {
/*  69 */       browserType = BrowserType.IE7;
/*     */     }
/*  71 */     if (getBrowserType(request, "MSIE 6.0")) {
/*  72 */       browserType = BrowserType.IE6;
/*     */     }
/*  74 */     if (getBrowserType(request, "Firefox")) {
/*  75 */       browserType = BrowserType.Firefox;
/*     */     }
/*  77 */     if (getBrowserType(request, "Safari")) {
/*  78 */       browserType = BrowserType.Safari;
/*     */     }
/*  80 */     if (getBrowserType(request, "Chrome")) {
/*  81 */       browserType = BrowserType.Chrome;
/*     */     }
/*  83 */     if (getBrowserType(request, "Opera")) {
/*  84 */       browserType = BrowserType.Opera;
/*     */     }
/*  86 */     if (getBrowserType(request, "Camino")) {
/*  87 */       browserType = BrowserType.Camino;
/*     */     }
/*  89 */     return browserType;
/*     */   }
/*     */ 
/*     */   private static boolean getBrowserType(HttpServletRequest request, String brosertype)
/*     */   {
/*  94 */     return request.getHeader("USER-AGENT").toLowerCase()
/*  95 */       .indexOf(brosertype) > 0;
/*     */   }
/*     */ 
/*     */   public static String checkBrowse(HttpServletRequest request)
/*     */   {
/* 115 */     String userAgent = request.getHeader("USER-AGENT");
/* 116 */     if (regex("Opera", userAgent))
/* 117 */       return "Opera";
/* 118 */     if (regex("Chrome", userAgent))
/* 119 */       return "Chrome";
/* 120 */     if (regex("Firefox", userAgent))
/* 121 */       return "Firefox";
/* 122 */     if (regex("Safari", userAgent))
/* 123 */       return "Safari";
/* 124 */     if (regex("360SE", userAgent))
/* 125 */       return "360SE";
/* 126 */     if (regex("GreenBrowser", userAgent))
/* 127 */       return "GreenBrowser";
/* 128 */     if (regex("QQBrowser", userAgent))
/* 129 */       return "QQBrowser";
/* 130 */     if (regex("Maxthon", userAgent))
/* 131 */       return "Maxthon";
/* 132 */     if (regex("rv:11.0", userAgent))
/* 133 */       return "rv:11.0";
/* 134 */     if (regex("MSIE 10.0", userAgent))
/* 135 */       return "MSIE 10.0";
/* 136 */     if (regex("MSIE 9.0", userAgent))
/* 137 */       return "MSIE 9.0";
/* 138 */     if (regex("MSIE 8.0", userAgent))
/* 139 */       return "MSIE 8.0";
/* 140 */     if (regex("MSIE 7.0", userAgent))
/* 141 */       return "MSIE 7.0";
/* 142 */     if (regex("MSIE 6.0", userAgent))
/* 143 */       return "MSIE 6.0";
/* 144 */     return "其它";
/*     */   }
/*     */ 
/*     */   public static boolean regex(String regex, String str) {
/* 148 */     Pattern p = Pattern.compile(regex, 8);
/* 149 */     Matcher m = p.matcher(str);
/* 150 */     return m.find();
/*     */   }
/*     */ 
/*     */   public static String getBrowserLanguage(HttpServletRequest request)
/*     */   {
/* 170 */     String browserLang = request.getLocale().getLanguage();
/* 171 */     String browserLangCode = (String)langMap.get(browserLang);
/*     */ 
/* 173 */     if (browserLangCode == null)
/*     */     {
/* 175 */       browserLangCode = "en";
/*     */     }
/* 177 */     return browserLangCode;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.BrowserUtils
 * JD-Core Version:    0.6.2
 */