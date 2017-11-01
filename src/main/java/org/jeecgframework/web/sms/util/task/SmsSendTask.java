/*    */ package org.jeecgframework.web.sms.util.task;
/*    */ 
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.jeecgframework.web.sms.service.TSSmsServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("smsSendTask")
/*    */ public class SmsSendTask
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TSSmsServiceI tSSmsService;
/*    */ 
/*    */   public void run()
/*    */   {
/* 27 */     long start = System.currentTimeMillis();
/* 28 */     LogUtil.info("===================消息中间件定时任务开始===================");
/*    */     try {
/* 30 */       this.tSSmsService.send();
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/* 34 */     LogUtil.info("===================消息中间件定时任务结束===================");
/* 35 */     long end = System.currentTimeMillis();
/* 36 */     long times = end - start;
/* 37 */     LogUtil.info("总耗时" + times + "毫秒");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.sms.util.task.SmsSendTask
 * JD-Core Version:    0.6.2
 */