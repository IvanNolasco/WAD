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

public class ModifyExam extends ActionSupport {
    
    private List<Question> questions;
    private String id;
    
    public ModifyExam() {
    }
     
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        
        List<Integer> questionsExam = new ArrayList<Integer>();
        questions = new ArrayList<Question>(); 
  
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        //procedimiento para encontrar las preguntas del examen
        try {
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos, iteramos sus nodos de examenes
                    List examsList = teacher.getChildren("exam");
                    for(int j=0; j<examsList.size();j++){
                        Element exam = (Element) examsList.get(j);
                        String examname = exam.getAttributeValue("name");
                        if(examname.equals(id)){
                            //al encontrar el examen que buscamos, iteramos sus nodos de preguntas
                            List questionsList = exam.getChildren("question");
                            for(int k=0;k<questionsList.size();k++){
                                //recuperamos el id de cada pregunta y se agrega a una lista
                                //esta lista contandrá las preguntas que actualmente
                                //formen parte del examen
                                Element question = (Element)questionsList.get(k);
                                Integer idI = Integer.parseInt(question.getAttributeValue("id"));
                                questionsExam.add(idI);
                            }
                        }
                    }
                    
                    break;
                }
            }
             
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        //procedimiento para encontrar todas las preguntas del profesor
        try {
            //procedimiento para leer contenido del xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos iteramos sus nodos preguntas
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        //recuperamos cada pregunta y se agrega a una lista
                        Element question = (Element)questionsList.get(j);
                        String idQ = question.getAttributeValue("id");
                        String qtype = question.getAttributeValue("qtype");
                        String name = question.getAttributeValue("name");
                        String questionText = question.getAttributeValue("question");
                        String answer = question.getAttributeValue("answer");
                        Question questionObject = new Question(idQ, qtype, name, questionText, answer);
                        Integer idI = Integer.parseInt(idQ);
                        //verificamos si se encuentra en la lista de preguntas ya seleccionadas
                        if(questionsExam.contains(idI)){
                            questionObject.setCheck("checked");
                        } else{
                            questionObject.setCheck("");
                        }
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
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return this.id;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
