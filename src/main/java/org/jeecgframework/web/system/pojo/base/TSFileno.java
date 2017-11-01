/*    */ package org.jeecgframework.web.system.pojo.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Table;
/*    */ import javax.persistence.Temporal;
/*    */ import javax.persistence.TemporalType;
/*    */ import org.jeecgframework.core.common.entity.IdEntity;
/*    */ 
/*    */ @Entity
/*    */ @Table(name="t_s_fileno")
/*    */ public class TSFileno extends IdEntity
/*    */   implements Serializable
/*    */ {
/*    */   private String filenoBefore;
/*    */   private int filenoNum;
/*    */   private Date filenoYear;
/*    */   private String filenoType;
/*    */ 
/*    */   @Column(name="filenobefore", length=32)
/*    */   public String getFilenoBefore()
/*    */   {
/* 27 */     return this.filenoBefore;
/*    */   }
/*    */ 
/*    */   public void setFilenoBefore(String filenoBefore) {
/* 31 */     this.filenoBefore = filenoBefore;
/*    */   }
/*    */   @Column(name="filenonum")
/*    */   public int getFilenoNum() {
/* 35 */     return this.filenoNum;
/*    */   }
/*    */ 
/*    */   public void setFilenoNum(int filenoNum) {
/* 39 */     this.filenoNum = filenoNum;
/*    */   }
/*    */ 
/*    */   @Column(name="filenotype", length=32)
/*    */   public String getFilenoType() {
/* 44 */     return this.filenoType;
/*    */   }
/*    */ 
/*    */   public void setFilenoType(String filenoType) {
/* 48 */     this.filenoType = filenoType;
/*    */   }
/* 53 */   @Temporal(TemporalType.DATE)
/*    */   @Column(name="filenoYear", length=13)
/*    */   public Date getFilenoYear() { return this.filenoYear; }
/*    */ 
/*    */   public void setFilenoYear(Date filenoYear)
/*    */   {
/* 57 */     this.filenoYear = filenoYear;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.pojo.base.TSFileno
 * JD-Core Version:    0.6.2
 */