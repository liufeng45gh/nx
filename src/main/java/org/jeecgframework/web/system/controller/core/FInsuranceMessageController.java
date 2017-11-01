//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ssb.warmline.business.commons.utils.DateUtils;

@Scope("prototype")
@Controller
@RequestMapping({"/fInsuranceMessageController"})
public class FInsuranceMessageController extends BaseController {
    public FInsuranceMessageController() {
    }

    @RequestMapping(
            params = {"showPic"}
    )
    public void showPic(HttpServletRequest request, String path, HttpServletResponse response) {
        FileInputStream fis = null;
        response.setContentType("image/" + FileUtils.getExtend(path));

        try {
            String uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
            String curpath = uploadbasepath + "/";
            String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + curpath + "/files/";
            File file = new File(realPath);
            if(!file.exists()) {
                file.mkdirs();
            }

            File imageFile = new File(file, path);
            OutputStream out = response.getOutputStream();
            fis = new FileInputStream(imageFile);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception var20) {
            var20.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }
            }

        }

    }

    @RequestMapping(
            params = {"uploadPic"}
    )
    @ResponseBody
    public AjaxJson uploadPic(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
        String copypath = ResourceUtil.getConfigByName("fileCopeyPath");
        String curpath = uploadbasepath + "/";
        String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + curpath + "/files/";
        File fileDir = new File(realPath);
        File copyDir = new File(copypath);
        if(!fileDir.exists()) {
            fileDir.mkdirs();
        }

        if(!copyDir.exists()) {
            copyDir.mkdirs();
        }

        File picTempFile = null;
        File copyImageFile = null;
        Iterator var15 = fileMap.entrySet().iterator();

        while(var15.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var15.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            String fileNametemp = FileUtils.getExtend(file.getOriginalFilename());
            String fileName = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8) + "." + FileUtils.getExtend(file.getOriginalFilename());
            picTempFile = new File(fileDir, fileName);
            copyImageFile = new File(copyDir, fileName);

            try {
                if(picTempFile.exists()) {
                    org.apache.commons.io.FileUtils.forceDelete(picTempFile);
                }

                FileCopyUtils.copy(file.getBytes(), picTempFile);
                FileCopyUtils.copy(file.getBytes(), copyImageFile);
            } catch (Exception var20) {
                var20.printStackTrace();
                j.setMsg("缩略图上传失败！");
                j.setSuccess(false);
            }

            j.setObj(picTempFile.getName());
            j.setMsg("缩略图上传成功！");
            j.setSuccess(true);
        }

        return j;
    }
}
