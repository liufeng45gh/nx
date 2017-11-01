/*     */ package ssb.warmline.business.service.impl.worder;
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
/* 263 */     this.fieldPage = fieldPage;
/* 264 */     this.columnDB = fieldPage;
/* 265 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB)
/*     */   {
/* 270 */     this.fieldPage = fieldPage;
/* 271 */     if (columnDB == null)
/* 272 */       this.columnDB = fieldPage;
/*     */     else {
/* 274 */       this.columnDB = columnDB;
/*     */     }
/* 276 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB, IMyDataExchanger dataExchanger)
/*     */   {
/* 281 */     this.fieldPage = fieldPage;
/* 282 */     if (columnDB == null)
/* 283 */       this.columnDB = fieldPage;
/*     */     else {
/* 285 */       this.columnDB = columnDB;
/*     */     }
/* 287 */     this.dataExchanger = dataExchanger;
/*     */   }
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 294 */     return this.fieldPage;
/*     */   }
/*     */ 
/*     */   public Object getData(Map mapDB)
/*     */   {
/* 305 */     Object objValue = mapDB.get(this.columnDB);
/* 306 */     if (objValue == null) {
/* 307 */       return null;
/*     */     }
/* 309 */     if (this.dataExchanger != null) {
/* 310 */       return this.dataExchanger.exchange(objValue);
/*     */     }
/* 312 */     return objValue;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.worder.Db2Page
 * JD-Core Version:    0.6.2
 */