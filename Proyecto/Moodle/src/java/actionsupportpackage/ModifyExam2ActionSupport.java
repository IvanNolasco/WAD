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

public class ModifyExam2ActionSupport extends ActionSupport {
    
    public ModifyExam2ActionSupport() {
    }
    
    private List<String> questionList;
    private String nameE;
    
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de profesores
            for (Object t : teachersList) {
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)){
                    //al encontrar al profesor que buscamos iteramos sus nodos de examenes
                    List examList = teacher.getChildren("exam");
                    for (Object e : examList) {
                        Element exam = (Element)e;
                        String name = exam.getAttributeValue("name");
                        if(name.equals(nameE)){
                            //al encontrar el examen que buscamos aliminamos sus nodos preguntas
                            exam.removeChildren("question");
                            for (String id : questionList) {
                                //creamos un nodo por cada pregunta de la lista
                                //y se agrega al nodo de examen
                                Element quest = new Element("question");
                                quest.setAttribute("id", id);
                                exam.addContent(quest);
                            }
                            break;
                        }
                    }
                }
            }
            //procedimiento para escribir contenido en el xml
            Format formato = Format.getPrettyFormat();
            XMLOutputter xmloutputter = new XMLOutputter(formato);
            FileWriter writer= new FileWriter(path+"\\xmls\\Exams.xml");
            xmloutputter.output(document, writer);
            writer.close();
        } catch (Exception e) {
        }
        
        return SUCCESS;
    }
    
    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
        this.questionList = questionList;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }
}
