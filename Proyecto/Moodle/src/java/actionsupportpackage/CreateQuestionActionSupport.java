/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateQuestionActionSupport extends ActionSupport {
    
    public String ID;
    public String name;
    public String question;
    public String answer;
    
    public CreateQuestionActionSupport() {
    }
    
    public String execute() throws Exception {
        List<String> questions; 
        questions = new ArrayList<String>();
        
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/CreateQuestionActionSupport.class", "jsons/Questions.json/");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        
        try{
            Object obj = parser.parse(new FileReader(pathString));
            JSONArray questionArray = (JSONArray) obj;
            
            JSONObject q = new JSONObject();
            q.put("id", ID);
            q.put("name", name);
            q.put("question", question);
            q.put("answer", answer);
            JSONObject newQuestion = new JSONObject();
            newQuestion.put("Question", q);
            
            questionArray.add(newQuestion);
            FileWriter file = new FileWriter(pathString);
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
    
}
