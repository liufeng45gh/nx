/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class IdcardUtils extends StringUtils
/*     */ {
/*     */   public static final int CHINA_ID_MIN_LENGTH = 15;
/*     */   public static final int CHINA_ID_MAX_LENGTH = 18;
/*  26 */   public static final String[] cityCode = { 
/*  27 */     "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", 
/*  28 */     "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", 
/*  29 */     "81", "82", "91" };
/*     */ 
/*  33 */   public static final int[] power = { 
/*  34 */     7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
/*     */ 
/*  38 */   public static final String[] verifyCode = { 
/*  39 */     "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
/*     */   public static final int MIN = 1930;
/*  43 */   public static Map<String, String> cityCodes = new HashMap();
/*     */ 
/*  45 */   public static Map<String, Integer> twFirstCode = new HashMap();
/*     */ 
/*  47 */   public static Map<String, Integer> hkFirstCode = new HashMap();
/*     */ 
/*  49 */   static { cityCodes.put("11", "北京");
/*  50 */     cityCodes.put("12", "天津");
/*  51 */     cityCodes.put("13", "河北");
/*  52 */     cityCodes.put("14", "山西");
/*  53 */     cityCodes.put("15", "内蒙古");
/*  54 */     cityCodes.put("21", "辽宁");
/*  55 */     cityCodes.put("22", "吉林");
/*  56 */     cityCodes.put("23", "黑龙江");
/*  57 */     cityCodes.put("31", "上海");
/*  58 */     cityCodes.put("32", "江苏");
/*  59 */     cityCodes.put("33", "浙江");
/*  60 */     cityCodes.put("34", "安徽");
/*  61 */     cityCodes.put("35", "福建");
/*  62 */     cityCodes.put("36", "江西");
/*  63 */     cityCodes.put("37", "山东");
/*  64 */     cityCodes.put("41", "河南");
/*  65 */     cityCodes.put("42", "湖北");
/*  66 */     cityCodes.put("43", "湖南");
/*  67 */     cityCodes.put("44", "广东");
/*  68 */     cityCodes.put("45", "广西");
/*  69 */     cityCodes.put("46", "海南");
/*  70 */     cityCodes.put("50", "重庆");
/*  71 */     cityCodes.put("51", "四川");
/*  72 */     cityCodes.put("52", "贵州");
/*  73 */     cityCodes.put("53", "云南");
/*  74 */     cityCodes.put("54", "西藏");
/*  75 */     cityCodes.put("61", "陕西");
/*  76 */     cityCodes.put("62", "甘肃");
/*  77 */     cityCodes.put("63", "青海");
/*  78 */     cityCodes.put("64", "宁夏");
/*  79 */     cityCodes.put("65", "新疆");
/*  80 */     cityCodes.put("71", "台湾");
/*  81 */     cityCodes.put("81", "香港");
/*  82 */     cityCodes.put("82", "澳门");
/*  83 */     cityCodes.put("91", "国外");
/*  84 */     twFirstCode.put("A", Integer.valueOf(10));
/*  85 */     twFirstCode.put("B", Integer.valueOf(11));
/*  86 */     twFirstCode.put("C", Integer.valueOf(12));
/*  87 */     twFirstCode.put("D", Integer.valueOf(13));
/*  88 */     twFirstCode.put("E", Integer.valueOf(14));
/*  89 */     twFirstCode.put("F", Integer.valueOf(15));
/*  90 */     twFirstCode.put("G", Integer.valueOf(16));
/*  91 */     twFirstCode.put("H", Integer.valueOf(17));
/*  92 */     twFirstCode.put("J", Integer.valueOf(18));
/*  93 */     twFirstCode.put("K", Integer.valueOf(19));
/*  94 */     twFirstCode.put("L", Integer.valueOf(20));
/*  95 */     twFirstCode.put("M", Integer.valueOf(21));
/*  96 */     twFirstCode.put("N", Integer.valueOf(22));
/*  97 */     twFirstCode.put("P", Integer.valueOf(23));
/*  98 */     twFirstCode.put("Q", Integer.valueOf(24));
/*  99 */     twFirstCode.put("R", Integer.valueOf(25));
/* 100 */     twFirstCode.put("S", Integer.valueOf(26));
/* 101 */     twFirstCode.put("T", Integer.valueOf(27));
/* 102 */     twFirstCode.put("U", Integer.valueOf(28));
/* 103 */     twFirstCode.put("V", Integer.valueOf(29));
/* 104 */     twFirstCode.put("X", Integer.valueOf(30));
/* 105 */     twFirstCode.put("Y", Integer.valueOf(31));
/* 106 */     twFirstCode.put("W", Integer.valueOf(32));
/* 107 */     twFirstCode.put("Z", Integer.valueOf(33));
/* 108 */     twFirstCode.put("I", Integer.valueOf(34));
/* 109 */     twFirstCode.put("O", Integer.valueOf(35));
/* 110 */     hkFirstCode.put("A", Integer.valueOf(1));
/* 111 */     hkFirstCode.put("B", Integer.valueOf(2));
/* 112 */     hkFirstCode.put("C", Integer.valueOf(3));
/* 113 */     hkFirstCode.put("R", Integer.valueOf(18));
/* 114 */     hkFirstCode.put("U", Integer.valueOf(21));
/* 115 */     hkFirstCode.put("Z", Integer.valueOf(26));
/* 116 */     hkFirstCode.put("X", Integer.valueOf(24));
/* 117 */     hkFirstCode.put("W", Integer.valueOf(23));
/* 118 */     hkFirstCode.put("O", Integer.valueOf(15));
/* 119 */     hkFirstCode.put("N", Integer.valueOf(14));
/*     */   }
/*     */ 
/*     */   public static String conver15CardTo18(String idCard)
/*     */   {
/* 130 */     String idCard18 = "";
/* 131 */     if (idCard.length() != 15) {
/* 132 */       return null;
/*     */     }
/* 134 */     if (isNum(idCard))
/*     */     {
/* 136 */       String birthday = idCard.substring(6, 12);
/* 137 */       Date birthDate = null;
/*     */       try {
/* 139 */         birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
/*     */       } catch (ParseException e) {
/* 141 */         e.printStackTrace();
/*     */       }
/* 143 */       Calendar cal = Calendar.getInstance();
/* 144 */       if (birthDate != null) {
/* 145 */         cal.setTime(birthDate);
/*     */       }
/* 147 */       String sYear = String.valueOf(cal.get(1));
/* 148 */       idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
/*     */ 
/* 150 */       char[] cArr = idCard18.toCharArray();
/* 151 */       if (cArr != null) {
/* 152 */         int[] iCard = converCharToInt(cArr);
/* 153 */         int iSum17 = getPowerSum(iCard);
/*     */ 
/* 155 */         String sVal = getCheckCode18(iSum17);
/* 156 */         if (sVal.length() > 0)
/* 157 */           idCard18 = idCard18 + sVal;
/*     */         else
/* 159 */           return null;
/*     */       }
/*     */     }
/*     */     else {
/* 163 */       return null;
/*     */     }
/* 165 */     return idCard18;
/*     */   }
/*     */ 
/*     */   public static boolean validateCard(String idCard)
/*     */   {
/* 172 */     String card = idCard.trim();
/* 173 */     if (validateIdCard18(card)) {
/* 174 */       return true;
/*     */     }
/* 176 */     if (validateIdCard15(card)) {
/* 177 */       return true;
/*     */     }
/* 179 */     String[] cardval = validateIdCard10(card);
/* 180 */     if ((cardval != null) && 
/* 181 */       (cardval[2].equals("true"))) {
/* 182 */       return true;
/*     */     }
/*     */ 
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean validateIdCard18(String idCard)
/*     */   {
/* 195 */     boolean bTrue = false;
/* 196 */     if (idCard.length() == 18)
/*     */     {
/* 198 */       String code17 = idCard.substring(0, 17);
/*     */ 
/* 200 */       String code18 = idCard.substring(17, 18);
/* 201 */       if (isNum(code17)) {
/* 202 */         char[] cArr = code17.toCharArray();
/* 203 */         if (cArr != null) {
/* 204 */           int[] iCard = converCharToInt(cArr);
/* 205 */           int iSum17 = getPowerSum(iCard);
/*     */ 
/* 207 */           String val = getCheckCode18(iSum17);
/* 208 */           if ((val.length() > 0) && 
/* 209 */             (val.equalsIgnoreCase(code18))) {
/* 210 */             bTrue = true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 216 */     return bTrue;
/*     */   }
/*     */ 
/*     */   public static boolean validateIdCard15(String idCard)
/*     */   {
/* 227 */     if (idCard.length() != 15) {
/* 228 */       return false;
/*     */     }
/* 230 */     if (isNum(idCard)) {
/* 231 */       String proCode = idCard.substring(0, 2);
/* 232 */       if (cityCodes.get(proCode) == null) {
/* 233 */         return false;
/*     */       }
/* 235 */       String birthCode = idCard.substring(6, 12);
/* 236 */       Date birthDate = null;
/*     */       try {
/* 238 */         birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
/*     */       } catch (ParseException e) {
/* 240 */         e.printStackTrace();
/*     */       }
/* 242 */       Calendar cal = Calendar.getInstance();
/* 243 */       if (birthDate != null) {
/* 244 */         cal.setTime(birthDate);
/*     */       }
/* 246 */       if (!valiDate(cal.get(1), Integer.valueOf(birthCode.substring(2, 4)).intValue(), 
/* 246 */         Integer.valueOf(birthCode.substring(4, 6)).intValue()))
/* 247 */         return false;
/*     */     }
/*     */     else {
/* 250 */       return false;
/*     */     }
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */   public static String[] validateIdCard10(String idCard)
/*     */   {
/* 266 */     String[] info = new String[3];
/* 267 */     String card = idCard.replaceAll("[\\(|\\)]", "");
/* 268 */     if ((card.length() != 8) && (card.length() != 9) && (idCard.length() != 10)) {
/* 269 */       return null;
/*     */     }
/* 271 */     if (idCard.matches("^[a-zA-Z][0-9]{9}$")) {
/* 272 */       info[0] = "台湾";
/* 273 */       LogUtil.info("11111");
/* 274 */       String char2 = idCard.substring(1, 2);
/* 275 */       if (char2.equals("1")) {
/* 276 */         info[1] = "M";
/* 277 */         LogUtil.info("MMMMMMM");
/* 278 */       } else if (char2.equals("2")) {
/* 279 */         info[1] = "F";
/* 280 */         LogUtil.info("FFFFFFF");
/*     */       } else {
/* 282 */         info[1] = "N";
/* 283 */         info[2] = "false";
/* 284 */         LogUtil.info("NNNN");
/* 285 */         return info;
/*     */       }
/* 287 */       info[2] = (validateTWCard(idCard) ? "true" : "false");
/* 288 */     } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) {
/* 289 */       info[0] = "澳门";
/* 290 */       info[1] = "N";
/*     */     }
/* 292 */     else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) {
/* 293 */       info[0] = "香港";
/* 294 */       info[1] = "N";
/* 295 */       info[2] = (validateHKCard(idCard) ? "true" : "false");
/*     */     } else {
/* 297 */       return null;
/*     */     }
/* 299 */     return info;
/*     */   }
/*     */ 
/*     */   public static boolean validateTWCard(String idCard)
/*     */   {
/* 310 */     String start = idCard.substring(0, 1);
/* 311 */     String mid = idCard.substring(1, 9);
/* 312 */     String end = idCard.substring(9, 10);
/* 313 */     Integer iStart = (Integer)twFirstCode.get(start);
/* 314 */     Integer sum = Integer.valueOf(iStart.intValue() / 10 + iStart.intValue() % 10 * 9);
/* 315 */     char[] chars = mid.toCharArray();
/* 316 */     Integer iflag = Integer.valueOf(8);
/* 317 */     for (char c : chars) {
/* 318 */       sum = Integer.valueOf(sum.intValue() + Integer.valueOf(c).intValue() * iflag.intValue());
/* 319 */       iflag = Integer.valueOf(iflag.intValue() - 1);
/*     */     }
/* 321 */     return (sum.intValue() % 10 == 0 ? 0 : 10 - sum.intValue() % 10) == Integer.valueOf(end).intValue();
/*     */   }
/*     */ 
/*     */   public static boolean validateHKCard(String idCard)
/*     */   {
/* 338 */     String card = idCard.replaceAll("[\\(|\\)]", "");
/* 339 */     Integer sum = Integer.valueOf(0);
/* 340 */     if (card.length() == 9) {
/* 341 */       sum = Integer.valueOf((Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]).intValue() - 55) * 9 + 
/* 342 */         (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]).intValue() - 55) * 8);
/* 343 */       card = card.substring(1, 9);
/*     */     } else {
/* 345 */       sum = Integer.valueOf(522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]).intValue() - 55) * 8);
/*     */     }
/* 347 */     String mid = card.substring(1, 7);
/* 348 */     String end = card.substring(7, 8);
/* 349 */     char[] chars = mid.toCharArray();
/* 350 */     Integer iflag = Integer.valueOf(7);
/* 351 */     for (char c : chars) {
/* 352 */       sum = Integer.valueOf(sum.intValue() + Integer.valueOf(c).intValue() * iflag.intValue());
/* 353 */       iflag = Integer.valueOf(iflag.intValue() - 1);
/*     */     }
/* 355 */     if (end.toUpperCase().equals("A"))
/* 356 */       sum = Integer.valueOf(sum.intValue() + 10);
/*     */     else {
/* 358 */       sum = Integer.valueOf(sum.intValue() + Integer.valueOf(end).intValue());
/*     */     }
/* 360 */     return sum.intValue() % 11 == 0;
/*     */   }
/*     */ 
/*     */   public static int[] converCharToInt(char[] ca)
/*     */   {
/* 371 */     int len = ca.length;
/* 372 */     int[] iArr = new int[len];
/*     */     try {
/* 374 */       for (int i = 0; i < len; i++)
/* 375 */         iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 378 */       e.printStackTrace();
/*     */     }
/* 380 */     return iArr;
/*     */   }
/*     */ 
/*     */   public static int getPowerSum(int[] iArr)
/*     */   {
/* 390 */     int iSum = 0;
/* 391 */     if (power.length == iArr.length) {
/* 392 */       for (int i = 0; i < iArr.length; i++) {
/* 393 */         for (int j = 0; j < power.length; j++) {
/* 394 */           if (i == j) {
/* 395 */             iSum += iArr[i] * power[j];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 400 */     return iSum;
/*     */   }
/*     */ 
/*     */   public static String getCheckCode18(int iSum)
/*     */   {
/* 410 */     String sCode = "";
/* 411 */     switch (iSum % 11) {
/*     */     case 10:
/* 413 */       sCode = "2";
/* 414 */       break;
/*     */     case 9:
/* 416 */       sCode = "3";
/* 417 */       break;
/*     */     case 8:
/* 419 */       sCode = "4";
/* 420 */       break;
/*     */     case 7:
/* 422 */       sCode = "5";
/* 423 */       break;
/*     */     case 6:
/* 425 */       sCode = "6";
/* 426 */       break;
/*     */     case 5:
/* 428 */       sCode = "7";
/* 429 */       break;
/*     */     case 4:
/* 431 */       sCode = "8";
/* 432 */       break;
/*     */     case 3:
/* 434 */       sCode = "9";
/* 435 */       break;
/*     */     case 2:
/* 437 */       sCode = "x";
/* 438 */       break;
/*     */     case 1:
/* 440 */       sCode = "0";
/* 441 */       break;
/*     */     case 0:
/* 443 */       sCode = "1";
/*     */     }
/*     */ 
/* 446 */     return sCode;
/*     */   }
/*     */ 
/*     */   public static int getAgeByIdCard(String idCard)
/*     */   {
/* 457 */     int iAge = 0;
/* 458 */     if (idCard.length() == 15) {
/* 459 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 461 */     String year = idCard.substring(6, 10);
/* 462 */     Calendar cal = Calendar.getInstance();
/* 463 */     int iCurrYear = cal.get(1);
/* 464 */     iAge = iCurrYear - Integer.valueOf(year).intValue();
/* 465 */     return iAge;
/*     */   }
/*     */ 
/*     */   public static String getBirthByIdCard(String idCard)
/*     */   {
/* 475 */     Integer len = Integer.valueOf(idCard.length());
/* 476 */     if (len.intValue() < 15)
/* 477 */       return null;
/* 478 */     if (len.intValue() == 15) {
/* 479 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 481 */     return idCard.substring(6, 14);
/*     */   }
/*     */ 
/*     */   public static Short getYearByIdCard(String idCard)
/*     */   {
/* 491 */     Integer len = Integer.valueOf(idCard.length());
/* 492 */     if (len.intValue() < 15)
/* 493 */       return null;
/* 494 */     if (len.intValue() == 15) {
/* 495 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 497 */     return Short.valueOf(idCard.substring(6, 10));
/*     */   }
/*     */ 
/*     */   public static Short getMonthByIdCard(String idCard)
/*     */   {
/* 508 */     Integer len = Integer.valueOf(idCard.length());
/* 509 */     if (len.intValue() < 15)
/* 510 */       return null;
/* 511 */     if (len.intValue() == 15) {
/* 512 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 514 */     return Short.valueOf(idCard.substring(10, 12));
/*     */   }
/*     */ 
/*     */   public static Short getDateByIdCard(String idCard)
/*     */   {
/* 525 */     Integer len = Integer.valueOf(idCard.length());
/* 526 */     if (len.intValue() < 15)
/* 527 */       return null;
/* 528 */     if (len.intValue() == 15) {
/* 529 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 531 */     return Short.valueOf(idCard.substring(12, 14));
/*     */   }
/*     */ 
/*     */   public static String getGenderByIdCard(String idCard)
/*     */   {
/* 541 */     String sGender = "N";
/* 542 */     if (idCard.length() == 15) {
/* 543 */       idCard = conver15CardTo18(idCard);
/*     */     }
/* 545 */     String sCardNum = idCard.substring(16, 17);
/* 546 */     if (Integer.parseInt(sCardNum) % 2 != 0)
/* 547 */       sGender = "M";
/*     */     else {
/* 549 */       sGender = "F";
/*     */     }
/* 551 */     return sGender;
/*     */   }
/*     */ 
/*     */   public static String getProvinceByIdCard(String idCard)
/*     */   {
/* 561 */     int len = idCard.length();
/* 562 */     String sProvince = null;
/* 563 */     String sProvinNum = "";
/* 564 */     if ((len == 15) || (len == 18)) {
/* 565 */       sProvinNum = idCard.substring(0, 2);
/*     */     }
/* 567 */     sProvince = (String)cityCodes.get(sProvinNum);
/* 568 */     return sProvince;
/*     */   }
/*     */ 
/*     */   public static boolean isNum(String val)
/*     */   {
/* 578 */     return (val == null) || ("".equals(val)) ? false : val.matches("^[0-9]*$");
/*     */   }
/*     */ 
/*     */   public static boolean valiDate(int iYear, int iMonth, int iDate)
/*     */   {
/* 593 */     Calendar cal = Calendar.getInstance();
/* 594 */     int year = cal.get(1);
/*     */ 
/* 596 */     if ((iYear < 1930) || (iYear >= year)) {
/* 597 */       return false;
/*     */     }
/* 599 */     if ((iMonth < 1) || (iMonth > 12))
/* 600 */       return false;
/*     */     int datePerMonth;
/*     */     //int datePerMonth;
/*     */     //int datePerMonth;
/* 602 */     switch (iMonth) {
/*     */     case 4:
/*     */     case 6:
/*     */     case 9:
/*     */     case 11:
/* 607 */       datePerMonth = 30;
/* 608 */       break;
/*     */     case 2:
/* 610 */       boolean dm = ((iYear % 4 == 0) && (iYear % 100 != 0)) || ((iYear % 400 == 0) && 
/* 611 */         (iYear > 1930) && (
/* 611 */         iYear < year));
/* 612 */       datePerMonth = dm ? 29 : 28;
/* 613 */       break;
/*     */     case 3:
/*     */     case 5:
/*     */     case 7:
/*     */     case 8:
/*     */     case 10:
/*     */     default:
/* 615 */       datePerMonth = 31;
/*     */     }
/* 617 */     return (iDate >= 1) && (iDate <= datePerMonth);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.IdcardUtils
 * JD-Core Version:    0.6.2
 */