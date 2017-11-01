//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.service.impl;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.MutiLangEntity;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mutiLangService")
@Transactional
public class MutiLangServiceImpl extends CommonServiceImpl implements MutiLangServiceI {
    @Autowired
    private HttpServletRequest request;

    public MutiLangServiceImpl() {
    }

    public void initAllMutiLang() {
        List<MutiLangEntity> mutiLang = this.commonDao.loadAll(MutiLangEntity.class);
        Iterator var3 = mutiLang.iterator();

        while(var3.hasNext()) {
            MutiLangEntity mutiLangEntity = (MutiLangEntity)var3.next();
            ResourceUtil.mutiLangMap.put(mutiLangEntity.getLangKey() + "_" + mutiLangEntity.getLangCode(), mutiLangEntity.getLangContext());
        }

    }

    public void putMutiLang(String langKey, String langCode, String langContext) {
        ResourceUtil.mutiLangMap.put(langKey + "_" + langCode, langContext);
    }

    public void putMutiLang(MutiLangEntity mutiLangEntity) {
        ResourceUtil.mutiLangMap.put(mutiLangEntity.getLangKey() + "_" + mutiLangEntity.getLangCode(), mutiLangEntity.getLangContext());
    }

    public String getLang(String langKey) {
        String language = BrowserUtils.getBrowserLanguage(this.request);
        if(this.request.getSession().getAttribute("lang") != null) {
            language = (String)this.request.getSession().getAttribute("lang");
        }

        String langContext = (String)ResourceUtil.mutiLangMap.get(langKey + "_" + language);
        if(StringUtil.isEmpty(langContext)) {
            langContext = (String)ResourceUtil.mutiLangMap.get("common.notfind.langkey_" + this.request.getSession().getAttribute("lang"));
            if("null".equals(langContext) || langContext == null || langKey.startsWith("?")) {
                langContext = "";
            }

            langContext = langContext + langKey;
        }

        return langContext;
    }

    public String getLang(String lanKey, String langArg) {
        String langContext = StringUtil.getEmptyString();
        if(StringUtil.isEmpty(langArg)) {
            langContext = this.getLang(lanKey);
        } else {
            String[] argArray = langArg.split(",");
            langContext = this.getLang(lanKey);

            for(int i = 0; i < argArray.length; ++i) {
                String langKeyArg = argArray[i].trim();
                String langKeyContext = this.getLang(langKeyArg);
                langContext = StringUtil.replace(langContext, "{" + i + "}", langKeyContext);
            }
        }

        return langContext;
    }

    public void refleshMutiLangCach() {
        ResourceUtil.mutiLangMap.clear();
        this.initAllMutiLang();
    }
}
