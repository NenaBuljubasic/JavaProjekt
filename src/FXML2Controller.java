/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author gabrijela
 */
public class FXML2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private LineChart graf;
   
    @FXML
    private Button gumb;
    
    @FXML
    public void crtajPoGrafu(ActionEvent event)throws InterruptedException{
        BazaPodataka baza = new BazaPodataka();
        baza.stvoriBazu("baza4");
        baza.stvoriStol();
        List<UBazi> lista = baza.dohvatiIzBaze();
        lista.sort(new Komparator());
        
        Series standardni = new XYChart.Series<Number, Number>();
        standardni.setName("Standardno množenje");
        Series rekurzivni = new XYChart.Series<Number, Number>();
        rekurzivni.setName("Rekurzivni FFT");
        Series iterativni = new XYChart.Series<Number, Number>();
        iterativni.setName("Iterativni FFT");
        Series standardniJNI = new XYChart.Series<Number, Number>();
        standardniJNI.setName("Standardni (JNI)");
        
        for(UBazi el:lista){
            standardni.getData().add(new XYChart.Data(String.valueOf(el.dimenzija), el.standardni));
            rekurzivni.getData().add(new XYChart.Data(String.valueOf(el.dimenzija), el.rekFFT));
            iterativni.getData().add(new XYChart.Data(String.valueOf(el.dimenzija), el.iterFFT));
            standardniJNI.getData().add(new XYChart.Data(String.valueOf(el.dimenzija), el.standJNI));
        }
        graf.getData().add(standardni);
        graf.getData().add(rekurzivni);
        graf.getData().add(iterativni);
        graf.getData().add(standardniJNI);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
