/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.web.context.request.RequestContextHolder;
/*     */ import org.springframework.web.context.request.ServletRequestAttributes;
/*     */ 
/*     */ public class GenericsUtils
/*     */ {
/* 260 */   private static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', 
/* 261 */     '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
/* 262 */     'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
/* 263 */     'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
/* 264 */     'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
/* 265 */     'Z' };
/*     */ 
/*     */   public static Class getSuperClassGenricType(Class clazz, int index)
/*     */   {
/*  41 */     Type genType = clazz.getGenericSuperclass();
/*     */ 
/*  43 */     if (!(genType instanceof ParameterizedType)) {
/*  44 */       return Object.class;
/*     */     }
/*     */ 
/*  48 */     Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
/*  49 */     if ((index >= params.length) || (index < 0)) {
/*  50 */       throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
/*     */     }
/*  52 */     if (!(params[index] instanceof Class)) {
/*  53 */       return Object.class;
/*     */     }
/*  55 */     return (Class)params[index];
/*     */   }
/*     */ 
/*     */   public static Class getSuperClassGenricType(Class clazz)
/*     */   {
/*  68 */     return getSuperClassGenricType(clazz, 0);
/*     */   }
/*     */ 
/*     */   public static Class getMethodGenericReturnType(Method method, int index)
/*     */   {
/*  82 */     Type returnType = method.getGenericReturnType();
/*  83 */     if ((returnType instanceof ParameterizedType)) {
/*  84 */       ParameterizedType type = (ParameterizedType)returnType;
/*  85 */       Type[] typeArguments = type.getActualTypeArguments();
/*  86 */       if ((index >= typeArguments.length) || (index < 0)) {
/*  87 */         throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
/*     */       }
/*  89 */       return (Class)typeArguments[index];
/*     */     }
/*  91 */     return Object.class;
/*     */   }
/*     */ 
/*     */   public static Class getMethodGenericReturnType(Method method)
/*     */   {
/* 104 */     return getMethodGenericReturnType(method, 0);
/*     */   }
/*     */ 
/*     */   public static List<Class> getMethodGenericParameterTypes(Method method, int index)
/*     */   {
/* 118 */     List results = new ArrayList();
/* 119 */     Type[] genericParameterTypes = method.getGenericParameterTypes();
/* 120 */     if ((index >= genericParameterTypes.length) || (index < 0)) {
/* 121 */       throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
/*     */     }
/* 123 */     Type genericParameterType = genericParameterTypes[index];
/* 124 */     if ((genericParameterType instanceof ParameterizedType)) {
/* 125 */       ParameterizedType aType = (ParameterizedType)genericParameterType;
/* 126 */       Type[] parameterArgTypes = aType.getActualTypeArguments();
/* 127 */       for (Type parameterArgType : parameterArgTypes) {
/* 128 */         Class parameterArgClass = (Class)parameterArgType;
/* 129 */         results.add(parameterArgClass);
/*     */       }
/* 131 */       return results;
/*     */     }
/* 133 */     return results;
/*     */   }
/*     */ 
/*     */   public static List<Class> getMethodGenericParameterTypes(Method method)
/*     */   {
/* 146 */     return getMethodGenericParameterTypes(method, 0);
/*     */   }
/*     */ 
/*     */   public static Class getFieldGenericType(Field field, int index)
/*     */   {
/* 160 */     Type genericFieldType = field.getGenericType();
/*     */ 
/* 162 */     if ((genericFieldType instanceof ParameterizedType)) {
/* 163 */       ParameterizedType aType = (ParameterizedType)genericFieldType;
/* 164 */       Type[] fieldArgTypes = aType.getActualTypeArguments();
/* 165 */       if ((index >= fieldArgTypes.length) || (index < 0)) {
/* 166 */         throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
/*     */       }
/* 168 */       return (Class)fieldArgTypes[index];
/*     */     }
/* 170 */     return Object.class;
/*     */   }
/*     */ 
/*     */   public static Class getFieldGenericType(Field field)
/*     */   {
/* 184 */     return getFieldGenericType(field, 0);
/*     */   }
/*     */ 
/*     */   public static String[] getColumnNames(String objClass)
/*     */     throws ClassNotFoundException
/*     */   {
/* 193 */     String[] wageStrArray = null;
/* 194 */     if (objClass != null) {
/* 195 */       Class class1 = Class.forName(objClass);
/* 196 */       Field[] field = class1.getDeclaredFields();
/* 197 */       StringBuffer sb = new StringBuffer();
/* 198 */       for (int i = 0; i < field.length; i++)
/*     */       {
/* 200 */         sb.append(field[i].getName());
/*     */ 
/* 207 */         if (i < field.length - 1) {
/* 208 */           sb.append(",");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 215 */       wageStrArray = sb.toString().split(",");
/* 216 */       return wageStrArray;
/*     */     }
/* 218 */     return wageStrArray;
/*     */   }
/*     */ 
/*     */   public static Object[] field2Value(Field[] f, Object o) throws Exception {
/* 222 */     Object[] value = new Object[f.length];
/* 223 */     for (int i = 0; i < f.length; i++) {
/* 224 */       value[i] = f[i].get(o);
/*     */     }
/* 226 */     return value;
/*     */   }
/*     */ 
/*     */   public HttpSession getSession()
/*     */   {
/* 234 */     HttpSession session = null;
/* 235 */     ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
/* 236 */     HttpSession contextSess = attr == null ? session : attr.getRequest().getSession(true);
/*     */ 
/* 238 */     return contextSess;
/*     */   }
/*     */ 
/*     */   public static Class getEntityClass(String objClass)
/*     */   {
/* 246 */     Class entityClass = null;
/*     */     try {
/* 248 */       entityClass = Class.forName(objClass);
/*     */     } catch (ClassNotFoundException e) {
/* 250 */       e.printStackTrace();
/*     */     }
/* 252 */     return entityClass;
/*     */   }
/*     */ 
/*     */   public static String getPasswords(int passLength)
/*     */   {
/* 276 */     String passwords = "";
/* 277 */     Random random = new Random();
/* 278 */     StringBuilder password = new StringBuilder("");
/* 279 */     for (int m = 1; m <= passLength; m++) {
/* 280 */       password.append(chars[random.nextInt(62)]);
/*     */     }
/* 282 */     passwords = password.toString();
/* 283 */     return passwords;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.GenericsUtils
 * JD-Core Version:    0.6.2
 */