package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CreateExamActionSupport extends ActionSupport {
    
    private List<Question> questions;
    
    public CreateExamActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        questions = new ArrayList<Question>(); 
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            //procedimiento para leer el contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //se iteran los nodos de profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos, se iteran sus nodos de preguntas
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        //de cada pregunta se recuperan sus atributos y se guardan en una lista
                        Element question = (Element)questionsList.get(j);
                        String id = question.getAttributeValue("id");
                        String qtype = question.getAttributeValue("qtype");
                        String name = question.getAttributeValue("name");
                        String questionText = question.getAttributeValue("question");
                        String answer = question.getAttributeValue("answer");
                        Question questionObject = new Question(id, qtype, name, questionText, answer);
                        questions.add(questionObject);
                    }
                    break;
                }
            }
             
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
