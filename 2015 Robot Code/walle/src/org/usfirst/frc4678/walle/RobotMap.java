// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc4678.walle;
    

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
    public static Encoder drivetrainLeftEncoder;
    public static Encoder drivetrainRightEncoder;
    public static AnalogPotentiometer pickupLifterHeight;
    public static SpeedController pickupLifterMotor;
    public static Servo pickupLifterServo;
    public static DigitalInput pickupdrivingOverTote;
    public static DigitalInput pickupToteSensor1;
    public static DigitalInput pickupToteSensor2;
    public static SpeedController armArmMotor;
    public static AnalogPotentiometer armArmPosition;
    public static Relay indexWheelsIndexMotor;
    public static SpeedController clawClawMotor;
    public static Encoder clawClawPosition;
    public static AnalogPotentiometer squeezeArmOpenPosition;
    public static SpeedController squeezeOpenMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrainLeftMotor = new Jaguar(4);
        LiveWindow.addActuator("Drivetrain", "LeftMotor", (Jaguar) drivetrainLeftMotor);
        
        drivetrainRightMotor = new Jaguar(5);
        LiveWindow.addActuator("Drivetrain", "RightMotor", (Jaguar) drivetrainRightMotor);
        
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
        
        pickupLifterMotor = new Victor(6);
        LiveWindow.addActuator("Pickup", "LifterMotor", (Victor) pickupLifterMotor);
        
        pickupLifterServo = new Servo(7);
        LiveWindow.addActuator("Pickup", "LifterServo", pickupLifterServo);
        
        pickupdrivingOverTote = new DigitalInput(9);
        LiveWindow.addSensor("Pickup", "drivingOverTote", pickupdrivingOverTote);
        
        pickupToteSensor1 = new DigitalInput(5);
        LiveWindow.addSensor("Pickup", "ToteSensor1", pickupToteSensor1);
        
        pickupToteSensor2 = new DigitalInput(6);
        LiveWindow.addSensor("Pickup", "ToteSensor2", pickupToteSensor2);
        
        armArmMotor = new Victor(3);
        LiveWindow.addActuator("Arm", "ArmMotor", (Victor) armArmMotor);
        
        armArmPosition = new AnalogPotentiometer(0, 10.0, 0.0);
        LiveWindow.addSensor("Arm", "ArmPosition", armArmPosition);
        
        indexWheelsIndexMotor = new Relay(0);
        LiveWindow.addActuator("Index Wheels", "IndexMotor", indexWheelsIndexMotor);
        
        clawClawMotor = new Victor(1);
        LiveWindow.addActuator("Claw", "ClawMotor", (Victor) clawClawMotor);
        
        clawClawPosition = new Encoder(7, 8, false, EncodingType.k4X);
        LiveWindow.addSensor("Claw", "ClawPosition", clawClawPosition);
        clawClawPosition.setDistancePerPulse(1.0);
        clawClawPosition.setPIDSourceParameter(PIDSourceParameter.kRate);
        squeezeArmOpenPosition = new AnalogPotentiometer(4, 1.0, 0.0);
        LiveWindow.addSensor("squeeze", "ArmOpenPosition", squeezeArmOpenPosition);
        
        squeezeOpenMotor = new Victor(2);
        LiveWindow.addActuator("squeeze", "OpenMotor", (Victor) squeezeOpenMotor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
