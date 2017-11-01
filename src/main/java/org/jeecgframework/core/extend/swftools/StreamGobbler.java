/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ 
/*    */ public class StreamGobbler extends Thread
/*    */ {
/*    */   InputStream is;
/*    */   String type;
/*    */ 
/*    */   public StreamGobbler(InputStream is, String type)
/*    */   {
/* 14 */     this.is = is;
/* 15 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public void run() {
/*    */     try {
/* 20 */       InputStreamReader isr = new InputStreamReader(this.is);
/* 21 */       BufferedReader br = new BufferedReader(isr);
/* 22 */       String line = null;
/* 23 */       while ((line = br.readLine()) != null)
/* 24 */         if (this.type.equals("Error"))
/* 25 */           LogUtil.info("Error\t:" + line);
/*    */         else
/* 27 */           LogUtil.info("文件转换:" + line);
/*    */     }
/*    */     catch (IOException ioe)
/*    */     {
/* 31 */       ioe.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.StreamGobbler
 * JD-Core Version:    0.6.2
 */