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
import edu.wpi.first.wpilibj.Encoder;
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
	boolean getStopped;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController clawMotor = RobotMap.clawClawMotor;
    Encoder clawPosition = RobotMap.clawClawPosition;

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
    	System.out.println("Power: " + power);
    }
    public int getClawPosition() {
    	return clawPosition.get();
    }
    public void setClaw(double target) {
    	getStopped = clawPosition.getStopped();
    	power = Robot.clawMaxPower();
    	clawMaxPower = Robot.clawMaxPower();
    	clawTarget = target;
    	//calculating the difference between the target and current position
    	error = clawTarget - clawPosition.get();
    	System.out.println("Claw Position: " + clawPosition.get());
    	System.out.println("Encoder Initialized is: " + getStopped);
    	//if the difference is greater than 0.5 power should be full forward
    	if (error > 50) {
			power = clawMaxPower;
		}
		//if the difference is less than -0.5, power should be full in reverse
		else if (error < -50) {
			power = -clawMaxPower;
		}
		else {
			power = error * 0.05;
			if (power > clawMaxPower) {
				power = clawMaxPower;
			}
			else if (power < -clawMaxPower) {
				power = -clawMaxPower;
			}
		}
    	clawMotor.set(-power);
    	
    }
}

