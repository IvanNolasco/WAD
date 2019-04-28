/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyFeedbackActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString+"jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            
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
            
            for (Object fA : feedbackArray){
                JSONObject jsonObject = (JSONObject) fA;
                JSONObject feedbackJObject = (JSONObject) jsonObject.get("Feedback");
                String nameJ = (String) feedbackJObject.get("id");
                if(nameJ.equals(id)){
                    feedbackArray.remove(jsonObject);
                    break;
                }
            }
            
            feedbackArray.add(newFeedback);
            FileWriter file = new FileWriter(pathString+"jsons/Feedbacks.json/");
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
