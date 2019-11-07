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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          configurarTabela();

        //Carregue a lista de atores na tabela
        listarAtoresTabela();
        // TODO
    }

 private void configurarTabela() {

        //Dizer de onde a coluna vai pegar o valor para
        //exibir, basta dizer o nome do metodo get
        //que deve ser chamado para cada coluna
        // A string entre parênteses é o nome do atributo
        // que vc deseja chamar o get (quase sempre)
        //import javafx.scene.control.cell.PropertyValueFactory;
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
 
  private void listarAtoresTabela() {
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
