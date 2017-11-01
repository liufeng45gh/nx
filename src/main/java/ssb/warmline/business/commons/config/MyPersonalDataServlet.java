//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ssb.warmline.business.commons.enums.PhotoType;
import ssb.warmline.business.commons.utils.CommonUtils;
import ssb.warmline.business.commons.utils.DateUtils;
import ssb.warmline.business.commons.utils.UUIDUtil;
import ssb.warmline.business.entity.worderphotomain.WOrderPhotoMainEntity;
import ssb.warmline.business.service.tsbaseuser.TSBaseUserServiceI;
import ssb.warmline.business.service.worderphotomain.WOrderPhotoMainServiceI;

public class MyPersonalDataServlet extends HttpServlet {
    protected static final Logger logger = LoggerFactory.getLogger(MyPersonalDataServlet.class);
    static final String separator;
    private ApplicationContext context = null;

    static {
        separator = File.separator;
    }

    public MyPersonalDataServlet() {
    }

    public void init() throws ServletException {
        this.context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.uploadPersonageImgae(req, resp);
    }

    public void uploadPersonageImgae(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TSBaseUserServiceI tSBaseUserService = (TSBaseUserServiceI)this.context.getBean(TSBaseUserServiceI.class);
        Map<String, Object> jsonMap = new HashMap();
        String uid = request.getParameter("uid");
        String token = request.getParameter("token");
        String imageType = request.getParameter("type");
        String imageTypes = request.getParameter("types");
        String oldPhotoMainId = request.getParameter("photoMainId");

        try {
            if(uid != null && !"".equals(uid)) {
                TSBaseUser tsBaseUser = (TSBaseUser)tSBaseUserService.findUniqueByProperty(TSBaseUser.class, "id", uid);
                if(tsBaseUser != null) {
                    String path;
                    //String path;
                    String uploadPath;
                    String filePath;
                    File headDir;
                    boolean test1;
                    String pathUrlName;
                    if("ios".equals(imageTypes)) {
                        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
                        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
                        MultipartFile file = multipartRequest.getFile("file");
                        String path_temp = request.getSession().getServletContext().getRealPath("");
                        path = path_temp.substring(0, path_temp.lastIndexOf(separator));
                        if("avarl".equals(imageType)) {
                            path = separator + "upload" + separator + "head";
                        } else {
                            path = separator + "upload" + separator + "picture";
                        }

                        uploadPath = path + separator + "upload";
                        filePath = path + path;
                        File uploadDir = new File(uploadPath);
                        headDir = new File(filePath);
                        //boolean test1;
                        if(!uploadDir.exists()) {
                            test1 = uploadDir.mkdir();
                            if(!test1) {
                                response.setCharacterEncoding("utf-8");
                                CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                            } else if(!headDir.exists()) {
                                test1 = headDir.mkdir();
                                if(!test1) {
                                    response.setCharacterEncoding("utf-8");
                                    CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                                }
                            }
                        } else if(!headDir.exists()) {
                            test1 = headDir.mkdir();
                            if(!test1) {
                                response.setCharacterEncoding("utf-8");
                                CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                            }
                        }

                        double fileSize = (double)file.getSize() / 1024.0D / 1024.0D;
                        String fileName = file.getOriginalFilename();
                        String fileSuffix = ".jpg";
                        if(!"".equals(fileName) && "png".equals(fileName)) {
                            fileSuffix = ".png";
                        }

                        String newName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + fileSuffix;
                        String fullFile = filePath + separator + newName;
                        FileOutputStream fileOS = new FileOutputStream(fullFile);
                        fileOS.write(file.getBytes());
                        fileOS.close();
                        pathUrlName = request.getServerName() + ":";
                        //String pathUrlName = null;
                        int port = request.getServerPort();
                        if(443 == port) {
                            pathUrlName = "http://" + pathUrlName + "80";
                        } else {
                            pathUrlName = "http://" + pathUrlName + port;
                        }

                        if("avarl".equals(imageType)) {
                            if(tsBaseUser.getHeadPortrait() != null && !"".equals(tsBaseUser.getHeadPortrait())) {
                                File fileDir = new File(path + separator + tsBaseUser.getHeadPortrait());
                                if(fileDir != null) {
                                    fileDir.delete();
                                }
                            }

                            tsBaseUser.setHeadPortrait(pathUrlName + "/upload/head/" + newName);
                            tSBaseUserService.saveOrUpdate(tsBaseUser);
                            jsonMap.put("paths", "/upload/head/" + newName);
                            jsonMap.put("uid", tsBaseUser.getId());
                            jsonMap.put("token", tsBaseUser.getToken());
                        } else if("picture".equals(imageType)) {
                            WOrderPhotoMainServiceI wOrderPhotoMainService = (WOrderPhotoMainServiceI)this.context.getBean(WOrderPhotoMainServiceI.class);
                            WOrderPhotoMainEntity wOrderPhotoMain = new WOrderPhotoMainEntity();
                            String photoMainId = UUIDUtil.getId();
                            wOrderPhotoMain.setId(photoMainId);
                            wOrderPhotoMain.setCreateTime(new Date());
                            wOrderPhotoMain.setPhotoUrl(pathUrlName + "/upload/picture/" + newName);
                            wOrderPhotoMain.setPhotoName(newName);
                            wOrderPhotoMain.setUid(uid);
                            wOrderPhotoMain.setPhotoType(PhotoType.PHOTOTYPE_000.toString());
                            wOrderPhotoMainService.save(wOrderPhotoMain);
                            jsonMap.put("photoId", wOrderPhotoMain.getId());
                            String newPhotoMainIds = this.delPhotoMain(filePath, oldPhotoMainId, wOrderPhotoMain.getId(), tsBaseUser);
                            tsBaseUser.setPersonalityPhoto(newPhotoMainIds);
                            tSBaseUserService.saveOrUpdate(tsBaseUser);
                            jsonMap.put("uid", tsBaseUser.getId());
                            jsonMap.put("token", tsBaseUser.getToken());
                            jsonMap.put("photoMainId", wOrderPhotoMain.getId());
                        }

                        response.setCharacterEncoding("utf-8");
                        CommonUtils.repsonseToClientWithBody(response, "10000", jsonMap, new String[]{"success"});
                    } else {
                        String fileName_temp = request.getParameter("fileName");
                        int start = fileName_temp.indexOf(".");
                        String fileFormat = fileName_temp.substring(start + 1);
                        ServletInputStream bufferIn = request.getInputStream();
                        path = request.getSession().getServletContext().getRealPath("");
                        path = path.substring(0, path.lastIndexOf(separator));
                        if("avarl".equals(imageType)) {
                            uploadPath = separator + "upload" + separator + "head";
                        } else {
                            uploadPath = separator + "upload" + separator + "picture";
                        }

                        filePath = path + separator + "upload";
                        filePath = path + uploadPath;
                        //headDir = new File(filePath);
                        headDir = new File(filePath);
                        if(!headDir.exists()) {
                            test1 = headDir.mkdir();
                            if(!test1) {
                                response.setCharacterEncoding("utf-8");
                                CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                            } else if(!headDir.exists()) {
                                test1 = headDir.mkdir();
                                if(!test1) {
                                    response.setCharacterEncoding("utf-8");
                                    CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                                }
                            }
                        } else if(!headDir.exists()) {
                            test1 = headDir.mkdir();
                            if(!test1) {
                                response.setCharacterEncoding("utf-8");
                                CommonUtils.repsonseToClientWithBody(response, "20028", jsonMap, new String[]{"error"});
                            }
                        }

                        String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + fileFormat;
                        File file = new File(filePath, fileName);
                        byte[] buffer = new byte[1024];
                        FileOutputStream out = new FileOutputStream(file);

                        for(int len = bufferIn.read(buffer, 0, 1024); len != -1; len = bufferIn.read(buffer, 0, 1024)) {
                            out.write(buffer, 0, len);
                        }

                        out.close();
                        bufferIn.close();
                        String domain = request.getServerName() + ":";
                        pathUrlName = "http://" + domain + "80";
                        if("avarl".equals(imageType)) {
                            if(tsBaseUser.getHeadPortrait() != null && !"".equals(tsBaseUser.getHeadPortrait())) {
                                File fileDir = new File(path + separator + tsBaseUser.getHeadPortrait());
                                if(fileDir != null) {
                                    fileDir.delete();
                                }
                            }

                            tsBaseUser.setHeadPortrait(pathUrlName + "/upload/head/" + fileName);
                            tSBaseUserService.saveOrUpdate(tsBaseUser);
                            jsonMap.put("paths", "/upload/head/" + fileName);
                            jsonMap.put("uid", tsBaseUser.getId());
                            jsonMap.put("token", tsBaseUser.getToken());
                        } else if("picture".equals(imageType)) {
                            WOrderPhotoMainServiceI wOrderPhotoMainService = (WOrderPhotoMainServiceI)this.context.getBean(WOrderPhotoMainServiceI.class);
                            WOrderPhotoMainEntity wOrderPhotoMain = new WOrderPhotoMainEntity();
                            String photoMainId = UUIDUtil.getId();
                            wOrderPhotoMain.setId(photoMainId);
                            wOrderPhotoMain.setCreateTime(new Date());
                            wOrderPhotoMain.setPhotoUrl(pathUrlName + "/upload/picture/" + fileName);
                            wOrderPhotoMain.setPhotoName(fileName);
                            wOrderPhotoMain.setUid(uid);
                            wOrderPhotoMain.setPhotoType(PhotoType.PHOTOTYPE_000.toString());
                            wOrderPhotoMainService.save(wOrderPhotoMain);
                            jsonMap.put("photoId", wOrderPhotoMain.getId());
                            String newPhotoMainIds = this.delPhotoMain(filePath, oldPhotoMainId, wOrderPhotoMain.getId(), tsBaseUser);
                            tsBaseUser.setPersonalityPhoto(newPhotoMainIds);
                            tSBaseUserService.saveOrUpdate(tsBaseUser);
                            jsonMap.put("uid", tsBaseUser.getId());
                            jsonMap.put("token", tsBaseUser.getToken());
                            jsonMap.put("photoMainId", wOrderPhotoMain.getId());
                        }

                        response.setCharacterEncoding("utf-8");
                        CommonUtils.repsonseToClientWithBody(response, "10000", jsonMap, new String[]{"success"});
                    }
                } else {
                    response.setCharacterEncoding("utf-8");
                    CommonUtils.repsonseToClientWithBody(response, "20012", jsonMap, new String[]{"failed"});
                }
            } else {
                response.setCharacterEncoding("utf-8");
                CommonUtils.repsonseToClientWithBody(response, "20027", jsonMap, new String[]{"failed"});
            }
        } catch (Exception var35) {
            var35.printStackTrace();
            response.setCharacterEncoding("utf-8");
            CommonUtils.repsonseToClientWithBody(response, "20022", jsonMap, new String[]{"error"});
        }

    }

    public String delPhotoMain(String filePath, String oldPhotoMainId, String newPhotoMainId, TSBaseUser tsBaseUser) {
        WOrderPhotoMainServiceI wOrderPhotoMainService = (WOrderPhotoMainServiceI)this.context.getBean(WOrderPhotoMainServiceI.class);
        StringBuffer buf = new StringBuffer();
        if(oldPhotoMainId != null && !"".equals(oldPhotoMainId) && !"null".equals(oldPhotoMainId)) {
            WOrderPhotoMainEntity oldOrderPhotoMain = (WOrderPhotoMainEntity)wOrderPhotoMainService.findUniqueByProperty(WOrderPhotoMainEntity.class, "id", oldPhotoMainId);
            if(oldOrderPhotoMain != null) {
                wOrderPhotoMainService.delete(oldOrderPhotoMain);
                File fileDir = new File(filePath + separator + oldOrderPhotoMain.getPhotoName());
                if(fileDir != null) {
                    fileDir.delete();
                }

                String personalityPhoto = tsBaseUser.getPersonalityPhoto();
                String[] photoIds = personalityPhoto.split(",");

                for(int a = 0; photoIds.length > a; ++a) {
                    if(oldPhotoMainId.equals(photoIds[a])) {
                        photoIds[a] = newPhotoMainId;
                    }

                    buf.append(photoIds[a]).append(",");
                }
            }
        } else {
            if(tsBaseUser.getPersonalityPhoto() != null && !"".equals(tsBaseUser.getPersonalityPhoto())) {
                buf.append(tsBaseUser.getPersonalityPhoto());
            }

            buf.append(newPhotoMainId).append(",");
        }

        return buf.toString();
    }
}
