package Testes;

import dados.entidade.Livro;
import javax.persistence.*;
import UTIL.JPAUtil;
import dados.entidade.Emprestimo;
import dados.entidade.Exemplar;
import dados.entidade.Funcionario;
import dados.entidade.Usuario;

public class TestaRelacionamentos {
    
    public static void main(String[] args) {
        
        //Criando um objeto ator
        Usuario u1 = new Usuario();
        u1.setAtivo(true);
        u1.setCodigoCadastro(123);
        u1.setCpf("123456");
        u1.setEmail("amsdmad");
        u1.setEndereco("asdasd");
        u1.setNome("Mars");
        u1.setTelefone("37441217");
        
         Funcionario f1 = new Funcionario();
        f1.setCodigoContrato(123);
        f1.setSenhaacesso("123");
        f1.setFimContrato("2010-10-10");
        f1.setCpf("123456");
        f1.setEmail("amsdmad");
        f1.setEndereco("asdasd");
        f1.setNome("Mars");
        f1.setTelefone("37441217");
        
        Livro l1 = new Livro();
        l1.setAno(1900);
        l1.setAutor("Eu");
        l1.setEditora("RSRS");
        l1.setGenero("Roma");
        l1.setTitulo("Casa");
        l1.setVolume(1);
        
        Exemplar e1 = new Exemplar();
        e1.setDisponivel(true);
        e1.setEdicao(1);
        e1.setLivro(l1);
        e1.setNumExemplar(2);
        e1.setTombo(1);
        
        Emprestimo emp1 = new Emprestimo();
        emp1.setDataDevolucao("2010-10-10");
        emp1.setDataretirada("2010-10-03");
        emp1.setExemplar(e1);
        emp1.setFuncionario(f1);
        emp1.setObservacao("nenhuma");
        emp1.setUsuario(u1);                
                                                
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandando persistir o objeto
        gerenciador.persist(u1);
        gerenciador.persist(f1);
        gerenciador.persist(l1);
        gerenciador.persist(e1);
        gerenciador.persist(emp1);
        
        
        //Finalizo a transação
        gerenciador.getTransaction().commit();
        
        //Fechar o gerenciador
        gerenciador.close();
        
    }
    
}
