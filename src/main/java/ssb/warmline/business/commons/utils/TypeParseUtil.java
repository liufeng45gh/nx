/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Locale;
/*     */ import ssb.warmline.business.commons.support.exception.DataParseException;
/*     */ 
/*     */ public class TypeParseUtil
/*     */ {
/*     */   static final String message = "Could not convert %1$s to %2$s";
/*     */   static final String support = "Convert from %1$s to %2$s not currently supported";
/*     */ 
/*     */   public static Object convert(Object value, Class<?> type, String format)
/*     */   {
/*  36 */     return convert(value, type.getName(), format);
/*     */   }
/*     */ 
/*     */   public static Object convert(Object value, String type, String format)
/*     */   {
/*  52 */     Locale locale = Locale.getDefault();
/*  53 */     if (value == null)
/*  54 */       return null;
/*  55 */     if ((value.getClass().getName().equalsIgnoreCase(type)) || 
/*  56 */       (value.getClass().getSimpleName().equalsIgnoreCase(type)))
/*  57 */       return value;
/*  58 */     if (("Object".equalsIgnoreCase(type)) || ("java.lang.Object".equals(type)))
/*  59 */       return value;
/*  60 */     if ((value instanceof String))
/*  61 */       return string2Object(value, type, format, locale);
/*  62 */     if ((value instanceof BigDecimal))
/*  63 */       return decimal2Obj(value, type, locale);
/*  64 */     if ((value instanceof Double))
/*  65 */       return double2Obj(value, type, locale);
/*  66 */     if ((value instanceof Float))
/*  67 */       return float2Obj(value, type, locale);
/*  68 */     if ((value instanceof Long))
/*  69 */       return long2Obj(value, type, locale);
/*  70 */     if ((value instanceof Integer))
/*  71 */       return intr2Obj(value, type, locale);
/*  72 */     if ((value instanceof java.util.Date))
/*  73 */       return date2Obj(value, type, format);
/*  74 */     if ((value instanceof java.sql.Date))
/*  75 */       return sqlDate2Obj(value, type, format);
/*  76 */     if ((value instanceof Timestamp))
/*  77 */       return time2Obj(value, type, format);
/*  78 */     if ((value instanceof Boolean))
/*  79 */       return bool2Obj(value, type);
/*  80 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type))) {
/*  81 */       return value.toString();
/*     */     }
/*  83 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { value.getClass().getName(), type }));
/*     */   }
/*     */ 
/*     */   private static Object bool2Obj(Object value, String type)
/*     */   {
/*  89 */     String fromType = "Boolean";
/*  90 */     Boolean bol = (Boolean)value;
/*  91 */     if (("Boolean".equalsIgnoreCase(type)) || ("java.lang.Boolean".equalsIgnoreCase(type)))
/*  92 */       return bol;
/*  93 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/*  94 */       return bol.toString();
/*  95 */     if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type))) {
/*  96 */       if (bol.booleanValue()) {
/*  97 */         return new Integer(1);
/*     */       }
/*  99 */       return new Integer(0);
/*     */     }
/*     */ 
/* 102 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object time2Obj(Object value, String type, String format)
/*     */   {
/* 108 */     String fromType = "Timestamp";
/* 109 */     Timestamp tme = (Timestamp)value;
/* 110 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type))) {
/* 111 */       if ((format == null) || (format.length() == 0)) {
/* 112 */         return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tme).toString();
/*     */       }
/* 114 */       SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 115 */       return sdf.format(new java.util.Date(tme.getTime()));
/*     */     }
/* 117 */     if (("Date".equalsIgnoreCase(type)) || ("java.util.Date".equalsIgnoreCase(type)))
/* 118 */       return new java.util.Date(tme.getTime());
/* 119 */     if ("java.sql.Date".equalsIgnoreCase(type))
/* 120 */       return new java.sql.Date(tme.getTime());
/* 121 */     if (("Time".equalsIgnoreCase(type)) || ("java.sql.Time".equalsIgnoreCase(type)))
/* 122 */       return new Time(tme.getTime());
/* 123 */     if (("Timestamp".equalsIgnoreCase(type)) || ("java.sql.Timestamp".equalsIgnoreCase(type))) {
/* 124 */       return value;
/*     */     }
/* 126 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object sqlDate2Obj(Object value, String type, String format)
/*     */   {
/* 132 */     String fromType = "Date";
/* 133 */     java.sql.Date dte = (java.sql.Date)value;
/* 134 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type))) {
/* 135 */       if ((format == null) || (format.length() == 0)) {
/* 136 */         return dte.toString();
/*     */       }
/* 138 */       SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 139 */       return sdf.format(new java.util.Date(dte.getTime()));
/*     */     }
/* 141 */     if (("Date".equalsIgnoreCase(type)) || ("java.util.Date".equalsIgnoreCase(type)))
/* 142 */       return new java.util.Date(dte.getTime());
/* 143 */     if ("java.sql.Date".equalsIgnoreCase(type))
/* 144 */       return value;
/* 145 */     if (("Time".equalsIgnoreCase(type)) || ("java.sql.Time".equalsIgnoreCase(type)))
/* 146 */       throw new DataParseException("Conversion from " + fromType + " to " + type + " not currently supported");
/* 147 */     if (("Timestamp".equalsIgnoreCase(type)) || ("java.sql.Timestamp".equalsIgnoreCase(type))) {
/* 148 */       return new Timestamp(dte.getTime());
/*     */     }
/* 150 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object date2Obj(Object value, String type, String format)
/*     */   {
/* 156 */     String fromType = "Date";
/* 157 */     java.util.Date dte = (java.util.Date)value;
/* 158 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type))) {
/* 159 */       if ((format == null) || (format.length() == 0)) {
/* 160 */         return dte.toString();
/*     */       }
/* 162 */       SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 163 */       return sdf.format(dte);
/*     */     }
/* 165 */     if (("Date".equalsIgnoreCase(type)) || ("java.util.Date".equalsIgnoreCase(type)))
/* 166 */       return value;
/* 167 */     if ("java.sql.Date".equalsIgnoreCase(type))
/* 168 */       return new java.sql.Date(dte.getTime());
/* 169 */     if (("Time".equalsIgnoreCase(type)) || ("java.sql.Time".equalsIgnoreCase(type)))
/* 170 */       return new Time(dte.getTime());
/* 171 */     if (("Timestamp".equalsIgnoreCase(type)) || ("java.sql.Timestamp".equalsIgnoreCase(type))) {
/* 172 */       return new Timestamp(dte.getTime());
/*     */     }
/* 174 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object intr2Obj(Object value, String type, Locale locale)
/*     */   {
/* 180 */     String fromType = "Integer";
/* 181 */     Integer intgr = (Integer)value;
/* 182 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 183 */       return getNf(locale).format(intgr.longValue());
/* 184 */     if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type)))
/* 185 */       return new Double(intgr.doubleValue());
/* 186 */     if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type)))
/* 187 */       return new Float(intgr.floatValue());
/* 188 */     if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type))) {
/* 189 */       String str = intgr.toString();
/* 190 */       BigDecimal retBig = new BigDecimal(intgr.doubleValue());
/* 191 */       int iscale = str.indexOf(".");
/* 192 */       int keylen = str.length();
/* 193 */       if (iscale > -1) {
/* 194 */         iscale = keylen - (iscale + 1);
/* 195 */         return retBig.setScale(iscale, 5);
/*     */       }
/* 197 */       return retBig.setScale(0, 5);
/*     */     }
/* 199 */     if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type)))
/* 200 */       return new Long(intgr.longValue());
/* 201 */     if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type))) {
/* 202 */       return value;
/*     */     }
/* 204 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object long2Obj(Object value, String type, Locale locale)
/*     */   {
/* 210 */     String fromType = "Long";
/* 211 */     Long lng = (Long)value;
/* 212 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 213 */       return getNf(locale).format(lng.longValue());
/* 214 */     if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type)))
/* 215 */       return new Double(lng.doubleValue());
/* 216 */     if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type)))
/* 217 */       return new Float(lng.floatValue());
/* 218 */     if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type)))
/* 219 */       return new BigDecimal(lng.toString());
/* 220 */     if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type)))
/* 221 */       return value;
/* 222 */     if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type)))
/* 223 */       return new Integer(lng.intValue());
/* 224 */     if (("Date".equalsIgnoreCase(type)) || ("java.util.Date".equalsIgnoreCase(type)))
/* 225 */       return new java.util.Date(lng.longValue());
/* 226 */     if ("java.sql.Date".equalsIgnoreCase(type))
/* 227 */       return new java.sql.Date(lng.longValue());
/* 228 */     if (("Time".equalsIgnoreCase(type)) || ("java.sql.Time".equalsIgnoreCase(type)))
/* 229 */       return new Time(lng.longValue());
/* 230 */     if (("Timestamp".equalsIgnoreCase(type)) || ("java.sql.Timestamp".equalsIgnoreCase(type))) {
/* 231 */       return new Timestamp(lng.longValue());
/*     */     }
/* 233 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object float2Obj(Object value, String type, Locale locale)
/*     */   {
/* 238 */     String fromType = "Float";
/* 239 */     Float flt = (Float)value;
/* 240 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 241 */       return getNf(locale).format(flt.doubleValue());
/* 242 */     if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type)))
/* 243 */       return new BigDecimal(flt.doubleValue());
/* 244 */     if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type)))
/* 245 */       return new Double(flt.doubleValue());
/* 246 */     if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type)))
/* 247 */       return value;
/* 248 */     if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type)))
/* 249 */       return new Long(Math.round(flt.doubleValue()));
/* 250 */     if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type))) {
/* 251 */       return new Integer((int)Math.round(flt.doubleValue()));
/*     */     }
/* 253 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object double2Obj(Object value, String type, Locale locale)
/*     */   {
/* 259 */     String fromType = "Double";
/* 260 */     Double dbl = (Double)value;
/* 261 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 262 */       return getNf(locale).format(dbl.doubleValue());
/* 263 */     if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type)))
/* 264 */       return value;
/* 265 */     if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type)))
/* 266 */       return new Float(dbl.floatValue());
/* 267 */     if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type)))
/* 268 */       return new Long(Math.round(dbl.doubleValue()));
/* 269 */     if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type)))
/* 270 */       return new Integer((int)Math.round(dbl.doubleValue()));
/* 271 */     if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type))) {
/* 272 */       return new BigDecimal(dbl.toString());
/*     */     }
/* 274 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object decimal2Obj(Object value, String type, Locale locale)
/*     */   {
/* 280 */     String fromType = "BigDecimal";
/* 281 */     BigDecimal bigD = (BigDecimal)value;
/* 282 */     if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 283 */       return getNf(locale).format(bigD.doubleValue());
/* 284 */     if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type)))
/* 285 */       return value;
/* 286 */     if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type)))
/* 287 */       return new Double(bigD.doubleValue());
/* 288 */     if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type)))
/* 289 */       return new Float(bigD.floatValue());
/* 290 */     if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type)))
/* 291 */       return new Long(Math.round(bigD.doubleValue()));
/* 292 */     if (("Integer".equals(type)) || ("java.lang.Integer".equalsIgnoreCase(type))) {
/* 293 */       return new Integer((int)Math.round(bigD.doubleValue()));
/*     */     }
/* 295 */     throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */   }
/*     */ 
/*     */   private static Object string2Object(Object value, String type, String format, Locale locale)
/*     */   {
/* 301 */     String fromType = "String";
/* 302 */     String str = (String)value;
/*     */     try {
/* 304 */       if (("String".equalsIgnoreCase(type)) || ("java.lang.String".equalsIgnoreCase(type)))
/* 305 */         return value;
/* 306 */       if (str.length() == 0)
/* 307 */         return null;
/* 308 */       if (("Boolean".equalsIgnoreCase(type)) || ("java.lang.Boolean".equals(type))) {
/* 309 */         if (str.equalsIgnoreCase("TRUE")) {
/* 310 */           return new Boolean(true);
/*     */         }
/* 312 */         return new Boolean(false);
/* 313 */       }if (("Double".equalsIgnoreCase(type)) || ("java.lang.Double".equalsIgnoreCase(type))) {
/* 314 */         Number tempNum = getNf(locale).parse(str.replaceAll(",", ""));
/* 315 */         return new Double(tempNum.doubleValue());
/* 316 */       }if (("BigDecimal".equalsIgnoreCase(type)) || ("java.math.BigDecimal".equalsIgnoreCase(type))) {
/* 317 */         BigDecimal retBig = new BigDecimal(str.replaceAll(",", ""));
/* 318 */         int iscale = str.indexOf(".");
/* 319 */         int keylen = str.length();
/* 320 */         if (iscale > -1) {
/* 321 */           iscale = keylen - (iscale + 1);
/* 322 */           return retBig.setScale(iscale, 5);
/*     */         }
/* 324 */         return retBig.setScale(0, 5);
/*     */       }
/* 326 */       if (("Float".equalsIgnoreCase(type)) || ("java.lang.Float".equalsIgnoreCase(type))) {
/* 327 */         Number tempNum = getNf(locale).parse(str.replaceAll(",", ""));
/* 328 */         return new Float(tempNum.floatValue());
/*     */       }
/* 330 */       if (("Long".equalsIgnoreCase(type)) || ("java.lang.Long".equalsIgnoreCase(type))) {
/* 331 */         NumberFormat nf = getNf(locale);
/* 332 */         nf.setMaximumFractionDigits(0);
/* 333 */         Number tempNum = nf.parse(str.replaceAll(",", ""));
/* 334 */         return new Long(tempNum.longValue());
/* 335 */       }if (("Integer".equalsIgnoreCase(type)) || ("java.lang.Integer".equalsIgnoreCase(type))) {
/* 336 */         NumberFormat nf = getNf(locale);
/* 337 */         nf.setMaximumFractionDigits(0);
/* 338 */         Number tempNum = nf.parse(str.replaceAll(",", ""));
/* 339 */         return new Integer(tempNum.intValue());
/* 340 */       }if (("Date".equalsIgnoreCase(type)) || ("java.util.Date".equalsIgnoreCase(type))) {
/* 341 */         if ((format == null) || (format.length() == 0)) {
/* 342 */           String separator = String.valueOf(str.charAt(4));
/*     */ 
/* 344 */           if (separator.matches("\\d*")) {
/* 345 */             StringBuilder pattern = new StringBuilder("yyyyMMdd HH:mm:ss");
/* 346 */             format = pattern.substring(0, str.length());
/*     */           } else {
/* 348 */             StringBuilder pattern = new StringBuilder("yyyy").append(separator).append("MM").append(separator)
/* 349 */               .append("dd HH:mm:ss");
/* 350 */             format = pattern.substring(0, str.length());
/*     */           }
/*     */         }
/* 353 */         SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 354 */         java.util.Date fieldDate = sdf.parse(str);
/* 355 */         return new java.sql.Date(fieldDate.getTime());
/* 356 */       }if ("java.sql.Date".equalsIgnoreCase(type)) {
/* 357 */         if ((format == null) || (format.length() == 0))
/*     */           try {
/* 359 */             return java.sql.Date.valueOf(str);
/*     */           } catch (Exception e) {
/*     */             try {
/* 362 */               DateFormat df = null;
/* 363 */               if (locale != null)
/* 364 */                 df = DateFormat.getDateInstance(3, locale);
/*     */               else
/* 366 */                 df = DateFormat.getDateInstance(3);
/* 367 */               java.util.Date fieldDate = df.parse(str);
/* 368 */               return new java.sql.Date(fieldDate.getTime());
/*     */             } catch (ParseException e1) {
/* 370 */               throw new DataParseException(String.format("Could not convert %1$s to %2$s", new Object[] { str, type }), e);
/*     */             }
/*     */           }
/* 373 */         SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 374 */         java.util.Date fieldDate = sdf.parse(str);
/* 375 */         return new java.sql.Date(fieldDate.getTime());
/* 376 */       }if (("Timestamp".equalsIgnoreCase(type)) || ("java.sql.Timestamp".equalsIgnoreCase(type))) {
/* 377 */         if (str.length() == 10)
/* 378 */           str = str + " 00:00:00";
/* 379 */         if ((format == null) || (format.length() == 0))
/*     */           try {
/* 381 */             return Timestamp.valueOf(str);
/*     */           } catch (Exception e) {
/*     */             try {
/* 384 */               DateFormat df = null;
/* 385 */               if (locale != null)
/* 386 */                 df = DateFormat.getDateTimeInstance(3, 3, locale);
/*     */               else
/* 388 */                 df = DateFormat.getDateTimeInstance(3, 3);
/* 389 */               java.util.Date fieldDate = df.parse(str);
/* 390 */               return new Timestamp(fieldDate.getTime());
/*     */             } catch (ParseException e1) {
/* 392 */               throw new DataParseException(String.format("Could not convert %1$s to %2$s", new Object[] { str, type }), e);
/*     */             }
/*     */           }
/*     */         try {
/* 396 */           SimpleDateFormat sdf = new SimpleDateFormat(format);
/* 397 */           java.util.Date fieldDate = sdf.parse(str);
/* 398 */           return new Timestamp(fieldDate.getTime());
/*     */         } catch (ParseException e) {
/* 400 */           throw new DataParseException(String.format("Could not convert %1$s to %2$s", new Object[] { str, type }), e);
/*     */         }
/*     */       }
/* 403 */       throw new DataParseException(String.format("Convert from %1$s to %2$s not currently supported", new Object[] { fromType, type }));
/*     */     }
/*     */     catch (Exception e) {
/* 406 */       throw new DataParseException(String.format("Could not convert %1$s to %2$s", new Object[] { str, type }), e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static NumberFormat getNf(Locale locale) {
/* 411 */     NumberFormat nf = null;
/* 412 */     if (locale == null)
/* 413 */       nf = NumberFormat.getNumberInstance();
/*     */     else {
/* 415 */       nf = NumberFormat.getNumberInstance(locale);
/*     */     }
/* 417 */     nf.setGroupingUsed(false);
/* 418 */     return nf;
/*     */   }
/*     */ 
/*     */   public static Boolean convertToBoolean(Object obj)
/*     */   {
/* 423 */     return (Boolean)convert(obj, "Boolean", null);
/*     */   }
/*     */ 
/*     */   public static Integer convertToInteger(Object obj)
/*     */   {
/* 428 */     return (Integer)convert(obj, "Integer", null);
/*     */   }
/*     */ 
/*     */   public static String convertToString(Object obj)
/*     */   {
/* 433 */     return (String)convert(obj, "String", null);
/*     */   }
/*     */ 
/*     */   public static String convertToString(Object obj, String defaultValue)
/*     */   {
/* 438 */     Object s = convert(obj, "String", null);
/* 439 */     if (s != null) {
/* 440 */       return (String)s;
/*     */     }
/* 442 */     return "";
/*     */   }
/*     */ 
/*     */   public static Long convertToLong(Object obj)
/*     */   {
/* 448 */     return (Long)convert(obj, "Long", null);
/*     */   }
/*     */ 
/*     */   public static Double convertToDouble(Object obj)
/*     */   {
/* 453 */     return (Double)convert(obj, "Double", null);
/*     */   }
/*     */ 
/*     */   public static Double convertToFloat(Object obj)
/*     */   {
/* 458 */     return (Double)convert(obj, "Float", null);
/*     */   }
/*     */ 
/*     */   public static BigDecimal convertToBigDecimal(Object obj, int scale)
/*     */   {
/* 463 */     return ((BigDecimal)convert(obj, "BigDecimal", null)).setScale(scale, 5);
/*     */   }
/*     */ 
/*     */   public static java.util.Date convertToDate(Object obj, String format)
/*     */   {
/* 468 */     return (java.util.Date)convert(obj, "Date", format);
/*     */   }
/*     */ 
/*     */   public static java.sql.Date convertToSqlDate(Object obj, String format)
/*     */   {
/* 473 */     return (java.sql.Date)convert(obj, "java.sql.Date", format);
/*     */   }
/*     */ 
/*     */   public static Timestamp convertToTimestamp(Object obj, String format)
/*     */   {
/* 478 */     return (Timestamp)convert(obj, "Timestamp", format);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.TypeParseUtil
 * JD-Core Version:    0.6.2
 */