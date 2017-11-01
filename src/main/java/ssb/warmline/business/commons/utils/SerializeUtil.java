/*    */ package ssb.warmline.business.commons.utils;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ 
/*    */ public class SerializeUtil
/*    */ {
/*    */   public static byte[] serialize(Object obj)
/*    */   {
/* 13 */     ObjectOutputStream obi = null;
/* 14 */     ByteArrayOutputStream bai = null;
/*    */     try {
/* 16 */       bai = new ByteArrayOutputStream();
/* 17 */       obi = new ObjectOutputStream(bai);
/* 18 */       obi.writeObject(obj);
/* 19 */       return bai.toByteArray();
/*    */     }
/*    */     catch (IOException e) {
/* 22 */       e.printStackTrace();
/*    */     }
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   public static Object unserizlize(byte[] byt)
/*    */   {
/* 29 */     ObjectInputStream oii = null;
/* 30 */     ByteArrayInputStream bis = null;
/* 31 */     bis = new ByteArrayInputStream(byt);
/*    */     try {
/* 33 */       oii = new ObjectInputStream(bis);
/* 34 */       return oii.readObject();
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */ 
/* 42 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.SerializeUtil
 * JD-Core Version:    0.6.2
 */