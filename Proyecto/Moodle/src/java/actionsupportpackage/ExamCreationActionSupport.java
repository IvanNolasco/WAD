package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class ExamCreationActionSupport extends ActionSupport {
    private List<String> exams;
    private String examsJSON;
    public ExamCreationActionSupport() {
    }

    public List<String> getExams() {
        return exams;
    }

    public void setExams(List<String> exams) {
        this.exams = exams;
    }

    public String getExamsJSON() {
        return examsJSON;
    }

    public void setExamsJSON(String examsJSON) {
        this.examsJSON = examsJSON;
    }
    
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        exams = new ArrayList<String>();
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            JSONArray list = new JSONArray();
            for (Object t : teachersList) {
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)) {
                    List examList = teacher.getChildren("exam");
                    for (Object e : examList) {
                        Element exam = (Element)e;
                        JSONObject obj = new JSONObject();
                        obj.put("name", exam.getAttributeValue("name"));
                        exams.add(exam.getAttributeValue("name"));
                        list.add(obj);
                    }
                    break;
                }
            }
            examsJSON = list.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
}
