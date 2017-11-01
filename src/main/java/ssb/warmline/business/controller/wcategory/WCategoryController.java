//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.controller.wcategory;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssb.warmline.business.entity.wcategory.WCategoryEntity;
import ssb.warmline.business.service.wcategory.WCategoryServiceI;

@Controller
@RequestMapping({"/wCategoryController"})
public class WCategoryController extends BaseController {
    private static final Logger logger = Logger.getLogger(WCategoryController.class);
    @Autowired
    private WCategoryServiceI wCategoryService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    public WCategoryController() {
    }

    @RequestMapping(
            params = {"list"}
    )
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("ssb/warmline/business/wcategory/wCategoryListTree");
    }

    @RequestMapping(
            params = {"categoryList"}
    )
    @ResponseBody
    public JSONArray categoryList(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        JSONArray childMenu = new JSONArray();
        if(id == null) {
            id = "0";
            List<WCategoryEntity> listCategoryTemp = this.systemService.loadAll(WCategoryEntity.class);
            Iterator var7 = listCategoryTemp.iterator();

            while(var7.hasNext()) {
                WCategoryEntity object = (WCategoryEntity)var7.next();
                JSONObject jsonMenu = JSONObject.fromObject(object);
                childMenu.add(jsonMenu);
            }
        }

        return childMenu;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String categoryParentid = request.getParameter("categoryParentid");
        String categoryName = request.getParameter("categoryName");
        String preNodeCategoryCode = request.getParameter("preNodeCategoryCode");
        String preNodeCategorySort = request.getParameter("preNodeCategorySort");
        String preNodeCategoryLevel = request.getParameter("preNodeCategoryLevel");
        String categoryLevel = request.getParameter("categoryLevel");
        String isParent = request.getParameter("isParent");
        String parentIsParent = request.getParameter("parentIsParent");
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "分类添加成功";

        try {
            WCategoryEntity wCategory = (WCategoryEntity)this.systemService.getEntity(WCategoryEntity.class, id);
            int categoryCode;
            DecimalFormat df;
            String str2;
            int categorySort;
            if(wCategory == null) {
                wCategory = new WCategoryEntity();
                wCategory.setCategoryParentid(categoryParentid);
                wCategory.setCategoryName(categoryName);
                wCategory.setCategoryLevel(Integer.valueOf(Integer.parseInt(categoryLevel)));
                wCategory.setIsParent(isParent);
                wCategory.setOpen("false");
                categoryCode = Integer.parseInt(preNodeCategoryCode) + 1;
                df = new DecimalFormat("0000000");
                str2 = df.format((long)categoryCode);
                wCategory.setCategoryCode(str2);
                categorySort = Integer.parseInt(preNodeCategorySort) + 1;
                wCategory.setCategorySort(Integer.valueOf(categorySort));
                this.wCategoryService.save(wCategory);
            } else {
                wCategory.setCategoryParentid(categoryParentid);
                wCategory.setCategoryName(categoryName);
                wCategory.setCategoryLevel(Integer.valueOf(Integer.parseInt(categoryLevel)));
                wCategory.setIsParent(isParent);
                wCategory.setOpen("true");
                categoryCode = Integer.parseInt(preNodeCategoryCode) + 1;
                df = new DecimalFormat("0000000");
                str2 = df.format((long)categoryCode);
                wCategory.setCategoryCode(str2);
                categorySort = Integer.parseInt(preNodeCategorySort) + 1;
                wCategory.setCategorySort(Integer.valueOf(categorySort));
                this.wCategoryService.saveOrUpdate(wCategory);
            }

            WCategoryEntity wCategoryParent = (WCategoryEntity)this.systemService.getEntity(WCategoryEntity.class, categoryParentid);
            wCategoryParent.setIsParent(parentIsParent);
            this.wCategoryService.save(wCategoryParent);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var19) {
            var19.printStackTrace();
            message = "分类表添加失败";
        }

        j.setMsg(message);
        j.setObj(categoryParentid);
        return j;
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        AjaxJson j = new AjaxJson();
        String id = request.getParameter("id");
        String isParent = request.getParameter("isParent");
        String parentIsParent = request.getParameter("parentIsParent");
        String treeNodeLen = request.getParameter("treeNodeLen");
        String categoryParentid = request.getParameter("categoryParentid");
        if("true".equals(isParent)) {
            try {
                boolean delFlag = this.delCategoryEntity(id);
                if(delFlag) {
                    message = "分类表删除成功";
                } else {
                    message = "分类表删除失败";
                }
            } catch (Exception var13) {
                var13.printStackTrace();
                message = "分类表删除失败";
                throw new BusinessException(var13.getMessage());
            }
        } else {
            WCategoryEntity wCategory = (WCategoryEntity)this.systemService.getEntity(WCategoryEntity.class, id);

            try {
                this.systemService.delete(wCategory);
                message = "分类表删除成功";
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            } catch (Exception var12) {
                var12.printStackTrace();
                message = "分类表删除失败";
                throw new BusinessException(var12.getMessage());
            }
        }

        int countNode = Integer.parseInt(treeNodeLen);
        if(countNode == 1) {
            parentIsParent = "false";
        }

        WCategoryEntity wCategoryParent = (WCategoryEntity)this.systemService.getEntity(WCategoryEntity.class, categoryParentid);
        wCategoryParent.setIsParent(parentIsParent);
        this.wCategoryService.save(wCategoryParent);
        j.setMsg(message);
        return j;
    }

    private boolean delCategoryEntity(String categoryParentid) {
        boolean delFlag = false;

        try {
            WCategoryEntity wCategory = (WCategoryEntity)this.systemService.getEntity(WCategoryEntity.class, categoryParentid);
            this.systemService.delete(wCategory);
            List<WCategoryEntity> listCategoryTemp = this.systemService.findByProperty(WCategoryEntity.class, "categoryParentid", categoryParentid);
            if(listCategoryTemp != null && listCategoryTemp.size() > 0) {
                Iterator var6 = listCategoryTemp.iterator();

                while(var6.hasNext()) {
                    WCategoryEntity categoryEntity = (WCategoryEntity)var6.next();
                    if("true".equals(categoryEntity.getIsParent())) {
                        this.delCategoryEntity(categoryEntity.getId());
                    } else {
                        this.systemService.delete(categoryEntity);
                    }
                }
            }

            delFlag = true;
            return delFlag;
        } catch (Exception var7) {
            delFlag = false;
            var7.printStackTrace();
            throw new BusinessException(var7.getMessage());
        }
    }
}
