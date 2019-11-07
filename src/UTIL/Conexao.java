/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author mats-
 */
public class Conexao {

    Connection conn = null;

    public static Connection Conexao() {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "");
            return conn;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            return null;

        }

    }

}
