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
	boolean canPickup = false;
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
    	canPickup = Robot.pickup.toteInPlace();
    	if (canPickup) {
	    	//If the robot has not hit the bottom, lower the lifter
	    	if (!lift) {
	    		if (Robot.pickup.lift(Robot.lifterPickupTarget())) {
	    			//If the lifter is at the bottom, start lifting up
	    			lift = true;
	    		}
	    	
	    	//If the robot has hit the bottom, lift up
	    	} else {
	    		Robot.logger.debug("PickTote", "Lifting");
	    	}
    	} else {
    		Robot.pickup.lift(Robot.lifterUpperTarget());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.info("PickTote", "Ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pickup.setLifterPower(0);
    	Robot.logger.warning("PickTote", "Interrupted");
    }
}
