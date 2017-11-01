/*     */ package org.jeecgframework.tag.vo.easyui;
/*     */ 
/*     */ public class Tab
/*     */ {
/*     */   private String href;
/*     */   private String iframe;
/*     */   private String id;
/*     */   private String title;
/*  14 */   private String icon = "'icon-default'";
/*     */   private String width;
/*     */   private String heigth;
/*     */   private boolean cache;
/*     */   private String content;
/*  19 */   private boolean closable = true;
/*     */ 
/*     */   public boolean isClosable()
/*     */   {
/*  24 */     return this.closable;
/*     */   }
/*     */ 
/*     */   public void setClosable(boolean closable)
/*     */   {
/*  32 */     this.closable = closable;
/*     */   }
/*     */ 
/*     */   public String getHref() {
/*  36 */     return this.href;
/*     */   }
/*     */ 
/*     */   public void setHref(String href) {
/*  40 */     this.href = href;
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/*  44 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title) {
/*  48 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getIcon() {
/*  52 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon) {
/*  56 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   public String getWidth() {
/*  60 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  64 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public String getHeigth() {
/*  68 */     return this.heigth;
/*     */   }
/*     */ 
/*     */   public void setHeigth(String heigth) {
/*  72 */     this.heigth = heigth;
/*     */   }
/*     */ 
/*     */   public boolean isCache() {
/*  76 */     return this.cache;
/*     */   }
/*     */ 
/*     */   public void setCache(boolean cache) {
/*  80 */     this.cache = cache;
/*     */   }
/*     */ 
/*     */   public String getContent() {
/*  84 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/*  88 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public String getId() {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  96 */     this.id = id;
/*     */   }
/*     */   public String getIframe() {
/*  99 */     return this.iframe;
/*     */   }
/*     */ 
/*     */   public void setIframe(String iframe) {
/* 103 */     this.iframe = iframe;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.vo.easyui.Tab
 * JD-Core Version:    0.6.2
 */