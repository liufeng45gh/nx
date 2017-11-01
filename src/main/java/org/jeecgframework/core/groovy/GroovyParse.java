/*    */ package org.jeecgframework.core.groovy;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jeecgframework.core.util.ApplicationContextUtil;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class GroovyParse
/*    */ {
/*    */   public static Object formulaParse(String formula, Map<String, Object> map)
/*    */   {
/* 19 */     ApplicationContext context = ApplicationContextUtil.getContext();
/* 20 */     GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine)context.getBean(GroovyScriptEngine.class);
/* 21 */     Object value = groovyScriptEngine.executeObject(formula, map);
/* 22 */     return value;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 26 */     String formula = "println 'Hello World!';po = '9s00';return (a * b);";
/* 27 */     Map map = new HashMap();
/* 28 */     map.put("a", Integer.valueOf(900));
/* 29 */     map.put("b", Integer.valueOf(10));
/* 30 */     GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine();
/* 31 */     Object value = groovyScriptEngine.executeObject(formula, map);
/* 32 */     System.out.println(value);
/* 33 */     System.out.println(groovyScriptEngine.binding.getVariable("po"));
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.groovy.GroovyParse
 * JD-Core Version:    0.6.2
 */