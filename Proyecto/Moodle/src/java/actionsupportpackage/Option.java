/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actionsupportpackage;

/**
 *
 * @author luis_
 */
public class Option {
    private String text = "default";
    private int points = 0;

    public Option(String text, int points) {
        this.text = text;
        this.points = points;
    }

    public Option() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    public String toString(){
        return "text:" + this.text + " points: " + String.valueOf(this.points);
    }
}
