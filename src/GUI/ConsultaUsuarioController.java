/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Servicos.UsuarioServico;
import dados.entidade.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class ConsultaUsuarioController implements Initializable {

    @FXML
    private TextField txtPesquisaNome;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Usuario> tabela;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn codContrato;
    @FXML
    private TableColumn nome;
    @FXML
    private TableColumn cpf;
    @FXML
    private TableColumn endereco;
    @FXML
    private TableColumn email;
    @FXML
    private TableColumn telefone;

    @FXML
    private TableColumn ativo;

    private ObservableList<Usuario> dados
            = FXCollections.observableArrayList();

    private Usuario selecionado;

    private UsuarioServico servico = new UsuarioServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabela();

        listarAtoresTabela();

    }

    private void configurarTabela() {

        id.setCellValueFactory(
                new PropertyValueFactory("id"));
        codContrato.setCellValueFactory(
                new PropertyValueFactory("codigoContrato"));
        nome.setCellValueFactory(
                new PropertyValueFactory("nome"));
        cpf.setCellValueFactory(
                new PropertyValueFactory("cpf"));
        endereco.setCellValueFactory(
                new PropertyValueFactory("endereco"));
        email.setCellValueFactory(
                new PropertyValueFactory("email"));
        telefone.setCellValueFactory(
                new PropertyValueFactory("telefone"));

        ativo.setCellValueFactory(
                new PropertyValueFactory("ativo"));

    }//configurarTabela    
    
     private void listarAtoresTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de atores
        List<Usuario> usuarios = servico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(usuarios);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }

}
