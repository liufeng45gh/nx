//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang3.StringUtils;

public class FileUtils {
    public FileUtils() {
    }

    public static String getExtend(String filename) {
        return getExtend(filename, "");
    }

    public static String getExtend(String filename, String defExt) {
        if(filename != null && filename.length() > 0) {
            int i = filename.lastIndexOf(46);
            if(i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }

        return defExt.toLowerCase();
    }

    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
    }

    public static String getFilePrefix2(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = new FileInputStream(sFile);
        FileOutputStream fos = new FileOutputStream(tFile);
        //int temp = false;
        byte[] buf = new byte[10240];

        try {
            int temp;
            try {
                while((temp = fis.read(buf)) != -1) {
                    fos.write(buf, 0, temp);
                }
            } catch (IOException var17) {
                var17.printStackTrace();
            }
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException var16) {
                var16.printStackTrace();
            }

        }

    }

    public static boolean isPicture(String filename) {
        if(oConvertUtils.isEmpty(filename)) {
            return false;
        } else {
            String tmpName = filename;
            String[][] imgeArray = new String[][]{{"bmp", "0"}, {"dib", "1"}, {"gif", "2"}, {"jfif", "3"}, {"jpe", "4"}, {"jpeg", "5"}, {"jpg", "6"}, {"png", "7"}, {"tif", "8"}, {"tiff", "9"}, {"ico", "10"}};

            for(int i = 0; i < imgeArray.length; ++i) {
                if(imgeArray[i][0].equals(tmpName.toLowerCase())) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isDwg(String filename) {
        if(oConvertUtils.isEmpty(filename)) {
            return false;
        } else {
            String tmpName = getExtend(filename);
            return tmpName.equals("dwg");
        }
    }

    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);
        if(fileDelete.exists() && fileDelete.isFile()) {
            LogUtil.info("--------成功删除文件---------" + strFileName);
            return fileDelete.delete();
        } else {
            LogUtil.info("错误: " + strFileName + "不存在!");
            return false;
        }
    }

    public static String encodingFileName(String fileName) {
        String returnFileName = "";

        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if(returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
            LogUtil.info("Don't support this encoding ...");
        }

        return returnFileName;
    }
}
