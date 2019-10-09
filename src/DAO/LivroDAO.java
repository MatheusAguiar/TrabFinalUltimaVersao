package DAO;

import dados.entidade.Livro;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;

public class LivroDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Livro livro){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(livro);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
}
