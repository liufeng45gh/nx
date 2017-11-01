/*   */ package ssb.warmline.business.commons.utils;
/*   */ 
/*   */ import java.io.PrintStream;
/*   */ import java.util.UUID;
/*   */ 
/*   */ public class UUIDUtil
/*   */ {
/*   */   public static synchronized String getId()
/*   */   {
/* 5 */     return UUID.randomUUID().toString().replaceAll("-", "");
/*   */   }
/*   */   public static void main(String[] args) {
/* 8 */     System.out.println(getId());
/*   */   }
/*   */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.UUIDUtil
 * JD-Core Version:    0.6.2
 */