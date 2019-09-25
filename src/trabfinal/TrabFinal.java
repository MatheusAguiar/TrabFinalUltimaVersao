/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabfinal;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author mats-
 */
public class TrabFinal extends Application {
    
    @Override
   public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        
        Scene scene = new Scene(root);

         stage.resizableProperty().setValue(Boolean.FALSE);

        stage.setTitle("Acesse sua Conta!"); 
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
