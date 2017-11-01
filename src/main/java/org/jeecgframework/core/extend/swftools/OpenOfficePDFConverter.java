/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.artofsolving.jodconverter.OfficeDocumentConverter;
/*    */ import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
/*    */ import org.artofsolving.jodconverter.office.OfficeManager;
/*    */ import org.jeecgframework.core.util.FileUtils;
/*    */ import org.jeecgframework.core.util.LogUtil;
/*    */ 
/*    */ public class OpenOfficePDFConverter
/*    */   implements PDFConverter
/*    */ {
/*    */   private static OfficeManager officeManager;
/* 22 */   private static String OFFICE_HOME = ConStant.OFFICE_HOME;
/* 23 */   private static int[] port = { 8100 };
/*    */ 
/*    */   public void convert2PDF(String inputFile, String pdfFile, String extend)
/*    */   {
/* 46 */     startService();
/*    */ 
/* 48 */     LogUtil.info("进行文档转换转换:" + inputFile + " --> " + pdfFile);
/* 49 */     OfficeDocumentConverter converter = new OfficeDocumentConverter(
/* 50 */       officeManager);
/*    */     try {
/* 52 */       converter.convert(new File(inputFile), new File(pdfFile));
/*    */     }
/*    */     catch (Exception e) {
/* 55 */       LogUtil.info(e.getMessage());
/*    */     }
/*    */ 
/* 58 */     stopService();
/* 59 */     LogUtil.info("进行文档转换转换---- 结束----");
/*    */   }
/*    */ 
/*    */   public void convert2PDF(String inputFile, String extend)
/*    */   {
/* 64 */     String pdfFile = FileUtils.getFilePrefix2(inputFile) + ".pdf";
/* 65 */     convert2PDF(inputFile, pdfFile, extend);
/*    */   }
/*    */ 
/*    */   public static void startService()
/*    */   {
/* 70 */     DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
/*    */     try
/*    */     {
/* 73 */       configuration.setOfficeHome(OFFICE_HOME);
/*    */ 
/* 75 */       configuration.setPortNumbers(port);
/*    */ 
/* 77 */       configuration.setTaskExecutionTimeout(300000L);
/*    */ 
/* 79 */       configuration.setTaskQueueTimeout(86400000L);
/*    */ 
/* 81 */       officeManager = configuration.buildOfficeManager();
/* 82 */       officeManager.start();
/* 83 */       LogUtil.info("office转换服务启动成功!");
/*    */     } catch (Exception ce) {
/* 85 */       LogUtil.info("office转换服务启动失败!详细信息:" + ce);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void stopService() {
/* 90 */     LogUtil.info("关闭office转换服务....");
/* 91 */     if (officeManager != null) {
/* 92 */       officeManager.stop();
/*    */     }
/* 94 */     LogUtil.info("关闭office转换成功!");
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.OpenOfficePDFConverter
 * JD-Core Version:    0.6.2
 */