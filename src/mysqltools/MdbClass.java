/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqltools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chaurasia
 */
public class MdbClass implements QueryState {

    void createTempTableLog(Connection MDBconn) {
        Statement st;
        try {
            st = MDBconn.createStatement();
            st.execute("CREATE TABLE example1 (id COUNTER PRIMARY KEY,descr text(400), number numeric(12,3), date0 datetime) ");
            queryOK();
        } catch (SQLException ex) {
            queryFailed();
            Logger.getLogger(MdbClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void queryOK() {
        System.err.println("Query Executed Successfully");
    }

    @Override
    public void queryFailed() {
        System.err.println("Query Execution Failed");
    }

}
