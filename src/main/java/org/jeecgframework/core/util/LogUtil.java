/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.net.URL;
/*     */ import org.apache.log4j.Level;
/*     */ import org.apache.log4j.LogManager;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class LogUtil
/*     */ {
/*     */   private static final String LOGCONFIG = "log4j.properties";
/*     */   private static Logger objLog;
/*     */ 
/*     */   private static Logger getLogger()
/*     */   {
/*  24 */     if (objLog == null)
/*     */     {
/*  26 */       objLog = LogManager.getLogger(LogUtil.class);
/*     */     }
/*  28 */     return objLog;
/*     */   }
/*     */ 
/*     */   private static String getConfigFile() {
/*  32 */     String s = LogUtil.class.getClassLoader().getResource("").toString();
/*  33 */     String filePath = s + "log4j.properties";
/*  34 */     return filePath;
/*     */   }
/*     */ 
/*     */   public static void info(String message, Exception exception)
/*     */   {
/*     */     try
/*     */     {
/*  44 */       log("INFO", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void info(Object message)
/*     */   {
/*  55 */     log("INFO", message);
/*     */   }
/*     */ 
/*     */   public static void trace(String message)
/*     */   {
/*     */     try
/*     */     {
/*  62 */       log("TRACE", message);
/*     */     } catch (Exception localException) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void trace(String message, Exception exception) {
/*     */     try {
/*  69 */       log("TRACE", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void error(String message, Exception exception)
/*     */   {
/*     */     try
/*     */     {
/*  81 */       log("ERROR", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void error(String message)
/*     */   {
/*     */     try
/*     */     {
/*  93 */       log("ERROR", message);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void warning(String message, Exception exception)
/*     */   {
/*     */     try
/*     */     {
/* 110 */       log("WARN", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void warning(String message)
/*     */   {
/*     */     try
/*     */     {
/* 122 */       log("WARN", message);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void fatal(String message, Exception exception)
/*     */   {
/*     */     try
/*     */     {
/* 139 */       log("FATAL", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void fatal(String message)
/*     */   {
/*     */     try
/*     */     {
/* 151 */       log("FATAL", message);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void debug(String message, Exception exception)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       log("DEBUG", message, exception);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void debug(String message)
/*     */   {
/*     */     try
/*     */     {
/* 180 */       log("DEBUG", message);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void log(String level, Object msg)
/*     */   {
/* 189 */     log(level, msg, null);
/*     */   }
/*     */ 
/*     */   public static void log(String level, Throwable e)
/*     */   {
/* 194 */     log(level, null, e);
/*     */   }
/*     */ 
/*     */   public static void log(String level, Object msg, Throwable e)
/*     */   {
/* 199 */     StringWriter sw = new StringWriter();
/* 200 */     PrintWriter pw = new PrintWriter(sw);
/*     */     try {
/* 202 */       StringBuilder sb = new StringBuilder();
/* 203 */       Throwable t = new Throwable();
/* 204 */       t.printStackTrace(pw);
/* 205 */       String input = sw.getBuffer().toString();
/* 206 */       StringReader sr = new StringReader(input);
/* 207 */       BufferedReader br = new BufferedReader(sr);
/* 208 */       for (int i = 0; i < 4; i++)
/* 209 */         br.readLine();
/* 210 */       String line = br.readLine();
/*     */ 
/* 212 */       int paren = line.indexOf("at ");
/* 213 */       line = line.substring(paren + 3);
/* 214 */       paren = line.indexOf('(');
/* 215 */       String invokeInfo = line.substring(0, paren);
/* 216 */       int period = invokeInfo.lastIndexOf('.');
/* 217 */       sb.append('[');
/* 218 */       sb.append(invokeInfo.substring(0, period));
/* 219 */       sb.append(':');
/* 220 */       sb.append(invokeInfo.substring(period + 1));
/* 221 */       sb.append("():");
/* 222 */       paren = line.indexOf(':');
/* 223 */       period = line.lastIndexOf(')');
/* 224 */       sb.append(line.substring(paren + 1, period));
/* 225 */       sb.append(']');
/* 226 */       sb.append(" - ");
/* 227 */       sb.append(msg);
/* 228 */       getLogger().log(Level.toLevel(level), sb.toString(), e);
/*     */     } catch (Exception ex) {
/* 230 */       ex.printStackTrace();
/*     */       try
/*     */       {
/* 233 */         sw.close();
/* 234 */         pw.close();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/*     */     }
/*     */     finally
/*     */     {
/*     */       try
/*     */       {
/* 233 */         sw.close();
/* 234 */         pw.close();
/*     */       }
/*     */       catch (Exception localException2)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.LogUtil
 * JD-Core Version:    0.6.2
 */