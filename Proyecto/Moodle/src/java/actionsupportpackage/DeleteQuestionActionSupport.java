package actionsupportpackage;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DeleteQuestionActionSupport extends ActionSupport { 
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String execute() throws Exception {
        //SE DEFINE LA RUTA DONDE SE VAN A BUSCAR LOS JSON QUE CONTIENEN LA INFORMACION DE LAS PREGUNTAS
        URL path=this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String pathString = path.toString().replace("build/web/WEB-INF/classes/actionsupportpackage/DeleteQuestionActionSupport.class", "");
        pathString=pathString.replace("file:/","");
        //Se inicializa el parser que interpretará la estructura del JSON
        JSONParser parser = new JSONParser();
        try{
            //Se abre el JSON de las preguntas y se asigna a un JSON array, el cual tiene cada pregunta como elemento de un "arreglo"
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Questions.json/"));
            JSONArray questionArray = (JSONArray) obj;
            //Se recorre cada elemento del arreglo
            for (Object q : questionArray){
                JSONObject jsonObject = (JSONObject) q;
                JSONObject questionJObject = (JSONObject) jsonObject.get("Question");
                String nameJ = (String) questionJObject.get("id");
                String mediaFilePath = (String) questionJObject.get("source");
                //se compara el id de cada pregunta con el id de la pregunta que se desea eliminar 
                if(nameJ.equals(id)){
                    //al encontrarse la pregunta a eliminar se elimina el multimedia correspondiente 
                    File file = new File(pathString+"web/"+mediaFilePath);
                    System.out.println(mediaFilePath);
                    System.out.println(file.delete());
                    //se elimina la pregunta del array de JSON de preguntas
                    questionArray.remove(jsonObject);
                    break;
                }
            }
            //se sobreescribe el archivo de JSONs
            FileWriter file = new FileWriter(pathString+"web/jsons/Questions.json/");
            file.write(questionArray.toJSONString());
            file.flush();
            file.close(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        //se repite el procedimiento de eliminar pregunta, pero ahora eliminando el feedback correspondiente
        try{
            Object obj = parser.parse(new FileReader(pathString+"web/jsons/Feedbacks.json/"));
            JSONArray feedbackArray = (JSONArray) obj;
            for (Object fA : feedbackArray){
                JSONObject jsonObject = (JSONObject) fA;
                JSONObject feedbackJObject = (JSONObject) jsonObject.get("Feedback");
                String nameJ = (String) feedbackJObject.get("id");
                if(nameJ.equals(id)){
                    feedbackArray.remove(jsonObject);
                    break;
                }
            }
            FileWriter file = new FileWriter(pathString+"web/jsons/Feedbacks.json/");
            file.write(feedbackArray.toJSONString());
            file.flush();
            file.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
