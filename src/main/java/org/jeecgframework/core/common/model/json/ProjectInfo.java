/*    */ package org.jeecgframework.core.common.model.json;
/*    */ 
/*    */ import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/*    */ 
/*    */ @JsonIgnoreProperties({"hibernateLazyInitializer"})
/*    */ public class ProjectInfo
/*    */ {
/*    */   private Integer gid;
/*    */   private String name;
/*    */   private String geom;
/*    */ 
/*    */   public Integer getGid()
/*    */   {
/* 12 */     return this.gid;
/*    */   }
/*    */   public void setGid(Integer gid) {
/* 15 */     this.gid = gid;
/*    */   }
/*    */   public String getName() {
/* 18 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 21 */     this.name = name;
/*    */   }
/*    */   public String getGeom() {
/* 24 */     return this.geom;
/*    */   }
/*    */   public void setGeom(String geom) {
/* 27 */     this.geom = geom;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.model.json.ProjectInfo
 * JD-Core Version:    0.6.2
 */