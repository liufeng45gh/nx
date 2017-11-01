//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpUtil {
    private static Logger log = Logger.getLogger(HttpUtil.class);
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
    private static final int TIME_OUT = 300000;
    private static final int MAX_CON_PER_HOST = 15000;
    private static final long DEFAULT_RETRY_INTERVAL_SECONDS = 5L;
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static HttpClient httpClient;

    static {
        init();
    }

    private static void init() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setDefaultMaxConnectionsPerHost(15000);
        params.setConnectionTimeout(300000);
        params.setSoTimeout(300000);
        params.setMaxTotalConnections(30000);
        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setCookiePolicy("ignoreCookies");
        clientParams.setSoTimeout(300000);
        clientParams.setConnectionManagerTimeout(300000L);
        httpClient = new HttpClient(clientParams, connectionManager);
    }

    private HttpUtil() {
    }

    public static void setTimeout(int timeout) {
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        httpClient.getParams().setSoTimeout(timeout);
        httpClient.getParams().setConnectionManagerTimeout((long)timeout);
    }

    public static void close() {
        if(httpClient != null) {
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(300000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(300000);
            httpClient.getParams().setSoTimeout(300000);
            httpClient.getParams().setConnectionManagerTimeout(300000L);
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }

    }

    public static String getString(String url) throws Exception {
        return getString(url, 0);
    }

    public static String getString(String url, int maxRetryTimes) throws Exception {
        GetMethod method = null;

        String var4;
        try {
            method = new GetMethod(url);
            method.setFollowRedirects(true);
            method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22");
            executeMethod(method, maxRetryTimes);
            var4 = new String(method.getResponseBody(), getCharset(method, method.getResponseBodyAsStream()));
        } finally {
            if(method != null) {
                method.releaseConnection();
            }

            close();
        }

        return var4;
    }

    private static String getCharset(HttpMethod method, InputStream in) throws IOException {
        return "UTF-8";
    }

    public static String postString(String url, NameValuePair[] parameters) throws Exception {
        return postString(url, parameters, 0);
    }

    public static String postString(String url, NameValuePair[] parameters, int maxRetryTimes) throws Exception {
        PostMethod method = null;

        String var5;
        try {
            method = new PostMethod(url);
            method.addParameters(parameters);
            method.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22");
            method.getParams().setContentCharset("UTF-8");
            executeMethod(method, maxRetryTimes);
            var5 = new String(method.getResponseBody(), "UTF-8");
        } finally {
            if(method != null) {
                method.releaseConnection();
            }

            close();
        }

        return var5;
    }

    public static int executeMethod(HttpMethod method) throws Exception {
        return executeMethod(method, 0, 0L);
    }

    public static int executeMethod(HttpMethod method, int maxRetryTimes) throws Exception {
        return executeMethod(method, maxRetryTimes, 5L);
    }

    public static int executeMethod(HttpMethod method, int maxRetryTimes, long intervalSeconds) throws Exception {
        Exception exception = null;
        int retryTimes = 0;

        while(true) {
            if(retryTimes > 0) {
                log.info("Retry [" + retryTimes + "/" + maxRetryTimes + "], after " + intervalSeconds + " seconds, " + method.getURI());
                if(intervalSeconds > 0L) {
                    TimeUnit.SECONDS.sleep(intervalSeconds);
                }
            }

            try {
                requestLog(method);
                httpClient.getParams().setAuthenticationPreemptive(true);
                int status = httpClient.executeMethod(method);
                responseLog(method);
                return status;
            } catch (Exception var7) {
                log.error("Http request error: " + var7.getClass().getName() + " " + var7.getMessage() + ", " + method.getURI());
                method.releaseConnection();
                close();
                ++retryTimes;
                if(retryTimes > maxRetryTimes) {
                    throw var7;
                }
            }
        }
    }

    private static void requestLog(HttpMethod method) throws URIException {
        log.info(">> [Request]");
        log.info(">> [" + method.getURI().getScheme() + "://" + method.getURI().getHost() + "]");
        log.info(">> [" + method.getName() + " " + method.getURI().getPathQuery() + " " + method.getParams().getVersion() + "]");
        int var2;
        int var3;
        if(method instanceof PostMethod) {
            NameValuePair[] var4;
            var3 = (var4 = ((PostMethod)method).getParameters()).length;

            for(var2 = 0; var2 < var3; ++var2) {
                NameValuePair nameValuePair = var4[var2];
                log.info(">> [" + nameValuePair.getName() + " = " + nameValuePair.getValue() + "]");
            }
        }

        Header[] var6;
        var3 = (var6 = method.getRequestHeaders()).length;

        for(var2 = 0; var2 < var3; ++var2) {
            Header header = var6[var2];
            log.info(">> [" + header.getName() + ": " + header.getValue() + "]");
        }

    }

    private static void responseLog(HttpMethod method) throws IOException {
        log.info("<< [Response]");
        log.info("<< [" + method.getStatusCode() + " " + method.getStatusText() + "]");
        Header[] var4;
        int var3 = (var4 = method.getResponseHeaders()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            Header header = var4[var2];
            log.info("<< [" + header.getName() + " " + header.getValue() + "]");
        }

        if(responseBodyIsText(method)) {
            String body = method.getResponseBodyAsString();
            log.info("<< [Response body: " + body.substring(0, Math.min(body.length(), 300)) + "]");
        }

    }

    private static boolean responseBodyIsText(HttpMethod method) {
        Header contentTypeHeader = method.getResponseHeader("Content-Type");
        if(contentTypeHeader != null) {
            String contentType = contentTypeHeader.getValue().toLowerCase();
            if(contentType.contains("text") || contentType.contains("json")) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        String postStr = postString("https://api.weibo.com/2/statuses/update.json", new NameValuePair[]{new NameValuePair("access_token", "2.00fHT5BDeE2s5B6c8280e6060_slY1")});
        System.out.println(postStr);
    }

    public static String postMultipartRequest(String url, Map<String, Object> params, String name, File file) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
        PostMethod postMethod = new PostMethod(url);

        String var11;
        try {
            Part[] data = new Part[params.size() + 1];
            int i = 0;

            String key;
            for(Iterator var9 = params.keySet().iterator(); var9.hasNext(); data[i++] = new StringPart(key, params.get(key) == null?"":params.get(key).toString(), "UTF-8")) {
                key = (String)var9.next();
            }

            data[i] = new FilePart(name, file);
            MultipartRequestEntity mrp = new MultipartRequestEntity(data, postMethod.getParams());
            postMethod.setRequestEntity(mrp);
            int status = httpClient.executeMethod(postMethod);
            if(200 != status) {
                throw new RuntimeException("Networking error!");
            }

            var11 = postMethod.getResponseBodyAsString();
        } catch (Throwable var14) {
            var14.printStackTrace();
            return null;
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }

        return var11;
    }

    public static Object postRequest(String url, Map<String, Object> mapPara, String charset, String contentType, String bodyContent, String resultType) throws Exception {
        log.info("url = " + url);
        HttpClient httpClient = new HttpClient();
        charset = charset == null?"UTF-8":charset;
        httpClient.getParams().setParameter("http.protocol.content-charset", charset);
        PostMethod postMethod = new PostMethod(url);

        byte[] var14;
        try {
            contentType = contentType == null?"application/x-www-form-urlencoded;charset=" + charset:contentType;
            postMethod.setRequestHeader("ContentType", contentType);
            postMethod.setRequestHeader("Accept-Language", "zh-cn");
            postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22");
            if(mapPara != null) {
                NameValuePair[] data = new NameValuePair[mapPara.size()];
                int i = 0;

                for(Iterator itor = mapPara.entrySet().iterator(); itor.hasNext(); ++i) {
                    Entry<String, Object> entry = (Entry)itor.next();
                    data[i] = new NameValuePair(((String)entry.getKey()).toString(), entry.getValue() == null?"":entry.getValue().toString());
                }

                postMethod.setRequestBody(data);
            }

            if(bodyContent != null) {
                RequestEntity requestEntity = new StringRequestEntity(bodyContent, contentType, charset);
                postMethod.setRequestEntity(requestEntity);
            }

            httpClient.executeMethod(postMethod);
            if(!"1".equals(resultType)) {
                InputStream in = postMethod.getResponseBodyAsStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in, charset));
                StringBuffer stringBuffer = new StringBuffer();

                String line;
                while((line = bufferReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }

                if(stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }

                if(bufferReader != null) {
                    bufferReader.close();
                }

                String result = stringBuffer.toString();
                String var24 = result;
                return var24;
            }

            var14 = postMethod.getResponseBody();
        } catch (Throwable var17) {
            var17.printStackTrace();
            return null;
        } finally {
            postMethod.releaseConnection();
            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }

        return var14;
    }

    public static Object getRequest(String url, Map<String, Object> mapPara, String charset, String contentType, String resultType, boolean proxyFlag) throws Exception {
        if(charset == null) {
            charset = "UTF-8";
        }

        httpClient.getParams().setParameter("http.protocol.content-charset", charset);
        GetMethod getMethod = null;

        byte[] var13;
        try {
            if(mapPara != null && !mapPara.isEmpty()) {
                StringBuffer parames = new StringBuffer();
                Iterator itor = mapPara.entrySet().iterator();

                while(itor.hasNext()) {
                    Entry<String, Object> entry = (Entry)itor.next();
                    parames.append(((String)entry.getKey()).toString()).append("=").append(URLEncoder.encode(entry.getValue().toString(), charset)).append("&");
                }

                parames.deleteCharAt(parames.length() - 1);
                getMethod = new GetMethod(url + "?" + parames.toString());
            } else {
                getMethod = new GetMethod(url);
            }

            if(contentType == null) {
                getMethod.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=" + charset);
            } else {
                getMethod.setRequestHeader("ContentType", contentType);
            }

            executeMethod(getMethod);
            if(!"1".equals(resultType)) {
                InputStream in = getMethod.getResponseBodyAsStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(in, charset));
                StringBuffer stringBuffer = new StringBuffer();

                String line;
                while((line = bufferReader.readLine()) != null) {
                    stringBuffer.append(line + "\n");
                }

                if(stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }

                if(bufferReader != null) {
                    bufferReader.close();
                }

                String result = stringBuffer.toString();
                String var21 = result;
                return var21;
            }

            var13 = getMethod.getResponseBody();
        } catch (Throwable var16) {
            var16.printStackTrace();
            return null;
        } finally {
            if(getMethod != null) {
                getMethod.releaseConnection();
            }

            httpClient.getHttpConnectionManager().closeIdleConnections(0L);
        }

        return var13;
    }

    public static String getRequest(String url, Map<String, Object> mapPara) throws Exception {
        try {
            String result = (String)getRequest(url, mapPara, (String)null, (String)null, (String)null, false);
            return result;
        } catch (Throwable var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String postBodyRequest(String url, String urlParam, String content) throws Exception {
        String rs = null;

        try {
            rs = (String)postRequest(url + "?" + urlParam, (Map)null, (String)null, (String)null, content, (String)null);
        } catch (Throwable var5) {
            var5.printStackTrace();
        }

        return rs;
    }

    public static String postBodyRequest(String url, String content) throws Exception {
        String rs = null;

        try {
            rs = (String)postRequest(url, (Map)null, (String)null, (String)null, content, (String)null);
        } catch (Throwable var4) {
            var4.printStackTrace();
        }

        return rs;
    }

    public static byte[] getByteRequest(String url, Map<String, Object> mapPara, String ContentType) throws Exception {
        try {
            return (byte[])getRequest(url, mapPara, (String)null, ContentType, "1", false);
        } catch (Throwable var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static Part[] toParts(Map<String, String> params) {
        if ( params == null) {
            params = new HashMap();
        }
        Part[] parts = new Part[((Map)params).size()];
        int i = 0;

        String key;
        for(Iterator var4 = ((Map)params).keySet().iterator(); var4.hasNext(); parts[i++] = new StringPart(key, (String)((Map)params).get(key))) {
            key = (String)var4.next();
        }

        return parts;
    }

    public static Map<String, String> getCookies(String cookieStr) {
        Map<String, String> cookies = new HashMap();
        String[] var5;
        int var4 = (var5 = cookieStr.split(";")).length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String cookie = var5[var3];
            String key = "";
            String value = "";
            if(cookie.indexOf("=") == -1) {
                key = cookie;
            } else {
                key = cookie.substring(0, cookie.indexOf("="));
                value = cookie.substring(cookie.indexOf("=") + 1);
            }

            if(StringUtils.isNotBlank(value)) {
                cookies.put(key, value);
            }
        }

        return cookies;
    }

    public static Map<String, String> getCookies(Map<String, Object> cookieMap, String[] keys) {
        Map<String, String> remainCookies = new HashMap();
        String[] var6 = keys;
        int var5 = keys.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String key = var6[var4];
            if(cookieMap.containsKey(key.trim())) {
                remainCookies.put(key, (String)cookieMap.get(key));
            }
        }

        return remainCookies;
    }

    public static String getCookieStr(Map<String, String> cookie) {
        String cookiestr = "";

        String key;
        for(Iterator var3 = cookie.keySet().iterator(); var3.hasNext(); cookiestr = cookiestr + key + "=" + (String)cookie.get(key) + ";") {
            key = (String)var3.next();
        }

        return cookiestr;
    }

    public static Map<String, String> getRequestCookie(String url) throws Exception {
        GetMethod getMethod = new GetMethod(url);
        getMethod.setFollowRedirects(true);
        httpClient.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));
        httpClient.getParams().setParameter("http.protocol.cookie-policy", "compatibility");
        executeMethod(getMethod);
        Map<String, String> cookies = new HashMap();
        Header[] var6;
        int var5 = (var6 = getMethod.getResponseHeaders("Set-Cookie")).length;

        Header header;
        int var4;
        for(var4 = 0; var4 < var5; ++var4) {
            header = var6[var4];
            cookies.putAll(getCookies(header.getValue()));
        }

        var5 = (var6 = getMethod.getRequestHeaders("Cookie")).length;

        for(var4 = 0; var4 < var5; ++var4) {
            header = var6[var4];
            cookies.putAll(getCookies(header.getValue()));
        }

        return cookies;
    }

    public static final String httpClientPost(String url) {
        String result = "";
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);

        try {
            client.executeMethod(getMethod);
            result = getMethod.getResponseBodyAsString();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }

        return result;
    }

    public static final String httpClientPost(String url, ArrayList<NameValuePair> list) {
        String result = "";
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        try {
            NameValuePair[] params = new NameValuePair[list.size()];

            for(int i = 0; i < list.size(); ++i) {
                params[i] = (NameValuePair)list.get(i);
            }

            postMethod.addParameters(params);
            client.executeMethod(postMethod);
            result = postMethod.getResponseBodyAsString();
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return result;
    }
}
