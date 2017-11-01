/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.jeecgframework.core.util.FileUtils;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ import org.jeecgframework.core.util.PinyinUtil;
/*    */ 
/*    */ public class SWFToolsSWFConverter
/*    */   implements SWFConverter
/*    */ {
/* 12 */   private static String PDF2SWF_PATH = "pdf2swf.exe";
/*    */ 
/*    */   public void convert2SWF(String inputFile, String swfFile, String extend) {
/* 15 */     File pdfFile = new File(inputFile);
/* 16 */     File outFile = new File(swfFile);
/*    */ 
/* 18 */     if (!pdfFile.exists()) {
/* 19 */       LogUtil.info("PDF文件不存在！");
/* 20 */       return;
/*    */     }
/*    */ 
/* 23 */     if (outFile.exists()) {
/* 24 */       LogUtil.info("SWF文件已存在！");
/* 25 */       return;
/*    */     }
/* 27 */     String command = ConStant.getSWFToolsPath(extend) + " \"" + inputFile + 
/* 29 */       "\" -o " + " \"" + swfFile + " \"" + " -s languagedir=D:\\xpdf-chinese-simplified -T 9 -f";
/*    */     try
/*    */     {
/* 34 */       Process process = Runtime.getRuntime().exec(command);
/* 35 */       StreamGobbler errorGobbler = new StreamGobbler(
/* 36 */         process.getErrorStream(), "Error");
/* 37 */       StreamGobbler outputGobbler = new StreamGobbler(
/* 38 */         process.getInputStream(), "Output");
/* 39 */       errorGobbler.start();
/* 40 */       outputGobbler.start();
/*    */       try {
/* 42 */         process.waitFor();
/* 43 */         LogUtil.info("时间-------" + process.waitFor());
/*    */       } catch (InterruptedException e) {
/* 45 */         e.printStackTrace();
/*    */       }
/*    */     } catch (IOException e) {
/* 48 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 52 */   public void convert2SWF(String inputFile, String extend) { String swfFile = PinyinUtil.getPinYinHeadChar(FileUtils.getFilePrefix2(inputFile)) + ".swf";
/* 53 */     convert2SWF(inputFile, swfFile, extend);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.SWFToolsSWFConverter
 * JD-Core Version:    0.6.2
 */