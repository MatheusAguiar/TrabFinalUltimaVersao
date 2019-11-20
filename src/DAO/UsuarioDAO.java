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

}
