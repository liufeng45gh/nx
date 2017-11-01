//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.core.common.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.hibernate.Query;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.dao.IGenericBaseCommonDao;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.extend.template.DataSourceMap;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.GenericsUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ssb.warmline.business.commons.utils.DateUtils;

@Repository
public class CommonDao extends GenericBaseCommonDao implements ICommonDao, IGenericBaseCommonDao {
    public CommonDao() {
    }

    public TSUser getUserByUserIdAndUserNameExits(TSUser user) {
        String password = PasswordUtil.encrypt(user.getPhone(), user.getPassword(), PasswordUtil.getStaticSalt());
        String query = "from TSUser u where u.userName = :username and u.password=:passowrd";
        Query queryObject = this.getSession().createQuery(query);
        queryObject.setParameter("username", user.getUserName());
        queryObject.setParameter("passowrd", password);
        List<TSUser> users = queryObject.list();
        if(users != null && users.size() > 0) {
            return (TSUser)users.get(0);
        } else {
            queryObject = this.getSession().createQuery(query);
            queryObject.setParameter("username", user.getUserName());
            queryObject.setParameter("passowrd", user.getPassword());
            users = queryObject.list();
            return users != null && users.size() > 0?(TSUser)users.get(0):null;
        }
    }

    public void pwdInit(TSUser user, String newPwd) {
        String query = "from TSUser u where u.userName = :username ";
        Query queryObject = this.getSession().createQuery(query);
        queryObject.setParameter("username", user.getUserName());
        List<TSUser> users = queryObject.list();
        if(users != null && users.size() > 0) {
            user = (TSUser)users.get(0);
            String pwd = PasswordUtil.encrypt(user.getUserName(), newPwd, PasswordUtil.getStaticSalt());
            user.setPassword(pwd);
            this.save(user);
        }

    }

    public String getUserRole(TSUser user) {
        String userRole = "";
        List<TSRoleUser> sRoleUser = this.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());

        TSRoleUser tsRoleUser;
        for(Iterator var5 = sRoleUser.iterator(); var5.hasNext(); userRole = userRole + tsRoleUser.getTSRole().getRoleCode() + ",") {
            tsRoleUser = (TSRoleUser)var5.next();
        }

        return userRole;
    }

    public Object uploadFile(UploadFile uploadFile) {
        Object object = uploadFile.getObject();
        if(uploadFile.getFileKey() != null) {
            this.updateEntitie(object);
        } else {
            try {
                uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
                MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
                ReflectHelper reflectHelper = new ReflectHelper(uploadFile.getObject());
                String uploadbasepath = uploadFile.getBasePath();
                if(uploadbasepath == null) {
                    uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
                }

                Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
                String path = uploadbasepath + "/";
                String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + "/" + path;
                File file = new File(realPath);
                if(!file.exists()) {
                    file.mkdirs();
                }

                if(uploadFile.getCusPath() != null) {
                    realPath = realPath + uploadFile.getCusPath() + "/";
                    path = path + uploadFile.getCusPath() + "/";
                    file = new File(realPath);
                    if(!file.exists()) {
                        file.mkdirs();
                    }
                } else {
                    realPath = realPath + DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
                    path = path + DateUtils.getDataString(DateUtils.yyyyMMdd) + "/";
                    file = new File(realPath);
                    if(!file.exists()) {
                        file.mkdir();
                    }
                }

                String entityName = uploadFile.getObject().getClass().getSimpleName();
                if(entityName.equals("TSTemplate")) {
                    realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + ResourceUtil.getConfigByName("templatepath") + "/";
                    path = ResourceUtil.getConfigByName("templatepath") + "/";
                } else if(entityName.equals("TSIcon")) {
                    realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath("/") + uploadFile.getCusPath() + "/";
                    path = uploadFile.getCusPath() + "/";
                }

                String fileName = "";
                String swfName = "";
                Iterator var14 = fileMap.entrySet().iterator();

                while(var14.hasNext()) {
                    Entry<String, MultipartFile> entity = (Entry)var14.next();
                    MultipartFile mf = (MultipartFile)entity.getValue();
                    fileName = mf.getOriginalFilename();
                    swfName = PinyinUtil.getPinYinHeadChar(oConvertUtils.replaceBlank(FileUtils.getFilePrefix(fileName)));
                    String extend = FileUtils.getExtend(fileName);
                    String myfilename = "";
                    String noextfilename = "";
                    if(uploadFile.isRename()) {
                        noextfilename = DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(8);
                        myfilename = noextfilename + "." + extend;
                    } else {
                        myfilename = fileName;
                    }

                    String savePath = realPath + myfilename;
                    String fileprefixName = FileUtils.getFilePrefix(fileName);
                    if(uploadFile.getTitleField() != null) {
                        reflectHelper.setMethodValue(uploadFile.getTitleField(), fileprefixName);
                    }

                    if(uploadFile.getExtend() != null) {
                        reflectHelper.setMethodValue(uploadFile.getExtend(), extend);
                    }

                    uploadFile.getByteField();
                    File savefile = new File(savePath);
                    if(uploadFile.getRealPath() != null) {
                        reflectHelper.setMethodValue(uploadFile.getRealPath(), path + myfilename);
                    }

                    this.saveOrUpdate(object);
                    if("txt".equals(extend)) {
                        byte[] allbytes = mf.getBytes();

                        String contents;
                        try {
                            String head1 = this.toHexString(allbytes[0]);
                            contents = this.toHexString(allbytes[1]);
                            FileOutputStream out;
                            //String contents;
                            if("ef".equals(head1) && "bb".equals(contents)) {
                                contents = new String(mf.getBytes(), "UTF-8");
                                if(StringUtils.isNotBlank(contents)) {
                                    out = new FileOutputStream(savePath);
                                    out.write(contents.getBytes());
                                    out.close();
                                }
                            } else {
                                contents = new String(mf.getBytes(), "GBK");
                                out = new FileOutputStream(savePath);
                                out.write(contents.getBytes());
                                out.close();
                            }
                        } catch (Exception var27) {
                            contents = new String(mf.getBytes(), "UTF-8");
                            if(StringUtils.isNotBlank(contents)) {
                                OutputStream out = new FileOutputStream(savePath);
                                out.write(contents.getBytes());
                                out.close();
                            }
                        }
                    } else {
                        FileCopyUtils.copy(mf.getBytes(), savefile);
                    }

                    if(uploadFile.getSwfpath() != null) {
                        reflectHelper.setMethodValue(uploadFile.getSwfpath(), path + FileUtils.getFilePrefix(myfilename) + ".swf");
                        SwfToolsUtil.convert2SWF(savePath);
                    }
                }
            } catch (Exception var28) {
                ;
            }
        }

        return object;
    }

    private String toHexString(int index) {
        String hexString = Integer.toHexString(index);
        hexString = hexString.substring(hexString.length() - 2);
        return hexString;
    }

    public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile) {
        uploadFile.getResponse().setContentType("UTF-8");
        uploadFile.getResponse().setCharacterEncoding("UTF-8");
        InputStream bis = null;
        BufferedOutputStream bos = null;
        HttpServletResponse response = uploadFile.getResponse();
        HttpServletRequest request = uploadFile.getRequest();
        String ctxPath = request.getSession().getServletContext().getRealPath("/");
        String downLoadPath = "";
        long fileLength = 0L;
        if(uploadFile.getRealPath() != null && uploadFile.getContent() == null) {
            downLoadPath = ctxPath + uploadFile.getRealPath();
            fileLength = (new File(downLoadPath)).length();

            try {
                bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            } catch (FileNotFoundException var21) {
                var21.printStackTrace();
            }
        } else {
            if(uploadFile.getContent() != null) {
                bis = new ByteArrayInputStream(uploadFile.getContent());
            }

            fileLength = (long)uploadFile.getContent().length;
        }

        try {
            if(!uploadFile.isView() && uploadFile.getExtend() != null) {
                if(uploadFile.getExtend().equals("text")) {
                    response.setContentType("text/plain;");
                } else if(uploadFile.getExtend().equals("doc")) {
                    response.setContentType("application/msword;");
                } else if(uploadFile.getExtend().equals("xls")) {
                    response.setContentType("application/ms-excel;");
                } else if(uploadFile.getExtend().equals("pdf")) {
                    response.setContentType("application/pdf;");
                } else if(!uploadFile.getExtend().equals("jpg") && !uploadFile.getExtend().equals("jpeg")) {
                    response.setContentType("application/x-msdownload;");
                } else {
                    response.setContentType("image/jpeg;");
                }

                response.setHeader("Content-disposition", "attachment; filename=" + new String((uploadFile.getTitleField() + "." + uploadFile.getExtend()).getBytes("GBK"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileLength));
            }

            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];

            int bytesRead;
            while(-1 != (bytesRead = ((InputStream)bis).read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException var22) {
            var22.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    ((InputStream)bis).close();
                }

                if(bos != null) {
                    bos.close();
                }
            } catch (IOException var20) {
                var20.printStackTrace();
            }

        }

        return response;
    }

    public Map<Object, Object> getDataSourceMap(Template template) {
        return DataSourceMap.getDataSourceMap();
    }

    public HttpServletResponse createXml(ImportFile importFile) {
        HttpServletResponse response = importFile.getResponse();
        HttpServletRequest request = importFile.getRequest();

        try {
            Document document = DocumentHelper.createDocument();
            document.setXMLEncoding("UTF-8");
            String rootname = importFile.getEntityName() + "s";
            Element rElement = document.addElement(rootname);
            Class entityClass = importFile.getEntityClass();
            String[] fields = importFile.getField().split(",");
            List objList = this.loadAll(entityClass);
            Class classType = entityClass.getClass();
            Iterator var12 = objList.iterator();

            while(var12.hasNext()) {
                Object t = var12.next();
                Element childElement = rElement.addElement(importFile.getEntityName());

                for(int i = 0; i < fields.length; ++i) {
                    String fieldName = fields[i];
                    if(i == 0) {
                        childElement.addAttribute(fieldName, String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
                    } else {
                        Element name = childElement.addElement(fieldName);
                        name.setText(String.valueOf(TagUtil.fieldNametoValues(fieldName, t)));
                    }
                }
            }

            String ctxPath = request.getSession().getServletContext().getRealPath("");
            File fileWriter = new File(ctxPath + "/" + importFile.getFileName());
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileWriter));
            xmlWriter.write(document);
            xmlWriter.close();
            UploadFile uploadFile = new UploadFile(request, response);
            uploadFile.setRealPath(importFile.getFileName());
            uploadFile.setTitleField(importFile.getFileName());
            uploadFile.setExtend("bak");
            this.viewOrDownloadFile(uploadFile);
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        return response;
    }

    public void parserXml(String fileName) {
        try {
            File inputXml = new File(fileName);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputXml);
            Element employees = document.getRootElement();
            Iterator i = employees.elementIterator();

            while(i.hasNext()) {
                Element employee = (Element)i.next();
                Class entityClass = GenericsUtils.getEntityClass(employee.getName());
                Field[] fields = TagUtil.getFiled(entityClass);
                String id = employee.attributeValue(fields[0].getName());
                Object obj1 = this.getEntity(entityClass, id);
                if(obj1 == null) {
                    obj1 = entityClass.newInstance();
                }

                Iterator j = employee.elementIterator();

                while(j.hasNext()) {
                    Element node = (Element)j.next();

                    for(int k = 0; k < fields.length; ++k) {
                        if(node.getName().equals(fields[k].getName())) {
                            String fieldName = fields[k].getName();
                            String stringLetter = fieldName.substring(0, 1).toUpperCase();
                            String setName = "set" + stringLetter + fieldName.substring(1);
                            Method setMethod = entityClass.getMethod(setName, new Class[]{fields[k].getType()});
                            String type = TagUtil.getColumnType(fieldName, fields);
                            if(type.equals("int")) {
                                setMethod.invoke(obj1, new Object[]{new Integer(node.getText())});
                            } else if(type.equals("string")) {
                                setMethod.invoke(obj1, new Object[]{node.getText().toString()});
                            } else if(type.equals("short")) {
                                setMethod.invoke(obj1, new Object[]{new Short(node.getText())});
                            } else if(type.equals("double")) {
                                setMethod.invoke(obj1, new Object[]{new Double(node.getText())});
                            } else if(type.equals("Timestamp")) {
                                setMethod.invoke(obj1, new Object[]{new Timestamp(DateUtils.str2Date(node.getText(), DateUtils.datetimeFormat).getTime())});
                            }
                        }
                    }
                }

                if(obj1 != null) {
                    this.saveOrUpdate(obj1);
                } else {
                    this.save(obj1);
                }
            }
        } catch (Exception var20) {
            var20.printStackTrace();
        }

    }

    public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive) {
        List<ComboTree> trees = new ArrayList();
        Iterator var7 = all.iterator();

        while(var7.hasNext()) {
            Object obj = var7.next();
            trees.add(this.comboTree(obj, comboTreeModel, in, recursive));
        }

        all.clear();
        return trees;
    }

    private ComboTree comboTree(Object obj, ComboTreeModel comboTreeModel, List in, boolean recursive) {
        ComboTree tree = new ComboTree();
        Map<String, Object> attributes = new HashMap();
        ReflectHelper reflectHelper = new ReflectHelper(obj);
        String id = oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getIdField()));
        tree.setId(id);
        tree.setText(oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getTextField())));
        if(comboTreeModel.getSrcField() != null) {
            attributes.put("href", oConvertUtils.getString(reflectHelper.getMethodValue(comboTreeModel.getSrcField())));
            tree.setAttributes(attributes);
        }

        if(in != null && in.size() > 0) {
            Iterator var10 = in.iterator();

            while(var10.hasNext()) {
                Object inobj = var10.next();
                ReflectHelper reflectHelper2 = new ReflectHelper(inobj);
                String inId = oConvertUtils.getString(reflectHelper2.getMethodValue(comboTreeModel.getIdField()));
                if(inId.equals(id)) {
                    tree.setChecked(Boolean.valueOf(true));
                }
            }
        }

        List curChildList = (List)reflectHelper.getMethodValue(comboTreeModel.getChildField());
        if(curChildList != null && curChildList.size() > 0) {
            tree.setState("closed");
            tree.setChecked(Boolean.valueOf(false));
            if(recursive) {
                List<ComboTree> children = new ArrayList();
                List nextChildList = new ArrayList(curChildList);
                Iterator var13 = nextChildList.iterator();

                while(var13.hasNext()) {
                    Object childObj = var13.next();
                    ComboTree t = this.comboTree(childObj, comboTreeModel, in, recursive);
                    children.add(t);
                }

                tree.setChildren(children);
            }
        }

        curChildList.clear();
        return tree;
    }

    public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
        List<TreeGrid> treegrid = new ArrayList();

        TreeGrid tg;
        for(Iterator var5 = all.iterator(); var5.hasNext(); treegrid.add(tg)) {
            Object obj = var5.next();
            ReflectHelper reflectHelper = new ReflectHelper(obj);
            tg = new TreeGrid();
            String id = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIdField()));
            String src = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getSrc()));
            String text = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getTextField()));
            String isparent;
            if(StringUtils.isNotEmpty(treeGridModel.getOrder())) {
                isparent = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getOrder()));
                tg.setOrder(isparent);
            }

            tg.setId(id);
            if(treeGridModel.getIcon() != null) {
                isparent = TagUtil.fieldNametoValues(treeGridModel.getIcon(), obj).toString();
                if(isparent != null) {
                    tg.setCode(isparent);
                } else {
                    tg.setCode("");
                }
            }

            if(treeGridModel.getIsparent() != null) {
                isparent = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIsparent()));
                if(isparent != null) {
                    tg.setCode(isparent);
                } else {
                    tg.setCode("");
                }
            }

            if(StringUtils.isNotEmpty(treeGridModel.getIsparent())) {
                isparent = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getIsparent()));
                tg.setIsparent(isparent);
            }

            tg.setSrc(src);
            tg.setText(text);
            Object ptext;
            if(treeGridModel.getParentId() != null) {
                ptext = TagUtil.fieldNametoValues(treeGridModel.getParentId(), obj);
                if(ptext != null) {
                    tg.setParentId(ptext.toString());
                } else {
                    tg.setParentId("");
                }
            }

            if(treeGridModel.getParentText() != null) {
                ptext = TagUtil.fieldNametoValues(treeGridModel.getTextField(), obj);
                if(ptext != null) {
                    tg.setParentText(ptext.toString());
                } else {
                    tg.setParentText("");
                }
            }

            List childList = (List)reflectHelper.getMethodValue(treeGridModel.getChildList());
            if(childList != null && childList.size() > 0) {
                tg.setState("closed");
            }

            if(treeGridModel.getRoleid() != null) {
                String[] opStrings = new String[0];
                List<TSRoleFunction> roleFunctions = this.findByProperty(TSRoleFunction.class, "TSFunction.id", id);
                if(roleFunctions.size() > 0) {
                    Iterator var15 = roleFunctions.iterator();

                    while(var15.hasNext()) {
                        TSRoleFunction tRoleFunction = (TSRoleFunction)var15.next();
                        if(tRoleFunction.getTSRole().getId().toString().equals(treeGridModel.getRoleid())) {
                            String bbString = tRoleFunction.getOperation();
                            if(bbString != null) {
                                opStrings = bbString.split(",");
                                break;
                            }
                        }
                    }
                }

                List<TSOperation> operateions = this.findByProperty(TSOperation.class, "TSFunction.id", id);
                StringBuffer attributes = new StringBuffer();
                if(operateions.size() > 0) {
                    Iterator var28 = operateions.iterator();

                    label97:
                    while(true) {
                        while(true) {
                            if(!var28.hasNext()) {
                                break label97;
                            }

                            TSOperation tOperation = (TSOperation)var28.next();
                            if(opStrings.length < 1) {
                                attributes.append("<input type=checkbox name=operatons value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
                            } else {
                                StringBuffer sb = new StringBuffer();
                                sb.append("<input type=checkbox name=operatons");

                                for(int i = 0; i < opStrings.length; ++i) {
                                    if(opStrings[i].equals(tOperation.getId().toString())) {
                                        sb.append(" checked=checked");
                                    }
                                }

                                sb.append(" value=" + tOperation.getId() + "_" + id + ">" + tOperation.getOperationname());
                                attributes.append(sb.toString());
                            }
                        }
                    }
                }

                tg.setOperations(attributes.toString());
            }

            if(treeGridModel.getFieldMap() != null) {
                tg.setFieldMap(new HashMap());
                Iterator var23 = treeGridModel.getFieldMap().entrySet().iterator();

                while(var23.hasNext()) {
                    Entry<String, Object> entry = (Entry)var23.next();
                    Object fieldValue = reflectHelper.getMethodValue(entry.getValue().toString());
                    tg.getFieldMap().put((String)entry.getKey(), fieldValue);
                }
            }

            if(treeGridModel.getFunctionType() != null) {
                String functionType = oConvertUtils.getString(reflectHelper.getMethodValue(treeGridModel.getFunctionType()));
                tg.setFunctionType(functionType);
            }
        }

        return treegrid;
    }
}
