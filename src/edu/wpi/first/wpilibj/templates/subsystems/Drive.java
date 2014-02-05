/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.JagPair;

/**
 *
 * @author David
 */
public class Drive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    PIDController PIDControl;
    private static final double p = 0.2;
    private static final double i = 0.001;
    private static final double d = 0.001;
    private static final double f = 0.15;
    Encoder encoder;
    private static final int encoderAChannel = 5;
    private static final int encoderBChannel = 4;
    CANJaguar jag;
    private static final double cycleTime = 0.020;
    
    public Drive() {
        JagPair pair = new JagPair(6,3);
        encoder = new Encoder(encoderAChannel,encoderBChannel, false, EncodingType.k4X);
        encoder.setDistancePerPulse(1.57/250);
        encoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate);
        encoder.setSamplesToAverage(5);
        PIDControl = new PIDController(p,i,d,f,encoder, pair , cycleTime);
        
    }
    public void EnablePID() {
        encoder.reset();
        PIDControl.enable();
        encoder.start();
        
    }
    
    public void Run() {
        
        PIDControl.setSetpoint(2);
        while (true){
            SmartDashboard.putDouble("encoder value", encoder.getRate());
            System.out.println("error " + PIDControl.getError());
            System.out.println("PIDControl " + PIDControl.get());
            System.out.println("encoder " + encoder.getRate());
        }
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
