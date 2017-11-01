/*    */ package org.jeecgframework.web.cgform.entity.generate;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
/*    */ 
/*    */ public class GenerateSubListEntity
/*    */ {
/*    */   private String projectPath;
/*    */   private String packageStyle;
/*    */   private List<SubTableEntity> subTabParamIn;
/*    */ 
/*    */   public List<SubTableEntity> getSubTabParamIn()
/*    */   {
/* 24 */     return this.subTabParamIn;
/*    */   }
/*    */ 
/*    */   public void setSubTabParamIn(List<SubTableEntity> subTabParamIn) {
/* 28 */     this.subTabParamIn = subTabParamIn;
/*    */   }
/*    */ 
/*    */   public String getProjectPath() {
/* 32 */     String pt = this.projectPath;
/* 33 */     if (pt != null) {
/* 34 */       pt = pt.replace("\\", "/");
/* 35 */       if (!pt.endsWith("/")) {
/* 36 */         pt = pt + "/";
/*    */       }
/*    */     }
/* 39 */     return pt;
/*    */   }
/*    */ 
/*    */   public void setProjectPath(String projectPath) {
/* 43 */     this.projectPath = projectPath;
/*    */   }
/*    */ 
/*    */   public String getPackageStyle() {
/* 47 */     return this.packageStyle;
/*    */   }
/*    */ 
/*    */   public void setPackageStyle(String packageStyle) {
/* 51 */     this.packageStyle = packageStyle;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgform.entity.generate.GenerateSubListEntity
 * JD-Core Version:    0.6.2
 */