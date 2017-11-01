//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.cgform.common;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jodd.util.StringUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.LogUtil;
import org.w3c.dom.Document;

public class OfficeHtmlUtil {
    int WORD_HTML = 8;
    int WORD_TXT = 7;
    int EXCEL_HTML = 44;
    private static final String regEx_style = "<[\\s]*?(style|xml|meta|font|xml|del|ins|o:p|head|!)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(style|xml|meta|font|xml|del|ins|o:p|head|>)[\\s]*?>";
    private static final String regEx_attr1 = "[\\s] ?xmlns?(:v|:o|:w|)=\"([^\"]+)\"";
    private static final String regEx_attr2 = "style='([^']+)'";
    private static final String regEx_attr3 = "[\\s]?(class|lang)=([^?(\\s|>)]+)";
    private static final String regEx_attr4 = "<span[^>]+>";
    private static final String regEx_attr5 = "<\\/span>";
    private static final String regEx_attr7 = "width=.[0-9]*";
    private static final String regEx_attr8 = "valign=top";
    private static final String regEx_attr6 = "\\#{([a-zA-Z_0-9]+)\\}";
    private static final String regEx_replace = "[^>]+>";

    public OfficeHtmlUtil() {
    }

    public void wordToHtml(String docfile, String htmlfile) throws Exception {
        ActiveXComponent app = null;

        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.invoke(docs, "Open", 1, new Object[]{docfile, new Variant(false), new Variant(true)}, new int[1]).toDispatch();
            Dispatch.invoke(doc, "SaveAs", 1, new Object[]{htmlfile, new Variant(this.WORD_HTML)}, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(doc, "Close", new Object[]{f});
        } catch (Exception var10) {
            throw new Exception("请确认，Word转化组件是否安装！");
        } finally {
            if(app != null) {
                app.invoke("Quit", new Variant[0]);
            }

        }

    }

    public void WordConverterHtml(String docfile, String htmlfile) {
        try {
            InputStream input = new FileInputStream(docfile);
            HWPFDocument wordDocument = new HWPFDocument(input);
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty("encoding", "UTF-8");
            serializer.setOutputProperty("indent", "yes");
            serializer.setOutputProperty("method", "html");
            serializer.transform(domSource, streamResult);
            outStream.close();
            String content = new String(outStream.toByteArray(), "UTF-8");
            this.stringToFile(content, htmlfile);
        } catch (Exception var13) {
            var13.printStackTrace();
        }

    }

    public void excelToHtml(String xlsfile, String htmlfile) {
        ActiveXComponent app = new ActiveXComponent("Excel.Application");

        try {
            app.setProperty("Visible", new Variant(false));
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.invoke(excels, "Open", 1, new Object[]{xlsfile, new Variant(false), new Variant(true)}, new int[1]).toDispatch();
            Dispatch.invoke(excel, "SaveAs", 1, new Object[]{htmlfile, new Variant(this.EXCEL_HTML)}, new int[1]);
            Variant f = new Variant(false);
            Dispatch.call(excel, "Close", new Object[]{f});
            LogUtil.info("wordtohtml转换成功");
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            app.invoke("Quit", new Variant[0]);
        }

    }

    public String getInfo(String tmpFile) throws IOException {
        StringBuffer sbFile = null;

        try {
            FileInputStream fin = new FileInputStream(tmpFile);
            InputStreamReader in = null;
            char[] buffer = new char[4096];
            sbFile = new StringBuffer();
            in = new InputStreamReader(fin, "gb2312");

            int len;
            while((len = in.read(buffer)) != -1) {
                String s = new String(buffer, 0, len);
                sbFile.append(s);
            }
        } catch (IOException var8) {
            LogUtil.error(var8.toString());
        }

        return sbFile.toString();
    }

    public void stringToFile(String str, String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            OutputStreamWriter out = null;
            out = new OutputStreamWriter(fout, "gb2312");
            out.write(str);
            out.close();
        } catch (IOException var5) {
            LogUtil.error(var5.toString());
        }

    }

    public String regExReplace(String sourceStr, String oldStr, String newStr) {
        String ls_comStr = oldStr + "[^>]+>";
        String ls_returnStr = "";

        try {
            Pattern pattern = Pattern.compile(ls_comStr, 2);
            Matcher matcher = pattern.matcher(sourceStr);
            ls_returnStr = matcher.replaceAll(newStr);
        } catch (Exception var9) {
            LogUtil.error(var9.toString());
        }

        return ls_returnStr;
    }

    public String doHtml(String htmlStr) {
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");

        try {
            Pattern pattern = Pattern.compile("<[\\s]*?(style|xml|meta|font|xml|del|ins|o:p|head|!)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(style|xml|meta|font|xml|del|ins|o:p|head|>)[\\s]*?>", 2);
            Matcher matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("[\\s] ?xmlns?(:v|:o|:w|)=\"([^\"]+)\"", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("style='([^']+)'", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("[\\s]?(class|lang)=([^?(\\s|>)]+)", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll(" ");
            pattern = Pattern.compile("<span[^>]+>", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("<\\/span>", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("valign=top", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            StringBuilder ls_include = new StringBuilder("");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
            ls_include.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
            ls_include.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\"></link>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
            ls_include.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:4,");
            ls_include.append("btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:");
            ls_include.append("{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().");
            ls_include.append("find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}");
            ls_include.append("else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\")");
            ls_include.append(".hide();}}}},callback:function(data){if(data.success");
            ls_include.append("==true){if(!neibuClickFlag){var win = frameElement.api.opener;frameElement.api.close();win.tip(data.msg);win.reloadTable();}else {alert(data.msg)}}else{if(data.responseText==''||");
            ls_include.append("data.responseText==undefined)$(\"#formobj\").html(data.msg);else $(\"#formobj\")");
            ls_include.append(".html(data.responseText); return false;}if(!neibuClickFlag){var win = frameElement.api.opener;win.reloadTable();}}});});</script>");
            ls_include.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script>", "{0}", lang));
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
            ls_include.append("<style>");
            ls_include.append("body{font-size:12px;}");
            ls_include.append("table{border: 1px solid #000000;padding:0; margin:0 auto;border-collapse: collapse;width:100%;align:right;}");
            ls_include.append("td {border: 1px solid #000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");
            ls_include.append("</style>\r\n<body");
            pattern = Pattern.compile("<body", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll(ls_include.toString().replace("$", "\\$"));
            String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">\r\n<input type=\"hidden\" name=\"tableName\" value=\"\\${tableName?if_exists?html}\"\\/>\r\n<input type=\"hidden\" name=\"id\" value=\"\\${id?if_exists?html}\"\\/>\r\n<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\"\\/>\r\n#{jform_hidden_field}<table";
            pattern = Pattern.compile("<table", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll(ls_form);
            pattern = Pattern.compile("</table>", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("</table>\r\n</form>");
        } catch (Exception var8) {
            LogUtil.error(var8.getMessage());
        }

        return htmlStr;
    }

    public String doPoiHtml(String htmlStr) {
        String regEx_poi1 = "<meta[^>]+>";
        String regEx_poi2 = "<[\\s]*?(style)[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?(style)[\\s]*?>";
        String regEx_poi3 = "[\\s]?(class|lang)=([^?(\\s|>)]+)";
        HttpSession session = ContextHolderUtils.getSession();
        String lang = (String)session.getAttribute("lang");

        try {
            Pattern pattern = Pattern.compile(regEx_poi1, 2);
            Matcher matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile(regEx_poi2, 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile(regEx_poi3, 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("b2\"", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            pattern = Pattern.compile("<tbody>|</tbody>", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("");
            StringBuilder ls_include = new StringBuilder("");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
            ls_include.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"plug-in/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
            ls_include.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\"></link>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>");
            ls_include.append(StringUtil.replace("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools_{0}.js\"></script>", "{0}", lang));
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
            ls_include.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>");
            ls_include.append("<script type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
            ls_include.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:4,btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\").hide();}}}},callback:function(data){var win = frameElement.api.opener;if(data.success==true){frameElement.api.close();win.tip(data.msg);}else{if(data.responseText==''||data.responseText==undefined)$(\"#formobj\").html(data.msg);else $(\"#formobj\").html(data.responseText); return false;}win.reloadTable();}});});</script>");
            ls_include.append("<style>");
            ls_include.append("body{font-size:12px;}");
            ls_include.append("table{border: 1px solid #000000;padding:0; margin:0 auto;border-collapse: collapse;width:100%;align:right;}");
            ls_include.append("td {border: 1px solid #000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");
            ls_include.append("</style>\r\n<body");
            pattern = Pattern.compile("<body", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll(ls_include.toString().replace("$", "\\$"));
            String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">\r\n<input type=\"hidden\" name=\"tableName\" value=\"\\${tableName?if_exists?html}\"\\/>\r\n<input type=\"hidden\" name=\"id\" value=\"\\${id?if_exists?html}\"\\/>\r\n<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\"\\/>\r\n<table";
            pattern = Pattern.compile("<table", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll(ls_form);
            pattern = Pattern.compile("</table>", 2);
            matcher = pattern.matcher(htmlStr);
            htmlStr = matcher.replaceAll("</table>\r\n</form>");
        } catch (Exception var11) {
            LogUtil.error(var11.getMessage());
        }

        return htmlStr;
    }

    public static void main(String[] args) {
        try {
            OfficeHtmlUtil wordtohtml = new OfficeHtmlUtil();
            wordtohtml.WordConverterHtml("D://jeecg//qjd.doc", "D://jeecg//test.html");
            String htmlStr = wordtohtml.getInfo("D://jeecg//test.html");
            htmlStr = wordtohtml.doPoiHtml(htmlStr);
            wordtohtml.stringToFile(htmlStr, "D://jeecg//tt.html");
        } catch (IOException var3) {
            ;
        }

    }
}
