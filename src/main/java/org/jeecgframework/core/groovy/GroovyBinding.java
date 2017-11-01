/*    */ package org.jeecgframework.core.groovy;
/*    */ 
/*    */ import groovy.lang.Binding;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ 
/*    */ public class GroovyBinding extends Binding
/*    */ {
/* 10 */   private static ThreadLocal<Map<String, Object>> localVars = new ThreadLocal();
/*    */ 
/* 12 */   private static Map<String, Object> propertyMap = new ConcurrentHashMap();
/*    */ 
/*    */   public GroovyBinding() {
/*    */   }
/*    */ 
/*    */   public GroovyBinding(Map<String, Object> variables) {
/* 18 */     localVars.set(variables);
/*    */   }
/*    */ 
/*    */   public GroovyBinding(String[] args) {
/* 22 */     this();
/* 23 */     setVariable("args", args);
/*    */   }
/*    */ 
/*    */   public Object getVariable(String name) {
/* 27 */     Map map = (Map)localVars.get();
/* 28 */     Object result = null;
/* 29 */     if ((map != null) && (map.containsKey(name)))
/* 30 */       result = map.get(name);
/*    */     else {
/* 32 */       result = propertyMap.get(name);
/*    */     }
/*    */ 
/* 35 */     return result;
/*    */   }
/*    */ 
/*    */   public void setVariable(String name, Object value) {
/* 39 */     if (localVars.get() == null) {
/* 40 */       Map vars = new LinkedHashMap();
/* 41 */       vars.put(name, value);
/* 42 */       localVars.set(vars);
/*    */     } else {
/* 44 */       ((Map)localVars.get()).put(name, value);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Map<String, Object> getVariables() {
/* 49 */     if (localVars.get() == null) {
/* 50 */       return new LinkedHashMap();
/*    */     }
/*    */ 
/* 53 */     return (Map)localVars.get();
/*    */   }
/*    */ 
/*    */   public void clearVariables() {
/* 57 */     localVars.remove();
/*    */   }
/*    */ 
/*    */   public Object getProperty(String property) {
/* 61 */     return propertyMap.get(property);
/*    */   }
/*    */ 
/*    */   public void setProperty(String property, Object newValue) {
/* 65 */     propertyMap.put(property, newValue);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.groovy.GroovyBinding
 * JD-Core Version:    0.6.2
 */