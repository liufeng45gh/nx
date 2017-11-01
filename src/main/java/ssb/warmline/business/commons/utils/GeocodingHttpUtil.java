//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

public class GeocodingHttpUtil {
    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int READ_TIMEOUT = 5000;
    private static final String ENCODE_CHARSET = "UTF-8";

    public GeocodingHttpUtil() {
    }

    public static String postRequest(String reqURL, String... params) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;

        try {
            url = new URL(reqURL);
        } catch (MalformedURLException var19) {
            var19.printStackTrace();
        }

        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        DataOutputStream out = null;
        if(url != null) {
            try {
                if("https".equals(url.getProtocol())) {
                    urlConn = (HttpsURLConnection)url.openConnection();
                } else {
                    urlConn = (HttpURLConnection)url.openConnection();
                }

                ((HttpURLConnection)urlConn).setDoInput(true);
                ((HttpURLConnection)urlConn).setDoOutput(true);
                ((HttpURLConnection)urlConn).setRequestMethod("POST");
                ((HttpURLConnection)urlConn).setUseCaches(false);
                ((HttpURLConnection)urlConn).setInstanceFollowRedirects(true);
                ((HttpURLConnection)urlConn).setConnectTimeout(3000);
                ((HttpURLConnection)urlConn).setReadTimeout(5000);
                ((HttpURLConnection)urlConn).setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                ((HttpURLConnection)urlConn).setRequestProperty("Charset", "UTF-8");
                String param = sendPostParams(params);
                ((HttpURLConnection)urlConn).setRequestProperty("Content-Length", String.valueOf(param.getBytes().length));
                ((HttpURLConnection)urlConn).connect();
                if(!"".equals(param)) {
                    out = new DataOutputStream(((HttpURLConnection)urlConn).getOutputStream());
                    out.writeBytes(param);
                    out.flush();
                    out.close();
                }

                in = new InputStreamReader(((HttpURLConnection)urlConn).getInputStream(), "UTF-8");
                buffer = new BufferedReader(in);
                if(((HttpURLConnection)urlConn).getResponseCode() == 200) {
                    while((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException var20) {
                var20.printStackTrace();
            } finally {
                try {
                    if(buffer != null) {
                        buffer.close();
                    }

                    if(in != null) {
                        in.close();
                    }

                    if(urlConn != null) {
                        ((HttpURLConnection)urlConn).disconnect();
                    }
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }
        }

        return resultData.toString();
    }

    public static String getRequest(String httpUrl, String... params) {
        StringBuilder resultData = new StringBuilder();
        URL url = null;

        try {
            String paramurl = sendGetParams(httpUrl, params);
            url = new URL(paramurl);
        } catch (MalformedURLException var18) {
            var18.printStackTrace();
        }

        HttpURLConnection urlConn = null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        String inputLine = null;
        if(url != null) {
            try {
                if("https".equals(url.getProtocol())) {
                    urlConn = (HttpsURLConnection)url.openConnection();
                } else {
                    urlConn = (HttpURLConnection)url.openConnection();
                }

                ((HttpURLConnection)urlConn).setRequestMethod("GET");
                ((HttpURLConnection)urlConn).setConnectTimeout(3000);
                in = new InputStreamReader(((HttpURLConnection)urlConn).getInputStream(), "UTF-8");
                buffer = new BufferedReader(in);
                if(((HttpURLConnection)urlConn).getResponseCode() == 200) {
                    while((inputLine = buffer.readLine()) != null) {
                        resultData.append(inputLine);
                    }
                }
            } catch (IOException var19) {
                var19.printStackTrace();
            } finally {
                try {
                    if(buffer != null) {
                        buffer.close();
                    }

                    if(in != null) {
                        in.close();
                    }

                    if(urlConn != null) {
                        ((HttpURLConnection)urlConn).disconnect();
                    }
                } catch (IOException var17) {
                    var17.printStackTrace();
                }

            }
        }

        return resultData.toString();
    }

    private static String sendPostParams(String... params) {
        StringBuilder sbd = new StringBuilder("");
        if(params != null && params.length > 0) {
            for(int i = 0; i < params.length; ++i) {
                String[] temp = params[i].split(":");
                sbd.append(temp[0]);
                sbd.append("=");
                sbd.append(urlEncode(temp[1]));
                sbd.append("&");
            }

            sbd.setLength(sbd.length() - 1);
        }

        return sbd.toString();
    }

    private static String sendGetParams(String reqURL, String... params) {
        StringBuilder sbd = new StringBuilder(reqURL);
        if(params != null && params.length > 0) {
            if(isexist(reqURL, "?")) {
                sbd.append("&");
            } else {
                sbd.append("?");
            }

            for(int i = 0; i < params.length; ++i) {
                String[] temp = params[i].split(":");
                sbd.append(temp[0]);
                sbd.append("=");
                sbd.append(urlEncode(temp[1]));
                sbd.append("&");
            }

            sbd.setLength(sbd.length() - 1);
        }

        return sbd.toString();
    }

    private static boolean isexist(String str, String fstr) {
        return str.indexOf(fstr) != -1;
    }

    private static String urlEncode(String source) {
        String result = source;

        try {
            result = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return result;
    }
}
