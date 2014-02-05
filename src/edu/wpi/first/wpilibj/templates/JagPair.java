/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;


/**
 *
 * @author David
 */
public class JagPair implements PIDOutput {

    CANJaguar jag1, jag2;
    public JagPair( int jag1In, int jag2In) {
        try{
            jag1 = new CANJaguar(jag1In);
            jag2 = new CANJaguar(jag2In);
        }catch(CANTimeoutException ex) {
            System.out.println("CAN issue");
        }
    }
    public void pidWrite(double output) {
        jag1.pidWrite(output);
        jag2.pidWrite(output);
        
    }
    
}
