/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ResourceBundle;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ 
/*     */ public class JeecgSqlUtil
/*     */ {
/*     */   private static final String KEY_01 = "service";
/*     */   private static final String KEY_02 = "impl";
/*     */   private static final String KEY_03 = "Impl.";
/*     */   private static final String PACKAGE_SQL = "sql";
/*     */   private static final String SUFFIX_SQL = ".sql";
/*     */   private static final String SUFFIX_D = ".";
/*     */   private static final String SUFFIX_X = "/";
/*  37 */   private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");
/*     */   private static Cache dictCache;
/*     */ 
/*     */   static
/*     */   {
/*  40 */     if (dictCache == null)
/*  41 */       dictCache = CacheManager.getInstance().getCache("dictCache");
/*     */   }
/*     */ 
/*     */   private static String loadStringFromFile(File file)
/*     */     throws IOException
/*     */   {
/*  51 */     return loadStringFromFile(file, "UTF-8");
/*     */   }
/*     */ 
/*     */   private static String loadStringFromFile(File file, String encoding)
/*     */     throws IOException
/*     */   {
/*  63 */     BufferedReader reader = null;
/*     */     try {
/*  65 */       reader = new BufferedReader(new InputStreamReader(
/*  66 */         new FileInputStream(file), encoding));
/*  67 */       StringBuilder builder = new StringBuilder();
/*  68 */       char[] chars = new char[4096];
/*     */ 
/*  70 */       int length = 0;
/*     */ 
/*  72 */       while ((length = reader.read(chars)) > 0)
/*     */       {
/*  74 */         builder.append(chars, 0, length);
/*     */       }
/*     */ 
/*  78 */       return builder.toString();
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/*  84 */         if (reader != null)
/*  85 */           reader.close();
/*     */       }
/*     */       catch (IOException e)
/*     */       {
/*  89 */         throw new RuntimeException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String getFlieTxt(String fileUrl)
/*     */   {
/* 104 */     LogUtil.info("---------------------------------------sql 路径 :" + fileUrl);
/* 105 */     String sql = null;
/*     */     try {
/* 107 */       sql = loadStringFromFile(new File(fileUrl));
/*     */     } catch (IOException e) {
/* 109 */       e.printStackTrace();
/*     */     }
/* 111 */     return sql;
/*     */   }
/*     */ 
/*     */   public static String getMethodSql(String methodUrl)
/*     */   {
/* 118 */     if ("DEV".equals(bundle.getObject("sqlReadMode"))) {
/* 119 */       return getMethodSqlLogicJar(methodUrl);
/*     */     }
/*     */ 
/* 122 */     if ("PUB".equals(bundle.getObject("sqlReadMode"))) {
/* 123 */       Element element = dictCache.get(methodUrl);
/* 124 */       if (element == null) {
/* 125 */         element = new Element(methodUrl, getMethodSqlLogicJar(methodUrl));
/*     */ 
/* 127 */         dictCache.put(element);
/*     */       }
/* 129 */       return element.getValue().toString();
/*     */     }
/*     */ 
/* 132 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getMethodSqlLogic(String methodUrl)
/*     */   {
/* 142 */     String head = methodUrl.substring(0, methodUrl.indexOf("service"));
/* 143 */     String end = methodUrl.substring(methodUrl.indexOf("impl") + "impl".length()).replace("Impl.", "_");
/* 144 */     String sqlurl = head + "sql" + end;
/* 145 */     sqlurl = sqlurl.replace(".", "/");
/* 146 */     sqlurl = sqlurl + ".sql";
/*     */ 
/* 148 */     String projectPath = getAppPath(JeecgSqlUtil.class);
/* 149 */     sqlurl = projectPath + "/" + sqlurl;
/* 150 */     LogUtil.info(sqlurl);
/* 151 */     return getFlieTxt(sqlurl);
/*     */   }
/*     */ 
/*     */   public static String getMethodSqlLogicJar(String methodUrl)
/*     */   {
/* 160 */     StringBuffer sb = new StringBuffer();
/* 161 */     String head = methodUrl.substring(0, methodUrl.indexOf("service"));
/* 162 */     String end = methodUrl.substring(methodUrl.indexOf("impl") + "impl".length()).replace("Impl.", "_");
/* 163 */     String sqlurl = head + "sql" + end;
/* 164 */     sqlurl = sqlurl.replace(".", "/");
/* 165 */     sqlurl = "/" + sqlurl + ".sql";
/*     */ 
/* 168 */     InputStream is = JeecgSqlUtil.class.getResourceAsStream(sqlurl);
/* 169 */     BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 170 */     String s = "";
/*     */     try {
/* 172 */       while ((s = br.readLine()) != null)
/* 173 */         sb.append(s + " ");
/*     */     } catch (IOException e) {
/* 175 */       e.printStackTrace();
/*     */     }
/* 177 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static String getMethodSqlLogicOld(String methodUrl)
/*     */   {
/* 193 */     methodUrl = methodUrl.substring(17).replace("Impl", "").replace(".", "/");
/* 194 */     String[] str = methodUrl.split("/");
/* 195 */     StringBuffer sb = new StringBuffer();
/* 196 */     int num = 2;
/* 197 */     int length = str.length;
/* 198 */     for (String s : str)
/*     */     {
/* 200 */       if (num < length) {
/* 201 */         sb.append(s);
/* 202 */         sb.append("/");
/* 203 */       } else if (num == length) {
/* 204 */         sb.append(s);
/* 205 */         sb.append("_");
/*     */       }
/*     */       else {
/* 208 */         sb.append(s);
/*     */       }
/* 210 */       num++;
/*     */     }
/* 212 */     String projectPath = getAppPath(JeecgSqlUtil.class);
/* 213 */     String fileUrl = projectPath + "/sun/sql/" + sb.toString() + ".sql";
/* 214 */     return getFlieTxt(fileUrl);
/*     */   }
/*     */ 
/*     */   public static String getAppPath(Class cls)
/*     */   {
/* 220 */     if (cls == null)
/* 221 */       throw new IllegalArgumentException("参数不能为空！");
/* 222 */     ClassLoader loader = cls.getClassLoader();
/*     */ 
/* 224 */     String clsName = cls.getName() + ".class";
/*     */ 
/* 226 */     Package pack = cls.getPackage();
/* 227 */     String path = "";
/*     */ 
/* 229 */     if (pack != null) {
/* 230 */       String packName = pack.getName();
/*     */ 
/* 232 */       if ((packName.startsWith("java.")) || (packName.startsWith("javax."))) {
/* 233 */         throw new IllegalArgumentException("不要传送系统类！");
/*     */       }
/* 235 */       clsName = clsName.substring(packName.length() + 1);
/*     */ 
/* 237 */       if (packName.indexOf(".") < 0) {
/* 238 */         path = packName + "/";
/*     */       } else {
/* 240 */         int start = 0; int end = 0;
/* 241 */         end = packName.indexOf(".");
/* 242 */         while (end != -1) {
/* 243 */           path = path + packName.substring(start, end) + "/";
/* 244 */           start = end + 1;
/* 245 */           end = packName.indexOf(".", start);
/*     */         }
/* 247 */         path = path + packName.substring(start) + "/";
/*     */       }
/*     */     }
/*     */ 
/* 251 */     URL url = loader.getResource(path + clsName);
/*     */ 
/* 253 */     String realPath = url.getPath();
/*     */ 
/* 255 */     int pos = realPath.indexOf("file:");
/* 256 */     if (pos > -1) {
/* 257 */       realPath = realPath.substring(pos + 5);
/*     */     }
/* 259 */     pos = realPath.indexOf(path + clsName);
/* 260 */     realPath = realPath.substring(0, pos - 1);
/*     */ 
/* 262 */     if (realPath.endsWith("!")) {
/* 263 */       realPath = realPath.substring(0, realPath.lastIndexOf("/"));
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 271 */       realPath = URLDecoder.decode(realPath, "utf-8");
/*     */     } catch (Exception e) {
/* 273 */       throw new RuntimeException(e);
/*     */     }
/* 275 */     LogUtil.info("realPath----->" + realPath);
/* 276 */     return realPath;
/*     */   }
/*     */ 
/*     */   public static String getMethodUrl()
/*     */   {
/* 286 */     StringBuffer sb = new StringBuffer();
/* 287 */     StackTraceElement[] stacks = new Throwable().getStackTrace();
/* 288 */     sb.append(stacks[1].getClassName()).append(".").append(
/* 289 */       stacks[1].getMethodName());
/* 290 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 296 */     LogUtil.info(getCountSqlBySql("SELECT * \tfrom JEECG_DICT_PARAM WHERE 1=1"));
/*     */   }
/*     */ 
/*     */   public static String getCountSqlBySql(String sql)
/*     */   {
/* 306 */     String countSql = "SELECT COUNT(*)  ";
/*     */ 
/* 308 */     String upperSql = sql.toUpperCase();
/* 309 */     int fromIndex = upperSql.indexOf("FROM");
/* 310 */     int whereIndex = upperSql.indexOf("WHERE");
/*     */ 
/* 312 */     if (whereIndex > -1)
/* 313 */       countSql = countSql + sql.substring(fromIndex, whereIndex);
/*     */     else {
/* 315 */       countSql = countSql + sql.substring(fromIndex);
/*     */     }
/* 317 */     return countSql;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.JeecgSqlUtil
 * JD-Core Version:    0.6.2
 */