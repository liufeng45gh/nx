/*     */ package org.jeecgframework.core.util;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.jeecgframework.web.system.manager.ClientManager;
/*     */ import org.jeecgframework.web.system.pojo.base.Client;
/*     */ import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
/*     */ import org.jeecgframework.web.system.pojo.base.TSIcon;
/*     */ import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
/*     */ import org.jeecgframework.web.system.pojo.base.TSType;
/*     */ import org.jeecgframework.web.system.pojo.base.TSTypegroup;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import ssb.warmline.business.commons.utils.DateUtils;
/*     */ 
/*     */ public class ResourceUtil
/*     */ {
/*     */   public static final String LOCAL_CLINET_USER = "LOCAL_CLINET_USER";
/*  35 */   public static Map<String, TSTypegroup> allTypeGroups = new HashMap();
/*     */ 
/*  39 */   public static Map<String, List<TSType>> allTypes = new HashMap();
/*     */ 
/*  44 */   public static Map<String, String> mutiLangMap = new HashMap();
/*     */ 
/*  48 */   public static Map<String, TSIcon> allTSIcons = new HashMap();
/*     */ 
/*  52 */   public static Map<String, DynamicDataSourceEntity> dynamicDataSourceMap = new HashMap();
/*     */ 
/*  54 */   private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");
/*     */ 
/*     */   public static final String getSessionattachmenttitle(String sessionName)
/*     */   {
/*  71 */     return bundle.getString(sessionName);
/*     */   }
/*     */   public static final TSUser getSessionUserName() {
/*  74 */     HttpSession session = ContextHolderUtils.getSession();
/*  75 */     if (ClientManager.getInstance().getClient(session.getId()) != null) {
/*  76 */       return ClientManager.getInstance().getClient(session.getId()).getUser();
/*     */     }
/*     */ 
/*  79 */     TSUser u = (TSUser)session.getAttribute("LOCAL_CLINET_USER");
/*  80 */     Client client = new Client();
/*  81 */     client.setIp("");
/*  82 */     client.setLogindatetime(new Date());
/*  83 */     client.setUser(u);
/*  84 */     ClientManager.getInstance().addClinet(session.getId(), client);
/*     */ 
/*  87 */     return null;
/*     */   }
/*     */   @Deprecated
/*     */   public static final List<TSRoleFunction> getSessionTSRoleFunction(String roleId) {
/*  91 */     HttpSession session = ContextHolderUtils.getSession();
/*  92 */     if (session.getAttributeNames().hasMoreElements()) {
/*  93 */       List TSRoleFunctionList = (List)session.getAttribute(roleId);
/*  94 */       if (TSRoleFunctionList != null) {
/*  95 */         return TSRoleFunctionList;
/*     */       }
/*  97 */       return null;
/*     */     }
/*     */ 
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getRequestPath(HttpServletRequest request)
/*     */   {
/* 111 */     String requestPath = request.getRequestURI() + "?" + request.getQueryString();
/* 112 */     if (requestPath.indexOf("&") > -1) {
/* 113 */       requestPath = requestPath.substring(0, requestPath.indexOf("&"));
/*     */     }
/* 115 */     requestPath = requestPath.substring(request.getContextPath().length() + 1);
/* 116 */     return requestPath;
/*     */   }
/*     */ 
/*     */   public static final String getConfigByName(String name)
/*     */   {
/* 126 */     return bundle.getString(name);
/*     */   }
/*     */ 
/*     */   public static final Map<Object, Object> getConfigMap(String path)
/*     */   {
/* 136 */     ResourceBundle bundle = ResourceBundle.getBundle(path);
/* 137 */     Set set = bundle.keySet();
/* 138 */     return oConvertUtils.SetToMap(set);
/*     */   }
/*     */ 
/*     */   public static String getSysPath()
/*     */   {
/* 144 */     String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
/* 145 */     String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
/* 146 */     String separator = System.getProperty("file.separator");
/* 147 */     String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
/* 148 */     return resultPath;
/*     */   }
/*     */ 
/*     */   public static String getPorjectPath()
/*     */   {
/* 160 */     String nowpath = System.getProperty("user.dir");
/* 161 */     String tempdir = nowpath.replace("bin", "webapps");
/* 162 */     tempdir = tempdir + "\\";
/* 163 */     return tempdir;
/*     */   }
/*     */ 
/*     */   public static String getClassPath() {
/* 167 */     String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
/* 168 */     String temp = path.replaceFirst("file:/", "");
/* 169 */     String separator = System.getProperty("file.separator");
/* 170 */     String resultPath = temp.replaceAll("/", separator + separator);
/* 171 */     return resultPath;
/*     */   }
/*     */ 
/*     */   public static String getSystempPath() {
/* 175 */     return System.getProperty("java.io.tmpdir");
/*     */   }
/*     */ 
/*     */   public static String getSeparator() {
/* 179 */     return System.getProperty("file.separator");
/*     */   }
/*     */ 
/*     */   public static String getParameter(String field) {
/* 183 */     HttpServletRequest request = ContextHolderUtils.getRequest();
/* 184 */     return request.getParameter(field);
/*     */   }
/*     */ 
/*     */   public static final String getJdbcUrl()
/*     */   {
/* 194 */     return DBTypeUtil.getDBType().toLowerCase();
/*     */   }
/*     */ 
/*     */   public static String getRandCodeLength()
/*     */   {
/* 203 */     return bundle.getString("randCodeLength");
/*     */   }
/*     */ 
/*     */   public static String getRandCodeType()
/*     */   {
/* 212 */     return bundle.getString("randCodeType");
/*     */   }
/*     */ 
/*     */   public static String getOrgCodeLengthType()
/*     */   {
/* 222 */     return bundle.getString("orgCodeLengthType");
/*     */   }
/*     */ 
/*     */   public static String getUserSystemData(String key)
/*     */   {
/* 232 */     String moshi = "";
/* 233 */     if (key.indexOf("}") != -1) {
/* 234 */       moshi = key.substring(key.indexOf("}") + 1);
/*     */     }
/* 236 */     String returnValue = null;
/*     */ 
/* 238 */     if (key.contains("#{"))
/* 239 */       key = key.substring(2, key.indexOf("}"));
/*     */     else {
/* 241 */       key = key;
/*     */     }
/*     */ 
/* 251 */     if ((key.equals("sysUserCode")) || 
/* 252 */       (key.equals("sys_user_code"))) {
/* 253 */       returnValue = getSessionUserName().getUserName();
/*     */     }
/*     */ 
/* 260 */     if ((key.equals("sysUserName")) || 
/* 261 */       (key.equals("sys_user_name")))
/*     */     {
/* 263 */       returnValue = getSessionUserName().getRealName();
/*     */     }
/*     */ 
/* 266 */     if ((key.equals("sysDate")) || (key.equals("sys_date"))) {
/* 267 */       returnValue = DateUtils.formatDate();
/*     */     }
/*     */ 
/* 270 */     if ((key.equals("sysTime")) || (key.equals("sys_time"))) {
/* 271 */       returnValue = DateUtils.formatTime();
/*     */     }
/* 273 */     if (returnValue != null) returnValue = returnValue + moshi;
/* 274 */     return returnValue;
/*     */   }
/*     */ 
/*     */   private static String getSessionData(String key)
/*     */   {
/* 286 */     String moshi = "";
/* 287 */     if (key.indexOf("}") != -1) {
/* 288 */       moshi = key.substring(key.indexOf("}") + 1);
/*     */     }
/* 290 */     String returnValue = null;
/*     */ 
/* 292 */     if (key.contains("#{")) {
/* 293 */       key = key.substring(2, key.indexOf("}"));
/*     */     }
/*     */ 
/* 296 */     if (!StringUtil.isEmpty(key)) {
/* 297 */       HttpSession session = ContextHolderUtils.getSession();
/* 298 */       returnValue = (String)session.getAttribute(key);
/*     */     }
/*     */ 
/* 303 */     if (returnValue != null) returnValue = returnValue + moshi;
/* 304 */     return returnValue;
/*     */   }
/*     */ 
/*     */   public static String converRuleValue(String ruleValue)
/*     */   {
/* 315 */     String value = getSessionData(ruleValue);
/* 316 */     if (StringUtil.isEmpty(value))
/* 317 */       value = getUserSystemData(ruleValue);
/* 318 */     return value != null ? value : ruleValue;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 323 */     LogUtil.info(getPorjectPath());
/* 324 */     LogUtil.info(getSysPath());
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.util.ResourceUtil
 * JD-Core Version:    0.6.2
 */