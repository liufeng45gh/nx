//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgdynamgraph.controller;

import java.util.Iterator;
import java.util.List;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
import org.jeecgframework.web.cgdynamgraph.page.core.CgDynamGraphConfigHeadPage;
import org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphConfigHeadServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgDynamGraphConfigHeadController.do"})
public class CgDynamGraphConfigHeadController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgDynamGraphConfigHeadController.class);
    @Autowired
    private CgDynamGraphConfigHeadServiceI cgDynamGraphConfigHeadService;
    @Autowired
    private SystemService systemService;

    public CgDynamGraphConfigHeadController() {
    }

    @RequestMapping(
            params = {"cgDynamGraphConfigHead"}
    )
    public ModelAndView CgDynamGraphConfigHead(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHeadList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgDynamGraphConfigHeadEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgDynamGraphConfigHead);
        cq.add();
        this.cgDynamGraphConfigHeadService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        cgDynamGraphConfigHead = (CgDynamGraphConfigHeadEntity)this.systemService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
        message = "动态报表配置抬头删除成功";

        try {
            this.cgDynamGraphConfigHeadService.delMain(cgDynamGraphConfigHead);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "动态报表配置抬头删除失败";
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
        message = "动态报表配置抬头删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead = (CgDynamGraphConfigHeadEntity)this.systemService.getEntity(CgDynamGraphConfigHeadEntity.class, id);
                this.cgDynamGraphConfigHeadService.delMain(cgDynamGraphConfigHead);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "动态报表配置抬头删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, CgDynamGraphConfigHeadPage cgDynamGraphConfigHeadPage, HttpServletRequest request) {
        String message = null;
        List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigItemList();
        List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigParamList();
        AjaxJson j = new AjaxJson();
        message = "添加成功";

        try {
            this.cgDynamGraphConfigHeadService.addMain(cgDynamGraphConfigHead, cgDynamGraphConfigItemList, cgDynamGraphConfigParamList);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            var9.printStackTrace();
            message = "动态报表配置抬头添加失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, CgDynamGraphConfigHeadPage cgDynamGraphConfigHeadPage, HttpServletRequest request) {
        String message = null;
        List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigItemList();
        List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigParamList();
        AjaxJson j = new AjaxJson();
        message = "更新成功";

        try {
            this.cgDynamGraphConfigHeadService.updateMain(cgDynamGraphConfigHead, cgDynamGraphConfigItemList, cgDynamGraphConfigParamList);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var9) {
            var9.printStackTrace();
            message = "更新动态报表配置抬头失败";
            throw new BusinessException(var9.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgDynamGraphConfigHead.getId())) {
            cgDynamGraphConfigHead = (CgDynamGraphConfigHeadEntity)this.cgDynamGraphConfigHeadService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
            req.setAttribute("cgDynamGraphConfigHeadPage", cgDynamGraphConfigHead);
        }

        return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHead-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgDynamGraphConfigHead.getId())) {
            cgDynamGraphConfigHead = (CgDynamGraphConfigHeadEntity)this.cgDynamGraphConfigHeadService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
            req.setAttribute("cgDynamGraphConfigHeadPage", cgDynamGraphConfigHead);
        }

        return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHead-update");
    }

    @RequestMapping(
            params = {"cgDynamGraphConfigItemList"}
    )
    public ModelAndView cgDynamGraphConfigItemList(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest req) {
        Object id0 = cgDynamGraphConfigHead.getId();
        String hql0 = "from CgDynamGraphConfigItemEntity where 1 = 1 AND cgrheadId = ? ";

        try {
            List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemEntityList = this.systemService.findHql(hql0, new Object[]{id0});
            req.setAttribute("cgDynamGraphConfigItemList", cgDynamGraphConfigItemEntityList);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
        }

        return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigItemList");
    }

    @RequestMapping(
            params = {"cgDynamGraphConfigParamList"}
    )
    public ModelAndView cgDynamGraphConfigParamList(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead, HttpServletRequest req) {
        Object id0 = cgDynamGraphConfigHead.getId();
        String hql0 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";

        try {
            List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamEntityList = this.systemService.findHql(hql0, new Object[]{id0});
            req.setAttribute("cgDynamGraphConfigParamList", cgDynamGraphConfigParamEntityList);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
        }

        return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigParamList");
    }

    @RequestMapping(
            params = {"popmenulink"}
    )
    public ModelAndView popmenulink(ModelMap modelMap, @RequestParam String url, @RequestParam String title, HttpServletRequest request) {
        modelMap.put("title", title);
        modelMap.put("url", url);
        StringBuilder sb = new StringBuilder("");

        try {
            CgDynamGraphConfigHeadEntity cgDynamGraphConfigHeadEntity = (CgDynamGraphConfigHeadEntity)this.systemService.findUniqueByProperty(CgDynamGraphConfigHeadEntity.class, "code", title);
            String hql0 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
            List<CgDynamGraphConfigParamEntity> cgreportConfigParamList = this.systemService.findHql(hql0, new Object[]{cgDynamGraphConfigHeadEntity.getId()});
            if(cgreportConfigParamList != null & cgreportConfigParamList.size() > 0) {
                Iterator var10 = cgreportConfigParamList.iterator();

                while(var10.hasNext()) {
                    CgDynamGraphConfigParamEntity cgreportConfigParam = (CgDynamGraphConfigParamEntity)var10.next();
                    sb.append("&").append(cgreportConfigParam.getParamName()).append("=");
                    if(StringUtil.isNotEmpty(cgreportConfigParam.getParamValue())) {
                        sb.append(cgreportConfigParam.getParamValue());
                    } else {
                        sb.append("${" + cgreportConfigParam.getParamName() + "}");
                    }
                }
            }
        } catch (Exception var11) {
            ;
        }

        modelMap.put("params", sb.toString());
        return new ModelAndView("jeecg/cgreport/core/popmenulink");
    }
}
