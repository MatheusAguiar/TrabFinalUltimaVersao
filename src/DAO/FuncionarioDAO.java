package DAO;

import UTIL.Conexao;
import dados.entidade.Funcionario;
import javax.persistence.EntityManager;
import UTIL.JPAUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.TypedQuery;

public class FuncionarioDAO {

    /**
     * Salvar o ator no BD
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

}
