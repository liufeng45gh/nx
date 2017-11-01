//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgreport.controller.core;

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
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigHeadEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigItemEntity;
import org.jeecgframework.web.cgreport.entity.core.CgreportConfigParamEntity;
import org.jeecgframework.web.cgreport.page.core.CgreportConfigHeadPage;
import org.jeecgframework.web.cgreport.service.core.CgreportConfigHeadServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cgreportConfigHeadController"})
public class CgreportConfigHeadController extends BaseController {
    private static final Logger logger = Logger.getLogger(CgreportConfigHeadController.class);
    @Autowired
    private CgreportConfigHeadServiceI cgreportConfigHeadService;
    @Autowired
    private SystemService systemService;

    public CgreportConfigHeadController() {
    }

    @RequestMapping(
            params = {"cgreportConfigHead"}
    )
    public ModelAndView cgreportConfigHead(HttpServletRequest request) {
        return new ModelAndView("jeecg/cgreport/core/cgreportConfigHeadList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(CgreportConfigHeadEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, cgreportConfigHead);
        cq.add();
        this.cgreportConfigHeadService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        cgreportConfigHead = (CgreportConfigHeadEntity)this.systemService.getEntity(CgreportConfigHeadEntity.class, cgreportConfigHead.getId());
        message = "动态报表配置抬头删除成功";

        try {
            this.cgreportConfigHeadService.delMain(cgreportConfigHead);
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
                CgreportConfigHeadEntity cgreportConfigHead = (CgreportConfigHeadEntity)this.systemService.getEntity(CgreportConfigHeadEntity.class, id);
                this.cgreportConfigHeadService.delMain(cgreportConfigHead);
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
    public AjaxJson doAdd(CgreportConfigHeadEntity cgreportConfigHead, CgreportConfigHeadPage cgreportConfigHeadPage, HttpServletRequest request) {
        String message = null;
        List<CgreportConfigItemEntity> cgreportConfigItemList = cgreportConfigHeadPage.getCgreportConfigItemList();
        List<CgreportConfigParamEntity> cgreportConfigParamList = cgreportConfigHeadPage.getCgreportConfigParamList();
        AjaxJson j = new AjaxJson();
        message = "添加成功";

        try {
            this.cgreportConfigHeadService.addMain(cgreportConfigHead, cgreportConfigItemList, cgreportConfigParamList);
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
    public AjaxJson doUpdate(CgreportConfigHeadEntity cgreportConfigHead, CgreportConfigHeadPage cgreportConfigHeadPage, HttpServletRequest request) {
        String message = null;
        List<CgreportConfigItemEntity> cgreportConfigItemList = cgreportConfigHeadPage.getCgreportConfigItemList();
        List<CgreportConfigParamEntity> cgreportConfigParamList = cgreportConfigHeadPage.getCgreportConfigParamList();
        AjaxJson j = new AjaxJson();
        message = "更新成功";

        try {
            this.cgreportConfigHeadService.updateMain(cgreportConfigHead, cgreportConfigItemList, cgreportConfigParamList);
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
    public ModelAndView goAdd(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgreportConfigHead.getId())) {
            cgreportConfigHead = (CgreportConfigHeadEntity)this.cgreportConfigHeadService.getEntity(CgreportConfigHeadEntity.class, cgreportConfigHead.getId());
            req.setAttribute("cgreportConfigHeadPage", cgreportConfigHead);
        }

        return new ModelAndView("jeecg/cgreport/core/cgreportConfigHead-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(cgreportConfigHead.getId())) {
            cgreportConfigHead = (CgreportConfigHeadEntity)this.cgreportConfigHeadService.getEntity(CgreportConfigHeadEntity.class, cgreportConfigHead.getId());
            req.setAttribute("cgreportConfigHeadPage", cgreportConfigHead);
        }

        return new ModelAndView("jeecg/cgreport/core/cgreportConfigHead-update");
    }

    @RequestMapping(
            params = {"cgreportConfigItemList"}
    )
    public ModelAndView cgreportConfigItemList(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest req) {
        Object id0 = cgreportConfigHead.getId();
        String hql0 = "from CgreportConfigItemEntity where 1 = 1 AND cgrheadId = ? ";

        try {
            List<CgreportConfigItemEntity> cgreportConfigItemEntityList = this.systemService.findHql(hql0, new Object[]{id0});
            req.setAttribute("cgreportConfigItemList", cgreportConfigItemEntityList);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
        }

        return new ModelAndView("jeecg/cgreport/core/cgreportConfigItemList");
    }

    @RequestMapping(
            params = {"cgreportConfigParamList"}
    )
    public ModelAndView cgreportConfigParamList(CgreportConfigHeadEntity cgreportConfigHead, HttpServletRequest req) {
        Object id0 = cgreportConfigHead.getId();
        String hql0 = "from CgreportConfigParamEntity where 1 = 1 AND cgrheadId = ? ";

        try {
            List<CgreportConfigParamEntity> cgreportConfigParamEntityList = this.systemService.findHql(hql0, new Object[]{id0});
            req.setAttribute("cgreportConfigParamList", cgreportConfigParamEntityList);
        } catch (Exception var6) {
            logger.info(var6.getMessage());
        }

        return new ModelAndView("jeecg/cgreport/core/cgreportConfigParamList");
    }

    @RequestMapping(
            params = {"popmenulink"}
    )
    public ModelAndView popmenulink(ModelMap modelMap, @RequestParam String url, @RequestParam String title, HttpServletRequest request) {
        modelMap.put("title", title);
        modelMap.put("url", url);
        StringBuilder sb = new StringBuilder("");

        try {
            CgreportConfigHeadEntity cgreportConfigHead = (CgreportConfigHeadEntity)this.systemService.findUniqueByProperty(CgreportConfigHeadEntity.class, "code", title);
            String hql0 = "from CgreportConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
            List<CgreportConfigParamEntity> cgreportConfigParamList = this.systemService.findHql(hql0, new Object[]{cgreportConfigHead.getId()});
            if(cgreportConfigParamList != null & cgreportConfigParamList.size() > 0) {
                Iterator var10 = cgreportConfigParamList.iterator();

                while(var10.hasNext()) {
                    CgreportConfigParamEntity cgreportConfigParam = (CgreportConfigParamEntity)var10.next();
                    sb.append("&").append(cgreportConfigParam.getParamName()).append("=");
                    if(StringUtil.isNotEmpty(cgreportConfigParam.getParamValue())) {
                        sb.append(cgreportConfigParam.getParamValue());
                    } else {
                        sb.append("${" + cgreportConfigParam.getParamName() + "}");
                    }
                }
            }
        } catch (Exception var11) {
            logger.info(var11.getMessage());
        }

        modelMap.put("params", sb.toString());
        return new ModelAndView("jeecg/cgreport/core/popmenulink");
    }
}
