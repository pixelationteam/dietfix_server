/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pup.thesis.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import pup.thesis.logging.App;
import pup.thesis.nlu.CoreParser;

/**
 *
 * @author Dell
 */
public class DietfixServer {
    private static CoreParser parser = null;
    
    private static void init(){
        App.log("Dietfixser:21", "Starting server.");
        parser=new CoreParser();
    }
    public static void start(){
        init();
    }
    public static void stop(){
        parser = null;
        
    }
    
    public static boolean isRunning(){
        return parser!=null;
    	//return true;
    }
    
    public static CoreParser getParser(){
        return parser;
    }
    
  
}
