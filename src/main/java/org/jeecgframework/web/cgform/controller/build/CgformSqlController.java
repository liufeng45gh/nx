//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.build;

import com.thoughtworks.xstream.XStream;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.dao.config.CgFormVersionDao;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.pojo.config.CgFormHeadPojo;
import org.jeecgframework.web.cgform.service.migrate.MigrateForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgformSqlController"})
public class CgformSqlController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgformSqlController.class);
    @Autowired
    private CgFormVersionDao cgFormVersionDao;
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public CgformSqlController() {
    }

    @RequestMapping(
            params = {"doMigrateOut"}
    )
    public void doMigrateOut(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");

        try {
            List<DBTable> dbTables = MigrateForm.buildExportDbTableList(ids, this.jdbcTemplate);
            String ls_id = "";
            if(ids.indexOf(",") > 0) {
                ls_id = ids.substring(0, ids.indexOf(","));
            } else {
                ls_id = ids;
            }

            CgFormHeadEntity cgFormHeadEntity = this.cgFormVersionDao.getCgFormById(ls_id);
            String ls_filename = cgFormHeadEntity.getTableName();
            String destFileDir = ResourceUtil.getSystempPath() + "/" + ls_filename;
            MigrateForm.generateXmlDataOutFlieContent(dbTables, destFileDir);
            ls_filename = MigrateForm.zip((String)null, "", destFileDir);
            File file = new File(ls_filename);
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
        } catch (Exception var15) {
            var15.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"toCgformMigrate"}
    )
    public ModelAndView toCgformMigrate(HttpServletRequest req) {
        return new ModelAndView("jeecg/cgform/config/cgformMigrateSqlInclude");
    }

    @RequestMapping(
            params = {"doMigrateIn"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
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

            label48:
            while(true) {
                List dbTables;
                do {
                    do {
                        if(!var15.hasNext()) {
                            break label48;
                        }

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
                        dbTables = (List)xStream.fromXML(new File(sqlfilename));
                    } while(dbTables.isEmpty());
                } while(dbTables.size() <= 0);

                Iterator var26 = dbTables.iterator();

                while(var26.hasNext()) {
                    DBTable dbTable = (DBTable)var26.next();
                    this.mergeMigrateInComponent(dbTable);
                }
            }
        } catch (Exception var27) {
            var27.printStackTrace();
            LogUtil.error(var27.toString());
            message = var27.toString();
        }

        if(StringUtil.isNotEmpty(message)) {
            j.setMsg("SQL文件导入失败," + message);
        } else {
            j.setMsg("SQL文件导入成功");
        }

        return j;
    }

    private <T> void mergeMigrateInComponent(DBTable<T> dbTable) {
        Class<T> clazz = dbTable.getClass1();
        if(clazz != null) {
            List<T> dataList = dbTable.getTableData();
            if(dataList != null && dataList.size() >= 1) {
                Map<String, String> idMap = new HashMap();
                String id = "";
                String countSql = "";
                new ArrayList();
                Iterator var10 = dataList.iterator();

                while(true) {
                    while(var10.hasNext()) {
                        T t = (T) var10.next();
                        ReflectHelper reflectHelper = new ReflectHelper(t);
                        List<String> ignores = new ArrayList();
                        ignores.add("class");
                        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
                        PropertyDescriptor[] var17 = pds;
                        int var16 = pds.length;

                        for(int var15 = 0; var15 < var16; ++var15) {
                            PropertyDescriptor pd = var17[var15];
                            if(reflectHelper.getMethodValue(pd.getName()) == null) {
                                ignores.add(pd.getName());
                            }
                        }

                        if(t instanceof CgFormHeadPojo) {
                            reflectHelper.setMethodValue("isDbsynch", "N");
                        }

                        id = (String)reflectHelper.getMethodValue("id");
                        countSql = "select id from " + dbTable.getTableName() + " where id=?";
                        if(t instanceof CgFormHeadPojo) {
                            countSql = "select id from " + dbTable.getTableName() + " where table_name=?";
                            id = oConvertUtils.getString(reflectHelper.getMethodValue("tableName"));
                        }

                        List<Map<String, Object>> idList = this.jdbcTemplate.queryForList(countSql, new Object[]{id});
                        SqlParameterSource sqlParameterSource = MigrateForm.generateParameterMap(t, ignores);
                        if(idList.size() > 0 && t instanceof CgFormHeadPojo) {
                            idMap.put("id", oConvertUtils.getString(((Map)idList.get(0)).get("id")));
                            this.namedJdbcTemplate.update("delete from cgform_field where table_id=:id", idMap);
                            this.namedJdbcTemplate.update("delete from cgform_head where id=:id", idMap);
                            this.namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                        } else if(idList.size() > 0) {
                            this.namedJdbcTemplate.update(MigrateForm.generateUpdateSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                        } else {
                            this.namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
                        }
                    }

                    return;
                }
            }
        }
    }
}
