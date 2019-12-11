/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Servicos.ExemplarServico;
import Servicos.LivroServico;
import com.jfoenix.controls.JFXTextField;
import dados.entidade.Exemplar;
import dados.entidade.Livro;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class CadastrarExemplarController implements Initializable {

    @FXML
    private ComboBox<Livro> cboLivro;
    @FXML
    private JFXTextField txtEdicao;
    @FXML
    private JFXTextField txtTombo;
    @FXML
    private JFXTextField txtExemplares;
    @FXML
    private Button btnCadastrar;
    @FXML
    private RadioButton rbSim;
    @FXML
    private RadioButton rbNao;
    
     private LivroServico servico = new LivroServico();
     private ExemplarServico servico1 = new ExemplarServico();
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      preencherListaLivro();
    }    

    @FXML
    private void salvar(ActionEvent event) {
        
         Exemplar ex = new Exemplar();
            ex.setLivro((Livro) cboLivro.getValue());
            ex.setEdicao(Integer.valueOf(txtEdicao.getText()));
            ex.setTombo(Integer.valueOf(txtTombo.getText()));
            if (rbSim.isSelected())
            {

                ex.setDisponivel(true);
            }

            if (rbNao.isSelected())
            {

                ex.setDisponivel(false);
            }

            ex.setNumExemplar(Integer.valueOf(txtExemplares.getText()));

           servico1.salvar(ex);

            //Exibindo mensagem
            mensagemSucesso("Exemplar salvo com sucesso!");
            limparCampos();
        
        
    }
    
    private void preencherListaLivro() 
    {

       List<Livro> livros = servico.listar();

        cboLivro.setItems(FXCollections.observableArrayList(livros));
    
    }
    
    public void limparCampos(){

          //Limpando o form
         txtEdicao.setText("");
        txtTombo.setText("");
        txtExemplares.setText("");

}

    public void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

}
