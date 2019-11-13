package DAO;

import dados.entidade.Exemplar;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import java.util.List;
import javax.persistence.TypedQuery;

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
    
     public List<Exemplar> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select exemplar from Exemplar exemplar", Exemplar.class);

        //Retornar a lista de atores
        return consulta.getResultList();

    }
    
}
