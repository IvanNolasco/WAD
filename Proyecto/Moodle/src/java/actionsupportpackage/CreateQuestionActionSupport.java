/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateQuestionActionSupport extends ActionSupport {
    
    public String ID;
    public String name;
    public String question;
    public String answer;
    
    public CreateQuestionActionSupport() {
    }
    
    public String execute() throws Exception {
        JSONObject obj = new JSONObject();
	obj.put("ID", ID);
	obj.put("name", name);
	obj.put("question", question);
        obj.put("answer", answer);
        
            try {
                FileWriter file = new FileWriter("C:\\Users\\navi_\\OneDrive\\Documentos\\Questions1.json");
		file.write(obj.toJSONString());
		file.flush();
		file.close();
                return SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "fail";
        
    }
    
}
