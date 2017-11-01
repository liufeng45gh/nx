//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.enums.SysACEIconEnum;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;

public class ListtoMenu {
    @Autowired
    private static MutiLangServiceI mutiLangService;
    static int count = 0;

    public ListtoMenu() {
    }

    public static String getMenu(List<TSFunction> set, List<TSFunction> set1) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{\"menus\":[");
        Iterator var4 = set.iterator();

        while(var4.hasNext()) {
            TSFunction node = (TSFunction)var4.next();
            String iconClas = "default";
            if(node.getTSIcon() != null) {
                iconClas = ((TSIcon)ResourceUtil.allTSIcons.get(node.getTSIcon().getId())).getIconClas();
            }

            buffer.append("{\"menuid\":\"" + node.getId() + "\",\"icon\":\"" + iconClas + "\"," + "\"menuname\":\"" + getMutiLang(node.getFunctionName()) + "\",\"menus\":[");
            iterGet(set1, node.getId(), buffer);
            buffer.append("]},");
        }

        buffer.append("]}");
        String tmp = buffer.toString();
        tmp = tmp.replaceAll(",\n]", "\n]");
        tmp = tmp.replaceAll(",]}", "]}");
        return tmp;
    }

    static void iterGet(List<TSFunction> set1, String pid, StringBuffer buffer) {
        Iterator var4 = set1.iterator();

        while(var4.hasNext()) {
            TSFunction node = (TSFunction)var4.next();
            ++count;
            if(node.getTSFunction().getId().equals(pid)) {
                buffer.append("{\"menuid\":\"" + node.getId() + " \",\"icon\":\"" + ((TSIcon)ResourceUtil.allTSIcons.get(node.getTSIcon().getId())).getIconClas() + "\"," + "\"menuname\":\"" + getMutiLang(node.getFunctionName()) + "\",\"url\":\"" + node.getFunctionUrl() + "\"");
                if(count == set1.size()) {
                    buffer.append("}\n");
                }

                buffer.append("},\n");
            }
        }

    }

    public static String getBootMenu(List<TSFunction> pFunctions, List<TSFunction> functions) {
        StringBuffer menuString = new StringBuffer();
        menuString.append("<ul>");
        Iterator var4 = pFunctions.iterator();

        while(var4.hasNext()) {
            TSFunction pFunction = (TSFunction)var4.next();
            menuString.append("<li><a href=\"#\"><span class=\"icon16 icomoon-icon-stats-up\"></span><b>" + getMutiLang(pFunction.getFunctionName()) + "</b></a>");
            boolean hasSubfun = pFunction.hasSubFunction(functions);
            if(!hasSubfun) {
                menuString.append("</li>");
            } else {
                menuString.append("<ul class=\"sub\">");
            }

            Iterator var7 = functions.iterator();

            while(var7.hasNext()) {
                TSFunction function = (TSFunction)var7.next();
                if(function.getTSFunction().getId().equals(pFunction.getId())) {
                    menuString.append("<li><a href=\"" + function.getFunctionUrl() + "\" target=\"contentiframe\"><span class=\"icon16 icomoon-icon-file\"></span>" + getMutiLang(function.getFunctionName()) + "</a></li>");
                }
            }

            if(hasSubfun) {
                menuString.append("</ul></li>");
            }
        }

        menuString.append("</ul>");
        return menuString.toString();
    }

    public static String getEasyuiMenu(List<TSFunction> pFunctions, List<TSFunction> functions) {
        StringBuffer menuString = new StringBuffer();
        Iterator var4 = pFunctions.iterator();

        while(var4.hasNext()) {
            TSFunction pFunction = (TSFunction)var4.next();
            menuString.append("<div  title=\"" + getMutiLang(pFunction.getFunctionName()) + "\" iconCls=\"" + ((TSIcon)ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId())).getIconClas() + "\">");
            boolean hasSubfun = pFunction.hasSubFunction(functions);
            if(!hasSubfun) {
                menuString.append("</div>");
            } else {
                menuString.append("<ul>");
            }

            Iterator var7 = functions.iterator();

            while(var7.hasNext()) {
                TSFunction function = (TSFunction)var7.next();
                if(function.getTSFunction().getId().equals(pFunction.getId())) {
                    String icon = "folder";
                    if(function.getTSIcon() != null) {
                        icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
                    }

                    menuString.append("<li><div onclick=\"addTab('" + getMutiLang(function.getFunctionName()) + "','" + function.getFunctionUrl() + "&clickFunctionId=" + function.getId() + "','" + icon + "')\"  title=\"" + getMutiLang(function.getFunctionName()) + "\" url=\"" + function.getFunctionUrl() + "\" iconCls=\"" + icon + "\"> <a class=\"" + getMutiLang(function.getFunctionName()) + "\" href=\"#\" > <span class=\"icon " + icon + "\" >&nbsp;</span> <span class=\"nav\" >" + getMutiLang(function.getFunctionName()) + "</span></a></div></li>");
                }
            }

            if(hasSubfun) {
                menuString.append("</ul></div>");
            }
        }

        return menuString.toString();
    }

    public static String getEasyuiMultistageMenu(Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(0));
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            TSFunction function = (TSFunction)var4.next();
            menuString.append("<div   title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas() + "\">");
            if(!function.hasSubFunction(map)) {
                menuString.append("</div>");
                menuString.append(getChild(function, 1, map));
            } else {
                menuString.append("<ul>");
                menuString.append(getChild(function, 1, map));
                menuString.append("</ul></div>");
            }
        }

        return menuString.toString();
    }

    public static String getEasyuiMultistageTree(Map<Integer, List<TSFunction>> map, String style) {
        if(map != null && map.size() != 0 && map.containsKey(Integer.valueOf(0))) {
            StringBuffer menuString = new StringBuffer();
            List<TSFunction> list = (List)map.get(Integer.valueOf(0));
            int curIndex = 0;
            TSFunction function;
            Iterator var6;
            if("easyui".equals(style)) {
                for(var6 = list.iterator(); var6.hasNext(); ++curIndex) {
                    function = (TSFunction)var6.next();
                    if(curIndex == 0) {
                        menuString.append("<li>");
                    } else {
                        menuString.append("<li state='closed'>");
                    }

                    menuString.append("<span>").append(getMutiLang(function.getFunctionName())).append("</span>");
                    if(!function.hasSubFunction(map)) {
                        menuString.append("</li>");
                        menuString.append(getChildOfTree(function, 1, map));
                    } else {
                        menuString.append("<ul>");
                        menuString.append(getChildOfTree(function, 1, map));
                        menuString.append("</ul></li>");
                    }
                }
            } else if("shortcut".equals(style)) {
                var6 = list.iterator();

                while(var6.hasNext()) {
                    function = (TSFunction)var6.next();
                    menuString.append("<div   title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas() + "\">");
                    if(!function.hasSubFunction(map)) {
                        menuString.append("</div>");
                        menuString.append(getChildOfTree(function, 1, map));
                    } else {
                        menuString.append("<ul class=\"easyui-tree tree-lines\"  fit=\"false\" border=\"false\">");
                        menuString.append(getChildOfTree(function, 1, map));
                        menuString.append("</ul></div>");
                    }
                }
            }

            return menuString.toString();
        } else {
            return "不具有任何权限,\n请找管理员分配权限";
        }
    }

    private static String getChild(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            if(function.getTSFunction().getId().equals(parent.getId())) {
                if(!function.hasSubFunction(map)) {
                    menuString.append(getLeaf(function));
                } else if(map.containsKey(Integer.valueOf(level + 1))) {
                    menuString.append("<div  class=\"easyui-accordion\"  fit=\"false\" border=\"false\">");
                    menuString.append("<div></div>");
                    menuString.append("<div title=\"" + getMutiLang(function.getFunctionName()) + "\" iconCls=\"" + ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas() + "\"><ul>");
                    menuString.append(getChild(function, level + 1, map));
                    menuString.append("</ul></div>");
                    menuString.append("</div>");
                }
            }
        }

        return menuString.toString();
    }

    private static String getChildOfTree(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            if(function.getTSFunction().getId().equals(parent.getId())) {
                if(!function.hasSubFunction(map)) {
                    menuString.append(getLeafOfTree(function));
                } else if(map.containsKey(Integer.valueOf(level + 1))) {
                    menuString.append("<li state=\"closed\" iconCls=\"" + ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas() + "\" ><span>" + getMutiLang(function.getFunctionName()) + "</span>");
                    menuString.append("<ul >");
                    menuString.append(getChildOfTree(function, level + 1, map));
                    menuString.append("</ul></li>");
                }
            }
        }

        return menuString.toString();
    }

    private static String getLeaf(TSFunction function) {
        StringBuffer menuString = new StringBuffer();
        String icon = "folder";
        if(function.getTSIcon() != null) {
            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
        }

        menuString.append("<li><div onclick=\"addTab('");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("','");
        menuString.append(function.getFunctionUrl());
        menuString.append("&clickFunctionId=");
        menuString.append(function.getId());
        menuString.append("','");
        menuString.append(icon);
        menuString.append("')\"  title=\"");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("\" url=\"");
        menuString.append(function.getFunctionUrl());
        menuString.append("\" iconCls=\"");
        menuString.append(icon);
        menuString.append("\"> <a class=\"");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("\" href=\"#\" > <span class=\"icon ");
        menuString.append(icon);
        menuString.append("\" >&nbsp;</span> <span class=\"nav\" >");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("</span></a></div></li>");
        return menuString.toString();
    }

    private static String getLeafOfTree(TSFunction function) {
        StringBuffer menuString = new StringBuffer();
        String icon = "folder";
        if(function.getTSIcon() != null) {
            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
        }

        menuString.append("<li iconCls=\"");
        menuString.append(icon);
        menuString.append("\"> <a onclick=\"addTab('");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("','");
        menuString.append(function.getFunctionUrl());
        if(function.getFunctionUrl().indexOf("http:") == -1) {
            if(function.getFunctionUrl().indexOf("?") == -1) {
                menuString.append("?clickFunctionId=");
            } else {
                menuString.append("&clickFunctionId=");
            }

            menuString.append(function.getId());
        }

        menuString.append("','");
        menuString.append(icon);
        menuString.append("')\"  title=\"");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("\" url=\"");
        menuString.append(function.getFunctionUrl());
        menuString.append("\" href=\"#\" ><span class=\"nav\" >");
        menuString.append(getMutiLang(function.getFunctionName()));
        menuString.append("</span></a></li>");
        return menuString.toString();
    }

    public static String getBootstrapMenu(Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        menuString.append("<ul class=\"nav\">");
        List<TSFunction> pFunctions = (List)map.get(Integer.valueOf(0));
        if(pFunctions != null && pFunctions.size() != 0) {
            for(Iterator var4 = pFunctions.iterator(); var4.hasNext(); menuString.append("\t</li> ")) {
                TSFunction pFunction = (TSFunction)var4.next();
                boolean hasSub = pFunction.hasSubFunction(map);
                menuString.append("\t<li class=\"dropdown\"> ");
                menuString.append("\t\t<a href=\"javascript:;\" class=\"dropdown-toggle\" data-toggle=\"dropdown\"> ");
                menuString.append("\t\t\t<span class=\"bootstrap-icon\" style=\"background-image: url('" + ((TSIcon)ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId())).getIconPath() + "')\"></span> " + pFunction.getFunctionName() + " ");
                if(hasSub) {
                    menuString.append("\t\t\t<b class=\"caret\"></b> ");
                }

                menuString.append("\t\t</a> ");
                if(hasSub) {
                    menuString.append(getBootStrapChild(pFunction, 1, map));
                }
            }

            menuString.append("</ul>");
            return menuString.toString();
        } else {
            return "";
        }
    }

    private static String getBootStrapChild(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        if(list != null && list.size() != 0) {
            menuString.append("\t\t<ul class=\"dropdown-menu\"> ");
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                TSFunction function = (TSFunction)var6.next();
                if(function.getTSFunction().getId().equals(parent.getId())) {
                    boolean hasSub = function.hasSubFunction(map);
                    String menu_url = function.getFunctionUrl();
                    if(StringUtils.isNotEmpty(menu_url)) {
                        menu_url = menu_url + "&clickFunctionId=" + function.getId();
                    }

                    menuString.append("\t\t<li onclick=\"showContent('" + getMutiLang(function.getFunctionName()) + "','" + menu_url + "')\"  title=\"" + getMutiLang(function.getFunctionName()) + "\" url=\"" + function.getFunctionUrl() + "\" ");
                    if(hasSub) {
                        menuString.append(" class=\"dropdown-submenu\"");
                    }

                    menuString.append(" > ");
                    menuString.append("\t\t\t<a href=\"javascript:;\"> ");
                    menuString.append("\t\t\t\t<span class=\"bootstrap-icon\" style=\"background-image: url('" + function.getTSIcon().getIconPath() + "')\"></span>\t\t ");
                    menuString.append(getMutiLang(function.getFunctionName()));
                    menuString.append("\t\t\t</a> ");
                    if(hasSub) {
                        menuString.append(getBootStrapChild(function, level + 1, map));
                    }

                    menuString.append("\t\t</li> ");
                }
            }

            menuString.append("\t\t</ul> ");
            return menuString.toString();
        } else {
            return "";
        }
    }

    public static String getWebosMenu(Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        StringBuffer DeskpanelString = new StringBuffer();
        StringBuffer dataString = new StringBuffer();
        String menu = "";
        String desk = "";
        String data = "";
        menuString.append("{");
        dataString.append("{app:{");
        DeskpanelString.append("{");
        List<TSFunction> pFunctions = (List)map.get(Integer.valueOf(0));
        if(pFunctions != null && pFunctions.size() != 0) {
            int n = 1;

            for(Iterator var10 = pFunctions.iterator(); var10.hasNext(); ++n) {
                TSFunction pFunction = (TSFunction)var10.next();
                boolean hasSub = pFunction.hasSubFunction(map);
                menuString.append("\"" + pFunction.getId() + "\":");
                menuString.append("{\"id\":\"" + pFunction.getId() + "\",\"name\":\"" + pFunction.getFunctionName() + "\",\"path\":\"" + ((TSIcon)ResourceUtil.allTSIcons.get(pFunction.getTSIcon().getId())).getIconPath() + "\",\"level\":\"" + pFunction.getFunctionLevel() + "\",");
                menuString.append("\"child\":{");
                DeskpanelString.append("Icon" + n + ":[");
                if(hasSub) {
                    DeskpanelString.append(getWebosDeskpanelChild(pFunction, 1, map));
                    dataString.append(getWebosDataChild(pFunction, 1, map));
                }

                DeskpanelString.append("],");
                menuString.append("}},");
            }

            menu = menuString.substring(0, menuString.toString().length() - 1);
            menu = menu + "}";
            data = dataString.substring(0, dataString.toString().length() - 1);
            data = data + "}}";
            desk = DeskpanelString.substring(0, DeskpanelString.toString().length() - 1);
            desk = desk + "}";
            --n;
            return menu + "$$" + desk + "$$" + data + "$$" + n;
        } else {
            return "";
        }
    }

    private static String getWebosChild(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        String menu = "";
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        if(list != null && list.size() != 0) {
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                TSFunction function = (TSFunction)var7.next();
                if(function.getTSFunction().getId().equals(parent.getId())) {
                    boolean hasSub = function.hasSubFunction(map);
                    menuString.append("\"" + function.getId() + "\":");
                    menuString.append("{\"id\":\"" + function.getId() + "\",\"name\":\"" + getMutiLang(function.getFunctionName()) + "\",\"path\":\"" + function.getTSIcon().getIconPath() + "\",\"url\":\"" + function.getFunctionUrl() + "\",\"level\":\"" + function.getFunctionLevel() + "\"}");
                    if(hasSub) {
                        menuString.append("\"child\":{");
                        menuString.append(getWebosChild(function, level + 1, map));
                        menuString.append("\t} ");
                    }

                    menuString.append(",");
                }
            }

            menu = menuString.substring(0, menuString.toString().length() - 1);
            return menu;
        } else {
            return "";
        }
    }

    private static String getWebosDeskpanelChild(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer DeskpanelString = new StringBuffer();
        String desk = "";
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        if(list != null && list.size() != 0) {
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                TSFunction function = (TSFunction)var7.next();
                if(function.getTSFunction().getId().equals(parent.getId())) {
                    DeskpanelString.append("'" + function.getId() + "',");
                }
            }

            desk = DeskpanelString.substring(0, DeskpanelString.toString().length() - 1);
            return desk;
        } else {
            return "";
        }
    }

    private static String getWebosDataChild(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer dataString = new StringBuffer();
        String data = "";
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        if(list != null && list.size() != 0) {
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                TSFunction function = (TSFunction)var7.next();
                if(function.getTSFunction().getId().equals(parent.getId())) {
                    dataString.append("'" + function.getId() + "':{ ");
                    dataString.append("appid:'" + function.getId() + "',");
                    dataString.append("url:'" + function.getFunctionUrl() + "',");
                    dataString.append(getIconAndNameForDesk(function));
                    dataString.append("asc :" + function.getFunctionOrder());
                    dataString.append(" },");
                }
            }

            data = dataString.toString();
            return data;
        } else {
            return "";
        }
    }

    private static String getIconAndNameForDesk(TSFunction function) {
        StringBuffer dataString = new StringBuffer();
        String colName = function.getTSIconDesk() == null?null:function.getTSIconDesk().getIconPath();
        colName = colName != null && !colName.equals("")?colName:"plug-in/sliding/icon/default.png";
        String functionName = getMutiLang(function.getFunctionName());
        dataString.append("icon:'" + colName + "',");
        dataString.append("name:'" + functionName + "',");
        return dataString.toString();
    }

    /** @deprecated */
    @Deprecated
    private static String getIconandName(String functionName) {
        StringBuffer dataString = new StringBuffer();
        if("online开发".equals(functionName)) {
            dataString.append("icon:'Customize.png',");
        } else if("表单配置".equals(functionName)) {
            dataString.append("icon:'Applications Folder.png',");
        } else if("动态表单配置".equals(functionName)) {
            dataString.append("icon:'Documents Folder.png',");
        } else if("用户分析".equals(functionName)) {
            dataString.append("icon:'User.png',");
        } else if("报表分析".equals(functionName)) {
            dataString.append("icon:'Burn.png',");
        } else if("用户管理".equals(functionName)) {
            dataString.append("icon:'Finder.png',");
        } else if("数据字典".equals(functionName)) {
            dataString.append("icon:'friendnear.png',");
        } else if("角色管理".equals(functionName)) {
            dataString.append("icon:'friendgroup.png',");
        } else if("菜单管理".equals(functionName)) {
            dataString.append("icon:'kaikai.png',");
        } else if("图标管理".equals(functionName)) {
            dataString.append("icon:'kxjy.png',");
        } else if("表单验证".equals(functionName)) {
            dataString.append("icon:'qidianzhongwen.png',");
        } else if("一对多模型".equals(functionName)) {
            dataString.append("icon:'qqread.png',");
        } else if("特殊布局".equals(functionName)) {
            dataString.append("icon:'xiami.png',");
        } else if("在线word".equals(functionName)) {
            dataString.append("icon:'musicbox.png',");
        } else if("多附件管理".equals(functionName)) {
            dataString.append("icon:'vadio.png',");
        } else if("数据监控".equals(functionName)) {
            dataString.append("icon:'Super Disk.png',");
        } else if("定时任务".equals(functionName)) {
            dataString.append("icon:'Utilities.png',");
        } else if("系统日志".equals(functionName)) {
            dataString.append("icon:'fastsearch.png',");
        } else if("在线维护".equals(functionName)) {
            dataString.append("icon:'Utilities Folder.png',");
        } else {
            dataString.append("icon:'folder_o.png',");
        }

        dataString.append("name:'" + functionName + "',");
        return dataString.toString();
    }

    private static String getMutiLang(String functionName) {
        if(mutiLangService == null) {
            mutiLangService = (MutiLangServiceI)ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);
        }

        String lang_context = mutiLangService.getLang(functionName);
        return lang_context;
    }

    public static String getDIYMultistageTree(Map<Integer, List<TSFunction>> map) {
        if(map != null && map.size() != 0 && map.containsKey(Integer.valueOf(0))) {
            StringBuffer menuString = new StringBuffer();
            List<TSFunction> list = (List)map.get(Integer.valueOf(0));
            int curIndex = 0;

            for(Iterator var5 = list.iterator(); var5.hasNext(); ++curIndex) {
                TSFunction function = (TSFunction)var5.next();
                menuString.append("<li>");
                menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"menu-icon fa fa-desktop\"></i>").append(getMutiLang(function.getFunctionName()));
                if(!function.hasSubFunction(map)) {
                    menuString.append("</a></li>");
                    menuString.append(getDIYSubMenu(function, 1, map));
                } else {
                    menuString.append("<b class=\"arrow\"></b><ul  class=\"submenu\" >");
                    menuString.append(getDIYSubMenu(function, 1, map));
                    menuString.append("</ul></li>");
                }
            }

            return menuString.toString();
        } else {
            return "不具有任何权限,\n请找管理员分配权限";
        }
    }

    private static String getDIYSubMenu(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            if(function.getTSFunction().getId().equals(parent.getId())) {
                if(!function.hasSubFunction(map)) {
                    menuString.append(getLeafOfDIYTree(function));
                } else if(map.containsKey(Integer.valueOf(level + 1))) {
                    String icon = "folder";

                    try {
                        if(function.getTSIcon() != null) {
                            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
                        }
                    } catch (Exception var9) {
                        ;
                    }

                    menuString.append("<li><a href=\"#\" ><i class=\"menu-icon fa fa-eye pink\" iconCls=\"" + icon + "\" ></i>" + getMutiLang(function.getFunctionName()) + "<b class=\"arrow\"></b>");
                    menuString.append("<ul class=\"submenu\">");
                    menuString.append(getChildOfTree(function, level + 1, map));
                    menuString.append("</ul></li>");
                }
            }
        }

        return menuString.toString();
    }

    public static String getAceMultistageTree(Map<Integer, List<TSFunction>> map) {
        if(map != null && map.size() != 0 && map.containsKey(Integer.valueOf(0))) {
            StringBuffer menuString = new StringBuffer();
            List<TSFunction> list = (List)map.get(Integer.valueOf(0));
            int curIndex = 0;

            for(Iterator var5 = list.iterator(); var5.hasNext(); ++curIndex) {
                TSFunction function = (TSFunction)var5.next();
                menuString.append("<li>");
                menuString.append("<a href=\"#\" class=\"dropdown-toggle\" ><i class=\"" + SysACEIconEnum.toEnum(function.getTSIcon().getIconClas()).getThemes() + "\"></i>");
                menuString.append("<span class=\"menu-text\">");
                menuString.append(getMutiLang(function.getFunctionName()));
                menuString.append("</span>");
                if(!function.hasSubFunction(map)) {
                    menuString.append("</a></li>");
                } else {
                    menuString.append("<b class=\"arrow icon-angle-down\"></b></a><ul  class=\"submenu\" >");
                    menuString.append(getACESubMenu(function, 1, map));
                    menuString.append("</ul></li>");
                }
            }

            return menuString.toString();
        } else {
            return "不具有任何权限,\n请找管理员分配权限";
        }
    }

    private static String getACESubMenu(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            if(function.getTSFunction().getId().equals(parent.getId()) && !function.hasSubFunction(map)) {
                menuString.append(getLeafOfACETree(function));
            }
        }

        return menuString.toString();
    }

    private static String getLeafOfACETree(TSFunction function) {
        StringBuffer menuString = new StringBuffer();
        String icon = "folder";
        if(function.getTSIcon() != null) {
            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
        }

        String name = getMutiLang(function.getFunctionName());
        menuString.append("<li> <a href=\"javascript:addTabs({id:'").append(function.getId());
        menuString.append("',title:'").append(name).append("',close: true,url:'");
        menuString.append(function.getFunctionUrl());
        menuString.append("&clickFunctionId=");
        menuString.append(function.getId());
        menuString.append("'})\"  title=\"");
        menuString.append(name);
        menuString.append("\" url=\"");
        menuString.append(function.getFunctionUrl());
        menuString.append("\"  >");
        menuString.append("<i class=\"icon-double-angle-right\"></i>");
        menuString.append(name);
        menuString.append("</a></li>");
        return menuString.toString();
    }

    private static String getLeafOfDIYTree(TSFunction function) {
        StringBuffer menuString = new StringBuffer();
        String icon = "folder";
        if(function.getTSIcon() != null) {
            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
        }

        String name = getMutiLang(function.getFunctionName());
        menuString.append("<li iconCls=\"");
        menuString.append(icon);
        menuString.append("\"> <a href=\"javascript:loadModule('");
        menuString.append(name);
        menuString.append("','");
        menuString.append(function.getFunctionUrl());
        menuString.append("&clickFunctionId=");
        menuString.append(function.getId());
        menuString.append("','");
        menuString.append(icon);
        menuString.append("')\"  title=\"");
        menuString.append(name);
        menuString.append("\" url=\"");
        menuString.append(function.getFunctionUrl());
        menuString.append("\"  >");
        menuString.append(name);
        menuString.append("</a></li>");
        return menuString.toString();
    }

    public static String getHplusMultistageTree(Map<Integer, List<TSFunction>> map) {
        if(map != null && map.size() != 0 && map.containsKey(Integer.valueOf(0))) {
            StringBuffer menuString = new StringBuffer();
            List<TSFunction> list = (List)map.get(Integer.valueOf(0));
            int curIndex = 0;

            for(Iterator var5 = list.iterator(); var5.hasNext(); ++curIndex) {
                TSFunction function = (TSFunction)var5.next();
                menuString.append("<li>");
                menuString.append("<a href=\"#\" class=\"\" ><i class=\"fa fa-columns\"></i>");
                menuString.append("<span class=\"menu-text\">");
                menuString.append(getMutiLang(function.getFunctionName()));
                menuString.append("</span>");
                menuString.append("<span class=\"fa arrow\">");
                menuString.append("</span>");
                if(!function.hasSubFunction(map)) {
                    menuString.append("</a></li>");
                } else {
                    menuString.append("</a><ul  class=\"nav nav-second-level\" >");
                    menuString.append(getHplusSubMenu(function, 1, map));
                    menuString.append("</ul></li>");
                }
            }

            return menuString.toString();
        } else {
            return "不具有任何权限,\n请找管理员分配权限";
        }
    }

    private static String getHplusSubMenu(TSFunction parent, int level, Map<Integer, List<TSFunction>> map) {
        StringBuffer menuString = new StringBuffer();
        List<TSFunction> list = (List)map.get(Integer.valueOf(level));
        Iterator var6 = list.iterator();

        while(var6.hasNext()) {
            TSFunction function = (TSFunction)var6.next();
            if(function.getTSFunction().getId().equals(parent.getId()) && !function.hasSubFunction(map)) {
                menuString.append(getLeafOfHplusTree(function));
            }
        }

        return menuString.toString();
    }

    private static String getLeafOfHplusTree(TSFunction function) {
        StringBuffer menuString = new StringBuffer();
        String icon = "folder";
        if(function.getTSIcon() != null) {
            icon = ((TSIcon)ResourceUtil.allTSIcons.get(function.getTSIcon().getId())).getIconClas();
        }

        String name = getMutiLang(function.getFunctionName());
        menuString.append("<li> <a class=\"J_menuItem\" href=\"").append(function.getFunctionUrl()).append("\">");
        menuString.append(name);
        menuString.append("</a></li>");
        return menuString.toString();
    }
}
