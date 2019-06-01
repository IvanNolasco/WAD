package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
public class QuestionCreationActionSupport extends ActionSupport {
    private List<Question> questions;
    private String questionsJSON;

    public String getQuestionsJSON() {
        return questionsJSON;
    }

    public void setQuestionsJSON(String questionsJSON) {
        this.questionsJSON = questionsJSON;
    }
    
    @Override
    public String execute() throws IOException {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            JSONArray list = new JSONArray();
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String id = question.getAttributeValue("id");
                        String qtype = question.getAttributeValue("qtype");
                        String name = question.getAttributeValue("name");
                        String questionText = question.getAttributeValue("question");
                        String answer = question.getAttributeValue("answer");
                        JSONObject obj = new JSONObject();
                        obj.put("id", id);
                        obj.put("qtype", qtype);
                        obj.put("name", name);
                        obj.put("questionText", questionText);
                        obj.put("answer", answer);
                        list.add(obj);
                    }
                    break;
                }
            }
            questionsJSON = list.toJSONString();
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
