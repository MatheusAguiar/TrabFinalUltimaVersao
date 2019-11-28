/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Servicos.FuncionarioServico;
import UTIL.TextFieldFormatter;
import com.jfoenix.controls.JFXButton;
import dados.entidade.Funcionario;
import dados.entidade.Usuario;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private JFXButton btnPesquisar;

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

    @FXML
    private void pesquisarPorNome(ActionEvent event) {

        //Limpando quaisquer dados anteriores
        dados.clear();

        String nome = txtPesquisaFuncionario.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Funcionario> funcionarios = servico.buscarPorNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(funcionarios);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }

    @FXML
    private void editar(ActionEvent event) {

        Optional<ButtonType> btn
                = mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                        "EDITAR");

        //Se o botão OK foi pressionado
        if (btn.get() == ButtonType.OK) {
            //Pegar os novos dados do formulário e
            //atualizar o usuario
            selecionado.setId(Integer.valueOf(txtId.getText()));
            selecionado.setNome(txtNome.getText());
            selecionado.setCpf(txtCpf.getText());
            selecionado.setEndereco(txtEndereco.getText());
            selecionado.setEmail(txtEmail.getText());
            selecionado.setTelefone(txtTelefone.getText());
            selecionado.setCodigoContrato(Integer.valueOf(txtContrato.getText()));
            selecionado.setFimContrato(String.valueOf(txtFimContrato.getValue()));

            //Mandando pra camada de serviço salvar as alterações
            servico.editar(selecionado);

            //Exibindo mensagem
            mensagemSucesso("Funcionario atualizado com sucesso!");

            //Chama o metodo para atualizar a tabela
            listarFuncionariosTabela();
        }
    }

    @FXML
    private void excluir(ActionEvent event) {

        //Pegar o ator que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
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
                mensagemSucesso("Funcionario excluído com sucesso");

                //Atualizar a tabela
                listarFuncionariosTabela();

            }

        } else {
            mensagemErro("Selecione um livro.");
        }
    }

    @FXML
    private void mouseclick(MouseEvent event) {
        
        //Pegar o usuario que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
                .getSelectedItem();

        //Se tem algum usuario selecionado
        if (selecionado != null) { //tem usuario selecionado
            //Pegar os dados do usuario e jogar nos campos do
            //formulario
            txtId.setText(
                    String.valueOf(selecionado.getId()));
            txtNome.setText(selecionado.getNome());            
            txtCpf.setText(selecionado.getCpf());
            txtEndereco.setText(selecionado.getEndereco());
            txtEmail.setText(selecionado.getEmail());
            txtTelefone.setText(selecionado.getTelefone());
            txtContrato.setText(String.valueOf(selecionado.getCodigoContrato()));
            txtFimContrato.setValue(LocalDate.parse(selecionado.getFimContrato()));           
                      
            

        } else { //não tem usuario selecionado na tabela
            mensagemErro("Selecione um funcionário.");
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

    public void mensagemErro(String m) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERRO!");
        alerta.setHeaderText(null);
        alerta.setContentText(m);
        alerta.showAndWait();
    }

    @FXML
    private void maskCpf(KeyEvent event) {
        
         TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    @FXML
    private void maskFone(KeyEvent event) {
        
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtTelefone);
        tff.formatter();
    }

}
