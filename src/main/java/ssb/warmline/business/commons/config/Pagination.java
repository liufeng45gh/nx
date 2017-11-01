/*     */ package ssb.warmline.business.commons.config;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Pagination
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 30161819074846596L;
/*     */   public static final int PAGESIZE = 20;
/*  28 */   private int pageSize = 20;
/*     */   private int totalResult;
/*     */   private int startResult;
/*     */   private int indexes;
/*     */   private List<?> items;
/*     */ 
/*     */   public Pagination(List<?> items, int totalResult)
/*     */   {
/*  63 */     setPageSize(20);
/*  64 */     setTotalResult(totalResult);
/*  65 */     setStartResult(0);
/*  66 */     setItems(items);
/*     */   }
/*     */ 
/*     */   public Pagination(List<?> items, int totalResult, int startResult)
/*     */   {
/*  79 */     setPageSize(20);
/*  80 */     setTotalResult(totalResult);
/*  81 */     setStartResult(startResult);
/*  82 */     setItems(items);
/*     */   }
/*     */ 
/*     */   public Pagination(List<?> items, int totalResult, int startResult, int pageSize)
/*     */   {
/*  98 */     setPageSize(pageSize);
/*  99 */     setTotalResult(totalResult);
/* 100 */     setStartResult(startResult);
/* 101 */     setItems(items);
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 105 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 109 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getTotalResult() {
/* 113 */     return this.totalResult;
/*     */   }
/*     */ 
/*     */   public void setTotalResult(int totalResult)
/*     */   {
/* 123 */     if (totalResult > 0) {
/* 124 */       this.totalResult = totalResult;
/* 125 */       int count = totalResult / this.pageSize;
/* 126 */       if (totalResult % this.pageSize > 0)
/* 127 */         count++;
/* 128 */       this.indexes = count;
/*     */     } else {
/* 130 */       this.totalResult = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getStartResult() {
/* 135 */     return this.startResult;
/*     */   }
/*     */ 
/*     */   public void setStartResult(int startResult)
/*     */   {
/* 145 */     this.startResult = startResult;
/*     */   }
/*     */ 
/*     */   public int getNextIndex()
/*     */   {
/* 154 */     int nextIndex = getStartResult() + this.pageSize;
/* 155 */     if (nextIndex >= this.totalResult) {
/* 156 */       return getStartResult();
/*     */     }
/* 158 */     return nextIndex;
/*     */   }
/*     */ 
/*     */   public int getPreviousIndex()
/*     */   {
/* 167 */     int previousIndex = getStartResult() - this.pageSize;
/* 168 */     if (previousIndex < 0) {
/* 169 */       return 0;
/*     */     }
/* 171 */     return previousIndex;
/*     */   }
/*     */ 
/*     */   public int getIndexes() {
/* 175 */     return this.indexes;
/*     */   }
/*     */ 
/*     */   public void setIndexes(int indexes) {
/* 179 */     this.indexes = indexes;
/*     */   }
/*     */ 
/*     */   public List<?> getItems()
/*     */   {
/* 188 */     return this.items;
/*     */   }
/*     */ 
/*     */   public void setItems(List<?> items) {
/* 192 */     this.items = items;
/*     */   }
/*     */ 
/*     */   public int getTotalCount() {
/* 196 */     return getIndexes();
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.Pagination
 * JD-Core Version:    0.6.2
 */