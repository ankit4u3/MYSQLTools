/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqltools;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author Chaurasia
 */
public class CheckMDBClass {

    Main mRef;

    public CheckMDBClass(Main m) {
        this.mRef = m;

    }

    public void checkMdbExists() {
        String currentLocation;
        try {
            currentLocation = new File(".").getCanonicalPath();
            File checkpet = new File(currentLocation + "/" + "JK_finalpass.mdb");

            if (checkpet.exists() == false) {

                mRef.ConsoleMsg("MDB JK_finalpass.mdb is missing");
                mRef.ConsoleMsg("File Located JK_finalpass.mdb");
                

            } else {
                mRef.ConsoleMsg("File Located JK_finalpass.mdb");
               
                mRef.ConsoleMsg("Starting Download Daemon");
            }

        } catch (IOException ex) {

        }

    }

}
