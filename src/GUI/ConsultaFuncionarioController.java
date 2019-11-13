/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.FuncionarioServico;
import dados.entidade.Funcionario;
import dados.entidade.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class ConsultaFuncionarioController implements Initializable {

    @FXML
    private TextField txtPesquisaFuncionario;
    @FXML
    private TableView<Funcionario> tabela;
    @FXML
    private TableColumn id;
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
    private TableColumn contrato;
    @FXML
    private TableColumn fimcontrato;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtContrato;
    @FXML
    private DatePicker txtFimContrato;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    
    
     private ObservableList<Funcionario> dados
            = FXCollections.observableArrayList();

    private Funcionario selecionado;

    private FuncionarioServico servico = new FuncionarioServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        configurarTabela();

        listarFuncionariosTabela();

        // TODO
    }

     private void configurarTabela() {

        id.setCellValueFactory(
                new PropertyValueFactory("id"));
        contrato.setCellValueFactory(
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

        fimcontrato.setCellValueFactory(
                new PropertyValueFactory("fimContrato"));

    }//configurarTabela    
     
     private void listarFuncionariosTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Funcionario> funcionarios = servico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(funcionarios);

        //Jogando os dados na tabela
        tabela.setItems(dados);


    }

    
}
