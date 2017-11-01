/*     */ package ssb.warmline.business.commons.utils.keyword;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SensitivewordFilter
/*     */ {
/*  20 */   static final String separator = File.separator;
/*     */ 
/*  22 */   public Map sensitiveWordMap = null;
/*  23 */   public static int minMatchTYpe = 1;
/*  24 */   public static int maxMatchType = 2;
/*     */ 
/*     */   public SensitivewordFilter(String filePath)
/*     */   {
/*  30 */     this.sensitiveWordMap = new SensitiveWordInit().initKeyWord(filePath);
/*     */   }
/*     */ 
/*     */   public boolean isContaintSensitiveWord(String txt, int matchType)
/*     */   {
/*  46 */     boolean flag = false;
/*  47 */     for (int i = 0; i < txt.length(); i++) {
/*  48 */       int matchFlag = CheckSensitiveWord(txt, i, matchType);
/*  49 */       if (matchFlag > 0) {
/*  50 */         flag = true;
/*     */       }
/*     */     }
/*  53 */     return flag;
/*     */   }
/*     */ 
/*     */   public Set<String> getSensitiveWord(String txt, int matchType)
/*     */   {
/*  69 */     Set sensitiveWordList = new HashSet();
/*     */ 
/*  71 */     for (int i = 0; i < txt.length(); i++) {
/*  72 */       int length = CheckSensitiveWord(txt, i, matchType);
/*  73 */       if (length > 0) {
/*  74 */         sensitiveWordList.add(txt.substring(i, i + length));
/*  75 */         i = i + length - 1;
/*     */       }
/*     */     }
/*     */ 
/*  79 */     return sensitiveWordList;
/*     */   }
/*     */ 
/*     */   public String replaceSensitiveWord(String txt, int matchType, String replaceChar)
/*     */   {
/*  94 */     String resultTxt = txt;
/*  95 */     Set set = getSensitiveWord(txt, matchType);
/*  96 */     Iterator iterator = set.iterator();
/*  97 */     String word = null;
/*  98 */     String replaceString = null;
/*  99 */     while (iterator.hasNext()) {
/* 100 */       word = (String)iterator.next();
/* 101 */       replaceString = getReplaceChars(replaceChar, word.length());
/* 102 */       resultTxt = resultTxt.replaceAll(word, replaceString);
/*     */     }
/*     */ 
/* 105 */     return resultTxt;
/*     */   }
/*     */ 
/*     */   private String getReplaceChars(String replaceChar, int length)
/*     */   {
/* 119 */     StringBuffer resultReplace = new StringBuffer(replaceChar);
/* 120 */     for (int i = 1; i < length; i++) {
/* 121 */       resultReplace.append(replaceChar);
/*     */     }
/*     */ 
/* 124 */     return resultReplace.toString();
/*     */   }
/*     */ 
/*     */   public int CheckSensitiveWord(String txt, int beginIndex, int matchType)
/*     */   {
/* 140 */     boolean flag = false;
/* 141 */     int matchFlag = 0;
/* 142 */     char word = '\000';
/* 143 */     Map nowMap = this.sensitiveWordMap;
/* 144 */     for (int i = beginIndex; i < txt.length(); i++) {
/* 145 */       word = txt.charAt(i);
/* 146 */       nowMap = (Map)nowMap.get(Character.valueOf(word));
/* 147 */       if (nowMap == null) break;
/* 148 */       matchFlag++;
/* 149 */       if ("1".equals(nowMap.get("isEnd"))) {
/* 150 */         flag = true;
/* 151 */         if (minMatchTYpe == matchType)
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 159 */     if ((matchFlag < 2) || (!flag)) {
/* 160 */       matchFlag = 0;
/*     */     }
/* 162 */     return matchFlag;
/*     */   }
/*     */ 
/*     */   public static Set<String> verifyWord(String url, String word)
/*     */   {
/* 173 */     String filePath = url + separator + "keyword" + separator + "word.txt";
/* 174 */     SensitivewordFilter filter = new SensitivewordFilter(filePath);
/* 175 */     Set set = filter.getSensitiveWord(word, 2);
/* 176 */     return set;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 181 */     String filePath = "D:\\word.txt";
/* 182 */     File file = new File("/src/main/resources/keyword/word.txt");
/* 183 */     InputStream is = SensitivewordFilter.class.getResourceAsStream("/word.txt");
/*     */ 
/* 185 */     String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
/* 186 */     File af = new File(path + "/resource/keyword/word.txt");
/*     */ 
/* 188 */     SensitivewordFilter filter = new SensitivewordFilter(af.toString());
/* 189 */     System.out.println("敏感词的数量：" + filter.sensitiveWordMap.size());
/* 190 */     String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
/*     */ 
/* 193 */     System.out.println("待检测语句字数：" + string.length());
/* 194 */     long beginTime = System.currentTimeMillis();
/* 195 */     Set set = filter.getSensitiveWord(string, 2);
/* 196 */     long endTime = System.currentTimeMillis();
/* 197 */     System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
/* 198 */     System.out.println("总共消耗时间为：" + (endTime - beginTime));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.keyword.SensitivewordFilter
 * JD-Core Version:    0.6.2
 */