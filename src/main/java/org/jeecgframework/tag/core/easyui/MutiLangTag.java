/*    */ package org.jeecgframework.tag.core.easyui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.jsp.JspTagException;
/*    */ import javax.servlet.jsp.JspWriter;
/*    */ import javax.servlet.jsp.PageContext;
/*    */ import javax.servlet.jsp.tagext.TagSupport;
/*    */ import org.jeecgframework.core.util.ApplicationContextUtil;
/*    */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ 
/*    */ public class MutiLangTag extends TagSupport
/*    */ {
/*    */   protected String langKey;
/*    */   protected String langArg;
/*    */ 
/*    */   @Autowired
/*    */   private static MutiLangServiceI mutiLangService;
/*    */ 
/*    */   public int doStartTag()
/*    */     throws JspTagException
/*    */   {
/* 30 */     return 6;
/*    */   }
/*    */ 
/*    */   public int doEndTag() throws JspTagException {
/* 34 */     JspWriter out = null;
/*    */     try {
/* 36 */       out = this.pageContext.getOut();
/* 37 */       out.print(end().toString());
/* 38 */       out.flush();
/*    */     } catch (IOException e) {
/* 40 */       e.printStackTrace();
/*    */       try
/*    */       {
/* 43 */         out.clear();
/* 44 */         out.close();
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 43 */         out.clear();
/* 44 */         out.close();
/*    */       } catch (Exception localException1) {
/*    */       }
/*    */     }
/* 48 */     return 6;
/*    */   }
/*    */ 
/*    */   public String end() {
/* 52 */     if (mutiLangService == null)
/*    */     {
/* 54 */       mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
/*    */     }
/*    */ 
/* 57 */     String lang_context = mutiLangService.getLang(this.langKey, this.langArg);
/*    */ 
/* 59 */     return lang_context;
/*    */   }
/*    */ 
/*    */   public void setLangKey(String langKey) {
/* 63 */     this.langKey = langKey;
/*    */   }
/*    */ 
/*    */   public void setLangArg(String langArg) {
/* 67 */     this.langArg = langArg;
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.tag.core.easyui.MutiLangTag
 * JD-Core Version:    0.6.2
 */