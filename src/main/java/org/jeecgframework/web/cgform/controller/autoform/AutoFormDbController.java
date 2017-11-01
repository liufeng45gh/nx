//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.autoform;

import com.alibaba.fastjson.JSONObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.codegenerate.pojo.Columnt;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbFieldEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormDbPage;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormParamEntity;
import org.jeecgframework.web.cgform.entity.autoform.AutoFormStyleEntity;
import org.jeecgframework.web.cgform.service.autoform.AutoFormDbServiceI;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/autoFormDbController"})
public class AutoFormDbController extends BaseController {
    private static final Logger logger = Logger.getLogger(AutoFormDbController.class);
    @Autowired
    private AutoFormDbServiceI autoFormDbService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private DynamicDataSourceServiceI dynamicDataSourceServiceI;

    public AutoFormDbController() {
    }

    @RequestMapping(
            params = {"autoFormDb"}
    )
    public ModelAndView autoFormDb(HttpServletRequest request) {
        String autoFormId = request.getParameter("autoFormId");
        if(oConvertUtils.isNotEmpty(autoFormId)) {
            request.setAttribute("autoFormId", autoFormId);
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDbList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(AutoFormDbEntity autoFormDb, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(AutoFormDbEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, autoFormDb);
        cq.add();
        this.autoFormDbService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(AutoFormDbEntity autoFormDb, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        autoFormDb = (AutoFormDbEntity)this.systemService.getEntity(AutoFormDbEntity.class, autoFormDb.getId());
        String message = "表单数据源删除成功";

        try {
            this.autoFormDbService.delMain(autoFormDb);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "表单数据源删除失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doBatchDel"}
    )
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "表单数据源删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                AutoFormDbEntity autoFormDb = (AutoFormDbEntity)this.systemService.getEntity(AutoFormDbEntity.class, id);
                this.autoFormDbService.delMain(autoFormDb);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "表单数据源删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(AutoFormDbEntity autoFormDb, AutoFormDbPage autoFormDbPage, HttpServletRequest request) {
        List<AutoFormDbFieldEntity> autoFormDbFieldList = autoFormDbPage.getAutoFormDbFieldList();
        List<AutoFormParamEntity> autoFormParamList = autoFormDbPage.getAutoFormParamList();
        AjaxJson j = new AjaxJson();
        String message = "添加成功";

        try {
            this.autoFormDbService.addMain(autoFormDb, autoFormDbFieldList, autoFormParamList);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            var9.printStackTrace();
            message = "表单数据源添加失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(AutoFormDbEntity autoFormDb, AutoFormDbPage autoFormDbPage, HttpServletRequest request) {
        List<AutoFormDbFieldEntity> autoFormDbFieldList = autoFormDbPage.getAutoFormDbFieldList();
        List<AutoFormParamEntity> autoFormParamList = autoFormDbPage.getAutoFormParamList();
        AjaxJson j = new AjaxJson();
        String message = "更新成功";

        try {
            this.autoFormDbService.updateMain(autoFormDb, autoFormDbFieldList, autoFormParamList);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            var9.printStackTrace();
            message = "更新表单数据源失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoFormDb.getId())) {
            autoFormDb = (AutoFormDbEntity)this.autoFormDbService.getEntity(AutoFormDbEntity.class, autoFormDb.getId());
        }

        req.setAttribute("autoFormDbPage", autoFormDb);
        Collection<DynamicDataSourceEntity> dynamicDataSourceEntitys = ResourceUtil.dynamicDataSourceMap.values();
        req.setAttribute("dynamicDataSourceEntitys", dynamicDataSourceEntitys);

        try {
            List<String> tableNames = (new JeecgReadTable()).readAllTableNames();
            req.setAttribute("tableNames", tableNames);
        } catch (SQLException var5) {
            logger.info(var5.getMessage());
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDb-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoFormDb.getId())) {
            autoFormDb = (AutoFormDbEntity)this.autoFormDbService.getEntity(AutoFormDbEntity.class, autoFormDb.getId());
            List<String> tableNames = null;
            if(StringUtils.isNotBlank(autoFormDb.getDbKey()) && "table".equals(autoFormDb.getDbType())) {
                DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(autoFormDb.getDbKey());
                if(dynamicDataSourceEntity != null) {
                    tableNames = DynamicDBUtil.findList(autoFormDb.getDbKey(), SqlUtil.getAllTableSql(dynamicDataSourceEntity.getDbType(), new String[]{"'" + dynamicDataSourceEntity.getDbName() + "'"}), String.class, new Object[0]);
                }
            }

            Collection<DynamicDataSourceEntity> dynamicDataSourceEntitys = ResourceUtil.dynamicDataSourceMap.values();
            req.setAttribute("dynamicDataSourceEntitys", dynamicDataSourceEntitys);
            String dbKey = null;
            if("table".equals(autoFormDb.getDbType())) {
                dbKey = autoFormDb.getDbKey();
            } else if("SQL".equals(autoFormDb.getDbType())) {
                dbKey = autoFormDb.getDbKey();
            }

            if(StringUtils.isBlank(dbKey)) {
                try {
                    tableNames = (new JeecgReadTable()).readAllTableNames();
                } catch (SQLException var7) {
                    ;
                }
            } else {
                DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(dbKey);
                if(dynamicDataSourceEntity != null) {
                    tableNames = DynamicDBUtil.findList(dbKey, SqlUtil.getAllTableSql(dynamicDataSourceEntity.getDbType(), new String[]{"'" + dynamicDataSourceEntity.getDbName() + "'"}), String.class, new Object[0]);
                }
            }

            req.setAttribute("tableNames", tableNames);
            req.setAttribute("autoFormDbPage", autoFormDb);
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDb-update");
    }

    @RequestMapping(
            params = {"autoFormDbFieldList"}
    )
    public ModelAndView autoFormDbFieldList(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoFormDb.getId())) {
            String hql0 = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

            try {
                List<AutoFormDbFieldEntity> autoFormDbFieldEntityList = this.systemService.findHql(hql0, new Object[]{autoFormDb.getId()});
                req.setAttribute("autoFormDbFieldList", autoFormDbFieldEntityList);
            } catch (Exception var5) {
                logger.info(var5.getMessage());
            }
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDbFieldList");
    }

    @RequestMapping(
            params = {"autoFormParamList"}
    )
    public ModelAndView autoFormParamList(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoFormDb.getId())) {
            String hql1 = "from AutoFormParamEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

            try {
                List<AutoFormParamEntity> autoFormParamEntityList = this.systemService.findHql(hql1, new Object[]{autoFormDb.getId()});
                req.setAttribute("autoFormParamList", autoFormParamEntityList);
            } catch (Exception var5) {
                logger.info(var5.getMessage());
            }
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormParamList");
    }

    @RequestMapping(
            params = {"getFields"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object getSqlFields(String sql, String dbKey) {
        List<String> files = null;
        List<String> params = null;
        HashMap reJson = new HashMap();

        try {
            files = this.autoFormDbService.getField(sql, dbKey);
            params = this.autoFormDbService.getSqlParams(sql);
        } catch (Exception var9) {
            var9.printStackTrace();
            String errorInfo = "解析失败!<br><br>失败原因：";
            int i = var9.getMessage().indexOf("Connection refused: connect");
            if(i != -1) {
                errorInfo = errorInfo + "数据源连接失败.";
            } else {
                errorInfo = errorInfo + "当前数据源没有查到数据.";
            }

            reJson.put("status", "error");
            reJson.put("datas", errorInfo);
            return reJson;
        }

        reJson.put("status", "success");
        reJson.put("files", files);
        reJson.put("params", params);
        return reJson;
    }

    @RequestMapping(
            params = {"autoFormDbFieldForTableList"}
    )
    public ModelAndView autoFormDbFieldForTableList(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(autoFormDb.getId())) {
            String hql0 = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

            try {
                List<AutoFormDbFieldEntity> autoFormDbFieldEntityList = this.systemService.findHql(hql0, new Object[]{autoFormDb.getId()});
                req.setAttribute("autoFormDbFieldList", autoFormDbFieldEntityList);
            } catch (Exception var5) {
                logger.info(var5.getMessage());
            }
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDbFieldForTableList");
    }

    @RequestMapping(
            params = {"getAllTableNames"}
    )
    @ResponseBody
    public Object getAllTableNames(String dbKey) {
        Map reJson = new HashMap();
        List tableNames = null;

        try {
            if(StringUtils.isNotBlank(dbKey)) {
                DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(dbKey);
                if(dynamicDataSourceEntity != null) {
                    tableNames = DynamicDBUtil.findList(dbKey, SqlUtil.getAllTableSql(dynamicDataSourceEntity.getDbType(), new String[]{"'" + dynamicDataSourceEntity.getDbName() + "'"}), String.class, new Object[0]);
                }
            } else {
                tableNames = (new JeecgReadTable()).readAllTableNames();
            }
        } catch (Exception var5) {
            reJson.put("status", "error");
            reJson.put("datas", "表查询失败！");
            reJson.put("tableNames", new ArrayList());
        }

        reJson.put("status", "success");
        reJson.put("tableNames", tableNames);
        return reJson;
    }

    @RequestMapping(
            params = {"getTableFields"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public Object getTableFields(String dbKey, String tableName) {
        Map reJson = new HashMap();
        List<String> columnsNames = null;
        if(StringUtils.isNotBlank(dbKey)) {
            DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(dbKey);
            if(dynamicDataSourceEntity != null) {
                columnsNames = DynamicDBUtil.findList(dbKey, SqlUtil.getAllCloumnSql(dynamicDataSourceEntity.getDbType(), new String[]{"'" + tableName + "'", "'" + dynamicDataSourceEntity.getDbName() + "'"}), String.class, new Object[0]);
            }
        } else {
            try {
                List<Columnt> columns = (new JeecgReadTable()).readOriginalTableColumn(tableName);
                columnsNames = new ArrayList();
                Iterator var7 = columns.iterator();

                while(var7.hasNext()) {
                    Columnt column = (Columnt)var7.next();
                    ((List)columnsNames).add(column.getFieldDbName());
                }
            } catch (Exception var8) {
                reJson.put("status", "error");
                reJson.put("datas", "列查询失败！");
            }
        }

        reJson.put("status", "success");
        reJson.put("files", columnsNames);
        return reJson;
    }

    @RequestMapping(
            params = {"goView"}
    )
    public ModelAndView goView(@RequestParam String id, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(id)) {
            AutoFormDbEntity autoFormDbEntity = (AutoFormDbEntity)this.autoFormDbService.getEntity(AutoFormDbEntity.class, id);
            if(autoFormDbEntity != null) {
                String hql1 = "from AutoFormParamEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

                try {
                    List<AutoFormParamEntity> autoFormParamEntityList = this.systemService.findHql(hql1, new Object[]{id});
                    req.setAttribute("autoFormParamList", autoFormParamEntityList);
                } catch (Exception var6) {
                    logger.info(var6.getMessage());
                }
            }

            req.setAttribute("autoFormDbEntity", autoFormDbEntity);
        }

        return new ModelAndView("jeecg/cgform/autoform/autoFormDb-view");
    }

    @RequestMapping(
            params = {"view"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson view(AutoFormDbEntity autoFormDb, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        String msg = "";
        Map<String, String[]> paramMap = req.getParameterMap();
        List<Map<String, Object>> map = null;
        String dbKey = autoFormDb.getDbKey();
        String dbType = autoFormDb.getDbType();
        String dbTableName = autoFormDb.getDbTableName();
        String dbDynSql = autoFormDb.getDbDynSql();
        if("table".equals(dbType)) {
            String hqlField = "from AutoFormDbFieldEntity where 1 = 1 AND aUTO_FORM_DB_ID = ? ";

            try {
                List<AutoFormDbFieldEntity> autoFormDbFieldEntityList = this.systemService.findHql(hqlField, new Object[]{autoFormDb.getId()});
                if(autoFormDbFieldEntityList.size() > 0) {
                    StringBuffer hqlTable = (new StringBuffer()).append("select ");
                    Iterator var15 = autoFormDbFieldEntityList.iterator();

                    while(var15.hasNext()) {
                        AutoFormDbFieldEntity autoFormDbFieldEntity = (AutoFormDbFieldEntity)var15.next();
                        hqlTable.append(autoFormDbFieldEntity.getFieldName() + ",");
                    }

                    hqlTable.deleteCharAt(hqlTable.length() - 1).append(" from " + dbTableName);
                    if("".equals(dbKey)) {
                        map = this.systemService.findForJdbc(hqlTable.toString(), new Object[0]);
                    } else {
                        DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(dbKey);
                        if(dynamicDataSourceEntity != null) {
                            map = DynamicDBUtil.findList(dbKey, hqlTable.toString(), new Object[0]);
                        }
                    }

                    j.setObj(map);
                    msg = "表数据查询成功！";
                } else {
                    j.setSuccess(false);
                    msg = "表属性配置有误！";
                }
            } catch (Exception var17) {
                logger.info(var17.getMessage());
            }
        } else if("sql".equals(dbType)) {
            List<String> params = this.autoFormDbService.getSqlParams(dbDynSql);

            String param;
            String[] paramValue;
            for(Iterator var21 = params.iterator(); var21.hasNext(); dbDynSql = dbDynSql.replaceAll("\\$\\{" + param + "\\}", paramValue[0])) {
                param = (String)var21.next();
                paramValue = (String[])paramMap.get("#" + param);
            }

            if(dbDynSql.contains("\\$")) {
                j.setSuccess(false);
                msg = "动态SQL数据查询失败！";
            } else {
                try {
                    DynamicDataSourceEntity dynamicDataSourceEntity = this.dynamicDataSourceServiceI.getDynamicDataSourceEntityForDbKey(dbKey);
                    if(dynamicDataSourceEntity != null) {
                        if(StringUtil.isNotEmpty(dbKey)) {
                            map = DynamicDBUtil.findList(dbKey, dbDynSql, new Object[0]);
                        } else {
                            map = this.systemService.findForJdbc(dbDynSql, new Object[0]);
                        }
                    }

                    msg = "动态SQL数据查询成功！";
                    j.setObj(map);
                } catch (Exception var16) {
                    logger.info(var16.getMessage());
                    j.setSuccess(false);
                    msg = "动态SQL数据查询失败！";
                }
            }
        }

        j.setMsg(msg);
        return j;
    }

    @ResponseBody
    @RequestMapping(
            params = {"checkDbName"}
    )
    public JSONObject checkDbName(HttpServletRequest req, String cVal) {
        JSONObject jsonObject = new JSONObject();
        String param = req.getParameter("param");
        if(StringUtils.isNotBlank(cVal) && cVal.equals(param)) {
            jsonObject.put("info", "验证通过！");
            jsonObject.put("status", "y");
            return jsonObject;
        } else {
            new ArrayList();
            String hql = "from AutoFormDbEntity t where t.dbName = ?";
            List<AutoFormStyleEntity> list = this.systemService.findHql(hql, new Object[]{param});
            if(list.size() > 0) {
                jsonObject.put("status", "n");
                jsonObject.put("info", "数据源名称重复，请重新输入！");
                return jsonObject;
            } else {
                jsonObject.put("info", "验证通过！");
                jsonObject.put("status", "y");
                return jsonObject;
            }
        }
    }
}
