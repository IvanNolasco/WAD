package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
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
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyQuestion2ActionSupport.class", "");
        pathString=pathString.replace("file:/","");
  
        int sobre=0; //Variable para sobreescribir el archivo si es necesario
        
        //Si el usuario mete un archivo nuevo se crea el file y se guarda en el servidor
        if(media!=null)
        {
            
            File salida = new File(pathString+"web/media/"+mediaFileName);
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
        
        JSONParser parser = new JSONParser();
        try{
            //se recuperan las preguntas del archivo JSON y se guardan en un arreglo
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            //se contruye un nuevo objeto json con la informacion de la pregunta
            JSONObject q = new JSONObject();
            q.put("id", id);
            q.put("name", name);
            q.put("question", question);
            q.put("answer", answer);
            //Si no se seleccionó ningun archivo, se vuelve a escribir la ruta del archivo original en el json
            if(sobre==0)
                q.put("source", mediaFileName);
            else
            //Si se seleccionó algún archivo, este se guardará en la carpeta media    
                q.put("source", "media\\"+mediaFileName);
            q.put("type", mediaContentType);
            JSONObject newQuestion = new JSONObject();
            newQuestion.put("Question", q);
            //se recorre el arreglo de jsons
            for (Object qA : questionArray){
                JSONObject jsonObject = (JSONObject) qA;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String nameJ = (String) questionJObject.get("id");
                String mediaFilePath = (String) questionJObject.get("source");
                //se busca la pregunta con el mismo id 
                if(nameJ.equals(id)){
                    if (sobre == 1) {
                        //al encontrarse la pregunta se elimina el multimedia antiguo asociado a esa pregunta
                        File file = new File(pathString+"web/"+mediaFilePath);
                        System.out.println(file.delete());
                    }
                    //se elimina la pregunta del arreglo
                    questionArray.remove(jsonObject);
                    break;
                }
            }
            //se agrega al arreglo la pregunta que s emodifico
            questionArray.add(newQuestion);
            //se sobreescribe el archivo JSON con el arreglo
            FileWriter file = new FileWriter(pathString+"web/jsons/Questions.json/");
            file.write(questionArray.toJSONString());
            file.flush();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LOS FEEDBACK
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            for (Object f : feedbackArray){
                JSONObject jsonObject = (JSONObject) f;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Feedback");
                String idJ = (String) questionJObject.get("id");
                //se busca el feedback correspondiente al id
                if(idJ.equals(id)){
                    //se recupera cada dato del feedback
                    this.id = (String) questionJObject.get("id");
                    this.tries = (String) questionJObject.get("tries");
                    this.initial = (String) questionJObject.get("initial");
                    this.evaluate = (String) questionJObject.get("evaluate");
                    this.correct = (String) questionJObject.get("correct");
                    this.incorrect = (String) questionJObject.get("incorrect");
                    this.triesFB = (String) questionJObject.get("triesFB");
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("cache");
            
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
