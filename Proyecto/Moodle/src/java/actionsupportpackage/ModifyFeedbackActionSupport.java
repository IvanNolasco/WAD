/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author MAYRA
 */
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
       URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyFeedbackActionSupport.class", "jsons/Feedbacks.json/");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString));
            JSONArray feedbackArray = (JSONArray) obj;
            for (Object f : feedbackArray){
                JSONObject jsonObject = (JSONObject) f;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Feedback");
                String idJ = (String) questionJObject.get("id");
                if(idJ.equals(id)){
                    this.tries = (String) questionJObject.get("tries");
                    this.initial = (String) questionJObject.get("initial");
                    this.evaluate = (String) questionJObject.get("evaluate");
                    this.correct = (String) questionJObject.get("correct");
                    this.incorrect = (String) questionJObject.get("incorrect");
                    this.triesFB = (String) questionJObject.get("triesFB");
                    break;
                }
            }
            FileWriter file = new FileWriter(pathString);
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
