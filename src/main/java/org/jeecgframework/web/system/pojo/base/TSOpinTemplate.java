/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_opintemplate")
/*    */ public class TSOpinTemplate extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String descript;
/*    */ 
/*    */   @Column(name="descript", length=100)
/*    */   public String getDescript()
/*    */   {
/* 31 */     return this.descript;
/*    */   }
/*    */ 
/*    */   public void setDescript(String descript) {
/* 35 */     this.descript = descript;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSOpinTemplate
 * JD-Core Version:    0.6.2
 */