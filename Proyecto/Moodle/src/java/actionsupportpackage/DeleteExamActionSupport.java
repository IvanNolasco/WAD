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

public class DeleteExamActionSupport extends ActionSupport {
    
    private String id;
    
    @Override
    public String execute() throws Exception {
        //se define la ruta donde se van a buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        try {
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de los profesores
            for (Object t : teachersList) {
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos se iteran sus nodos de examenes
                    List examList = teacher.getChildren("exam");
                    for (Object e : examList) {
                        Element exam = (Element)e;
                        if (exam.getAttributeValue("name").equals(id)) {
                            //al encontrar el nodo del examen que buscamos se elimina
                            examList.remove(e);
                            break;
                        }
                    }
                    break;
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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
