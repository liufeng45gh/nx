/*     */ package org.jeecgframework.web.system.servlet;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */  enum RandCodeImageEnum
/*     */ {
/* 179 */   ALL_CHAR(
/* 182 */     "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 
/*     */ 
/* 186 */   LETTER_CHAR("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 
/* 187 */   LOWER_CHAR(
/* 190 */     "abcdefghijklmnopqrstuvwxyz"), 
/* 191 */   NUMBER_CHAR(
/* 194 */     "0123456789"), 
/* 195 */   UPPER_CHAR(
/* 198 */     "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
/*     */ 
/*     */   private String charStr;
/*     */ 
/*     */   private RandCodeImageEnum(String charStr)
/*     */   {
/* 208 */     this.charStr = charStr;
/*     */   }
/*     */ 
/*     */   public String generateStr(int codeLength)
/*     */   {
/* 219 */     StringBuffer sb = new StringBuffer();
/* 220 */     Random random = new Random();
/* 221 */     String sourseStr = getCharStr();
/*     */ 
/* 223 */     for (int i = 0; i < codeLength; i++) {
/* 224 */       sb.append(sourseStr.charAt(random.nextInt(sourseStr.length())));
/*     */     }
/*     */ 
/* 227 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public String getCharStr()
/*     */   {
/* 234 */     return this.charStr;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.servlet.RandCodeImageEnum
 * JD-Core Version:    0.6.2
 */