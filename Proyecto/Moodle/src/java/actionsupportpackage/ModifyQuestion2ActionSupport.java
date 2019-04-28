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

public class ModifyQuestion2ActionSupport extends ActionSupport {
    
    public String id;
    public String name;
    public String question;
    public String answer;
    private File media;
    private String mediaContentType;
    private String mediaFileName;
    
    public String tries;
    public String initial;
    public String evaluate;
    public String correct;
    public String incorrect;
    public String triesFB;
    
    public ModifyQuestion2ActionSupport() {
    }
    
    public String execute() throws Exception {
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/ModifyQuestion2ActionSupport.class", "");
        pathString=pathString.replace("file:/","");
  
        File salida = new File(pathString+"web/media/"+mediaFileName);
        FileInputStream in = new FileInputStream(media);
        FileOutputStream out = new FileOutputStream(salida);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString+"jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            
            JSONObject q = new JSONObject();
            q.put("id", id);
            q.put("name", name);
            q.put("question", question);
            q.put("answer", answer);
            q.put("source", "media\\"+mediaFileName);
            q.put("type", mediaContentType);
            JSONObject newQuestion = new JSONObject();
            newQuestion.put("Question", q);
            
            for (Object qA : questionArray){
                JSONObject jsonObject = (JSONObject) qA;
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
            
            questionArray.add(newQuestion);
            FileWriter file = new FileWriter(pathString+"jsons/Questions.json/");
            file.write(questionArray.toJSONString());
            file.flush();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            Object obj = parser.parse(new FileReader(pathString+"jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            for (Object f : feedbackArray){
                JSONObject jsonObject = (JSONObject) f;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Feedback");
                String idJ = (String) questionJObject.get("id");
                if(idJ.equals(id)){
                    this.id = (String) questionJObject.get("id");
                    this.tries = (String) questionJObject.get("tries");
                    this.initial = (String) questionJObject.get("initial");
                    this.evaluate = (String) questionJObject.get("evaluate");
                    this.correct = (String) questionJObject.get("correct");
                    this.incorrect = (String) questionJObject.get("incorrect");
                    this.triesFB = (String) questionJObject.get("triesFB");
                    break;
                }
            }
            FileWriter file = new FileWriter(pathString+"jsons/Feedbacks.json/");
            file.write(feedbackArray.toJSONString());
            file.flush();
            file.close(); 
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("cache");
            
        }
        return SUCCESS; 
    }
    
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
