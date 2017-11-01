/*    */ package ssb.warmline.business.commons.utils.JMessage;
/*    */ 
/*    */ import cn.jiguang.common.ServiceHelper;
/*    */ import cn.jiguang.common.connection.HttpProxy;
/*    */ import cn.jiguang.common.connection.NativeHttpClient;
/*    */ import com.google.gson.Gson;
/*    */ 
/*    */ public class BaseClient
/*    */ {
/*    */   protected final NativeHttpClient _httpClient;
/*    */   protected String _baseUrl;
/* 12 */   protected Gson _gson = new Gson();
/*    */ 
/*    */   public BaseClient(String appkey, String masterSecret, HttpProxy proxy, JMessageConfig config)
/*    */   {
/* 22 */     ServiceHelper.checkBasic(appkey, masterSecret);
/* 23 */     String authCode = ServiceHelper.getBasicAuthorization(appkey, masterSecret);
/* 24 */     this._baseUrl = ((String)config.get("im.api.host.name"));
/* 25 */     this._httpClient = new NativeHttpClient(authCode, proxy, config.getClientConfig());
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.BaseClient
 * JD-Core Version:    0.6.2
 */