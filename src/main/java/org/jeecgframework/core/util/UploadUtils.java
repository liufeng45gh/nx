//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtils {
    public static final String FORM_FIELDS = "form_fields";
    public static final String FILE_FIELDS = "file_fields";
    private long maxSize = 1000000L;
    private Map<String, String> extMap = new HashMap();
    private String basePath = "upload";
    private String dirName = "images";
    private static final String TEMP_PATH = "/temp";
    private String tempPath;
    private String fileName;
    private String savePath;
    private String saveUrl;
    private String fileUrl;

    public UploadUtils() {
        this.tempPath = this.basePath + "/temp";
        this.extMap.put("images", "gif,jpg,jpeg,png,bmp");
        this.extMap.put("flashs", "swf,flv");
        this.extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        this.extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
    }

    public String[] uploadFile(HttpServletRequest request) {
        String[] infos = new String[5];
        infos[0] = this.validateFields(request);
        Map<String, Object> fieldsMap = new HashMap();
        if(infos[0].equals("true")) {
            fieldsMap = this.initFields(request);
        }

        List<FileItem> fiList = (List)((Map)fieldsMap).get("file_fields");
        if(fiList != null) {
            FileItem item;
            for(Iterator var6 = fiList.iterator(); var6.hasNext(); infos[1] = this.saveFile(item)) {
                item = (FileItem)var6.next();
            }

            infos[2] = this.savePath;
            infos[3] = this.saveUrl;
            infos[4] = this.fileUrl;
        }

        return infos;
    }

    private String validateFields(HttpServletRequest request) {
        String errorInfo = "true";
        String contentType = request.getContentType();
        int contentLength = request.getContentLength();
        this.savePath = request.getSession().getServletContext().getRealPath("/") + this.basePath + "/";
        this.saveUrl = request.getContextPath() + "/" + this.basePath + "/";
        File uploadDir = new File(this.savePath);
        if(contentType != null && contentType.startsWith("multipart")) {
            if(this.maxSize < (long)contentLength) {
                LogUtil.info("上传文件大小超出文件最大大小");
                errorInfo = "上传文件大小超出文件最大大小[" + this.maxSize + "]";
            } else if(!ServletFileUpload.isMultipartContent(request)) {
                errorInfo = "请选择文件";
            } else if(!uploadDir.isDirectory()) {
                errorInfo = "上传目录[" + this.savePath + "]不存在";
            } else if(!uploadDir.canWrite()) {
                errorInfo = "上传目录[" + this.savePath + "]没有写权限";
            } else if(!this.extMap.containsKey(this.dirName)) {
                errorInfo = "目录名不正确";
            } else {
                this.savePath = this.savePath + this.dirName + "/";
                this.saveUrl = this.saveUrl + this.dirName + "/";
                File saveDirFile = new File(this.savePath);
                if(!saveDirFile.exists()) {
                    saveDirFile.mkdirs();
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String ymd = sdf.format(new Date());
                this.savePath = this.savePath + ymd + "/";
                this.saveUrl = this.saveUrl + ymd + "/";
                File dirFile = new File(this.savePath);
                if(!dirFile.exists()) {
                    dirFile.mkdirs();
                }

                this.tempPath = request.getSession().getServletContext().getRealPath("/") + this.tempPath + "/";
                File file = new File(this.tempPath);
                if(!file.exists()) {
                    file.mkdirs();
                }
            }
        } else {
            LogUtil.info("请求不包含multipart/form-data流");
            errorInfo = "请求不包含multipart/form-data流";
        }

        return errorInfo;
    }

    private Map<String, Object> initFields(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(10485760);
            factory.setRepository(new File(this.tempPath));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setSizeMax(this.maxSize);
            List items = null;

            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException var13) {
                var13.printStackTrace();
            }

            if(items != null && items.size() > 0) {
                Iterator<FileItem> iter = items.iterator();
                List<FileItem> list = new ArrayList();
                HashMap fields = new HashMap();

                while(iter.hasNext()) {
                    FileItem item = (FileItem)iter.next();
                    if(item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        fields.put(name, value);
                    } else {
                        list.add(item);
                    }
                }

                map.put("form_fields", fields);
                map.put("file_fields", list);
            }
        }

        return map;
    }

    private String saveFile(FileItem item) {
        String error = "true";
        String fileName = item.getName();
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(item.getSize() > this.maxSize) {
            error = "上传文件大小超过限制";
        } else if(!Arrays.asList(((String)this.extMap.get(this.dirName)).split(",")).contains(fileExt)) {
            error = "上传文件扩展名是不允许的扩展名。\n只允许" + (String)this.extMap.get(this.dirName) + "格式。";
        } else {
            String newFileName;
            if("".equals(fileName.trim())) {
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                newFileName = df.format(new Date()) + "_" + (new Random()).nextInt(1000) + "." + fileExt;
            } else {
                newFileName = fileName + "." + fileExt;
            }

            this.fileUrl = this.saveUrl + newFileName;

            try {
                File uploadedFile = new File(this.savePath, newFileName);
                item.write(uploadedFile);
            } catch (IOException var7) {
                var7.printStackTrace();
                LogUtil.info("上传失败了！！！");
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

        return error;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public String getSaveUrl() {
        return this.saveUrl;
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public Map<String, String> getExtMap() {
        return this.extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
        this.tempPath = basePath + "/temp";
    }

    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getTempPath() {
        return this.tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
