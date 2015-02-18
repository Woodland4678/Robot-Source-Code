// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.walle.subsystems;

import org.usfirst.frc4678.walle.Robot;
import org.usfirst.frc4678.walle.RobotMap;
import org.usfirst.frc4678.walle.commands.*;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class Arm extends Subsystem {
	double power;
	double error;
	int armState;
	double cnt;
	double armMaxPower;
	double armTarget;
	double lastArmTarget = -1;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController armMotor = RobotMap.armArmMotor;
    AnalogPotentiometer armPosition = RobotMap.armArmPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setArmMotor(double power) {
    	armMotor.set(power);
    }
    public double getArmPosition() {
    	return armPosition.get();
    }
    public boolean setArm(double Target) {
    	power = Robot.armPower();
    	armMaxPower = Robot.armPower();
    	double armTarget = Target;

    	//switch(armState) {
    		//case 0:
		    	//Finds the difference between target and current position
				error = armTarget - armPosition.get();
				//if the difference is greater than 0 power should be full
				if (error > 1) {
					power = armMaxPower;
				}
				//if the difference is less than 0, power should be full in reverse
				else if (error < -1) {
					power = -armMaxPower;
				}
				else {
					power = error * 0.9;
				}
				armMotor.set(power);
				
				/*if (Math.abs(error) < 0.1) {
					armMotor.set(0);
					cnt = 0;
					return true;
				}*/
				//break;
    		//}
    		return false;
    	
    	}
    	
	}


