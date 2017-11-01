/*     */ package ssb.warmline.business.controller.keyword;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.exception.BusinessException;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/keyWordController"})
/*     */ public class KeyWordController extends BaseController
/*     */ {
/*  32 */   static final String separator = File.separator;
/*     */ 
/*  36 */   private static final Logger logger = Logger.getLogger(KeyWordController.class);
/*     */ 
/*     */   @RequestMapping(params={"list"})
/*     */   public ModelAndView list(HttpServletRequest request)
/*     */   {
/*  47 */     String path_temp = request.getSession().getServletContext().getRealPath("");
/*  48 */     String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/*  49 */     String wordPath = path + separator + "keyword" + separator + "word.txt";
/*     */ 
/*  57 */     String word = null;
/*     */     try {
/*  59 */       File f = new File(wordPath);
/*  60 */       InputStream input = new FileInputStream(f);
/*  61 */       byte[] b = new byte[(int)f.length()];
/*  62 */       for (int i = 0; i < b.length; i++) {
/*  63 */         b[i] = ((byte)input.read());
/*     */       }
/*  65 */       input.close();
/*  66 */       word = new String(b);
/*     */     } catch (IOException e) {
/*  68 */       e.printStackTrace();
/*     */     }
/*  70 */     request.setAttribute("word", word);
/*     */ 
/*  72 */     return new ModelAndView("ssb/warmline/business/keyword/keyword");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"doAdd"})
/*     */   @ResponseBody
/*     */   public String doAdd(HttpServletRequest request)
/*     */   {
/*  84 */     String message = null;
/*  85 */     message = "添加成功";
/*  86 */     String word = request.getParameter("keyword");
/*     */ 
/*  89 */     String path_temp = request.getSession().getServletContext().getRealPath("");
/*  90 */     String path = path_temp.substring(0, path_temp.lastIndexOf(separator));
/*  91 */     String wordPath = path + separator + "keyword" + separator + "word.txt";
/*  92 */     System.out.println(word);
/*  93 */     if ((word != null) && (!"".equals(word))) {
/*     */       try {
/*  95 */         File f = new File(wordPath);
/*  96 */         OutputStream out = new FileOutputStream(f);
/*  97 */         byte[] b = word.getBytes();
/*  98 */         for (int a = 0; a < b.length; a++) {
/*  99 */           out.write(b[a]);
/* 100 */           out.flush();
/*     */         }
/* 102 */         out.close();
/*     */       } catch (Exception e) {
/* 104 */         e.printStackTrace();
/* 105 */         message = "添加失败";
/* 106 */         throw new BusinessException(e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/* 110 */     return message;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.controller.keyword.KeyWordController
 * JD-Core Version:    0.6.2
 */