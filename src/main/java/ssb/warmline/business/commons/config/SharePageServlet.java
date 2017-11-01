/*    */ package ssb.warmline.business.commons.config;
/*    */ 
/*    */ import com.alibaba.druid.support.json.JSONUtils;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import net.sf.json.JSONObject;
/*    */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.web.context.support.WebApplicationContextUtils;
/*    */ import ssb.warmline.business.commons.utils.CommonUtils;
/*    */ import ssb.warmline.business.entity.worder.WOrderEntity;
/*    */ import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
/*    */ import ssb.warmline.business.entity.wversionupdatemanagement.WVersionUpdateManagementEntity;
/*    */ import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
/*    */ import ssb.warmline.business.service.worder.WOrderServiceI;
/*    */ import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;
/*    */ import ssb.warmline.business.service.wversionupdatemanagement.WVersionUpdateManagementServiceI;
/*    */ 
/*    */ public class SharePageServlet extends HttpServlet
/*    */ {
/* 39 */   protected static final Logger log = LoggerFactory.getLogger(SharePageServlet.class);
/*    */ 
/* 41 */   private ApplicationContext context = null;
/*    */ 
/*    */   public void init() throws ServletException
/*    */   {
/* 45 */     this.context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
/*    */   }
/*    */ 
/*    */   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 50 */     doPost(req, resp);
/*    */   }
/*    */ 
/*    */   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
/*    */   {
/* 55 */     sharePageOrderDetails(req, resp);
/*    */   }
/*    */ 
/*    */   public void sharePageOrderDetails(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/*    */     try
/*    */     {
/* 68 */       WOrderPhotoMainServiceI wOrderPhotoMainService = (WOrderPhotoMainServiceI)this.context.getBean(WOrderPhotoMainServiceI.class);
/* 69 */       WOrderServiceI wOrderService = (WOrderServiceI)this.context.getBean(WOrderServiceI.class);
/* 70 */       TSBaseUserServiceI tSBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
/* 71 */       WVersionUpdateManagementServiceI wVersionUpdateManagementService = (WVersionUpdateManagementServiceI)this.context.getBean(WVersionUpdateManagementServiceI.class);
/* 72 */       String callback = request.getParameter("callback");
/* 73 */       String uid = request.getParameter("uid");
/* 74 */       String token = request.getParameter("token");
/*    */ 
/* 77 */       String orderId = request.getParameter("orderId");
/* 78 */       String deviceToken = request.getParameter("deviceToken");
/* 79 */       Map jsonMap = new HashMap();
/* 80 */       ResponseObject object = null;
/* 81 */       WOrderEntity orderEntity = (WOrderEntity)wOrderService.findUniqueByProperty(WOrderEntity.class, "id", orderId);
/* 82 */       if (orderEntity == null) {
/* 83 */         jsonMap.put("20025", "没有查询到当前订单!");
/* 84 */         String json = JSONUtils.toJSONString(jsonMap);
/* 85 */         if (callback == null) {
/* 86 */           response.setContentType("text/html");
/* 87 */           response.setCharacterEncoding("UTF-8");
/* 88 */           response.getWriter().write(json);
/* 89 */           response.getWriter().flush();
/* 90 */           response.getWriter().close();
/*    */         } else {
/* 92 */           response.setContentType("text/html");
/* 93 */           response.setCharacterEncoding("UTF-8");
/* 94 */           response.getWriter().write(callback + "(" + json + ")");
/* 95 */           response.getWriter().flush();
/* 96 */           response.getWriter().close();
/*    */         }
/*    */       }
/* 99 */       TSBaseUser tsBaseUsers = (TSBaseUser)tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
/* 100 */       if (tsBaseUsers != null)
/*    */       {
/* 102 */         StringBuffer url = request.getRequestURL();
/* 103 */         String tempContextUrl = request.getServletContext().getContextPath();
/* 104 */         List orderList = new ArrayList();
/* 105 */         Map map = new HashMap();
/* 106 */         map.put("seekStatus", orderEntity.getSeekStatus());
/* 107 */         map.put("price", orderEntity.getPrice());
/*    */ 
/* 110 */         TSBaseUser tsBaseUser = (TSBaseUser)tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", orderEntity.getIssuerId());
/* 111 */         if ((tsBaseUser == null) || ("".equals(tsBaseUser))) {
/* 112 */           CommonUtils.repsonseOjbectToClientWithBody("20043", null, new String[0]);
/* 113 */           jsonMap.put("20043", "发单人用户不存在！");
/* 114 */           String json = JSONUtils.toJSONString(jsonMap);
/* 115 */           if (callback == null) {
/* 116 */             response.setContentType("text/html");
/* 117 */             response.setCharacterEncoding("UTF-8");
/* 118 */             response.getWriter().write(json);
/* 119 */             response.getWriter().flush();
/* 120 */             response.getWriter().close();
/*    */           } else {
/* 122 */             response.setContentType("text/html");
/* 123 */             response.setCharacterEncoding("UTF-8");
/* 124 */             response.getWriter().write(callback + "(" + json + ")");
/* 125 */             response.getWriter().flush();
/* 126 */             response.getWriter().close();
/*    */           }
/*    */         }
/* 129 */         if ((tsBaseUser.getHeadPortrait() == null) || ("".equals(tsBaseUser.getHeadPortrait())))
/* 130 */           map.put("HeadPortrait", null);
/*    */         else {
/* 132 */           map.put("HeadPortrait", tempContextUrl + tsBaseUser.getHeadPortrait());
/*    */         }
/*    */ 
/* 135 */         if ((tsBaseUser.getUserName() == null) || ("".equals(tsBaseUser.getUserName())))
/* 136 */           map.put("userName", null);
/*    */         else {
/* 138 */           map.put("userName", tsBaseUser.getUserName());
/*    */         }
/* 140 */         map.put("issuerId", orderEntity.getIssuerId());
/*    */ 
/* 151 */         List photoList = new ArrayList();
/* 152 */         List findByProperty = wOrderPhotoMainService.findByProperty(WOrderPhotoMainEntity.class, "orderId", orderEntity.getId());
/* 153 */         if (findByProperty.size() > 0) {
/* 154 */           for (int a1 = 0; a1 < findByProperty.size(); a1++) {
/* 155 */             WOrderPhotoMainEntity bmg1 = (WOrderPhotoMainEntity)findByProperty.get(a1);
/* 156 */             JSONObject jsonObject = new JSONObject();
/* 157 */             jsonObject.put("photo", tempContextUrl + bmg1.getPhotoUrl());
/* 158 */             photoList.add(jsonObject);
/*    */           }
/* 160 */           map.put("orderPhoto", photoList);
/*    */         } else {
/* 162 */           map.put("orderPhoto", null);
/*    */         }
/*    */ 
/* 165 */         if (tsBaseUsers.getId().equals(orderEntity.getIssuerId()))
/* 166 */           map.put("orderPerson", "own");
/*    */         else {
/* 168 */           map.put("orderPerson", "others");
/*    */         }
/* 170 */         map.put("certification", tsBaseUser.getCertification());
/* 171 */         map.put("orderid", orderEntity.getId());
/* 172 */         map.put("city", orderEntity.getCity());
/* 173 */         map.put("state", orderEntity.getState());
/* 174 */         map.put("Category", orderEntity.getCategory());
/* 175 */         if ("1".equals(orderEntity.getSeekStatus()))
/*    */         {
/* 177 */           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 178 */           String StartTime = dateFormat.format(orderEntity.getStartTime());
/* 179 */           String EndTime = dateFormat.format(orderEntity.getEndTime());
/* 180 */           map.put("time", StartTime + " -- " + EndTime);
/* 181 */         } else if ("0".equals(orderEntity.getSeekStatus())) {
/* 182 */           map.put("time", orderEntity.getStartTime() + " -- " + orderEntity.getEndTime());
/*    */         }
/* 184 */         map.put("location", orderEntity.getLocation());
/* 185 */         map.put("title", orderEntity.getTitle());
/*    */ 
/* 187 */         map.put("subtitle", orderEntity.getSubtitle());
/* 188 */         map.put("description", orderEntity.getDescription());
/* 189 */         map.put("phone", orderEntity.getPhone());
/*    */ 
/* 191 */         Long issuerCount = wOrderService
/* 192 */           .getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where issuer_id = '" + uid + 
/* 193 */           "' AND buy_status='PAY_002'  ");
/* 194 */         map.put("issuerCount", issuerCount);
/* 195 */         Long helepCount = wOrderService
/* 196 */           .getCountForJdbc("SELECT IFNULL(COUNT(id),0) from w_order where order_person_id = '" + uid + 
/* 197 */           "'  AND order_status in ('ORDERSTATU_003','ORDERSTATU_004','ORDERSTATU_006') ");
/* 198 */         map.put("helepCount", helepCount);
/* 199 */         orderList.add(map);
/* 200 */         jsonMap.put("orderList", orderList);
/* 201 */         jsonMap.put("uid", uid);
/* 202 */         jsonMap.put("token", token);
/*    */ 
/* 204 */         if ("Android".equals(deviceToken)) {
/* 205 */           WVersionUpdateManagementEntity versionUpdate = (WVersionUpdateManagementEntity)wVersionUpdateManagementService.findUniqueByProperty(WVersionUpdateManagementEntity.class, "currentField", "true");
/* 206 */           if (versionUpdate != null)
/* 207 */             jsonMap.put("downLoadUrl", versionUpdate.getUploadUrl());
/*    */         }
/* 209 */         else if ("IOS".equals(deviceToken)) {
/* 210 */           jsonMap.put("downLoadUrl", "https://itunes.apple.com/us/app/暖行-生活出行互助平台/id1272027940?l=zh&ls=1&mt=8");
/*    */         }
/* 212 */         String json = JSONUtils.toJSONString(jsonMap);
/* 213 */         if (callback == null) {
/* 214 */           response.setContentType("text/html");
/* 215 */           response.setCharacterEncoding("UTF-8");
/* 216 */           response.getWriter().write(json);
/* 217 */           response.getWriter().flush();
/* 218 */           response.getWriter().close();
/*    */         } else {
/* 220 */           response.setContentType("text/html");
/* 221 */           response.setCharacterEncoding("UTF-8");
/* 222 */           response.getWriter().write(callback + "(" + json + ")");
/* 223 */           response.getWriter().flush();
/* 224 */           response.getWriter().close();
/*    */         }
/*    */       }
/*    */       else {
/* 228 */         jsonMap.put("20012", "用户为空,出现错误！");
/* 229 */         String json = JSONUtils.toJSONString(jsonMap);
/* 230 */         if (callback == null) {
/* 231 */           response.setContentType("text/html");
/* 232 */           response.setCharacterEncoding("UTF-8");
/*    */ 
/* 234 */           response.getWriter().write(json);
/*    */ 
/* 236 */           response.getWriter().flush();
/* 237 */           response.getWriter().close();
/*    */         } else {
/* 239 */           response.setContentType("text/html");
/* 240 */           response.setCharacterEncoding("UTF-8");
/* 241 */           response.getWriter().write(callback + "(" + json + ")");
/* 242 */           response.getWriter().flush();
/* 243 */           response.getWriter().close();
/*    */         }
/*    */       }
/*    */     } catch (IOException e) {
/* 247 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.config.SharePageServlet
 * JD-Core Version:    0.6.2
 */