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
public class  setArm extends Command {
	boolean dPadPressed = false;
	boolean placingBin = false;
	double clawAngleIncreace = 10;
	double distancePastNinety;
	double startingClawTarget;
    public setArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	dPadPressed = false;
    	Robot.arm.setCurrentArmPosition(Robot.armRestPosition());
    	startingClawTarget = Robot.claw.getClawTargetDegrees();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.setArm(Robot.arm.getCurrentArmPosition());
    	
    	if (Robot.oi.getGamepad2().getPOV() == 0 && !dPadPressed) {
    		if (Robot.oi.getGamepad2().getRawButton(4)) {
    			Robot.arm.setCurrentArmPosition(Robot.arm.getCurrentArmPosition() + 0.07);
    		} else {
    			Robot.arm.setCurrentArmPosition(Robot.arm.getCurrentArmPosition() + 0.03);
    		}
    	} else if (Robot.oi.getGamepad2().getPOV() == 180 && !dPadPressed) {
    		if (Robot.oi.getGamepad2().getRawButton(4)) {
    			Robot.arm.setCurrentArmPosition(Robot.arm.getCurrentArmPosition() - 0.03);
    		} else {
    			Robot.arm.setCurrentArmPosition(Robot.arm.getCurrentArmPosition() - 0.01);
    		}
    	}
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
    }
}
