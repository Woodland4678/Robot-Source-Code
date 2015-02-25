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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Claw extends Subsystem {
	double power;
	double error;
	double clawMaxPower;
	double clawTarget;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController clawMotor = RobotMap.clawClawMotor;
    AnalogPotentiometer clawPosition = RobotMap.clawClawPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setClawMotor(double power) {
    	clawMotor.set(power);
    }
    public void setClaw(double target) {
    	power = Robot.clawMaxPower();
    	clawMaxPower = Robot.clawMaxPower();
    	clawTarget = target;
    	error = clawTarget - clawPosition.get();
    	if (error > 0.5) {
			power = clawMaxPower;
		}
		//if the difference is less than 0, power should be full in reverse
		else if (error < -0.5) {
			power = -clawMaxPower;
		}
		else {
			power = error * 0.9;
		}
    	clawMotor.set(power);
    }
}

