/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Client
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private TSUser user;
/*    */   private Map<String, TSFunction> functions;
/*    */   private Map<Integer, List<TSFunction>> functionMap;
/*    */   private String ip;
/*    */   private Date logindatetime;
/*    */ 
/*    */   public Map<Integer, List<TSFunction>> getFunctionMap()
/*    */   {
/* 22 */     return this.functionMap;
/*    */   }
/*    */ 
/*    */   public void setFunctionMap(Map<Integer, List<TSFunction>> functionMap) {
/* 26 */     this.functionMap = functionMap;
/*    */   }
/*    */ 
/*    */   public TSUser getUser()
/*    */   {
/* 39 */     return this.user;
/*    */   }
/*    */ 
/*    */   public void setUser(TSUser user) {
/* 43 */     this.user = user;
/*    */   }
/*    */ 
/*    */   public Map<String, TSFunction> getFunctions()
/*    */   {
/* 48 */     return this.functions;
/*    */   }
/*    */ 
/*    */   public void setFunctions(Map<String, TSFunction> functions) {
/* 52 */     this.functions = functions;
/*    */   }
/*    */ 
/*    */   public String getIp() {
/* 56 */     return this.ip;
/*    */   }
/*    */ 
/*    */   public void setIp(String ip) {
/* 60 */     this.ip = ip;
/*    */   }
/*    */ 
/*    */   public Date getLogindatetime() {
/* 64 */     return this.logindatetime;
/*    */   }
/*    */ 
/*    */   public void setLogindatetime(Date logindatetime) {
/* 68 */     this.logindatetime = logindatetime;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.Client
 * JD-Core Version:    0.6.2
 */