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
public class  PickTote extends Command {
	boolean lift = false;
    public PickTote() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.pickup);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pickup.setLifterState(0);
    	Robot.logger.info("PickTote", "Initialized");
    	lift = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.pickup.getLifterHeight() > Robot.lifterLowerTarget()) {
    		
    		//If it is not currently lifting, lower
    		if (!lift) {
    			Robot.pickup.lift(Robot.lifterLowerTarget());
    			Robot.logger.debug("PickTote", "Lowering");
    		}
    		
    	} else {
    		lift = true;
    		Robot.pickup.lift(Robot.lifterUpperTarget());
    		Robot.logger.debug("PickTote", "Lifting");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.pickup.getLifterHeight() > Robot.lifterUpperTarget() && lift) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pickup.setLifterServo(Robot.servoLockPos());
    	Robot.pickup.setLifterPower(0);
    	Robot.logger.info("PickTote", "Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pickup.setLifterPower(0);
    	Robot.logger.warning("PickTote", "Interrupted");
    }
}