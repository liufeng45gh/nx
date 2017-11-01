/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_role_function")
/*    */ public class TSRoleFunction extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSFunction TSFunction;
/*    */   private TSRole TSRole;
/*    */   private String operation;
/*    */   private String dataRule;
/*    */ 
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="functionid")
/*    */   public TSFunction getTSFunction()
/*    */   {
/* 29 */     return this.TSFunction;
/*    */   }
/*    */ 
/*    */   public void setTSFunction(TSFunction TSFunction) {
/* 33 */     this.TSFunction = TSFunction;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="roleid")
/*    */   public TSRole getTSRole() {
/* 39 */     return this.TSRole;
/*    */   }
/*    */ 
/*    */   public void setTSRole(TSRole TSRole) {
/* 43 */     this.TSRole = TSRole;
/*    */   }
/*    */ 
/*    */   @Column(name="operation", length=100)
/*    */   public String getOperation() {
/* 48 */     return this.operation;
/*    */   }
/*    */ 
/*    */   public void setOperation(String operation) {
/* 52 */     this.operation = operation;
/*    */   }
/*    */   @Column(name="datarule", length=100)
/*    */   public String getDataRule() {
/* 56 */     return this.dataRule;
/*    */   }
/*    */ 
/*    */   public void setDataRule(String dataRule) {
/* 60 */     this.dataRule = dataRule;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSRoleFunction
 * JD-Core Version:    0.6.2
 */