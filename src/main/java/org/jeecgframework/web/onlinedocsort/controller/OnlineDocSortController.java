//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.onlinedocsort.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.onlinedocsort.entity.OnlineDocSortEntity;
import org.jeecgframework.web.onlinedocsort.service.OnlineDocSortServiceI;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
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
@RequestMapping({"/onlineDocSortController"})
public class OnlineDocSortController extends BaseController {
    private static final Logger logger = Logger.getLogger(OnlineDocSortController.class);
    @Autowired
    private OnlineDocSortServiceI onlineDocSortService;
    @Autowired
    private SystemService systemService;

    public OnlineDocSortController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("jeecg/onlinedocsort/onlineDocSortList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    @ResponseBody
    public List<TreeGrid> datagrid(OnlineDocSortEntity onlineDocSort, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(OnlineDocSortEntity.class, dataGrid);
        if(onlineDocSort.getId() != null && !StringUtils.isEmpty(onlineDocSort.getId())) {
            cq.eq("parent.id", onlineDocSort.getId());
            onlineDocSort.setId((String)null);
        } else {
            cq.isNull("parent");
        }

        HqlGenerateUtil.installHql(cq, onlineDocSort, request.getParameterMap());
        List<TSCategoryEntity> list = this.onlineDocSortService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIdField("id");
        treeGridModel.setSrc("id");
        treeGridModel.setTextField("name");
        treeGridModel.setParentText("parent_name");
        treeGridModel.setParentId("parent_id");
        treeGridModel.setChildList("list");
        List<TreeGrid> treeGrids = this.systemService.treegrid(list, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"tree"}
    )
    @ResponseBody
    public List<ComboTree> tree(String selfCode, ComboTree comboTree, boolean isNew) {
        CriteriaQuery cq = new CriteriaQuery(OnlineDocSortEntity.class);
        cq.isNull("parent");
        cq.add();
        List<OnlineDocSortEntity> categoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        List<ComboTree> comboTrees = new ArrayList();

        for(int i = 0; i < categoryList.size(); ++i) {
            comboTrees.add(this.onlineDocSortEntityConvertToTree((OnlineDocSortEntity)categoryList.get(i)));
        }

        return comboTrees;
    }

    private ComboTree onlineDocSortEntityConvertToTree(OnlineDocSortEntity entity) {
        ComboTree tree = new ComboTree();
        tree.setId(entity.getId());
        tree.setText(entity.getName());
        if(entity.getList() != null && entity.getList().size() > 0) {
            List<ComboTree> comboTrees = new ArrayList();

            for(int i = 0; i < entity.getList().size(); ++i) {
                comboTrees.add(this.onlineDocSortEntityConvertToTree((OnlineDocSortEntity)entity.getList().get(i)));
            }

            tree.setChildren(comboTrees);
        }

        return tree;
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(OnlineDocSortEntity onlineDocSort, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        onlineDocSort = (OnlineDocSortEntity)this.systemService.getEntity(OnlineDocSortEntity.class, onlineDocSort.getId());
        message = "在线文档分类删除成功";

        try {
            this.onlineDocSortService.delete(onlineDocSort);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "在线文档分类删除失败";
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
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "在线文档分类删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                OnlineDocSortEntity onlineDocSort = (OnlineDocSortEntity)this.systemService.getEntity(OnlineDocSortEntity.class, id);
                this.onlineDocSortService.delete(onlineDocSort);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "在线文档分类删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(OnlineDocSortEntity onlineDocSort, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        boolean flag = StringUtil.isEmpty(onlineDocSort.getParent().getId());
        message = "在线文档分类添加成功";

        try {
            if(flag) {
                onlineDocSort.setParent((OnlineDocSortEntity)null);
            }

            this.onlineDocSortService.save(onlineDocSort);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "在线文档分类添加失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(OnlineDocSortEntity onlineDocSort, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        boolean flag = StringUtil.isEmpty(onlineDocSort.getParent().getId());
        message = "在线文档分类更新成功";
        OnlineDocSortEntity t = (OnlineDocSortEntity)this.onlineDocSortService.get(OnlineDocSortEntity.class, onlineDocSort.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(onlineDocSort, t);
            if(flag) {
                t.setParent((OnlineDocSortEntity)null);
            }

            this.onlineDocSortService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var8) {
            var8.printStackTrace();
            message = "在线文档分类更新失败";
            throw new BusinessException(var8.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAddOrUpdate(OnlineDocSortEntity onlineDocSort, HttpServletRequest request) {
        String id = request.getParameter("id");
        if(StringUtil.isNotEmpty(id)) {
            onlineDocSort = (OnlineDocSortEntity)this.onlineDocSortService.getEntity(OnlineDocSortEntity.class, id);
            request.setAttribute("onlineDocSortPage", onlineDocSort);
        }

        return new ModelAndView("jeecg/onlinedocsort/onlineDocSort-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(OnlineDocSortEntity onlineDocSort, HttpServletRequest request) {
        String id = request.getParameter("id");
        if(StringUtil.isNotEmpty(id)) {
            onlineDocSort = (OnlineDocSortEntity)this.onlineDocSortService.getEntity(OnlineDocSortEntity.class, id);
            request.setAttribute("onlineDocSortPage", onlineDocSort);
        }

        return new ModelAndView("jeecg/onlinedocsort/onlineDocSort-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "onlineDocSortController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(
            params = {"exportXls"}
    )
    public String exportXls(OnlineDocSortEntity onlineDocSort, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(OnlineDocSortEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, onlineDocSort, request.getParameterMap());
        List<OnlineDocSortEntity> onlineDocSorts = this.onlineDocSortService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "在线文档分类");
        modelMap.put("entity", OnlineDocSortEntity.class);
        modelMap.put("params", new ExportParams("在线文档分类列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", onlineDocSorts);
        return "jeecgExcelView";
    }

    @RequestMapping(
            params = {"exportXlsByT"}
    )
    public String exportXlsByT(OnlineDocSortEntity onlineDocSort, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "在线文档分类");
        modelMap.put("entity", OnlineDocSortEntity.class);
        modelMap.put("params", new ExportParams("在线文档分类列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
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
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);

            try {
                List<OnlineDocSortEntity> listOnlineDocSortEntitys = ExcelImportUtil.importExcel(file.getInputStream(), OnlineDocSortEntity.class, params);
                Iterator var12 = listOnlineDocSortEntitys.iterator();

                while(var12.hasNext()) {
                    OnlineDocSortEntity onlineDocSort = (OnlineDocSortEntity)var12.next();
                    this.onlineDocSortService.save(onlineDocSort);
                }

                j.setMsg("文件导入成功！");
            } catch (Exception var21) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(var21));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }

            }
        }

        return j;
    }
}
