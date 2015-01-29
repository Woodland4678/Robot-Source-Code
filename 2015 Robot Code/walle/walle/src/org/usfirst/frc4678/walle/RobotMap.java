// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.walle;
    
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController drivetrainLeftMotor;
    public static SpeedController drivetrainRightMotor;
    public static AnalogInput drivetrainLeftDistSensor;
    public static AnalogInput drivetrainRightDistSensor;
    public static Encoder drivetrainLeftEncoder;
    public static Encoder drivetrainRightEncoder;
    public static AnalogPotentiometer pickupLifterHeight;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainLeftMotor = new Jaguar(4);
        LiveWindow.addActuator("Drivetrain", "LeftMotor", (Jaguar) drivetrainLeftMotor);
        
        drivetrainRightMotor = new Jaguar(5);
        LiveWindow.addActuator("Drivetrain", "RightMotor", (Jaguar) drivetrainRightMotor);
        
        drivetrainLeftDistSensor = new AnalogInput(3);
        LiveWindow.addSensor("Drivetrain", "LeftDistSensor", drivetrainLeftDistSensor);
        
        drivetrainRightDistSensor = new AnalogInput(1);
        LiveWindow.addSensor("Drivetrain", "RightDistSensor", drivetrainRightDistSensor);
        
        drivetrainLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "LeftEncoder", drivetrainLeftEncoder);
        drivetrainLeftEncoder.setDistancePerPulse(1.0);
        drivetrainLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        drivetrainRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Drivetrain", "RightEncoder", drivetrainRightEncoder);
        drivetrainRightEncoder.setDistancePerPulse(1.0);
        drivetrainRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        pickupLifterHeight = new AnalogPotentiometer(2, 10.0, 0.0);
        LiveWindow.addSensor("Pickup", "LifterHeight", pickupLifterHeight);
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
