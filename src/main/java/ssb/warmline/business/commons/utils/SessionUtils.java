/*     */ package ssb.warmline.business.commons.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.commons.lang3.time.DateFormatUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.web.system.pojo.base.TSBaseUser;
/*     */ import org.springframework.web.context.request.RequestContextHolder;
/*     */ import org.springframework.web.context.request.ServletRequestAttributes;
/*     */ import ssb.warmline.business.commons.support.spring.data.redis.ObjectRedisSerializer;
/*     */ 
/*     */ public class SessionUtils
/*     */ {
/*     */   public static final String LOGIN_USER = "login_user";
/*     */   public static final String VALIDATE_CODE_SESSION_ID = "validate_code_session_id";
/*     */   public static final String OPEN_ID_IN_SESSION = "open_id_in_session";
/*     */   public static final String SUNSHINE_SESSION_ID = "sunshine_session_id";
/*  32 */   private static final Logger LOGGER = Logger.getLogger(SessionUtils.class);
/*     */ 
/*     */   public static HttpServletRequest getRequest() {
/*  35 */     ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
/*  36 */     if (servletRequestAttributes == null) {
/*  37 */       return null;
/*     */     }
/*  39 */     HttpServletRequest request = servletRequestAttributes.getRequest();
/*  40 */     return request;
/*     */   }
/*     */ 
/*     */   public static HttpSession getSession() {
/*  44 */     HttpServletRequest request = getRequest();
/*  45 */     if (request != null) {
/*  46 */       HttpSession session = request.getSession();
/*  47 */       return session;
/*     */     }
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   public static TSBaseUser getLoginUser(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  59 */     return (TSBaseUser)getSessionAttr(getUniqueKey(request, response) + "userId");
/*     */   }
/*     */ 
/*     */   public static void refreshLoginUser(HttpServletRequest request, HttpServletResponse response, TSBaseUser fUser)
/*     */   {
/*  70 */     if (fUser != null)
/*  71 */       setSessionAttr(getUniqueKey(request, response) + "userId", fUser);
/*     */     else
/*  73 */       removeSessionAttr(getUniqueKey(request, response) + "userId");
/*     */   }
/*     */ 
/*     */   public static void refreshValidateCode(String code)
/*     */   {
/*  84 */     if (StringUtils.isNotBlank(code))
/*  85 */       setSessionAttr("validate_code_session_id", code);
/*     */     else
/*  87 */       removeSessionAttr("validate_code_session_id");
/*     */   }
/*     */ 
/*     */   public static String getValidateCode()
/*     */   {
/*  98 */     return (String)getSessionAttr("validate_code_session_id");
/*     */   }
/*     */ 
/*     */   public static void refreshOpenId(HttpServletRequest request, HttpServletResponse response, String openid)
/*     */   {
/* 108 */     String host = getUniqueKey(request, response);
/* 109 */     System.out.println("微信授权成功后返回的【" + host + ":" + openid + "】");
/* 110 */     if (StringUtils.isNotBlank(openid))
/* 111 */       setSessionAttr(host, openid);
/*     */     else
/* 113 */       removeSessionAttr(host);
/*     */   }
/*     */ 
/*     */   public static String getUniqueKey(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 121 */     String key = WebUtil.getCookieValue(request, "a", request.getSession().getId());
/*     */ 
/* 123 */     WebUtil.setCookieValue(response, "a", key);
/* 124 */     return MD5Utils.MD5(key);
/*     */   }
/*     */ 
/*     */   public static void setSessionAttr(String key, Object value)
/*     */   {
/* 163 */     HttpSession session = getSession();
/* 164 */     if (session != null) {
/* 165 */       session.setAttribute(key, value);
/*     */ 
/* 167 */       ObjectRedisSerializer serializer = new ObjectRedisSerializer();
/*     */ 
/* 169 */       JedisUtil.setnx(key.getBytes(), serializer.serialize(value));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static <T> T getSessionAttr(String key)
/*     */   {
/* 182 */     HttpSession session = getSession();
/* 183 */     if (session != null) {
/* 184 */       Object attribute = session.getAttribute(key);
/*     */ 
/* 186 */       if ((attribute == null) && 
/* 187 */         (JedisUtil.exists(key.getBytes()).booleanValue())) {
/* 188 */         ObjectRedisSerializer serializer = new ObjectRedisSerializer();
/* 189 */         attribute = serializer.deserialize(JedisUtil.get(key.getBytes(), new Integer[0]));
/*     */       }
/*     */ 
/* 192 */       return (T) attribute;
/*     */     }
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */   public static void removeSessionAttr(String key)
/*     */   {
/* 202 */     System.out.println("当前内存中待删除的key为【" + key + "】");
/* 203 */     HttpSession session = getSession();
/* 204 */     if (session != null) {
/* 205 */       session.removeAttribute(key);
/*     */ 
/* 207 */       if (JedisUtil.exists(key.getBytes()).booleanValue())
/* 208 */         JedisUtil.del(key.getBytes());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void removeSessionAttr(String[] keys)
/*     */   {
/* 218 */     HttpSession session = getSession();
/* 219 */     String[] arrayOfString = keys; int j = keys.length; for (int i = 0; i < j; i++) { String key = arrayOfString[i];
/* 220 */       if (session != null)
/* 221 */         session.removeAttribute(key);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getContextPath()
/*     */   {
/* 236 */     return getRequest().getContextPath();
/*     */   }
/*     */ 
/*     */   public static String getSunshineSessionId(boolean isRefresh)
/*     */   {
/* 249 */     String sunshineSessionId = (String)getSessionAttr("sunshine_session_id");
/* 250 */     if (StringUtils.isEmpty(sunshineSessionId)) {
/* 251 */       setSessionAttr("sunshine_session_id", genSessionId());
/*     */     }
/* 254 */     else if (isRefresh) {
/* 255 */       setSessionAttr("sunshine_session_id", genSessionId());
/*     */     }
/*     */ 
/* 258 */     return (String)getSessionAttr("sunshine_session_id");
/*     */   }
/*     */ 
/*     */   private static String genSessionId()
/*     */   {
/* 270 */     DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
/* 271 */     int hashCode = java.util.UUID.randomUUID().toString().split("-")[0].hashCode();
/* 272 */     if (hashCode < 0) {
/* 273 */       hashCode = -hashCode;
/*     */     }
/* 275 */     String id = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + hashCode;
/* 276 */     return id;
/*     */   }
/*     */ 
/*     */   public static void setSessionTimeout(int timeout)
/*     */   {
/* 281 */     HttpSession session = getSession();
/* 282 */     if (session != null)
/* 283 */       session.setMaxInactiveInterval(timeout);
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.SessionUtils
 * JD-Core Version:    0.6.2
 */