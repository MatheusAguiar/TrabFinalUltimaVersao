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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    
}
