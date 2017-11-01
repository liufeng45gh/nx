/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import org.jeecgframework.core.util.FileUtils;
/*    */ 
/*    */ public class SwfToolsUtil
/*    */ {
/*    */   public static void convert2SWF(String inputFile)
/*    */   {
/* 12 */     String extend = FileUtils.getExtend(inputFile);
/* 13 */     PDFConverter pdfConverter = new OpenOfficePDFConverter();
/* 14 */     SWFConverter swfConverter = new SWFToolsSWFConverter();
/* 15 */     if (extend.equals("pdf"))
/*    */     {
/* 17 */       swfConverter.convert2SWF(inputFile, extend);
/*    */     }
/* 19 */     if ((extend.equals("doc")) || (extend.equals("docx")) || (extend.equals("xls")) || (extend.equals("pptx")) || (extend.equals("xlsx")) || (extend.equals("ppt")) || (extend.equals("txt")) || (extend.equals("odt")))
/*    */     {
/* 21 */       DocConverter converter = new DocConverter(pdfConverter, swfConverter);
/* 22 */       converter.convert(inputFile, extend);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.SwfToolsUtil
 * JD-Core Version:    0.6.2
 */