//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.generate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.codegenerate.generate.CgformCodeGenerate;
import org.jeecgframework.codegenerate.generate.onetomany.CgformCodeGenerateOneToMany;
import org.jeecgframework.codegenerate.pojo.CreateFileProperty;
import org.jeecgframework.codegenerate.pojo.onetomany.CodeParamEntity;
import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.codegenerate.util.CodeStringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.entity.generate.GenerateEntity;
import org.jeecgframework.web.cgform.entity.generate.GenerateSubListEntity;
import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
import org.jeecgframework.web.cgform.service.button.CgformButtonSqlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.jeecgframework.web.cgform.service.impl.generate.TempletContextWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/generateController"})
public class GenerateController extends BaseController {
    private static final Logger logger = Logger.getLogger(GenerateController.class);
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private CgformButtonServiceI cgformButtonService;
    @Autowired
    private CgformButtonSqlServiceI cgformButtonSqlService;
    @Autowired
    private CgformEnhanceJsServiceI cgformEnhanceJsService;
    @Autowired
    private TempletContextWord templetContextWord;

    public GenerateController() {
    }

    @RequestMapping(
            params = {"gogenerate"}
    )
    public ModelAndView gogenerate(CgFormHeadEntity cgFormHead, HttpServletRequest request) {
        if(!StringUtil.isNotEmpty(cgFormHead.getId())) {
            throw new RuntimeException("表单配置不存在");
        } else {
            cgFormHead = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
            String returnModelAndView = null;
            HashMap entityNameMap = new HashMap(0);
            if(cgFormHead.getJformType().intValue() != 1 && cgFormHead.getJformType().intValue() != 3) {
                List<CgFormHeadEntity> subTableList = new ArrayList();
                if(StringUtil.isNotEmpty(cgFormHead.getSubTableStr())) {
                    String[] subTables = cgFormHead.getSubTableStr().split(",");
                    String[] var10 = subTables;
                    int var9 = subTables.length;

                    for(int var8 = 0; var8 < var9; ++var8) {
                        String subTable = var10[var8];
                        CgFormHeadEntity subHead = this.cgFormFieldService.getCgFormHeadByTableName(subTable);
                        subTableList.add(subHead);
                        entityNameMap.put(subHead.getTableName(), JeecgReadTable.formatFieldCapital(subHead.getTableName()));
                    }
                }

                request.setAttribute("subTableList", subTableList);
                returnModelAndView = "jeecg/cgform/generate/one2many";
            } else {
                returnModelAndView = "jeecg/cgform/generate/single";
            }

            String projectPath = CodeResourceUtil.getProject_path();

            try {
                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(int i = 0; i < cookies.length; ++i) {
                        if(cookies[i].getName().equals("cookie_projectPath")) {
                            String value = cookies[i].getValue();
                            if(value != null && !"".equals(value)) {
                                projectPath = cookies[i].getValue();
                                projectPath = URLDecoder.decode(projectPath, "UTF-8");
                            }
                        }

                        request.setAttribute("projectPath", projectPath);
                    }
                }
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            String entityName = JeecgReadTable.formatFieldCapital(cgFormHead.getTableName());
            entityNameMap.put(cgFormHead.getTableName(), entityName);
            request.setAttribute("cgFormHeadPage", cgFormHead);
            request.setAttribute("entityNames", entityNameMap);
            return new ModelAndView(returnModelAndView);
        }
    }

    @RequestMapping(
            params = {"dogenerate"}
    )
    public void dogenerate(CgFormHeadEntity cgFormHead, GenerateEntity generateEntity, CreateFileProperty createFileProperty, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(StringUtil.isNotEmpty(cgFormHead.getId())) {
            cgFormHead = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
            this.getCgformConfig(cgFormHead, generateEntity);
            AjaxJson j = new AjaxJson();
            String tableName = generateEntity.getTableName();
            String ftlDescription = generateEntity.getFtlDescription();

            try {
                boolean tableexist = (new JeecgReadTable()).checkTableExist(tableName);
                if(tableexist) {
                    CgformCodeGenerate generate = new CgformCodeGenerate(createFileProperty, generateEntity);
                    if(createFileProperty.getJspMode().equals("04")) {
                        String formhtml = this.templetContextWord.autoFormGenerateHtml(tableName, (String)null, (String)null);
                        generate.setCgformJspHtml(formhtml);
                    }

                    generate.generateToFile();
                    j.setMsg(ftlDescription + "：功能生成成功，请刷新项目重启，菜单访问路径：" + CodeStringUtils.getInitialSmall(generateEntity.getEntityName()) + "Controller.do?list");
                } else {
                    j.setMsg("表[" + tableName + "] 在数据库中，不存在");
                }
            } catch (Exception var22) {
                var22.printStackTrace();
                j.setMsg(var22.getMessage());
                throw new RuntimeException(var22.getMessage());
            }

            try {
                String projectPath = URLEncoder.encode(generateEntity.getProjectPath(), "UTF-8");
                Cookie cookie = new Cookie("cookie_projectPath", projectPath);
                cookie.setMaxAge(2592000);
                response.addCookie(cookie);
                response.getWriter().print(j.getJsonStr());
                response.getWriter().flush();
            } catch (IOException var20) {
                var20.printStackTrace();
            } finally {
                try {
                    response.getWriter().close();
                } catch (Exception var19) {
                    ;
                }

            }

        } else {
            throw new RuntimeException("表单配置不存在");
        }
    }

    @RequestMapping(
            params = {"dogenerateOne2Many"}
    )
    @ResponseBody
    public void dogenerateOne2Many(CodeParamEntity codeParamEntityIn, GenerateSubListEntity subTableListEntity, String jspMode, HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        String projectPath;
        try {
            projectPath = codeParamEntityIn.getTableName();
            GenerateEntity mainG = new GenerateEntity();
            mainG.setProjectPath(subTableListEntity.getProjectPath());
            mainG.setPackageStyle(subTableListEntity.getPackageStyle());
            CgFormHeadEntity mCgFormHead = this.cgFormFieldService.getCgFormHeadByTableName(projectPath);
            this.getCgformConfig(mCgFormHead, mainG);
            Map<String, GenerateEntity> subsG = new HashMap();
            List<SubTableEntity> subTabParamIn = subTableListEntity.getSubTabParamIn();
            Iterator var13 = subTabParamIn.iterator();

            while(true) {
                if(!var13.hasNext()) {
                    codeParamEntityIn.setSubTabParam(subTabParamIn);
                    if("06".equals(jspMode)) {
                        CgformCodeGenerateOneToMany.oneToManyCreateBootstap(subTabParamIn, codeParamEntityIn, mainG, subsG);
                    } else {
                        CgformCodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn, mainG, subsG);
                    }

                    j.setMsg(codeParamEntityIn.getFtlDescription() + "：功能生成成功，请刷新项目重启，菜单访问路径：" + CodeStringUtils.getInitialSmall(codeParamEntityIn.getEntityName()) + "Controller.do?list");
                    break;
                }

                SubTableEntity po = (SubTableEntity)var13.next();
                String sTableName = po.getTableName();
                CgFormHeadEntity cgSubHead = this.cgFormFieldService.getCgFormHeadByTableName(sTableName);
                List<CgFormFieldEntity> colums = cgSubHead.getColumns();
                String[] foreignKeys = this.getForeignkeys(colums);
                po.setForeignKeys(foreignKeys);
                GenerateEntity subG = new GenerateEntity();
                this.getCgformConfig(cgSubHead, subG);
                subG.setEntityName(po.getEntityName());
                subG.setEntityPackage(po.getEntityPackage());
                subG.setFieldRowNum(Integer.valueOf(1));
                subG.setFtlDescription(po.getFtlDescription());
                subG.setForeignKeys(foreignKeys);
                subG.setTableName(po.getTableName());
                subG.setProjectPath(subTableListEntity.getProjectPath());
                subG.setPackageStyle(subTableListEntity.getPackageStyle());
                subsG.put(sTableName, subG);
            }
        } catch (Exception var28) {
            var28.printStackTrace();
            j.setMsg(var28.getMessage());
            throw new RuntimeException(var28.getMessage());
        }

        try {
            projectPath = URLEncoder.encode(subTableListEntity.getProjectPath(), "UTF-8");
            Cookie cookie = new Cookie("cookie_projectPath", projectPath);
            cookie.setMaxAge(2592000);
            response.addCookie(cookie);
            response.getWriter().print(j.getJsonStr());
            response.getWriter().flush();
        } catch (IOException var26) {
            var26.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (Exception var25) {
                ;
            }

        }

    }

    private String[] getForeignkeys(List<CgFormFieldEntity> colums) {
        List<String> fs = new ArrayList(0);
        Iterator var4 = colums.iterator();

        while(var4.hasNext()) {
            CgFormFieldEntity c = (CgFormFieldEntity)var4.next();
            if(StringUtil.isNotEmpty(c.getMainTable()) && StringUtil.isNotEmpty(c.getMainField())) {
                fs.add(c.getFieldName().toUpperCase());
            }
        }

        String[] foreignkeys = (String[])fs.toArray(new String[fs.size()]);
        return foreignkeys;
    }

    private void getCgformConfig(CgFormHeadEntity cgFormHead, GenerateEntity generateEntity) throws Exception {
        int filedNums = cgFormHead.getColumns().size();
        List<CgformButtonEntity> buttons = null;
        Map<String, String[]> buttonSqlMap = new LinkedHashMap();
        cgFormHead = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
        buttons = this.cgformButtonService.getCgformButtonListByFormId(cgFormHead.getId());
        Iterator var7 = buttons.iterator();

        CgformButtonSqlEntity cbsDelete;
        while(var7.hasNext()) {
            CgformButtonEntity cb = (CgformButtonEntity)var7.next();
            cbsDelete = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId(cb.getButtonCode(), cgFormHead.getId());
            buttonSqlMap.put(cb.getButtonCode(), cbsDelete == null?new String[0]:cbsDelete.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "").split(";"));
        }

        CgformButtonSqlEntity cbsAdd = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId("add", cgFormHead.getId());
        buttonSqlMap.put("add", cbsAdd == null?new String[0]:cbsAdd.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "").split(";"));
        CgformButtonSqlEntity cbsUpdate = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId("update", cgFormHead.getId());
        buttonSqlMap.put("update", cbsUpdate == null?new String[0]:cbsUpdate.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "").split(";"));
        cbsDelete = this.cgformButtonSqlService.getCgformButtonSqlByCodeFormId("delete", cgFormHead.getId());
        buttonSqlMap.put("delete", cbsDelete == null?new String[0]:cbsDelete.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "").split(";"));
        CgformEnhanceJsEntity listJs = this.cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("list", cgFormHead.getId());
        CgformEnhanceJsEntity listJsCopy = null;

        try {
            listJsCopy = listJs.deepCopy();
        } catch (Exception var17) {
            logger.debug(var17.getMessage());
        }

        CgformEnhanceJsEntity formJs = this.cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("form", cgFormHead.getId());
        CgformEnhanceJsEntity formJsCopy = null;

        try {
            formJsCopy = formJs.deepCopy();
        } catch (Exception var16) {
            logger.debug(var16.getMessage());
        }

        Iterator var14 = cgFormHead.getColumns().iterator();

        while(var14.hasNext()) {
            CgFormFieldEntity field = (CgFormFieldEntity)var14.next();
            String fieldName = field.getFieldName();
            if(listJsCopy != null) {
                listJsCopy.setCgJsStr(listJsCopy.getCgJsStr().replace(fieldName, JeecgReadTable.formatField(fieldName)));
            }

            if(formJsCopy != null) {
                formJsCopy.setCgJsStr(formJsCopy.getCgJsStr().replace(fieldName, JeecgReadTable.formatField(fieldName)));
            }
        }

        generateEntity.setButtons(buttons);
        generateEntity.setButtonSqlMap(buttonSqlMap);
        generateEntity.setCgFormHead(cgFormHead);
        generateEntity.setListJs(listJsCopy);
        generateEntity.setFormJs(formJsCopy);
    }

    @RequestMapping(
            params = {"goFileTree"}
    )
    public ModelAndView goFileTree(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/generate/fileTree");
    }

    @RequestMapping(
            params = {"doExpandFileTree"}
    )
    @ResponseBody
    public Object doExpandFileTree(String parentNode) {
        JSONArray fjson = new JSONArray();

        try {
            int var6;
            if(StringUtil.isEmpty(parentNode)) {
                File[] roots = File.listRoots();
                File[] var7 = roots;
                var6 = roots.length;

                for(int var5 = 0; var5 < var6; ++var5) {
                    File r = var7[var5];
                    JSONObject item = new JSONObject();
                    item.put("id", r.getAbsolutePath());
                    item.put("text", r.getPath());
                    item.put("iconCls", "icon-folder");
                    if(this.hasDirs(r)) {
                        item.put("state", "closed");
                    } else {
                        item.put("state", "open");
                    }

                    fjson.add(item);
                }
            } else {
                try {
                    parentNode = new String(parentNode.getBytes("ISO-8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException var10) {
                    var10.printStackTrace();
                }

                File parent = new File(parentNode);
                File[] chs = parent.listFiles();
                File[] var16 = chs;
                int var15 = chs.length;

                for(var6 = 0; var6 < var15; ++var6) {
                    File r = var16[var6];
                    JSONObject item = new JSONObject();
                    if(r.isDirectory()) {
                        item.put("id", r.getAbsolutePath());
                        item.put("text", r.getPath());
                        if(this.hasDirs(r)) {
                            item.put("state", "closed");
                        } else {
                            item.put("state", "open");
                        }

                        fjson.add(item);
                    }
                }
            }

            return fjson;
        } catch (Exception var11) {
            var11.printStackTrace();
            throw new RuntimeException("该文件夹不可选择");
        }
    }

    private boolean hasDirs(File dir) {
        try {
            return dir.listFiles().length != 0;
        } catch (Exception var3) {
            logger.info(var3.getMessage());
            return false;
        }
    }
}
