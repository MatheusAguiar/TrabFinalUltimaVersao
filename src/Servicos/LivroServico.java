package Servicos;

import DAO.LivroDAO;
import dados.entidade.Livro;
import java.util.List;

public class LivroServico {
    
    //Atributo para representar a camada de dados
    private LivroDAO dao = new LivroDAO();
    
    public void salvar(Livro a){
        //Fazer qualquer regra de negócio
        
        //Mandar o ator para a camada de dados
        //para ser salvo no banco de dados
        dao.salvar(a);
    }
    
     public List<Livro> listar(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listar();
        
    }
      
    
    
    public void editar(Livro livro){
        
        dao.editar(livro);
        
    }
    
    public void excluir(Livro livro){
        
        dao.excluir(livro);
    }
    
}
