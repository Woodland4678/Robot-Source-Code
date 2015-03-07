// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.walle.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4678.walle.Robot;

/**
 *
 */
public class  GoToLoad extends Command {
	double GO_TO_LOAD_POWER = 0.17;
	double GO_TO_LOAD_DISTANCE = 0.73;
	double power = 0.2;

    public GoToLoad() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	power = GO_TO_LOAD_POWER;
    	
    	power *= Math.abs(Robot.drivetrain.getBackDistanceSensor() - GO_TO_LOAD_DISTANCE) * 5;
    	
    	if (Robot.drivetrain.getBackDistanceSensor() > GO_TO_LOAD_DISTANCE) {
    		power *= -1;
    	}
    	
    	if (Math.abs(power) < 0.05) {
    		power = 0;
    	} else if (power > 1) {
    		power = 1;
    	} else if (power < -1) {
    		power = -1;
    	}
    	
    	Robot.drivetrain.setMotor("both", power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setMotor("both", 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
