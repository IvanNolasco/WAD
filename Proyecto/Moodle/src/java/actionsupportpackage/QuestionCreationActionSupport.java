package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class QuestionCreationActionSupport extends ActionSupport {
    
    public QuestionCreationActionSupport() {
    }
    
    private List<Question> questions; 
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Question> getQuestions() {  
        return questions;  
    } 
    
    public void setQuestions(List<Question> questions) {  
        this.questions = questions;  
    } 
    
    @Override
    public String execute() throws IOException { 
        questions = new ArrayList<Question>();
        
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/QuestionCreationActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String id = (String) questionJObject.get("id");
                String name = (String) questionJObject.get("name");
                String question = (String) questionJObject.get("question");
                String answer = (String) questionJObject.get("answer");
                Question questionObject = new Question(id, name, question, answer);
                questions.add(questionObject);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
