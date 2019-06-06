package actionsupportpackage;

public class Option {
    private String text;
    private int points;

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
