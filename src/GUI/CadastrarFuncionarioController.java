/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.FuncionarioServico;
import UTIL.TextFieldFormatter;
import dados.entidade.Criptografar;
import dados.entidade.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private Button btCadastro;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtFone;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtContrato;
    @FXML
    private DatePicker txtData;

    private FuncionarioServico servico = new FuncionarioServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Cadastrar(ActionEvent event) {

        if (verificaDados()) {

            Funcionario func = new Funcionario();
            func.setNome(txtNome.getText());
            func.setCpf(txtCpf.getText());
            func.setEndereco(txtEndereco.getText());
            func.setEmail(txtEmail.getText());
            func.setTelefone(txtFone.getText());
            func.setSenhaacesso(Criptografar.encriptografar(txtSenha.getText()));
            func.setCodigoContrato(Integer.valueOf(txtContrato.getText()));
            func.setFimContrato(String.valueOf(txtData.getValue()));

            //Mandar o funcionário para a camada de servico
            servico.salvar(func);

            //Exibindo mensagem
            mensagemSucesso("Funcionário salvo com sucesso!");
            limparCampos();

        }

    }

    public void limparCampos() {

        //Limpando o form
        txtNome.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtFone.setText("");
        txtSenha.setText("");
        txtContrato.setText("");
        txtData.setStyle("");

    }

    public void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    private boolean verificaDados() {
        if ((!txtNome.getText().equals("")) && ((!txtCpf.getText().equals(""))
                && (!txtEndereco.getText().equals("")) && ((!txtEmail.getText().equals(""))
                && ((!txtFone.getText().equals("")) && (!txtSenha.getText().equals("")) && (!txtData.getValue().equals("")) && (!txtContrato.getText().equals("")))))) {

            return true;
        }
        mensagemErro("Dados incompletos.");
        return false;
    }

    public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void maskTelefone(KeyEvent event) {
        
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtFone);
        tff.formatter();
        
    }

    @FXML
    private void maskCpf(KeyEvent event) {
        
         TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCpf);
        tff.formatter();
        
    }
    
    
   

}
