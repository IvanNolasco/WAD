package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ModifyFeedbackActionSupport extends ActionSupport {
    
    public String id;
    public String tries;
    public String initial;
    public String evaluate;
    public String correct;
    public String incorrect;
    public String triesFB;
    
    public ModifyFeedbackActionSupport() {
    }
    
    public String execute() throws Exception {
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try{
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");
                if(username.equals(userName)){                   
                    List feedbackList = teacher.getChildren("feedback");
                    for(int j=0;j<feedbackList.size();j++){
                        Element feedback = (Element)feedbackList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(feedbackid.equals(this.id)){
                            feedbackList.remove(j);
                            feedback.setAttribute("id",this.id);
                            
                            feedback.setAttribute("tries", tries);
                            feedback.setAttribute("initial", initial);
                            feedback.setAttribute("evaluate", evaluate);
                            feedback.setAttribute("correct", correct);
                            feedback.setAttribute("incorrect", incorrect);
                            feedback.setAttribute("triesFB", triesFB);
                            teacher.addContent(feedback);
                        }
                            Format formato = Format.getPrettyFormat();
                            XMLOutputter xmloutputter = new XMLOutputter(formato);
                            FileWriter writer = new FileWriter(path+"\\xmls\\Questions.xml");
                            xmloutputter.output(document, writer);
                    }
                }
            }
        }
        catch(Exception e){
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

    public String getTries() {
        return tries;
    }

    public void setTries(String tries) {
        this.tries = tries;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }

    public String getTriesFB() {
        return triesFB;
    }

    public void setTriesFB(String triesFB) {
        this.triesFB = triesFB;
    }
    
    
}
