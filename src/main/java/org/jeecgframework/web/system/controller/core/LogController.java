/*     */ package org.jeecgframework.web.system.controller.core;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringReader;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.ParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.batik.transcoder.Transcoder;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderInput;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.image.JPEGTranscoder;
/*     */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*     */ import org.apache.fop.svg.PDFTranscoder;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.common.controller.BaseController;
/*     */ import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.common.model.json.Highchart;
/*     */ import org.jeecgframework.core.util.MutiLangUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.core.util.oConvertUtils;
/*     */ import org.jeecgframework.tag.core.easyui.TagUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSLog;
/*     */ import org.jeecgframework.web.system.service.LogService;
/*     */ import org.jeecgframework.web.system.service.MutiLangServiceI;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import ssb.warmline.business.commons.utils.DateUtils;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/logController"})
/*     */ public class LogController extends BaseController
/*     */ {
/*  58 */   private static final Logger logger = Logger.getLogger(LogController.class);
/*     */   private static final String USER_BROWSER_ANALYSIS = "user.browser.analysis";
/*     */   private SystemService systemService;
/*     */   private LogService logService;
/*     */ 
/*     */   @Autowired
/*     */   public void setSystemService(SystemService systemService)
/*     */   {
/*  68 */     this.systemService = systemService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setLogService(LogService logService) {
/*  73 */     this.logService = logService;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"log"})
/*     */   public ModelAndView log()
/*     */   {
/*  83 */     return new ModelAndView("system/log/logList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"datagrid"})
/*     */   public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/*  95 */     CriteriaQuery cq = new CriteriaQuery(TSLog.class, dataGrid);
/*  96 */     String loglevel = request.getParameter("loglevel");
/*  97 */     if ((loglevel != null) && (!loglevel.equals("0")))
/*     */     {
/*  99 */       cq.eq("loglevel", oConvertUtils.getShort(loglevel));
/* 100 */       cq.add();
/*     */     }
/*     */ 
/* 103 */     String operatetime_begin = request.getParameter("operatetime_begin");
/* 104 */     if (operatetime_begin != null) {
/* 105 */       Timestamp beginValue = null;
/*     */       try {
/* 107 */         beginValue = DateUtils.parseTimestamp(operatetime_begin, "yyyy-MM-dd");
/*     */       } catch (ParseException e) {
/* 109 */         e.printStackTrace();
/*     */       }
/* 111 */       cq.ge("operatetime", beginValue);
/*     */     }
/* 113 */     String operatetime_end = request.getParameter("operatetime_end");
/* 114 */     if (operatetime_end != null) {
/* 115 */       if (operatetime_end.length() == 10) {
/* 116 */         operatetime_end = operatetime_end + " 23:59:59";
/*     */       }
/* 118 */       Timestamp endValue = null;
/*     */       try {
/* 120 */         endValue = DateUtils.parseTimestamp(operatetime_end, "yyyy-MM-dd hh:mm:ss");
/*     */       } catch (ParseException e) {
/* 122 */         e.printStackTrace();
/*     */       }
/* 124 */       cq.le("operatetime", endValue);
/*     */     }
/* 126 */     cq.add();
/*     */ 
/* 128 */     this.systemService.getDataGridReturn(cq, true);
/* 129 */     TagUtil.datagrid(response, dataGrid);
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"logDetail"})
/*     */   public ModelAndView logDetail(TSLog tsLog, HttpServletRequest request)
/*     */   {
/* 142 */     if (StringUtil.isNotEmpty(tsLog.getId())) {
/* 143 */       tsLog = (TSLog)this.logService.getEntity(TSLog.class, tsLog.getId());
/* 144 */       request.setAttribute("tsLog", tsLog);
/*     */     }
/* 146 */     return new ModelAndView("system/log/logDetail");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"statisticTabs"})
/*     */   public ModelAndView statisticTabs(HttpServletRequest request)
/*     */   {
/* 169 */     return new ModelAndView("system/log/statisticTabs");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"userBroswer"})
/*     */   public ModelAndView userBroswer(String reportType, HttpServletRequest request)
/*     */   {
/* 178 */     request.setAttribute("reportType", reportType);
/* 179 */     if ("pie".equals(reportType))
/* 180 */       return new ModelAndView("system/log/userBroswerPie");
/* 181 */     if ("line".equals(reportType)) {
/* 182 */       return new ModelAndView("system/log/userBroswerLine");
/*     */     }
/* 184 */     return new ModelAndView("system/log/userBroswer");
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"getBroswerBar"})
/*     */   @ResponseBody
/*     */   public List<Highchart> getBroswerBar(HttpServletRequest request, String reportType, HttpServletResponse response)
/*     */   {
/* 195 */     List list = new ArrayList();
/* 196 */     Highchart hc = new Highchart();
/* 197 */     StringBuffer sb = new StringBuffer();
/* 198 */     sb.append("SELECT broswer ,count(broswer) FROM TSLog group by broswer");
/* 199 */     List userBroswerList = this.systemService.findByQueryString(sb.toString());
/* 200 */     Long count = this.systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_Log WHERE 1=1");
/* 201 */     List lt = new ArrayList();
/* 202 */     hc = new Highchart();
/*     */ 
/* 204 */     hc.setName(MutiLangUtil.getMutiLangInstance().getLang("user.browser.analysis"));
/*     */ 
/* 206 */     hc.setType(reportType);
/*     */ 
/* 208 */     if (userBroswerList.size() > 0) {
/* 209 */       for (Iterator localIterator = userBroswerList.iterator(); localIterator.hasNext(); ) { Object object = localIterator.next();
/* 210 */         Map map = new HashMap();
/* 211 */         Object[] obj = (Object[])object;
/* 212 */         map.put("name", obj[0]);
/* 213 */         map.put("y", obj[1]);
/* 214 */         Long groupCount = (Long)obj[1];
/* 215 */         Double percentage = Double.valueOf(0.0D);
/* 216 */         if ((count != null) && (count.intValue() != 0)) {
/* 217 */           percentage = Double.valueOf(new Double(groupCount.longValue()).doubleValue() / count.longValue());
/*     */         }
/* 219 */         map.put("percentage", Double.valueOf(percentage.doubleValue() * 100.0D));
/* 220 */         lt.add(map);
/*     */       }
/*     */     }
/* 223 */     hc.setData(lt);
/* 224 */     list.add(hc);
/* 225 */     return list;
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"export"})
/*     */   public void export(HttpServletRequest request, HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/* 238 */     request.setCharacterEncoding("utf-8");
/* 239 */     response.setCharacterEncoding("utf-8");
/* 240 */     String type = request.getParameter("type");
/* 241 */     String svg = request.getParameter("svg");
/* 242 */     String filename = request.getParameter("filename");
/*     */ 
/* 244 */     filename = filename == null ? "chart" : filename;
/* 245 */     ServletOutputStream out = response.getOutputStream();
/*     */     try {
/* 247 */       if ((type != null) && (svg != null)) {
/* 248 */         svg = svg.replaceAll(":rect", "rect");
/* 249 */         String ext = "";
/* 250 */         Transcoder t = null;
/* 251 */         if (type.equals("image/png")) {
/* 252 */           ext = "png";
/* 253 */           t = new PNGTranscoder();
/* 254 */         } else if (type.equals("image/jpeg")) {
/* 255 */           ext = "jpg";
/* 256 */           t = new JPEGTranscoder();
/* 257 */         } else if (type.equals("application/pdf")) {
/* 258 */           ext = "pdf";
/* 259 */           t = new PDFTranscoder();
/* 260 */         } else if (type.equals("image/svg+xml")) {
/* 261 */           ext = "svg";
/* 262 */         }response.addHeader("Content-Disposition", 
/* 263 */           "attachment; filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1") + "." + ext);
/* 264 */         response.addHeader("Content-Type", type);
/*     */ 
/* 266 */         if (t != null) {
/* 267 */           TranscoderInput input = new TranscoderInput(
/* 268 */             new StringReader(svg));
/* 269 */           TranscoderOutput output = new TranscoderOutput(out);
/*     */           try
/*     */           {
/* 272 */             t.transcode(input, output);
/*     */           } catch (TranscoderException e) {
/* 274 */             out.print("Problem transcoding stream. See the web logs for more details.");
/* 275 */             e.printStackTrace();
/*     */           }
/* 277 */         } else if (ext.equals("svg"))
/*     */         {
/* 279 */           OutputStreamWriter writer = new OutputStreamWriter(out, 
/* 280 */             "UTF-8");
/* 281 */           writer.append(svg);
/* 282 */           writer.close();
/*     */         } else {
/* 284 */           out.print("Invalid type: " + type);
/*     */         }
/*     */       } else { response.addHeader("Content-Type", "text/html");
/* 287 */         out
/* 288 */           .println("Usage:\n\tParameter [svg]: The DOM Element to be converted.\n\tParameter [type]: The destination MIME type for the elment to be transcoded."); }
/*     */     }
/*     */     finally
/*     */     {
/* 292 */       if (out != null) {
/* 293 */         out.flush();
/* 294 */         out.close();
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.web.system.controller.core.LogController
 * JD-Core Version:    0.6.2
 */