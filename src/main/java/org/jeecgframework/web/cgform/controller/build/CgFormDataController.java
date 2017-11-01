//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.controller.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.service.autolist.CgTableServiceI;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.util.SignatureUtil;
import org.jeecgframework.web.cgform.util.TableJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/cgFormDataController"})
public class CgFormDataController {
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private CgTableServiceI cgTableService;
    @Autowired
    private CgFormFieldServiceI cgFormFieldService;
    private static final String SIGN_KEY = "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753";

    public CgFormDataController() {
    }

    @RequestMapping(
            params = {"getFormInfo"}
    )
    @ResponseBody
    public TableJson getFormInfo(String tableName, String id, String sign, HttpServletRequest request, HttpServletResponse response) {
        TableJson j = new TableJson();

        try {
            if(StringUtil.isEmpty(tableName)) {
                throw new BusinessException("tableName不能为空");
            }

            if(StringUtil.isEmpty(id)) {
                throw new BusinessException("id不能为空");
            }

            if(StringUtil.isEmpty(sign)) {
                throw new BusinessException("sign不能为空");
            }

            Map<String, String> paramMap = new HashMap();
            paramMap.put("tableName", tableName);
            paramMap.put("id", id);
            paramMap.put("method", "getFormInfo");
            if(!SignatureUtil.checkSign(paramMap, "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753", sign)) {
                throw new BusinessException("签名验证失败");
            }

            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
            if(head == null) {
                throw new BusinessException("该表单不是online表单");
            }

            Map mainForm;
            if(head.getJformType().intValue() == 1) {
                j.setTableType(head.getJformType());
                mainForm = this.dataBaseService.findOneForJdbc(tableName, id);
                j.setTableData(mainForm);
            } else {
                if(head.getJformType().intValue() != 2) {
                    throw new BusinessException("不支持该类型的表单的操作");
                }

                j.setTableType(head.getJformType());
                mainForm = this.dataBaseService.findOneForJdbc(tableName, id);
                j.setTableData(mainForm);
                Map<String, Object> tableData = new HashMap();
                String subTableStr = head.getSubTableStr();
                if(StringUtils.isNotEmpty(subTableStr)) {
                    String[] subTables = subTableStr.split(",");
                    new ArrayList();
                    String[] var17 = subTables;
                    int var16 = subTables.length;

                    for(int var15 = 0; var15 < var16; ++var15) {
                        String subTable = var17[var15];
                        List<Map<String, Object>> subTableData = this.cgFormFieldService.getSubTableData(tableName, subTable, id);
                        tableData.put(subTable, subTableData);
                    }
                }

                j.setSubTableDate(tableData);
            }
        } catch (BusinessException var18) {
            j.setSuccess(false);
            j.setMsg(var18.getMessage());
        } catch (Exception var19) {
            j.setSuccess(false);
            j.setMsg("系统异常");
        }

        return j;
    }

    @RequestMapping(
            params = {"deleteFormInfo"}
    )
    @ResponseBody
    public TableJson deleteFormInfo(String tableName, String id, String sign, HttpServletRequest request, HttpServletResponse response) {
        TableJson j = new TableJson();

        try {
            if(StringUtil.isEmpty(tableName)) {
                throw new BusinessException("tableName不能为空");
            }

            if(StringUtil.isEmpty(id)) {
                throw new BusinessException("id不能为空");
            }

            if(StringUtil.isEmpty(sign)) {
                throw new BusinessException("sign不能为空");
            }

            Map<String, String> paramMap = new HashMap();
            paramMap.put("tableName", tableName);
            paramMap.put("id", id);
            paramMap.put("method", "deleteFormInfo");
            if(!SignatureUtil.checkSign(paramMap, "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753", sign)) {
                throw new BusinessException("签名验证失败");
            }

            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
            if(head == null) {
                throw new BusinessException("该表单不是online表单");
            }

            if(head.getJformType().intValue() != 1 && head.getJformType().intValue() != 2) {
                throw new BusinessException("不支持该类型的表单的操作");
            }

            this.cgTableService.delete(tableName, id);
            j.setMsg("删除成功");
        } catch (BusinessException var9) {
            j.setSuccess(false);
            j.setMsg(var9.getMessage());
        } catch (Exception var10) {
            j.setSuccess(false);
            j.setMsg("系统异常");
        }

        return j;
    }

    @RequestMapping(
            params = {"addFormInfo"}
    )
    @ResponseBody
    public TableJson addFormInfo(String tableName, String id, String data, String sign, HttpServletRequest request, HttpServletResponse response) {
        TableJson j = new TableJson();

        try {
            if(StringUtil.isEmpty(tableName)) {
                throw new BusinessException("tableName不能为空");
            }

            if(StringUtil.isEmpty(id)) {
                throw new BusinessException("id不能为空");
            }

            if(StringUtil.isEmpty(data)) {
                throw new BusinessException("data不能为空");
            }

            if(StringUtil.isEmpty(sign)) {
                throw new BusinessException("sign不能为空");
            }

            Map<String, String> paramMap = new HashMap();
            paramMap.put("tableName", tableName);
            paramMap.put("id", id);
            paramMap.put("data", data);
            paramMap.put("method", "addFormInfo");
            if(!SignatureUtil.checkSign(paramMap, "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753", sign)) {
                throw new BusinessException("签名验证失败");
            }

            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
            if(head == null) {
                throw new BusinessException("该表单不是online表单");
            }

            if(head.getJformType().intValue() != 1 && head.getJformType().intValue() != 2) {
                throw new BusinessException("不支持该类型的表单的操作");
            }

            Map<String, Object> dataForm = this.dataBaseService.findOneForJdbc(tableName, id);
            if(dataForm != null) {
                throw new BusinessException("表单数据已存在");
            }

            Map formData;
            if(head.getJformType().intValue() == 1) {
                try {
                    new HashMap();
                    formData = JSONHelper.json2Map(data);
                } catch (Exception var14) {
                    throw new BusinessException("json解析异常");
                }

                formData.put("id", id);
                this.dataBaseService.insertTable(tableName, formData);
            } else if(head.getJformType().intValue() == 2) {
                try {
                    label68: {
                        new HashMap();
                        formData = JSONHelper.json2MapList(data);
                        List<Map<String, Object>> list = (List)formData.get(tableName);
                        if(list != null && list.size() > 0) {
                            Map<String, Object> mainMap = (Map)list.get(0);
                            if(mainMap.get("id") != null && !"".equals((String)mainMap.get("id"))) {
                                if(!id.equals((String)mainMap.get("id"))) {
                                    throw new BusinessException("id与主表id不一致");
                                }
                                break label68;
                            }

                            throw new BusinessException("主表数据缺少id");
                        }

                        throw new BusinessException("主表数据异常");
                    }
                } catch (Exception var15) {
                    throw new BusinessException("json解析异常");
                }

                this.dataBaseService.insertTableMore(formData, tableName);
            }

            j.setMsg("新增表单数据成功");
        } catch (BusinessException var16) {
            j.setSuccess(false);
            j.setMsg(var16.getMessage());
        } catch (Exception var17) {
            j.setSuccess(false);
            j.setMsg("系统异常");
        }

        return j;
    }

    @RequestMapping(
            params = {"updateFormInfo"}
    )
    @ResponseBody
    public TableJson updateFormInfo(String tableName, String id, String data, String sign, HttpServletRequest request, HttpServletResponse response) {
        TableJson j = new TableJson();

        try {
            if(StringUtil.isEmpty(tableName)) {
                throw new BusinessException("tableName不能为空");
            }

            if(StringUtil.isEmpty(id)) {
                throw new BusinessException("id不能为空");
            }

            if(StringUtil.isEmpty(data)) {
                throw new BusinessException("data不能为空");
            }

            if(StringUtil.isEmpty(sign)) {
                throw new BusinessException("sign不能为空");
            }

            Map<String, String> paramMap = new HashMap();
            paramMap.put("tableName", tableName);
            paramMap.put("id", id);
            paramMap.put("data", data);
            paramMap.put("method", "updateFormInfo");
            if(!SignatureUtil.checkSign(paramMap, "26F72780372E84B6CFAED6F7B19139CC47B1912B6CAED753", sign)) {
                throw new BusinessException("签名验证失败");
            }

            CgFormHeadEntity head = this.cgFormFieldService.getCgFormHeadByTableName(tableName);
            if(head == null) {
                throw new BusinessException("该表单不是online表单");
            }

            if(head.getJformType().intValue() != 1 && head.getJformType().intValue() != 2) {
                throw new BusinessException("不支持该类型的表单的操作");
            }

            Map<String, Object> dataForm = this.dataBaseService.findOneForJdbc(tableName, id);
            if(dataForm == null) {
                throw new BusinessException("表单数据不存在");
            }

            Map formData;
            if(head.getJformType().intValue() == 1) {
                try {
                    new HashMap();
                    formData = JSONHelper.json2Map(data);
                } catch (Exception var14) {
                    throw new BusinessException("json解析异常");
                }

                this.dataBaseService.updateTable(tableName, id, formData);
            } else if(head.getJformType().intValue() == 2) {
                try {
                    label68: {
                        new HashMap();
                        formData = JSONHelper.json2MapList(data);
                        List<Map<String, Object>> list = (List)formData.get(tableName);
                        if(list != null && list.size() > 0) {
                            Map<String, Object> mainMap = (Map)list.get(0);
                            if(mainMap.get("id") != null && !"".equals((String)mainMap.get("id"))) {
                                if(!id.equals((String)mainMap.get("id"))) {
                                    throw new BusinessException("id与主表id不一致");
                                }
                                break label68;
                            }

                            throw new BusinessException("主表数据缺少id");
                        }

                        throw new BusinessException("主表数据异常");
                    }
                } catch (Exception var15) {
                    throw new BusinessException("json解析异常");
                }

                this.dataBaseService.updateTableMore(formData, tableName);
            }

            j.setMsg("更新表单数据成功");
        } catch (BusinessException var16) {
            j.setSuccess(false);
            j.setMsg(var16.getMessage());
        } catch (Exception var17) {
            j.setSuccess(false);
            j.setMsg("系统异常");
        }

        return j;
    }
}
