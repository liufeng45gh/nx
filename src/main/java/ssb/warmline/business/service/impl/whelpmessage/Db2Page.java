/*     */ package ssb.warmline.business.service.impl.whelpmessage;
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
/* 164 */     this.fieldPage = fieldPage;
/* 165 */     this.columnDB = fieldPage;
/* 166 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB)
/*     */   {
/* 171 */     this.fieldPage = fieldPage;
/* 172 */     if (columnDB == null)
/* 173 */       this.columnDB = fieldPage;
/*     */     else {
/* 175 */       this.columnDB = columnDB;
/*     */     }
/* 177 */     this.dataExchanger = null;
/*     */   }
/*     */ 
/*     */   public Db2Page(String fieldPage, String columnDB, IMyDataExchanger dataExchanger)
/*     */   {
/* 182 */     this.fieldPage = fieldPage;
/* 183 */     if (columnDB == null)
/* 184 */       this.columnDB = fieldPage;
/*     */     else {
/* 186 */       this.columnDB = columnDB;
/*     */     }
/* 188 */     this.dataExchanger = dataExchanger;
/*     */   }
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 195 */     return this.fieldPage;
/*     */   }
/*     */ 
/*     */   public Object getData(Map mapDB)
/*     */   {
/* 206 */     Object objValue = mapDB.get(this.columnDB);
/* 207 */     if (objValue == null) {
/* 208 */       return null;
/*     */     }
/* 210 */     if (this.dataExchanger != null) {
/* 211 */       return this.dataExchanger.exchange(objValue);
/*     */     }
/* 213 */     return objValue;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.service.impl.whelpmessage.Db2Page
 * JD-Core Version:    0.6.2
 */