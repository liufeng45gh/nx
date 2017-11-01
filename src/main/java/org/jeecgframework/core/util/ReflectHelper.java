/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Hashtable;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class ReflectHelper
/*     */ {
/*     */   private Class cls;
/*     */   private Object obj;
/*  22 */   private Hashtable<String, Method> getMethods = null;
/*     */ 
/*  26 */   private Hashtable<String, Method> setMethods = null;
/*     */ 
/*     */   public ReflectHelper(Object o)
/*     */   {
/*  35 */     this.obj = o;
/*  36 */     initMethods();
/*     */   }
/*     */ 
/*     */   public void initMethods()
/*     */   {
/*  44 */     this.getMethods = new Hashtable();
/*  45 */     this.setMethods = new Hashtable();
/*  46 */     this.cls = this.obj.getClass();
/*  47 */     Method[] methods = this.cls.getMethods();
/*     */ 
/*  49 */     String gs = "get(\\w+)";
/*  50 */     Pattern getM = Pattern.compile(gs);
/*  51 */     String ss = "set(\\w+)";
/*  52 */     Pattern setM = Pattern.compile(ss);
/*     */ 
/*  54 */     String rapl = "$1";
/*     */ 
/*  56 */     for (int i = 0; i < methods.length; i++) {
/*  57 */       Method m = methods[i];
/*  58 */       String methodName = m.getName();
/*  59 */       if (Pattern.matches(gs, methodName)) {
/*  60 */         String param = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
/*  61 */         this.getMethods.put(param, m);
/*  62 */       } else if (Pattern.matches(ss, methodName)) {
/*  63 */         String param = setM.matcher(methodName).replaceAll(rapl).toLowerCase();
/*  64 */         this.setMethods.put(param, m);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean setMethodValue(String property, Object object)
/*     */   {
/*  76 */     Method m = (Method)this.setMethods.get(property.toLowerCase());
/*  77 */     if (m != null) {
/*     */       try
/*     */       {
/*  80 */         m.invoke(this.obj, new Object[] { object });
/*  81 */         return true;
/*     */       } catch (Exception ex) {
/*  83 */         LogUtil.info("invoke getter on " + property + " error: " + ex.toString());
/*  84 */         return false;
/*     */       }
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   public Object getMethodValue(String property)
/*     */   {
/*  95 */     Object value = null;
/*  96 */     Method m = (Method)this.getMethods.get(property.toLowerCase());
/*  97 */     if (m != null)
/*     */     {
/*     */       try
/*     */       {
/* 102 */         value = m.invoke(this.obj, new Object[0]);
/*     */       }
/*     */       catch (Exception ex) {
/* 105 */         LogUtil.info("invoke getter on " + property + " error: " + ex.toString());
/*     */       }
/*     */     }
/* 108 */     return value;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ReflectHelper
 * JD-Core Version:    0.6.2
 */