/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

/**
 *
 * @author navi_
 */
class Question {
    
    public String id;
    public String name;
    public String question;
    public String answer;

    public Question() {
    }

    public Question(String id, String name, String question, String answer) {
        this.id = id;
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getId() {
        return id;
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
}
