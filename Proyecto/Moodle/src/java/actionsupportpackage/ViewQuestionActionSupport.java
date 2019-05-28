package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ViewQuestionActionSupport extends ActionSupport {
    private String id;
    private String name;
    private String qtype;
    private String question;
    private String answer;
    private String source;
    private String type;
    
    private String tries;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    private String triesFB;
    
    
    public ViewQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
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
                    List feedbacksList = teacher.getChildren("feedback");
                    for(int j=0;j<questionsList.size();j++){
                        Element questionE = (Element)questionsList.get(j);
                        String idQ = questionE.getAttributeValue("id");
                        if(idQ.equals(this.id)) {
                            this.name = questionE.getAttributeValue("name");
                            this.qtype = questionE.getAttributeValue("qtype");
                            this.question = questionE.getAttributeValue("question");
                            this.answer = questionE.getAttributeValue("answer");
                            this.source = questionE.getAttributeValue("source");
                            this.type = questionE.getAttributeValue("type");
                            break;
                        }   
                    }
                    for(int k=0;k<feedbacksList.size();k++){
                        Element feedbackE = (Element)feedbacksList.get(k);
                        String idF = feedbackE.getAttributeValue("id");
                        if(idF.equals(this.id)) {
                            this.tries = feedbackE.getAttributeValue("tries");
                            this.initial = feedbackE.getAttributeValue("initial");
                            this.evaluate = feedbackE.getAttributeValue("evaluate");
                            this.correct = feedbackE.getAttributeValue("correct");
                            this.incorrect = feedbackE.getAttributeValue("incorrect");
                            this.triesFB = feedbackE.getAttributeValue("triesFB");
                            break;
                        }
                        
                    }
                }
            }
             
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        return SUCCESS;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTries() {
        return tries;
    }

    public String getInitial() {
        return initial;
    }

    public String getCorrect() {
        return correct;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public String getTriesFB() {
        return triesFB;
    }
}
