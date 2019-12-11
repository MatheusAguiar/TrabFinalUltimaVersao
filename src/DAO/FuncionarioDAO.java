package DAO;

import UTIL.Conexao;
import dados.entidade.Funcionario;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.TypedQuery;

public class FuncionarioDAO {

    /**
     * Salvar o funcionário no BD
     */
    public void salvar(Funcionario funcionario) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o funcionário
        gerenciador.persist(funcionario);

        //Commit
        gerenciador.getTransaction().commit();

    }

    public boolean checkLogin(String email, String senhaacesso) throws SQLException {

      

        PreparedStatement stat = null;
        ResultSet rs = null;
        boolean check = false;
       Connection con = Conexao.Conexao();
        /*Criando obj. capaz de executar instruções
         SQL no banco de dados*/
        

        try
        {

            stat = con.prepareStatement("select * from funcionario where email = ? and senhaacesso = ?");

            stat.setString(1, email);
            stat.setString(2, senhaacesso);

            rs = stat.executeQuery();
            if (rs.next())
            {

                check = true;
            }

            //Retornando o ArrayList com todos objetos
        } catch (SQLException se)
        {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally
        {

        }//fecha finally

        return check;

    }
    
     public List<Funcionario> listar() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select funcionario from Funcionario funcionario", Funcionario.class);

        //Retornar a lista de atores
        return consulta.getResultList();

    }
     
     public void editar(Funcionario func) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(func);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public void excluir(Funcionario func) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Para excluir tem que dar o merge primeiro para 
        //sincronizar o funcionário do BD com o funcionário que foi
        //selecionado na tela
        func = gerenciador.merge(func);

        //Mandar sincronizar as alterações 
        gerenciador.remove(func);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public List<Funcionario> buscarPorNome(String n) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Funcionario> consulta = gerenciador.createQuery(
                "Select funcionario from Funcionario funcionario where funcionario.nome like :nome", Funcionario.class);

        consulta.setParameter("nome", "%" + n + "%");

        return consulta.getResultList();

    }

}
