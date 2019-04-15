/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author navi_
 */
public class DeleteQuestionActionSupport extends ActionSupport { 
    public String dname;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
    
    @Override
    public String execute() throws Exception {
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println(dname);
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/DeleteQuestionActionSupport.class", "jsons/Questions.json/");
        pathString=pathString.replace("file:/","");
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(pathString));
            JSONArray questionArray = (JSONArray) obj;
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String nameJ = (String) questionJObject.get("name");
                if(nameJ.equals("nombre3")){
                    questionArray.remove(jsonObject);
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
    
}
