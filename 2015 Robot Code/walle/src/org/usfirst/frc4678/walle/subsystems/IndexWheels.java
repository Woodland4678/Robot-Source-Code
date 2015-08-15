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

import org.usfirst.frc4678.walle.RobotMap;
import org.usfirst.frc4678.walle.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class IndexWheels extends Subsystem {
	double indexTarget;
	double error;
	int wheelDirection;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Relay openIndexWheels = RobotMap.indexWheelsOpenIndexWheels;
    AnalogPotentiometer indexPosition = RobotMap.indexWheelsIndexPosition;
    SpeedController indexMotor = RobotMap.indexWheelsIndexMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void stopOpenMotor() {
    	openIndexWheels.set(Relay.Value.kOff);
    }
    public void setIndexMotor(int direction) {
    	wheelDirection = direction;
    	if (direction == 1) {
    		indexMotor.set(1);
    	}
    	else if (direction == -1) {
    		indexMotor.set(-1);
    	}
    	else {
    		indexMotor.disable();
    	}
    }
    
    public void setIndexWheels(double target) {
    	System.out.println("index target is " + target);
    	indexTarget = target;
    	error = indexTarget - indexPosition.get();
    	if (error > 0.04) {
    		openIndexWheels.set(Relay.Value.kForward);
    	}
    	else if (error < -0.04) {
    		openIndexWheels.set(Relay.Value.kReverse);
    	}
    	else {
    		openIndexWheels.set(Relay.Value.kOff);
    	}
    }
    public double getIndexPosition() {
    	return indexPosition.get();
    }
    public int getWheelDirection() {
    	return wheelDirection;
    }
}

