/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nenab
 */
public class NewFXMain extends Application{
    
    @Override
    public void start(Stage primaryStage) throws IOException,ClassNotFoundException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene scene = new Scene(root,950,491);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //BazaPodataka baza = new BazaPodataka();
        //baza.stvoriBazu("baza");
        //baza.stvoriStol();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
