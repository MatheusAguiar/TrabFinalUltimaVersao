/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class MenuController implements Initializable {

    @FXML
    private Label data;
    @FXML
    private Label hora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDateTime dt = LocalDateTime.now();
        String dataAtual = dt.getDayOfMonth() + "/" + dt.getMonth() + "/" + dt.getYear();
        String horaAtual = dt.getHour() + ":" + dt.getMinute();
        data.setText(dataAtual);
        hora.setText(horaAtual);
    }    
    
}
