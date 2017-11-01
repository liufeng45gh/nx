/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class LogUtil
/*    */ {
/* 11 */   protected static final Logger log = LoggerFactory.getLogger(LogUtil.class);
/* 12 */   private static int logRows = 50;
/* 13 */   private static int logSize = 2000;
/*    */ 
/*    */   public static void saveException(Logger logger, Throwable exception)
/*    */   {
/*    */     try
/*    */     {
/* 22 */       String errmsg = getExceptionStackMsg(exception);
/* 23 */       logger.error(errmsg);
/*    */     } catch (Exception e) {
/* 25 */       log.error(getExceptionStackMsg(e));
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getExceptionStackMsg(Throwable exception)
/*    */   {
/* 35 */     String errmsg = "";
/*    */     try {
/* 37 */       if (exception != null) {
/* 38 */         StringBuffer buffer = new StringBuffer();
/* 39 */         StackTraceElement[] stem = exception.getStackTrace();
/* 40 */         for (int i = 0; i < stem.length; i++) {
/* 41 */           buffer.append(stem[i].toString()).append(System.getProperty("line.separator"));
/* 42 */           if (i > logRows) {
/*    */             break;
/*    */           }
/*    */         }
/* 46 */         if (buffer.length() < 10) {
/* 47 */           StackTraceElement[] stack = new Throwable().getStackTrace();
/* 48 */           for (int i = 0; (i < stack.length) && (i < 10); i++) {
/* 49 */             StackTraceElement s = stack[i];
/* 50 */             buffer.append("----->").append(s.getClassName()).append(".").append(s.getMethodName()).append(
/* 51 */               "(").append(s.getFileName()).append(":").append(s.getLineNumber()).append(")").append(
/* 52 */               System.getProperty("line.separator"));
/*    */           }
/*    */         }
/* 55 */         buffer.insert(0, "出现异常:" + exception.getMessage() + "  ");
/* 56 */         errmsg = buffer.toString();
/* 57 */         if (errmsg.getBytes().length > logSize)
/* 58 */           errmsg = new String(errmsg.getBytes(), 0, logSize - 1);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 62 */       e.printStackTrace();
/*    */     }
/* 64 */     return errmsg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.LogUtil
 * JD-Core Version:    0.6.2
 */