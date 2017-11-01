/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.beans.PropertyEditorSupport;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public class DateUtils extends PropertyEditorSupport
/*     */ {
/*  24 */   public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
/*  25 */     "yyyy-MM-dd");
/*     */ 
/*  27 */   public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
/*  28 */     "yyyyMMdd");
/*     */ 
/*  30 */   public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat(
/*  31 */     "yyyy年MM月dd日");
/*     */ 
/*  32 */   public static final SimpleDateFormat time_sdf = new SimpleDateFormat(
/*  33 */     "yyyy-MM-dd HH:mm");
/*     */ 
/*  34 */   public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat(
/*  35 */     "yyyyMMddHHmmss");
/*     */ 
/*  36 */   public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat(
/*  37 */     "HH:mm");
/*     */ 
/*  38 */   public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
/*  39 */     "yyyy-MM-dd HH:mm:ss");
/*     */   private static final long DAY_IN_MILLIS = 86400000L;
/*     */   private static final long HOUR_IN_MILLIS = 3600000L;
/*     */   private static final long MINUTE_IN_MILLIS = 60000L;
/*     */   private static final long SECOND_IN_MILLIS = 1000L;
/*     */ 
/*     */   private static SimpleDateFormat getSDFormat(String pattern)
/*     */   {
/*  47 */     return new SimpleDateFormat(pattern);
/*     */   }
/*     */ 
/*     */   public static Calendar getCalendar()
/*     */   {
/*  56 */     return Calendar.getInstance();
/*     */   }
/*     */ 
/*     */   public static Calendar getCalendar(long millis)
/*     */   {
/*  67 */     Calendar cal = Calendar.getInstance();
/*  68 */     cal.setTime(new Date(millis));
/*  69 */     return cal;
/*     */   }
/*     */ 
/*     */   public static Date getDate()
/*     */   {
/*  83 */     return new Date();
/*     */   }
/*     */ 
/*     */   public static Date getDate(long millis)
/*     */   {
/*  94 */     return new Date(millis);
/*     */   }
/*     */ 
/*     */   public static String timestamptoStr(Timestamp time)
/*     */   {
/* 104 */     Date date = null;
/* 105 */     if (time != null) {
/* 106 */       date = new Date(time.getTime());
/*     */     }
/* 108 */     return date2Str(date_sdf);
/*     */   }
/*     */ 
/*     */   public static Timestamp str2Timestamp(String str)
/*     */   {
/* 118 */     Date date = str2Date(str, date_sdf);
/* 119 */     return new Timestamp(date.getTime());
/*     */   }
/*     */ 
/*     */   public static Date str2Date(String str, SimpleDateFormat sdf)
/*     */   {
/* 128 */     if ((str == null) || ("".equals(str))) {
/* 129 */       return null;
/*     */     }
/* 131 */     Date date = null;
/*     */     try {
/* 133 */       return sdf.parse(str);
/*     */     }
/*     */     catch (ParseException e) {
/* 136 */       e.printStackTrace();
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   public static String date2Str(SimpleDateFormat date_sdf)
/*     */   {
/* 151 */     Date date = getDate();
/* 152 */     if (date == null) {
/* 153 */       return null;
/*     */     }
/* 155 */     return date_sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static String dateformat(String date, String format)
/*     */   {
/* 165 */     SimpleDateFormat sformat = new SimpleDateFormat(format);
/* 166 */     Date _date = null;
/*     */     try {
/* 168 */       _date = sformat.parse(date);
/*     */     }
/*     */     catch (ParseException e) {
/* 171 */       e.printStackTrace();
/*     */     }
/* 173 */     return sformat.format(_date);
/*     */   }
/*     */ 
/*     */   public static String date2Str(Date date, SimpleDateFormat date_sdf)
/*     */   {
/* 185 */     if (date == null) {
/* 186 */       return null;
/*     */     }
/* 188 */     return date_sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static String getDate(String format)
/*     */   {
/* 200 */     Date date = new Date();
/* 201 */     if (date == null) {
/* 202 */       return null;
/*     */     }
/* 204 */     SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 205 */     return sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static Timestamp getTimestamp(long millis)
/*     */   {
/* 216 */     return new Timestamp(millis);
/*     */   }
/*     */ 
/*     */   public static Timestamp getTimestamp(String time)
/*     */   {
/* 227 */     return new Timestamp(Long.parseLong(time));
/*     */   }
/*     */ 
/*     */   public static Timestamp getTimestamp()
/*     */   {
/* 236 */     return new Timestamp(new Date().getTime());
/*     */   }
/*     */ 
/*     */   public static Timestamp getTimestamp(Date date)
/*     */   {
/* 247 */     return new Timestamp(date.getTime());
/*     */   }
/*     */ 
/*     */   public static Timestamp getCalendarTimestamp(Calendar cal)
/*     */   {
/* 258 */     return new Timestamp(cal.getTime().getTime());
/*     */   }
/*     */ 
/*     */   public static Timestamp gettimestamp() {
/* 262 */     Date dt = new Date();
/* 263 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 264 */     String nowTime = df.format(dt);
/* 265 */     Timestamp buydate = Timestamp.valueOf(nowTime);
/* 266 */     return buydate;
/*     */   }
/*     */ 
/*     */   public static long getMillis()
/*     */   {
/* 280 */     return new Date().getTime();
/*     */   }
/*     */ 
/*     */   public static long getMillis(Calendar cal)
/*     */   {
/* 291 */     return cal.getTime().getTime();
/*     */   }
/*     */ 
/*     */   public static long getMillis(Date date)
/*     */   {
/* 302 */     return date.getTime();
/*     */   }
/*     */ 
/*     */   public static long getMillis(Timestamp ts)
/*     */   {
/* 313 */     return ts.getTime();
/*     */   }
/*     */ 
/*     */   public static String formatDate()
/*     */   {
/* 327 */     return date_sdf.format(getCalendar().getTime());
/*     */   }
/*     */ 
/*     */   public static String getDataString(SimpleDateFormat formatstr)
/*     */   {
/* 333 */     return formatstr.format(getCalendar().getTime());
/*     */   }
/*     */ 
/*     */   public static String formatDate(Calendar cal)
/*     */   {
/* 343 */     return date_sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String formatDate(Date date)
/*     */   {
/* 354 */     return date_sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static String formatDate(long millis)
/*     */   {
/* 365 */     return date_sdf.format(new Date(millis));
/*     */   }
/*     */ 
/*     */   public static String formatDate(String pattern)
/*     */   {
/* 376 */     return getSDFormat(pattern).format(getCalendar().getTime());
/*     */   }
/*     */ 
/*     */   public static String formatDate(Calendar cal, String pattern)
/*     */   {
/* 389 */     return getSDFormat(pattern).format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String formatDate(Date date, String pattern)
/*     */   {
/* 402 */     return getSDFormat(pattern).format(date);
/*     */   }
/*     */ 
/*     */   public static String formatTime()
/*     */   {
/* 416 */     return time_sdf.format(getCalendar().getTime());
/*     */   }
/*     */ 
/*     */   public static String formatTime(long millis)
/*     */   {
/* 427 */     return time_sdf.format(new Date(millis));
/*     */   }
/*     */ 
/*     */   public static String formatTime(Calendar cal)
/*     */   {
/* 438 */     return time_sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String formatTime(Date date)
/*     */   {
/* 449 */     return time_sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static String formatShortTime()
/*     */   {
/* 463 */     return short_time_sdf.format(getCalendar().getTime());
/*     */   }
/*     */ 
/*     */   public static String formatShortTime(long millis)
/*     */   {
/* 474 */     return short_time_sdf.format(new Date(millis));
/*     */   }
/*     */ 
/*     */   public static String formatShortTime(Calendar cal)
/*     */   {
/* 485 */     return short_time_sdf.format(cal.getTime());
/*     */   }
/*     */ 
/*     */   public static String formatShortTime(Date date)
/*     */   {
/* 496 */     return short_time_sdf.format(date);
/*     */   }
/*     */ 
/*     */   public static Date parseDate(String src, String pattern)
/*     */     throws ParseException
/*     */   {
/* 519 */     return getSDFormat(pattern).parse(src);
/*     */   }
/*     */ 
/*     */   public static Calendar parseCalendar(String src, String pattern)
/*     */     throws ParseException
/*     */   {
/* 537 */     Date date = parseDate(src, pattern);
/* 538 */     Calendar cal = Calendar.getInstance();
/* 539 */     cal.setTime(date);
/* 540 */     return cal;
/*     */   }
/*     */ 
/*     */   public static String formatAddDate(String src, String pattern, int amount)
/*     */     throws ParseException
/*     */   {
/* 546 */     Calendar cal = parseCalendar(src, pattern);
/* 547 */     cal.add(5, amount);
/* 548 */     return formatDate(cal);
/*     */   }
/*     */ 
/*     */   public static Timestamp parseTimestamp(String src, String pattern)
/*     */     throws ParseException
/*     */   {
/* 564 */     Date date = parseDate(src, pattern);
/* 565 */     return new Timestamp(date.getTime());
/*     */   }
/*     */ 
/*     */   public static int dateDiff(char flag, Calendar calSrc, Calendar calDes)
/*     */   {
/* 586 */     long millisDiff = getMillis(calSrc) - getMillis(calDes);
/*     */ 
/* 588 */     if (flag == 'y') {
/* 589 */       return calSrc.get(1) - calDes.get(1);
/*     */     }
/*     */ 
/* 592 */     if (flag == 'd') {
/* 593 */       return (int)(millisDiff / 86400000L);
/*     */     }
/*     */ 
/* 596 */     if (flag == 'h') {
/* 597 */       return (int)(millisDiff / 3600000L);
/*     */     }
/*     */ 
/* 600 */     if (flag == 'm') {
/* 601 */       return (int)(millisDiff / 60000L);
/*     */     }
/*     */ 
/* 604 */     if (flag == 's') {
/* 605 */       return (int)(millisDiff / 1000L);
/*     */     }
/*     */ 
/* 608 */     return 0;
/*     */   }
/*     */ 
/*     */   public void setAsText(String text)
/*     */     throws IllegalArgumentException
/*     */   {
/* 618 */     if (StringUtils.hasText(text))
/*     */       try {
/* 620 */         if ((text.indexOf(":") == -1) && (text.length() == 10)) {
/* 621 */           setValue(date_sdf.parse(text));
/* 622 */           return; } if ((text.indexOf(":") > 0) && (text.length() == 19)) {
/* 623 */           setValue(datetimeFormat.parse(text));
/* 624 */           return;
/* 625 */         }throw new IllegalArgumentException(
/* 626 */           "Could not parse date, date format is error ");
/*     */       }
/*     */       catch (ParseException ex) {
/* 629 */         IllegalArgumentException iae = new IllegalArgumentException(
/* 630 */           "Could not parse date: " + ex.getMessage());
/* 631 */         iae.initCause(ex);
/* 632 */         throw iae;
/*     */       }
/*     */     else
/* 635 */       setValue(null);
/*     */   }
/*     */ 
/*     */   public static int getYear() {
/* 639 */     GregorianCalendar calendar = new GregorianCalendar();
/* 640 */     calendar.setTime(getDate());
/* 641 */     return calendar.get(1);
/*     */   }
/*     */ 
/*     */   public static Date getDates(String startDate) throws ParseException {
/* 645 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 646 */     Date startTime = sdf.parse(startDate);
/* 647 */     return startTime;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.DateUtils
 * JD-Core Version:    0.6.2
 */