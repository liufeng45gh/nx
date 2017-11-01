/*    */ package org.jeecgframework.core.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.net.InetAddress;
/*    */ import java.net.NetworkInterface;
/*    */ 
/*    */ public class IpConfigMac
/*    */ {
/*    */   public static void main(String[] arguments)
/*    */     throws Exception
/*    */   {
/* 12 */     InetAddress ia = InetAddress.getLocalHost();
/* 13 */     System.out.println(ia);
/* 14 */     System.out.println("MAC ......... " + getMACAddress(ia));
/*    */   }
/*    */ 
/*    */   private static String getMACAddress(InetAddress ia)
/*    */     throws Exception
/*    */   {
/* 20 */     byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
/*    */ 
/* 22 */     StringBuffer sb = new StringBuffer();
/* 23 */     for (int i = 0; i < mac.length; i++) {
/* 24 */       if (i != 0) {
/* 25 */         sb.append("-");
/*    */       }
/*    */ 
/* 28 */       String s = Integer.toHexString(mac[i] & 0xFF);
/* 29 */       sb.append(s.length() == 1 ? 0 + s : s);
/*    */     }
/*    */ 
/* 32 */     return sb.toString().toUpperCase();
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.IpConfigMac
 * JD-Core Version:    0.6.2
 */