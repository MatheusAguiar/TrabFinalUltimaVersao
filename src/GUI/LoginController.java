/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.FuncionarioDAO;
import dados.entidade.Criptografar;
import dados.entidade.Funcionario;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnAcessar;
    @FXML
    private Button btnCadastrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickAcessar(ActionEvent event) throws IOException, SQLException {

        FuncionarioDAO func = new FuncionarioDAO();

        if (func.checkLogin(txtUsuario.getText(), Criptografar.encriptografar(txtSenha.getText()))) {
            
            Stage s1 = new Stage();
            s1.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Scene scene = new Scene(root);
            s1.resizableProperty().setValue(Boolean.FALSE);
            s1.getIcons().add(new Image("/imagens/icone.jpg"));
            s1.setTitle("Menu");
            s1.setScene(scene);
            s1.show();
        } else {
            
            JOptionPane.showMessageDialog(null, "Senha Incorreta");
        }
    }

    @FXML
    private void CadastrarFunc(ActionEvent event) throws IOException {
        
         Stage s1 = new Stage();
            s1.initModality(Modality.APPLICATION_MODAL);
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/CadastrarFuncionario.fxml"));
            Scene scene = new Scene(root);
            s1.resizableProperty().setValue(Boolean.FALSE);
            s1.getIcons().add(new Image("/imagens/icone.jpg"));
            s1.setTitle("Cadastro - Funcionário");
            s1.setScene(scene);
            s1.show();
    }
}

