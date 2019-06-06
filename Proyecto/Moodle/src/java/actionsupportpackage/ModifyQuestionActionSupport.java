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

public class ModifyQuestionActionSupport extends ActionSupport {
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private String answer;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    @Override
    public String execute() throws Exception {
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se van a buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
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
                    //al encontrar al profesor que buscamos iteramos sus nodos de preguntas
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String questionid = question.getAttributeValue("id");
                        if(questionid.equals(this.id)){
                            //al encontrar la pregunta que buscamos, se recuperan sus datos
                            this.qtype = question.getAttributeValue("qtype");
                            this.name = question.getAttributeValue("name");
                            this.question = question.getAttributeValue("question");
                            this.answer = question.getAttributeValue("answer");
                            this.mediaFileName = question.getAttributeValue("source");
                            this.mediaContentType = question.getAttributeValue("type");
                            break;
                        }
                    }

                }
            }
             
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
    
    public String getQtype(){
        return qtype;
    }
    
    public void setQtype(String qtype){
        this.qtype = qtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }
    
    public ModifyQuestionActionSupport() {
    }
}
