/*    */ package org.jeecgframework.core.timer;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
/*    */ import org.jeecgframework.web.system.service.TimeTaskServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.scheduling.quartz.CronTriggerBean;
/*    */ 
/*    */ public class DataBaseCronTriggerBean extends CronTriggerBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   @Autowired
/*    */   private TimeTaskServiceI timeTaskService;
/*    */ 
/*    */   public void afterPropertiesSet()
/*    */   {
/* 25 */     super.afterPropertiesSet();
/* 26 */     TSTimeTaskEntity task = (TSTimeTaskEntity)this.timeTaskService.findUniqueByProperty(
/* 27 */       TSTimeTaskEntity.class, "taskId", getName());
/* 28 */     if ((task != null) && (task.getIsEffect().equals("1")) && 
/* 29 */       (!task.getCronExpression().equals(getCronExpression())))
/*    */     {
/*    */       try {
/* 32 */         setCronExpression(task.getCronExpression());
/*    */       }
/*    */       catch (ParseException e) {
/* 35 */         e.printStackTrace();
/*    */       }
/*    */ 
/* 38 */       DynamicTask.updateSpringMvcTaskXML(this, task.getCronExpression());
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.timer.DataBaseCronTriggerBean
 * JD-Core Version:    0.6.2
 */