/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.LivroServico;
import dados.entidade.Livro;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class CadastroLivroController implements Initializable {

    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAtor;
    @FXML
    private TextField txtVolume;
    @FXML
    private TextField txtEditora;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btbVoltar;

    private LivroServico servico = new LivroServico();
    private TextField txtId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Cadastrar(ActionEvent event) {

        //Verificar se está atualizando ou inserindo
     
        
            Livro livro = new Livro();
            livro.setTitulo(txtTitulo.getText());
            livro.setAno(Integer.valueOf(txtAno.getText()));
            livro.setGenero(txtGenero.getText());
            livro.setAutor(txtAtor.getText());
            livro.setVolume(Integer.valueOf(txtVolume.getText()));
            livro.setEditora(txtEditora.getText());

            //Mandar o ator para a camada de servico
            servico.salvar(livro);

            //Exibindo mensagem
            mensagemSucesso("Livro salvo com sucesso!");
            limparCampos();
             

    }
    
    public void limparCampos(){

          //Limpando o form
        txtTitulo.setText("");
        txtAno.setText("");
        txtGenero.setText("");
        txtAtor.setText("");
        txtVolume.setText("");
        txtEditora.setText("");

}

    public void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

}
