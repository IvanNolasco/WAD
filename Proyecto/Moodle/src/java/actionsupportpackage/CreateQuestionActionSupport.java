package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
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
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/CreateQuestionActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        //Se inicializa el parser que interpretará la estructura del JSON
        JSONParser parser = new JSONParser();
        try{
            //Se abre el JSON de las preguntas y se asigna a un JSON array, el cual tiene cada pregunta como elemento de un "arreglo"
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            //Se recorre cada elemento del arreglo
            for (Object q : questionArray){
                //Se obtiene la informacion de la pregunta en questionJObject
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                //Se obtiene el id de la pregunta
                String idE = (String) questionJObject.get("id");
                //Si el id coincide con el id obtenido en el jsp se recupera el resto de sus atributos y se rompe el ciclo
                if(idE.equals(this.id)){
                    System.out.println(idE);
                    System.out.println(this.id);
                    return "IDExistent";
                }
            }
            //se define la ruta para escribir el archivo multimedia 
            File salida = new File(pathString+"web/media/"+mediaFileName);
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

            obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            questionArray = (JSONArray) obj;
            
            //se contruye el JSON de la nueva pregunta
            JSONObject q = new JSONObject();
            q.put("id", id);
            q.put("name", name);
            q.put("question", question);
            q.put("answer", answer);
            q.put("source", "media\\"+mediaFileName);
            q.put("type", mediaContentType);
            JSONObject newQuestion = new JSONObject();
            newQuestion.put("Question", q);
            //se agrega el objeto JSON de la nueva pregunta a la lista de JSONs 
            questionArray.add(newQuestion);
            //se sobreescribe el archivo
            FileWriter file = new FileWriter(pathString+"web/jsons/Questions.json/");
            file.write(questionArray.toJSONString());
            file.flush();
            file.close();

            return SUCCESS;
        }
        catch(Exception e){
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
