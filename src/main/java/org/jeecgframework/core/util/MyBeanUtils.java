/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.beans.PropertyDescriptor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.beanutils.DynaBean;
/*     */ import org.apache.commons.beanutils.DynaClass;
/*     */ import org.apache.commons.beanutils.DynaProperty;
/*     */ import org.apache.commons.beanutils.PropertyUtils;
/*     */ import org.apache.commons.beanutils.PropertyUtilsBean;
/*     */ 
/*     */ public class MyBeanUtils extends PropertyUtilsBean
/*     */ {
/*     */   private static void convert(Object dest, Object orig)
/*     */     throws IllegalAccessException, InvocationTargetException
/*     */   {
/*  27 */     if (dest == null) {
/*  28 */       throw new IllegalArgumentException(
/*  29 */         "No destination bean specified");
/*     */     }
/*  31 */     if (orig == null) {
/*  32 */       throw new IllegalArgumentException("No origin bean specified");
/*     */     }
/*     */ 
/*  36 */     if ((orig instanceof DynaBean)) {
/*  37 */       DynaProperty[] origDescriptors = 
/*  38 */         ((DynaBean)orig).getDynaClass().getDynaProperties();
/*  39 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  40 */         String name = origDescriptors[i].getName();
/*  41 */         if (PropertyUtils.isWriteable(dest, name)) {
/*  42 */           Object value = ((DynaBean)orig).get(name);
/*     */           try {
/*  44 */             getInstance().setSimpleProperty(dest, name, value);
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*  53 */     else if ((orig instanceof Map)) {
/*  54 */       Iterator names = ((Map)orig).keySet().iterator();
/*  55 */       while (names.hasNext()) {
/*  56 */         String name = (String)names.next();
/*  57 */         if (PropertyUtils.isWriteable(dest, name)) {
/*  58 */           Object value = ((Map)orig).get(name);
/*     */           try {
/*  60 */             getInstance().setSimpleProperty(dest, name, value);
/*     */           }
/*     */           catch (Exception localException1)
/*     */           {
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  72 */       PropertyDescriptor[] origDescriptors = 
/*  73 */         PropertyUtils.getPropertyDescriptors(orig);
/*  74 */       for (int i = 0; i < origDescriptors.length; i++) {
/*  75 */         String name = origDescriptors[i].getName();
/*     */ 
/*  77 */         if (!"class".equals(name))
/*     */         {
/*  80 */           if ((PropertyUtils.isReadable(orig, name)) && 
/*  81 */             (PropertyUtils.isWriteable(dest, name)))
/*     */             try {
/*  83 */               Object value = PropertyUtils.getSimpleProperty(orig, name);
/*  84 */               getInstance().setSimpleProperty(dest, name, value);
/*     */             }
/*     */             catch (IllegalArgumentException localIllegalArgumentException)
/*     */             {
/*     */             }
/*     */             catch (Exception localException2)
/*     */             {
/*     */             }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyBeanNotNull2Bean(Object databean, Object tobean)
/*     */     throws Exception
/*     */   {
/* 111 */     PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(databean);
/* 112 */     for (int i = 0; i < origDescriptors.length; i++) {
/* 113 */       String name = origDescriptors[i].getName();
/*     */ 
/* 115 */       if (!"class".equals(name))
/*     */       {
/* 118 */         if ((PropertyUtils.isReadable(databean, name)) && (PropertyUtils.isWriteable(tobean, name)))
/*     */           try {
/* 120 */             Object value = PropertyUtils.getSimpleProperty(databean, name);
/* 121 */             if (value != null)
/* 122 */               getInstance().setSimpleProperty(tobean, name, value);
/*     */           }
/*     */           catch (IllegalArgumentException localIllegalArgumentException)
/*     */           {
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyBean2Bean(Object dest, Object orig)
/*     */     throws Exception
/*     */   {
/* 145 */     convert(dest, orig);
/*     */   }
/*     */ 
/*     */   public static void copyBean2Map(Map map, Object bean) {
/* 149 */     PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean);
/* 150 */     for (int i = 0; i < pds.length; i++)
/*     */     {
/* 152 */       PropertyDescriptor pd = pds[i];
/* 153 */       String propname = pd.getName();
/*     */       try {
/* 155 */         Object propvalue = PropertyUtils.getSimpleProperty(bean, propname);
/* 156 */         map.put(propname, propvalue);
/*     */       }
/*     */       catch (IllegalAccessException localIllegalAccessException)
/*     */       {
/*     */       }
/*     */       catch (InvocationTargetException localInvocationTargetException)
/*     */       {
/*     */       }
/*     */       catch (NoSuchMethodException localNoSuchMethodException)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyMap2Bean(Object bean, Map properties)
/*     */     throws IllegalAccessException, InvocationTargetException
/*     */   {
/* 177 */     if ((bean == null) || (properties == null)) {
/* 178 */       return;
/*     */     }
/*     */ 
/* 181 */     Iterator names = properties.keySet().iterator();
/* 182 */     while (names.hasNext()) {
/* 183 */       String name = (String)names.next();
/*     */ 
/* 185 */       if (name != null)
/*     */       {
/* 188 */         Object value = properties.get(name);
/*     */         try {
/* 190 */           Class clazz = PropertyUtils.getPropertyType(bean, name);
/* 191 */           if (clazz != null)
/*     */           {
/* 194 */             String className = clazz.getName();
/* 195 */             if ((!className.equalsIgnoreCase("java.sql.Timestamp")) || (
/* 196 */               (value != null) && (!value.equals(""))))
/*     */             {
/* 200 */               getInstance().setSimpleProperty(bean, name, value);
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (NoSuchMethodException localNoSuchMethodException)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyMap2Bean_Nobig(Object bean, Map properties)
/*     */     throws IllegalAccessException, InvocationTargetException
/*     */   {
/* 220 */     if ((bean == null) || (properties == null)) {
/* 221 */       return;
/*     */     }
/*     */ 
/* 224 */     Iterator names = properties.keySet().iterator();
/* 225 */     while (names.hasNext()) {
/* 226 */       String name = (String)names.next();
/*     */ 
/* 228 */       if (name != null)
/*     */       {
/* 231 */         Object value = properties.get(name);
/*     */         try
/*     */         {
/* 235 */           if (value != null)
/*     */           {
/* 238 */             Class clazz = PropertyUtils.getPropertyType(bean, name);
/* 239 */             if (clazz != null)
/*     */             {
/* 242 */               String className = clazz.getName();
/*     */ 
/* 244 */               if (className.equalsIgnoreCase("java.util.Date")) {
/* 245 */                 value = new Date(((Timestamp)value).getTime());
/*     */               }
/*     */ 
/* 252 */               getInstance().setSimpleProperty(bean, name, value);
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (NoSuchMethodException localNoSuchMethodException)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void copyMap2Bean(Object bean, Map properties, String defaultValue)
/*     */     throws IllegalAccessException, InvocationTargetException
/*     */   {
/* 272 */     if ((bean == null) || (properties == null)) {
/* 273 */       return;
/*     */     }
/*     */ 
/* 276 */     Iterator names = properties.keySet().iterator();
/* 277 */     while (names.hasNext()) {
/* 278 */       String name = (String)names.next();
/*     */ 
/* 280 */       if (name != null)
/*     */       {
/* 283 */         Object value = properties.get(name);
/*     */         try {
/* 285 */           Class clazz = PropertyUtils.getPropertyType(bean, name);
/* 286 */           if (clazz != null)
/*     */           {
/* 289 */             String className = clazz.getName();
/* 290 */             if ((!className.equalsIgnoreCase("java.sql.Timestamp")) || (
/* 291 */               (value != null) && (!value.equals(""))))
/*     */             {
/* 295 */               if ((className.equalsIgnoreCase("java.lang.String")) && 
/* 296 */                 (value == null)) {
/* 297 */                 value = defaultValue;
/*     */               }
/*     */ 
/* 300 */               getInstance().setSimpleProperty(bean, name, value);
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (NoSuchMethodException localNoSuchMethodException)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.MyBeanUtils
 * JD-Core Version:    0.6.2
 */