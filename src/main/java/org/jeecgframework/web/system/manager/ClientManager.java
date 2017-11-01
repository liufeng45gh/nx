//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.system.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.EhcacheUtil;
import org.jeecgframework.web.system.pojo.base.Client;

public class ClientManager {
    private final String CACHENAME = "eternalCache";
    private final String OnlineClientsKey = "onLineClients";
    private static ClientManager instance = new ClientManager();

    private ClientManager() {
    }

    public static ClientManager getInstance() {
        return instance;
    }

    private boolean addClientToCachedMap(String sessionId, Client client) {
        HashMap onLineClients;
        if(EhcacheUtil.get("eternalCache", "onLineClients") == null) {
            onLineClients = new HashMap();
        } else {
            onLineClients = (HashMap)EhcacheUtil.get("eternalCache", "onLineClients");
        }

        onLineClients.put(sessionId, client);
        EhcacheUtil.put("eternalCache", "onLineClients", onLineClients);
        return true;
    }

    private boolean removeClientFromCachedMap(String sessionId) {
        if(EhcacheUtil.get("eternalCache", "onLineClients") != null) {
            HashMap<String, Client> onLineClients = (HashMap)EhcacheUtil.get("eternalCache", "onLineClients");
            onLineClients.remove(sessionId);
            EhcacheUtil.put("eternalCache", "onLineClients", onLineClients);
            return true;
        } else {
            return false;
        }
    }

    public void addClinet(String sessionId, Client client) {
        ContextHolderUtils.getSession().setAttribute(sessionId, client);
        if(client != null) {
            Client ret = new Client();
            ret.setIp(client.getIp());
            ret.setLogindatetime(client.getLogindatetime());
            ret.setUser(client.getUser());
            this.addClientToCachedMap(sessionId, ret);
        }

    }

    public void removeClinet(String sessionId) {
        ContextHolderUtils.getSession().removeAttribute(sessionId);
        this.removeClientFromCachedMap(sessionId);
    }

    public Client getClient(String sessionId) {
        return !StringUtils.isEmpty(sessionId) && ContextHolderUtils.getSession().getAttribute(sessionId) != null?(Client)ContextHolderUtils.getSession().getAttribute(sessionId):null;
    }

    public Client getClient() {
        String sessionId = ContextHolderUtils.getSession().getId();
        return !StringUtils.isEmpty(sessionId) && ContextHolderUtils.getSession().getAttribute(sessionId) != null?(Client)ContextHolderUtils.getSession().getAttribute(sessionId):null;
    }

    public Collection<Client> getAllClient() {
        if(EhcacheUtil.get("eternalCache", "onLineClients") != null) {
            HashMap<String, Client> onLineClients = (HashMap)EhcacheUtil.get("eternalCache", "onLineClients");
            return onLineClients.values();
        } else {
            return new ArrayList();
        }
    }
}
