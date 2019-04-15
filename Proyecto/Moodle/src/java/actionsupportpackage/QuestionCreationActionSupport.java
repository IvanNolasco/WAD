/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QuestionCreationActionSupport extends ActionSupport {
    
    public QuestionCreationActionSupport() {
    }
    
    private List<String> questions; 
    
    public List<String> getQuestions() {  
        return questions;  
    } 
    
    public void setQuestions(List<String> questions) {  
        this.questions = questions;  
    } 
    
    public String execute() throws IOException { 
        questions = new ArrayList<String>();
        
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/QuestionCreationActionSupport.class", "jsons/Questions.json/");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject question = (JSONObject) jsonObject.get("Question");
                String name = (String) question.get("name");
                questions.add(name);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
