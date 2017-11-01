//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.AndroidNotification.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;

public class JpushClientUtil {
    private static final String appKey = "a63545fccaa2de66e1fe585b";
    private static final String masterSecret = "0a64dac040059403d260c7e7";
    private static final JPushClient jPushClient = new JPushClient("0a64dac040059403d260c7e7", "a63545fccaa2de66e1fe585b");

    public JpushClientUtil() {
    }

    public static int sendToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam) {
        byte result = 0;

        try {
            PushPayload pushPayload = buildPushObject_all_registrationId_alertWithTitle(registrationId, notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException var8) {
            var8.printStackTrace();
        } catch (APIRequestException var9) {
            var9.printStackTrace();
        }

        return result;
    }

    public static int sendToAllAndroid(String notification_title, String msg_title, String msg_content, String extrasparam) {
        byte result = 0;

        try {
            PushPayload pushPayload = buildPushObject_android_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    public static int sendToAllIos(String notification_title, String msg_title, String msg_content, String extrasparam) {
        byte result = 0;

        try {
            PushPayload pushPayload = buildPushObject_ios_all_alertWithTitle(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    public static int sendToAll(String notification_title, String msg_title, String msg_content, String extrasparam) {
        byte result = 0;

        try {
            PushPayload pushPayload = buildPushObject_android_and_ios(notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        System.out.println(result);
        return result;
    }

    public static int sendToALLByAlias(String notification_title, String msg_title, String msg_content, String extrasparam, List<String> aliases) {
        byte result = 0;

        try {
            PushPayload pushPayload = buildPushObject_all_alias_alert(notification_title, msg_title, msg_content, extrasparam, aliases);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            if(pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return result;
    }

    public static PushPayload buildPushObject_android_and_ios(String notification_title, String msg_title, String msg_content, String extrasparam) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all()).setNotification(Notification.newBuilder().setAlert(notification_title).addPlatformNotification(((Builder)AndroidNotification.newBuilder().setAlert(notification_title).setTitle(notification_title).addExtra("extras", extrasparam)).build()).addPlatformNotification(((cn.jpush.api.push.model.notification.IosNotification.Builder)IosNotification.newBuilder().setAlert(notification_title).incrBadge(1).setSound("sound.caf").addExtra("extras", extrasparam)).build()).build()).setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("extras", extrasparam).build()).setOptions(Options.newBuilder().setApnsProduction(false).setSendno(1).setTimeToLive(86400L).build()).build();
    }

    private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam) {
        System.out.println("----------buildPushObject_all_all_alert");
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(new String[]{registrationId})).setNotification(Notification.newBuilder().addPlatformNotification(((Builder)AndroidNotification.newBuilder().setAlert(notification_title).setTitle(notification_title).addExtra("androidNotification extras key", extrasparam)).build()).addPlatformNotification(((cn.jpush.api.push.model.notification.IosNotification.Builder)IosNotification.newBuilder().setAlert(notification_title).incrBadge(1).setSound("sound.caf").addExtra("extras", extrasparam)).build()).build()).setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("extras", extrasparam).build()).setOptions(Options.newBuilder().setApnsProduction(false).setSendno(1).setTimeToLive(86400L).build()).build();
    }

    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        System.out.println("----------buildPushObject_android_registrationId_alertWithTitle");
        return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all()).setNotification(Notification.newBuilder().addPlatformNotification(((Builder)AndroidNotification.newBuilder().setAlert(notification_title).setTitle(notification_title).addExtra("androidNotification extras key", extrasparam)).build()).build()).setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("extras", extrasparam).build()).setOptions(Options.newBuilder().setApnsProduction(false).setSendno(1).setTimeToLive(86400L).build()).build();
    }

    private static PushPayload buildPushObject_ios_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam) {
        System.out.println("----------buildPushObject_ios_registrationId_alertWithTitle");
        return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.all()).setNotification(Notification.newBuilder().addPlatformNotification(((cn.jpush.api.push.model.notification.IosNotification.Builder)IosNotification.newBuilder().setAlert(notification_title).incrBadge(1).setSound("sound.caf").addExtra("extras", extrasparam)).build()).build()).setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("extras", extrasparam).build()).setOptions(Options.newBuilder().setApnsProduction(false).setSendno(1).setTimeToLive(86400L).build()).build();
    }

    public static PushPayload buildPushObject_all_alias_alert(String notification_title, String msg_title, String msg_content, String extrasparam, List<String> aliases) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(aliases)).setNotification(Notification.newBuilder().setAlert(notification_title).addPlatformNotification(((Builder)AndroidNotification.newBuilder().setAlert(notification_title).setTitle(notification_title).addExtra("extras", extrasparam)).build()).addPlatformNotification(((cn.jpush.api.push.model.notification.IosNotification.Builder)IosNotification.newBuilder().setAlert(notification_title).incrBadge(1).setSound("sound.caf").addExtra("extras", extrasparam)).build()).build()).setMessage(Message.newBuilder().setMsgContent(msg_content).setTitle(msg_title).addExtra("extras", extrasparam).build()).setOptions(Options.newBuilder().setApnsProduction(false).setSendno(1).setTimeToLive(86400L).build()).build();
    }

    private static PushPayload push_Android(String message) {
        Map<String, String> map = new HashMap();
        map.put("测试", "测试1");
        cn.jpush.api.push.model.Message.Builder msg = Message.newBuilder();
        msg.setTitle("衡云title");
        msg.setMsgContent("衡云content");
        msg.setContentType("type:1");
        msg.addExtra("type", Integer.valueOf(1));
        return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(new String[]{"200000249hykj"})).setMessage(msg.build()).build();
    }

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("orderId", "402881345c1acc03015c1af28d1d0053");
        json.put("helpMessageState", "HELP");
        json.put("title", "文本新消息");
        json.put("content", "阿达大!");

        try {
            String string = json.toString();
            System.out.println(string);
            List<String> ids = new ArrayList();
            ids.add("18518078991");
            if(sendToALLByAlias("您有新的订单消息", "", "", string, ids) == 1) {
                System.out.println("success");
            } else {
                System.out.println("sss");
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static String jiGuangPush(String orderId, String helpMessageState, String title, String content, String userIds) {
        try {
            JSONObject json = new JSONObject();
            json.put("orderId", orderId);
            json.put("helpMessageState", helpMessageState);
            json.put("title", title);
            json.put("content", content);
            String extrasparam = json.toString();
            List<String> ids = new ArrayList();
            ids.add(userIds);
            if(sendToALLByAlias("", "", "", extrasparam, ids) == 1) {
                System.out.println("success");
            } else {
                System.out.println("因为没有别名报错了,不用搭理！");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public static String jiGuangPushIos(String orderId, String helpMessageState, String title, String content, String userIds) {
        try {
            JSONObject json = new JSONObject();
            json.put("orderId", orderId);
            json.put("helpMessageState", helpMessageState);
            json.put("title", title);
            json.put("content", content);
            String extrasparam = json.toString();
            List<String> ids = new ArrayList();
            ids.add(userIds);
            if(sendToALLByAlias(title, "", "", extrasparam, ids) == 1) {
                System.out.println("success");
            } else {
                System.out.println("因为没有别名报错了,不用搭理！");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public static String jiGuangPushIoss(String extrasparam, String title, String text, List<String> userIds) {
        try {
            if(sendToALLByAlias(title, "", text, extrasparam, userIds) == 1) {
                System.out.println("success");
            } else {
                System.out.println("因为没有别名报错了,不用搭理！");
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public static String jiGuangPushs(String extrasparam, List<String> userIds) {
        try {
            if(sendToALLByAlias("", "", "", extrasparam, userIds) == 1) {
                System.out.println("success");
            } else {
                System.out.println("因为没有别名报错了,不用搭理！");
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }
}
