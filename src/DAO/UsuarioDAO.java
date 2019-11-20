package DAO;

import dados.entidade.Usuario;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import java.util.List;
import javax.persistence.TypedQuery;

public class UsuarioDAO {

    /**
     * Salvar o usuario no BD
     */
    public void salvar(Usuario usuario) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o usuario
        gerenciador.persist(usuario);

        //Commit
        gerenciador.getTransaction().commit();

    }

    public List<Usuario> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select usuario from Usuario usuario", Usuario.class);

        //Retornar a lista de usuarios
        return consulta.getResultList();

    }
    
    public void editar(Usuario usuario) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(usuario);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public void excluir(Usuario usuario) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o livro do BD com o ator que foi
        //selecionado na tela
        usuario = gerenciador.merge(usuario);

        //Mandar sincronizar as alterações 
        gerenciador.remove(usuario);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public List<Usuario> buscarPorNome(String n) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Usuario> consulta = gerenciador.createQuery(
                "Select usuario from Usuario usuario where usuario.nome like :nome", Usuario.class);

        consulta.setParameter("nome", "%" + n + "%");

        return consulta.getResultList();

    }

}
