/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class ModifyQuestionActionSupport extends ActionSupport {
    
    public String id;
    public String name;
    public String question;
    public String answer;
    public File media;
    public String mediaContentType;
    public String mediaFileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }

    public String getMediaContentType() {
        return mediaContentType;
    }

    public void setMediaContentType(String mediaContentType) {
        this.mediaContentType = mediaContentType;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public void setMediaFileName(String mediaFileName) {
        this.mediaFileName = mediaFileName;
    }
    
    public ModifyQuestionActionSupport() {
    }
    
    @Override
    public String execute() throws Exception {
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyQuestionActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String idJ = (String) questionJObject.get("id");
                if(idJ.equals(id)){
                    this.name = (String) questionJObject.get("name");
                    this.question = (String) questionJObject.get("question");
                    this.answer = (String) questionJObject.get("answer");
                    this.mediaFileName = (String) questionJObject.get("source");
                    this.mediaContentType = (String) questionJObject.get("type");
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
        return SUCCESS;
    }
    
}
