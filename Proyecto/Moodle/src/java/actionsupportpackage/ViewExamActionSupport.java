package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
public class ViewExamActionSupport extends ActionSupport {
    private List<Question> questions;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        questions = new ArrayList<Question>(); 
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            List questionList = null;
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for (Object t : teachersList){
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)){
                    List examList = teacher.getChildren("exam");
                    for (Object e : examList) {
                        Element exam = (Element)e;
                        String name = exam.getAttributeValue("name");
                        if (name.equals(id)){
                            questionList = exam.getChildren("question");
                            break;
                        }
                    }
                    break;
                }
            }
            xmlFile = new File(path+"\\xmls\\Questions.xml");
            document = builder.build(xmlFile);
            root = document.getRootElement();
            teachersList = root.getChildren("teacher");
            for (Object t : teachersList){
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)){
                    List qList = teacher.getChildren("question");
                    for (Object qE : questionList) {
                        Element questionE = (Element)qE;
                        for (Object qQ : qList) {
                            Element questionQ = (Element) qQ;
                            if (questionQ.getAttributeValue("id").equals(questionE.getAttributeValue("id"))) {
                                String id = questionQ.getAttributeValue("id");
                                String type = questionQ.getAttributeValue("type");
                                String name = questionQ.getAttributeValue("name");
                                String questionText = questionQ.getAttributeValue("question");
                                String answer = questionQ.getAttributeValue("answer");
                                Question questionObject = new Question(id, type, name, questionText, answer);
                                questions.add(questionObject);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            
            
        } catch (Exception e) {
        }
        return SUCCESS;
    }
    
}
