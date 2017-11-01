/*     */ package ssb.warmline.business.entity.wquestionanswer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ import org.jeecgframework.poi.excel.annotation.Excel;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="w_question_answer", schema="")
/*     */ public class WQuestionAnswerEntity
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */ 
/*     */   @Excel(name="问题")
/*     */   private String problem;
/*     */ 
/*     */   @Excel(name="答案")
/*     */   private String answer;
/*     */ 
/*     */   @Excel(name="创建时间", format="yyyy-MM-dd")
/*     */   private Date creatTime;
/*     */ 
/*     */   @Excel(name="序号")
/*     */   private Integer serialNumber;
/*     */ 
/*     */   @Id
/*     */   @GeneratedValue(generator="paymentableGenerator")
/*     */   @GenericGenerator(name="paymentableGenerator", strategy="uuid")
/*     */   @Column(name="ID", nullable=false, length=255)
/*     */   public String getId()
/*     */   {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  65 */     this.id = id;
/*     */   }
/*     */ 
/*     */   @Column(name="PROBLEM", nullable=true, length=500)
/*     */   public String getProblem()
/*     */   {
/*  73 */     return this.problem;
/*     */   }
/*     */ 
/*     */   public void setProblem(String problem)
/*     */   {
/*  81 */     this.problem = problem;
/*     */   }
/*     */ 
/*     */   @Column(name="ANSWER", nullable=true, length=1000)
/*     */   public String getAnswer()
/*     */   {
/*  89 */     return this.answer;
/*     */   }
/*     */ 
/*     */   public void setAnswer(String answer)
/*     */   {
/*  97 */     this.answer = answer;
/*     */   }
/*     */ 
/*     */   @Column(name="CREAT_TIME", nullable=true)
/*     */   public Date getCreatTime()
/*     */   {
/* 105 */     return this.creatTime;
/*     */   }
/*     */ 
/*     */   public void setCreatTime(Date creatTime)
/*     */   {
/* 113 */     this.creatTime = creatTime;
/*     */   }
/*     */ 
/*     */   @Column(name="SERIAL_NUMBER", nullable=true, length=10)
/*     */   public Integer getSerialNumber()
/*     */   {
/* 121 */     return this.serialNumber;
/*     */   }
/*     */ 
/*     */   public void setSerialNumber(Integer serialNumber)
/*     */   {
/* 129 */     this.serialNumber = serialNumber;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.entity.wquestionanswer.WQuestionAnswerEntity
 * JD-Core Version:    0.6.2
 */