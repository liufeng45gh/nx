/*    */ package ssb.warmline.business.commons.shortMessage;
/*    */ 
/*    */ public class PhoneCode
/*    */ {
/*    */   public static String getLoginPhoneCode(String phone)
/*    */   {
/* 17 */     Integer mobileCode = Integer.valueOf((int)((Math.random() * 9.0D + 1.0D) * 1000.0D));
/*    */ 
/* 19 */     JedisUtil.setex(("mobileCode" + phone).getBytes(), 60, (mobileCode + phone).getBytes());
/* 20 */     String content = new String("【暖行】您的验证码是：" + mobileCode + "，一分钟内有效。");
/* 21 */     SmsUtil.sendSMS(phone, content, null);
/* 22 */     return mobileCode.toString();
/*    */   }
/*    */ 
/*    */   public static String getLoginPhoneOrderCode(String phone, String content)
/*    */   {
/* 31 */     SmsUtil.sendSMS(phone, content, null);
/* 32 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.shortMessage.PhoneCode
 * JD-Core Version:    0.6.2
 */