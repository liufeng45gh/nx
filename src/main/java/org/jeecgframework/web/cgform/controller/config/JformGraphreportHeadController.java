//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadEntity;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportHeadPage;
import org.jeecgframework.web.cgform.entity.config.JformGraphreportItemEntity;
import org.jeecgframework.web.cgform.service.config.JformGraphreportHeadServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/jformGraphreportHeadController"})
public class JformGraphreportHeadController extends BaseController {
    private static final Logger logger = Logger.getLogger(JformGraphreportHeadController.class);
    @Autowired
    private JformGraphreportHeadServiceI jformGraphreportHeadService;
    @Autowired
    private SystemService systemService;

    public JformGraphreportHeadController() {
    }

    @RequestMapping(
            params = {"jformGraphreportHead"}
    )
    public ModelAndView jformGraphreportHead(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgform/graphreport/jformGraphreportHeadList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(JformGraphreportHeadEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, jformGraphreportHead);
        cq.add();
        this.jformGraphreportHeadService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        jformGraphreportHead = (JformGraphreportHeadEntity)this.systemService.getEntity(JformGraphreportHeadEntity.class, jformGraphreportHead.getId());
        String message = "图表配置删除成功";

        try {
            this.jformGraphreportHeadService.delMain(jformGraphreportHead);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "图表配置删除失败";
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
        String message = "图表配置删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                JformGraphreportHeadEntity jformGraphreportHead = (JformGraphreportHeadEntity)this.systemService.getEntity(JformGraphreportHeadEntity.class, id);
                this.jformGraphreportHeadService.delMain(jformGraphreportHead);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "图表配置删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(JformGraphreportHeadEntity jformGraphreportHead, JformGraphreportHeadPage jformGraphreportHeadPage, HttpServletRequest request) {
        List<JformGraphreportItemEntity> jformGraphreportItemList = jformGraphreportHeadPage.getJformGraphreportItemList();
        AjaxJson j = new AjaxJson();
        String message = "添加成功";

        try {
            this.jformGraphreportHeadService.addMain(jformGraphreportHead, jformGraphreportItemList);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "图表配置添加失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(JformGraphreportHeadEntity jformGraphreportHead, JformGraphreportHeadPage jformGraphreportHeadPage, HttpServletRequest request) {
        List<JformGraphreportItemEntity> jformGraphreportItemList = jformGraphreportHeadPage.getJformGraphreportItemList();
        AjaxJson j = new AjaxJson();
        String message = "更新成功";

        try {
            this.jformGraphreportHeadService.updateMain(jformGraphreportHead, jformGraphreportItemList);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "更新图表配置失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(jformGraphreportHead.getId())) {
            jformGraphreportHead = (JformGraphreportHeadEntity)this.jformGraphreportHeadService.getEntity(JformGraphreportHeadEntity.class, jformGraphreportHead.getId());
            req.setAttribute("jformGraphreportHeadPage", jformGraphreportHead);
        }

        return new ModelAndView("jeecg/cgform/graphreport/jformGraphreportHead-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(jformGraphreportHead.getId())) {
            jformGraphreportHead = (JformGraphreportHeadEntity)this.jformGraphreportHeadService.getEntity(JformGraphreportHeadEntity.class, jformGraphreportHead.getId());
            req.setAttribute("jformGraphreportHeadPage", jformGraphreportHead);
        }

        return new ModelAndView("jeecg/cgform/graphreport/jformGraphreportHead-update");
    }

    @RequestMapping(
            params = {"jformGraphreportItemList"}
    )
    public ModelAndView jformGraphreportItemList(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest req) {
        Object id0 = jformGraphreportHead.getId();
        String hql0 = "from JformGraphreportItemEntity where 1 = 1 AND cGREPORT_HEAD_ID = ? ";

        try {
            List<JformGraphreportItemEntity> jformGraphreportItemEntityList = this.systemService.findHql(hql0, new Object[]{id0});
            req.setAttribute("jformGraphreportItemList", jformGraphreportItemEntityList);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
        }

        return new ModelAndView("jeecg/cgform/graphreport/jformGraphreportItemList");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(JformGraphreportHeadEntity jformGraphreportHead, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
        CriteriaQuery cq = new CriteriaQuery(JformGraphreportHeadEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, jformGraphreportHead);
        List<JformGraphreportHeadEntity> dataList = this.jformGraphreportHeadService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        List<JformGraphreportHeadPage> pageList = new ArrayList();
        if(dataList != null && dataList.size() > 0) {
            String hql0 = "from JformGraphreportItemEntity where 1 = 1 AND cGREPORT_HEAD_ID = ? ";
            Iterator var11 = dataList.iterator();

            while(var11.hasNext()) {
                JformGraphreportHeadEntity headEntity = (JformGraphreportHeadEntity)var11.next();
                List<JformGraphreportItemEntity> itemEntities = this.systemService.findHql(hql0, new Object[]{headEntity.getId()});
                pageList.add(new JformGraphreportHeadPage(itemEntities, headEntity));
            }
        }

        map.put("fileName", "图表配置");
        map.put("entity", JformGraphreportHeadPage.class);
        map.put("params", new ExportParams("图表配置", "导出信息"));
        map.put("data", pageList);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"goImportExcel"}
    )
    public String goImportExcel() {
        return "jeecg/cgform/graphreport/jformGraphreportUpload";
    }

    @RequestMapping(
            params = {"importExcel"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator var7 = fileMap.entrySet().iterator();

        while(var7.hasNext()) {
            Entry<String, MultipartFile> entity = (Entry)var7.next();
            MultipartFile file = (MultipartFile)entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(true);

            try {
                List<JformGraphreportHeadPage> listCourses = ExcelImportUtil.importExcel(file.getInputStream(), JformGraphreportHeadPage.class, params);
                Iterator var12 = listCourses.iterator();

                while(var12.hasNext()) {
                    JformGraphreportHeadPage page = (JformGraphreportHeadPage)var12.next();
                    JformGraphreportHeadEntity headEntity = page.getJformGraphreportHeadEntity();
                    List<JformGraphreportItemEntity> itemEntities = page.getJformGraphreportItemList();
                    if(headEntity != null && itemEntities != null) {
                        this.jformGraphreportHeadService.addMain(headEntity, itemEntities);
                    }
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var23) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var23));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var22) {
                    var22.printStackTrace();
                }

            }
        }

        return j;
    }
}
