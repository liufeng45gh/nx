/*    */ package org.jeecgframework.web.demo.service.impl.test;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.jeecgframework.web.demo.service.test.TaskDemoServiceI;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("taskDemoService")
/*    */ public class TaskDemoServiceImpl
/*    */   implements TaskDemoServiceI
/*    */ {
/*    */   public void work()
/*    */   {
/* 13 */     LogUtil.info(Long.valueOf(new Date().getTime()));
/* 14 */     LogUtil.info("----------任务测试-------");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.demo.service.impl.test.TaskDemoServiceImpl
 * JD-Core Version:    0.6.2
 */