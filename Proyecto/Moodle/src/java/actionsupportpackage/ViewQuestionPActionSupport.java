package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ViewQuestionPActionSupport extends ActionSupport {
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private List<Option> optionList;
    private String maxQuant;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    
    @Override
    public String execute() throws Exception {
        //recuperamos el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //se define la ruta donde se van a buscar los xml
        String path = ServletActionContext.getServletContext().getRealPath("/");
        optionList = new ArrayList<>();
        try {
            //procedimiento para leer contenido del xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos se iteran sus nodos preguntas
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String questionid = question.getAttributeValue("id");
                        if(questionid.equals(this.id)){
                            //al encontrar la pregunta que buscamos recuperamos sus atributos
                            this.qtype = question.getAttributeValue("qtype");
                            this.name = question.getAttributeValue("name");
                            this.question = question.getAttributeValue("question");
                            this.maxQuant = question.getAttributeValue("max");
                            this.mediaFileName = question.getAttributeValue("source");
                            this.mediaContentType = question.getAttributeValue("type");
                            List options = question.getChildren("option");
                            for(int k=0; k<options.size(); k++){
                                Element optionE = (Element) options.get(k);
                                String auxText = optionE.getAttributeValue("text");
                                int auxPoints = Integer.parseInt(optionE.getAttributeValue("points"));
                                Option o = new Option(auxText, auxPoints);
                                System.out.println(o.toString());
                                this.optionList.add(o);
                            }
                            break;
                        }
                    }
                }
            }
             
        }
        catch(JDOMException e) {
            e.printStackTrace();
        }
        
        try {
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos, iteramos sus nodos feedback
                    List feedbacksList = teacher.getChildren("feedback");
                    for(int j=0;j<feedbacksList.size();j++){
                        Element feedback = (Element)feedbacksList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(feedbackid.equals(this.id)){
                            //al encontrar el feedback que bucamos, recuperamos sus atributos
                            this.correct = feedback.getAttributeValue("correct");
                            this.incorrect = feedback.getAttributeValue("incorrect");
                            this.initial = feedback.getAttributeValue("initial");
                            this.evaluate = feedback.getAttributeValue("evaluate");
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

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
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

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public String getMaxQuant() {
        return maxQuant;
    }

    public void setMaxQuant(String maxQuant) {
        this.maxQuant = maxQuant;
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
    
    public ViewQuestionPActionSupport() {
    }
}
