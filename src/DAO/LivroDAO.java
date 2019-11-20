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
     
      public void editar(Livro livro) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(livro);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
      
      public void excluir(Livro livro){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o livro do BD com o ator que foi
        //selecionado na tela
        livro = gerenciador.merge(livro);

        //Mandar sincronizar as alterações 
        gerenciador.remove(livro);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }
      
      public List<Livro>buscarPorTitulo(String n){
      
      EntityManager gerenciador = JPAUtil.getGerenciador();
      
      TypedQuery<Livro> consulta = gerenciador.createQuery(
                "Select livro from Livro livro where livro.titulo like :titulo", Livro.class);
      
      consulta.setParameter("titulo", "%"+ n + "%");
      
      return consulta.getResultList();
      
      
      }
    
}
