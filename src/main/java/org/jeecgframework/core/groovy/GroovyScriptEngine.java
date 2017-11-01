/*    */ package org.jeecgframework.core.groovy;
/*    */ 
/*    */ import groovy.lang.GroovyShell;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.springframework.beans.BeansException;
/*    */ import org.springframework.beans.factory.config.BeanPostProcessor;
/*    */ 
/*    */ public class GroovyScriptEngine
/*    */   implements BeanPostProcessor
/*    */ {
/* 20 */   private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
/* 21 */   public GroovyBinding binding = new GroovyBinding();
/*    */ 
/*    */   public void execute(String script, Map<String, Object> vars) {
/* 24 */     executeObject(script, vars);
/*    */   }
/*    */ 
/*    */   private void setParameters(GroovyShell shell, Map<String, Object> vars) {
/* 28 */     if (vars == null)
/* 29 */       return;
/* 30 */     Set set = vars.entrySet();
/* 31 */     for (Iterator it = set.iterator(); it.hasNext(); )
/*    */     {
/* 33 */       Map.Entry entry = (Map.Entry)it.next();
/* 34 */       shell.setVariable((String)entry.getKey(), entry.getValue());
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean executeBoolean(String script, Map<String, Object> vars) {
/* 39 */     Boolean rtn = (Boolean)executeObject(script, vars);
/* 40 */     return rtn.booleanValue();
/*    */   }
/*    */ 
/*    */   public String executeString(String script, Map<String, Object> vars) {
/* 44 */     String str = (String)executeObject(script, vars);
/* 45 */     return str;
/*    */   }
/*    */ 
/*    */   public int executeInt(String script, Map<String, Object> vars) {
/* 49 */     Integer rtn = (Integer)executeObject(script, vars);
/* 50 */     return rtn.intValue();
/*    */   }
/*    */ 
/*    */   public float executeFloat(String script, Map<String, Object> vars) {
/* 54 */     Float rtn = (Float)executeObject(script, vars);
/* 55 */     return rtn.floatValue();
/*    */   }
/*    */ 
/*    */   public Object executeObject(String script, Map<String, Object> vars) {
/* 59 */     this.logger.debug("执行:" + script);
/* 60 */     this.binding.clearVariables();
/* 61 */     GroovyShell shell = new GroovyShell(this.binding);
/* 62 */     setParameters(shell, vars);
/*    */ 
/* 64 */     script = script.replace("&apos;", "'").replace("&quot;", "\"").replace("&gt;", ">").replace("&lt;", "<").replace("&nuot;", "\n").replace("&amp;", "&");
/*    */ 
/* 66 */     Object rtn = shell.evaluate(script);
/*    */ 
/* 68 */     return rtn;
/*    */   }
/*    */ 
/*    */   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
/* 72 */     boolean isImplScript = bean.getClass().isInstance(IScript.class);
/* 73 */     if (isImplScript) {
/* 74 */       this.binding.setProperty(beanName, bean);
/*    */     }
/* 76 */     return bean;
/*    */   }
/*    */ 
/*    */   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
/* 80 */     return bean;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.groovy.GroovyScriptEngine
 * JD-Core Version:    0.6.2
 */