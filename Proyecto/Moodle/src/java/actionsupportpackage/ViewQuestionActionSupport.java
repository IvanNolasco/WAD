package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ViewQuestionActionSupport extends ActionSupport {
    private String id;
    private String name;
    private String question;
    private String answer;
    private String source;
    private String type;
    
    public ViewQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ViewQuestionActionSupport.class", "jsons/Questions.json/");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String idJ = (String) questionJObject.get("id");
                if(idJ.equals(id)){
                    this.name = (String) questionJObject.get("name");
                    this.question = (String) questionJObject.get("question");
                    this.answer = (String) questionJObject.get("answer");
                    this.source = (String) questionJObject.get("source");
                    this.type = (String) questionJObject.get("type");
                    break;
                }
            }
            FileWriter file = new FileWriter(pathString);
            file.write(questionArray.toJSONString());
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    
    
    
}
