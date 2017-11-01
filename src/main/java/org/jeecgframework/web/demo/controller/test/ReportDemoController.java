//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecgframework.web.demo.controller.test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/reportDemoController"})
public class ReportDemoController extends BaseController {
    private static final Logger logger = Logger.getLogger(ReportDemoController.class);
    private static final String CLASS_STUDENT_COUNT_ANALYSIS = "class.student.count.analysis";
    private SystemService systemService;
    @Autowired
    private MutiLangServiceI mutiLangService;

    public ReportDemoController() {
    }

    @Autowired
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @RequestMapping(
            params = {"listAllStatisticByJdbc"}
    )
    public void listAllStatisticByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        List<Map<String, Object>> maplist = this.systemService.findForJdbc("SELECT s.classname classname ,count(className) personcount FROM T_s_Student s group by s.className", (Object[])null);
        Long countSutent = this.systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_student WHERE 1=1");

        Map map;
        Double percentage;
        for(Iterator var7 = maplist.iterator(); var7.hasNext(); map.put("rate", String.format("%.2f", new Object[]{Double.valueOf(percentage.doubleValue() * 100.0D)}) + "%")) {
            map = (Map)var7.next();
            Long personcount = Long.valueOf(Long.parseLong(map.get("personcount").toString()));
            percentage = Double.valueOf(0.0D);
            if(personcount != null && personcount.intValue() != 0) {
                percentage = Double.valueOf((new Double((double)personcount.longValue())).doubleValue() / (double)countSutent.longValue());
            }
        }

        Long count = Long.valueOf(0L);
        if("sqlserver".equals(DBTypeUtil.getDBType())) {
            count = this.systemService.getCountForJdbcParam("select count(0) from (SELECT s.className  classname ,count(className) totalclass FROM T_s_Student  s group by s.className) as t( classname, totalclass)", (Object[])null);
        } else {
            count = this.systemService.getCountForJdbcParam("select count(0) from (SELECT s.className ,count(className) FROM T_s_Student s group by s.className)t", (Object[])null);
        }

        dataGrid.setTotal(count.intValue());
        dataGrid.setResults(maplist);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(
            params = {"studentStatisticTabs"}
    )
    public ModelAndView studentStatisticTabs(HttpServletRequest request) {
        return new ModelAndView("jeecg/demo/base/report/reportDemo");
    }

    @RequestMapping(
            params = {"studentCount"}
    )
    @ResponseBody
    public List<Highchart> studentCount(HttpServletRequest request, String reportType, HttpServletResponse response) {
        List<Highchart> list = new ArrayList();
        new Highchart();
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT className ,count(className) FROM TSStudent group by className");
        List userBroswerList = this.systemService.findByQueryString(sb.toString());
        Long count = this.systemService.getCountForJdbc("SELECT COUNT(1) FROM T_S_student WHERE 1=1");
        List lt = new ArrayList();
        Highchart hc = new Highchart();
        hc.setName(this.mutiLangService.getLang("class.student.count.analysis"));
        hc.setType(reportType);
        if(userBroswerList.size() > 0) {
            Iterator var12 = userBroswerList.iterator();

            while(var12.hasNext()) {
                Object object = var12.next();
                Map<String, Object> map = new HashMap();
                Object[] obj = (Object[])object;
                map.put("name", obj[0]);
                map.put("y", obj[1]);
                Long groupCount = (Long)obj[1];
                Double percentage = Double.valueOf(0.0D);
                if(count != null && count.intValue() != 0) {
                    percentage = Double.valueOf((new Double((double)groupCount.longValue())).doubleValue() / (double)count.longValue());
                }

                map.put("percentage", Double.valueOf(percentage.doubleValue() * 100.0D));
                lt.add(map);
            }
        }

        hc.setData(lt);
        list.add(hc);
        return list;
    }

    @RequestMapping(
            params = {"export"}
    )
    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        String svg = request.getParameter("svg");
        String filename = request.getParameter("filename");
        filename = filename == null?"chart":filename;
        ServletOutputStream out = response.getOutputStream();

        try {
            if(type != null && svg != null) {
                svg = svg.replaceAll(":rect", "rect");
                String ext = "";
                Transcoder t = null;
                if(type.equals("image/png")) {
                    ext = "png";
                    t = new PNGTranscoder();
                } else if(type.equals("image/jpeg")) {
                    ext = "jpg";
                    t = new JPEGTranscoder();
                } else if(type.equals("application/pdf")) {
                    ext = "pdf";
                    t = new PDFTranscoder();
                } else if(type.equals("image/svg+xml")) {
                    ext = "svg";
                }

                response.addHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1") + "." + ext);
                response.addHeader("Content-Type", type);
                if(t != null) {
                    TranscoderInput input = new TranscoderInput(new StringReader(svg));
                    TranscoderOutput output = new TranscoderOutput(out);

                    try {
                        ((Transcoder)t).transcode(input, output);
                    } catch (TranscoderException var15) {
                        out.print("Problem transcoding stream. See the web logs for more details.");
                        var15.printStackTrace();
                    }
                } else if(ext.equals("svg")) {
                    OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
                    writer.append(svg);
                    writer.close();
                } else {
                    out.print("Invalid type: " + type);
                }
            } else {
                response.addHeader("Content-Type", "text/html");
                out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted.\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
            }
        } finally {
            if(out != null) {
                out.flush();
                out.close();
            }

        }

    }
}
