/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("Questions.json"));
            System.out.println();
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questionArray = (JSONArray) jsonObject.get("Question");
            for (Object q : questionArray){
                jsonObject = (JSONObject) q;
                String nombre = (String)jsonObject.get("nombre");
                questions.add(nombre);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
