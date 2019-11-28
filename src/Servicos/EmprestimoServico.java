/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import DAO.EmprestimoDAO;
import dados.entidade.Emprestimo;
import dados.entidade.Exemplar;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author IFNMG
 */
public class EmprestimoServico {
    
    
     private EmprestimoDAO dao = new EmprestimoDAO();
    
    public void salvar(Emprestimo a){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
     public List<Emprestimo> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
     
      public List<Emprestimo> pesquisaNomeFunc(String n) {

        return dao.pesquisaNomeFunc(n);

    }
      
       public List<Emprestimo> pesquisaNomeUser(String n) {

        return dao.pesquisaNomeUser(n);

    }
       
       public List<Emprestimo> pesquisaExemplar(String n) {

        return dao.pesquisaExemplar(n);

    }
    
    
}
