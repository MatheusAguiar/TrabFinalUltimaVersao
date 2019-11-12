
package Servicos;

import DAO.FuncionarioDAO;
import dados.entidade.Funcionario;

public class FuncionarioServico {
    
    
    private FuncionarioDAO dao = new FuncionarioDAO();
    
    public void salvar(Funcionario a){
        
        dao.salvar(a);
    }
    
}
