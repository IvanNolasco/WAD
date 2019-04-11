/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    
    public String execute() throws IOException { 
        FileWriter file = new FileWriter("C:\\Users\\MAYRA\\Documents\\prueba.txt");
        file.write("ggg");
        file.flush();
        file.close();
        questions = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader("C:\\Users\\MAYRA\\Documents\\Questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray questionArray = (JSONArray) jsonObject.get("Question");
            for (Object q : questionArray){
                jsonObject = (JSONObject) q;
                String nombre = (String) jsonObject.get("nombre");
                questions.add(nombre);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
