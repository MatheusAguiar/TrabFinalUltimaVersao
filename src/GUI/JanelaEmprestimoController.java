/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.ExemplarServico;
import Servicos.FuncionarioServico;
import Servicos.UsuarioServico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidade.Exemplar;
import dados.entidade.Funcionario;
import dados.entidade.Usuario;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class JanelaEmprestimoController implements Initializable {

    @FXML
    private TableView<Funcionario> tabelaFunc;
    @FXML
    private TableColumn idfunc;
    @FXML
    private TableColumn nomefunc;
    @FXML
    private TableView<Exemplar> tabelaExemplar;
    @FXML
    private TableColumn idexemplar;
    @FXML
    private TableColumn titulo;
    @FXML
    private TableColumn disponivel;
    @FXML
    private TableColumn numexemplar;
    @FXML
    private TableView<Usuario> tabelaUser;
    @FXML
    private TableColumn iduser;
    @FXML
    private TableColumn nomeuser;
    @FXML
    private TableColumn ativo;
    @FXML
    private ToggleGroup Selecione;
    @FXML
    private JFXRadioButton rbExemplar;
    @FXML
    private JFXRadioButton rbUsuario;

    @FXML
    private JFXRadioButton rbFuncionario;
    @FXML
    private JFXTextField txtPesquisa;
    @FXML
    private JFXButton btnPesquisar;

    private ObservableList<Funcionario> dadosfunc
            = FXCollections.observableArrayList();

    private Funcionario funcselecionado;

    private FuncionarioServico servicofunc = new FuncionarioServico();

    private ObservableList<Usuario> dadosuser
            = FXCollections.observableArrayList();

    private Usuario userselecionado;

    private UsuarioServico servicouser = new UsuarioServico();

    private ObservableList<Exemplar> dadosexemplar
            = FXCollections.observableArrayList();

    private Exemplar exemplarselecionado;

    private ExemplarServico servicoexemplar = new ExemplarServico();
    @FXML
    private JFXTextField txtDataEmprestimo;
    @FXML
    private JFXTextField txtDataDevolucao;
    @FXML
    private JFXTextField txtObs;
    @FXML
    private JFXTextField txtFunc;
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXTextField txtExemplar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabela();
        HORA_DATA();
        listarFuncionariosTabela();
        listarUsuariosTabela();
        listarExemplaresTabela();
        // TODO
    }

    private void listaFuncionario() {

        dadosfunc.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Funcionario> funcionarios = servicofunc.buscarPorNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosfunc = FXCollections.observableArrayList(funcionarios);

        //Jogando os dados na tabela
        tabelaFunc.setItems(dadosfunc);

    }

    private void listaUsuario() {

        dadosuser.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Usuario> usuarios = servicouser.buscarPorNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosuser = FXCollections.observableArrayList(usuarios);

        //Jogando os dados na tabela
        tabelaUser.setItems(dadosuser);

    }

    private void listaExemplares() {

        dadosexemplar.clear();

        String nome = txtPesquisa.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Exemplar> exemplares = servicoexemplar.buscarPorTitulo(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosexemplar = FXCollections.observableArrayList(exemplares);

        //Jogando os dados na tabela
        tabelaExemplar.setItems(dadosexemplar);

    }

    @FXML
    private void pesquisar(ActionEvent event) {

        if (!(rbFuncionario.isSelected() || rbUsuario.isSelected() || rbExemplar.isSelected())) {
            mensagemErro("Selecione um campo de pesquisa.");
        } else if (rbFuncionario.isSelected()) {
            // Quando seleciona PESQUISA Funcionário

            listaFuncionario();

        } else if (rbUsuario.isSelected()) {
            // Quando seleciona PESQUISA Usuários

            listaUsuario();

        } else if (rbExemplar.isSelected()) {
            // Quando seleciona PESQUISA Exemplares

            listaExemplares();

        }
    }

    private void configurarTabela() {

        idfunc.setCellValueFactory(
                new PropertyValueFactory("id"));

        nomefunc.setCellValueFactory(
                new PropertyValueFactory("nome"));

        iduser.setCellValueFactory(
                new PropertyValueFactory("id"));

        nomeuser.setCellValueFactory(
                new PropertyValueFactory("nome"));

        ativo.setCellValueFactory(
                new PropertyValueFactory("ativo"));

        idexemplar.setCellValueFactory(
                new PropertyValueFactory("id"));

        titulo.setCellValueFactory(
                new PropertyValueFactory("livro"));

        disponivel.setCellValueFactory(
                new PropertyValueFactory("disponivel"));

        numexemplar.setCellValueFactory(
                new PropertyValueFactory("numExemplar"));

    }//configurarTabela  

    private void listarFuncionariosTabela() {
        //Limpando quaisquer dados anteriores
        dadosfunc.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Funcionario> funcionarios = servicofunc.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosfunc = FXCollections.observableArrayList(funcionarios);

        //Jogando os dados na tabela
        tabelaFunc.setItems(dadosfunc);

    }

    private void listarUsuariosTabela() {
        //Limpando quaisquer dados anteriores
        dadosuser.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Usuario> usuarios = servicouser.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosuser = FXCollections.observableArrayList(usuarios);

        //Jogando os dados na tabela
        tabelaUser.setItems(dadosuser);

    }

    private void listarExemplaresTabela() {
        //Limpando quaisquer dados anteriores
        dadosexemplar.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Exemplar> exemplares = servicoexemplar.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosexemplar = FXCollections.observableArrayList(exemplares);

        //Jogando os dados na tabela
        tabelaExemplar.setItems(dadosexemplar);

    }

    public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    public void HORA_DATA() {
        txtDataEmprestimo.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));

        Date a = new Date(System.currentTimeMillis());
        a.setDate(a.getDate() + 7);

        String formato = "dd/MM/yyyy";
        SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);
        txtDataDevolucao.setText(dataFormatada.format(a));

    }

}
