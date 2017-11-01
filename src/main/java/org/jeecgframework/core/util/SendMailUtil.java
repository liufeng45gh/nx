/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import freemarker.template.Configuration;
/*     */ import freemarker.template.Template;
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.mail.HtmlEmail;
/*     */ import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
/*     */ 
/*     */ public class SendMailUtil
/*     */ {
/*     */   private static final String from = "zhangdh@163.com";
/*     */   private static final String fromName = "测试公司";
/*     */   private static final String charSet = "utf-8";
/*     */   private static final String username = "zhangdh@163.com";
/*     */   private static final String password = "123456";
/*  28 */   private static Map<String, String> hostMap = new HashMap();
/*     */ 
/*     */   static {
/*  31 */     hostMap.put("smtp.126", "smtp.126.com");
/*     */ 
/*  33 */     hostMap.put("smtp.qq", "smtp.qq.com");
/*     */ 
/*  36 */     hostMap.put("smtp.163", "smtp.163.com");
/*     */ 
/*  39 */     hostMap.put("smtp.sina", "smtp.sina.com.cn");
/*     */ 
/*  42 */     hostMap.put("smtp.tom", "smtp.tom.com");
/*     */ 
/*  45 */     hostMap.put("smtp.263", "smtp.263.net");
/*     */ 
/*  48 */     hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");
/*     */ 
/*  51 */     hostMap.put("smtp.hotmail", "smtp.live.com");
/*     */ 
/*  54 */     hostMap.put("smtp.gmail", "smtp.gmail.com");
/*  55 */     hostMap.put("smtp.port.gmail", "465");
/*     */   }
/*     */ 
/*     */   public static String getHost(String email) throws Exception {
/*  59 */     Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
/*  60 */     Matcher matcher = pattern.matcher(email);
/*  61 */     String key = "unSupportEmail";
/*  62 */     if (matcher.find()) {
/*  63 */       key = "smtp." + matcher.group(1);
/*     */     }
/*  65 */     if (hostMap.containsKey(key)) {
/*  66 */       return (String)hostMap.get(key);
/*     */     }
/*  68 */     throw new Exception("unSupportEmail");
/*     */   }
/*     */ 
/*     */   public static int getSmtpPort(String email) throws Exception
/*     */   {
/*  73 */     Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
/*  74 */     Matcher matcher = pattern.matcher(email);
/*  75 */     String key = "unSupportEmail";
/*  76 */     if (matcher.find()) {
/*  77 */       key = "smtp.port." + matcher.group(1);
/*     */     }
/*  79 */     if (hostMap.containsKey(key)) {
/*  80 */       return Integer.parseInt((String)hostMap.get(key));
/*     */     }
/*  82 */     return 25;
/*     */   }
/*     */ 
/*     */   public static void sendFtlMail(String toMailAddr, String subject, String templatePath, Map<String, Object> map)
/*     */   {
/*  95 */     Template template = null;
/*  96 */     Configuration freeMarkerConfig = null;
/*  97 */     HtmlEmail hemail = new HtmlEmail();
/*     */     try {
/*  99 */       hemail.setHostName(getHost("zhangdh@163.com"));
/* 100 */       hemail.setSmtpPort(getSmtpPort("zhangdh@163.com"));
/* 101 */       hemail.setCharset("utf-8");
/* 102 */       hemail.addTo(toMailAddr);
/* 103 */       hemail.setFrom("zhangdh@163.com", "测试公司");
/* 104 */       hemail.setAuthentication("zhangdh@163.com", "123456");
/* 105 */       hemail.setSubject(subject);
/* 106 */       freeMarkerConfig = new Configuration();
/* 107 */       freeMarkerConfig.setDirectoryForTemplateLoading(new File(getFilePath()));
/*     */ 
/* 109 */       template = freeMarkerConfig.getTemplate(getFileName(templatePath), new Locale("Zh_cn"), "UTF-8");
/*     */ 
/* 111 */       String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
/* 112 */       LogUtil.info(htmlText);
/* 113 */       hemail.setMsg(htmlText);
/* 114 */       hemail.send();
/* 115 */       LogUtil.info("email send true!");
/*     */     } catch (Exception e) {
/* 117 */       e.printStackTrace();
/* 118 */       LogUtil.info("email send error!");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendCommonMail(String toMailAddr, String subject, String message)
/*     */   {
/* 129 */     HtmlEmail hemail = new HtmlEmail();
/*     */     try {
/* 131 */       hemail.setHostName(getHost("zhangdh@163.com"));
/* 132 */       hemail.setSmtpPort(getSmtpPort("zhangdh@163.com"));
/* 133 */       hemail.setCharset("utf-8");
/* 134 */       hemail.addTo(toMailAddr);
/* 135 */       hemail.setFrom("zhangdh@163.com", "测试公司");
/* 136 */       hemail.setAuthentication("zhangdh@163.com", "123456");
/* 137 */       hemail.setSubject(subject);
/* 138 */       hemail.setMsg(message);
/* 139 */       hemail.send();
/* 140 */       LogUtil.info("email send true!");
/*     */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       LogUtil.info("email send error!");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getHtmlText(String templatePath, Map<String, Object> map)
/*     */   {
/* 149 */     Template template = null;
/* 150 */     String htmlText = "";
/*     */     try {
/* 152 */       Configuration freeMarkerConfig = null;
/* 153 */       freeMarkerConfig = new Configuration();
/* 154 */       freeMarkerConfig.setDirectoryForTemplateLoading(new File(getFilePath()));
/*     */ 
/* 156 */       template = freeMarkerConfig.getTemplate(getFileName(templatePath), new Locale("Zh_cn"), "UTF-8");
/*     */ 
/* 158 */       htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
/* 159 */       LogUtil.info(htmlText);
/*     */     } catch (Exception e) {
/* 161 */       e.printStackTrace();
/*     */     }
/* 163 */     return htmlText;
/*     */   }
/*     */ 
/*     */   private static String getFilePath()
/*     */   {
/* 169 */     String path = getAppPath(SendMailUtil.class);
/* 170 */     path = path + File.separator + "mailtemplate" + File.separator;
/* 171 */     path = path.replace("\\", "/");
/* 172 */     LogUtil.info(path);
/* 173 */     return path;
/*     */   }
/*     */ 
/*     */   private static String getFileName(String path) {
/* 177 */     path = path.replace("\\", "/");
/* 178 */     LogUtil.info(path);
/* 179 */     return path.substring(path.lastIndexOf("/") + 1);
/*     */   }
/*     */ 
/*     */   public static String getAppPath(Class cls)
/*     */   {
/* 185 */     if (cls == null)
/* 186 */       throw new IllegalArgumentException("参数不能为空！");
/* 187 */     ClassLoader loader = cls.getClassLoader();
/*     */ 
/* 189 */     String clsName = cls.getName() + ".class";
/*     */ 
/* 191 */     Package pack = cls.getPackage();
/* 192 */     String path = "";
/*     */ 
/* 194 */     if (pack != null) {
/* 195 */       String packName = pack.getName();
/*     */ 
/* 197 */       if ((packName.startsWith("java.")) || (packName.startsWith("javax."))) {
/* 198 */         throw new IllegalArgumentException("不要传送系统类！");
/*     */       }
/* 200 */       clsName = clsName.substring(packName.length() + 1);
/*     */ 
/* 202 */       if (packName.indexOf(".") < 0) {
/* 203 */         path = packName + "/";
/*     */       } else {
/* 205 */         int start = 0; int end = 0;
/* 206 */         end = packName.indexOf(".");
/* 207 */         while (end != -1) {
/* 208 */           path = path + packName.substring(start, end) + "/";
/* 209 */           start = end + 1;
/* 210 */           end = packName.indexOf(".", start);
/*     */         }
/* 212 */         path = path + packName.substring(start) + "/";
/*     */       }
/*     */     }
/*     */ 
/* 216 */     URL url = loader.getResource(path + clsName);
/*     */ 
/* 218 */     String realPath = url.getPath();
/*     */ 
/* 220 */     int pos = realPath.indexOf("file:");
/* 221 */     if (pos > -1) {
/* 222 */       realPath = realPath.substring(pos + 5);
/*     */     }
/* 224 */     pos = realPath.indexOf(path + clsName);
/* 225 */     realPath = realPath.substring(0, pos - 1);
/*     */ 
/* 227 */     if (realPath.endsWith("!")) {
/* 228 */       realPath = realPath.substring(0, realPath.lastIndexOf("/"));
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 236 */       realPath = URLDecoder.decode(realPath, "utf-8");
/*     */     } catch (Exception e) {
/* 238 */       throw new RuntimeException(e);
/*     */     }
/* 240 */     LogUtil.info("realPath----->" + realPath);
/* 241 */     return realPath;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 267 */     Map map = new HashMap();
/* 268 */     map.put("subject", "测试标题");
/* 269 */     map.put("content", "测试 内容");
/* 270 */     String templatePath = "mailtemplate/test.ftl";
/* 271 */     sendFtlMail("test@et-bank.com", "sendemail test!", templatePath, map);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.SendMailUtil
 * JD-Core Version:    0.6.2
 */