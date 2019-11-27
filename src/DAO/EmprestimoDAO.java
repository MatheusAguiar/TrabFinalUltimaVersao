package DAO;

import dados.entidade.Emprestimo;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
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
    
    public List<Emprestimo> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select emprestimo from Emprestimo emprestimo", Emprestimo.class);

        //Retornar a lista de exemplares
        return consulta.getResultList();

    }

}
