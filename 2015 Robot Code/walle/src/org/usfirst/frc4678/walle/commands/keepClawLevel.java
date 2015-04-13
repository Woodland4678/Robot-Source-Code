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
public class  keepClawLevel extends Command {
	double armDegrees = Robot.arm.getArmDegrees();
	double goalClawDegrees;
	double goalClawPosition;
	double clawOffset = 0;
	boolean initialized = false;
    public keepClawLevel() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.claw);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!initialized) {
        	Robot.claw.resetClawPosition();
        	Robot.claw.setClawTargetDegrees(0);
        	clawOffset = -armDegrees;
        	initialized = true;
    	}
    	
    	//Adjust the angle of the claw when the dpad is held
    	if (Robot.oi.getGamepad2().getPOV() == 90) {
    		if (Robot.oi.getGamepad2().getRawButton(8)) {
    			Robot.claw.setClawTargetDegrees(Robot.claw.getClawTargetDegrees() + 1);
    		} else {
    			Robot.claw.setClawTargetDegrees(Robot.claw.getClawTargetDegrees() + 0.3);
    		}
    	} else if (Robot.oi.getGamepad2().getPOV() == 270) {
    		if (Robot.oi.getGamepad2().getRawButton(8)) {
    			Robot.claw.setClawTargetDegrees(Robot.claw.getClawTargetDegrees() - 1);
    		} else {
    			Robot.claw.setClawTargetDegrees(Robot.claw.getClawTargetDegrees() - 0.3);
    		}
    	}
    	
    	armDegrees = Robot.arm.getArmDegrees();
    	goalClawDegrees = armDegrees + Robot.claw.getClawTargetDegrees() + clawOffset;
    	goalClawPosition = (goalClawDegrees * Robot.clawTicsPerDegree());
    	Robot.claw.setClaw(goalClawPosition);
    	
    	System.out.println("goalClawDegrees = " + goalClawDegrees + " tics per degree = " + Robot.clawTicsPerDegree());
    	System.out.println("armDegrees = " + armDegrees + " Claw target = " + Robot.claw.getClawTargetDegrees());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.claw.setClawMotor(0);
    }
}
