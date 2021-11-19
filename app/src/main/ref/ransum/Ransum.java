/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ransum;

import controller.Main;
import java.sql.SQLException;
import model.BahanPakan;
import model.SapiPerah;
import view.FrameAlgen;


/**
 *
 * @author ggnryr
 */
public class Ransum {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws SQLException {
        new Main(new FrameAlgen(), new SapiPerah(), new BahanPakan());
//          FrameAlgen rf = new FrameAlgen();
//          rf.setVisible(true);
    }
    
}
