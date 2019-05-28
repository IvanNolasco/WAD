/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author navi_
 */
public class ModifyExam extends ActionSupport {
    
    private List<Question> questions;
    private String id;
    
    public ModifyExam() {
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
    
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        
        List<Integer> questionsExam = new ArrayList<Integer>();
        questions = new ArrayList<Question>(); 
  
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    List examsList = teacher.getChildren("exam");
                    for(int j=0; j<examsList.size();j++){
                        Element exam = (Element) examsList.get(j);
                        String examname = exam.getAttributeValue("name");
                        if(examname.equals(id)){
                            List questionsList = exam.getChildren("question");
                            for(int k=0;k<questionsList.size();k++){
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
                        String idQ = question.getAttributeValue("id");
                        String qtype = question.getAttributeValue("qtype");
                        String name = question.getAttributeValue("name");
                        String questionText = question.getAttributeValue("question");
                        String answer = question.getAttributeValue("answer");
                        Question questionObject = new Question(idQ, qtype, name, questionText, answer);
                        Integer idI = Integer.parseInt(idQ);
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
    
}
