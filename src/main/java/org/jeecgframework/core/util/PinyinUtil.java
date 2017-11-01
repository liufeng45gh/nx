//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    public PinyinUtil() {
    }

    public static String[] stringToPinyin(String src) {
        return stringToPinyin(src, false, (String)null);
    }

    public static String[] stringToPinyin(String src, String separator) {
        return stringToPinyin(src, true, separator);
    }

    public static String[] stringToPinyin(String src, boolean isPolyphone, String separator) {
        if(!"".equals(src) && src != null) {
            char[] srcChar = src.toCharArray();
            int srcCount = srcChar.length;
            String[] srcStr = new String[srcCount];

            for(int i = 0; i < srcCount; ++i) {
                srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);
            }

            return srcStr;
        } else {
            return null;
        }
    }

    public static String charToPinyin(char src, boolean isPolyphone, String separator) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuffer tempPinying = new StringBuffer();
        if(src > 128) {
            try {
                String[] strs = PinyinHelper.toHanyuPinyinStringArray(src, defaultFormat);
                if(isPolyphone && separator != null) {
                    for(int i = 0; i < strs.length; ++i) {
                        tempPinying.append(strs[i]);
                        if(strs.length != i + 1) {
                            tempPinying.append(separator);
                        }
                    }
                } else {
                    tempPinying.append(strs[0]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination var7) {
                var7.printStackTrace();
            }
        } else {
            tempPinying.append(src);
        }

        return tempPinying.toString();
    }

    public static String hanziToPinyin(String hanzi) {
        return hanziToPinyin(hanzi, " ");
    }

    public static String hanziToPinyin(String hanzi, String separator) {
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyingStr = "";

        try {
            pinyingStr = PinyinHelper.toHanyuPinyinString(hanzi, defaultFormat, separator);
        } catch (BadHanyuPinyinOutputFormatCombination var5) {
            var5.printStackTrace();
        }

        return pinyingStr;
    }

    public static String stringArrayToString(String[] str, String separator) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < str.length; ++i) {
            sb.append(str[i]);
            if(str.length != i + 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String stringArrayToString(String[] str) {
        return stringArrayToString(str, "");
    }

    public static String charArrayToString(char[] ch, String separator) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < ch.length; ++i) {
            sb.append(ch[i]);
            if(ch.length != i + 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    public static String charArrayToString(char[] ch) {
        return charArrayToString(ch, " ");
    }

    public static char[] getHeadByChar(char src, boolean isCapital) {
        if(src <= 128) {
            return new char[]{src};
        } else {
            String[] pinyingStr = PinyinHelper.toHanyuPinyinStringArray(src);
            int polyphoneSize = pinyingStr.length;
            char[] headChars = new char[polyphoneSize];
            int i = 0;
            String[] var9 = pinyingStr;
            int var8 = pinyingStr.length;

            for(int var7 = 0; var7 < var8; ++var7) {
                String s = var9[var7];
                char headChar = s.charAt(0);
                if(isCapital) {
                    headChars[i] = Character.toUpperCase(headChar);
                } else {
                    headChars[i] = headChar;
                }

                ++i;
            }

            return headChars;
        }
    }

    public static char[] getHeadByChar(char src) {
        return getHeadByChar(src, true);
    }

    public static String[] getHeadByString(String src) {
        return getHeadByString(src, true);
    }

    public static String[] getHeadByString(String src, boolean isCapital) {
        return getHeadByString(src, isCapital, (String)null);
    }

    public static String[] getHeadByString(String src, boolean isCapital, String separator) {
        char[] chars = src.toCharArray();
        String[] headString = new String[chars.length];
        int i = 0;
        char[] var9 = chars;
        int var8 = chars.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            char ch = var9[var7];
            char[] chs = getHeadByChar(ch, isCapital);
            StringBuffer sb = new StringBuffer();
            if(separator != null) {
                int j = 1;
                char[] var16 = chs;
                int var15 = chs.length;

                for(int var14 = 0; var14 < var15; ++var14) {
                    char ch1 = var16[var14];
                    sb.append(ch1);
                    if(j != chs.length) {
                        sb.append(separator);
                    }

                    ++j;
                }
            } else {
                sb.append(chs[0]);
            }

            headString[i] = sb.toString();
            ++i;
        }

        return headString;
    }

    public static String getPinYin(String src) {
        //char[] t1 = null;
        char[] t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;

        try {
            for(int i = 0; i < t0; ++i) {
                if(Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 = t4 + t2[0];
                } else {
                    t4 = t4 + Character.toString(t1[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination var7) {
            var7.printStackTrace();
        }

        return t4;
    }

    public static String getPinYinHeadChar(String str) {
        String convert = "";

        for(int j = 0; j < str.length(); ++j) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if(pinyinArray != null) {
                convert = convert + pinyinArray[0].charAt(0);
            } else {
                convert = convert + word;
            }
        }

        return convert;
    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();

        for(int i = 0; i < bGBK.length; ++i) {
            strBuf.append(Integer.toHexString(bGBK[i] & 255));
        }

        return strBuf.toString();
    }

    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < nameChar.length; ++i) {
            if(nameChar[i] > 128) {
                try {
                    pinyinName = pinyinName + PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            } else {
                pinyinName = pinyinName + nameChar[i];
            }
        }

        return pinyinName;
    }

    public static String converterToSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for(int i = 0; i < nameChar.length; ++i) {
            if(nameChar[i] > 128) {
                try {
                    pinyinName = pinyinName + PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                } catch (BadHanyuPinyinOutputFormatCombination var6) {
                    var6.printStackTrace();
                }
            } else {
                pinyinName = pinyinName + nameChar[i];
            }
        }

        return pinyinName;
    }

    public static String makeStringByStringSet(Set<String> stringSet) {
        StringBuilder str = new StringBuilder();
        int i = 0;

        for(Iterator var4 = stringSet.iterator(); var4.hasNext(); ++i) {
            String s = (String)var4.next();
            if(i == stringSet.size() - 1) {
                str.append(s);
            } else {
                str.append(s + ",");
            }
        }

        return str.toString().toLowerCase();
    }

    public static Set<String> getPinyin(String src) {
        if(src != null && !src.trim().equalsIgnoreCase("")) {
            char[] srcChar = src.toCharArray();
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            String[][] temp = new String[src.length()][];

            for(int i = 0; i < srcChar.length; ++i) {
                char c = srcChar[i];
                if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
                    } catch (BadHanyuPinyinOutputFormatCombination var7) {
                        var7.printStackTrace();
                    }
                } else if((c < 65 || c > 90) && (c < 97 || c > 122)) {
                    temp[i] = new String[]{""};
                } else {
                    temp[i] = new String[]{String.valueOf(srcChar[i])};
                }
            }

            String[] pingyinArray = Exchange(temp);
            Set<String> pinyinSet = new HashSet();

            for(int i = 0; i < pingyinArray.length; ++i) {
                pinyinSet.add(pingyinArray[i]);
            }

            return pinyinSet;
        } else {
            return null;
        }
    }

    public static String[] Exchange(String[][] strJaggedArray) {
        String[][] temp = DoExchange(strJaggedArray);
        return temp[0];
    }

    private static String[][] DoExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if(len < 2) {
            return strJaggedArray;
        } else {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int Index = 0;

            int i;

            for(i = 0; i < len2; ++i) {
                temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][i];
                ++Index;
            }


            String[][] newArray = new String[len - 1][];

            for(i = 2; i < len; ++i) {
                newArray[i - 1] = strJaggedArray[i];
            }

            newArray[0] = temp;
            return DoExchange(newArray);
        }
    }
}
