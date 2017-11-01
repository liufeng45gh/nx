/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.net.SocketException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.sql.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class oConvertUtils
/*     */ {
/*     */   public static boolean isEmpty(Object object)
/*     */   {
/*  31 */     if (object == null) {
/*  32 */       return true;
/*     */     }
/*  34 */     if (object.equals("")) {
/*  35 */       return true;
/*     */     }
/*  37 */     if (object.equals("null")) {
/*  38 */       return true;
/*     */     }
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isNotEmpty(Object object) {
/*  44 */     if ((object != null) && (!object.equals("")) && (!object.equals("null"))) {
/*  45 */       return true;
/*     */     }
/*  47 */     return false;
/*     */   }
/*     */ 
/*     */   public static String decode(String strIn, String sourceCode, String targetCode) {
/*  51 */     String temp = code2code(strIn, sourceCode, targetCode);
/*  52 */     return temp;
/*     */   }
/*     */ 
/*     */   public static String StrToUTF(String strIn, String sourceCode, String targetCode) {
/*  56 */     strIn = "";
/*     */     try {
/*  58 */       strIn = new String(strIn.getBytes("ISO-8859-1"), "GBK");
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/*  61 */       e.printStackTrace();
/*     */     }
/*  63 */     return strIn;
/*     */   }
/*     */ 
/*     */   private static String code2code(String strIn, String sourceCode, String targetCode)
/*     */   {
/*  68 */     String strOut = null;
/*  69 */     if ((strIn == null) || (strIn.trim().equals("")))
/*  70 */       return strIn;
/*     */     try {
/*  72 */       byte[] b = strIn.getBytes(sourceCode);
/*  73 */       for (int i = 0; i < b.length; i++) {
/*  74 */         System.out.print(b[i] + "  ");
/*     */       }
/*  76 */       strOut = new String(b, targetCode);
/*     */     } catch (Exception e) {
/*  78 */       e.printStackTrace();
/*  79 */       return null;
/*     */     }
/*  81 */     return strOut;
/*     */   }
/*     */ 
/*     */   public static int getInt(String s, int defval) {
/*  85 */     if ((s == null) || (s == ""))
/*  86 */       return defval;
/*     */     try
/*     */     {
/*  89 */       return Integer.parseInt(s); } catch (NumberFormatException e) {
/*     */     }
/*  91 */     return defval;
/*     */   }
/*     */ 
/*     */   public static int getInt(String s)
/*     */   {
/*  96 */     if ((s == null) || (s == ""))
/*  97 */       return 0;
/*     */     try
/*     */     {
/* 100 */       return Integer.parseInt(s); } catch (NumberFormatException e) {
/*     */     }
/* 102 */     return 0;
/*     */   }
/*     */ 
/*     */   public static int getInt(String s, Integer df)
/*     */   {
/* 107 */     if ((s == null) || (s == ""))
/* 108 */       return df.intValue();
/*     */     try
/*     */     {
/* 111 */       return Integer.parseInt(s); } catch (NumberFormatException e) {
/*     */     }
/* 113 */     return 0;
/*     */   }
/*     */ 
/*     */   public static Integer[] getInts(String[] s)
/*     */   {
/* 118 */     Integer[] integer = new Integer[s.length];
/* 119 */     if (s == null) {
/* 120 */       return null;
/*     */     }
/* 122 */     for (int i = 0; i < s.length; i++) {
/* 123 */       integer[i] = Integer.valueOf(Integer.parseInt(s[i]));
/*     */     }
/* 125 */     return integer;
/*     */   }
/*     */ 
/*     */   public static double getDouble(String s, double defval)
/*     */   {
/* 130 */     if ((s == null) || (s == ""))
/* 131 */       return defval;
/*     */     try
/*     */     {
/* 134 */       return Double.parseDouble(s); } catch (NumberFormatException e) {
/*     */     }
/* 136 */     return defval;
/*     */   }
/*     */ 
/*     */   public static double getDou(Double s, double defval)
/*     */   {
/* 141 */     if (s == null) {
/* 142 */       return defval;
/*     */     }
/* 144 */     return s.doubleValue();
/*     */   }
/*     */ 
/*     */   public static Short getShort(String s) {
/* 148 */     if (StringUtil.isNotEmpty(s)) {
/* 149 */       return Short.valueOf(Short.parseShort(s));
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getInt(Object object, int defval)
/*     */   {
/* 156 */     if (isEmpty(object))
/* 157 */       return defval;
/*     */     try
/*     */     {
/* 160 */       return Integer.parseInt(object.toString()); } catch (NumberFormatException e) {
/*     */     }
/* 162 */     return defval;
/*     */   }
/*     */ 
/*     */   public static int getInt(BigDecimal s, int defval)
/*     */   {
/* 167 */     if (s == null) {
/* 168 */       return defval;
/*     */     }
/* 170 */     return s.intValue();
/*     */   }
/*     */ 
/*     */   public static Integer[] getIntegerArry(String[] object) {
/* 174 */     int len = object.length;
/* 175 */     Integer[] result = new Integer[len];
/*     */     try {
/* 177 */       for (int i = 0; i < len; i++) {
/* 178 */         result[i] = new Integer(object[i].trim());
/*     */       }
/* 180 */       return result; } catch (NumberFormatException e) {
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getString(String s)
/*     */   {
/* 187 */     return getString(s, "");
/*     */   }
/*     */ 
/*     */   public static String getString(Object object) {
/* 191 */     if (isEmpty(object)) {
/* 192 */       return "";
/*     */     }
/* 194 */     return object.toString().trim();
/*     */   }
/*     */ 
/*     */   public static String getString(int i) {
/* 198 */     return String.valueOf(i);
/*     */   }
/*     */ 
/*     */   public static String getString(float i) {
/* 202 */     return String.valueOf(i);
/*     */   }
/*     */ 
/*     */   public static String getString(String s, String defval) {
/* 206 */     if (isEmpty(s)) {
/* 207 */       return defval;
/*     */     }
/* 209 */     return s.trim();
/*     */   }
/*     */ 
/*     */   public static String getString(Object s, String defval) {
/* 213 */     if (isEmpty(s)) {
/* 214 */       return defval;
/*     */     }
/* 216 */     return s.toString().trim();
/*     */   }
/*     */ 
/*     */   public static long stringToLong(String str) {
/* 220 */     Long test = new Long(0L);
/*     */     try {
/* 222 */       test = Long.valueOf(str);
/*     */     } catch (Exception localException) {
/*     */     }
/* 225 */     return test.longValue();
/*     */   }
/*     */ 
/*     */   public static String getIp()
/*     */   {
/* 232 */     String ip = null;
/*     */     try {
/* 234 */       InetAddress address = InetAddress.getLocalHost();
/* 235 */       ip = address.getHostAddress();
/*     */     }
/*     */     catch (UnknownHostException e) {
/* 238 */       e.printStackTrace();
/*     */     }
/* 240 */     return ip;
/*     */   }
/*     */ 
/*     */   private static boolean isBaseDataType(Class clazz)
/*     */     throws Exception
/*     */   {
/* 251 */     return (clazz.equals(String.class)) || (clazz.equals(Integer.class)) || (clazz.equals(Byte.class)) || (clazz.equals(Long.class)) || (clazz.equals(Double.class)) || (clazz.equals(Float.class)) || (clazz.equals(Character.class)) || (clazz.equals(Short.class)) || (clazz.equals(BigDecimal.class)) || (clazz.equals(BigInteger.class)) || (clazz.equals(Boolean.class)) || (clazz.equals(Date.class)) || (clazz.isPrimitive());
/*     */   }
/*     */ 
/*     */   public static String getIpAddrByRequest(HttpServletRequest request)
/*     */   {
/* 260 */     String ip = request.getHeader("x-forwarded-for");
/* 261 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 262 */       ip = request.getHeader("Proxy-Client-IP");
/*     */     }
/* 264 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 265 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*     */     }
/* 267 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 268 */       ip = request.getRemoteAddr();
/*     */     }
/* 270 */     return ip;
/*     */   }
/*     */ 
/*     */   public static String getRealIp()
/*     */     throws SocketException
/*     */   {
/* 278 */     String localip = null;
/* 279 */     String netip = null;
/*     */ 
/* 281 */     Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
/* 282 */     InetAddress ip = null;
/* 283 */     boolean finded = false;
/* 284 */     while ((netInterfaces.hasMoreElements()) && (!finded)) {
/* 285 */       NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
/* 286 */       Enumeration address = ni.getInetAddresses();
/* 287 */       while (address.hasMoreElements()) {
/* 288 */         ip = (InetAddress)address.nextElement();
/* 289 */         if ((!ip.isSiteLocalAddress()) && (!ip.isLoopbackAddress()) && (ip.getHostAddress().indexOf(":") == -1)) {
/* 290 */           netip = ip.getHostAddress();
/* 291 */           finded = true;
/* 292 */           break;
/* 293 */         }if ((ip.isSiteLocalAddress()) && (!ip.isLoopbackAddress()) && (ip.getHostAddress().indexOf(":") == -1)) {
/* 294 */           localip = ip.getHostAddress();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 299 */     if ((netip != null) && (!"".equals(netip))) {
/* 300 */       return netip;
/*     */     }
/* 302 */     return localip;
/*     */   }
/*     */ 
/*     */   public static String replaceBlank(String str)
/*     */   {
/* 313 */     String dest = "";
/* 314 */     if (str != null) {
/* 315 */       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
/* 316 */       Matcher m = p.matcher(str);
/* 317 */       dest = m.replaceAll("");
/*     */     }
/* 319 */     return dest;
/*     */   }
/*     */ 
/*     */   public static boolean isIn(String substring, String[] source)
/*     */   {
/* 331 */     if ((source == null) || (source.length == 0)) {
/* 332 */       return false;
/*     */     }
/* 334 */     for (int i = 0; i < source.length; i++) {
/* 335 */       String aSource = source[i];
/* 336 */       if (aSource.equals(substring)) {
/* 337 */         return true;
/*     */       }
/*     */     }
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */   public static Map<Object, Object> getHashMap()
/*     */   {
/* 347 */     return new HashMap();
/*     */   }
/*     */ 
/*     */   public static Map<Object, Object> SetToMap(Set<Object> setobj)
/*     */   {
/* 357 */     Map map = getHashMap();
/* 358 */     for (Iterator iterator = setobj.iterator(); iterator.hasNext(); ) {
/* 359 */       Map.Entry entry = (Map.Entry)iterator.next();
/* 360 */       map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
/*     */     }
/* 362 */     return map;
/*     */   }
/*     */ 
/*     */   public static boolean isInnerIP(String ipAddress)
/*     */   {
/* 367 */     boolean isInnerIp = false;
/* 368 */     long ipNum = getIpNum(ipAddress);
/*     */ 
/* 372 */     long aBegin = getIpNum("10.0.0.0");
/* 373 */     long aEnd = getIpNum("10.255.255.255");
/* 374 */     long bBegin = getIpNum("172.16.0.0");
/* 375 */     long bEnd = getIpNum("172.31.255.255");
/* 376 */     long cBegin = getIpNum("192.168.0.0");
/* 377 */     long cEnd = getIpNum("192.168.255.255");
/* 378 */     isInnerIp = (isInner(ipNum, aBegin, aEnd)) || (isInner(ipNum, bBegin, bEnd)) || (isInner(ipNum, cBegin, cEnd)) || (ipAddress.equals("127.0.0.1"));
/* 379 */     return isInnerIp;
/*     */   }
/*     */ 
/*     */   private static long getIpNum(String ipAddress) {
/* 383 */     String[] ip = ipAddress.split("\\.");
/* 384 */     long a = Integer.parseInt(ip[0]);
/* 385 */     long b = Integer.parseInt(ip[1]);
/* 386 */     long c = Integer.parseInt(ip[2]);
/* 387 */     long d = Integer.parseInt(ip[3]);
/*     */ 
/* 389 */     long ipNum = a * 256L * 256L * 256L + b * 256L * 256L + c * 256L + d;
/* 390 */     return ipNum;
/*     */   }
/*     */ 
/*     */   private static boolean isInner(long userIp, long begin, long end) {
/* 394 */     return (userIp >= begin) && (userIp <= end);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.oConvertUtils
 * JD-Core Version:    0.6.2
 */