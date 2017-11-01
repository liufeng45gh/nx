//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.service.migrate.MigrateForm;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/transdata"})
public class JeecgDemoTransDataForXml extends BaseController {
    private static final Logger logger = Logger.getLogger(JeecgDemoTransDataForXml.class);
    @Autowired
    private JdbcDao jdbcDao;
    @Autowired
    private CommonService commonService;

    public JeecgDemoTransDataForXml() {
    }

    @RequestMapping(
            params = {"doMigrateOut"}
    )
    public void doMigrateOut(HttpServletRequest request, HttpServletResponse response) {
        String savePath = ResourceUtil.getSystempPath();
        savePath = savePath + "/test_export_migrate.xml";
        File file = new File(savePath);

        try {
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException var18) {
                    logger.info("创建文件名失败！！");
                    var18.printStackTrace();
                }
            }

            String ids = request.getParameter("ids");
            XStream xStream = new XStream();
            DBTable dbTable = new DBTable();
            dbTable.setTableName("jeecg_demo");
            dbTable.setClass1(JeecgDemo.class);
            String sql = "select * from jeecg_demo";
            if(StringUtil.isNotEmpty(ids) && ids.split(",").length > 0) {
                sql = sql + " where id in ('" + ids.replaceAll(",", "','") + "')";
            }

            List<JeecgDemo> list = this.jdbcDao.find(sql, JeecgDemo.class, (Map)null);
            dbTable.setTableData(list);
            xStream.processAnnotations(DBTable.class);
            FileOutputStream outputStream = new FileOutputStream(savePath);
            Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
            xStream.toXML(dbTable, writer);
            logger.debug("create doMigrateOut file: " + savePath);
            String ls_filename = MigrateForm.zip((String)null, "", savePath);
            file = new File(ls_filename);
            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(ls_filename));
            response.reset();
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            //int bytesRead = false;
            byte[] buffer = new byte[8192];

            int bytesRead;
            while((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
                toClient.write(buffer, 0, bytesRead);
            }

            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            fis.close();
        } catch (Exception var19) {
            var19.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toMigrate"}
    )
    public ModelAndView toCgformMigrate(HttpServletRequest req) {
        return new ModelAndView("jeecg/demo/jeecgDemo/cgformMigrateInclude");
    }

    @ResponseBody
    @RequestMapping(
            params = {"doMigrateIn"}
    )
    public AjaxJson doMigrateIn(HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String ls_file = "";
        UploadFile uploadFile = new UploadFile(request, ls_file);
        uploadFile.setCusPath("");
        uploadFile.setSwfpath("");
        String uploadbasepath = uploadFile.getBasePath();
        if(uploadbasepath == null) {
            uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
        }

        String path = uploadbasepath + "\\";
        String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("\\") + path;
        message = null;

        try {
            File file = new File(realPath);
            if(!file.exists()) {
                file.mkdir();
            }

            uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
            MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
            Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
            String fileName = "";
            Iterator var15 = fileMap.entrySet().iterator();

            while(var15.hasNext()) {
                Entry<String, MultipartFile> entity = (Entry)var15.next();
                MultipartFile mf = (MultipartFile)entity.getValue();
                fileName = mf.getOriginalFilename();
                String savePath = realPath + fileName;
                File savefile = new File(savePath);
                String ls_tmp = savefile.getName();
                FileCopyUtils.copy(mf.getBytes(), savefile);
                MigrateForm.unzip(savePath, "");
                String sqlFileDir = realPath + ls_tmp.substring(0, ls_tmp.lastIndexOf("."));
                File sqlDirFile = new File(sqlFileDir);
                String sqlfilename = sqlDirFile.getPath() + "/";
                if(sqlDirFile.isDirectory()) {
                    sqlfilename = sqlfilename + sqlDirFile.list()[0];
                }

                XStream xStream = new XStream();
                xStream.processAnnotations(DBTable.class);
                DBTable dbTable = (DBTable)xStream.fromXML(new File(sqlfilename));
                if(dbTable.getClass1() != null) {
                    this.commonService.batchSave(dbTable.getTableData());
                }
            }
        } catch (Exception var25) {
            LogUtil.error(var25.toString());
            message = var25.toString();
        }

        if(StringUtil.isNotEmpty(message)) {
            j.setMsg("SQL文件导入失败," + message);
        } else {
            j.setMsg("SQL文件导入成功");
        }

        return j;
    }
}
