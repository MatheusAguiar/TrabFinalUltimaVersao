package DAO;

import dados.entidade.Usuario;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;

public class UsuarioDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Usuario usuario){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(usuario);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
}
