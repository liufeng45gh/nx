//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.keyword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SensitiveWordInit {
    static final String separator;
    private String ENCODING = "UTF-8";
    public HashMap sensitiveWordMap;
    static int[] x;

    static {
        separator = File.separator;
        x = new int[15];
    }

    public SensitiveWordInit() {
    }

    public Map initKeyWord(String filePath) {
        try {
            Set<String> keyWordSet = this.readSensitiveWordFile(filePath);
            this.addSensitiveWordToHashMap(keyWordSet);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return this.sensitiveWordMap;
    }

    private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        this.sensitiveWordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        Iterator iterator = keyWordSet.iterator();

        while(iterator.hasNext()) {
            key = (String)iterator.next();
            nowMap = this.sensitiveWordMap;

            for(int i = 0; i < key.length(); ++i) {
                char keyChar = key.charAt(i);
                Object wordMap = ((Map)nowMap).get(Character.valueOf(keyChar));
                if(wordMap != null) {
                    nowMap = (Map)wordMap;
                } else {
                    newWorMap = new HashMap();
                    newWorMap.put("isEnd", "0");
                    ((Map)nowMap).put(Character.valueOf(keyChar), newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1) {
                    ((Map)nowMap).put("isEnd", "1");
                }
            }
        }

    }

    private Set<String> readSensitiveWordFile(String filePath) throws Exception {
        Set<String> set = null;
        File file = new File(filePath);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), this.ENCODING);

        try {
            if(!file.isFile() || !file.exists()) {
                throw new Exception("敏感词库文件不存在");
            }

            set = new HashSet();
            BufferedReader bufferedReader = new BufferedReader(read);
            String txt = null;

            while((txt = bufferedReader.readLine()) != null) {
                set.add(txt);
            }
        } catch (Exception var10) {
            throw var10;
        } finally {
            read.close();
        }

        return set;
    }

    public static void main(String[] args) {
        System.out.println(12);
        System.out.println(x[5]);

        for(int i = 0; i < 10; ++i) {
            if(i != 5) {
                System.out.print(i);
                System.out.println(12);
            }
        }

    }
}
