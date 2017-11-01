/*     */ package ssb.warmline.business.service.businessprocessor;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*     */ 
/*     */ @Service
/*     */ public class BaseInterface
/*     */ {
/*  20 */   protected static final Logger logger = LoggerFactory.getLogger(BaseInterface.class);
/*     */   private String taskType;
/*     */   private static ApplicationContext context;
/*  24 */   private static Map taskStatisticMap = new HashMap();
/*     */ 
/*     */   public String getTaskType() {
/*  27 */     return this.taskType;
/*     */   }
/*     */ 
/*     */   public void setTaskType(String taskType) {
/*  31 */     this.taskType = taskType;
/*     */   }
/*     */ 
/*     */   private String parseReturnData(Object obj)
/*     */   {
/*  41 */     String rs = null;
/*  42 */     return rs;
/*     */   }
/*     */ 
/*     */   public synchronized void updateCallCount()
/*     */   {
/*     */   }
/*     */ 
/*     */   public synchronized void errorStatistic()
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void resetTaskStatisticMap()
/*     */   {
/*  60 */     taskStatisticMap.clear();
/*     */   }
/*     */ 
/*     */   public static Map getTaskStatisticMap() {
/*  64 */     return taskStatisticMap;
/*     */   }
/*     */ 
/*     */   public boolean ipInterceptor(String taskType, HttpServletRequest req) {
/*  68 */     if (("24".equals(taskType)) || ("25".equals(taskType)) || ("26".equals(taskType))) {
/*  69 */       context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
/*     */ 
/*  73 */       String remoteAddr = getRemoteIp(req);
/*     */ 
/*  79 */       return false;
/*     */     }
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   protected String getRemoteIp(HttpServletRequest req)
/*     */   {
/*  89 */     String remoteIp = req.getHeader("x-forwarded-for");
/*  90 */     if ((remoteIp == null) || (remoteIp.length() == 0) || ("unknown".equalsIgnoreCase(remoteIp))) {
/*  91 */       remoteIp = req.getHeader("Proxy-Client-IP");
/*     */     }
/*  93 */     if ((remoteIp == null) || (remoteIp.length() == 0) || ("unknown".equalsIgnoreCase(remoteIp))) {
/*  94 */       remoteIp = req.getHeader("WL-Proxy-Client-IP");
/*     */     }
/*  96 */     if ((remoteIp == null) || (remoteIp.length() == 0) || ("unknown".equalsIgnoreCase(remoteIp))) {
/*  97 */       remoteIp = req.getHeader("HTTP_CLIENT_IP");
/*     */     }
/*  99 */     if ((remoteIp == null) || (remoteIp.length() == 0) || ("unknown".equalsIgnoreCase(remoteIp))) {
/* 100 */       remoteIp = req.getHeader("HTTP_X_FORWARDED-FOR");
/*     */     }
/* 102 */     if ((remoteIp == null) || (remoteIp.length() == 0) || ("unknown".equalsIgnoreCase(remoteIp))) {
/* 103 */       remoteIp = req.getRemoteAddr();
/*     */     }
/*     */ 
/* 107 */     if ((remoteIp != null) && (remoteIp.length() > 15) && 
/* 108 */       (remoteIp.indexOf(",") > 0)) {
/* 109 */       remoteIp = remoteIp.substring(0, remoteIp.indexOf(","));
/*     */     }
/*     */ 
/* 112 */     System.out.println("当前请求的ip地址为：" + remoteIp);
/* 113 */     return remoteIp;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.businessprocessor.BaseInterface
 * JD-Core Version:    0.6.2
 */