package DAO;

import dados.entidade.Funcionario;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;

public class FuncionarioDAO {
    
    /**
     * Salvar o ator no BD
     */
    public void salvar(Funcionario funcionario){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandar persistir o ator
        gerenciador.persist(funcionario);
        
        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
}
