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

public class CreateFeedbackPActionSupport extends ActionSupport {
    
    private String id;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    
    public CreateFeedbackPActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        System.out.println(id);
        //se define la ruta en la que se van a buscar los xml*
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        try{
            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"\\xmls\\Questions.xml\\");
            Document documento=builder.build(archivoXML);
            Element raiz = documento.getRootElement();
            List lista=raiz.getChildren("teacher");
            //iteramos los nodos de los profesores
            for (Object l : lista) {
                Element teacher = (Element)l;
                if (teacher.getAttributeValue("username").equals(userName)) {
                    //al encontrar al profesor que buscamos
                    //creamos un nodo feedback y lo añadimos al nodo del profesor
                    Element quest = new Element("feedback");
                    quest.setAttribute("id", id);
                    quest.setAttribute("initial", initial);
                    quest.setAttribute("evaluate", evaluate);
                    quest.setAttribute("correct", correct);
                    quest.setAttribute("incorrect", incorrect);
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

    public void setId(String Id) {
        this.id = Id;
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
