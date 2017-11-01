/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/*    */ 
/*    */ @JsonIgnoreProperties({"hibernateLazyInitializer"})
/*    */ public class ProjectDeclare
/*    */ {
/*    */   private Integer detialid;
/*    */   private Integer declareid;
/*    */   private String constructionunit;
/*    */   private String projectname;
/*    */   private String statusid;
/*    */   private String buildingno;
/*    */   private Double undergroundconstructionarea;
/*    */   private String geom;
/*    */ 
/*    */   public Integer getDetialid()
/*    */   {
/* 17 */     return this.detialid;
/*    */   }
/*    */   public void setDetialid(Integer detialid) {
/* 20 */     this.detialid = detialid;
/*    */   }
/*    */   public Integer getDeclareid() {
/* 23 */     return this.declareid;
/*    */   }
/*    */   public void setDeclareid(Integer declareid) {
/* 26 */     this.declareid = declareid;
/*    */   }
/*    */   public String getConstructionunit() {
/* 29 */     return this.constructionunit;
/*    */   }
/*    */   public void setConstructionunit(String constructionunit) {
/* 32 */     this.constructionunit = constructionunit;
/*    */   }
/*    */   public String getProjectname() {
/* 35 */     return this.projectname;
/*    */   }
/*    */   public void setProjectname(String projectname) {
/* 38 */     this.projectname = projectname;
/*    */   }
/*    */   public String getStatusid() {
/* 41 */     return this.statusid;
/*    */   }
/*    */   public void setStatusid(String statusid) {
/* 44 */     this.statusid = statusid;
/*    */   }
/*    */   public String getBuildingno() {
/* 47 */     return this.buildingno;
/*    */   }
/*    */   public void setBuildingno(String buildingno) {
/* 50 */     this.buildingno = buildingno;
/*    */   }
/*    */   public Double getUndergroundconstructionarea() {
/* 53 */     return this.undergroundconstructionarea;
/*    */   }
/*    */   public void setUndergroundconstructionarea(Double undergroundconstructionarea) {
/* 56 */     this.undergroundconstructionarea = undergroundconstructionarea;
/*    */   }
/*    */   public String getGeom() {
/* 59 */     return this.geom;
/*    */   }
/*    */   public void setGeom(String geom) {
/* 62 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.ProjectDeclare
 * JD-Core Version:    0.6.2
 */