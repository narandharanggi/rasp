/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransum;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ggnryr
 */
public class Koneksi {
//    String username = "root";
//    String password = "";
//    String dbms = "mysql";
//    String serverName = "localhost";
//    String portNumber = "3306";
//    String dbName = "db-algen";
    
    String username;
    String password;
    String dbms;
    String serverName;
    String portNumber;
    String dbName;
    
    public Koneksi(){
        InputStream is = getClass().getResourceAsStream("/resource/configure.properties");
        Properties props = new Properties();
        try {
            props.load(is);
            username = props.getProperty("username");
            password = props.getProperty("password");
            dbms = props.getProperty("dbms");
            serverName = props.getProperty("serverName");
            portNumber = props.getProperty("portNumber");
            dbName = props.getProperty("dbName");
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws SQLException{
        Connection con = null;
        System.out.println("cobaaa");
        Properties connecProp = new Properties();
        connecProp.put("user",this.username);
        connecProp.put("password",this.password);

        con = (Connection) DriverManager.getConnection("jdbc:"+this.dbms+"://"+
                this.serverName+":"+this.portNumber+"/"+this.dbName, connecProp);

        System.out.println("DB connected");
        return con;
    }
}
