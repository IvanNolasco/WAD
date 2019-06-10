package actionsupportpackage;

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

public class CreateExam2ActionSupport extends ActionSupport {
    
    private List<String> questionList;
    private String nameE;
    
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            //procedimiento para leer el contenido del xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            int existnode = 0; //bandera para saber si existe el nodo del profesor 
            //iteramos los nodos de profesores
            for (Object t : teachersList) {
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)){
                    //al encontrar al profesor que buscamos creamos un nodo para el examen
                    Element exam = new Element("exam");
                    exam.setAttribute("name",nameE);
                    for (String id : questionList) {
                        //por cada pregunta creamos un nodo y lo añadimos al nodo de examen
                        Element quest = new Element("question");
                        quest.setAttribute("id", id);
                        exam.addContent(quest);
                    }
                    teacher.addContent(exam);
                    existnode = 1;
                    break;
                }
            }
            //si no se encuentra el nodo de profesor porque no existe, lo creamos
            if(existnode == 0){
                Element teacher = new Element("teacher");
                Element exam = new Element("exam");
                exam.setAttribute("name",nameE);
                for (String id : questionList) {
                    //por cada pregunta creamos un nodo y lo añadimos al nodo de examen
                    Element quest = new Element("question");
                    quest.setAttribute("id", id);
                    exam.addContent(quest);
                }
                teacher.addContent(exam);
                root.addContent(teacher);
            }
            //procedimiento para escribir el contenido en el xml
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
