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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Pickup extends Subsystem {
	double distanceFromTarget = 0;
	double error = 0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogPotentiometer lifterHeight = RobotMap.pickupLifterHeight;
    SpeedController lifterMotor = RobotMap.pickupLifterMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new holdLifterSteady());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setLifterPower(double power) {
    	lifterMotor.set(power);
    }
    
    public double getLifterHeight() {
    	return lifterHeight.get();
    }
    
    public boolean lift(double target) {
    	double power = Robot.lifterPower();
    	double lifterMaxPower = Robot.lifterPower();
    	double lifterTarget = target;
    	SmartDashboard.putNumber("Lifter Height", lifterHeight.get());
		//creates the difference between target and current position
		error = lifterTarget - lifterHeight.get();
		//if the difference is greater than 1.2 power should be full
		if (error > 1.2) {
			power = lifterMaxPower;
		}
		//if the difference is less than -1.2, power should be full in reverse
		else if (error < -1.2) {
			power = -lifterMaxPower;
		}
		else {
			//creates a power reduction as the lifter gets closer to the target
			power = error * 0.5;
			// if power goes greater than 1, power is set to 1
			if (power > lifterMaxPower) {
				power = lifterMaxPower;
			}
			// if power goes less than -1, power is set to -1
			else if (power < -lifterMaxPower) {
				power = -lifterMaxPower;
			}
		}
				
		SmartDashboard.putNumber("Lifter Power", power);
		
		if (Math.abs(error) < 0.1) {
			System.out.println("Setting lifter to 0, because it was within the margin");
			lifterMotor.set(0);
			return true;
		}
		
		lifterMotor.set(-power);
		System.out.println("set power to " + (-power));
		return false;
    }
    
    /*public void Lower(double power) {
    	
    	double lifterMaxPower = Robot.lifterPower();
    	double lifterTarget = Robot.lifterLowerTarget();
    	SmartDashboard.putNumber("Lifter Height", lifterHeight.get());
    		error = lifterTarget - lifterHeight.get();
    		if(error > 1.2) {
    			power = lifterMaxPower;
    		}
    		else if (error < -1.2) {
    			power = -lifterMaxPower;
    		}
    		else {
    			power = error * 0.5;
    			if (power > lifterMaxPower) {
    				power = lifterMaxPower;
    			}
    			else if (power < -lifterMaxPower) {
    				power = -lifterMaxPower;
    			}
    		} 
    				
    		System.out.println(Robot.pickup.getLifterHeight());
    		lifterMotor.set(-power);
    	
    	
    } */
}

