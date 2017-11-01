/*     */ package org.jeecgframework.core.constant;
/*     */ 
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ 
/*     */ public final class Globals
/*     */ {
/*     */   public static final String USER_SESSION = "USER_SESSION";
/*  22 */   public static final Short User_Normal = Short.valueOf((short)1);
/*  23 */   public static final Short User_Forbidden = Short.valueOf((short)0);
/*  24 */   public static final Short User_ADMIN = Short.valueOf((short)-1);
/*     */ 
/*  32 */   public static final Short Delete_Forbidden = Short.valueOf((short)1);
/*     */ 
/*  36 */   public static final Short Delete_Normal = Short.valueOf((short)0);
/*     */ 
/*  41 */   public static final Short Log_Leavel_INFO = Short.valueOf((short)1);
/*  42 */   public static final Short Log_Leavel_WARRING = Short.valueOf((short)2);
/*  43 */   public static final Short Log_Leavel_ERROR = Short.valueOf((short)3);
/*     */ 
/*  47 */   public static final Short Log_Type_LOGIN = Short.valueOf((short)1);
/*  48 */   public static final Short Log_Type_EXIT = Short.valueOf((short)2);
/*  49 */   public static final Short Log_Type_INSERT = Short.valueOf((short)3);
/*  50 */   public static final Short Log_Type_DEL = Short.valueOf((short)4);
/*  51 */   public static final Short Log_Type_UPDATE = Short.valueOf((short)5);
/*  52 */   public static final Short Log_Type_UPLOAD = Short.valueOf((short)6);
/*  53 */   public static final Short Log_Type_OTHER = Short.valueOf((short)7);
/*     */   public static final String TypeGroup_Database = "database";
/*  64 */   public static final Short Function_Leave_ONE = Short.valueOf((short)0);
/*  65 */   public static final Short Function_Leave_TWO = Short.valueOf((short)1);
/*     */   public static final String Function_Order_ONE = "ofun";
/*     */   public static final String Function_Order_TWO = "tfun";
/*  76 */   public static final Short Function_TYPE_PAGE = Short.valueOf((short)0);
/*  77 */   public static final Short Function_TYPE_FROM = Short.valueOf((short)1);
/*     */   public static final String NOAUTO_OPERATIONCODES = "noauto_operationCodes";
/*     */   public static final String OPERATIONCODES = "operationCodes";
/*  91 */   public static final Short OPERATION_TYPE_HIDE = Short.valueOf((short)0);
/*  92 */   public static final Short OPERATION_TYPE_DISABLED = Short.valueOf((short)1);
/*     */   public static final String MENU_DATA_AUTHOR_RULES = "MENU_DATA_AUTHOR_RULES";
/*     */   public static final String MENU_DATA_AUTHOR_RULE_SQL = "MENU_DATA_AUTHOR_RULE_SQL";
/* 107 */   public static final Short Document_NEW = Short.valueOf((short)0);
/* 108 */   public static final Short Document_PUBLICH = Short.valueOf((short)0);
/*     */   public static final String MAIL_STATUS_UNSEND = "00";
/*     */   public static final String MAIL_STATUS_SEND = "01";
/*     */   public static final String MAIL_STATUS_DEL = "02";
/*     */   public static final String MAILRECEIVER_STATUS_UNREAD = "00";
/*     */   public static final String MAILRECEIVER_STATUS_READ = "01";
/* 122 */   public static boolean BUTTON_AUTHORITY_CHECK = false;
/*     */ 
/* 124 */   static { String button_authority_jeecg = ResourceUtil.getSessionattachmenttitle("button.authority.jeecg");
/* 125 */     if ("true".equals(button_authority_jeecg))
/* 126 */       BUTTON_AUTHORITY_CHECK = true;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.constant.Globals
 * JD-Core Version:    0.6.2
 */