/*    */ package org.jeecgframework.core.extend.swftools;
/*    */ 
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.jeecgframework.core.util.ContextHolderUtils;
/*    */ import org.jeecgframework.core.util.ResourceUtil;
/*    */ 
/*    */ public class ConStant
/*    */ {
/* 16 */   public static final String OFFICE_HOME = ResourceUtil.getSessionattachmenttitle("office_home");
/*    */   public static final String UPLOAD_BASE_DIR = "upload";
/*    */   public static final String SWFTOOLS_BASE_DIR = "swftools";
/*    */   public static final String SWFTOOLS_PDF2SWF_PATH = "pdf2swf.exe";
/*    */   public static final String SWFTOOLS_GIF2SWF_PATH = "gif2swf.exe";
/*    */   public static final String SWFTOOLS_PNG2SWF_PATH = "png2swf.exe";
/*    */   public static final String SWFTOOLS_JPEG2SWF_PATH = "jpeg2swf.exe";
/*    */   public static final String SWFTOOLS_WAV2SWF_PATH = "wav2swf.exe";
/*    */   public static final String SWFTOOLS_PDFCOMBINE_PATH = "swfcombine.exe";
/*    */   public static final String SWF_STUFFIX = "swf";
/* 36 */   public static String SWFTOOLS_HOME = "";
/*    */ 
/*    */   public static String getSWFToolsPath(String extend)
/*    */   {
/* 44 */     HttpServletRequest request = ContextHolderUtils.getRequest();
/* 45 */     SWFTOOLS_HOME = request.getSession().getServletContext().getRealPath("/") + "swftools" + "/";
/* 46 */     if (extend.equals("pdf"))
/*    */     {
/* 48 */       SWFTOOLS_HOME += "pdf2swf.exe";
/*    */     }
/* 50 */     if (extend.equals("gif"))
/*    */     {
/* 52 */       SWFTOOLS_HOME += "gif2swf.exe";
/*    */     }
/* 54 */     if (extend.equals("png"))
/*    */     {
/* 56 */       SWFTOOLS_HOME += "png2swf.exe";
/*    */     }
/* 58 */     if (extend.equals("jpeg"))
/*    */     {
/* 60 */       SWFTOOLS_HOME += "jpeg2swf.exe";
/*    */     }
/* 62 */     if (extend.equals("wav"))
/*    */     {
/* 64 */       SWFTOOLS_HOME += "wav2swf.exe";
/*    */     }
/* 66 */     return SWFTOOLS_HOME;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.extend.swftools.ConStant
 * JD-Core Version:    0.6.2
 */