package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ModifyQuestion2ActionSupport extends ActionSupport {
    
    public String id;
    public String name;
    public String question;
    public String answer;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    public String tries;
    public String initial;
    public String evaluate;
    public String correct;
    public String incorrect;
    public String triesFB;
    
    public ModifyQuestion2ActionSupport() {
    }
    
    public String execute() throws Exception {
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        String path = ServletActionContext.getServletContext().getRealPath("/");
  
        int sobre=0; //Variable para sobreescribir el archivo si es necesario
        
        //Si el usuario mete un archivo nuevo se crea el file y se guarda en el servidor
        if(media!=null)
        {
            
            File salida = new File(path+"media/"+mediaFileName);
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
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(path+"/xmls/Questions.xml");
            Document documento=builder.build(archivoXML);
            //Se crea el nuevo elemento con la informacion modificada
            Element quest = new Element("question");
            quest.setAttribute("id", id);
            quest.setAttribute("name", name);
            quest.setAttribute("question", question);
            quest.setAttribute("answer", answer);
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
                        System.out.println(question.getAttributeValue("id")+","+id);
                        if (question.getAttributeValue("id").equals(id)) {
                            System.out.println("entró aqui x2");
                            if (sobre == 1) {
                                //al encontrarse la pregunta se elimina el multimedia antiguo asociado a esa pregunta
                                File file = new File(path+question.getAttributeValue("source"));
                                System.out.println(file.delete());
                            }
                            System.out.println(questions.remove(qu));
                        }
                    }
                    teacher.addContent(quest);
                }
            }
            //Se escriben los cambios en el xml
            Format formato = Format.getPrettyFormat();
            XMLOutputter xmloutputter = new XMLOutputter(formato);
            FileWriter writer= new FileWriter(path+"/xmls/Questions.xml");
            xmloutputter.output(documento, writer);
            writer.close();
            
        } catch (JDOMException e) {
            e.printStackTrace();
        }
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
                    List feedbacksList = teacher.getChildren("feedback");
                    for(int j=0;j<feedbacksList.size();j++){
                        Element feedback = (Element)feedbacksList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if(feedbackid.equals(this.id)){
                            this.tries = feedback.getAttributeValue("tries");
                            this.correct = feedback.getAttributeValue("correct");
                            this.incorrect = feedback.getAttributeValue("incorrect");
                            this.initial = feedback.getAttributeValue("initial");
                            this.evaluate = feedback.getAttributeValue("evaluate");
                            this.triesFB = feedback.getAttributeValue("triesFB");
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

    public String getTries() {
        return tries;
    }

    public void setTries(String tries) {
        this.tries = tries;
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

    public String getTriesFB() {
        return triesFB;
    }

    public void setTriesFB(String triesFB) {
        this.triesFB = triesFB;
    }
    
    
}
