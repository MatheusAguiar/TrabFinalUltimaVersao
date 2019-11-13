/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ExemplarDAO;
import DAO.LivroDAO;
import dados.entidade.Livro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox<?> cboLivro;
    @FXML
    private TextField txtEdicao;
    @FXML
    private TextField txtTombo;
    @FXML
    private TextField txtExemplares;
    @FXML
    private Button btnCadastrar;
    @FXML
    private RadioButton rbSim;
    @FXML
    private RadioButton rbNao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      preencherListaLivro();
    }    

    @FXML
    private void salvar(ActionEvent event) {
    }
    
    private void preencherListaLivro() 
    {

        LivroDAO exemdao = new LivroDAO();

        for (Livro liv : exemdao.listar())
        {

            cboLivro.setItems((ObservableList)liv);

        }

    }
    
}
