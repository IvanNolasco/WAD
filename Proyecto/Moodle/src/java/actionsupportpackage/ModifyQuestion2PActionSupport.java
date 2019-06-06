package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ModifyQuestion2PActionSupport extends ActionSupport {
    
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
    
    public ModifyQuestion2PActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se define la ruta donde se van a buscar los xml
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        
        int sobre=0; //Variable para sobreescribir el archivo si es necesario
        
        //Si el usuario mete un archivo nuevo se crea el file y se guarda en el servidor
        if(media!=null)
        {
            File salida = new File(pathString+"media/"+mediaFileName);
            FileInputStream in = new FileInputStream(media);
            FileOutputStream out = new FileOutputStream(salida);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            sobre=1;
        }
        try {
            //procedimiento para leer contenido xml
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"/xmls/Questions.xml");
            Document documento=builder.build(archivoXML);
            //Se crea el nuevo elemento con la informacion modificada
            Element quest = new Element("question");
            quest.setAttribute("id", id);
            quest.setAttribute("qtype", qtype);
            quest.setAttribute("name", name);
            quest.setAttribute("question", question);
            quest.setAttribute("max", maxQuant);
            for(int i=0; i<optionList.size(); i++){
                        Element option = new Element("option");
                        option.setAttribute("text", optionList.get(i).getText());
                        option.setAttribute("points", String.valueOf(optionList.get(i).getPoints()));
                        quest.addContent(option);
            }
            if (sobre == 0)
                quest.setAttribute("source", mediaFileName);
            else
                quest.setAttribute("source", "media\\"+mediaFileName);
            quest.setAttribute("type", mediaContentType);
            
            //Se recupera el elemento raiz
            Element raiz = documento.getRootElement();
            //Se recuperan todos los nodos teacher
            List lista=raiz.getChildren("teacher");
            //Se recorre cada uno de los teacher
            for (Object l : lista) {
                Element teacher = (Element)l;
                //Se saca el profesor que coincida con el profesor en sesion
                if (teacher.getAttributeValue("username").equals(userName)) {
                    //Se listan todas las preguntas creadas por el profesor
                    List questions = teacher.getChildren("question");
                    //Se consigue la pregunta que coincida con el id
                    for (Object qu : questions) {
                        Element question = (Element)qu;
                        if (question.getAttributeValue("id").equals(id)) {
                            if (sobre == 1) {
                                //al encontrarse la pregunta se elimina el multimedia antiguo asociado a esa pregunta
                                File file = new File(pathString+question.getAttributeValue("source"));
                                file.delete();
                            }
                            questions.remove(qu);
                        }
                    }
                    teacher.addContent(quest);
                }
            }
            //Se escriben los cambios en el xml
            Format formato = Format.getPrettyFormat();
            XMLOutputter xmloutputter = new XMLOutputter(formato);
            FileWriter writer= new FileWriter(pathString+"/xmls/Questions.xml");
            xmloutputter.output(documento, writer);
            writer.close();
            
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        
        try {
            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(pathString+"\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            //iteramos los nodos de los profesores
            for(int i=0;i<teachersList.size();i++) {
                Element teacher = (Element)teachersList.get(i);
                String username = teacher.getAttributeValue("username");  
                if(username.equals(userName)){
                    //al encontrar al profesor que buscamos iteramos sus nodos feedback 
                    List feedbacksList = teacher.getChildren("feedback");
                    for(int j=0;j<feedbacksList.size();j++){
                        Element feedback = (Element)feedbacksList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(feedbackid.equals(this.id)){
                            //el encontrar el feedback que buscamos, se recuoeran sus atributos
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
    
}