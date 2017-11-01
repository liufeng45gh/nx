/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.persistence.Table;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_type")
/*    */ public class TSType extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private TSTypegroup TSTypegroup;
/*    */   private TSType TSType;
/*    */   private String typename;
/*    */   private String typecode;
/* 31 */   private List<TSType> TSTypes = new ArrayList();
/*    */ 
/* 36 */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="typegroupid")
/*    */   public TSTypegroup getTSTypegroup() { return this.TSTypegroup; }
/*    */ 
/*    */   public void setTSTypegroup(TSTypegroup TSTypegroup)
/*    */   {
/* 40 */     this.TSTypegroup = TSTypegroup;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="typepid")
/*    */   public TSType getTSType() {
/* 46 */     return this.TSType;
/*    */   }
/*    */ 
/*    */   public void setTSType(TSType TSType) {
/* 50 */     this.TSType = TSType;
/*    */   }
/*    */ 
/*    */   @Column(name="typename", length=50)
/*    */   public String getTypename() {
/* 55 */     return this.typename;
/*    */   }
/*    */ 
/*    */   public void setTypename(String typename) {
/* 59 */     this.typename = typename;
/*    */   }
/*    */ 
/*    */   @Column(name="typecode", length=50)
/*    */   public String getTypecode() {
/* 64 */     return this.typecode;
/*    */   }
/*    */ 
/*    */   public void setTypecode(String typecode) {
/* 68 */     this.typecode = typecode;
/*    */   }
/*    */ 
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TSType")
/*    */   public List<TSType> getTSTypes()
/*    */   {
/* 83 */     return this.TSTypes;
/*    */   }
/*    */ 
/*    */   public void setTSTypes(List<TSType> TSTypes) {
/* 87 */     this.TSTypes = TSTypes;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSType
 * JD-Core Version:    0.6.2
 */