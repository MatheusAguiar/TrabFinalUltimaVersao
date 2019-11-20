
package Servicos;

import DAO.FuncionarioDAO;
import dados.entidade.Funcionario;
import java.util.List;

public class FuncionarioServico {
    
    
    private FuncionarioDAO dao = new FuncionarioDAO();
    
    public void salvar(Funcionario a){
        
        dao.salvar(a);
    }
    
       public List<Funcionario> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
       
          public void editar(Funcionario func) {

        dao.editar(func);

    }

    public void excluir(Funcionario func) {

        dao.excluir(func);
    }

    public List<Funcionario> buscarPorNome(String n) {

        return dao.buscarPorNome(n);

    }
    
}
