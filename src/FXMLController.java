/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author nenab
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    TextAnimator textAnimator;
    TextAnimator textAnimator1;
    Cekanje cekanje;
    Thread thread;
    Thread thread1;


    @FXML
    private Button kreni;
    
    @FXML
    private Button dodajDimenziju;
    
    @FXML 
    private Button spremiUBazu;
    
    @FXML
    private Button pretraziBazu;
    
    @FXML 
    private TextField dimenzijaT;
    
    
    @FXML 
    private TextArea ispisZaA;
    
    @FXML
    private TextArea ispisZaB;
    
    @FXML 
    private TextArea ispisZaC;

    
    
   
    
    Complex[] ap,c;
    Complex[] bp;
    String umnozak,a,b;
    TextOutput textOutput, textOutput1;
    int dimenzija;
    int dimenzijaKonacna;
    int dimenzijaProsirena;
    @FXML
    void postaviDimenziju(ActionEvent event) throws InterruptedException {
        ap = null;
        bp = null;
        c = null;
        a = "";
        b = "";
        umnozak  = "";
        
        ispisZaC.setVisible(false);
        ispisZaA.setText("");
        ispisZaB.setText("");
        
        dimenzija = Integer.parseInt(dimenzijaT.getCharacters().toString())+1;
        System.out.println(dimenzija);
        dimenzijaProsirena = dimenzija;
        if((int)(Math.ceil((Math.log(dimenzija) / Math.log(2)))) != (int)(Math.floor(((Math.log(dimenzija) / Math.log(2))))))
        {
            while((int)(Math.ceil((Math.log(dimenzijaProsirena) / Math.log(2)))) != (int)(Math.floor(((Math.log(dimenzijaProsirena) / Math.log(2))))))
            {
                dimenzijaProsirena++;
            }
        }

        System.out.println(dimenzija+" "+dimenzijaProsirena);
        
        ap = new Polinomi().napuni(dimenzija, dimenzijaProsirena, dimenzijaKonacna);
        bp = new Polinomi().napuni(dimenzija, dimenzijaProsirena, dimenzijaKonacna);


        
        a = new Polinomi().ispisPolinoma(ap, dimenzija, dimenzijaKonacna);
        b = new Polinomi().ispisPolinoma(bp, dimenzija, dimenzijaKonacna);
        
        
        /*---------------racunanje ffta i mjerenje vremena--------------*/
        long start = System.currentTimeMillis();
        ap = racunajFFT.fft(ap);
        bp = racunajFFT.fft(bp);
        c = racunajFFT.mult(ap,bp);
        c = racunajFFT.invfft(c);
        
        long end = System.currentTimeMillis();
        System.out.println("vrijeme"+(end-start));
        
        
    }
    
    @FXML
    void pokreniRacunanje(ActionEvent event) throws InterruptedException {

        String s = "";
        for (int i=0;i<dimenzijaProsirena*2;i++)
        {
            if(c[i].real == 0 && c[i].imag == 0)
                continue;
            if(i == 0)
                s += c[i].toString() + " + ";
            else
            {
                if(!c[i].toString().equals(""))
                {
                    String konacni = c[i].toString() + "x^" + i +" + ";
                    s += konacni;
                }
               
            }
        }
        

        
        umnozak = "C(x) = ";
        for( int i = 0; i < s.length()-2; i++)
            umnozak += s.charAt(i);
        
        
        System.out.println(a);
        System.out.println(b);
        System.out.println(umnozak);

        
        
        textAnimator = new TextAnimator("A(x) = "+a,10,textOutput,ispisZaC);
        textAnimator1 = new TextAnimator("B(x) = "+b,10,textOutput1,ispisZaC);
        
        thread = new Thread(textAnimator);  
        thread.start();
        thread1 = new Thread(textAnimator1);
        thread1.start();
        
        Thread cekanje1 = new Thread(cekanje);
        cekanje1.start();

        cekanje = new Cekanje(ispisZaB, ispisZaC, "");
        ispisZaC.setText(umnozak);
        
       

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ispisZaC.setVisible(false);

        
        ispisZaA.setWrapText(true);
        ispisZaB.setWrapText(true);
        ispisZaC.setWrapText(true);
        textOutput = new TextOutput(){
            @Override
            public void writeText(String text) {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        ispisZaA.setText(text);
                    }
                    
                });
            }
            
        };
        textOutput1 = new TextOutput(){
            @Override
            public void writeText(String text) {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        ispisZaB.setText(text);                        
                    }
                    
                });
            }
            
        };
        

        
        
    }  
    
    
}

