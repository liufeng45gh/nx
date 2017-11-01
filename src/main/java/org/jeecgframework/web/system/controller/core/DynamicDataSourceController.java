//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysDatabaseEnum;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/dynamicDataSourceController"})
public class DynamicDataSourceController extends BaseController {
    private static final Logger logger = Logger.getLogger(DynamicDataSourceController.class);
    @Autowired
    private DynamicDataSourceServiceI dynamicDataSourceService;
    @Autowired
    private SystemService systemService;
    private String message;

    public DynamicDataSourceController() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @RequestMapping(
            params = {"dbSource"}
    )
    public ModelAndView dbSource(HttpServletRequest request) {
        return new ModelAndView("system/dbsource/dbSourceList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(DynamicDataSourceEntity dbSource, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(DynamicDataSourceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, dbSource, request.getParameterMap());
        this.dynamicDataSourceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        dbSource = (DynamicDataSourceEntity)this.systemService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());
        this.message = MutiLangUtil.paramDelSuccess("common.datasource.manage");
        this.dynamicDataSourceService.delete(dbSource);
        this.systemService.addLog(this.message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"save"}
    )
    @ResponseBody
    public AjaxJson save(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        if(StringUtil.isNotEmpty(dbSource.getId())) {
            this.message = MutiLangUtil.paramUpdSuccess("common.datasource.manage");
            DynamicDataSourceEntity t = (DynamicDataSourceEntity)this.dynamicDataSourceService.get(DynamicDataSourceEntity.class, dbSource.getId());

            try {
                MyBeanUtils.copyBeanNotNull2Bean(dbSource, t);
                this.dynamicDataSourceService.saveOrUpdate(t);
                this.dynamicDataSourceService.refleshCache();
                this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            } catch (Exception var6) {
                var6.printStackTrace();
                this.message = MutiLangUtil.paramUpdFail("common.datasource.manage");
            }
        } else {
            this.message = MutiLangUtil.paramAddSuccess("common.datasource.manage");
            this.dynamicDataSourceService.save(dbSource);
            this.dynamicDataSourceService.refleshCache();
            this.systemService.addLog(this.message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        }

        j.setMsg(this.message);
        return j;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(DynamicDataSourceEntity dbSource, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(dbSource.getId())) {
            dbSource = (DynamicDataSourceEntity)this.dynamicDataSourceService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());
            req.setAttribute("dbSourcePage", dbSource);
        }

        return new ModelAndView("system/dbsource/dbSource");
    }

    @RequestMapping(
            params = {"getAll"}
    )
    @ResponseBody
    public List<ComboBox> getAll() {
        List<DynamicDataSourceEntity> list = this.dynamicDataSourceService.getList(DynamicDataSourceEntity.class);
        List<ComboBox> comboBoxes = new ArrayList();
        if(list != null && list.size() > 0) {
            Iterator var4 = list.iterator();

            while(var4.hasNext()) {
                DynamicDataSourceEntity entity = (DynamicDataSourceEntity)var4.next();
                ComboBox comboBox = new ComboBox();
                comboBox.setId(entity.getId());
                comboBox.setText(entity.getDbKey());
                comboBoxes.add(comboBox);
            }
        }

        return comboBoxes;
    }

    @RequestMapping(
            params = {"getDynamicDataSourceParameter"}
    )
    @ResponseBody
    public AjaxJson getDynamicDataSourceParameter(@RequestParam String dbType) {
        AjaxJson j = new AjaxJson();
        SysDatabaseEnum sysDatabaseEnum = SysDatabaseEnum.toEnum(dbType);
        if(sysDatabaseEnum != null) {
            Map<String, String> map = new HashMap();
            map.put("driverClass", sysDatabaseEnum.getDriverClass());
            map.put("url", sysDatabaseEnum.getUrl());
            map.put("dbtype", sysDatabaseEnum.getDbtype());
            j.setObj(map);
        } else {
            j.setObj("");
        }

        return j;
    }
}
