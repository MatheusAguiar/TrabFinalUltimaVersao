/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.EmprestimoServico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidade.Emprestimo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class ConsultaEmprestimoController implements Initializable {

    @FXML
    private TableView<Emprestimo> tabelaEmprestimo;
    @FXML
    private TableColumn funcionario;
    @FXML
    private TableColumn usuario;
    @FXML
    private TableColumn exemplar;
    @FXML
    private TableColumn dataEmprestimo;
    @FXML
    private TableColumn dataDevolucao;
    @FXML
    private TableColumn observacao;
    @FXML
    private JFXRadioButton rbFuncionario;
    @FXML
    private ToggleGroup Selecione;
    @FXML
    private JFXRadioButton rbExemplar;
    @FXML
    private JFXRadioButton rbUsuario;
    @FXML
    private JFXTextField txtPesquisa;
    @FXML
    private JFXButton btnPesquisar;

    private ObservableList<Emprestimo> dados
            = FXCollections.observableArrayList();

    private EmprestimoServico servico = new EmprestimoServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       configurarTabela();
       listarEmprestimos();
    }

    @FXML
    private void pesquisar(ActionEvent event) {

        if (!(rbFuncionario.isSelected() || rbUsuario.isSelected() || rbExemplar.isSelected())) {
            mensagemErro("Selecione um campo de pesquisa.");
        } else if (rbFuncionario.isSelected()) {
            // Quando seleciona PESQUISA Funcionário

            pesquisaNomeFunc();

        } else if (rbUsuario.isSelected()) {
            // Quando seleciona PESQUISA Usuários

            pesquisaNomeUser();

        } else if (rbExemplar.isSelected()) {
            // Quando seleciona PESQUISA Exemplares

            pesquisaExemplar();

        }

    }

    private void pesquisaNomeFunc() {

        dados.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Emprestimo> emprestimos = servico.pesquisaNomeFunc(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(emprestimos);

        //Jogando os dados na tabela
        tabelaEmprestimo.setItems(dados);

    }
    
     private void pesquisaNomeUser() {

        dados.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Emprestimo> emprestimos = servico.pesquisaNomeUser(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(emprestimos);

        //Jogando os dados na tabela
        tabelaEmprestimo.setItems(dados);

    }
     
      private void pesquisaExemplar() {

        dados.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Emprestimo> emprestimos = servico.pesquisaExemplar(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(emprestimos);

        //Jogando os dados na tabela
        tabelaEmprestimo.setItems(dados);

    }
      
      private void configurarTabela(){
      
       funcionario.setCellValueFactory(
                new PropertyValueFactory("funcionario"));

        usuario.setCellValueFactory(
                new PropertyValueFactory("usuario"));

        exemplar.setCellValueFactory(
                new PropertyValueFactory("exemplar"));

        dataEmprestimo.setCellValueFactory(
                new PropertyValueFactory("dataretirada"));

        dataDevolucao.setCellValueFactory(
                new PropertyValueFactory("dataDevolucao"));

        observacao.setCellValueFactory(
                new PropertyValueFactory("observacao"));

    }//configurarTabela  
      
      
      private void listarEmprestimos() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Emprestimo> emprestimos = servico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(emprestimos);

        //Jogando os dados na tabela
        tabelaEmprestimo.setItems(dados);

    }
      
      
      
      
       public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

}
