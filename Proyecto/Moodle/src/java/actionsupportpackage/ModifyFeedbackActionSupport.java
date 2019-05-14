package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ModifyFeedbackActionSupport extends ActionSupport {
    
    public String id;
    public String tries;
    public String initial;
    public String evaluate;
    public String correct;
    public String incorrect;
    public String triesFB;
    
    public ModifyFeedbackActionSupport() {
    }
    
    public String execute() throws Exception {
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LOS FEEDBACK
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyFeedbackActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        
        JSONParser parser = new JSONParser();
        try{
            //se abre el archivo de los feedbacks
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            
            //se contruye el objeto json del nuevo feedback
            JSONObject f = new JSONObject();
            f.put("id", id);
            f.put("tries", tries);
            f.put("initial", initial);
            f.put("evaluate", evaluate);
            f.put("correct", correct);
            f.put("incorrect", incorrect);
            f.put("triesFB", triesFB);
            JSONObject newFeedback = new JSONObject();
            newFeedback.put("Feedback", f);
            //se recorre el arreglo de feedback
            for (Object fA : feedbackArray){
                JSONObject jsonObject = (JSONObject) fA;
                JSONObject feedbackJObject = (JSONObject) jsonObject.get("Feedback");
                String nameJ = (String) feedbackJObject.get("id");
                //se busca el feedback correspondiente al id
                if(nameJ.equals(id)){
                    //al encontrarse se remueve del arreglo de feedbacks
                    feedbackArray.remove(jsonObject);
                    break;
                }
            }
            //se agrega al arreglo el nuevo feedback
            feedbackArray.add(newFeedback);
            //se sobreescribe el archivo json de feedbacks
            FileWriter file = new FileWriter(pathString+"web/jsons/Feedbacks.json/");
            file.write(feedbackArray.toJSONString());
            file.flush();
            file.close();
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
