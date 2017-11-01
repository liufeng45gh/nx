/*     */ package ssb.warmline.business.service.impl.tsbaseuser;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ class Db2Page
/*     */ {
/*     */   String fieldPage;
/*     */   String columnDB;
/*     */   IMyDataExchanger dataExchanger;
/*     */ 
/*     */   public Db2Page(String fieldPage)
/*     */   {
/* 160 */     this.fieldPage = fieldPage;
/* 161 */     this.columnDB = fieldPage;
/* 162 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB)
/*     */   {
/* 167 */     this.fieldPage = fieldPage;
/* 168 */     if (columnDB == null)
/* 169 */       this.columnDB = fieldPage;
/*     */     else {
/* 171 */       this.columnDB = columnDB;
/*     */     }
/* 173 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB, IMyDataExchanger dataExchanger)
/*     */   {
/* 178 */     this.fieldPage = fieldPage;
/* 179 */     if (columnDB == null)
/* 180 */       this.columnDB = fieldPage;
/*     */     else {
/* 182 */       this.columnDB = columnDB;
/*     */     }
/* 184 */     this.dataExchanger = dataExchanger;
/*     */   }
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 191 */     return this.fieldPage;
/*     */   }
/*     */ 
/*     */   public Object getData(Map mapDB)
/*     */   {
/* 202 */     Object objValue = mapDB.get(this.columnDB);
/* 203 */     if (objValue == null) {
/* 204 */       return null;
/*     */     }
/* 206 */     if (this.dataExchanger != null) {
/* 207 */       return this.dataExchanger.exchange(objValue);
/*     */     }
/* 209 */     return objValue;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.tsbaseuser.Db2Page
 * JD-Core Version:    0.6.2
 */