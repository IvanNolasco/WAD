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

public class ModifyFeedbackPActionSupport extends ActionSupport {
    
    private String id;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    
    public ModifyFeedbackPActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se van a  buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try{
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //se iteran los nodos profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");
                if(username.equals(userName)){ 
                    //al encontrar el profesor que buscamos, iteramos los nodos feedback
                    List feedbackList = teacher.getChildren("feedback");
                    for(int j=0;j<feedbackList.size();j++){
                        Element feedback = (Element)feedbackList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(feedbackid.equals(this.id)){
                            //al encontrar el nodo del feedback que buscamos, se elimina
                            feedbackList.remove(j);
                            //se sobreescriben los atributos de feedback
                            //se agrega al nodo del profesor
                            feedback.setAttribute("id",this.id);
                            feedback.setAttribute("initial", initial);
                            feedback.setAttribute("evaluate", evaluate);
                            feedback.setAttribute("correct", correct);
                            feedback.setAttribute("incorrect", incorrect);
                            teacher.addContent(feedback);
                        }
                        //procedimiento para escribir contenido xml
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
    
}
