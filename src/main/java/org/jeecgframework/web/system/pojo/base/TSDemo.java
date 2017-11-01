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
/*    */ @Table(name="t_s_demo")
/*    */ public class TSDemo extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String demotitle;
/*    */   private String demourl;
/*    */   private TSDemo TSDemo;
/*    */   private Short demoorder;
/*    */   private String democode;
/* 29 */   private List<TSDemo> tsDemos = new ArrayList();
/*    */ 
/* 32 */   @Column(name="demotitle", length=200)
/*    */   public String getDemotitle() { return this.demotitle; }
/*    */ 
/*    */   public void setDemotitle(String demotitle)
/*    */   {
/* 36 */     this.demotitle = demotitle;
/*    */   }
/*    */ 
/*    */   @Column(name="demourl", length=200)
/*    */   public String getDemourl() {
/* 41 */     return this.demourl;
/*    */   }
/*    */ 
/*    */   public void setDemourl(String demourl) {
/* 45 */     this.demourl = demourl;
/*    */   }
/*    */   @ManyToOne(fetch=FetchType.LAZY)
/*    */   @JoinColumn(name="demopid")
/*    */   public TSDemo getTSDemo() {
/* 51 */     return this.TSDemo;
/*    */   }
/*    */ 
/*    */   public void setTSDemo(TSDemo tSDemo) {
/* 55 */     this.TSDemo = tSDemo;
/*    */   }
/*    */ 
/*    */   @Column(name="demoorder")
/*    */   public Short getDemoorder() {
/* 60 */     return this.demoorder;
/*    */   }
/*    */ 
/*    */   public void setDemoorder(Short demoorder) {
/* 64 */     this.demoorder = demoorder;
/*    */   }
/*    */ 
/*    */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TSDemo")
/*    */   public List<TSDemo> getTsDemos() {
/* 69 */     return this.tsDemos;
/*    */   }
/*    */ 
/*    */   public void setTsDemos(List<TSDemo> tsDemos) {
/* 73 */     this.tsDemos = tsDemos;
/*    */   }
/*    */   @Column(name="democode", length=8000)
/*    */   public String getDemocode() {
/* 77 */     return this.democode;
/*    */   }
/*    */ 
/*    */   public void setDemocode(String democode) {
/* 81 */     this.democode = democode;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSDemo
 * JD-Core Version:    0.6.2
 */