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

public class DeleteQuestionActionSupport extends ActionSupport { 
    private String id;    
    
    @Override
    public String execute() throws Exception {
        //se define la ruta donde se van a buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        try{
            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //se iteran los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos se iteran sus nodos de preguntas
                    List questionsList = teacher.getChildren("question");
                    List feedbackList = teacher.getChildren("feedback");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String questionid = question.getAttributeValue("id");
                        Element feedback = (Element)feedbackList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(questionid.equals(this.id)&&feedbackid.equals(this.id)){
                            //al encontrar los nodos de pregunta y feedback se eliminan
                            questionsList.remove(j);
                            feedbackList.remove(j);
                            //procedimiento para escribir contenido en el xml
                            Format formato = Format.getPrettyFormat();
                            XMLOutputter xmloutputter = new XMLOutputter(formato);
                            FileWriter writer = new FileWriter(path+"\\xmls\\Questions.xml");
                            xmloutputter.output(document, writer);
                            }
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
}
