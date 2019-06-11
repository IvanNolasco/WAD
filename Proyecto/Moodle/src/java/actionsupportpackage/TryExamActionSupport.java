package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author luis_
 */
public class TryExamActionSupport extends ActionSupport {
    private String questions;
    private String id;
    
    public TryExamActionSupport() {
    }
    
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se va a buscar el archivo XML que contiene las preguntas
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            List questionList = null;
            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Exams.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            JSONArray list = new JSONArray();
            //iteramos los nodos de los profesores
            for (Object t : teachersList){
                Element teacher = (Element)t;
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)){
                    //al encontrar al profesor que buscamos, iteramos sus nodos de examen
                    List examList = teacher.getChildren("exam");
                    for (Object e : examList) {
                        Element exam = (Element)e;
                        String name = exam.getAttributeValue("name");
                        if (name.equals(id)){
                            //al encontrar el examen que buscamos
                            //recuperamos sus nodos de preguntas y los guardamos en una lista
                            questionList = exam.getChildren("question");
                            for (Object q : questionList) {
                                Element question = (Element)q;
                                JSONObject obj = new JSONObject();
                                obj.put("id",question.getAttributeValue("id"));
                                list.add(obj);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            questions = list.toJSONString();
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

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
