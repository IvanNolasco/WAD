package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CreateQuestionActionSupport extends ActionSupport {
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private String answer;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    public CreateQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //se define la ruta en la que se van a buscar los xml
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        //se recupera el username desde la sesion
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
                    //al encontrar al profesor que buscamos, creamos un nodo para la pregunta
                    //escribimos los atributos de la pregunta y se añade al nodo del profesor
                    Element quest = new Element("question");
                    quest.setAttribute("id", id);
                    quest.setAttribute("qtype", qtype);
                    quest.setAttribute("name", name);
                    quest.setAttribute("question", question);
                    quest.setAttribute("answer", answer);
                    quest.setAttribute("source", "media\\"+mediaFileName);
                    quest.setAttribute("type", mediaContentType);
                    teacher.addContent(quest);
                    break;
                }
            }
            //procedimiento para escribir contenido en el xml
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

    public void setMedia(File media) {
            this.media = media;
    }

    public void setMediaContentType(String mediaContentType) {
            this.mediaContentType = mediaContentType;
    }

    public void setMediaFileName(String mediaFileName) {
            this.mediaFileName = mediaFileName;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setQtype(String qtype){
        this.qtype = qtype;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
