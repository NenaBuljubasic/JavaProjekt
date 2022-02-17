
import java.util.Random;
import javafx.scene.control.Label;
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
public class TextAnimator implements Runnable {
    
    private String text;
    private int animationTime;
    private TextOutput textOutput;
    private Random random = new Random();
    public boolean jeli = false;
    public TextArea c;

    public TextAnimator(String text, int animationTime, TextOutput textField, TextArea c) {
        this.text = text;
        this.animationTime = animationTime;
        this.textOutput = textField;
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
        for (int i = 0; i <= text.length(); i++) {
            String textAtThisPoint = text.substring(0,i);
                
                textOutput.writeText(textAtThisPoint);
                Thread.sleep(animationTime + random.nextInt(150));
            }
        //this.jeli = true;
        
        c.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
