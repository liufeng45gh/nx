//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.wechatpay;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

public class PayCommonUtil {
    public PayCommonUtil() {
    }

    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();

        String k;
        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(!"sign".equals(k) && v != null && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
        k = ((String)packageParams.get("sign")).toLowerCase();
        return k.equals(mysign);
    }

    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();

        while(it.hasNext()) {
            Entry entry = (Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(v != null && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();

        while(true) {
            while(it.hasNext()) {
                Entry entry = (Entry)it.next();
                String k = (String)entry.getKey();
                String v = (String)entry.getValue();
                if(!"attach".equalsIgnoreCase(k) && !"body".equalsIgnoreCase(k) && !"sign".equalsIgnoreCase(k)) {
                    sb.append("<" + k + ">" + v + "</" + k + ">");
                } else {
                    sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
                }
            }

            sb.append("</xml>");
            return sb.toString();
        }
    }

    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if(random < 0.1D) {
            random += 0.1D;
        }

        for(int i = 0; i < length; ++i) {
            num *= 10;
        }

        return (int)(random * (double)num);
    }

    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    public static String buildRandom() {
        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        int num = 1;
        double random = Math.random();
        if(random < 0.1D) {
            random += 0.1D;
        }

        for(int i = 0; i < 4; ++i) {
            num *= 10;
        }

        return (int)(random * (double)num) + strTime;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip == null) {
            ip = "";
        }

        if(StringUtils.isNotEmpty(ip)) {
            String[] ipArr = ip.split(",");
            if(ipArr.length > 1) {
                ip = ipArr[0];
            }
        }

        return ip;
    }

    public static String doSendMoney(String url, String data, String mch_id, InputStream inputStream) throws Exception {
        KeyStore keystore = KeyStore.getInstance("PKCS12");

        try {
            keystore.load(inputStream, mch_id.toCharArray());
        } finally {
            inputStream.close();
        }

        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keystore, mch_id.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, (String[])null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        HttpClientBuilder builder = HttpClients.custom();
        builder.setSSLSocketFactory(sslsf);
        CloseableHttpClient httpClient = builder.build();

        String var14;
        try {
            HttpPost httpost = new HttpPost(url);
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpost);

            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = toStringInfo(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                var14 = jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return var14;
    }

    private static String toStringInfo(HttpEntity entity, String defaultCharset) throws Exception, IOException {
        InputStream instream = entity.getContent();
        if(instream == null) {
            return null;
        } else {
            try {
                Args.check(entity.getContentLength() <= 2147483647L, "HTTP entity too large to be buffered in memory");
                int i = (int)entity.getContentLength();
                if(i < 0) {
                    i = 4096;
                }

                Charset charset = null;
                if(charset == null) {
                    charset = Charset.forName(defaultCharset);
                }

                if(charset == null) {
                    charset = HTTP.DEF_CONTENT_CHARSET;
                }

                Reader reader = new InputStreamReader(instream, charset);
                CharArrayBuffer buffer = new CharArrayBuffer(i);
                char[] tmp = new char[1024];

                int l;
                while((l = reader.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }

                String var10 = buffer.toString();
                return var10;
            } finally {
                instream.close();
            }
        }
    }
}
