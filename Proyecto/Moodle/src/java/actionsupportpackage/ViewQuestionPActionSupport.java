package actionsupportpackage;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ViewQuestionPActionSupport extends ActionSupport {
    
    private String id;
    private String qtype;
    private String name;
    private String question;
    private List<Option> optionList;
    private String maxQuant;
    private String source;
    private String type;
    
    private String initial;
    private String evaluate;
    private String correct;
    private String incorrect;
    
    private String questionJSON;
    
    @Override
    public String execute() throws Exception {
        String userName = (String) ServletActionContext.getRequest().getSession().getAttribute("userName");
        String path = ServletActionContext.getServletContext().getRealPath("/");
        optionList = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path + "\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for (int i = 0; i < teachersList.size(); i++) {
                Element teacher = (Element) teachersList.get(i);
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)) {
                    List questionsList = teacher.getChildren("question");
                    for (int j = 0; j < questionsList.size(); j++) {
                        Element questionE = (Element) questionsList.get(j);
                        String questionid = questionE.getAttributeValue("id");
                        if (questionid.equals(this.id)) {

                            this.qtype = questionE.getAttributeValue("qtype");
                            this.name = questionE.getAttributeValue("name");
                            this.question = questionE.getAttributeValue("question");
                            this.maxQuant = questionE.getAttributeValue("max");
                            this.source = questionE.getAttributeValue("source");
                            this.type = questionE.getAttributeValue("type");
                            List options = questionE.getChildren("option");
                            JSONArray list = new JSONArray();
                            for (int k = 0; k < options.size(); k++) {
                                Element optionE = (Element) options.get(k);
                                JSONObject objOptions = new JSONObject();
                                String auxText = optionE.getAttributeValue("text");
                                int auxPoints = Integer.parseInt(optionE.getAttributeValue("points"));
                                Option o = new Option(auxText, auxPoints);
                                this.optionList.add(o);
                                objOptions.put("text",auxText);
                                objOptions.put("points",auxPoints);
                                list.add(objOptions);
                            }
                            obj.put("question", question);
                            obj.put("qtype", qtype);
                            obj.put("name", name);
                            obj.put("max", maxQuant);
                            obj.put("source", source);
                            obj.put("type", type);
                            obj.put("options", list);
                            break;
                        }
                    }
                }
            }
            
        } catch (JDOMException e) {
            e.printStackTrace();
        }

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path + "\\xmls\\Questions.xml");
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            List teachersList = root.getChildren("teacher");
            for (int i = 0; i < teachersList.size(); i++) {
                Element teacher = (Element) teachersList.get(i);
                String username = teacher.getAttributeValue("username");
                if (username.equals(userName)) {
                    List feedbacksList = teacher.getChildren("feedback");
                    for (int j = 0; j < feedbacksList.size(); j++) {
                        Element feedback = (Element) feedbacksList.get(j);
                        String feedbackid = feedback.getAttributeValue("id");
                        if (feedbackid.equals(this.id)) {
                            this.correct = feedback.getAttributeValue("correct");
                            this.incorrect = feedback.getAttributeValue("incorrect");
                            this.initial = feedback.getAttributeValue("initial");
                            this.evaluate = feedback.getAttributeValue("evaluate");
                            obj.put("initial", initial);
                            obj.put("evaluate", evaluate);
                            obj.put("correct", correct);
                            obj.put("incorrect", incorrect);
                            break;
                        }
                    }

                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        questionJSON = obj.toJSONString();
        return SUCCESS;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
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

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }

    public String getMaxQuant() {
        return maxQuant;
    }

    public void setMaxQuant(String maxQuant) {
        this.maxQuant = maxQuant;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
    
    public ViewQuestionPActionSupport() {
    }

    public String getQuestionJSON() {
        return questionJSON;
    }

    public void setQuestionJSON(String questionJSON) {
        this.questionJSON = questionJSON;
    }
    
}
