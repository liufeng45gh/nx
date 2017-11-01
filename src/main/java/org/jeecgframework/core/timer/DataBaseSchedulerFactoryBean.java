/*    */ package org.jeecgframework.core.timer;
/*    */ 
/*    */ import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
/*    */ import org.jeecgframework.web.system.service.TimeTaskServiceI;
/*    */ import org.quartz.Scheduler;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.scheduling.quartz.SchedulerFactoryBean;
/*    */ 
/*    */ public class DataBaseSchedulerFactoryBean extends SchedulerFactoryBean
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private TimeTaskServiceI timeTaskService;
/*    */ 
/*    */   public void afterPropertiesSet()
/*    */     throws Exception
/*    */   {
/* 23 */     super.afterPropertiesSet();
/* 24 */     String[] trigerrNames = getScheduler().getTriggerNames("DEFAULT");
/*    */ 
/* 27 */     for (String trigerrName : trigerrNames) {
/* 28 */       TSTimeTaskEntity task = (TSTimeTaskEntity)this.timeTaskService.findUniqueByProperty(TSTimeTaskEntity.class, "taskId", trigerrName);
/*    */ 
/* 31 */       if ((task == null) || (!"1".equals(task.getIsStart())))
/* 32 */         getScheduler().pauseTrigger(trigerrName, "DEFAULT");
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.timer.DataBaseSchedulerFactoryBean
 * JD-Core Version:    0.6.2
 */