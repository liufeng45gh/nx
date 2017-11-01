/*    */ package ssb.warmline.business.commons.utils.JMessage;
/*    */ 
/*    */ import cn.jiguang.common.ClientConfig;
/*    */ 
/*    */ public class JMessageConfig
/*    */ {
/*  7 */   private static ClientConfig clientConfig = ClientConfig.getInstance();
/*    */ 
/*  9 */   private static JMessageConfig instance = new JMessageConfig();
/*    */   public static final String API_HOST_NAME = "im.api.host.name";
/*    */   public static final String API_REPORT_HOST_NAME = "im.api.report.host.name";
/*    */   public static final String ADMIN_PATH = "im.admin.path";
/*    */   public static final String USER_PATH = "im.user.path";
/*    */   public static final String V2_USER_PATH = "im.v2.user.path";
/*    */   public static final String GROUP_PATH = "im.group.path";
/*    */   public static final String MESSAGE_PATH = "im.message.path";
/*    */   public static final String V2_MESSAGE_PATH = "im.v2.message.path";
/*    */   public static final String RESOURCE_PATH = "im.resource.path";
/*    */   public static final String CROSS_USER_PATH = "im.cross.user.path";
/*    */   public static final String CROSS_GROUP_PATH = "im.cross.group.path";
/*    */   public static final String MAX_RETRY_TIMES = "max.retry.times";
/*    */   public static final String SEND_VERSION = "send.version";
/* 31 */   public static final Object SEND_VERSION_SCHMEA = Integer.class;
/*    */ 
/*    */   private JMessageConfig() {
/* 34 */     clientConfig.put("im.api.host.name", "https://api.im.jpush.cn");
/* 35 */     clientConfig.put("im.api.report.host.name", "https://report.im.jpush.cn");
/* 36 */     clientConfig.put("im.admin.path", "/v1/admins");
/* 37 */     clientConfig.put("im.user.path", "/v1/users");
/* 38 */     clientConfig.put("im.v2.user.path", "/v2/users");
/* 39 */     clientConfig.put("im.group.path", "/v1/groups");
/* 40 */     clientConfig.put("im.message.path", "/v1/messages");
/* 41 */     clientConfig.put("im.v2.message.path", "/v2/messages");
/* 42 */     clientConfig.put("im.resource.path", "/v1/resource");
/* 43 */     clientConfig.put("im.cross.user.path", "/v1/cross/users");
/* 44 */     clientConfig.put("im.cross.group.path", "/v1/cross/groups");
/* 45 */     clientConfig.put("max.retry.times", Integer.valueOf(3));
/* 46 */     clientConfig.put("send.version", Integer.valueOf(1));
/*    */   }
/*    */ 
/*    */   public static JMessageConfig getInstance() {
/* 50 */     return instance;
/*    */   }
/*    */ 
/*    */   public ClientConfig getClientConfig() {
/* 54 */     return clientConfig;
/*    */   }
/*    */ 
/*    */   public JMessageConfig setApiHostName(String hostName) {
/* 58 */     clientConfig.put("im.api.host.name", hostName);
/* 59 */     return this;
/*    */   }
/*    */ 
/*    */   public JMessageConfig setMaxRetryTimes(int maxRetryTimes) {
/* 63 */     clientConfig.setMaxRetryTimes(maxRetryTimes);
/* 64 */     return this;
/*    */   }
/*    */ 
/*    */   public void put(String key, Object value) {
/* 68 */     clientConfig.put(key, value);
/*    */   }
/*    */ 
/*    */   public Object get(String key) {
/* 72 */     return clientConfig.get(key);
/*    */   }
/*    */ }

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.ssb.warmline.business.commons.utils.JMessage.JMessageConfig
 * JD-Core Version:    0.6.2
 */