/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class IdcardValidator
/*     */ {
/*  28 */   protected String[][] codeAndCity = { { "11", "北京" }, { "12", "天津" }, { "13", "河北" }, { "14", "山西" }, { "15", "内蒙古" }, { "21", "辽宁" }, { "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" }, { "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" }, { "37", "山东" }, { "41", "河南" }, { "42", "湖北" }, { "43", "湖南" }, { "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" }, { "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" }, { "61", "陕西" }, { "62", "甘肃" }, { "63", "青海" }, { "64", "宁夏" }, { "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" }, { "91", "国外" } }; private String[] cityCode = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" }; private int[] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; private String[] verifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
/*     */ 
/*     */   public boolean isValidatedAllIdcard(String idcard)
/*     */   {
/*  58 */     if (idcard.length() == 15) {
/*  59 */       idcard = convertIdcarBy15bit(idcard);
/*     */     }
/*  61 */     return isValidate18Idcard(idcard);
/*     */   }
/*     */ 
/*     */   public boolean isValidate18Idcard(String idcard)
/*     */   {
/* 100 */     if (idcard.length() != 18) {
/* 101 */       return false;
/*     */     }
/*     */ 
/* 104 */     String idcard17 = idcard.substring(0, 17);
/*     */ 
/* 106 */     String idcard18Code = idcard.substring(17, 18);
/* 107 */     char[] c = null;
/* 108 */     String checkCode = "";
/*     */ 
/* 110 */     if (isDigital(idcard17))
/* 111 */       c = idcard17.toCharArray();
/*     */     else {
/* 113 */       return false;
/*     */     }
/*     */ 
/* 116 */     if (c != null) {
/* 117 */       int[] bit = new int[idcard17.length()];
/*     */ 
/* 119 */       bit = converCharToInt(c);
/*     */ 
/* 121 */       int sum17 = 0;
/*     */ 
/* 123 */       sum17 = getPowerSum(bit);
/*     */ 
/* 126 */       checkCode = getCheckCodeBySum(sum17);
/* 127 */       if (checkCode == null) {
/* 128 */         return false;
/*     */       }
/*     */ 
/* 131 */       if (!idcard18Code.equalsIgnoreCase(checkCode)) {
/* 132 */         return false;
/*     */       }
/*     */     }
/* 135 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isValidate15Idcard(String idcard)
/*     */   {
/* 146 */     if (idcard.length() != 15) {
/* 147 */       return false;
/*     */     }
/*     */ 
/* 151 */     if (isDigital(idcard)) {
/* 152 */       String provinceid = idcard.substring(0, 2);
/* 153 */       String birthday = idcard.substring(6, 12);
/* 154 */       int year = Integer.parseInt(idcard.substring(6, 8));
/* 155 */       int month = Integer.parseInt(idcard.substring(8, 10));
/* 156 */       int day = Integer.parseInt(idcard.substring(10, 12));
/*     */ 
/* 159 */       boolean flag = false;
/* 160 */       for (String id : this.cityCode) {
/* 161 */         if (id.equals(provinceid)) {
/* 162 */           flag = true;
/* 163 */           break;
/*     */         }
/*     */       }
/* 166 */       if (!flag) {
/* 167 */         return false;
/*     */       }
/*     */ 
/* 170 */       Date birthdate = null;
/*     */       try {
/* 172 */         birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
/*     */       } catch (ParseException e) {
/* 174 */         e.printStackTrace();
/*     */       }
/* 176 */       if ((birthdate == null) || (new Date().before(birthdate))) {
/* 177 */         return false;
/*     */       }
/*     */ 
/* 181 */       GregorianCalendar curDay = new GregorianCalendar();
/* 182 */       int curYear = curDay.get(1);
/* 183 */       int year2bit = Integer.parseInt(String.valueOf(curYear)
/* 184 */         .substring(2));
/*     */ 
/* 187 */       if ((year < 50) && (year > year2bit)) {
/* 188 */         return false;
/*     */       }
/*     */ 
/* 192 */       if ((month < 1) || (month > 12)) {
/* 193 */         return false;
/*     */       }
/*     */ 
/* 197 */       boolean mflag = false;
/* 198 */       curDay.setTime(birthdate);
/* 199 */       switch (month) {
/*     */       case 1:
/*     */       case 3:
/*     */       case 5:
/*     */       case 7:
/*     */       case 8:
/*     */       case 10:
/*     */       case 12:
/* 207 */         mflag = (day >= 1) && (day <= 31);
/* 208 */         break;
/*     */       case 2:
/* 210 */         if (curDay.isLeapYear(curDay.get(1)))
/* 211 */           mflag = (day >= 1) && (day <= 29);
/*     */         else {
/* 213 */           mflag = (day >= 1) && (day <= 28);
/*     */         }
/* 215 */         break;
/*     */       case 4:
/*     */       case 6:
/*     */       case 9:
/*     */       case 11:
/* 220 */         mflag = (day >= 1) && (day <= 30);
/*     */       }
/*     */ 
/* 223 */       if (!mflag)
/* 224 */         return false;
/*     */     }
/*     */     else {
/* 227 */       return false;
/*     */     }
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   public String convertIdcarBy15bit(String idcard)
/*     */   {
/* 239 */     String idcard17 = null;
/*     */ 
/* 241 */     if (idcard.length() != 15) {
/* 242 */       return null;
/*     */     }
/*     */ 
/* 245 */     if (isDigital(idcard))
/*     */     {
/* 247 */       String birthday = idcard.substring(6, 12);
/* 248 */       Date birthdate = null;
/*     */       try {
/* 250 */         birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
/*     */       } catch (ParseException e) {
/* 252 */         e.printStackTrace();
/*     */       }
/* 254 */       Calendar cday = Calendar.getInstance();
/* 255 */       cday.setTime(birthdate);
/* 256 */       String year = String.valueOf(cday.get(1));
/*     */ 
/* 258 */       idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);
/*     */ 
/* 260 */       char[] c = idcard17.toCharArray();
/* 261 */       String checkCode = "";
/*     */ 
/* 263 */       if (c != null) {
/* 264 */         int[] bit = new int[idcard17.length()];
/*     */ 
/* 267 */         bit = converCharToInt(c);
/* 268 */         int sum17 = 0;
/* 269 */         sum17 = getPowerSum(bit);
/*     */ 
/* 272 */         checkCode = getCheckCodeBySum(sum17);
/*     */ 
/* 274 */         if (checkCode == null) {
/* 275 */           return null;
/*     */         }
/*     */ 
/* 279 */         idcard17 = idcard17 + checkCode;
/*     */       }
/*     */     } else {
/* 282 */       return null;
/*     */     }
/* 284 */     return idcard17;
/*     */   }
/*     */ 
/*     */   public boolean isIdcard(String idcard)
/*     */   {
/* 294 */     return (idcard == null) || ("".equals(idcard)) ? false : Pattern.matches(
/* 295 */       "(^//d{15}$)|(//d{17}(?://d|x|X)$)", idcard);
/*     */   }
/*     */ 
/*     */   public boolean is15Idcard(String idcard)
/*     */   {
/* 305 */     return (idcard == null) || ("".equals(idcard)) ? false : Pattern.matches(
/* 306 */       "^[1-9]//d{7}((0//d)|(1[0-2]))(([0|1|2]//d)|3[0-1])//d{3}$", 
/* 307 */       idcard);
/*     */   }
/*     */ 
/*     */   public boolean is18Idcard(String idcard)
/*     */   {
/* 317 */     return 
/* 318 */       Pattern.matches(
/* 319 */       "^[1-9]//d{5}[1-9]//d{3}((0//d)|(1[0-2]))(([0|1|2]//d)|3[0-1])//d{3}([//d|x|X]{1})$", 
/* 320 */       idcard);
/*     */   }
/*     */ 
/*     */   public boolean isDigital(String str)
/*     */   {
/* 330 */     return (str == null) || ("".equals(str)) ? false : str.matches("^[0-9]*$");
/*     */   }
/*     */ 
/*     */   public int getPowerSum(int[] bit)
/*     */   {
/* 341 */     int sum = 0;
/*     */ 
/* 343 */     if (this.power.length != bit.length) {
/* 344 */       return sum;
/*     */     }
/*     */ 
/* 347 */     for (int i = 0; i < bit.length; i++) {
/* 348 */       for (int j = 0; j < this.power.length; j++) {
/* 349 */         if (i == j) {
/* 350 */           sum += bit[i] * this.power[j];
/*     */         }
/*     */       }
/*     */     }
/* 354 */     return sum;
/*     */   }
/*     */ 
/*     */   public String getCheckCodeBySum(int sum17)
/*     */   {
/* 365 */     String checkCode = null;
/* 366 */     switch (sum17 % 11) {
/*     */     case 10:
/* 368 */       checkCode = "2";
/* 369 */       break;
/*     */     case 9:
/* 371 */       checkCode = "3";
/* 372 */       break;
/*     */     case 8:
/* 374 */       checkCode = "4";
/* 375 */       break;
/*     */     case 7:
/* 377 */       checkCode = "5";
/* 378 */       break;
/*     */     case 6:
/* 380 */       checkCode = "6";
/* 381 */       break;
/*     */     case 5:
/* 383 */       checkCode = "7";
/* 384 */       break;
/*     */     case 4:
/* 386 */       checkCode = "8";
/* 387 */       break;
/*     */     case 3:
/* 389 */       checkCode = "9";
/* 390 */       break;
/*     */     case 2:
/* 392 */       checkCode = "x";
/* 393 */       break;
/*     */     case 1:
/* 395 */       checkCode = "0";
/* 396 */       break;
/*     */     case 0:
/* 398 */       checkCode = "1";
/*     */     }
/*     */ 
/* 401 */     return checkCode;
/*     */   }
/*     */ 
/*     */   public int[] converCharToInt(char[] c)
/*     */     throws NumberFormatException
/*     */   {
/* 412 */     int[] a = new int[c.length];
/* 413 */     int k = 0;
/* 414 */     for (char temp : c) {
/* 415 */       a[(k++)] = Integer.parseInt(String.valueOf(temp));
/*     */     }
/* 417 */     return a;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 421 */     String idcard15 = "";
/* 422 */     String idcard18 = "43062419891017701X";
/* 423 */     IdcardValidator iv = new IdcardValidator();
/* 424 */     boolean flag = false;
/* 425 */     flag = iv.isValidate18Idcard(idcard18);
/* 426 */     System.out.println(flag);
/*     */ 
/* 435 */     System.out.println(iv.isValidatedAllIdcard(idcard18));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.IdcardValidator
 * JD-Core Version:    0.6.2
 */