package org.jeecgframework.core.common.dao;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.extend.template.Template;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSUser;

public abstract interface ICommonDao extends IGenericBaseCommonDao
{
  public abstract void pwdInit(TSUser paramTSUser, String paramString);

  public abstract TSUser getUserByUserIdAndUserNameExits(TSUser paramTSUser);

  public abstract String getUserRole(TSUser paramTSUser);

  public abstract <T> T uploadFile(UploadFile paramUploadFile);

  public abstract HttpServletResponse viewOrDownloadFile(UploadFile paramUploadFile);

  public abstract Map<Object, Object> getDataSourceMap(Template paramTemplate);

  public abstract HttpServletResponse createXml(ImportFile paramImportFile);

  public abstract void parserXml(String paramString);

  public abstract List<ComboTree> ComboTree(List paramList1, ComboTreeModel paramComboTreeModel, List paramList2, boolean paramBoolean);

  public abstract List<TreeGrid> treegrid(List paramList, TreeGridModel paramTreeGridModel);
}

/* Location:           D:\用户目录\我的文档\Tencent Files\863916185\FileRecv\nx.zip
 * Qualified Name:     ROOT.WEB-INF.classes.org.jeecgframework.core.common.dao.ICommonDao
 * JD-Core Version:    0.6.2
 */