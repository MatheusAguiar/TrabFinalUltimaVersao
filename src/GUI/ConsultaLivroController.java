/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.LivroServico;
import dados.entidade.Livro;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mats-
 */
public class ConsultaLivroController implements Initializable {

    @FXML
    private TextField txtPesquisaLivro;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<Livro> tabelaConsultaLivro;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn titulo;
    @FXML
    private TableColumn ano;
    @FXML
    private TableColumn genero;
    @FXML
    private TableColumn autores;
    @FXML
    private TableColumn volume;
    @FXML
    private TableColumn editora;

    private ObservableList<Livro> dados
            = FXCollections.observableArrayList();

    private Livro selecionado;

    private LivroServico servico = new LivroServico();
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtGenereo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtVolume;
    @FXML
    private TextField txtEditora;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabela();

        //Carregue a lista de atores na tabela
        listarLivrosTabela();
        // TODO
    }

    private void configurarTabela() {

        id.setCellValueFactory(
                new PropertyValueFactory("id"));
        titulo.setCellValueFactory(
                new PropertyValueFactory("titulo"));
        ano.setCellValueFactory(
                new PropertyValueFactory("ano"));
        genero.setCellValueFactory(
                new PropertyValueFactory("genero"));
        autores.setCellValueFactory(
                new PropertyValueFactory("autor"));
        volume.setCellValueFactory(
                new PropertyValueFactory("volume"));
        editora.setCellValueFactory(
                new PropertyValueFactory("editora"));

    }//configurarTabela    

    private void listarLivrosTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de atores
        List<Livro> livros = servico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(livros);

        //Jogando os dados na tabela
        tabelaConsultaLivro.setItems(dados);

    }

    @FXML
    private void mouseClick(MouseEvent event) {

        //Pegar o livro que foi selecionado na tabela
        selecionado = tabelaConsultaLivro.getSelectionModel()
                .getSelectedItem();

        //Se tem algum livro selecionado
        if (selecionado != null) { //tem livro selecionado
            //Pegar os dados do ator e jogar nos campos do
            //formulario
            txtId.setText(
                    String.valueOf(selecionado.getId()));
            txtTitulo.setText(selecionado.getTitulo());
            txtAno.setText(String.valueOf(selecionado.getAno()));
            txtGenereo.setText(selecionado.getGenero());
            txtAutor.setText(selecionado.getAutor());
            txtVolume.setText(String.valueOf(selecionado.getVolume()));
            txtEditora.setText(selecionado.getEditora());

        } else { //não tem ator selecionado na tabela
            mensagemErro("Selecione um livro.");
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
    private void editar(ActionEvent event) {

        Optional<ButtonType> btn
                = mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                        "EDITAR");

        //Se o botão OK foi pressionado
        if (btn.get() == ButtonType.OK) {
            //Pegar os novos dados do formulário e
            //atualizar o livro
            selecionado.setId(Integer.valueOf(txtId.getText()));
            selecionado.setTitulo(txtTitulo.getText());
            selecionado.setAno(Integer.valueOf(txtAno.getText()));
            selecionado.setGenero(txtGenereo.getText());
            selecionado.setAutor(txtAutor.getText());
            selecionado.setVolume(Integer.valueOf(txtVolume.getText()));
            selecionado.setEditora(txtEditora.getText());

            //Mandando pra camada de serviço salvar as alterações
            servico.editar(selecionado);

            //Exibindo mensagem
            mensagemSucesso("Livro atualizado com sucesso!");

            //Chama o metodo para atualizar a tabela
            listarLivrosTabela();
        }
    }

    private Optional<ButtonType> mensagemDeConfirmacao(
            String mensagem, String titulo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }

    public void mensagemSucesso(String m) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("SUCESSO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void excluir(ActionEvent event) {

        //Pegar o ator que foi selecionado na tabela
        selecionado = tabelaConsultaLivro.getSelectionModel()
                .getSelectedItem();

        //Verifico se tem ator selecionado
        if (selecionado != null) { //existe ator selecionado

            //Pegando a resposta da confirmacao do usuario
            Optional<ButtonType> btn
                    = mensagemDeConfirmacao("Deseja mesmo excluir?",
                            "EXCLUIR");

            //Verificando se apertou o OK
            if (btn.get() == ButtonType.OK) {

                //Manda para a camada de serviço excluir
                servico.excluir(selecionado);

                //mostrar mensagem de sucesso
                mensagemSucesso("Livro excluído com sucesso");

                //Atualizar a tabela
                listarLivrosTabela();

            }

        } else {
            mensagemErro("Selecione um livro.");
        }

    }

    @FXML
    private void buscarPorTitulo(ActionEvent event) {

        //Limpando quaisquer dados anteriores
        dados.clear();

        String titulo = txtPesquisaLivro.getText();

        //Solicitando a camada de servico a lista de atores
        List<Livro> livros = servico.buscarPorTitulo(titulo);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(livros);

        //Jogando os dados na tabela
        tabelaConsultaLivro.setItems(dados);

    }

}
