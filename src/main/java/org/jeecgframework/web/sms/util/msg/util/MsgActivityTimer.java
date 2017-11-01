/*    */ package org.jeecgframework.web.sms.util.msg.util;
/*    */ 
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ import org.springframework.scheduling.quartz.QuartzJobBean;
/*    */ 
/*    */ public class MsgActivityTimer extends QuartzJobBean
/*    */ {
/*    */   protected void executeInternal(JobExecutionContext arg0)
/*    */     throws JobExecutionException
/*    */   {
/* 25 */     LogUtil.info("×××××××××××××开始链路检查××××××××××××××");
/* 26 */     int count = 0;
/* 27 */     boolean result = MsgContainer.activityTestISMG();
/* 28 */     while (!result) {
/* 29 */       count++;
/* 30 */       result = MsgContainer.activityTestISMG();
/* 31 */       if (count >= MsgConfig.getConnectCount() - 1) {
/*    */         break;
/*    */       }
/*    */     }
/* 35 */     LogUtil.info("×××××××××××××链路检查结束××××××××××××××");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.msg.util.MsgActivityTimer
 * JD-Core Version:    0.6.2
 */