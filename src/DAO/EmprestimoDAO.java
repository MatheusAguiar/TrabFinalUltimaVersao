package DAO;

import dados.entidade.Emprestimo;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;

public class EmprestimoDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Emprestimo emprestimo){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(emprestimo);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
}
