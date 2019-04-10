/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MAYRA
 */
public class QuestionCreationActionSupport extends ActionSupport {
    
    public QuestionCreationActionSupport() {
    }
    
    private List<String> questions; 
    
    public List<String> getQuestions() {  
        return questions;  
    }  
  
    public String execute() {  
        questions = new ArrayList<String>();  
        questions.add("0");  
        questions.add("1");  
        questions.add("2");  
        questions.add("3");  
        questions.add("4");  
        questions.add("5");  
        return SUCCESS;  
    } 
    
}
