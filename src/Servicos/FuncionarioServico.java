/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicos;

import DAO.FuncionarioDAO;
import dados.entidade.Funcionario;

/**
 *
 * @author mats-
 */
public class FuncionarioServico {
    
    
    private FuncionarioDAO dao = new FuncionarioDAO();
    
    public void salvar(Funcionario a){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
}
