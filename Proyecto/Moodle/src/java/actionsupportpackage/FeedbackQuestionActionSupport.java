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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FeedbackQuestionActionSupport extends ActionSupport {
    
    public String id;
    public String tries;
    public String initial;
    public String evaluate;
    public String correct;
    public String incorrect;
    public String triesFB;
           
    
    public FeedbackQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        JSONParser parser = new JSONParser();
        try{
             //Write XML
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"\\xmls\\Questions.xml\\");
            Document documento=builder.build(archivoXML);
            Element raiz = documento.getRootElement();
            List lista=raiz.getChildren("teacher");
            for (Object l : lista) {
                Element teacher = (Element)l;
                if (teacher.getAttributeValue("username").equals(userName)) {
                    Element quest = new Element("feedback");
                    quest.setAttribute("id", id);
                    quest.setAttribute("tries", tries);
                    quest.setAttribute("initial", initial);
                    quest.setAttribute("evaluate", evaluate);
                    quest.setAttribute("correct", correct);
                    quest.setAttribute("incorrect", incorrect);
                    quest.setAttribute("triesFB", triesFB);
                    teacher.addContent(quest);
                }
            }
            //AGREGA EL USUARIO AL ELEMENTO RAIZ
            Format formato = Format.getPrettyFormat();
            XMLOutputter xmloutputter = new XMLOutputter(formato);
            FileWriter writer= new FileWriter(pathString+"\\xmls\\Questions.xml\\");
            xmloutputter.output(documento, writer);
            writer.close();
            
            return SUCCESS;
        }
        catch(Exception e){
            e.printStackTrace();
        }
       
        return "fail";
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
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
