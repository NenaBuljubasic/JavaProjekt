
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nenab
 */
public class Cekanje implements Runnable{
    
    @FXML
    TextArea tekst;
    @FXML 
    TextArea tekst1;
    String umnozak;
    public Cekanje(TextArea t, TextArea t1, String umn) {
        this.tekst = t;
        this.tekst1 = t1;
        this.umnozak = umn;
    }
    @Override
    public void run() {

    }
    
}
