/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public final class DateUtil
/*     */ {
/*  17 */   static String PATTERN = "yyyy-MM-dd";
/*     */ 
/*  19 */   private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
/*     */ 
/*     */   public static final String format(Object date)
/*     */   {
/*  29 */     return format(date, PATTERN);
/*     */   }
/*     */ 
/*     */   public static final String format(Object date, String pattern)
/*     */   {
/*  40 */     if (date == null) {
/*  41 */       return null;
/*     */     }
/*  43 */     if (pattern == null) {
/*  44 */       return format(date);
/*     */     }
/*  46 */     return new SimpleDateFormat(pattern).format(date);
/*     */   }
/*     */ 
/*     */   public static final String getDate()
/*     */   {
/*  55 */     return format(new Date());
/*     */   }
/*     */ 
/*     */   public static final String getDateTime()
/*     */   {
/*  64 */     return format(new Date(), "yyyy-MM-dd HH:mm:ss");
/*     */   }
/*     */ 
/*     */   public static final String getDateTime(String pattern)
/*     */   {
/*  74 */     return format(new Date(), pattern);
/*     */   }
/*     */ 
/*     */   public static final Date addDate(Date date, int field, int amount)
/*     */   {
/*  86 */     if (date == null) {
/*  87 */       return null;
/*     */     }
/*  89 */     Calendar calendar = Calendar.getInstance();
/*  90 */     calendar.setTime(date);
/*  91 */     calendar.add(field, amount);
/*  92 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static final Date stringToDate(String date)
/*     */   {
/* 102 */     if (date == null) {
/* 103 */       return null;
/*     */     }
/* 105 */     String separator = String.valueOf(date.charAt(4));
/* 106 */     String pattern = "yyyyMMdd";
/* 107 */     if (!separator.matches("\\d*")) {
/* 108 */       pattern = "yyyy" + separator + "MM" + separator + "dd";
/* 109 */       if (date.length() < 10)
/* 110 */         pattern = "yyyy" + separator + "M" + separator + "d";
/*     */     }
/* 112 */     else if (date.length() < 8) {
/* 113 */       pattern = "yyyyMd";
/*     */     }
/* 115 */     pattern = pattern + " HH:mm:ss.SSS";
/* 116 */     pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
/*     */     try {
/* 118 */       return new SimpleDateFormat(pattern).parse(date); } catch (ParseException e) {
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public static final Integer getDayBetween(Date startDate, Date endDate)
/*     */   {
/* 132 */     Calendar start = Calendar.getInstance();
/* 133 */     start.setTime(startDate);
/* 134 */     start.set(11, 0);
/* 135 */     start.set(12, 0);
/* 136 */     start.set(13, 0);
/* 137 */     start.set(14, 0);
/* 138 */     Calendar end = Calendar.getInstance();
/* 139 */     end.setTime(endDate);
/* 140 */     end.set(11, 0);
/* 141 */     end.set(12, 0);
/* 142 */     end.set(13, 0);
/* 143 */     end.set(14, 0);
/*     */ 
/* 145 */     long n = end.getTimeInMillis() - start.getTimeInMillis();
/* 146 */     return Integer.valueOf((int)(n / 86400000L));
/*     */   }
/*     */ 
/*     */   public static final Integer getMonthBetween(Date startDate, Date endDate)
/*     */   {
/* 157 */     if ((startDate == null) || (endDate == null) || (!startDate.before(endDate))) {
/* 158 */       return null;
/*     */     }
/* 160 */     Calendar start = Calendar.getInstance();
/* 161 */     start.setTime(startDate);
/* 162 */     Calendar end = Calendar.getInstance();
/* 163 */     end.setTime(endDate);
/* 164 */     int year1 = start.get(1);
/* 165 */     int year2 = end.get(1);
/* 166 */     int month1 = start.get(2);
/* 167 */     int month2 = end.get(2);
/* 168 */     int n = (year2 - year1) * 12;
/* 169 */     n = n + month2 - month1;
/* 170 */     return Integer.valueOf(n);
/*     */   }
/*     */ 
/*     */   public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate)
/*     */   {
/* 181 */     if ((startDate == null) || (endDate == null) || (!startDate.before(endDate))) {
/* 182 */       return null;
/*     */     }
/* 184 */     Calendar start = Calendar.getInstance();
/* 185 */     start.setTime(startDate);
/* 186 */     Calendar end = Calendar.getInstance();
/* 187 */     end.setTime(endDate);
/* 188 */     int year1 = start.get(1);
/* 189 */     int year2 = end.get(1);
/* 190 */     int month1 = start.get(2);
/* 191 */     int month2 = end.get(2);
/* 192 */     int n = (year2 - year1) * 12;
/* 193 */     n = n + month2 - month1;
/* 194 */     int day1 = start.get(5);
/* 195 */     int day2 = end.get(5);
/* 196 */     if (day1 <= day2) {
/* 197 */       n++;
/*     */     }
/* 199 */     return Integer.valueOf(n);
/*     */   }
/*     */ 
/*     */   public static String getSpecifiedDayAfter(Date specifiedDay)
/*     */   {
/* 207 */     Calendar c = Calendar.getInstance();
/* 208 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 209 */     Date date = null;
/*     */     try {
/* 211 */       String strDate = sdf.format(specifiedDay);
/* 212 */       date = sdf.parse(strDate);
/*     */     } catch (ParseException e) {
/* 214 */       e.printStackTrace();
/*     */     }
/* 216 */     c.setTime(date);
/* 217 */     int day = c.get(5);
/* 218 */     c.set(5, day + 1);
/* 219 */     String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
/* 220 */     return dayAfter;
/*     */   }
/*     */ 
/*     */   public static String getSpecifiedYearAfter(String specifiedDay)
/*     */   {
/* 228 */     Calendar c = Calendar.getInstance();
/* 229 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 230 */     Date date = null;
/*     */     try {
/* 232 */       date = sdf.parse(specifiedDay);
/*     */     } catch (ParseException e) {
/* 234 */       e.printStackTrace();
/*     */     }
/* 236 */     c.setTime(date);
/* 237 */     int year = c.get(1);
/* 238 */     c.set(1, year + 1);
/* 239 */     String yearAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
/* 240 */     return yearAfter;
/*     */   }
/*     */ 
/*     */   public static String getFormateDate(Date date) {
/* 244 */     return dateformat.format(date);
/*     */   }
/*     */ 
/*     */   public static String getFormateDate(Date date, String format) {
/* 248 */     return new SimpleDateFormat(format).format(date);
/*     */   }
/*     */ 
/*     */   public static String getDateString(Date date) {
/* 252 */     return new SimpleDateFormat("yyyy-MM-dd").format(date);
/*     */   }
/*     */ 
/*     */   public static String getChineseFormateDate(Date date) {
/* 256 */     return new SimpleDateFormat("yyyy年MM月dd日").format(date);
/*     */   }
/*     */ 
/*     */   public static Date getDefaultDate(String dateStr) {
/*     */     try {
/* 261 */       return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); } catch (Throwable e) {
/*     */     }
/* 263 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date parseSinaDate(String dateStr)
/*     */   {
/*     */     try {
/* 269 */       return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(dateStr); } catch (Throwable e) {
/*     */     }
/* 271 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getUnixDate(Date date)
/*     */   {
/* 281 */     return (int)(date.getTime() / 1000L);
/*     */   }
/*     */ 
/*     */   public static Date parseUnixDate(int time)
/*     */   {
/* 290 */     return new Date(1000L * time);
/*     */   }
/*     */ 
/*     */   public static Date parseUnixDate(String timeStr) {
/* 294 */     return parseUnixDate(Integer.parseInt(timeStr));
/*     */   }
/*     */ 
/*     */   public static boolean passMoreThan(Date date, long diff) {
/* 298 */     return new Date().getTime() - date.getTime() > diff;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.DateUtil
 * JD-Core Version:    0.6.2
 */