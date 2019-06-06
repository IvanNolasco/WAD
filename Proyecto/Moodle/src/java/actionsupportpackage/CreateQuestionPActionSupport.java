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

public class CreateQuestionPActionSupport extends ActionSupport {    
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private List<Option> optionList;
    private String maxQuant;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    @Override
    public String execute() throws Exception {
        //se define la ruta en la que se van a buscar los xml
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        //recuperamos el usarname de la sesion
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        
        try{           
            //se define la ruta para escribir el archivo multimedia 
            File salida = new File(pathString+"media/"+mediaFileName);
            //se transfiere el archivo multimedia al servidor
            FileInputStream in = new FileInputStream(media);
            FileOutputStream out = new FileOutputStream(salida);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            //procedimiento para leer contenido de xml
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"/xmls/Questions.xml");
            Document documento=builder.build(archivoXML);
            Element raiz = documento.getRootElement();
            List lista=raiz.getChildren("teacher");
            //iteramos los nodos de profesores
            for (Object l : lista) {
                Element teacher = (Element)l;
                if (teacher.getAttributeValue("username").equals(userName)) {
                    //al encontrar el profesor que buscamos, creamos un nodo pregunta
                    //se escriben los atributos y se añade al nodo del profesor
                    Element quest = new Element("question");
                    quest.setAttribute("id", id);
                    quest.setAttribute("qtype", qtype);
                    quest.setAttribute("name", name);
                    quest.setAttribute("question", question);
                    quest.setAttribute("max", maxQuant);
                    quest.setAttribute("source", "media\\"+mediaFileName);
                    quest.setAttribute("type", mediaContentType);
                    //se iteran las opciones de la pregunta recuperados del formulario
                    for(int i=0; i<optionList.size(); i++){
                        //por cada opcion se crea un nodo y se añade al nodo de pregunta
                        Element option = new Element("option");
                        option.setAttribute("text", optionList.get(i).getText());
                        option.setAttribute("points", String.valueOf(optionList.get(i).getPoints()));
                        quest.addContent(option);
                    }
                    teacher.addContent(quest);
                    break;
                }
            }
            //procedimiento para escribir el contenido al xml
            Format formato = Format.getPrettyFormat();
            XMLOutputter xmloutputter = new XMLOutputter(formato);
            FileWriter writer= new FileWriter(pathString+"/xmls/Questions.xml");
            xmloutputter.output(documento, writer);
            writer.close();
            return SUCCESS;
        }
        catch(JDOMException e){
            e.printStackTrace();
        }
       
        return "fail";
        
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
    
    
}
