//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.service.impl.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.JeecgSqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.demo.entity.test.JeecgJdbcEntity;
import org.jeecgframework.web.demo.service.test.JeecgJdbcServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jeecgJdbcService")
@Transactional
public class JeecgJdbcServiceImpl extends CommonServiceImpl implements JeecgJdbcServiceI {
    @Autowired
    private JdbcDao jdbcDao;

    public JeecgJdbcServiceImpl() {
    }

    public void getDatagrid1(JeecgJdbcEntity pageObj, DataGrid dataGrid) {
        String sqlWhere = this.getSqlWhere(pageObj);
        String sqlCnt = "select count(*) from jeecg_demo t";
        if(!sqlWhere.isEmpty()) {
            sqlCnt = sqlCnt + " where" + sqlWhere;
        }

        Long iCount = this.getCountForJdbcParam(sqlCnt, (Object[])null);
        String sql = "select t.id,t.user_name as userName,d.departname as depId,t.sex,t.age,t.birthday,t.email,t.mobile_phone as mobilePhone from jeecg_demo t left join t_s_depart d on d.id=t.dep_id";
        if(!sqlWhere.isEmpty()) {
            sql = sql + " where" + sqlWhere;
        }

        List list = this.findObjForJdbc(sql, dataGrid.getPage(), dataGrid.getRows(), JeecgJdbcEntity.class);
        dataGrid.setResults(list);
        dataGrid.setTotal(iCount.intValue());
    }

    public void getDatagrid2(JeecgJdbcEntity pageObj, DataGrid dataGrid) {
        String sqlWhere = this.getSqlWhere(pageObj);
        String sqlCnt = "select count(*) from jeecg_demo t";
        if(!sqlWhere.isEmpty()) {
            sqlCnt = sqlCnt + " where" + sqlWhere;
        }

        Long iCount = this.getCountForJdbcParam(sqlCnt, (Object[])null);
        String sql = "select t.*,d.departname from jeecg_demo t left join t_s_depart d on d.id=t.dep_id";
        if(!sqlWhere.isEmpty()) {
            sql = sql + " where" + sqlWhere;
        }

        List<Map<String, Object>> mapList = this.findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
        List list = new ArrayList();
        JeecgJdbcEntity obj = null;
        Iterator var11 = mapList.iterator();

        while(var11.hasNext()) {
            Map m = (Map)var11.next();

            try {
                obj = new JeecgJdbcEntity();
                obj.setId((String)m.get("id"));
                obj.setUserName((String)m.get("user_name"));
                obj.setDepId((String)m.get("departname"));
                String sex = (String)m.get("sex");
                if(sex == null) {
                    obj.setSex("");
                } else if(sex.equals("0")) {
                    obj.setSex("男");
                } else {
                    obj.setSex("女");
                }

                obj.setAge((Integer)m.get("age"));
                Date birthday = (Date)m.get("birthday");
                if(birthday != null) {
                    obj.setBirthday(birthday);
                }

                obj.setEmail((String)m.get("email"));
                obj.setMobilePhone((String)m.get("mobile_phone"));
                list.add(obj);
            } catch (Exception var14) {
                var14.printStackTrace();
            }
        }

        dataGrid.setResults(list);
        dataGrid.setTotal(iCount.intValue());
    }

    public JSONObject getDatagrid3(JeecgJdbcEntity pageObj, DataGrid dataGrid) {
        String sqlWhere = this.getSqlWhere(pageObj);
        String sqlCnt = "select count(*) from jeecg_demo t";
        if(!sqlWhere.isEmpty()) {
            sqlCnt = sqlCnt + " where" + sqlWhere;
        }

        Long iCount = this.getCountForJdbcParam(sqlCnt, (Object[])null);
        String sql = "select t.*,d.departname from jeecg_demo t left join t_s_depart d on d.id=t.dep_id";
        if(!sqlWhere.isEmpty()) {
            sql = sql + " where" + sqlWhere;
        }

        List<Map<String, Object>> mapList = this.findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
        JeecgJdbcServiceImpl.Db2Page[] db2Pages = new JeecgJdbcServiceImpl.Db2Page[]{new JeecgJdbcServiceImpl.Db2Page("id"), new JeecgJdbcServiceImpl.Db2Page("userName", "user_name", (JeecgJdbcServiceImpl.IMyDataExchanger)null), new JeecgJdbcServiceImpl.Db2Page("depId", "departName", (JeecgJdbcServiceImpl.IMyDataExchanger)null), new JeecgJdbcServiceImpl.Db2Page("sex", (String)null, new JeecgJdbcServiceImpl.MyDataExchangerSex()), new JeecgJdbcServiceImpl.Db2Page("age"), new JeecgJdbcServiceImpl.Db2Page("birthday"), new JeecgJdbcServiceImpl.Db2Page("email"), new JeecgJdbcServiceImpl.Db2Page("mobilePhone", "mobile_phone", (JeecgJdbcServiceImpl.IMyDataExchanger)null)};
        JSONObject jObject = this.getJsonDatagridEasyUI(mapList, iCount.intValue(), db2Pages);
        return jObject;
    }

    String getSqlWhere(JeecgJdbcEntity pageObj) {
        String sqlWhere = "";
        if(StringUtil.isNotEmpty(pageObj.getUserName())) {
            if(!sqlWhere.isEmpty()) {
                sqlWhere = sqlWhere + " and";
            }

            sqlWhere = sqlWhere + " t.user_name like '%" + pageObj.getUserName() + "%'";
        }

        if(StringUtil.isNotEmpty(pageObj.getMobilePhone())) {
            if(!sqlWhere.isEmpty()) {
                sqlWhere = sqlWhere + " and";
            }

            sqlWhere = sqlWhere + " t.mobile_phone like '%" + pageObj.getMobilePhone() + "%'";
        }

        return sqlWhere;
    }

    public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, JeecgJdbcServiceImpl.Db2Page[] dataExchanger) {
        String jsonTemp = "{'total':" + iTotalCnt + ",'rows':[";

        for(int j = 0; j < mapList.size(); ++j) {
            Map<String, Object> m = (Map)mapList.get(j);
            if(j > 0) {
                jsonTemp = jsonTemp + ",";
            }

            jsonTemp = jsonTemp + "{";

            for(int i = 0; i < dataExchanger.length; ++i) {
                if(i > 0) {
                    jsonTemp = jsonTemp + ",";
                }

                jsonTemp = jsonTemp + "'" + dataExchanger[i].getKey() + "'" + ":";
                Object objValue = dataExchanger[i].getData(m);
                if(objValue == null) {
                    jsonTemp = jsonTemp + "null";
                } else {
                    jsonTemp = jsonTemp + "'" + objValue + "'";
                }
            }

            jsonTemp = jsonTemp + "}";
        }

        jsonTemp = jsonTemp + "]}";
        JSONObject jObject = JSONObject.fromObject(jsonTemp);
        return jObject;
    }

    public void listAllByJdbc(DataGrid dataGrid) {
        String sql = JeecgSqlUtil.getMethodSql(JeecgSqlUtil.getMethodUrl());
        String countsql = JeecgSqlUtil.getCountSqlBySql(sql);
        List<Map<String, Object>> maplist = this.jdbcDao.findForJdbcParam(sql, dataGrid.getPage(), dataGrid.getRows(), new Object[0]);
        Long count = this.jdbcDao.getCountForJdbcParam(countsql, new Object[0]);
        dataGrid.setTotal(count.intValue());
        dataGrid.setResults(maplist);
    }

    public static void main(String[] args) {
        JeecgJdbcServiceImpl service = new JeecgJdbcServiceImpl();
        service.listAllByJdbc((DataGrid)null);
    }

    class Db2Page {
        String fieldPage;
        String columnDB;
        JeecgJdbcServiceImpl.IMyDataExchanger dataExchanger;

        public Db2Page(String fieldPage) {
            this.fieldPage = fieldPage;
            this.columnDB = fieldPage;
            this.dataExchanger = null;
        }

        public Db2Page(String fieldPage, String columnDB) {
            this.fieldPage = fieldPage;
            if(columnDB == null) {
                this.columnDB = fieldPage;
            } else {
                this.columnDB = columnDB;
            }

            this.dataExchanger = null;
        }

        public Db2Page(String fieldPage, String columnDB, JeecgJdbcServiceImpl.IMyDataExchanger dataExchanger) {
            this.fieldPage = fieldPage;
            if(columnDB == null) {
                this.columnDB = fieldPage;
            } else {
                this.columnDB = columnDB;
            }

            this.dataExchanger = dataExchanger;
        }

        public String getKey() {
            return this.fieldPage;
        }

        public Object getData(Map mapDB) {
            Object objValue = mapDB.get(this.columnDB);
            return objValue == null?null:(this.dataExchanger != null?this.dataExchanger.exchange(objValue):objValue);
        }
    }

    interface IMyDataExchanger {
        Object exchange(Object var1);
    }

    class MyDataExchangerSex implements JeecgJdbcServiceImpl.IMyDataExchanger {
        MyDataExchangerSex() {
        }

        public Object exchange(Object value) {
            return value == null?"":("0".equals(value.toString())?"男":"女");
        }
    }
}
