/*     */ package org.jeecgframework.web.cgform.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.TreeMap;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.jeecgframework.p3.core.util.MD5Util;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class SignatureUtil
/*     */ {
/*  25 */   private static final Logger LOG = LoggerFactory.getLogger(SignatureUtil.class);
/*     */ 
/*     */   public static String sign(Map<String, String> paramMap, String key)
/*     */   {
/*  34 */     if (key == null) {
/*  35 */       throw new BusinessException("key不能为空");
/*     */     }
/*  37 */     String sign = createSign(paramMap, key);
/*  38 */     return sign;
/*     */   }
/*     */ 
/*     */   private static String createSign(Map<String, String> paramMap, String key)
/*     */   {
/*  45 */     StringBuffer sb = new StringBuffer();
/*  46 */     SortedMap sort = new TreeMap(paramMap);
/*  47 */     Set es = sort.entrySet();
/*  48 */     Iterator it = es.iterator();
/*  49 */     while (it.hasNext())
/*     */     {
/*  51 */       Map.Entry entry = (Map.Entry)it.next();
/*  52 */       String k = (String)entry.getKey();
/*  53 */       String v = (String)entry.getValue();
/*  54 */       if ((v != null) && (!"".equals(v)) && (!"null".equals(v)) && (!"sign".equals(k)) && (!"key".equals(k))) {
/*  55 */         sb.append(k + "=" + v + "&");
/*     */       }
/*     */     }
/*  58 */     sb.append("key=" + key);
/*  59 */     LOG.info("HMAC source:{}", new Object[] { sb.toString() });
/*  60 */     String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
/*  61 */     LOG.info("HMAC:{}", new Object[] { sign });
/*  62 */     return sign;
/*     */   }
/*     */ 
/*     */   public static boolean checkSign(Map<String, String> paramMap, String key, String sign)
/*     */   {
/*  73 */     if (key == null) {
/*  74 */       throw new BusinessException("key不能为空");
/*     */     }
/*  76 */     if (sign == null) {
/*  77 */       throw new BusinessException("需要验签的字符为空");
/*     */     }
/*     */ 
/*  80 */     return sign.equals(sign(paramMap, key));
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getSignMap(HttpServletRequest request)
/*     */   {
/*  90 */     Map paramMap = new HashMap();
/*  91 */     Map map = request.getParameterMap();
/*  92 */     Set es = map.entrySet();
/*  93 */     Iterator it = es.iterator();
/*  94 */     while (it.hasNext())
/*     */     {
/*  96 */       Map.Entry entry = (Map.Entry)it.next();
/*  97 */       String k = (String)entry.getKey();
/*  98 */       Object ov = entry.getValue();
/*  99 */       String v = "";
/* 100 */       if ((ov instanceof String[])) {
/* 101 */         String[] value = (String[])ov;
/* 102 */         v = value[0];
/*     */       } else {
/* 104 */         v = ov.toString();
/*     */       }
/* 106 */       paramMap.put(k, v);
/*     */     }
/* 108 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getSignMap(String url)
/*     */   {
/* 117 */     Map paramMap = new HashMap();
/* 118 */     url = url.substring(url.indexOf("?") + 1);
/* 119 */     String[] params = url.split("&");
/* 120 */     for (int i = 0; i < params.length; i++) {
/* 121 */       String param = params[i];
/* 122 */       if (param.indexOf("=") != -1) {
/* 123 */         String[] values = param.split("=");
/* 124 */         if ((values != null) && (values.length == 2))
/*     */         {
/* 126 */           if ("nickname".equals(values[0]))
/* 127 */             paramMap.put(values[0], URLDecoder.decode(values[1]));
/*     */           else {
/* 129 */             paramMap.put(values[0], values[1]);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 135 */     return paramMap;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 144 */     String key = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";
/* 145 */     Map paramMap = new HashMap();
/* 146 */     paramMap.put("tableName", "jform_le_main");
/* 147 */     paramMap.put("id", "402813815398698b015398698b710000");
/* 148 */     paramMap.put("data", "{jform_le_main:[{id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"}],jform_le_subone:[{main_id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"}],jform_le_submany:[{main_id=\"402813815398698b015398698b710000\",name:\"ceshi111111\",sex:1,remark:\"java developer\"},{name:\"ceshi111111\",sex:1,remark:\"java developer\"}]}");
/* 149 */     paramMap.put("method", "updateFormInfo");
/* 150 */     System.out.println(createSign(paramMap, key));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.util.SignatureUtil
 * JD-Core Version:    0.6.2
 */