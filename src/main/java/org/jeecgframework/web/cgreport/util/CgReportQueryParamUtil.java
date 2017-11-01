/*    */ package org.jeecgframework.web.cgreport.util;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.jeecgframework.core.util.StringUtil;
/*    */ import org.jeecgframework.web.cgform.util.QueryParamUtil;
/*    */ 
/*    */ public class CgReportQueryParamUtil extends QueryParamUtil
/*    */ {
/*    */   public static void loadQueryParams(HttpServletRequest request, Map item, Map params)
/*    */   {
/* 31 */     String filedName = (String)item.get("field_name");
/* 32 */     String queryMode = (String)item.get("search_mode");
/* 33 */     String filedType = (String)item.get("field_type");
/* 34 */     if ("single".equals(queryMode))
/*    */     {
/* 36 */       String value = request.getParameter(filedName);
/*    */       try {
/* 38 */         if (StringUtil.isEmpty(value)) {
/* 39 */           return;
/*    */         }
/* 41 */         String uri = request.getQueryString();
/* 42 */         if (uri.contains(filedName + "=")) {
/* 43 */           String contiansChinesevalue = new String(value.getBytes("ISO-8859-1"), "UTF-8");
/* 44 */           value = contiansChinesevalue;
/*    */         }
/*    */       } catch (UnsupportedEncodingException e) {
/* 47 */         e.printStackTrace();
/* 48 */         return;
/*    */       }
/* 50 */       sql_inj_throw(value);
/* 51 */       value = applyType(filedType, value);
/* 52 */       if (!StringUtil.isEmpty(value))
/* 53 */         if (value.contains("*"))
/*    */         {
/* 55 */           value = value.replaceAll("\\*", "%");
/* 56 */           params.put(filedName, " LIKE " + value);
/*    */         } else {
/* 58 */           params.put(filedName, " = " + value);
/*    */         }
/*    */     }
/* 61 */     else if ("group".equals(queryMode))
/*    */     {
/* 63 */       String begin = request.getParameter(filedName + "_begin");
/* 64 */       sql_inj_throw(begin);
/* 65 */       begin = applyType(filedType, begin);
/* 66 */       String end = request.getParameter(filedName + "_end");
/* 67 */       sql_inj_throw(end);
/* 68 */       end = applyType(filedType, end);
/* 69 */       if (!StringUtil.isEmpty(begin)) {
/* 70 */         String re = " >= " + begin;
/* 71 */         if (!StringUtil.isEmpty(end)) {
/* 72 */           re = re + " AND " + filedName + " <= " + end;
/*    */         }
/* 74 */         params.put(filedName, re);
/* 75 */       } else if (!StringUtil.isEmpty(end)) {
/* 76 */         String re = " <= " + end;
/* 77 */         params.put(filedName, re);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.cgreport.util.CgReportQueryParamUtil
 * JD-Core Version:    0.6.2
 */