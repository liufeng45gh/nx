//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.sms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.sms.entity.TSSmsEntity;
import org.jeecgframework.web.sms.service.TSSmsServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/tSSmsController"})
public class TSSmsController extends BaseController {
    private static final Logger logger = Logger.getLogger(TSSmsController.class);
    @Autowired
    private TSSmsServiceI tSSmsService;
    @Autowired
    private SystemService systemService;

    public TSSmsController() {
    }

    @RequestMapping(
            params = {"tSSms"}
    )
    public ModelAndView tSSms(HttpServletRequest request) {
        return new ModelAndView("system/sms/tSSmsList");
    }

    @RequestMapping(
            params = {"datagrid"}
    )
    public void datagrid(TSSmsEntity tSSms, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, tSSms, request.getParameterMap());
        cq.add();
        this.tSSmsService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"doDel"}
    )
    @ResponseBody
    public AjaxJson doDel(TSSmsEntity tSSms, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        tSSms = (TSSmsEntity)this.systemService.getEntity(TSSmsEntity.class, tSSms.getId());
        message = "消息发送记录表删除成功";

        try {
            this.tSSmsService.delete(tSSms);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "消息发送记录表删除失败";
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
        message = "消息发送记录表删除成功";

        try {
            String[] var8;
            int var7 = (var8 = ids.split(",")).length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String id = var8[var6];
                TSSmsEntity tSSms = (TSSmsEntity)this.systemService.getEntity(TSSmsEntity.class, id);
                this.tSSmsService.delete(tSSms);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            message = "消息发送记录表删除失败";
            throw new BusinessException(var10.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doAdd"}
    )
    @ResponseBody
    public AjaxJson doAdd(TSSmsEntity tSSms, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "消息发送记录表添加成功";

        try {
            this.tSSmsService.save(tSSms);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception var6) {
            var6.printStackTrace();
            message = "消息发送记录表添加失败";
            throw new BusinessException(var6.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"doUpdate"}
    )
    @ResponseBody
    public AjaxJson doUpdate(TSSmsEntity tSSms, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "消息发送记录表更新成功";
        TSSmsEntity t = (TSSmsEntity)this.tSSmsService.get(TSSmsEntity.class, tSSms.getId());

        try {
            MyBeanUtils.copyBeanNotNull2Bean(tSSms, t);
            this.tSSmsService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception var7) {
            var7.printStackTrace();
            message = "消息发送记录表更新失败";
            throw new BusinessException(var7.getMessage());
        }

        j.setMsg(message);
        return j;
    }

    @RequestMapping(
            params = {"goAdd"}
    )
    public ModelAndView goAdd(TSSmsEntity tSSms, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSSms.getId())) {
            tSSms = (TSSmsEntity)this.tSSmsService.getEntity(TSSmsEntity.class, tSSms.getId());
            req.setAttribute("tSSmsPage", tSSms);
        }

        return new ModelAndView("system/sms/tSSms-add");
    }

    @RequestMapping(
            params = {"goUpdate"}
    )
    public ModelAndView goUpdate(TSSmsEntity tSSms, HttpServletRequest req) {
        if(StringUtil.isNotEmpty(tSSms.getId())) {
            tSSms = (TSSmsEntity)this.tSSmsService.getEntity(TSSmsEntity.class, tSSms.getId());
            req.setAttribute("tSSmsPage", tSSms);
        }

        return new ModelAndView("system/sms/tSSms-update");
    }

    @RequestMapping(
            params = {"upload"}
    )
    public ModelAndView upload(HttpServletRequest req) {
        return new ModelAndView("system/sms/tSSmsUpload");
    }

    @RequestMapping(
            params = {"importExcel"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        return j;
    }

    @RequestMapping(
            params = {"getMsgs"}
    )
    @ResponseBody
    public AjaxJson getMsgs(HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        new ArrayList();
        String curUser = ResourceUtil.getSessionUserName().getUserName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(new Date());

        List list;
        try {
            String isSend = ResourceUtil.getConfigByName("sms.tip.control");
            if("1".equals(isSend)) {
                list = this.tSSmsService.getMsgsList(curUser, curDate);
                int size = list.size();
                if(size > 0) {
                    Iterator var10 = list.iterator();

                    while(var10.hasNext()) {
                        TSSmsEntity tSSmsEntity = (TSSmsEntity)var10.next();
                        if("1".equals(tSSmsEntity.getEsStatus())) {
                            tSSmsEntity.setEsStatus("2");
                            this.tSSmsService.saveOrUpdate(tSSmsEntity);
                        }
                    }

                    j.setSuccess(true);
                    j.setMsg("您收到系统消息，请到【控制面板】下\"系统消息\"菜单查看！");
                } else {
                    j.setSuccess(true);
                    j.setMsg("");
                }
            }
        } catch (Exception var11) {
            j.setSuccess(false);
            list = this.tSSmsService.getMsgsList(curUser, curDate);
            if(list.size() > 0) {
                Iterator var9 = list.iterator();

                while(var9.hasNext()) {
                    TSSmsEntity tSSmsEntity = (TSSmsEntity)var9.next();
                    if("1".equals(tSSmsEntity.getEsStatus())) {
                        tSSmsEntity.setEsStatus("3");
                        this.tSSmsService.saveOrUpdate(tSSmsEntity);
                    }
                }
            }

            logger.info("获取发送信息失败");
        }

        return j;
    }

    @RequestMapping(
            params = {"getSysInfos"}
    )
    public ModelAndView getSysInfos(HttpServletRequest request) {
        String curUser = ResourceUtil.getSessionUserName().getUserName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = sdf.format(new Date());
        List<TSSmsEntity> list = this.tSSmsService.getMsgsList(curUser, curDate);
        request.setAttribute("smsList", list);
        return new ModelAndView("system/sms/tSSmsDetailList");
    }

    @RequestMapping(
            params = {"getMessageList"}
    )
    @ResponseBody
    public AjaxJson getNoticeList(HttpServletRequest req) {
        AjaxJson j = new AjaxJson();

        try {
            j.setObj(Integer.valueOf(0));
            new ArrayList();
            String curUser = ResourceUtil.getSessionUserName().getUserName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String curDate = sdf.format(new Date());
            String isSend = ResourceUtil.getConfigByName("sms.tip.control");
            if("1".equals(isSend)) {
                List<TSSmsEntity> list = this.tSSmsService.getMsgsList(curUser, curDate);
                JSONArray result = new JSONArray();
                if(list != null && list.size() > 0) {
                    for(int i = 0; i < list.size(); ++i) {
                        JSONObject jsonParts = new JSONObject();
                        jsonParts.put("id", ((TSSmsEntity)list.get(i)).getId());
                        jsonParts.put("esTitle", ((TSSmsEntity)list.get(i)).getEsTitle());
                        jsonParts.put("esSender", ((TSSmsEntity)list.get(i)).getEsSender());
                        jsonParts.put("esContent", ((TSSmsEntity)list.get(i)).getEsContent());
                        jsonParts.put("esSendtime", ((TSSmsEntity)list.get(i)).getEsSendtime());
                        jsonParts.put("esStatus", ((TSSmsEntity)list.get(i)).getEsStatus());
                        if(((TSSmsEntity)list.get(i)).getEsSendtime() != null) {
                            SimpleDateFormat sdformat = new SimpleDateFormat("h:mm a");
                            jsonParts.put("esSendtimeTxt", sdformat.format(((TSSmsEntity)list.get(i)).getEsSendtime()));
                        }

                        result.add(jsonParts);
                    }

                    j.setObj(Integer.valueOf(list.size()));
                }

                Map<String, Object> attrs = new HashMap();
                attrs.put("messageList", result);
                String tip = MutiLangUtil.getMutiLangInstance().getLang("message.tip");
                attrs.put("tip", tip);
                String seeAll = MutiLangUtil.getMutiLangInstance().getLang("message.seeAll");
                attrs.put("seeAll", seeAll);
                j.setAttributes(attrs);
            }
        } catch (Exception var12) {
            j.setSuccess(false);
        }

        return j;
    }

    @RequestMapping(
            params = {"readMessage"}
    )
    @ResponseBody
    public AjaxJson readMessage(String messageId, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();

        try {
            if(StringUtil.isNotEmpty(messageId)) {
                TSSmsEntity tSSmsEntity = (TSSmsEntity)this.systemService.get(TSSmsEntity.class, messageId);
                if(tSSmsEntity != null) {
                    tSSmsEntity.setEsStatus("2");
                    this.tSSmsService.saveOrUpdate(tSSmsEntity);
                }
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return j;
    }

    @RequestMapping(
            params = {"getMsg"}
    )
    @ResponseBody
    public AjaxJson getMsg(String msgId, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();

        try {
            if(StringUtil.isNotEmpty(msgId)) {
                TSSmsEntity tSSmsEntity = (TSSmsEntity)this.systemService.get(TSSmsEntity.class, msgId);
                Map<String, Object> attrs = new HashMap();
                attrs.put("msginfo", tSSmsEntity);
                j.setAttributes(attrs);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return j;
    }
}
