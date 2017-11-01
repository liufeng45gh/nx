/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ public class YouBianCodeUtil
/*     */ {
/*     */   private static final int numLength = 2;
/*     */   public static final int zhanweiLength = 3;
/*     */ 
/*     */   public static synchronized String getNextYouBianCode(String code)
/*     */   {
/*  26 */     String newcode = "";
/*  27 */     if ((code == null) || (code == "")) {
/*  28 */       String zimu = "A";
/*  29 */       String num = getStrNum(1);
/*  30 */       newcode = zimu + num;
/*     */     } else {
/*  32 */       String before_code = code.substring(0, code.length() - 1 - 2);
/*  33 */       String after_code = code.substring(code.length() - 1 - 2, code.length());
/*  34 */       char after_code_zimu = after_code.substring(0, 1).charAt(0);
/*  35 */       Integer after_code_num = Integer.valueOf(Integer.parseInt(after_code.substring(1)));
/*     */ 
/*  40 */       String nextNum = "";
/*  41 */       char nextZimu = 'A';
/*     */ 
/*  43 */       if (after_code_num.intValue() == getMaxNumByLength(2))
/*  44 */         nextNum = getNextStrNum(0);
/*     */       else {
/*  46 */         nextNum = getNextStrNum(after_code_num.intValue());
/*     */       }
/*     */ 
/*  49 */       if (after_code_num.intValue() == getMaxNumByLength(2))
/*  50 */         nextZimu = getNextZiMu(after_code_zimu);
/*     */       else {
/*  52 */         nextZimu = after_code_zimu;
/*     */       }
/*     */ 
/*  56 */       if (('Z' == after_code_zimu) && (getMaxNumByLength(2) == after_code_num.intValue()))
/*  57 */         newcode = code + nextZimu + nextNum;
/*     */       else {
/*  59 */         newcode = before_code + nextZimu + nextNum;
/*     */       }
/*     */     }
/*  62 */     return newcode;
/*     */   }
/*     */ 
/*     */   public static synchronized String getSubYouBianCode(String parentCode, String localCode)
/*     */   {
/*  78 */     if ((localCode != null) && (localCode != ""))
/*     */     {
/*  81 */       return getNextYouBianCode(localCode);
/*     */     }
/*     */ 
/*  84 */     parentCode = parentCode + "A" + getNextStrNum(0);
/*     */ 
/*  86 */     return parentCode;
/*     */   }
/*     */ 
/*     */   private static String getNextStrNum(int num)
/*     */   {
/*  98 */     return getStrNum(getNextNum(num));
/*     */   }
/*     */ 
/*     */   private static String getStrNum(int num)
/*     */   {
/* 108 */     String s = String.format("%02d", new Object[] { Integer.valueOf(num) });
/* 109 */     return s;
/*     */   }
/*     */ 
/*     */   private static int getNextNum(int num)
/*     */   {
/* 119 */     num++;
/* 120 */     return num;
/*     */   }
/*     */ 
/*     */   private static char getNextZiMu(char zimu)
/*     */   {
/* 130 */     if (zimu == 'Z') {
/* 131 */       return 'A';
/*     */     }
/* 133 */     zimu = (char)(zimu + '\001');
/* 134 */     return zimu;
/*     */   }
/*     */ 
/*     */   private static int getMaxNumByLength(int length)
/*     */   {
/* 143 */     if (length == 0) {
/* 144 */       return 0;
/*     */     }
/* 146 */     String max_num = "";
/* 147 */     for (int i = 0; i < length; i++) {
/* 148 */       max_num = max_num + "9";
/*     */     }
/* 150 */     return Integer.parseInt(max_num);
/*     */   }
/*     */   public static String[] cutYouBianCode(String code) {
/* 153 */     if ((code == null) || (StringUtil.isEmpty(code))) {
/* 154 */       return null;
/*     */     }
/*     */ 
/* 157 */     int c = code.length() / 3;
/* 158 */     String[] cutcode = new String[c];
/* 159 */     for (int i = 0; i < c; i++) {
/* 160 */       cutcode[i] = code.substring(0, (i + 1) * 3);
/*     */     }
/* 162 */     return cutcode;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 169 */     LogUtil.info(getSubYouBianCode("C99A01", "B03"));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.YouBianCodeUtil
 * JD-Core Version:    0.6.2
 */