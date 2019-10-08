/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import UTIL.JPAUtil;
import dados.entidade.Funcionario;
import javax.persistence.EntityManager;

/**
 *
 * @author mats-
 */
public class TestaRelacionamentos {
    
     public static void main(String[] args) {
        
        //Criando um objeto ator
        Funcionario f1 = new Funcionario();
        f1.setNome("Matheus");
        f1.setCpf("12048995683");
        f1.setEndereco("Rua A");
        f1.setEmail("matsaguiar");
        f1.setTelefone("37441271");
        f1.setSenhaacesso("123");
        f1.setCodigoContrato(123);       
        
        
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
