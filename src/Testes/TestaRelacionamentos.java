/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import UTIL.JPAUtil;
import dados.entidade.Funcionario;
import dados.entidade.Livro;
import javax.persistence.EntityManager;

/**
 *
 * @author mats-
 */
public class TestaRelacionamentos {
    
     public static void main(String[] args) {
        
        //Criando um objeto ator
        Livro f1 = new Livro();
        f1.setAno(1900);
        f1.setAutor("ABC");
        f1.setEditora("Rua A");
        f1.setGenero("matsaguiar");
        f1.setTitulo("Eu");
        f1.setVolume(12);
              
        
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Mandando persistir o objeto
        gerenciador.persist(f1);
        //gerenciador.persist(a2);
        
        //Finalizo a transação
        gerenciador.getTransaction().commit();
        
        //Fechar o gerenciador
        gerenciador.close();
        
    }
    
}
