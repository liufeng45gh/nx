//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.controller.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/iconController"})
public class IconController extends BaseController {
    private SystemService systemService;

    public IconController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping(
            params = {"icon"}
    )
    public ModelAndView icon() {
        return new ModelAndView("system/icon/iconList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(TSIcon icon, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSIcon.class, dataGrid);
        HqlGenerateUtil.installHql(cq, icon);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        IconImageUtil.convertDataGrid(dataGrid, request);
        List<TSIcon> list = dataGrid.getResults();
        Iterator var8 = list.iterator();

        while(var8.hasNext()) {
            TSIcon tsicon = (TSIcon)var8.next();
            tsicon.setIconName(MutiLangUtil.doMutiLang(tsicon.getIconName(), ""));
        }

        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"saveOrUpdateIcon"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson saveOrUpdateIcon(HttpServletRequest request) throws Exception {
        String message = null;
        AjaxJson j = new AjaxJson();
        TSIcon icon = new TSIcon();
        Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
        String iconName = oConvertUtils.getString(request.getParameter("iconName"));
        String id = request.getParameter("id");
        icon.setId(id);
        icon.setIconName(iconName);
        icon.setIconType(iconType);
        UploadFile uploadFile = new UploadFile(request, icon);
        uploadFile.setCusPath("plug-in/accordion/images");
        uploadFile.setExtend("extend");
        uploadFile.setTitleField("iconclas");
        uploadFile.setRealPath("iconPath");
        uploadFile.setObject(icon);
        uploadFile.setByteField("iconContent");
        uploadFile.setRename(false);
        this.systemService.uploadFile(uploadFile);
        String css = "." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
        this.write(request, css);
        message = MutiLangUtil.paramAddSuccess("common.icon");
        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"update"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson update(HttpServletRequest request) throws Exception {
        String message = null;
        AjaxJson j = new AjaxJson();
        Short iconType = oConvertUtils.getShort(request.getParameter("iconType"));
        String iconName = URLDecoder.decode(oConvertUtils.getString(request.getParameter("iconName")));
        String id = request.getParameter("id");
        TSIcon icon = new TSIcon();
        if(StringUtil.isNotEmpty(id)) {
            icon = (TSIcon)this.systemService.get(TSIcon.class, id);
            icon.setId(id);
        }

        icon.setIconName(iconName);
        icon.setIconType(iconType);
        this.systemService.saveOrUpdate(icon);
        String css = "." + icon.getIconClas() + "{background:url('../images/" + icon.getIconClas() + "." + icon.getExtend() + "') no-repeat}";
        this.write(request, css);
        message = "更新成功";
        j.setMsg(message);
        return j;
    }

    protected void write(HttpServletRequest request, String css) {
        try {
            String path = request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }

            FileWriter out = new FileWriter(file, true);
            out.write("\r\n");
            out.write(css);
            out.close();
        } catch (Exception var6) {
            ;
        }

    }

    @RequestMapping(
            params = {"repair"}
    )
    @ResponseBody
    public AjaxJson repair(HttpServletRequest request) throws Exception {
        AjaxJson json = new AjaxJson();
        List<TSIcon> icons = this.systemService.loadAll(TSIcon.class);
        String rootpath = request.getSession().getServletContext().getRealPath("/");
        String csspath = request.getSession().getServletContext().getRealPath("/plug-in/accordion/css/icons.css");
        this.clearFile(csspath);
        Iterator var7 = icons.iterator();

        while(var7.hasNext()) {
            TSIcon c = (TSIcon)var7.next();
            File file = new File(rootpath + c.getIconPath());
            if(!file.exists()) {
                byte[] content = c.getIconContent();
                if(content != null) {
                    BufferedImage imag = ImageIO.read(new ByteArrayInputStream(content));
                    ImageIO.write(imag, "PNG", file);
                }
            }

            String css = "." + c.getIconClas() + "{background:url('../images/" + c.getIconClas() + "." + c.getExtend() + "') no-repeat}";
            this.write(request, css);
        }

        json.setMsg(MutiLangUtil.paramAddSuccess("common.icon.style"));
        json.setSuccess(true);
        return json;
    }

    protected void clearFile(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write("".getBytes());
            fos.close();
        } catch (FileNotFoundException var3) {
            var3.printStackTrace();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    @RequestMapping(
            params = {"del"}
    )
    @ResponseBody
    public AjaxJson del(TSIcon icon, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        icon = (TSIcon)this.systemService.getEntity(TSIcon.class, icon.getId());
        boolean isPermit = this.isPermitDel(icon);
        if(isPermit) {
            this.systemService.delete(icon);
            message = MutiLangUtil.paramDelSuccess("common.icon");
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            j.setMsg(message);
            return j;
        } else {
            message = MutiLangUtil.paramDelFail("common.icon,common.icon.isusing");
            j.setMsg(message);
            return j;
        }
    }

    private boolean isPermitDel(TSIcon icon) {
        List<TSFunction> functions = this.systemService.findByProperty(TSFunction.class, "TSIcon.id", icon.getId());
        return functions == null || functions.isEmpty();
    }

    public void upEntity(TSIcon icon) {
        List<TSFunction> functions = this.systemService.findByProperty(TSFunction.class, "TSIcon.id", icon.getId());
        if(functions.size() > 0) {
            Iterator var4 = functions.iterator();

            while(var4.hasNext()) {
                TSFunction tsFunction = (TSFunction)var4.next();
                tsFunction.setTSIcon((TSIcon)null);
                this.systemService.saveOrUpdate(tsFunction);
            }
        }

        List<TSOperation> operations = this.systemService.findByProperty(TSOperation.class, "TSIcon.id", icon.getId());
        if(operations.size() > 0) {
            Iterator var5 = operations.iterator();

            while(var5.hasNext()) {
                TSOperation tsOperation = (TSOperation)var5.next();
                tsOperation.setTSIcon((TSIcon)null);
                this.systemService.saveOrUpdate(tsOperation);
            }
        }

    }

    @RequestMapping(
            params = {"addorupdate"}
    )
    public ModelAndView addorupdate(TSIcon icon, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(icon.getId())) {
            icon = (TSIcon)this.systemService.getEntity(TSIcon.class, icon.getId());
            req.setAttribute("icon", icon);
        }

        return new ModelAndView("system/icon/icons");
    }
}
