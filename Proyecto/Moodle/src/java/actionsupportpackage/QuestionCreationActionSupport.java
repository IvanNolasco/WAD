package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class QuestionCreationActionSupport extends ActionSupport {
    
    public QuestionCreationActionSupport() {
    }
    
    private List<Question> questions; 

    public List<Question> getQuestions() {  
        return questions;  
    } 
    
    public void setQuestions(List<Question> questions) {  
        this.questions = questions;  
    } 
    
    @Override
    public String execute() throws IOException { 
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        questions = new ArrayList<Question>(); 
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String id = question.getAttributeValue("id");
                        String name = question.getAttributeValue("name");
                        String questionText = question.getAttributeValue("question");
                        String answer = question.getAttributeValue("answer");
                        Question questionObject = new Question(id, name, questionText, answer);
                        questions.add(questionObject);
                    }

                }
            }
             
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
