/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import DAO.ExemplarDAO;
import dados.entidade.Exemplar;
import java.util.List;

/**
 *
 * @author IFNMG
 */
public class ExemplarServico {
    
      private ExemplarDAO dao = new ExemplarDAO();
    
    public void salvar(Exemplar a){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
     public List<Exemplar> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
    
}
