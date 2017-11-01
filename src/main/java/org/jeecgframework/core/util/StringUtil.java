//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

public class StringUtil {
    private static Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
    private static Pattern numericStringPattern = Pattern.compile("^[0-9\\-\\-]+$");
    private static Pattern floatNumericPattern = Pattern.compile("^[0-9\\-\\.]+$");
    private static Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
    public static final String splitStrPattern = ",|，|;|；|、|\\.|。|-|_|\\(|\\)|\\[|\\]|\\{|\\}|\\\\|/| |　|\"";
    private static Log logger = LogFactory.getLog(StringUtil.class);

    public StringUtil() {
    }

    public static boolean isNumeric(String src) {
        boolean return_value = false;
        if(src != null && src.length() > 0) {
            Matcher m = numericPattern.matcher(src);
            if(m.find()) {
                return_value = true;
            }
        }

        return return_value;
    }

    public static boolean isNumericString(String src) {
        boolean return_value = false;
        if(src != null && src.length() > 0) {
            Matcher m = numericStringPattern.matcher(src);
            if(m.find()) {
                return_value = true;
            }
        }

        return return_value;
    }

    public static boolean isABC(String src) {
        boolean return_value = false;
        if(src != null && src.length() > 0) {
            Matcher m = abcPattern.matcher(src);
            if(m.find()) {
                return_value = true;
            }
        }

        return return_value;
    }

    public static boolean isFloatNumeric(String src) {
        boolean return_value = false;
        if(src != null && src.length() > 0) {
            Matcher m = floatNumericPattern.matcher(src);
            if(m.find()) {
                return_value = true;
            }
        }

        return return_value;
    }

    public static String joinString(List array, String symbol) {
        String result = "";
        if(array != null) {
            for(int i = 0; i < array.size(); ++i) {
                String temp = array.get(i).toString();
                if(temp != null && temp.trim().length() > 0) {
                    result = result + temp + symbol;
                }
            }

            if(result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }

        return result;
    }

    public static String subStringNotEncode(String subject, int size) {
        if(subject != null && subject.length() > size) {
            subject = subject.substring(0, size) + "...";
        }

        return subject;
    }

    public static String getLimitLengthString(String str, int len, String symbol) {
        int iLen = len * 2;
        int counterOfDoubleByte = 0;
        String strRet = "";

        String var9;
        try {
            if(str == null) {
                return "";
            }

            byte[] b = str.getBytes("GBK");
            if(b.length <= iLen) {
                var9 = str;
                return var9;
            }

            for(int i = 0; i < iLen; ++i) {
                if(b[i] < 0) {
                    ++counterOfDoubleByte;
                }
            }

            if(counterOfDoubleByte % 2 == 0) {
                strRet = new String(b, 0, iLen, "GBK") + symbol;
                var9 = strRet;
                return var9;
            }

            strRet = new String(b, 0, iLen - 1, "GBK") + symbol;
            var9 = strRet;
        } catch (Exception var12) {
            var9 = str.substring(0, len);
            return var9;
        } finally {
            strRet = null;
        }

        return var9;
    }

    public static String getLimitLengthString(String str, int len) {
        return getLimitLengthString(str, len, "...");
    }

    public static String subStrNotEncode(String subject, int size) {
        if(subject.length() > size) {
            subject = subject.substring(0, size);
        }

        return subject;
    }

    public static String joinString(String[] array, String symbol) {
        String result = "";
        if(array != null) {
            for(int i = 0; i < array.length; ++i) {
                String temp = array[i];
                if(temp != null && temp.trim().length() > 0) {
                    result = result + temp + symbol;
                }
            }

            if(result.length() > 1) {
                result = result.substring(0, result.length() - 1);
            }
        }

        return result;
    }

    public static int getStringLen(String SrcStr) {
        int return_value = 0;
        if(SrcStr != null) {
            char[] theChars = SrcStr.toCharArray();

            for(int i = 0; i < theChars.length; ++i) {
                return_value += theChars[i] <= 255?1:2;
            }
        }

        return return_value;
    }

    public static boolean check(String str) {
        String sIllegal = "'\"";
        int len = sIllegal.length();
        if(str == null) {
            return false;
        } else {
            for(int i = 0; i < len; ++i) {
                if(str.indexOf(sIllegal.charAt(i)) != -1) {
                    return true;
                }
            }

            return false;
        }
    }

    public static String getHideEmailPrefix(String email) {
        if(email != null) {
            int index = email.lastIndexOf(64);
            if(index > 0) {
                email = repeat("*", index).concat(email.substring(index));
            }
        }

        return email;
    }

    public static String repeat(String src, int num) {
        StringBuffer s = new StringBuffer();

        for(int i = 0; i < num; ++i) {
            s.append(src);
        }

        return s.toString();
    }

    public static List<String> parseString2ListByCustomerPattern(String pattern, String src) {
        if(src == null) {
            return null;
        } else {
            List<String> list = new ArrayList();
            String[] result = src.split(pattern);

            for(int i = 0; i < result.length; ++i) {
                list.add(result[i]);
            }

            return list;
        }
    }

    public static List<String> parseString2ListByPattern(String src) {
        String pattern = "，|,|、|。";
        return parseString2ListByCustomerPattern(pattern, src);
    }

    public static String formatFloat(float f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format((double)f);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    public static List<String> splitToList(String split, String src) {
        String sp = ",";
        if(split != null && split.length() == 1) {
            sp = split;
        }

        List<String> r = new ArrayList();
        int lastIndex = -1;
        int index = src.indexOf(sp);
        if(-1 == index && src != null) {
            r.add(src);
            return r;
        } else {
            while(index >= 0) {
                if(index > lastIndex) {
                    r.add(src.substring(lastIndex + 1, index));
                } else {
                    r.add("");
                }

                lastIndex = index;
                index = src.indexOf(sp, index + 1);
                if(index == -1) {
                    r.add(src.substring(lastIndex + 1, src.length()));
                }
            }

            return r;
        }
    }

    public static String linkedHashMapToString(LinkedHashMap<String, String> map) {
        if(map != null && map.size() > 0) {
            String result = "";

            String name;
            String value;
            for(Iterator it = map.keySet().iterator(); it.hasNext(); result = result + String.format("%s=%s", new Object[]{name, value})) {
                name = (String)it.next();
                value = (String)map.get(name);
                result = result + (result.equals("")?"":"&");
            }

            return result;
        } else {
            return null;
        }
    }

    public static LinkedHashMap<String, String> toLinkedHashMap(String str) {
        if(str != null && !str.equals("") && str.indexOf("=") > 0) {
            LinkedHashMap result = new LinkedHashMap();
            String name = null;
            String value = null;

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                switch(c) {
                    case '&':
                        if(name != null && value != null && !name.equals("")) {
                            result.put(name, value);
                        }

                        name = null;
                        value = null;
                        break;
                    case '=':
                        value = "";
                        break;
                    default:
                        if(value != null) {
                            value = value != null?value + c:"" + c;
                        } else {
                            name = name != null?name + c:"" + c;
                        }
                }
            }

            if(name != null && value != null && !name.equals("")) {
                result.put(name, value);
            }

            return result;
        } else {
            return null;
        }
    }

    public static String getCaption(String captions, int index) {
        if(index > 0 && captions != null && !captions.equals("")) {
            String[] ss = captions.split(",");
            if(ss != null && ss.length > 0 && index < ss.length) {
                return ss[index];
            }
        }

        return null;
    }

    public static String numberToString(Object num) {
        return num == null?null:(num instanceof Integer && ((Integer)num).intValue() > 0?Integer.toString(((Integer)num).intValue()):(num instanceof Long && ((Long)num).longValue() > 0L?Long.toString(((Long)num).longValue()):(num instanceof Float && ((Float)num).floatValue() > 0.0F?Float.toString(((Float)num).floatValue()):(num instanceof Double && ((Double)num).doubleValue() > 0.0D?Double.toString(((Double)num).doubleValue()):""))));
    }

    public static String moneyToString(Object money, String style) {
        if(money == null || style == null || !(money instanceof Double) && !(money instanceof Float)) {
            return null;
        } else {
            Double num = (Double)money;
            if(style.equalsIgnoreCase("default")) {
                return num.doubleValue() == 0.0D?"":(num.doubleValue() * 10.0D % 10.0D == 0.0D?Integer.toString(num.intValue()):num.toString());
            } else {
                DecimalFormat df = new DecimalFormat(style);
                return df.format(num);
            }
        }
    }

    public static boolean strPos(String sou, String... finds) {
        if(sou != null && finds != null && finds.length > 0) {
            for(int i = 0; i < finds.length; ++i) {
                if(sou.indexOf(finds[i]) > -1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean strPos(String sou, List<String> finds) {
        if(sou != null && finds != null && finds.size() > 0) {
            Iterator var3 = finds.iterator();

            while(var3.hasNext()) {
                String s = (String)var3.next();
                if(sou.indexOf(s) > -1) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean strPos(String sou, String finds) {
        List<String> t = splitToList(",", finds);
        return strPos(sou, t);
    }

    public static boolean equals(String s1, String s2) {
        return isEmpty(s1) && isEmpty(s2)?true:(!isEmpty(s1) && !isEmpty(s2)?s1.equals(s2):false);
    }

    public static int toInt(String s) {
        if(s != null && !"".equals(s.trim())) {
            try {
                return Integer.parseInt(s);
            } catch (Exception var2) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public static double toDouble(String s) {
        return s != null && !"".equals(s.trim())?Double.parseDouble(s):0.0D;
    }

    public static Object xmlToObject(String xml) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(xml.getBytes("UTF8"));
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(in));
            return decoder.readObject();
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static long toLong(String s) {
        try {
            if(s != null && !"".equals(s.trim())) {
                return Long.parseLong(s);
            }
        } catch (Exception var2) {
            ;
        }

        return 0L;
    }

    public static String simpleEncrypt(String str) {
        if(str != null && str.length() > 0) {
            str = str.replaceAll("1", "b");
            str = str.replaceAll("3", "d");
            str = str.replaceAll("5", "f");
            str = str.replaceAll("6", "g");
            str = str.replaceAll("7", "h");
            str = str.replaceAll("8", "i");
            str = str.replaceAll("9", "j");
        }

        return str;
    }

    public static String removeURL(String str) {
        if(str != null) {
            str = str.toLowerCase().replaceAll("(http|www|com|cn|org|\\.)+", "");
        }

        return str;
    }

    public static String numRandom(int bit) {
        if(bit == 0) {
            bit = 6;
        }

        String str = "";
        str = "0123456789";
        return RandomStringUtils.random(bit, str);
    }

    public static String random(int bit) {
        if(bit == 0) {
            bit = 6;
        }

        String str = "";
        str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";
        return RandomStringUtils.random(bit, str);
    }

    public static String replaceWapStr(String str) {
        if(str != null) {
            str = str.replaceAll("<span class=\"keyword\">", "");
            str = str.replaceAll("</span>", "");
            str = str.replaceAll("<strong class=\"keyword\">", "");
            str = str.replaceAll("<strong>", "");
            str = str.replaceAll("</strong>", "");
            str = str.replace('$', '＄');
            str = str.replaceAll("&amp;", "＆");
            str = str.replace('&', '＆');
            str = str.replace('<', '＜');
            str = str.replace('>', '＞');
        }

        return str;
    }

    public static Float toFloat(String s) {
        try {
            return Float.valueOf(Float.parseFloat(s));
        } catch (NumberFormatException var2) {
            return new Float(0.0F);
        }
    }

    public static String replaceBlank(String str) {
        if(str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }

        return str;
    }

    public static String Q2B(String QJstr) {
        String outStr = "";
        String Tstr = "";
        byte[] b = null;

        for(int i = 0; i < QJstr.length(); ++i) {
            try {
                Tstr = QJstr.substring(i, i + 1);
                b = Tstr.getBytes("unicode");
            } catch (UnsupportedEncodingException var7) {
                if(logger.isErrorEnabled()) {
                    logger.error(var7);
                }
            }

            if(b[3] == -1) {
                b[2] = (byte)(b[2] + 32);
                b[3] = 0;

                try {
                    outStr = outStr + new String(b, "unicode");
                } catch (UnsupportedEncodingException var6) {
                    if(logger.isErrorEnabled()) {
                        logger.error(var6);
                    }
                }
            } else {
                outStr = outStr + Tstr;
            }
        }

        return outStr;
    }

    public static String changCoding(String s, String fencode, String bencode) {
        try {
            String str;
            if(isNotEmpty(s)) {
                str = new String(s.getBytes(fencode), bencode);
            } else {
                str = "";
            }

            return str;
        } catch (UnsupportedEncodingException var5) {
            return s;
        }
    }

    public static String removeHTMLLableExe(String str) {
        str = stringReplace(str, ">\\s*<", "><");
        str = stringReplace(str, "&nbsp;", " ");
        str = stringReplace(str, "<br ?/?>", "\n");
        str = stringReplace(str, "<([^<>]+)>", "");
        str = stringReplace(str, "\\s\\s\\s*", " ");
        str = stringReplace(str, "^\\s*", "");
        str = stringReplace(str, "\\s*$", "");
        str = stringReplace(str, " +", " ");
        return str;
    }

    public static String removeHTMLLable(String str) {
        str = stringReplace(str, "\\s", "");
        str = stringReplace(str, "<br ?/?>", "\n");
        str = stringReplace(str, "<([^<>]+)>", "");
        str = stringReplace(str, "&nbsp;", " ");
        str = stringReplace(str, "&(\\S)(\\S?)(\\S?)(\\S?);", "");
        return str;
    }

    public static String removeOutHTMLLable(String str) {
        str = stringReplace(str, ">([^<>]+)<", "><");
        str = stringReplace(str, "^([^<>]+)<", "<");
        str = stringReplace(str, ">([^<>]+)$", ">");
        return str;
    }

    public static String stringReplace(String str, String sr, String sd) {
        Pattern p = Pattern.compile(sr, 2);
        Matcher m = p.matcher(str);
        str = m.replaceAll(sd);
        return str;
    }

    public static String fomateToFullForm(String str, String pt) {
        String regEx = "<" + pt + "\\s+([\\S&&[^<>]]*)/>";
        Pattern p = Pattern.compile(regEx, 2);
        Matcher m = p.matcher(str);
        String[] sa = null;
        String sf = "";
        String sf2 = "";

        for(String sf3 = ""; m.find(); sa = null) {
            sa = p.split(str);
            if(sa == null) {
                break;
            }

            sf = str.substring(sa[0].length(), str.indexOf("/>", sa[0].length()));
            sf2 = sf + "></" + pt + ">";
            sf3 = str.substring(sa[0].length() + sf.length() + 2);
            str = sa[0] + sf2 + sf3;
        }

        return str;
    }

    public static int[] getSubStringPos(String str, String sub, boolean b) {
        String[] sp = null;
        int l = sub.length();
        sp = splitString(str, sub);
        if(sp == null) {
            return null;
        } else {
            int[] ip = new int[sp.length - 1];

            int j;
            for(j = 0; j < sp.length - 1; ++j) {
                ip[j] = sp[j].length() + l;
                if(j != 0) {
                    ip[j] += ip[j - 1];
                }
            }

            if(b) {
                for(j = 0; j < ip.length; ++j) {
                    ip[j] -= l;
                }
            }

            return ip;
        }
    }

    public static String[] splitString(String str, String ms) {
        Pattern p = Pattern.compile(ms, 2);
        String[] sp = p.split(str);
        return sp;
    }

    public static String[] getStringArrayByPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern, 2);
        Matcher matcher = p.matcher(str);
        HashSet result = new HashSet();

        while(matcher.find()) {
            for(int i = 0; i < matcher.groupCount(); ++i) {
                result.add(matcher.group(i));
            }
        }

        String[] resultStr = null;
        if(result.size() > 0) {
            resultStr = new String[result.size()];
            return (String[])result.toArray(resultStr);
        } else {
            return resultStr;
        }
    }

    public static String[] midString(String s, String b, String e) {
        int i = s.indexOf(b) + b.length();
        int j = s.indexOf(e, i);
        String[] sa = new String[2];
        if(i >= b.length() && j >= i + 1 && i <= j) {
            sa[0] = s.substring(i, j);
            sa[1] = s.substring(j);
            return sa;
        } else {
            sa[1] = s;
            sa[0] = null;
            return sa;
        }
    }

    public static String stringReplace(String s, String pf, String pb, int start) {
        Pattern pattern_hand = Pattern.compile(pf);
        Matcher matcher_hand = pattern_hand.matcher(s);
        int gc = matcher_hand.groupCount();
        int pos = start;
        String sf1 = "";
        String sf2 = "";
        String sf3 = "";
        int if1 = 0;

        String strr;
        for(strr = ""; matcher_hand.find(pos); strr = strr + sf2) {
            sf1 = matcher_hand.group();
            if1 = s.indexOf(sf1, pos);
            if(if1 < pos) {
                return s;
            }

            strr = strr + s.substring(pos, if1);
            pos = if1 + sf1.length();
            sf2 = pb;

            for(int i = 1; i <= gc; ++i) {
                sf3 = "\\" + i;
                sf2 = replaceAll(sf2, sf3, matcher_hand.group(i));
            }
        }

        strr = s.substring(0, start) + strr;
        return strr;
    }

    public static String replaceAll(String s, String sf, String sb) {
        int i = 0;
        int j = 0;
        int l = sf.length();
        boolean b = true;
        boolean o = true;
        String str = "";

        do {
            j = i;
            i = s.indexOf(sf, i);
            if(i > j) {
                str = str + s.substring(j, i);
                str = str + sb;
                i += l;
                o = false;
            } else {
                str = str + s.substring(j);
                b = false;
            }
        } while(b);

        if(o) {
            str = s;
        }

        return str;
    }

    public static String replace(String strSource, String strOld, String strNew) {
        if(strSource == null) {
            return null;
        } else {
            int i = 0;
            
            if((i = strSource.indexOf(strOld, i)) < 0) {
                return strSource;
            } else {
                char[] cSrc = strSource.toCharArray();
                char[] cTo = strNew.toCharArray();
                int len = strOld.length();
                StringBuffer buf = new StringBuffer(cSrc.length);
                buf.append(cSrc, 0, i).append(cTo);
                i += len;

                int j;
                for(j = i; (i = strSource.indexOf(strOld, i)) > 0; j = i) {
                    buf.append(cSrc, j, i - j).append(cTo);
                    i += len;
                }

                buf.append(cSrc, j, cSrc.length - j);
                return buf.toString();
            }
        }
    }

    public static boolean isMatch(String str, String pattern) {
        Pattern pattern_hand = Pattern.compile(pattern);
        Matcher matcher_hand = pattern_hand.matcher(str);
        boolean b = matcher_hand.matches();
        return b;
    }

    public static String subStringExe(String s, String jmp, String sb, String se) {
        if(isEmpty(s)) {
            return "";
        } else {
            int i = s.indexOf(jmp);
            if(i >= 0 && i < s.length()) {
                s = s.substring(i + 1);
            }

            i = s.indexOf(sb);
            if(i >= 0 && i < s.length()) {
                s = s.substring(i + 1);
            }

            if(se == "") {
                return s;
            } else {
                i = s.indexOf(se);
                if(i >= 0 && i < s.length()) {
                    s = s.substring(i + 1);
                }

                return s;
            }
        }
    }

    public static String URLEncode(String src) {
        String return_value = "";

        try {
            if(src != null) {
                return_value = URLEncoder.encode(src, "GBK");
            }
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            return_value = src;
        }

        return return_value;
    }

    public static String getGBK(String str) {
        return transfer(str);
    }

    public static String transfer(String str) {
        Pattern p = Pattern.compile("&#\\d+;");

        String old;
        for(Matcher m = p.matcher(str); m.find(); str = str.replaceAll(old, getChar(old))) {
            old = m.group();
        }

        return str;
    }

    public static String getChar(String str) {
        String dest = str.substring(2, str.length() - 1);
        char ch = (char)Integer.parseInt(dest);
        return "" + ch;
    }

    public static String subYhooString(String subject, int size) {
        subject = subject.substring(1, size);
        return subject;
    }

    public static String subYhooStringDot(String subject, int size) {
        subject = subject.substring(1, size) + "...";
        return subject;
    }

    public static <T> String listTtoString(List<T> list) {
        if(list != null && list.size() >= 1) {
            Iterator<T> i = list.iterator();
            if(!i.hasNext()) {
                return "";
            } else {
                StringBuilder sb = new StringBuilder();

                while(true) {
                    T e = i.next();
                    sb.append(e);
                    if(!i.hasNext()) {
                        return sb.toString();
                    }

                    sb.append(",");
                }
            }
        } else {
            return "";
        }
    }

    public static String intArraytoString(int[] a) {
        if(a == null) {
            return "";
        } else {
            int iMax = a.length - 1;
            if(iMax == -1) {
                return "";
            } else {
                StringBuilder b = new StringBuilder();
                int i = 0;

                while(true) {
                    b.append(a[i]);
                    if(i == iMax) {
                        return b.toString();
                    }

                    b.append(",");
                    ++i;
                }
            }
        }
    }

    public static boolean isContentRepeat(String content) {
        int similarNum = 0;
        int forNum = 0;
        int subNum = 0;
        int thousandNum = 0;
        String startStr = "";
        String nextStr = "";
        boolean result = false;
        float endNum = 0.0F;
        if(content != null && content.length() > 0) {
            //int thousandNum;
            if(content.length() % 1000 > 0) {
                thousandNum = (int)Math.floor((double)(content.length() / 1000)) + 1;
            } else {
                thousandNum = (int)Math.floor((double)(content.length() / 1000));
            }

            //int subNum;
            if(thousandNum < 3) {
                subNum = 100 * thousandNum;
            } else if(thousandNum < 6) {
                subNum = 200 * thousandNum;
            } else if(thousandNum < 9) {
                subNum = 300 * thousandNum;
            } else {
                subNum = 3000;
            }

            for(int j = 1; j < subNum; ++j) {
                //int forNum;
                if(content.length() % j > 0) {
                    forNum = (int)Math.floor((double)(content.length() / j)) + 1;
                } else {
                    forNum = (int)Math.floor((double)(content.length() / j));
                }

                if(result || j >= content.length()) {
                    break;
                }

                for(int m = 0; m < forNum && m * j <= content.length() && (m + 1) * j <= content.length() && (m + 2) * j <= content.length(); ++m) {
                    startStr = content.substring(m * j, (m + 1) * j);
                    nextStr = content.substring((m + 1) * j, (m + 2) * j);
                    if(startStr.equals(nextStr)) {
                        ++similarNum;
                        endNum = (float)similarNum / (float)forNum;
                        if((double)endNum > 0.4D) {
                            result = true;
                            break;
                        }
                    } else {
                        similarNum = 0;
                    }
                }
            }
        }

        return result;
    }

    public static String isEmpty(String s, String result) {
        return s != null && !s.equals("")?s:result;
    }

    public static boolean isNotEmpty(Object str) {
        boolean flag = true;
        if(str != null && !str.equals("")) {
            if(str.toString().length() > 0) {
                flag = true;
            }
        } else {
            flag = false;
        }

        return flag;
    }

    public static String full2Half(String str) {
        if(str != null && !"".equals(str)) {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if(c >= '！' && c < '｝') {
                    sb.append((char)(c - 'ﻠ'));
                } else {
                    sb.append(str.charAt(i));
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String replaceBracketStr(String str) {
        if(str != null && str.length() > 0) {
            str = str.replaceAll("（", "(");
            str = str.replaceAll("）", ")");
        }

        return str;
    }

    public static Map<String, String> parseQuery(String query, char split1, char split2, String dupLink) {
        if(!isEmpty(query) && query.indexOf(split2) > 0) {
            Map<String, String> result = new HashMap();
            String name = null;
            String value = null;
            String tempValue = "";
            int len = query.length();

            for(int i = 0; i < len; ++i) {
                char c = query.charAt(i);
                if(c == split2) {
                    value = "";
                } else if(c == split1) {
                    if(!isEmpty(name) && value != null) {
                        if(dupLink != null) {
                            tempValue = (String)result.get(name);
                            if(tempValue != null) {
                                value = value + dupLink + tempValue;
                            }
                        }

                        result.put(name, value);
                    }

                    name = null;
                    value = null;
                } else if(value != null) {
                    value = value + c;
                } else {
                    name = name != null?name + c:"" + c;
                }
            }

            if(!isEmpty(name) && value != null) {
                if(dupLink != null) {
                    tempValue = (String)result.get(name);
                    if(tempValue != null) {
                        value = value + dupLink + tempValue;
                    }
                }

                result.put(name, value);
            }

            return result;
        } else {
            return null;
        }
    }

    public static String listToStringSlipStr(List list, String slipStr) {
        StringBuffer returnStr = new StringBuffer();
        if(list != null && list.size() > 0) {
            for(int i = 0; i < list.size(); ++i) {
                returnStr.append(list.get(i)).append(slipStr);
            }
        }

        return returnStr.toString().length() > 0?returnStr.toString().substring(0, returnStr.toString().lastIndexOf(slipStr)):"";
    }

    public static String getMaskStr(String str, int start, int len) {
        if(isEmpty(str)) {
            return str;
        } else if(str.length() < start) {
            return str;
        } else {
            String ret = str.substring(0, start);
            int strLen = str.length();
            if(strLen < start + len) {
                len = strLen - start;
            }

            for(int i = 0; i < len; ++i) {
                ret = ret + "*";
            }

            if(strLen > start + len) {
                ret = ret + str.substring(start + len);
            }

            return ret;
        }
    }

    public static List<String> stringToStringListBySlipStr(String slipStr, String src) {
        if(src == null) {
            return null;
        } else {
            List<String> list = new ArrayList();
            String[] result = src.split(slipStr);

            for(int i = 0; i < result.length; ++i) {
                list.add(result[i]);
            }

            return list;
        }
    }

    public static String getHtmlSubString(String str, int len, String tail) {
        if(str != null && str.length() > len) {
            int length = str.length();
            char c ;
            String tag = null;
            String name = null;
            int size = 0;
            String result = "";
            boolean isTag = false;
            List<String> tags = new ArrayList();
            int i = 0;
            int end = 0;

            for(boolean var13 = false; i < length && len > 0; ++i) {
                 c = str.charAt(i);
                if(c == 60) {
                    end = str.indexOf(62, i);
                }

                if(end > 0) {
                    tag = str.substring(i, end + 1);
                    int n = tag.length();
                    if(tag.endsWith("/>")) {
                        isTag = true;
                    } else if(tag.startsWith("</")) {
                        name = tag.substring(2, end - i);
                        size = tags.size() - 1;
                        if(size >= 0 && name.equals(tags.get(size))) {
                            isTag = true;
                            tags.remove(size);
                        }
                    } else {
                        int spanEnd = tag.indexOf(32, 0);
                        spanEnd = spanEnd > 0?spanEnd:n;
                        name = tag.substring(1, spanEnd);
                        if(name.trim().length() > 0) {
                            spanEnd = str.indexOf("</" + name + ">", end);
                            if(spanEnd > 0) {
                                isTag = true;
                                tags.add(name);
                            }
                        }
                    }

                    if(!isTag) {
                        if(n >= len) {
                            result = result + tag.substring(0, len);
                            break;
                        }

                        len -= n;
                    }

                    result = result + tag;
                    isTag = false;
                    i = end;
                    end = 0;
                } else {
                    --len;
                    result = result + c;
                }
            }

            String endTag;
            for(Iterator var19 = tags.iterator(); var19.hasNext(); result = result + "</" + endTag + ">") {
                endTag = (String)var19.next();
            }

            if(i < length) {
                result = result + tail;
            }

            return result;
        } else {
            return str;
        }
    }

    public static String getProperty(String property) {
        return property.contains("_")?property.replaceAll("_", "\\."):property;
    }

    public static String getEncodePra(String property) {
        String trem = "";
        if(isNotEmpty(property)) {
            try {
                trem = URLDecoder.decode(property, "UTF-8");
            } catch (UnsupportedEncodingException var3) {
                var3.printStackTrace();
            }
        }

        return trem;
    }

    public boolean isDigit(String strNum) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher(strNum);
        return matcher.matches();
    }

    public String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        return matcher.find()?matcher.group(0):"";
    }

    public String splitNotNumber(String content) {
        Pattern pattern = Pattern.compile("\\D+");
        Matcher matcher = pattern.matcher(content);
        return matcher.find()?matcher.group(0):"";
    }

    public static boolean contains(String[] stringArray, String source) {
        List<String> tempList = Arrays.asList(stringArray);
        return tempList.contains(source);
    }

    public static String formatHtml(String str) throws Exception {
        Document document = null;
        document = DocumentHelper.parseText(str);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        StringWriter writer = new StringWriter();
        HTMLWriter htmlWriter = new HTMLWriter(writer, format);
        htmlWriter.write(document);
        htmlWriter.close();
        return writer.toString();
    }

    public static String firstUpperCase(String realName) {
        return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toUpperCase());
    }

    public static String firstLowerCase(String realName) {
        return StringUtils.replaceChars(realName, realName.substring(0, 1), realName.substring(0, 1).toLowerCase());
    }

    public static boolean isJavaClass(Class<?> clazz) {
        boolean isBaseClass = false;
        if(clazz.isArray()) {
            isBaseClass = false;
        } else if(clazz.isPrimitive() || clazz.getPackage() == null || clazz.getPackage().getName().equals("java.lang") || clazz.getPackage().getName().equals("java.math") || clazz.getPackage().getName().equals("java.util")) {
            isBaseClass = true;
        }

        return isBaseClass;
    }

    public static String getEmptyString() {
        return "";
    }
}
