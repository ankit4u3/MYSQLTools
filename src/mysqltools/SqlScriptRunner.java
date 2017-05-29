/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqltools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SqlScriptRunner {
  Main mRef;
    
    SqlScriptRunner(Main m)
    {
        mRef=m;
    }
    
  private final Logger log = LoggerFactory.getLogger(getClass());
  
  public int run(Connection conn, File file, boolean continueOnError) throws IOException {
    return run(conn, file, "UTF-8", continueOnError);
  }
  
  public int run(Connection conn, File file, String charset, boolean continueOnError) throws IOException {
    return run(conn, new FileInputStream(file), charset, continueOnError);
  }
  
  public int run(Connection conn, InputStream in, boolean continueOnError) throws IOException {
    return run(conn, in, "UTF-8", continueOnError);
  }
  
  public int run(Connection conn, InputStream in, String charset, boolean continueOnError) throws IOException {
    int success = 0;
    BufferedReader bin = new BufferedReader(new InputStreamReader(in, charset));
    StringBuilder sql = new StringBuilder();
    String line = null;
    while( (line = bin.readLine()) != null){
      if(line.trim().endsWith(";")){
        try(Statement stmt = conn.createStatement()){
          String str = sql.toString();
          log.debug("Executing SQL: \n{}" , str);
          mRef.Console(str);
          stmt.executeUpdate(str);
          success++;
        }catch(SQLException e){
          if(continueOnError){
            log.error(e.getMessage(), e);
          }else{
            throw new RuntimeException(e);
          }
        }
        sql = new StringBuilder();
      }else{
        sql.append(line);
      }
    }
    return success;
  }
  
}