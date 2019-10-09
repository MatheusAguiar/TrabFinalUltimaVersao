package DAO;

import dados.entidade.Exemplar;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;

public class ExemplarDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Exemplar exemplar){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(exemplar);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
}
