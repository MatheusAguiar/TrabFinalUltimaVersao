/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.UsuarioDAO;
import Servicos.UsuarioServico;
import UTIL.TextFieldFormatter;
import com.jfoenix.controls.JFXButton;
import dados.entidade.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class CadastrarUsuarioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtCodigo;
    @FXML
    private RadioButton rbSim;
    @FXML
    private RadioButton rbNao;
    @FXML
    private Button btnCadastrar;

    private UsuarioServico servico = new UsuarioServico();
    @FXML
    private ToggleGroup Ativo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void CadastrarUsuarios(ActionEvent event) {

    }

    @FXML
    private void Cadastrar(ActionEvent event) {

        Usuario user = new Usuario();
        user.setCodigoContrato(Integer.valueOf(txtCodigo.getText()));
        user.setNome(txtNome.getText());
        user.setCpf(txtCpf.getText());
        user.setEndereco(txtEndereco.getText());
        user.setEmail(txtEmail.getText());
        user.setTelefone(txtTelefone.getText());
        if (rbSim.isSelected()) {

            user.setAtivo(true);
        }

        if (rbNao.isSelected()) {

            user.setAtivo(false);
        }

        servico.salvar(user);

        mensagemSucesso("Usuário salvo com sucesso!");
        limparCampos();
    }

    public void limparCampos() {

        //Limpando o form
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtCodigo.setText("");

    }

    public void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void maskCpf(KeyEvent event) {
        
         TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    @FXML
    private void maskFone(KeyEvent event) {
        
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtTelefone);
        tff.formatter();
    }

}
