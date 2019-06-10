package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FeedbackQuestionActionSupport extends ActionSupport {
    
    private String id;
    private String tries;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    private String triesFB;
    
    public FeedbackQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se define la ruta donde se van a buscar los xml
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        try{
            //procedimiento para leer contenido del xml
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"\\xmls\\Questions.xml\\");
            Document documento=builder.build(archivoXML);
            Element raiz = documento.getRootElement();
            List lista=raiz.getChildren("teacher");
            //se iteran los nodos de los profesores
            for (Object l : lista) {
                Element teacher = (Element)l;
                if (teacher.getAttributeValue("username").equals(userName)) {
                    //al encontrar al profesor que buscamos se crea un nodo feedback
                    //se escriben los atributos de feedback y se añade al nodo del profesor
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
            //procedimiento para escribir contenido en el xml
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
