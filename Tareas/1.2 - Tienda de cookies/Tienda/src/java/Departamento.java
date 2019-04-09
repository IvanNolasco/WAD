
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luis_
 */
public class Departamento {
    private int no;
    private ArrayList<Articulo> arts;

    public Departamento() {
        arts = new ArrayList();
    }

    public Departamento(int no, ArrayList<Articulo> arts) {
        this.no = no;
        this.arts = arts;
    }

    public ArrayList<Articulo> getArts() {
        return arts;
    }

    public int getNo() {
        return no;
    }

    public void setArts(ArrayList<Articulo> arts) {
        this.arts = arts;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
    
    
}
