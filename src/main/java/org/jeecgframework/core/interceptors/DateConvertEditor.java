/*    */ package org.jeecgframework.core.interceptors;
/*    */ 
/*    */ import java.beans.PropertyEditorSupport;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import org.springframework.util.StringUtils;
/*    */ 
/*    */ public class DateConvertEditor extends PropertyEditorSupport
/*    */ {
/* 14 */   private SimpleDateFormat datetimeFormat = new SimpleDateFormat(
/* 15 */     "yyyy-MM-dd HH:mm:ss");
/*    */ 
/* 16 */   private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*    */ 
/* 18 */   public void setAsText(String text) throws IllegalArgumentException { if (StringUtils.hasText(text))
/*    */       try {
/* 20 */         if ((text.indexOf(":") == -1) && (text.length() == 10)) {
/* 21 */           setValue(this.dateFormat.parse(text));
/* 22 */           return; } if ((text.indexOf(":") > 0) && (text.length() == 19)) {
/* 23 */           setValue(this.datetimeFormat.parse(text));
/* 24 */           return; } if ((text.indexOf(":") > 0) && (text.length() == 21)) {
/* 25 */           text = text.replace(".0", "");
/* 26 */           setValue(this.datetimeFormat.parse(text));
/* 27 */           return;
/* 28 */         }throw new IllegalArgumentException(
/* 29 */           "Could not parse date, date format is error ");
/*    */       }
/*    */       catch (ParseException ex) {
/* 32 */         IllegalArgumentException iae = new IllegalArgumentException(
/* 33 */           "Could not parse date: " + ex.getMessage());
/* 34 */         iae.initCause(ex);
/* 35 */         throw iae;
/*    */       }
/*    */     else
/* 38 */       setValue(null);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.interceptors.DateConvertEditor
 * JD-Core Version:    0.6.2
 */