package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ViewQuestionActionSupport extends ActionSupport {
    private String id;
    private String question;
    private String answer;
    private String source;
    private String type;
    
    private String tries;
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    private String triesFB;
    
    
    public ViewQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ViewQuestionActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        //Se inicializa el parser que interpretará la estructura del JSON
        JSONParser parser = new JSONParser();
        try{
            //Se abre el JSON de las preguntas y se asigna a un JSON array, el cual tiene cada pregunta como elemento de un "arreglo"
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            //Se realiza lo mismo para el JSON del feedback
            Object objF = parser.parse(new FileReader(pathString+"web/jsons/Feedbacks.json/"));
            JSONArray questionArray = (JSONArray) obj;
            JSONArray feedbackArray = (JSONArray) objF;            
            //Se recorre cada elemento del arreglo
            for (Object q : questionArray){
                //Se obtiene la informacion de la pregunta en questionJObject
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                //Se obtiene el id de la pregunta
                String idJ = (String) questionJObject.get("id");
                //Si el id coincide con el id obtenido en el jsp se recupera el resto de sus atributos y se rompe el ciclo
                if(idJ.equals(id)){
                    this.question = (String) questionJObject.get("question");
                    this.answer = (String) questionJObject.get("answer");
                    this.source = (String) questionJObject.get("source");
                    this.type = (String) questionJObject.get("type");
                    break;
                }
            }
            //Se recorre cada elemento del arreglo
            for (Object f : feedbackArray) {
                System.out.println("entre aqui");
                //Se obtiene la informacion del feedback de la pregunta en questionJObject
                JSONObject jsonObject = (JSONObject) f;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Feedback");
                //Se obtiene el id de la pregunta
                String idJ = (String) questionJObject.get("id");
                if(idJ.equals(id)){
                    this.tries = (String) questionJObject.get("tries");
                    this.evaluate = (String) questionJObject.get("evaluate");
                    this.initial = (String) questionJObject.get("initial");
                    this.correct = (String) questionJObject.get("correct");
                    this.incorrect = (String) questionJObject.get("incorrect");
                    this.triesFB = (String) questionJObject.get("triesFB");
                }
            }
        }
        
        catch(Exception e){
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTries() {
        return tries;
    }

    public String getInitial() {
        return initial;
    }

    public String getCorrect() {
        return correct;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public String getTriesFB() {
        return triesFB;
    }
}
