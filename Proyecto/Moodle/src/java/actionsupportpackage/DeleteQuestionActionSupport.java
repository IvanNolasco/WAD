/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DeleteQuestionActionSupport extends ActionSupport { 
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String execute() throws Exception {
        
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/DeleteQuestionActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String nameJ = (String) questionJObject.get("id");
                String mediaFilePath = (String) questionJObject.get("source");
                if(nameJ.equals(id)){
                    File file = new File(pathString+"web/"+mediaFilePath);
                    System.out.println(mediaFilePath);
                    System.out.println(file.delete());
                    questionArray.remove(jsonObject);
                    break;
                }
            }
            FileWriter file = new FileWriter(pathString+"web/jsons/Questions.json/");
            file.write(questionArray.toJSONString());
            file.flush();
            file.close();
            
        }
        
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            for (Object fA : feedbackArray){
                JSONObject jsonObject = (JSONObject) fA;
                JSONObject feedbackJObject = (JSONObject) jsonObject.get("Feedback");
                String nameJ = (String) feedbackJObject.get("id");
                if(nameJ.equals(id)){
                    feedbackArray.remove(jsonObject);
                    break;
                }
            }
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
    
}
