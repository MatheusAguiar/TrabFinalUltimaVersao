/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    private void AbrirJanelaCadastroExemplar(ActionEvent event) throws IOException {
        
        Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/CadastrarExemplar.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Cadastro Exemplar"); 
        s2.setScene(scene);
        s2.show();
    }

    @FXML
    private void AbrirJanelaCadastroUsuario(ActionEvent event) throws IOException {
        
         Stage s3 = new Stage();
        s3.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/CadastrarUsuario.fxml"));
        Scene scene = new Scene(root);
        s3.resizableProperty().setValue(Boolean.FALSE);
        s3.getIcons().add(new Image("/imagens/icone.jpg"));
        s3.setTitle("Cadastro Usuário"); 
        s3.setScene(scene);
        s3.show();
        
    }

    @FXML
    private void AbrirJanelaCadastroLivro(ActionEvent event) throws IOException {
        
         Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/CadastroLivro.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Cadastro Livro"); 
        s2.setScene(scene);
        s2.show();
    }

    @FXML
    private void AbrirJanelaConsultaLivro(ActionEvent event) throws IOException {
        
         Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsultaLivro.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Consultar Livro"); 
        s2.setScene(scene);
        s2.show();
        
    }

    @FXML
    private void AbrirJanelaConsultaExemplar(ActionEvent event) throws IOException {
        
         Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsultaExemplares.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Consultar Exemplar"); 
        s2.setScene(scene);
        s2.show();
        
    }

    @FXML
    private void AbrirJanelaConsultaUsuario(ActionEvent event) throws IOException {
        
         Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsultaUsuario.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Consultar Usuário"); 
        s2.setScene(scene);
        s2.show();
    }

    @FXML
    private void AbrirJanelaConsultaFunc(ActionEvent event) throws IOException {
        
         Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsultaFuncionario.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Consultar Funcionário"); 
        s2.setScene(scene);
        s2.show();
        
    }

    @FXML
    private void AbrirJanelaEmprestimo(ActionEvent event) throws IOException {
        
         
        
    }

    @FXML
    private void AbrirJanelaEmprestimo1(ActionEvent event) throws IOException {
        
        Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/JanelaEmprestimo.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Empréstimos"); 
        s2.setScene(scene);
        s2.show();
    }

    @FXML
    private void AbrirJanelaConsultaEmprestimo(ActionEvent event) throws IOException {
        
        Stage s2 = new Stage();
        s2.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConsultaEmprestimo.fxml"));
        Scene scene = new Scene(root);
        s2.resizableProperty().setValue(Boolean.FALSE);
        s2.getIcons().add(new Image("/imagens/icone.jpg"));
        s2.setTitle("Consultar Empréstimos"); 
        s2.setScene(scene);
        s2.show();
    }
    
}
