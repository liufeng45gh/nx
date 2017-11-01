//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.shortMessage;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AccessLimitUtils {
    private static Timer timer = new Timer("s-ms_frequency_filter_clear_data_thread");
    private static ConcurrentMap<String, Long> sendAddressMap = new ConcurrentHashMap();
    private static long sendInterval = 60000L;
    private static long cleanMapInterval = 60000L;

    static {
        timer.schedule(new TimerTask() {
            public void run() {
                AccessLimitUtils.cleanSendAddressMap();
            }
        }, cleanMapInterval, cleanMapInterval);
    }

    public AccessLimitUtils() {
    }

    private static void cleanSendAddressMap() {
        long currentTime = System.currentTimeMillis();
        long expireSendTime = currentTime - sendInterval;
        Iterator var5 = sendAddressMap.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            Long sendTime = (Long)sendAddressMap.get(key);
            if(sendTime.longValue() < expireSendTime) {
                sendAddressMap.remove(key, sendTime);
            }
        }

    }

    public static boolean filter(String ip, String mobile) {
        return setSendTime(mobile) && setSendTime(ip);
    }

    public static boolean filter1(String mobile) {
        return setSendTime(mobile);
    }

    private static boolean setSendTime(String id) {
        long currentTime = System.currentTimeMillis();
        Long sendTime = (Long)sendAddressMap.putIfAbsent(id, Long.valueOf(currentTime));
        if(sendTime == null) {
            return true;
        } else {
            long nextCanSendTime = sendTime.longValue() + sendInterval;
            return currentTime < nextCanSendTime?false:(sendAddressMap.replace(id, sendTime, Long.valueOf(currentTime))?true:sendAddressMap.putIfAbsent(id, Long.valueOf(currentTime)) == null);
        }
    }

    public static void destroy() {
        timer.cancel();
    }
}
