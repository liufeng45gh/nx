//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.dao.JeecgDictDao;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSDatalogEntity;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSLog;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ssb.warmline.business.commons.utils.DateUtils;

@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {
    @Autowired
    private JeecgDictDao jeecgDictDao;

    public SystemServiceImpl() {
    }

    public TSUser checkUserExits(TSUser user) throws Exception {
        return this.commonDao.getUserByUserIdAndUserNameExits(user);
    }

    public List<DictEntity> queryDict(String dicTable, String dicCode, String dicText) {
        List<DictEntity> dictList = null;
        if(StringUtil.isEmpty(dicTable)) {
            dictList = this.jeecgDictDao.querySystemDict(dicCode);
            Iterator var6 = dictList.iterator();

            while(var6.hasNext()) {
                DictEntity t = (DictEntity)var6.next();
                t.setTypename(MutiLangUtil.getMutiLangInstance().getLang(t.getTypename()));
            }
        } else {
            dicText = StringUtil.isEmpty(dicText, dicCode);
            dictList = this.jeecgDictDao.queryCustomDict(dicTable, dicCode, dicText);
        }

        return dictList;
    }

    public void addLog(String logcontent, Short loglevel, Short operatetype) {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        String broswer = BrowserUtils.checkBrowse(request);
        TSLog log = new TSLog();
        log.setLogcontent(logcontent);
        log.setLoglevel(loglevel);
        log.setOperatetype(operatetype);
        log.setNote(oConvertUtils.getIp());
        log.setBroswer(broswer);
        log.setOperatetime(DateUtils.gettimestamp());
        log.setTSUser(ResourceUtil.getSessionUserName());
        this.commonDao.save(log);
    }

    public TSType getType(String typecode, String typename, TSTypegroup tsTypegroup) {
        List<TSType> ls = this.commonDao.findHql("from TSType where typecode = ? and typegroupid = ?", new Object[]{typecode, tsTypegroup.getId()});
        TSType actType = null;
        if(ls != null && ls.size() != 0) {
            actType = (TSType)ls.get(0);
        } else {
            actType = new TSType();
            actType.setTypecode(typecode);
            actType.setTypename(typename);
            actType.setTSTypegroup(tsTypegroup);
            this.commonDao.save(actType);
        }

        return actType;
    }

    public TSTypegroup getTypeGroup(String typegroupcode, String typgroupename) {
        TSTypegroup tsTypegroup = (TSTypegroup)this.commonDao.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
        if(tsTypegroup == null) {
            tsTypegroup = new TSTypegroup();
            tsTypegroup.setTypegroupcode(typegroupcode);
            tsTypegroup.setTypegroupname(typgroupename);
            this.commonDao.save(tsTypegroup);
        }

        return tsTypegroup;
    }

    public TSTypegroup getTypeGroupByCode(String typegroupCode) {
        TSTypegroup tsTypegroup = (TSTypegroup)this.commonDao.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupCode);
        return tsTypegroup;
    }

    public void initAllTypeGroups() {
        List<TSTypegroup> typeGroups = this.commonDao.loadAll(TSTypegroup.class);
        Iterator var3 = typeGroups.iterator();

        while(var3.hasNext()) {
            TSTypegroup tsTypegroup = (TSTypegroup)var3.next();
            ResourceUtil.allTypeGroups.put(tsTypegroup.getTypegroupcode().toLowerCase(), tsTypegroup);
            List<TSType> types = this.commonDao.findByProperty(TSType.class, "TSTypegroup.id", tsTypegroup.getId());
            ResourceUtil.allTypes.put(tsTypegroup.getTypegroupcode().toLowerCase(), types);
        }

    }

    public void refleshTypesCach(TSType type) {
        TSTypegroup tsTypegroup = type.getTSTypegroup();
        TSTypegroup typeGroupEntity = (TSTypegroup)this.commonDao.get(TSTypegroup.class, tsTypegroup.getId());
        List<TSType> types = this.commonDao.findByProperty(TSType.class, "TSTypegroup.id", tsTypegroup.getId());
        ResourceUtil.allTypes.put(typeGroupEntity.getTypegroupcode().toLowerCase(), types);
    }

    public void refleshTypeGroupCach() {
        ResourceUtil.allTypeGroups.clear();
        List<TSTypegroup> typeGroups = this.commonDao.loadAll(TSTypegroup.class);
        Iterator var3 = typeGroups.iterator();

        while(var3.hasNext()) {
            TSTypegroup tsTypegroup = (TSTypegroup)var3.next();
            ResourceUtil.allTypeGroups.put(tsTypegroup.getTypegroupcode().toLowerCase(), tsTypegroup);
        }

    }

    public Set<String> getOperationCodesByRoleIdAndFunctionId(String roleId, String functionId) {
        Set<String> operationCodes = new HashSet();
        TSRole role = (TSRole)this.commonDao.get(TSRole.class, roleId);
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = this.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
        if(rFunctions != null && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            if(tsRoleFunction.getOperation() != null) {
                String[] operationArry = tsRoleFunction.getOperation().split(",");

                for(int i = 0; i < operationArry.length; ++i) {
                    operationCodes.add(operationArry[i]);
                }
            }
        }

        return operationCodes;
    }

    public Set<String> getOperationCodesByUserIdAndFunctionId(String userId, String functionId) {
        Set<String> operationCodes = new HashSet();
        List<TSRoleUser> rUsers = this.findByProperty(TSRoleUser.class, "TSUser.id", userId);
        Iterator var6 = rUsers.iterator();

        while(true) {
            TSRoleFunction tsRoleFunction;
            do {
                List rFunctions;
                do {
                    do {
                        if(!var6.hasNext()) {
                            return operationCodes;
                        }

                        TSRoleUser ru = (TSRoleUser)var6.next();
                        TSRole role = ru.getTSRole();
                        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
                        cq1.eq("TSRole.id", role.getId());
                        cq1.eq("TSFunction.id", functionId);
                        cq1.add();
                        rFunctions = this.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
                    } while(rFunctions == null);
                } while(rFunctions.size() <= 0);

                tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            } while(tsRoleFunction.getOperation() == null);

            String[] operationArry = tsRoleFunction.getOperation().split(",");

            for(int i = 0; i < operationArry.length; ++i) {
                operationCodes.add(operationArry[i]);
            }
        }
    }

    public void flushRoleFunciton(String id, TSFunction newFunction) {
        TSFunction functionEntity = (TSFunction)this.getEntity(TSFunction.class, id);
        if(functionEntity.getTSIcon() != null && StringUtil.isNotEmpty(functionEntity.getTSIcon().getId())) {
            TSIcon oldIcon = (TSIcon)this.getEntity(TSIcon.class, functionEntity.getTSIcon().getId());
            if(!oldIcon.getIconClas().equals(newFunction.getTSIcon().getIconClas())) {
                HttpSession session = ContextHolderUtils.getSession();
                TSUser user = ResourceUtil.getSessionUserName();
                List<TSRoleUser> rUsers = this.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
                Iterator var9 = rUsers.iterator();

                while(var9.hasNext()) {
                    TSRoleUser ru = (TSRoleUser)var9.next();
                    TSRole role = ru.getTSRole();
                    session.removeAttribute(role.getId());
                }
            }

        }
    }

    public String generateOrgCode(String id, String pid) {
        int orgCodeLength = 2;
        if("3".equals(ResourceUtil.getOrgCodeLengthType())) {
            orgCodeLength = 3;
        }

        String newOrgCode = "";
        String sql;
        Map pOrgCodeMap;
        String curOrgCode;
        if(!StringUtils.hasText(pid)) {
            sql = "select max(t.org_code) orgCode from t_s_depart t where t.parentdepartid is null";
            pOrgCodeMap = this.commonDao.findOneForJdbc(sql, new Object[0]);
            if(pOrgCodeMap.get("orgCode") != null) {
                curOrgCode = pOrgCodeMap.get("orgCode").toString();
                newOrgCode = String.format("%0" + orgCodeLength + "d", new Object[]{Integer.valueOf(Integer.valueOf(curOrgCode).intValue() + 1)});
            } else {
                newOrgCode = String.format("%0" + orgCodeLength + "d", new Object[]{Integer.valueOf(1)});
            }
        } else {
            sql = "select max(t.org_code) orgCode from t_s_depart t where t.parentdepartid = ?";
            pOrgCodeMap = this.commonDao.findOneForJdbc(sql, new Object[]{pid});
            String subOrgCode;
            if(pOrgCodeMap.get("orgCode") != null) {
                curOrgCode = pOrgCodeMap.get("orgCode").toString();
                String pOrgCode = curOrgCode.substring(0, curOrgCode.length() - orgCodeLength);
                subOrgCode = curOrgCode.substring(curOrgCode.length() - orgCodeLength, curOrgCode.length());
                newOrgCode = pOrgCode + String.format("%0" + orgCodeLength + "d", new Object[]{Integer.valueOf(Integer.valueOf(subOrgCode).intValue() + 1)});
            } else {
                curOrgCode = "select max(t.org_code) orgCode from t_s_depart t where t.id = ?";
                pOrgCodeMap = this.commonDao.findOneForJdbc(curOrgCode, new Object[]{pid});
                subOrgCode = pOrgCodeMap.get("orgCode").toString();
                newOrgCode = subOrgCode + String.format("%0" + orgCodeLength + "d", new Object[]{Integer.valueOf(1)});
            }
        }

        return newOrgCode;
    }

    public Set<String> getOperationCodesByRoleIdAndruleDataId(String roleId, String functionId) {
        Set<String> operationCodes = new HashSet();
        TSRole role = (TSRole)this.commonDao.get(TSRole.class, roleId);
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = this.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
        if(rFunctions != null && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            if(tsRoleFunction.getDataRule() != null) {
                String[] operationArry = tsRoleFunction.getDataRule().split(",");

                for(int i = 0; i < operationArry.length; ++i) {
                    operationCodes.add(operationArry[i]);
                }
            }
        }

        return operationCodes;
    }

    public Set<String> getOperationCodesByUserIdAndDataId(String userId, String functionId) {
        Set<String> dataRulecodes = new HashSet();
        List<TSRoleUser> rUsers = this.findByProperty(TSRoleUser.class, "TSUser.id", userId);
        Iterator var6 = rUsers.iterator();

        while(true) {
            TSRoleFunction tsRoleFunction;
            do {
                List rFunctions;
                do {
                    do {
                        if(!var6.hasNext()) {
                            return dataRulecodes;
                        }

                        TSRoleUser ru = (TSRoleUser)var6.next();
                        TSRole role = ru.getTSRole();
                        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
                        cq1.eq("TSRole.id", role.getId());
                        cq1.eq("TSFunction.id", functionId);
                        cq1.add();
                        rFunctions = this.getListByCriteriaQuery(cq1, Boolean.valueOf(false));
                    } while(rFunctions == null);
                } while(rFunctions.size() <= 0);

                tsRoleFunction = (TSRoleFunction)rFunctions.get(0);
            } while(tsRoleFunction.getDataRule() == null);

            String[] operationArry = tsRoleFunction.getDataRule().split(",");

            for(int i = 0; i < operationArry.length; ++i) {
                dataRulecodes.add(operationArry[i]);
            }
        }
    }

    public void initAllTSIcons() {
        List<TSIcon> list = this.loadAll(TSIcon.class);
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            TSIcon tsIcon = (TSIcon)var3.next();
            ResourceUtil.allTSIcons.put(tsIcon.getId(), tsIcon);
        }

    }

    public void upTSIcons(TSIcon icon) {
        ResourceUtil.allTSIcons.put(icon.getId(), icon);
    }

    public void delTSIcons(TSIcon icon) {
        ResourceUtil.allTSIcons.remove(icon.getId());
    }

    public void addDataLog(String tableName, String dataId, String dataContent) {
        int versionNumber = 0;
        Integer integer = (Integer)this.commonDao.singleResult("select max(versionNumber) from TSDatalogEntity where tableName = '" + tableName + "' and dataId = '" + dataId + "'");
        if(integer != null) {
            versionNumber = integer.intValue();
        }

        TSDatalogEntity tsDatalogEntity = new TSDatalogEntity();
        tsDatalogEntity.setTableName(tableName);
        tsDatalogEntity.setDataId(dataId);
        tsDatalogEntity.setDataContent(dataContent);
        tsDatalogEntity.setVersionNumber(Integer.valueOf(versionNumber + 1));
        this.commonDao.save(tsDatalogEntity);
    }
}
