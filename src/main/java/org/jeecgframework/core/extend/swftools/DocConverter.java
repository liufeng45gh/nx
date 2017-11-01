/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import org.jeecgframework.core.util.FileUtils;
/*    */ 
/*    */ public class DocConverter
/*    */ {
/*    */   private PDFConverter pdfConverter;
/*    */   private SWFConverter swfConverter;
/*    */ 
/*    */   public DocConverter(PDFConverter pdfConverter, SWFConverter swfConverter)
/*    */   {
/* 11 */     this.pdfConverter = pdfConverter;
/* 12 */     this.swfConverter = swfConverter;
/*    */   }
/*    */ 
/*    */   public void convert(String inputFile, String swfFile, String extend)
/*    */   {
/* 17 */     this.pdfConverter.convert2PDF(inputFile, extend);
/* 18 */     String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
/* 19 */     this.swfConverter.convert2SWF(pdfFile, swfFile);
/*    */   }
/*    */ 
/*    */   public void convert(String inputFile, String extend) {
/* 23 */     this.pdfConverter.convert2PDF(inputFile, extend);
/* 24 */     String pdfFile = FileUtils.getFilePrefix2(inputFile) + ".pdf";
/* 25 */     extend = FileUtils.getExtend(pdfFile);
/* 26 */     this.swfConverter.convert2SWF(pdfFile, extend);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.DocConverter
 * JD-Core Version:    0.6.2
 */