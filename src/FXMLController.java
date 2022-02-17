/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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


   
    public Polinomi pol;
    @FXML
    private Label standL;
    
    @FXML
    private Label rekL;
    
    @FXML 
    private Label iterL;
    
    @FXML
    private BarChart grafic;
    @FXML 
    private TextField dimenzijaT;
    
    @FXML 
    private TextArea ispisZaA;
    
    @FXML
    private TextArea ispisZaB;
    
    @FXML 
    private TextArea ispisZaC;
    
    
    Complex[] c;
    String umnozak,a,b;
    long m1, m2, m3;
    BazaPodataka baza;
    UBazi element;
    TextOutput textOutput, textOutput1;
    int dimenzija;
   
    @FXML
    void postaviDimenziju(ActionEvent event) throws InterruptedException {
        c = null;
        a = "";
        b = "";
        umnozak  = "";
        
        standL.setText("");
        rekL.setText("");
        iterL.setText("");
        ispisZaC.setVisible(false);
        ispisZaA.setText("");
        ispisZaB.setText("");
        
        dimenzija = Integer.parseInt(dimenzijaT.getCharacters().toString())+1;
        System.out.println(dimenzija);
        
        Polinomi p = new Polinomi(dimenzija);
        pol = p;
        a = p.ispisPolinoma(p.prvi_polinom);
        b = p.ispisPolinoma(p.drugi_polinom);
        
        
        /*---------------racunanje ffta i mjerenje vremena--------------*/
        
        Dretva prva_dretva = new Dretva(p, 1);
        Thread a1 = new Thread(prva_dretva);
        
        Dretva druga_dretva = new Dretva(p, 2);
        Thread a2 = new Thread(druga_dretva);
        
        Dretva treca_dretva = new Dretva(p, 3);
        Thread a3 = new Thread(treca_dretva);
        
        a2.start();
        a3.start();
        a1.start();
        a1.join();
        a2.join();
        a3.join();
        
        c = prva_dretva.vratiRjesenje();
        long vrijeme = prva_dretva.vratiVrijeme();
       System.out.println("Vrijeme prvog: " + vrijeme);
       m1 = vrijeme;
       standL.setText(m1 + " ms");
       long vrijeme2 = druga_dretva.vratiVrijeme();
       System.out.println("Vrijeme drugog: " + vrijeme2);
       m2 = vrijeme2;
       rekL.setText(m2 + " ms");
       long vrijeme3 = treca_dretva.vratiVrijeme();
       System.out.println("Vrijeme drugog: " + vrijeme3);
       m3 = vrijeme3;
       iterL.setText(m3 + " ms");
       stvoriGraf();
       element = new UBazi(Polinomi.id, p.dimenzija, (int)m1, (int)m2, (int)m3);
       System.out.println(element);
       baza.dodaj(element);
    }
    void stvoriGraf(){
        final  String stand = "Standardno množenje";
        final  String rek = "Rekurzivni FFT";
        final String iter = "Iterativni FFT";        
        XYChart.Series<String, Number> serije = new XYChart.Series<>();
        serije.setName(String.valueOf(dimenzija));
        serije.getData().add(new XYChart.Data<>(stand, m1));
        serije.getData().add(new XYChart.Data<>(rek, m2));
        serije.getData().add(new XYChart.Data<>(iter, m3));
        
        grafic.getData().addAll(serije);
    }
    @FXML
    void stvoriGrafIzBaze(ActionEvent event) throws InterruptedException, IOException{
         Stage secondStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene scene = new Scene(root,657,477);
        secondStage.setScene(scene);
        secondStage.show();
    }
    @FXML
    void pokreniRacunanje(ActionEvent event) throws InterruptedException {

        String s = "";
        for (int i=0;i<c.length;i++)
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

        
        
        textAnimator = new TextAnimator("A(x) = "+a,5,textOutput,ispisZaC);
        textAnimator1 = new TextAnimator("B(x) = "+b,5,textOutput1,ispisZaC);
        
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
        baza = new BazaPodataka();
        baza.stvoriBazu("baza2");
        baza.stvoriStol();
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

