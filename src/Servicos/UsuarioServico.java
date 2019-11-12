package Servicos;

import DAO.UsuarioDAO;
import dados.entidade.Usuario;
import java.util.List;


public class UsuarioServico {
    
      private UsuarioDAO dao = new UsuarioDAO();
    
    public void salvar(Usuario a){
       
        dao.salvar(a);
    }
    
     public List<Usuario> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
    
}
