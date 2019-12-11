package DAO;

import UTIL.Conexao;
import static UTIL.Conexao.Conexao;
import dados.entidade.Emprestimo;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import dados.entidade.Exemplar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.TypedQuery;

public class EmprestimoDAO {

    /**
     * Salvar o emprestimo no BD
     */
    public void salvar(Emprestimo emprestimo) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o emprestimo
        gerenciador.persist(emprestimo);

        //Commit
        gerenciador.getTransaction().commit();

    }
    
     public void editar(Emprestimo emp) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(emp);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    
    public List<Emprestimo> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select emprestimo from Emprestimo emprestimo", Emprestimo.class);

        //Retornar a lista de exemplares
        return consulta.getResultList();

    }
    
    public List<Emprestimo> listarEmprestimosDevolvidos() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select emprestimo from Emprestimo emprestimo WHERE emprestimo.devolvido=true", Emprestimo.class);

        //Retornar a lista de emprestimos
        return consulta.getResultList();

    }
    
    public List<Emprestimo> pesquisarPorData(String dataini, String datafim) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "select emp from Emprestimo emp WHERE emp.dataretirada >= :dataini AND emp.dataDevolucao < :datafim", Emprestimo.class);

        
        consulta.setParameter("dataini",  dataini +  "%");
        consulta.setParameter("datafim",  datafim + "%");
        //Retornar a lista de emprestimos
        return consulta.getResultList();

    }
    
     public List<Emprestimo> pesquisaNomeFunc(String n) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Emprestimo> consulta = gerenciador.createQuery(
                "select emp from Emprestimo emp inner join Funcionario func on func.id = emp.funcionario AND nome like :nome", Emprestimo.class);

        consulta.setParameter("nome", "%" + n + "%");

        return consulta.getResultList();

    }
     
      public List<Emprestimo> pesquisaNomeUser(String n) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Emprestimo> consulta = gerenciador.createQuery(
                "select emp from Emprestimo emp inner join Usuario user on user.id = emp.usuario AND nome like :nome", Emprestimo.class);

        consulta.setParameter("nome", "%" + n + "%");

        return consulta.getResultList();

    }
      
      public List<Emprestimo> pesquisaExemplar(String n) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Emprestimo> consulta = gerenciador.createQuery(
                "select emp from Emprestimo emp inner join Exemplar ex on ex.id = emp.emprestimo inner join Livro livro on livro.id = ex.livro AND titulo like :titulo", Emprestimo.class);

        consulta.setParameter("titulo", "%" + n + "%");

        return consulta.getResultList();

    }

}
