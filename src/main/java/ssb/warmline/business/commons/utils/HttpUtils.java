//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public final class HttpUtils {
    public HttpUtils() {
    }

    public static final String postXml(String url, String xml) throws IllegalStateException, IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        httppost.getParams().setParameter("http.protocol.content-charset", "utf-8");
        httppost.addHeader("Content-Type", "utf-8");
        httppost.setHeader("Content-Type", "utf-8");
        StringEntity myEntity = new StringEntity(xml, "utf-8");
        myEntity.setContentType("text/xml; charset=utf-8");
        httppost.setEntity(myEntity);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();
        InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();

        String line;
        while((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        httpclient.getConnectionManager().shutdown();
        return sb.toString();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader(" x-forwarded-for ");
        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }

        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }

        if(ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
