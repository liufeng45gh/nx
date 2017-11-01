/*    */ package ssb.warmline.business.entity;
/*    */ 
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Table(name="project_config")
/*    */ public class ProjectConfig
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @GeneratedValue(generator="JDBC")
/*    */   private Integer id;
/*    */   private String name;
/*    */   private String value;
/*    */ 
/*    */   public ProjectConfig()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ProjectConfig(String name)
/*    */   {
/* 22 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public ProjectConfig(Integer id, String name, String value)
/*    */   {
/* 28 */     this.id = id;
/* 29 */     this.name = name;
/* 30 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id)
/*    */   {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 53 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 60 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 67 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 74 */     this.value = value;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.ProjectConfig
 * JD-Core Version:    0.6.2
 */