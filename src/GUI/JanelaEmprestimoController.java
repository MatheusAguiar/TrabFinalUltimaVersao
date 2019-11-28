/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.EmprestimoServico;
import Servicos.ExemplarServico;
import Servicos.FuncionarioServico;
import Servicos.UsuarioServico;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidade.Emprestimo;
import dados.entidade.Exemplar;
import dados.entidade.Funcionario;
import dados.entidade.Livro;
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
import javafx.scene.input.MouseEvent;

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
    private EmprestimoServico servicoemprestimo = new EmprestimoServico();

    private ObservableList<Emprestimo> dadosemprestimo
            = FXCollections.observableArrayList();

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
    private JFXButton btnEmprestar;
    @FXML
    private JFXButton btnDevolver;

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
        listarEmprestimos();
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

    public void HORA_DATA() {
        txtDataEmprestimo.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));

        Date a = new Date(System.currentTimeMillis());
        a.setDate(a.getDate() + 7);

        String formato = "dd/MM/yyyy";
        SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);
        txtDataDevolucao.setText(dataFormatada.format(a));

    }

    @FXML
    private void pegarFuncionario(MouseEvent event) {

        //Pegar o usuario que foi selecionado na tabela
        funcselecionado = tabelaFunc.getSelectionModel()
                .getSelectedItem();

        //Se tem algum usuario selecionado
        if (funcselecionado != null) { //tem usuario selecionado
            //Pegar os dados do usuario e jogar nos campos do
            //formulario
            txtFunc.setText(
                    String.valueOf(funcselecionado.getId()));

        } else { //não tem usuario selecionado na tabela
            mensagemErro("Selecione um funcionário.");
        }
    }

    @FXML
    private void pegarExemplar(MouseEvent event) {

        //Pegar o usuario que foi selecionado na tabela
        exemplarselecionado = tabelaExemplar.getSelectionModel()
                .getSelectedItem();

        //Se tem algum usuario selecionado
        if (exemplarselecionado != null) { //tem usuario selecionado
            //Pegar os dados do usuario e jogar nos campos do
            //formulario
            txtExemplar.setText(
                    String.valueOf(exemplarselecionado.getId()));

        } else { //não tem usuario selecionado na tabela
            mensagemErro("Selecione um Exemplar.");
        }

    }

    @FXML
    private void pegarUsuario(MouseEvent event) {

        //Pegar o usuario que foi selecionado na tabela
        userselecionado = tabelaUser.getSelectionModel()
                .getSelectedItem();

        //Se tem algum usuario selecionado
        if (userselecionado != null) { //tem usuario selecionado
            //Pegar os dados do usuario e jogar nos campos do
            //formulario
            txtUser.setText(
                    String.valueOf(userselecionado.getId()));

        } else { //não tem usuario selecionado na tabela
            mensagemErro("Selecione um Usuário.");
        }
    }

    public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void emprestar(ActionEvent event) {

        Exemplar exemplar = tabelaExemplar.getSelectionModel().getSelectedItem();
        int numExemplar = exemplar.getNumExemplar();

        if (numExemplar == 0) {

            mensagemErro("Exemplar Indisponível");

        } else {

            Exemplar ex = new Exemplar();

            Emprestimo emp = new Emprestimo();
            emp.setDataretirada(txtDataEmprestimo.getText());
            emp.setDataDevolucao(txtDataDevolucao.getText());
            emp.setObservacao(txtObs.getText());
            Usuario u = new Usuario();
            u.setId(Integer.valueOf(txtUser.getText()));
            emp.setUsuario(u);

            ex.setId(Integer.valueOf(txtExemplar.getText()));
            emp.setExemplar(ex);
            Funcionario f = new Funcionario();
            f.setId(Integer.valueOf(txtFunc.getText()));
            emp.setFuncionario(f);
            servicoemprestimo.salvar(emp);
            listarExemplaresTabela();
            listarEmprestimos();

        }

        //Exibindo mensagem
    }

    private void listarEmprestimos() {
        //Limpando quaisquer dados anteriores
        dadosemprestimo.clear();

        //Solicitando a camada de servico a lista de funcionários
        List<Emprestimo> emprestimos = servicoemprestimo.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosemprestimo = FXCollections.observableArrayList(emprestimos);

        //Jogando os dados na tabela
        tabelaEmprestimo.setItems(dadosemprestimo);

    }

}
