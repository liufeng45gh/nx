//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wterritory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssb.warmline.business.entity.wterritory.WTerritoryBusiness;
import ssb.warmline.business.entity.wterritory.WTerritoryEntity;
import ssb.warmline.business.service.wterritory.WTerritoryServiceI;

@Controller
@RequestMapping({"/wTerritoryController"})
public class WTerritoryController extends BaseController {
    private static final Logger logger = Logger.getLogger(WTerritoryController.class);
    @Autowired
    private WTerritoryServiceI wTerritoryService;
    @Autowired
    private SystemService systemService;

    public WTerritoryController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wterritory/territoryList");
    }

    @RequestMapping(
            params = {"territorys"}
    )
    public ModelAndView roles(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("ssb/warmline/business/wterritory/territoryLists");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(
            params = {"lists"}
    )
    public ModelAndView lists(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wterritory/wTerritoryList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    @ResponseBody
    public List<TreeGrid> datagrid(HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryEntity.class);
        if(treegrid.getId() != null) {
            cq.eq("WTerritoryEntity.id", treegrid.getId());
        }

        if(treegrid.getId() == null) {
            cq.eq("WTerritoryEntity.id", "00");
        }

        cq.addOrder("territorysort", SortDirection.asc);
        cq.add();
        List territoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIcon("");
        treeGridModel.setTextField("territoryname");
        treeGridModel.setParentText("WTerritory_territoryName");
        treeGridModel.setParentId("WTerritory_id");
        treeGridModel.setSrc("territoryCode");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("WTerritoryEntitys");
        treeGridModel.setOrder("territorysort");
        treeGridModel.setIsparent("isparent");
        List<TreeGrid> treeGrids = this.systemService.treegrid(territoryList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"agent_territorys"}
    )
    public ModelAndView agent_territorys(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("ssb/warmline/business/wterritory/agent_territorys");
        String ids = oConvertUtils.getString(request.getParameter("ids"));
        mv.addObject("ids", ids);
        return mv;
    }

    @RequestMapping(
            params = {"datagrid_agent"}
    )
    @ResponseBody
    public List<TreeGrid> datagrid_agent(HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryEntity.class);
        if(treegrid.getId() == null) {
            cq.eq("WTerritoryEntity.id", "00");
        }

        if(treegrid.getId() != null) {
            WTerritoryEntity territory = (WTerritoryEntity)this.systemService.findUniqueByProperty(WTerritoryEntity.class, "id", treegrid.getId());
            cq.eq("WTerritoryEntity.id", treegrid.getId());
            if(!"Area".equals(territory.getOut_in())) {
                if("China".equals(territory.getOut_in())) {
                    cq.between("territorylevel", Integer.valueOf(0), Integer.valueOf(1));
                } else {
                    cq.between("territorylevel", Integer.valueOf(1), Integer.valueOf(2));
                }
            }
        }

        cq.addOrder("territorysort", SortDirection.asc);
        cq.add();
        List territoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIcon("");
        treeGridModel.setTextField("territoryname");
        treeGridModel.setParentText("WTerritory_territoryName");
        treeGridModel.setParentId("WTerritory_id");
        treeGridModel.setSrc("territoryCode");
        treeGridModel.setCode("isparent");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("WTerritoryEntitys");
        treeGridModel.setOrder("territorysort");
        List<TreeGrid> treeGrids = this.systemService.treegrid(territoryList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"datagrid_virtualOrder"}
    )
    @ResponseBody
    public List<TreeGrid> datagrid_virtualOrder(HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryEntity.class);
        if(treegrid.getId() == null) {
            cq.eq("WTerritoryEntity.id", "00");
        }

        if(treegrid.getId() != null) {
            WTerritoryEntity territory = (WTerritoryEntity)this.systemService.findUniqueByProperty(WTerritoryEntity.class, "id", treegrid.getId());
            cq.eq("WTerritoryEntity.id", treegrid.getId());
            if(!"Area".equals(territory.getOut_in())) {
                if("China".equals(territory.getOut_in())) {
                    if(!"2".equals(territory.getId()) && !"20".equals(territory.getId()) && !"2324".equals(territory.getId()) && !"802".equals(territory.getId())) {
                        cq.between("territorylevel", Integer.valueOf(0), Integer.valueOf(2));
                    } else {
                        cq.between("territorylevel", Integer.valueOf(0), Integer.valueOf(3));
                    }
                } else {
                    cq.between("territorylevel", Integer.valueOf(1), Integer.valueOf(2));
                }
            }
        }

        cq.addOrder("territorysort", SortDirection.asc);
        cq.add();
        List territoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        TreeGridModel treeGridModel = new TreeGridModel();
        treeGridModel.setIcon("");
        treeGridModel.setTextField("territoryname");
        treeGridModel.setParentText("WTerritory_territoryName");
        treeGridModel.setParentId("WTerritoryEntity.id");
        treeGridModel.setSrc("territoryCode");
        treeGridModel.setIsparent("isparent");
        treeGridModel.setIdField("id");
        treeGridModel.setChildList("WTerritoryEntitys");
        treeGridModel.setOrder("territorysort");
        List<TreeGrid> treeGrids = this.systemService.treegrid(territoryList, treeGridModel);
        return treeGrids;
    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(WTerritoryEntity wTerritoryEntity, HttpServletRequest req) {
        String functionid = req.getParameter("id");
        if(functionid != null) {
            wTerritoryEntity = (WTerritoryEntity)this.systemService.getEntity(WTerritoryEntity.class, functionid);
            req.setAttribute("wTerritoryEntity", wTerritoryEntity);
        }

        if(wTerritoryEntity.getWTerritoryEntity() != null && wTerritoryEntity.getWTerritoryEntity().getId() != null) {
            WTerritoryEntity territoryParent = (WTerritoryEntity)this.systemService.getEntity(WTerritoryEntity.class, wTerritoryEntity.getWTerritoryEntity().getId());
            wTerritoryEntity.setWTerritoryEntity(territoryParent);
            req.setAttribute("wTerritoryEntity", wTerritoryEntity);
        }

        return new ModelAndView("ssb/warmline/business/wterritory/territory");
    }

    @RequestMapping(
            params = {"setPTerritory"}
    )
    @ResponseBody
    public List<ComboTree> setPTerritory(HttpServletRequest request, ComboTree comboTree) {
        CriteriaQuery cq = new CriteriaQuery(WTerritoryEntity.class);
        if(comboTree.getId() != null) {
            cq.eq("WTerritoryEntity.id", comboTree.getId());
        }

        if(comboTree.getId() == null) {
            cq.isNull("WTerritoryEntity");
        }

        cq.add();
        List<WTerritoryEntity> wTerritoryList = this.systemService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        new ArrayList();
        ComboTreeModel comboTreeModel = new ComboTreeModel("id", "territoryname", "WTerritoryEntitys");
        List<ComboTree> comboTrees = this.systemService.ComboTree(wTerritoryList, comboTreeModel, (List)null, false);
        return comboTrees;
    }

    @RequestMapping(
            params = {"saveTerritory"}
    )
    @ResponseBody
    public AjaxJson saveTerritory(WTerritoryEntity wTerritoryEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        if(wTerritoryEntity.getXwgs84() == null) {
            wTerritoryEntity.setXwgs84(Double.valueOf(0.0D));
        }

        if(wTerritoryEntity.getYwgs84() == null) {
            wTerritoryEntity.setYwgs84(Double.valueOf(0.0D));
        }

        if(wTerritoryEntity.getTerritoryPinyin() != null) {
            char[] chars = wTerritoryEntity.getTerritoryPinyin().toCharArray();
            String firstName = String.valueOf(chars[0]);
            wTerritoryEntity.setFirst(firstName.toUpperCase());
        }

        wTerritoryEntity.setIshotcity(wTerritoryEntity.getIshotcity());
        WTerritoryEntity territoryParent;
        if(wTerritoryEntity.getWTerritoryEntity().getId().equals("")) {
            territoryParent = (WTerritoryEntity)this.systemService.getEntity(WTerritoryEntity.class, "00");
            if(territoryParent != null) {
                wTerritoryEntity.setWTerritoryEntity(territoryParent);
            } else {
                wTerritoryEntity.setWTerritoryEntity((WTerritoryEntity)null);
            }

            wTerritoryEntity.setOpen("true");
            wTerritoryEntity.setIsparent("false");
            wTerritoryEntity.setAreacode(wTerritoryEntity.getAreacode());
            wTerritoryEntity.setTerritorylevel(Integer.valueOf(1));
        } else {
            territoryParent = (WTerritoryEntity)this.systemService.getEntity(WTerritoryEntity.class, wTerritoryEntity.getWTerritoryEntity().getId());
            if(territoryParent.getTerritorylevel().intValue() != 0) {
                wTerritoryEntity.setTerritorylevel(Integer.valueOf(String.valueOf(territoryParent.getTerritorylevel().intValue() + 1)));
                wTerritoryEntity.setOut_in(territoryParent.getOut_in());
                wTerritoryEntity.setOpen("false");
                wTerritoryEntity.setIsparent("false");
                wTerritoryEntity.setAreacode((String)null);
                territoryParent.setIsparent("true");
                this.systemService.saveOrUpdate(territoryParent);
            }
        }

        if(StringUtil.isNotEmpty(wTerritoryEntity.getId())) {
            message = "地域: " + wTerritoryEntity.getTerritoryname() + "被更新成功";
            this.systemService.saveOrUpdate(wTerritoryEntity);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
            message = MutiLangUtil.paramUpdSuccess("common.area");
        } else {
            wTerritoryEntity.setTerritorysort(wTerritoryEntity.getTerritorysort());
            message = "地域: " + wTerritoryEntity.getTerritoryname() + "被添加成功";
            this.systemService.save(wTerritoryEntity);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            message = MutiLangUtil.paramAddSuccess("common.area");
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(WTerritoryEntity wTerritory, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        wTerritory = (WTerritoryEntity)this.systemService.getEntity(WTerritoryEntity.class, wTerritory.getId());
        message = "地域: " + wTerritory.getTerritoryname() + "被删除成功";
        this.systemService.delete(wTerritory);
        this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        message = MutiLangUtil.paramDelSuccess("common.area");
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"combox"}
    )
    @ResponseBody
    public List<ComboBox> combox(HttpServletRequest request) {
        String hql = "from WTerritoryBusiness where areacode is not null";
        List<WTerritoryBusiness> ls = this.wTerritoryService.findByQueryString(hql);
        List<ComboBox> comboxList = new ArrayList(ls.size());
        Iterator var6 = ls.iterator();

        while(var6.hasNext()) {
            WTerritoryBusiness wTerritory = (WTerritoryBusiness)var6.next();
            ComboBox comboBox = new ComboBox();
            comboBox.setId(wTerritory.getAreacode());
            comboBox.setText(wTerritory.getTerritoryname() + "(" + wTerritory.getAreacode() + ")");
            comboxList.add(comboBox);
        }

        return comboxList;
    }

    @RequestMapping(
            params = {"selectionClassIfication"}
    )
    public ModelAndView selectionClassIfication(HttpServletRequest request, String status) {
        HttpSession session = request.getSession();
        String territoryid = request.getParameter("territoryid");
        session.setAttribute("territoryid", territoryid);
        return new ModelAndView("ssb/warmline/business/territoryandcategory/selectionClassIfication");
    }
}
