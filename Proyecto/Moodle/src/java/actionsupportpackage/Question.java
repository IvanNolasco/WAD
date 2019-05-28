package actionsupportpackage;

class Question {
    
    public String id;
    public String qtype;
    public String name;
    public String question;
    public String answer;
    public String check;

    public Question() {
    }

    public Question(String id, String qtype, String name, String question, String answer) {
        this.id = id;
        this.qtype = qtype;
        this.name = name;
        this.question = question;
        this.answer = answer;
        this.check = "false";
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setQtype(String qtype){
        this.qtype = qtype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public void setCheck(String check){
        this.check = check;
    }
    
    public String getId() {
        return id;
    }
    
    public String getQtype(){
        return qtype;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
    
    public String getCheck(){
        return check;
    }
}
