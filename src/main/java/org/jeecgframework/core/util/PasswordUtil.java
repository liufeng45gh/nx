/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.security.Key;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.PBEKeySpec;
/*     */ import javax.crypto.spec.PBEParameterSpec;
/*     */ 
/*     */ public class PasswordUtil
/*     */ {
/*     */   public static final String ALGORITHM = "PBEWithMD5AndDES";
/*     */   public static final String Salt = "63293188";
/*     */   private static final int ITERATIONCOUNT = 1000;
/*     */ 
/*     */   public static byte[] getSalt()
/*     */     throws Exception
/*     */   {
/*  35 */     SecureRandom random = new SecureRandom();
/*     */ 
/*  37 */     return random.generateSeed(8);
/*     */   }
/*     */ 
/*     */   public static byte[] getStaticSalt()
/*     */   {
/*  42 */     return "63293188".getBytes();
/*     */   }
/*     */ 
/*     */   private static Key getPBEKey(String password)
/*     */   {
/*  55 */     SecretKey secretKey = null;
/*     */     try {
/*  57 */       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
/*     */ 
/*  59 */       PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
/*     */ 
/*  61 */       secretKey = keyFactory.generateSecret(keySpec);
/*     */     }
/*     */     catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  67 */     return secretKey;
/*     */   }
/*     */ 
/*     */   public static String encrypt(String plaintext, String password, byte[] salt)
/*     */   {
/*  84 */     Key key = getPBEKey(password);
/*  85 */     byte[] encipheredData = null;
/*  86 */     PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, 1000);
/*     */     try {
/*  88 */       Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
/*     */ 
/*  90 */       cipher.init(1, key, parameterSpec);
/*     */ 
/*  92 */       encipheredData = cipher.doFinal(plaintext.getBytes());
/*     */     } catch (Exception localException) {
/*     */     }
/*  95 */     return bytesToHexString(encipheredData);
/*     */   }
/*     */ 
/*     */   public static String decrypt(String ciphertext, String password)
/*     */   {
/* 112 */     Key key = getPBEKey(password);
/* 113 */     byte[] passDec = null;
/* 114 */     PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), 1000);
/*     */     try {
/* 116 */       Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
/*     */ 
/* 118 */       cipher.init(2, key, parameterSpec);
/*     */ 
/* 120 */       passDec = cipher.doFinal(hexStringToBytes(ciphertext));
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */ 
/* 126 */     return new String(passDec);
/*     */   }
/*     */ 
/*     */   public static String bytesToHexString(byte[] src)
/*     */   {
/* 137 */     StringBuilder stringBuilder = new StringBuilder("");
/* 138 */     if ((src == null) || (src.length <= 0)) {
/* 139 */       return null;
/*     */     }
/* 141 */     for (int i = 0; i < src.length; i++) {
/* 142 */       int v = src[i] & 0xFF;
/* 143 */       String hv = Integer.toHexString(v);
/* 144 */       if (hv.length() < 2) {
/* 145 */         stringBuilder.append(0);
/*     */       }
/* 147 */       stringBuilder.append(hv);
/*     */     }
/* 149 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static byte[] hexStringToBytes(String hexString)
/*     */   {
/* 160 */     if ((hexString == null) || (hexString.equals(""))) {
/* 161 */       return null;
/*     */     }
/* 163 */     hexString = hexString.toUpperCase();
/* 164 */     int length = hexString.length() / 2;
/* 165 */     char[] hexChars = hexString.toCharArray();
/* 166 */     byte[] d = new byte[length];
/* 167 */     for (int i = 0; i < length; i++) {
/* 168 */       int pos = i * 2;
/* 169 */       d[i] = ((byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)])));
/*     */     }
/* 171 */     return d;
/*     */   }
/*     */ 
/*     */   private static byte charToByte(char c) {
/* 175 */     return (byte)"0123456789ABCDEF".indexOf(c);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 179 */     int i = 10;
/* 180 */     for (int j = 0; j < i; j++) {
/* 181 */       if (j % 3 == 0)
/*     */       {
/* 183 */         System.out.print("<br>");
/*     */       }
/*     */       else {
/* 186 */         System.out.print(j);
/*     */       }
/*     */     }
/* 189 */     System.out.print(false);
/* 190 */     String str = "admin";
/* 191 */     String password = "123456";
/*     */ 
/* 193 */     LogUtil.info("明文:" + str);
/* 194 */     LogUtil.info("密码:" + password);
/*     */     try
/*     */     {
/* 197 */       byte[] salt = getStaticSalt();
/* 198 */       String ciphertext = encrypt(str, password, salt);
/* 199 */       LogUtil.info("密文:" + ciphertext);
/* 200 */       String plaintext = decrypt(ciphertext, password);
/* 201 */       LogUtil.info("明文:" + plaintext);
/*     */     } catch (Exception e) {
/* 203 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.PasswordUtil
 * JD-Core Version:    0.6.2
 */