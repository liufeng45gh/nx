//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.service.impl.autolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecgframework.core.annotation.Ehcache;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.service.autolist.ConfigServiceI;
import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("configService")
@Transactional
public class ConfigServiceImpl implements ConfigServiceI {
    @Autowired
    private CgFormFieldServiceI tablePropertyService;
    @Autowired
    private CgformButtonServiceI cgformButtonService;
    @Autowired
    private CgformEnhanceJsServiceI cgformEnhanceJsService;

    public ConfigServiceImpl() {
    }

    //@Ehcache
    public Map<String, Object> queryConfigs(String tableName, String jversion) {
        Map<String, Object> configs = new HashMap();
        CgFormHeadEntity tableEntity = null;

        try {
            tableEntity = (CgFormHeadEntity)this.tablePropertyService.findByProperty(CgFormHeadEntity.class, "tableName", tableName).get(0);
            this.loadConfigs(configs, tableEntity);
            return configs;
        } catch (Exception var6) {
            var6.printStackTrace();
            throw new RuntimeException("没有找到该动态列表");
        }
    }

    private void loadConfigs(Map<String, Object> configs, CgFormHeadEntity tableEntity) {
        List<CgFormFieldEntity> columns = tableEntity.getColumns();
        configs.put("config_id", tableEntity.getTableName());
        configs.put("config_name", tableEntity.getContent());
        configs.put("tableName", tableEntity.getTableName());
        configs.put("config_ischeckbox", tableEntity.getIsCheckbox());
        configs.put("config_ispagination", tableEntity.getIsPagination());
        configs.put("config_istree", tableEntity.getIsTree());
        configs.put("config_querymode", tableEntity.getQuerymode());
        configs.put("fileds", columns);
        configs.put("jformVersion", tableEntity.getJformVersion());
        configs.put("tree_parentid_fieldname", tableEntity.getTreeParentIdFieldName());
        configs.put("tree_id_fieldname", tableEntity.getTreeIdFieldname());
        configs.put("tree_fieldname", tableEntity.getTreeFieldname());
        configs.put("tableType", tableEntity.getJformType());
        configs.put("subTables", tableEntity.getSubTableStr());
        String formId = tableEntity.getId();
        List<CgformButtonEntity> buttons = this.cgformButtonService.getCgformButtonListByFormId(formId);
        configs.put("config_buttons", buttons.size() > 0?buttons:new ArrayList(0));
        String jsCode = "";
        CgformEnhanceJsEntity jsEnhance = this.cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("list", formId);
        if(jsEnhance != null) {
            jsCode = jsEnhance.getCgJsStr();
            if(StringUtil.isEmpty(jsCode)) {
                jsCode = "";
            }
        }

        configs.put("config_jsenhance", jsCode);
    }
}
