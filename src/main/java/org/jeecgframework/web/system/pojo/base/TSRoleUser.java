/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_role_user")
/*    */ public class TSRoleUser extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSUser TSUser;
/*    */   private TSRole TSRole;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="userid")
/*    */   public TSUser getTSUser()
/*    */   {
/* 28 */     return this.TSUser;
/*    */   }
/*    */ 
/*    */   public void setTSUser(TSUser TSUser) {
/* 32 */     this.TSUser = TSUser;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.EAGER)
/*    */   @JoinColumn(name="roleid")
/*    */   public TSRole getTSRole() {
/* 38 */     return this.TSRole;
/*    */   }
/*    */ 
/*    */   public void setTSRole(TSRole TSRole) {
/* 42 */     this.TSRole = TSRole;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSRoleUser
 * JD-Core Version:    0.6.2
 */