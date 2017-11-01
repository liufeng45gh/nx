/*    */ package org.jeecgframework.core.interceptors;
/*    */ 
/*    */ import java.lang.reflect.Array;
/*    */ import java.lang.reflect.Method;
/*    */ import org.aspectj.lang.JoinPoint;
/*    */ import org.aspectj.lang.JoinPoint.StaticPart;
/*    */ import org.aspectj.lang.annotation.Aspect;
/*    */ import org.aspectj.lang.annotation.Before;
/*    */ import org.jeecgframework.core.common.model.json.LogAnnotation;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ @Aspect
/*    */ public class LogInterceptor
/*    */ {
/*    */   @Before("execution(* com.renfang.controller.*.*(..))")
/*    */   public void beforeMethod(JoinPoint joinPoint)
/*    */     throws Exception
/*    */   {
/* 24 */     String temp = joinPoint.getStaticPart().toShortString();
/* 25 */     String longTemp = joinPoint.getStaticPart().toLongString();
/* 26 */     joinPoint.getStaticPart().toString();
/* 27 */     String classType = joinPoint.getTarget().getClass().getName();
/* 28 */     String methodName = temp.substring(10, temp.length() - 1);
/* 29 */     Class className = Class.forName(classType);
/* 30 */     Class[] args = new Class[joinPoint.getArgs().length];
/* 31 */     String[] sArgs = longTemp.substring(longTemp.lastIndexOf("(") + 1, 
/* 32 */       longTemp.length() - 2).split(",");
/* 33 */     for (int i = 0; i < args.length; i++) {
/* 34 */       if (sArgs[i].endsWith("String[]"))
/* 35 */         args[i] = Array.newInstance(Class.forName("java.lang.String"), 
/* 36 */           1).getClass();
/* 37 */       else if (sArgs[i].endsWith("Long[]"))
/* 38 */         args[i] = Array.newInstance(Class.forName("java.lang.Long"), 1)
/* 39 */           .getClass();
/* 40 */       else if (sArgs[i].indexOf(".") == -1) {
/* 41 */         if (sArgs[i].equals("int"))
/* 42 */           args[i] = Integer.TYPE;
/* 43 */         else if (sArgs[i].equals("char"))
/* 44 */           args[i] = Character.TYPE;
/* 45 */         else if (sArgs[i].equals("float"))
/* 46 */           args[i] = Float.TYPE;
/* 47 */         else if (sArgs[i].equals("long"))
/* 48 */           args[i] = Long.TYPE;
/*    */       }
/*    */       else {
/* 51 */         args[i] = Class.forName(sArgs[i]);
/*    */       }
/*    */     }
/* 54 */     Method method = className.getMethod(
/* 55 */       methodName.substring(methodName.indexOf(".") + 1, 
/* 56 */       methodName.indexOf("(")), args);
/* 57 */     if (method.isAnnotationPresent(LogAnnotation.class)) {
/* 58 */       LogAnnotation logAnnotation = 
/* 59 */         (LogAnnotation)method
/* 59 */         .getAnnotation(LogAnnotation.class);
/* 60 */       String operateModelNm = logAnnotation.operateModelNm();
/* 61 */       String operateDescribe = logAnnotation.operateDescribe();
/* 62 */       String str1 = logAnnotation.operateFuncNm();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.interceptors.LogInterceptor
 * JD-Core Version:    0.6.2
 */