package GUI;

import Servicos.UsuarioServico;
import UTIL.TextFieldFormatter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidade.Usuario;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtContrato;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtEndereco;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXRadioButton rbSim;
    @FXML
    private ToggleGroup Ativo;
    @FXML
    private JFXRadioButton rbNao;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnExcluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configurarTabela();

        listarUsuariosTabela();

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

    private void listarUsuariosTabela() {
        //Limpando quaisquer dados anteriores
        dados.clear();

        //Solicitando a camada de servico a lista de usuarios
        List<Usuario> usuarios = servico.listar();

        //Transformar a lista de usuarios no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(usuarios);

        //Jogando os dados na tabela
        tabela.setItems(dados);

    }

    @FXML
    private void pesquisarPorNome(ActionEvent event) {

        //Limpando quaisquer dados anteriores
        dados.clear();

        String nome = txtPesquisaNome.getText();

        //Solicitando a camada de servico a lista de usuarios
        List<Usuario> usuarios = servico.buscarPorNome(nome);

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dados = FXCollections.observableArrayList(usuarios);

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
            selecionado.setCodigoContrato(Integer.valueOf(txtContrato.getText()));
            selecionado.setNome(txtNome.getText());
            selecionado.setCpf(txtCpf.getText());
            selecionado.setEndereco(txtEndereco.getText());
            selecionado.setEmail(txtEmail.getText());
            selecionado.setTelefone(txtTelefone.getText());
            if (rbSim.isSelected()) {

                selecionado.setAtivo(true);
            }

            if (rbNao.isSelected()) {

                selecionado.setAtivo(false);
            }

            //Mandando pra camada de serviço salvar as alterações
            servico.editar(selecionado);

            //Exibindo mensagem
            mensagemSucesso("Usuario atualizado com sucesso!");

            //Chama o metodo para atualizar a tabela
            listarUsuariosTabela();
        }

    }

    @FXML
    private void excluir(ActionEvent event) {

        //Pegar o usuario que foi selecionado na tabela
        selecionado = tabela.getSelectionModel()
                .getSelectedItem();

        //Verifico se tem usuario selecionado
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
                listarUsuariosTabela();

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
            txtContrato.setText(String.valueOf(selecionado.getCodigoContrato()));
            txtNome.setText(selecionado.getNome());
            txtCpf.setText(selecionado.getCpf());
            txtEndereco.setText(selecionado.getEndereco());
            txtEmail.setText(selecionado.getEmail());
            txtTelefone.setText(selecionado.getTelefone());
            if (rbSim.isSelected()) {

                selecionado.setAtivo(true);
            }

            if (rbNao.isSelected()) {

                selecionado.setAtivo(false);
            }

        } else { //não tem usuario selecionado na tabela
            mensagemErro("Selecione um usuario.");
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
