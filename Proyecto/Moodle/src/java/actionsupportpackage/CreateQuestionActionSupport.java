package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateQuestionActionSupport extends ActionSupport {
    
    public String id;
    public String name;
    public String question;
    public String answer;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    public CreateQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        String pathString = ServletActionContext.getServletContext().getRealPath("/");
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
<<<<<<< HEAD
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

        //Write XML
        SAXBuilder builder = new SAXBuilder();
        File archivoXML = new File(pathString+"/xmls/Questions.xml");
        Document documento=builder.build(archivoXML);
        Element raiz = documento.getRootElement();
        List lista=raiz.getChildren("teacher");
        for (Object l : lista) {
            Element teacher = (Element)l;
            if (teacher.getAttributeValue("username").equals(userName)) {
                Element quest = new Element("question");
                quest.setAttribute("id", id);
                quest.setAttribute("name", name);
                quest.setAttribute("question", question);
                quest.setAttribute("answer", answer);
                quest.setAttribute("source", "media\\"+mediaFileName);
                quest.setAttribute("type", mediaContentType);
                teacher.addContent(quest);
=======
        //Se inicializa el parser que interpretará la estructura del JSON
        JSONParser parser = new JSONParser();
        try{ 
            //Write XML
            SAXBuilder builder = new SAXBuilder();
            File archivoXML = new File(pathString+"/xmls/Questions.xml");
            Document documento=builder.build(archivoXML);
            Element raiz = documento.getRootElement();
            List lista=raiz.getChildren("teacher");
            for (Object l : lista) {
                Element teacher = (Element)l;
                if (teacher.getAttributeValue("username").equals(userName)) {
                    Element quest = new Element("question");
                    quest.setAttribute("id", id);
                    quest.setAttribute("name", name);
                    quest.setAttribute("question", question);
                    quest.setAttribute("answer", answer);
                    quest.setAttribute("source", "media\\"+mediaFileName);
                    quest.setAttribute("type", mediaContentType);
                    teacher.addContent(quest);
                }
>>>>>>> e4f61793a248fc4200b069e4b98d234c068b0c11
            }
        }
        //AGREGA EL USUARIO AL ELEMENTO RAIZ
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
}
