/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ModifyQuestionPActionSupport extends ActionSupport {
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private List<Option> optionList;
    private String maxQuant;
    private File media;
    private String mediaContentType;
    private String mediaFileName;

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
    
    public ModifyQuestionPActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        String path = ServletActionContext.getServletContext().getRealPath("/");
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    List questionsList = teacher.getChildren("question");
                    for(int j=0;j<questionsList.size();j++){
                        Element question = (Element)questionsList.get(j);
                        String questionid = question.getAttributeValue("id");
                        if(questionid.equals(this.id)){
                            
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
        
        return SUCCESS;
    }
    
}
