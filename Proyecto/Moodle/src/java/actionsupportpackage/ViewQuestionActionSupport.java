package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ViewQuestionActionSupport extends ActionSupport {
    private String id;
    
    private String questionJSON;
    
    
    public ViewQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se recupera el username desde la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se van a buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            JSONObject obj = new JSONObject();
            //iteramos los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al enconetrar al profesor que buscamos
                    //iteramos sus nodos de preguntas y luego de feedback
                    List questionsList = teacher.getChildren("question");
                    List feedbacksList = teacher.getChildren("feedback");
                    for(int j=0;j<questionsList.size();j++){
                        Element questionE = (Element)questionsList.get(j);
                        String idQ = questionE.getAttributeValue("id");
                        if(idQ.equals(this.id)) {
                            //al encontrar la pregunta que buscamos, recuperamos sus atributos
                            String question = questionE.getAttributeValue("question");
                            String qtype = questionE.getAttributeValue("qtype");
                            String source = questionE.getAttributeValue("source");
                            String type = questionE.getAttributeValue("type");
                            String exact = questionE.getAttributeValue("exact");
                            String casechk = questionE.getAttributeValue("case");
                            obj.put("question", question);
                            obj.put("qtype", qtype);
                            obj.put("casechk", casechk);
                            obj.put("exactchk", exact);
                            obj.put("source", source);
                            obj.put("type", type);
                            List options = questionE.getChildren("option");
                            JSONArray list = new JSONArray();
                            for(int k=0; k<options.size(); k++){
                                Element opt = (Element) options.get(k);
                                String text = opt.getAttributeValue("text");
                                JSONObject obj2 = new JSONObject();
                                obj2.put("text", text);
                                list.add(obj2);
                            }
                            obj.put("options", list);
                            break;
                        }   
                    }
                    for(int k=0;k<feedbacksList.size();k++){
                        Element feedbackE = (Element)feedbacksList.get(k);
                        String idF = feedbackE.getAttributeValue("id");
                        if(idF.equals(this.id)) {
                            //al encontrar el feedback que buscamos, recuperamos sus atributos
                            String tries = feedbackE.getAttributeValue("tries");
                            String initial = feedbackE.getAttributeValue("initial");
                            String evaluate = feedbackE.getAttributeValue("evaluate");
                            String correct = feedbackE.getAttributeValue("correct");
                            String incorrect = feedbackE.getAttributeValue("incorrect");
                            String triesFB = feedbackE.getAttributeValue("triesFB");
                            obj.put("tries", tries);
                            obj.put("initial", initial);
                            obj.put("evaluate", evaluate);
                            obj.put("correct", correct);
                            obj.put("incorrect", incorrect);
                            obj.put("triesFB",triesFB);
                            break;
                        }
                        
                    }
                    break;
                }
            }
            questionJSON = obj.toJSONString();
        }
        catch(JDOMException e) {
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
    
    public void setQuestionJSON(String questionJSON) {
        this.questionJSON = questionJSON;
    }

    public String getQuestionJSON() {
        return questionJSON;
    }
}
