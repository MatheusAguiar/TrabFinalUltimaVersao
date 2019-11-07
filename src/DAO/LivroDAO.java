package DAO;

import dados.entidade.Livro;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import java.util.List;
import javax.persistence.TypedQuery;

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
    
     public List<Livro> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select livro from Livro livro", Livro.class);

        //Retornar a lista de atores
        return consulta.getResultList();

    }
    
}
