// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc4678.GLaDOS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
//import edu.wpi.first.wpilibj.can.CANTimeoutException;
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
    public static SpeedController driveTrainleftMotor;
    public static Encoder driveTrainrightEncoder;
    public static Encoder driveTrainleftEncoder;
    public static SpeedController driveTrainrightMotor;
    public static AnalogInput driveTraindistanceToWall;
    public static AnalogInput driveTrainlightSensorLeft;
    public static AnalogInput driveTrainlightSensorRight;
    public static AnalogInput shootingshooterAnglePot;
    public static Servo shootinglatchServo;
    public static SpeedController shootingwinchMotor;
    public static AnalogInput shootingwinchPot;
    public static DigitalInput shootingshooterLimit;
    public static SpeedController pickuppickupWheels;
    public static AnalogInput pickuparmPot;
    public static SpeedController pickuppickupArm;
    public static AnalogInput pickuparmOffset;
    public static DigitalInput pickuppickupLowerLimit;
    public static DigitalInput pickupisHoldingBall;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static void init()  {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainleftMotor = new Jaguar(1);
	LiveWindow.addActuator("driveTrain", "leftMotor", (Jaguar) driveTrainleftMotor);
        
        driveTrainrightEncoder = new Encoder(3, 4, false, EncodingType.k4X);
	LiveWindow.addSensor("driveTrain", "rightEncoder", driveTrainrightEncoder);
        driveTrainrightEncoder.setDistancePerPulse(1.0);
        driveTrainrightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        //driveTrainrightEncoder.start();
        driveTrainleftEncoder = new Encoder(1, 2, false, EncodingType.k4X);
	LiveWindow.addSensor("driveTrain", "leftEncoder", driveTrainleftEncoder);
        driveTrainleftEncoder.setDistancePerPulse(1.0);
        driveTrainleftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        //driveTrainleftEncoder.start();
        driveTrainrightMotor = new Jaguar(2);
	LiveWindow.addActuator("driveTrain", "rightMotor", (Jaguar) driveTrainrightMotor);
        
        driveTrainlightSensorLeft = new AnalogInput(7);
	LiveWindow.addSensor("driveTrain", "lightSensorLeft", driveTrainlightSensorLeft);
        
        driveTrainlightSensorRight = new AnalogInput(6);
	LiveWindow.addSensor("driveTrain", "lightSensorRight", driveTrainlightSensorRight);
        
        shootingshooterAnglePot = new AnalogInput(1);
	LiveWindow.addSensor("shooting", "shooterAnglePot", shootingshooterAnglePot);
        
        shootinglatchServo = new Servo(3);
	LiveWindow.addActuator("shooting", "latchServo", shootinglatchServo);
        
        shootingwinchMotor = new Jaguar(4);
	LiveWindow.addActuator("shooting", "winchMotor", (Jaguar) shootingwinchMotor);
        
        shootingwinchPot = new AnalogInput(2);
	LiveWindow.addSensor("shooting", "winchPot", shootingwinchPot);
        
        shootingshooterLimit = new DigitalInput(7);
	LiveWindow.addSensor("shooting", "shooterLimit", shootingshooterLimit);
        
        pickuppickupWheels = new Jaguar(6);
	LiveWindow.addActuator("pickup", "pickupWheels", (Jaguar) pickuppickupWheels);
        
        pickuparmPot = new AnalogInput(3);
	LiveWindow.addSensor("pickup", "armPot", pickuparmPot);
        
        pickuppickupArm = new Jaguar(8);
	LiveWindow.addActuator("pickup", "pickupArm", (Jaguar) pickuppickupArm);
        
        pickuparmOffset = new AnalogInput(4);
	LiveWindow.addSensor("pickup", "armOffset", pickuparmOffset);
        
        pickuppickupLowerLimit = new DigitalInput(5);
	LiveWindow.addSensor("pickup", "pickupLowerLimit", pickuppickupLowerLimit);
        
        pickupisHoldingBall = new DigitalInput(11);
	LiveWindow.addSensor("pickup", "isHoldingBall", pickupisHoldingBall);
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
