/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_typegroup")
/*    */ public class TSTypegroup extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String typegroupname;
/*    */   private String typegroupcode;
/* 26 */   private List<TSType> TSTypes = new ArrayList();
/*    */ 
/* 29 */   @Column(name="typegroupname", length=50)
/*    */   public String getTypegroupname() { return this.typegroupname; }
/*    */ 
/*    */   public void setTypegroupname(String typegroupname)
/*    */   {
/* 33 */     this.typegroupname = typegroupname;
/*    */   }
/*    */ 
/*    */   @Column(name="typegroupcode", length=50)
/*    */   public String getTypegroupcode() {
/* 38 */     return this.typegroupcode;
/*    */   }
/*    */ 
/*    */   public void setTypegroupcode(String typegroupcode) {
/* 42 */     this.typegroupcode = typegroupcode;
/*    */   }
/*    */ 
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TSTypegroup")
/*    */   public List<TSType> getTSTypes() {
/* 47 */     return this.TSTypes;
/*    */   }
/*    */ 
/*    */   public void setTSTypes(List<TSType> TSTypes) {
/* 51 */     this.TSTypes = TSTypes;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSTypegroup
 * JD-Core Version:    0.6.2
 */