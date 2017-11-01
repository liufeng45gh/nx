//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldVO;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.jeecgframework.web.cgform.service.impl.config.util.FieldNumComparator;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgFormHeadController"})
public class CgFormHeadController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgFormHeadController.class);
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    @Autowired
    private CgFormIndexServiceI cgFormIndexService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private TempletContext templetContext;

    public CgFormHeadController() {
    }

    @RequestMapping(
            params = {"cgFormHeadList"}
    )
    public ModelAndView cgFormHeadList(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/config/cgFormHeadList");
    }

    @RequestMapping(
            params = {"cgForms"}
    )
    public ModelAndView cgForms(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/config/cgForms");
    }

    @RequestMapping(
            params = {"goCgFormSynChoice"}
    )
    public ModelAndView goCgFormSynChoice(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/config/cgformSynChoice");
    }

    @RequestMapping(
            params = {"popmenulink"}
    )
    public ModelAndView popmenulink(ModelMap modelMap, @RequestParam String url, @RequestParam String title, HttpServletRequest request) {
        modelMap.put("title", title);
        modelMap.put("url", url);
        return new ModelAndView("jeecg/cgform/config/popmenulink");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgFormHeadEntity cgFormHead, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgFormHeadEntity.class, dataGrid);
        String jformCategory = request.getParameter("jformCategory");
        if(StringUtil.isNotEmpty(jformCategory)) {
            cq.eq("jformCategory", jformCategory);
            cq.add();
        }

        HqlGenerateUtil.installHql(cq, cgFormHead);
        this.cgFormFieldService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(CgFormHeadEntity cgFormHead, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        cgFormHead = (CgFormHeadEntity)this.systemService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
        String message = "删除成功";
        this.cgFormFieldService.deleteCgForm(cgFormHead);
        this.cgFormFieldService.removeSubTableStr4Main(cgFormHead);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"rem"}
    )
    @ResponseBody
    public AjaxJson rem(CgFormHeadEntity cgFormHead, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        cgFormHead = (CgFormHeadEntity)this.systemService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
        String message = "移除成功";
        this.cgFormFieldService.delete(cgFormHead);
        this.cgFormFieldService.removeSubTableStr4Main(cgFormHead);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"delField"}
    )
    @ResponseBody
    public AjaxJson delField(CgFormFieldEntity cgFormField, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        cgFormField = (CgFormFieldEntity)this.systemService.getEntity(CgFormFieldEntity.class, cgFormField.getId());
        String message = cgFormField.getFieldName() + "删除成功";
        this.cgFormFieldService.delete(cgFormField);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doDbSynch"}
    )
    @ResponseBody
    public AjaxJson doDbSynch(CgFormHeadEntity cgFormHead, String synMethod, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        cgFormHead = (CgFormHeadEntity)this.systemService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());

        try {
            boolean bl = this.cgFormFieldService.dbSynch(cgFormHead, synMethod);
            String message;
            if(bl) {
                this.cgFormFieldService.appendSubTableStr4Main(cgFormHead);
                message = "同步成功";
                j.setMsg(message);
                return j;
            } else {
                message = "同步失败";
                j.setMsg(message);
                return j;
            }
        } catch (BusinessException var7) {
            j.setMsg(var7.getMessage());
            return j;
        }
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(CgFormHeadEntity cgFormHead, HttpServletRequest request) {
        String message = "";
        this.templetContext.clearCache();
        AjaxJson j = new AjaxJson();
        CgFormHeadEntity oldTable = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
        this.cgFormFieldService.removeSubTableStr4Main(oldTable);
        StringBuffer msg = new StringBuffer();
        CgFormHeadEntity table = this.judgeTableIsNotExit(cgFormHead, oldTable, msg);
        message = msg.toString();
        this.refreshFormFieldOrderNum(cgFormHead);
        List formFieldEntities;
        CgFormFieldEntity cgFormFieldEntity;
        Iterator var10;
        if(StringUtil.isNotEmpty(cgFormHead.getId()) && table != null) {
            formFieldEntities = table.getColumns();
            var10 = formFieldEntities.iterator();

            while(var10.hasNext()) {
                cgFormFieldEntity = (CgFormFieldEntity)var10.next();
                if(StringUtil.isEmpty(cgFormFieldEntity.getOldFieldName()) && StringUtil.isNotEmpty(cgFormFieldEntity.getFieldName())) {
                    cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().toLowerCase());
                    cgFormFieldEntity.setOldFieldName(cgFormFieldEntity.getFieldName());
                }
            }

            boolean isChange = this.cgFormIndexService.updateIndexes(cgFormHead);
            this.cgFormFieldService.updateTable(table, (String)null, isChange);
            this.cgFormFieldService.appendSubTableStr4Main(table);
            this.cgFormFieldService.sortSubTableStr(table);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } else if(StringUtil.isEmpty(cgFormHead.getId()) && table == null) {
            formFieldEntities = cgFormHead.getColumns();
            var10 = formFieldEntities.iterator();

            while(var10.hasNext()) {
                cgFormFieldEntity = (CgFormFieldEntity)var10.next();
                if(StringUtil.isEmpty(cgFormFieldEntity.getOldFieldName())) {
                    cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().toLowerCase());
                    cgFormFieldEntity.setOldFieldName(cgFormFieldEntity.getFieldName());
                }
            }

            this.cgFormFieldService.saveTable(cgFormHead);
            this.cgFormIndexService.updateIndexes(cgFormHead);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(message);
        return j;
    }

    private void refreshFormFieldOrderNum(CgFormHeadEntity cgFormHead) {
        Collections.sort(cgFormHead.getColumns(), new FieldNumComparator());

        for(int i = 0; i < cgFormHead.getColumns().size(); ++i) {
            ((CgFormFieldEntity)cgFormHead.getColumns().get(i)).setOrderNum(Integer.valueOf(i + 1));
        }

    }

    private CgFormHeadEntity judgeTableIsNotExit(CgFormHeadEntity cgFormHead, CgFormHeadEntity oldTable, StringBuffer msg) {
        String message = "";
        CgFormHeadEntity table = (CgFormHeadEntity)this.cgFormFieldService.findUniqueByProperty(CgFormHeadEntity.class, "tableName", cgFormHead.getTableName());
        if(StringUtil.isNotEmpty(cgFormHead.getId())) {
            if(table != null && !oldTable.getTableName().equals(cgFormHead.getTableName())) {
                message = "重命名的表已经存在";
                table = null;
            } else {
                if(table == null) {
                    cgFormHead.setIsDbSynch("N");
                }

                table = table == null?oldTable:table;

                try {
                    MyBeanUtils.copyBeanNotNull2Bean(cgFormHead, table);
                } catch (Exception var7) {
                    var7.printStackTrace();
                }

                message = "修改成功";
            }
        } else {
            message = table != null?"表已经存在":"创建成功";
        }

        msg.append(message);
        return table;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(CgFormHeadEntity cgFormHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgFormHead.getId())) {
            cgFormHead = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
            req.setAttribute("cgFormHeadPage", cgFormHead);
        }

        List<TSType> typeList = (List)ResourceUtil.allTypes.get(MutiLangUtil.getMutiLangInstance().getLang("bdfl"));
        req.setAttribute("typeList", typeList);
        return new ModelAndView("jeecg/cgform/config/cgFormHead");
    }

    @RequestMapping(
            params = {"getColumnList"}
    )
    @ResponseBody
    public List<CgFormFieldEntity> getColumnList(CgFormHeadEntity cgFormHead, String type, HttpServletRequest req) {
        new ArrayList();
        List columnList;
        if(StringUtil.isNotEmpty(cgFormHead.getId())) {
            CriteriaQuery cq = new CriteriaQuery(CgFormFieldEntity.class);
            cq.eq("table.id", cgFormHead.getId());
            cq.add();
            columnList = this.cgFormFieldService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
            Collections.sort(columnList, new FieldNumComparator());
        } else {
            columnList = this.getInitDataList();
        }

        return columnList;
    }

    private List<CgFormFieldEntity> getInitDataList() {
        List<CgFormFieldEntity> columnList = new ArrayList();
        columnList.add(this.initCgFormFieldEntityId());
        columnList.add(this.initCgFormFieldEntityString("create_name", "创建人名称"));
        columnList.add(this.initCgFormFieldEntityString("create_by", "创建人登录名称"));
        columnList.add(this.initCgFormFieldEntityTime("create_date", "创建日期"));
        columnList.add(this.initCgFormFieldEntityString("update_name", "更新人名称"));
        columnList.add(this.initCgFormFieldEntityString("update_by", "更新人登录名称"));
        columnList.add(this.initCgFormFieldEntityTime("update_date", "更新日期"));
        columnList.add(this.initCgFormFieldEntityString("sys_org_code", "所属部门"));
        columnList.add(this.initCgFormFieldEntityString("sys_company_code", "所属公司"));
        columnList.add(this.initCgFormFieldEntityBpmStatus());
        return columnList;
    }

    private CgFormFieldEntity initCgFormFieldEntityId() {
        CgFormFieldEntity field = new CgFormFieldEntity();
        field.setFieldName("id");
        field.setLength(Integer.valueOf(36));
        field.setContent("主键");
        field.setIsKey("Y");
        field.setIsNull("N");
        field.setOrderNum(Integer.valueOf(1));
        field.setType("string");
        field.setPointLength(Integer.valueOf(0));
        field.setIsShow("N");
        field.setIsShowList("N");
        field.setFieldLength(Integer.valueOf(120));
        return field;
    }

    private CgFormFieldEntity initCgFormFieldEntityBpmStatus() {
        CgFormFieldEntity field = new CgFormFieldEntity();
        field.setFieldName("bpm_status");
        field.setLength(Integer.valueOf(32));
        field.setContent("流程状态");
        field.setIsKey("N");
        field.setIsNull("Y");
        field.setOrderNum(Integer.valueOf(1));
        field.setType("string");
        field.setPointLength(Integer.valueOf(0));
        field.setIsShow("N");
        field.setIsShowList("Y");
        field.setFieldLength(Integer.valueOf(120));
        field.setDictField("bpm_status");
        field.setFieldDefault("1");
        return field;
    }

    private CgFormFieldEntity initCgFormFieldEntityString(String fieldName, String content) {
        CgFormFieldEntity field = new CgFormFieldEntity();
        field.setFieldName(fieldName);
        field.setLength(Integer.valueOf(50));
        field.setContent(content);
        field.setIsKey("N");
        field.setIsNull("Y");
        field.setOrderNum(Integer.valueOf(2));
        field.setType("string");
        field.setPointLength(Integer.valueOf(0));
        field.setIsShow("N");
        field.setIsShowList("N");
        field.setFieldLength(Integer.valueOf(120));
        return field;
    }

    private CgFormFieldEntity initCgFormFieldEntityTime(String fieldName, String content) {
        CgFormFieldEntity field = new CgFormFieldEntity();
        field.setFieldName(fieldName);
        field.setLength(Integer.valueOf(20));
        field.setContent(content);
        field.setIsKey("N");
        field.setIsNull("Y");
        field.setOrderNum(Integer.valueOf(3));
        field.setType("Date");
        field.setPointLength(Integer.valueOf(0));
        field.setIsShow("N");
        field.setIsShowList("N");
        field.setFieldLength(Integer.valueOf(120));
        field.setShowType("date");
        return field;
    }

    @RequestMapping(
            params = {"checkIsExit"}
    )
    @ResponseBody
    public AjaxJson checkIsExit(String name, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        j.setSuccess(this.cgFormFieldService.judgeTableIsExit(name).booleanValue());
        return j;
    }

    @RequestMapping(
            params = {"sqlPlugin"}
    )
    public ModelAndView sqlPlugin(String id, HttpServletRequest request) {
        CgFormHeadEntity bean = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, id);
        request.setAttribute("bean", bean);
        return new ModelAndView("jeecg/cgform/config/cgFormSqlPlugin");
    }

    @RequestMapping(
            params = {"sqlPluginSave"}
    )
    @ResponseBody
    public AjaxJson sqlPluginSave(String id, String sql_plug_in, HttpServletRequest request) {
        String message = "";
        CgFormHeadEntity bean = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, id);
        this.cgFormFieldService.updateTable(bean, (String)null, false);
        message = "保存成功";
        this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        AjaxJson j = new AjaxJson();
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"jsPlugin"}
    )
    public ModelAndView jsPlugin(String id, HttpServletRequest request) {
        CgFormHeadEntity bean = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, id);
        request.setAttribute("bean", bean);
        return new ModelAndView("jeecg/cgform/config/cgFormJsPlugin");
    }

    @RequestMapping(
            params = {"jsPluginSave"}
    )
    @ResponseBody
    public AjaxJson jsPluginSave(String id, String js_plug_in, HttpServletRequest request) {
        String message = "";
        CgFormHeadEntity bean = (CgFormHeadEntity)this.cgFormFieldService.getEntity(CgFormHeadEntity.class, id);
        this.cgFormFieldService.updateTable(bean, (String)null, false);
        message = "保存成功";
        this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        AjaxJson j = new AjaxJson();
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"importExcel"}
    )
    @ResponseBody
    public AjaxJson importExcel(String headId, HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var8 = fileMap.entrySet().iterator();

        while(var8.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var8.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            params.setNeedSave(false);

            AjaxJson var26;
            try {
                CgFormHeadEntity cgFormHead = (CgFormHeadEntity)this.systemService.getEntity(CgFormHeadEntity.class, headId);
                if(cgFormHead != null) {
                    List<CgFormFieldVO> fieldList = ExcelImportUtil.importExcel(file.getInputStream(), CgFormFieldVO.class, params);
                    String hql = "from CgFormFieldEntity where table.id = ? ";
                    List<CgFormFieldEntity> list = this.systemService.findHql(hql, new Object[]{headId});
                    if(list == null) {
                        list = new ArrayList();
                    }

                    CgFormFieldEntity fieldEntity = null;
                    StringBuilder sb = new StringBuilder("");
                    List<CgFormFieldEntity> saveList = new ArrayList();
                    Iterator var19 = fieldList.iterator();

                    while(var19.hasNext()) {
                        CgFormFieldVO field = (CgFormFieldVO)var19.next();
                        if(!StringUtil.isEmpty(field.getFieldName())) {
                            if(this.existField(field.getFieldName(), (List)list)) {
                                sb.append(field.getFieldName()).append(",");
                            } else {
                                fieldEntity = new CgFormFieldEntity();
                                fieldEntity.setTable(cgFormHead);
                                fieldEntity.setFieldName(field.getFieldName());
                                String content = field.getContent();
                                if(StringUtil.isEmpty(content)) {
                                    content = field.getFieldName();
                                }

                                fieldEntity.setContent(content);
                                String type = field.getType();
                                if(StringUtil.isEmpty(type)) {
                                    type = "string";
                                }

                                fieldEntity.setType(type);
                                String length = field.getLength();
                                if(StringUtil.isEmpty(length)) {
                                    length = "32";
                                }

                                fieldEntity.setLength(Integer.valueOf(length));
                                String pointLength = field.getPointLength();
                                if(StringUtil.isEmpty(pointLength)) {
                                    pointLength = "0";
                                }

                                fieldEntity.setPointLength(Integer.valueOf(pointLength));
                                fieldEntity.setFieldDefault(field.getFieldDefault());
                                fieldEntity.setIsKey("N");
                                String isNull = field.getIsNull();
                                if("否".equals(isNull)) {
                                    isNull = "N";
                                } else {
                                    isNull = "Y";
                                }

                                fieldEntity.setIsNull(isNull);
                                fieldEntity.setOrderNum(Integer.valueOf(1));
                                fieldEntity.setIsShow("Y");
                                fieldEntity.setIsShowList("Y");
                                fieldEntity.setFieldLength(Integer.valueOf(120));
                                ((List)list).add(fieldEntity);
                                saveList.add(fieldEntity);
                            }
                        }
                    }

                    this.systemService.batchSave(saveList);
                    if(StringUtil.isEmpty(sb.toString())) {
                        j.setMsg("文件导入成功！");
                    } else {
                        j.setMsg("文件导入成功！重复字段【" + sb.toString() + "】忽略");
                    }
                    continue;
                }

                j.setMsg("表数据异常！");
                var26 = j;
            } catch (Exception var35) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var35));
                continue;
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var34) {
                    var34.printStackTrace();
                }

            }

            return var26;
        }

        return j;
    }

    private boolean existField(String field, List<CgFormFieldEntity> list) {
        boolean flag = false;
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            CgFormFieldEntity entity = (CgFormFieldEntity)var5.next();
            if(field.equalsIgnoreCase(entity.getFieldName())) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    @RequestMapping(
            params = {"upload"}
    )
    public String upload(String id, HttpServletRequest request) {
        request.setAttribute("headId", id);
        return "jeecg/cgform/config/cgformColUpload";
    }
}
