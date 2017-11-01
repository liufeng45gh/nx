/*     */ package ssb.warmline.business.commons.support.login;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import org.apache.commons.httpclient.NameValuePair;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import ssb.warmline.business.commons.utils.HttpUtil;
/*     */ 
/*     */ public final class ThirdPartyLoginHelper
/*     */ {
/*  20 */   public static final ResourceBundle THIRDPARTY = ResourceBundle.getBundle("thirdParty");
/*     */ 
/*     */   public static final ThirdPartyUser getQQUserinfo(String token, String openid)
/*     */     throws Exception
/*     */   {
/*  28 */     ThirdPartyUser user = new ThirdPartyUser();
/*  29 */     String url = THIRDPARTY.getString("getUserInfoURL_qq");
/*  30 */     url = url + "?format=json&access_token=" + token + "&oauth_consumer_key=" + 
/*  31 */       THIRDPARTY.getString("app_id_qq") + "&openid=" + openid;
/*  32 */     String res = HttpUtil.httpClientPost(url);
/*  33 */     JSONObject json = JSONObject.parseObject(res);
/*  34 */     if (json.getIntValue("ret") == 0) {
/*  35 */       user.setUserName(json.getString("nickname"));
/*  36 */       String img = json.getString("figureurl_qq_2");
/*  37 */       if ((img == null) || ("".equals(img))) {
/*  38 */         img = json.getString("figureurl_qq_1");
/*     */       }
/*  40 */       user.setAvatarUrl(img);
/*  41 */       String sex = json.getString("gender");
/*  42 */       if ("女".equals(sex))
/*  43 */         user.setGender("0");
/*     */       else
/*  45 */         user.setGender("1");
/*     */     }
/*     */     else {
/*  48 */       throw new IllegalArgumentException(json.getString("msg"));
/*     */     }
/*  50 */     return user;
/*     */   }
/*     */ 
/*     */   public static final ThirdPartyUser getWxUserinfo(String token, String openid) throws Exception
/*     */   {
/*  55 */     ThirdPartyUser user = new ThirdPartyUser();
/*  56 */     String url = THIRDPARTY.getString("getUserInfoURL_wx");
/*  57 */     url = url + "?access_token=" + token + "&openid=" + openid;
/*  58 */     String res = HttpUtil.httpClientPost(url);
/*  59 */     JSONObject json = JSONObject.parseObject(res);
/*  60 */     if (json.getString("errcode") == null) {
/*  61 */       user.setUserName(json.getString("nickname"));
/*  62 */       String img = json.getString("headimgurl");
/*  63 */       if ((img != null) && (!"".equals(img))) {
/*  64 */         user.setAvatarUrl(img);
/*     */       }
/*  66 */       String sex = json.getString("sex");
/*  67 */       if ("0".equals(sex))
/*  68 */         user.setGender("0");
/*     */       else
/*  70 */         user.setGender("1");
/*     */     }
/*     */     else {
/*  73 */       throw new IllegalArgumentException(json.getString("errmsg"));
/*     */     }
/*  75 */     return user;
/*     */   }
/*     */ 
/*     */   public static final ThirdPartyUser getSinaUserinfo(String token, String uid)
/*     */     throws Exception
/*     */   {
/*  86 */     ThirdPartyUser user = new ThirdPartyUser();
/*  87 */     String url = THIRDPARTY.getString("getUserInfoURL_sina");
/*  88 */     url = url + "?access_token=" + token + "&uid=" + uid;
/*  89 */     String res = HttpUtil.httpClientPost(url);
/*  90 */     JSONObject json = JSONObject.parseObject(res);
/*  91 */     String name = json.getString("name");
/*  92 */     String nickName = StringUtils.isBlank(json.getString("screen_name")) ? name : json.getString("screen_name");
/*  93 */     user.setAvatarUrl(json.getString("avatar_large"));
/*  94 */     user.setUserName(nickName);
/*  95 */     if ("f".equals(json.getString("gender")))
/*  96 */       user.setGender("0");
/*     */     else {
/*  98 */       user.setGender("1");
/*     */     }
/* 100 */     user.setToken(token);
/* 101 */     user.setOpenid(uid);
/* 102 */     user.setProvider("sina");
/* 103 */     return user;
/*     */   }
/*     */ 
/*     */   public static final Map<String, String> getQQTokenAndOpenid(String code, String host)
/*     */     throws Exception
/*     */   {
/* 114 */     Map map = new HashMap();
/*     */ 
/* 116 */     String tokenUrl = THIRDPARTY.getString("accessTokenURL_qq");
/* 117 */     tokenUrl = tokenUrl + "?grant_type=authorization_code&client_id=" + THIRDPARTY.getString("app_id_qq") + 
/* 118 */       "&client_secret=" + THIRDPARTY.getString("app_key_qq") + "&code=" + code + 
/* 119 */       "&redirect_uri=http://" + host + THIRDPARTY.getString("redirect_url_qq");
/* 120 */     String tokenRes = HttpUtil.httpClientPost(tokenUrl);
/* 121 */     if ((tokenRes != null) && (tokenRes.indexOf("access_token") > -1)) {
/* 122 */       Map tokenMap = toMap(tokenRes);
/* 123 */       map.put("access_token", (String)tokenMap.get("access_token"));
/*     */ 
/* 125 */       String openIdUrl = THIRDPARTY.getString("getOpenIDURL_qq");
/* 126 */       openIdUrl = openIdUrl + "?access_token=" + (String)tokenMap.get("access_token");
/* 127 */       String openIdRes = HttpUtil.httpClientPost(openIdUrl);
/* 128 */       int i = openIdRes.indexOf("(");
/* 129 */       int j = openIdRes.indexOf(")");
/* 130 */       openIdRes = openIdRes.substring(i + 1, j);
/* 131 */       JSONObject openidObj = JSONObject.parseObject(openIdRes);
/* 132 */       map.put("openId", openidObj.getString("openid"));
/*     */     } else {
/* 134 */       throw new IllegalArgumentException("没有获取QQ登录令牌");
/*     */     }
/* 136 */     return map;
/*     */   }
/*     */ 
/*     */   public static final Map<String, String> getWxTokenAndOpenid(String code, String host)
/*     */     throws Exception
/*     */   {
/* 147 */     Map map = new HashMap();
/*     */ 
/* 149 */     String tokenUrl = THIRDPARTY.getString("accessTokenURL_wx");
/* 150 */     tokenUrl = tokenUrl + "?appid=" + THIRDPARTY.getString("app_id_wx") + "&secret=" + 
/* 151 */       THIRDPARTY.getString("app_key_wx") + "&code=" + code + "&grant_type=authorization_code";
/* 152 */     String tokenRes = HttpUtil.httpClientPost(tokenUrl);
/* 153 */     if ((tokenRes != null) && (tokenRes.indexOf("access_token") > -1)) {
/* 154 */       Map tokenMap = toMap(tokenRes);
/* 155 */       map.put("access_token", (String)tokenMap.get("access_token"));
/*     */ 
/* 157 */       map.put("openId", (String)tokenMap.get("openid"));
/*     */     } else {
/* 159 */       throw new IllegalArgumentException("没有获取微信登录令牌");
/*     */     }
/* 161 */     return map;
/*     */   }
/*     */ 
/*     */   public static final JSONObject getSinaTokenAndUid(String code, String host)
/*     */   {
/* 172 */     JSONObject json = null;
/*     */     try
/*     */     {
/* 175 */       String tokenUrl = THIRDPARTY.getString("accessTokenURL_sina");
/* 176 */       ArrayList list = new ArrayList();
/* 177 */       NameValuePair params1 = new NameValuePair();
/* 178 */       params1.setName("client_id");
/* 179 */       params1.setValue(THIRDPARTY.getString("app_id_sina"));
/* 180 */       list.add(params1);
/* 181 */       NameValuePair params2 = new NameValuePair();
/* 182 */       params2.setName("client_secret");
/* 183 */       params2.setValue(THIRDPARTY.getString("app_key_sina"));
/* 184 */       list.add(params2);
/* 185 */       NameValuePair params3 = new NameValuePair();
/* 186 */       params3.setName("grant_type");
/* 187 */       params3.setValue("authorization_code");
/* 188 */       list.add(params3);
/* 189 */       NameValuePair params4 = new NameValuePair();
/* 190 */       params4.setName("redirect_uri");
/* 191 */       params4.setValue("http://" + host + THIRDPARTY.getString("redirect_url_sina"));
/* 192 */       list.add(params4);
/* 193 */       NameValuePair params5 = new NameValuePair();
/* 194 */       params5.setName("code");
/* 195 */       params5.setValue(code);
/* 196 */       list.add(params5);
/* 197 */       String tokenRes = HttpUtil.httpClientPost(tokenUrl, list);
/*     */ 
/* 200 */       if ((tokenRes != null) && (tokenRes.indexOf("access_token") > -1))
/* 201 */         json = JSONObject.parseObject(tokenRes);
/*     */       else
/* 203 */         throw new IllegalArgumentException("没有获取新浪登录令牌");
/*     */     }
/*     */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/*     */     }
/* 208 */     return json;
/*     */   }
/*     */ 
/*     */   private static final Map<String, String> toMap(String str)
/*     */   {
/* 218 */     Map map = new HashMap();
/* 219 */     String[] strs = str.split("&");
/* 220 */     for (int i = 0; i < strs.length; i++) {
/* 221 */       String[] ss = strs[i].split("=");
/* 222 */       map.put(ss[0], ss[1]);
/*     */     }
/* 224 */     return map;
/*     */   }
/*     */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.support.login.ThirdPartyLoginHelper
 * JD-Core Version:    0.6.2
 */