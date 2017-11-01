/*     */ package ssb.warmline.business.controller.areaagencybrokerage;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.jeecgframework.core.common.model.json.DataGrid;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRole;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRoleUser;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*     */ import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
/*     */ import ssb.warmline.business.service.wcommission.WCommissionServiceI;
/*     */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*     */ import ssb.warmline.business.utils.PropertiesUtil;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/AreaAgencyBrokerage"})
/*     */ public class AreaAgencyBrokerageController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*     */ 
/*     */   @Autowired
/*     */   private WOrderServiceI wOrderService;
/*     */ 
/*     */   @Autowired
/*     */   private WCommissionServiceI wCommissionService;
/*     */ 
/*     */   @RequestMapping(params={"list"})
/*     */   public ModelAndView list(HttpServletRequest request, DataGrid dataGrid)
/*     */   {
/*  54 */     HttpSession session = request.getSession();
/*  55 */     TSUser currentUser = ResourceUtil.getSessionUserName();
/*  56 */     List roleUsers = new ArrayList();
/*  57 */     String id = PropertiesUtil.getProperties();
/*  58 */     roleUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", currentUser.getId());
/*  59 */     TSRoleUser tsRU = (TSRoleUser)roleUsers.get(0);
/*  60 */     TSRole role = tsRU.getTSRole();
/*     */ 
/*  63 */     if ("297e47fc5b8ea693015b8ea8c1690001".equals(role.getId())) {
/*  64 */       String sql = "SELECT IFNULL(SUM(wc.amount),0) AS amount FROM w_commission wc left JOIN w_order wo ON wc.order_Number=wo.order_Number and wo.is_virtual_user is null ";
/*  65 */       String totalSalary = String.valueOf(this.systemService.findOneForJdbc(sql, new Object[0]).get("amount"));
/*  66 */       Double cny = Double.valueOf(Double.parseDouble(totalSalary));
/*     */ 
/*  68 */       DecimalFormat df = new DecimalFormat("0.00");
/*  69 */       String CNY = df.format(cny.doubleValue() * 0.1D);
/*  70 */       request.setAttribute("totalSalary", CNY);
/*     */ 
/*  72 */       Long orderCount = this.systemService.getCountForJdbc("SELECT count(id) FROM  w_commission");
/*  73 */       request.setAttribute("number", orderCount.toString());
/*     */ 
/*  75 */       Long totalNumber = this.systemService.getCountForJdbc("SELECT count(id) FROM  w_order where is_virtual_user is null");
/*  76 */       request.setAttribute("totalNumber", totalNumber.toString());
/*  77 */       request.setAttribute("territoryName", "全部");
/*  78 */       request.setAttribute("territoryId", "");
/*     */     } else {
/*  80 */       String territoryId = currentUser.getTerritoryId();
/*     */ 
/*  82 */       String sql = "SELECT IFNULL(SUM(wc.amount),0) AS amount FROM w_commission wc LEFT JOIN w_order wo ON wc.order_Number = wo.order_Number  LEFT JOIN w_territory wt ON wo.territory_id = wt.id WHERE wo.territory_id = '" + 
/*  83 */         territoryId + "' and wo.is_virtual_user is null ";
/*  84 */       String totalSalary = String.valueOf(this.systemService.findOneForJdbc(sql, new Object[0]).get("amount"));
/*  85 */       Double cny = Double.valueOf(Double.parseDouble(totalSalary));
/*  86 */       DecimalFormat df = new DecimalFormat("0.00");
/*  87 */       String CNY = df.format(cny.doubleValue() * 0.1D);
/*  88 */       request.setAttribute("totalSalary", CNY);
/*     */ 
/*  90 */       String sql1 = "select count(wo.id)  from  w_commission wc LEFT JOIN w_order wo ON wc.order_Number = wo.order_Number LEFT JOIN w_territory wt ON wo.territory_id = wt.id WHERE wo.territory_id = '" + 
/*  91 */         territoryId + "'  and wo.is_virtual_user is null ";
/*     */ 
/*  93 */       Long findByQueryCount = this.systemService.getCountForJdbc(sql1);
/*  94 */       request.setAttribute("number", findByQueryCount.toString());
/*     */ 
/*  97 */       Long totalNumber = this.systemService.getCountForJdbc("SELECT count(id) FROM  w_order where territory_id='" + territoryId + "' and is_virtual_user is null");
/*  98 */       request.setAttribute("totalNumber", totalNumber.toString());
/*     */ 
/* 100 */       List listTerritory = this.systemService.findByProperty(WTerritoryBusiness.class, "id", territoryId);
/* 101 */       if ((listTerritory != null) && (listTerritory.size() > 0)) {
/* 102 */         WTerritoryBusiness territory = (WTerritoryBusiness)listTerritory.get(0);
/* 103 */         String territoryName = String.valueOf(territory.getTerritoryname());
/* 104 */         request.setAttribute("territoryName", territoryName);
/*     */       } else {
/* 106 */         request.setAttribute("territoryName", "-");
/*     */       }
/* 108 */       request.setAttribute("territoryId", territoryId);
/*     */ 
/* 110 */       session.setAttribute("territoryId", territoryId);
/*     */     }
/* 112 */     return new ModelAndView("ssb/warmline/business/areaagencybrokerage/allOrderAndBrokerageList");
/*     */   }
/*     */   @RequestMapping(params={"findOrder"})
/*     */   public String findOrder(HttpServletRequest request) {
/* 116 */     HttpSession session = request.getSession();
/* 117 */     String territoryId = request.getParameter("territoryId");
/* 118 */     session.setAttribute("territoryId", territoryId);
/* 119 */     return "ssb/warmline/business/areaagencybrokerage/findOrderList";
/*     */   }
/*     */ 
/*     */   @RequestMapping(params={"AreaAgencyBrokerageDatagrid"})
/*     */   public void AreaAgencyBrokerageDatagrid(WOrderEntity wOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid)
/*     */   {
/* 125 */     HttpSession session = request.getSession();
/*     */ 
/* 134 */     String territoryId = (String)session.getAttribute("territoryId");
/*     */ 
/* 138 */     JSONObject jObject = this.wOrderService.findAllOrderAndBrokerage(wOrder, dataGrid, territoryId, wOrder.getOrderNumber(), wOrder.getIssuer(), wOrder.getSeekStatus());
/* 139 */     responseDatagrid(response, jObject);
/*     */   }
/*     */ 
/*     */   public void responseDatagrid(HttpServletResponse response, JSONObject jObject)
/*     */   {
/* 147 */     response.setContentType("application/json");
/* 148 */     response.setHeader("Cache-Control", "no-store");
/* 149 */     PrintWriter pw = null;
/*     */     try {
/* 151 */       pw = response.getWriter();
/* 152 */       pw.write(jObject.toString());
/* 153 */       pw.flush();
/*     */     } catch (IOException e) {
/* 155 */       e.printStackTrace();
/*     */       try
/*     */       {
/* 158 */         pw.close(); } catch (Exception localException) {  } } finally { try { pw.close(); }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.controller.areaagencybrokerage.AreaAgencyBrokerageController
 * JD-Core Version:    0.6.2
 */