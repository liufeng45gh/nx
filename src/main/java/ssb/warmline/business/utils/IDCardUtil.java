/*     */ package ssb.warmline.business.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Hashtable;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class IDCardUtil
/*     */ {
/*     */   public static boolean isHZ(String hzID)
/*     */   {
/*  22 */     Pattern p = 
/*  23 */       Pattern.compile("^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$");
/*  24 */     Matcher m = p.matcher(hzID);
/*  25 */     System.out.println("电话号码匹配" + m.matches());
/*  26 */     return m.matches();
/*     */   }
/*     */ 
/*     */   public static String IDCardValidate(String IDStr)
/*     */     throws ParseException
/*     */   {
/*  49 */     String errorInfo = "";
/*  50 */     String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", 
/*  51 */       "3", "2" };
/*  52 */     String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", 
/*  53 */       "9", "10", "5", "8", "4", "2" };
/*  54 */     String Ai = "";
/*     */ 
/*  56 */     if ((IDStr.length() != 15) && (IDStr.length() != 18)) {
/*  57 */       errorInfo = "身份证号码长度应该为15位或18位。";
/*  58 */       return errorInfo;
/*     */     }
/*     */ 
/*  63 */     if (IDStr.length() == 18)
/*  64 */       Ai = IDStr.substring(0, 17);
/*  65 */     else if (IDStr.length() == 15) {
/*  66 */       Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
/*     */     }
/*  68 */     if (!isNumeric(Ai)) {
/*  69 */       errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
/*  70 */       return errorInfo;
/*     */     }
/*     */ 
/*  75 */     String strYear = Ai.substring(6, 10);
/*  76 */     String strMonth = Ai.substring(10, 12);
/*  77 */     String strDay = Ai.substring(12, 14);
/*  78 */     if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
/*  79 */       errorInfo = "身份证生日无效。";
/*  80 */       return errorInfo;
/*     */     }
/*  82 */     GregorianCalendar gc = new GregorianCalendar();
/*  83 */     SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/*  85 */       if ((gc.get(1) - Integer.parseInt(strYear) > 150) || 
/*  87 */         (gc.getTime().getTime() - s.parse(
/*  87 */         strYear + "-" + strMonth + "-" + strDay).getTime() < 0L))
/*  88 */         return "身份证生日不在有效范围。";
/*     */     }
/*     */     catch (NumberFormatException e)
/*     */     {
/*  92 */       e.printStackTrace();
/*     */     } catch (ParseException e) {
/*  94 */       e.printStackTrace();
/*     */     }
/*  96 */     if ((Integer.parseInt(strMonth) > 12) || (Integer.parseInt(strMonth) == 0)) {
/*  97 */       errorInfo = "身份证月份无效";
/*  98 */       return errorInfo;
/*     */     }
/* 100 */     if ((Integer.parseInt(strDay) > 31) || (Integer.parseInt(strDay) == 0)) {
/* 101 */       errorInfo = "身份证日期无效";
/* 102 */       return errorInfo;
/*     */     }
/*     */ 
/* 107 */     Hashtable h = GetAreaCode();
/* 108 */     if (h.get(Ai.substring(0, 2)) == null) {
/* 109 */       errorInfo = "身份证地区编码错误。";
/* 110 */       return errorInfo;
/*     */     }
/*     */ 
/* 115 */     int TotalmulAiWi = 0;
/* 116 */     for (int i = 0; i < 17; i++)
/*     */     {
/* 118 */       TotalmulAiWi = TotalmulAiWi + 
/* 118 */         Integer.parseInt(String.valueOf(Ai.charAt(i))) * 
/* 119 */         Integer.parseInt(Wi[i]);
/*     */     }
/* 121 */     int modValue = TotalmulAiWi % 11;
/* 122 */     String strVerifyCode = ValCodeArr[modValue];
/* 123 */     Ai = Ai + strVerifyCode;
/*     */ 
/* 125 */     if (IDStr.length() == 18) {
/* 126 */       if (!Ai.equals(IDStr)) {
/* 127 */         errorInfo = "身份证无效，不是合法的身份证号码";
/* 128 */         return errorInfo;
/*     */       }
/*     */     }
/* 131 */     else return "";
/*     */ 
/* 134 */     return "";
/*     */   }
/*     */ 
/*     */   private static Hashtable GetAreaCode()
/*     */   {
/* 144 */     Hashtable hashtable = new Hashtable();
/* 145 */     hashtable.put("11", "北京");
/* 146 */     hashtable.put("12", "天津");
/* 147 */     hashtable.put("13", "河北");
/* 148 */     hashtable.put("14", "山西");
/* 149 */     hashtable.put("15", "内蒙古");
/* 150 */     hashtable.put("21", "辽宁");
/* 151 */     hashtable.put("22", "吉林");
/* 152 */     hashtable.put("23", "黑龙江");
/* 153 */     hashtable.put("31", "上海");
/* 154 */     hashtable.put("32", "江苏");
/* 155 */     hashtable.put("33", "浙江");
/* 156 */     hashtable.put("34", "安徽");
/* 157 */     hashtable.put("35", "福建");
/* 158 */     hashtable.put("36", "江西");
/* 159 */     hashtable.put("37", "山东");
/* 160 */     hashtable.put("41", "河南");
/* 161 */     hashtable.put("42", "湖北");
/* 162 */     hashtable.put("43", "湖南");
/* 163 */     hashtable.put("44", "广东");
/* 164 */     hashtable.put("45", "广西");
/* 165 */     hashtable.put("46", "海南");
/* 166 */     hashtable.put("50", "重庆");
/* 167 */     hashtable.put("51", "四川");
/* 168 */     hashtable.put("52", "贵州");
/* 169 */     hashtable.put("53", "云南");
/* 170 */     hashtable.put("54", "西藏");
/* 171 */     hashtable.put("61", "陕西");
/* 172 */     hashtable.put("62", "甘肃");
/* 173 */     hashtable.put("63", "青海");
/* 174 */     hashtable.put("64", "宁夏");
/* 175 */     hashtable.put("65", "新疆");
/* 176 */     hashtable.put("71", "台湾");
/* 177 */     hashtable.put("81", "香港");
/* 178 */     hashtable.put("82", "澳门");
/* 179 */     hashtable.put("91", "国外");
/* 180 */     return hashtable;
/*     */   }
/*     */ 
/*     */   private static boolean isNumeric(String str)
/*     */   {
/* 190 */     Pattern pattern = Pattern.compile("[0-9]*");
/* 191 */     Matcher isNum = pattern.matcher(str);
/* 192 */     if (isNum.matches()) {
/* 193 */       return true;
/*     */     }
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isDate(String strDate)
/*     */   {
/* 206 */     Pattern pattern = 
/* 207 */       Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
/* 208 */     Matcher m = pattern.matcher(strDate);
/* 209 */     if (m.matches()) {
/* 210 */       return true;
/*     */     }
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws ParseException
/*     */   {
/* 224 */     String IDCardNum = "11010219990101697x";
/* 225 */     IDCardUtil cc = new IDCardUtil();
/*     */ 
/* 227 */     System.out.println(IDCardValidate(IDCardNum));
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.utils.IDCardUtil
 * JD-Core Version:    0.6.2
 */