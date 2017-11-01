//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgdynamgraph.service.impl.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgdynamgraph.dao.core.CgDynamGraphDao;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
import org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cgDynamGraphService")
@Transactional
public class CgDynamGraphServiceImpl extends CommonServiceImpl implements CgDynamGraphServiceI {
    @Autowired
    private JdbcDao jdbcDao;
    @Autowired
    private CgDynamGraphDao cgDynamGraphDao;

    public CgDynamGraphServiceImpl() {
    }

    public Map<String, Object> queryCgDynamGraphConfig(String reportId) {
        Map<String, Object> cgDynamGraphM = new HashMap(0);
        Map<String, Object> mainM = this.queryCgDynamGraphMainConfig(reportId);
        List<Map<String, Object>> itemsM = this.queryCgDynamGraphItems(reportId);
        List<String> params = this.queryCgDynamGraphParam(reportId);
        cgDynamGraphM.put("main", mainM);
        cgDynamGraphM.put("items", itemsM);
        cgDynamGraphM.put("params", params);
        return cgDynamGraphM;
    }

    public Map<String, Object> queryCgDynamGraphMainConfig(String reportId) {
        return this.cgDynamGraphDao.queryCgDynamGraphMainConfig(reportId);
    }

    public List<Map<String, Object>> queryCgDynamGraphItems(String reportId) {
        return this.cgDynamGraphDao.queryCgDynamGraphItems(reportId);
    }

    public List<String> queryCgDynamGraphParam(String reportId) {
        List<String> list = null;
        CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead = (CgDynamGraphConfigHeadEntity)this.findUniqueByProperty(CgDynamGraphConfigHeadEntity.class, "code", reportId);
        String hql0 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = this.findHql(hql0, new Object[]{cgDynamGraphConfigHead.getId()});
        if(cgDynamGraphConfigParamList != null & cgDynamGraphConfigParamList.size() > 0) {
            list = new ArrayList();
            Iterator var7 = cgDynamGraphConfigParamList.iterator();

            while(var7.hasNext()) {
                CgDynamGraphConfigParamEntity cgDynamGraphConfigParam = (CgDynamGraphConfigParamEntity)var7.next();
                list.add(cgDynamGraphConfigParam.getParamName());
            }
        }

        return list;
    }

    public List<Map<String, Object>> queryByCgDynamGraphSql(String sql, Map params) {
        String querySql = this.getFullSql(sql, params);
        List<Map<String, Object>> result = null;
        result = this.jdbcDao.findForJdbc(querySql, new Object[0]);
        return result;
    }

    private String getFullSql(String sql, Map params) {
        StringBuilder sqlB = new StringBuilder();
        sqlB.append("SELECT t.* FROM ( ");
        sqlB.append(sql + " ");
        sqlB.append(") t ");
        if(params.size() >= 1) {
            sqlB.append("WHERE 1=1  ");
            Iterator it = params.keySet().iterator();

            while(it.hasNext()) {
                String key = String.valueOf(it.next());
                String value = String.valueOf(params.get(key));
                if(!StringUtil.isEmpty(value) && !"null".equals(value)) {
                    sqlB.append(" AND ");
                    sqlB.append(" " + key + value);
                }
            }
        }

        return sqlB.toString();
    }

    public long countQueryByCgDynamGraphSql(String sql, Map params) {
        String querySql = this.getFullSql(sql, params);
        querySql = "SELECT COUNT(*) FROM (" + querySql + ") t2";
        long result = this.jdbcDao.findForLong(querySql, new HashMap(0));
        return result;
    }

    public List<String> getSqlFields(String sql) {
        if(oConvertUtils.isEmpty(sql)) {
            return null;
        } else {
            List<Map<String, Object>> result = this.jdbcDao.findForJdbc(sql, 1, 1);
            if(result.size() < 1) {
                throw new BusinessException("该报表sql没有数据");
            } else {
                Set fieldsSet = ((Map)result.get(0)).keySet();
                List<String> fileds = new ArrayList(fieldsSet);
                return fileds;
            }
        }
    }
}
