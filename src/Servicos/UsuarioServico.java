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
     
      public void editar(Usuario usuario) {

        dao.editar(usuario);

    }

    public void excluir(Usuario usuario) {

        dao.excluir(usuario);
    }

    public List<Usuario> buscarPorNome(String n) {

        return dao.buscarPorNome(n);

    }
    
}
