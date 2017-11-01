/*    */ package ssb.warmline.business.commons.utils.JMessage;
/*    */ 
/*    */ import cn.jiguang.common.ServiceHelper;
/*    */ 
/*    */ public class StringUtils
/*    */ {
/*    */   public static void checkUsername(String username)
/*    */   {
/*  8 */     if ((username == null) || (username.trim().length() == 0)) {
/*  9 */       throw new IllegalArgumentException("用户名不能是空");
/*    */     }
/* 11 */     if ((username.contains("\n")) || (username.contains("\r")) || (username.contains("\t"))) {
/* 12 */       throw new IllegalArgumentException("用户名不能包含换行字符");
/*    */     }
/* 14 */     byte[] usernameByte = username.getBytes();
/* 15 */     if ((usernameByte.length < 4) || (usernameByte.length > 128)) {
/* 16 */       throw new IllegalArgumentException("用户名的长度必须在4和128字节之间。输入是： " + username);
/*    */     }
/* 18 */     if (!ServiceHelper.checkUsername(username))
/* 19 */       throw new IllegalArgumentException("参数的用户名包含非法字符, a-zA-Z_0-9.、-,@。 必须在,开始用字母或数字。输入是： " + 
/* 20 */         username);
/*    */   }
/*    */ 
/*    */   public static void checkPassword(String password)
/*    */   {
/* 25 */     if ((password == null) || (password.trim().length() == 0)) {
/* 26 */       throw new IllegalArgumentException("密码不能是空");
/*    */     }
/*    */ 
/* 29 */     byte[] passwordByte = password.getBytes();
/* 30 */     if ((passwordByte.length < 4) || (passwordByte.length > 128))
/* 31 */       throw new IllegalArgumentException("密码的长度必须在4和128字节之间。输入是： " + password);
/*    */   }
/*    */ 
/*    */   public static boolean isNotEmpty(String s)
/*    */   {
/* 37 */     return (s != null) && (s.length() > 0);
/*    */   }
/*    */ 
/*    */   public static boolean isTrimedEmpty(String s) {
/* 41 */     return (s == null) || (s.trim().length() == 0);
/*    */   }
/*    */ 
/*    */   public static boolean isLineBroken(String s) {
/* 45 */     if ((s.contains("\n")) || (s.contains("\r"))) {
/* 46 */       return true;
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.StringUtils
 * JD-Core Version:    0.6.2
 */