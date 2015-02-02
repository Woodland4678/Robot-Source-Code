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

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc4678.walle.Robot;

/**
 *
 */
public class  RobotDrive extends Command {
	double MAX_DECCELERATION_SPEED = Robot.maxDecelerationSpeed();
	double POWER_REDUCTION = Robot.driveMotorPowerReduction();
	double joystickX;
	double joystickY;
	double leftPower;
	double rightPower;
	double lastLeftPower = 0;
	double lastRightPower = 0;
	double current0;
	PowerDistributionPanel pdp = new PowerDistributionPanel();
    public RobotDrive() {
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
    	Robot.logger.info("RobotDrive", "Left Encoder = " + Robot.drivetrain.getLeftEncoder());
    	Robot.logger.info("RobotDrive", "Right Encoder = " + Robot.drivetrain.getRightEncoder());
    	
    	//Get the joystick values
    	joystickX = Robot.oi.getGamepad1().getX();
        joystickY = Robot.oi.getGamepad1().getY();
        
        //Determine the powers based on the joystick values, cubic for side to side
        leftPower = (Math.abs(joystickY) * joystickY) - (joystickX * joystickX * joystickX);
        rightPower = (Math.abs(joystickY) * joystickY) + (joystickX * joystickX * joystickX);
        
        //Reduce the acceleration if the button is held down
        if (Robot.oi.getButton(Robot.oi.getGamepad1(), 5)) {
        	Robot.logger.info("RobotDrive", "Button 5 Held");
        	
        	//If the left power has increased by more than the max
        	if (leftPower - lastLeftPower > MAX_DECCELERATION_SPEED) {
        		
        		//Manually set the power so the power does not change so quickly
        		leftPower = lastLeftPower + MAX_DECCELERATION_SPEED;
        	
        	//If the left power has decreased by more than the max
        	} else if (lastLeftPower - leftPower > MAX_DECCELERATION_SPEED) {
        		
        		//Manually set the power so the power does not change so quickly
        		leftPower = lastLeftPower - MAX_DECCELERATION_SPEED;
        	}
        	
        	//If the right power has increased by more than the max
        	if (rightPower - lastRightPower > MAX_DECCELERATION_SPEED) {
        		
        		//Manually set the power so the power does not change so quickly
        		rightPower = lastRightPower + MAX_DECCELERATION_SPEED;
        		
        	//If the right power has decreased more than the max
        	} else if (lastRightPower - rightPower > MAX_DECCELERATION_SPEED) {
        		
        		//Manually set the power so the power does not change so quickly
        		rightPower = lastRightPower - MAX_DECCELERATION_SPEED;
        	}
        	
        } else if (Robot.oi.getButton(Robot.oi.getGamepad1(), 6)) {
        	Robot.logger.info("RobotDrive", "Button 6 Held");
        	
        	leftPower *= POWER_REDUCTION;
        	rightPower*= POWER_REDUCTION;
        }
        
        //Set the drivetrain motors
        Robot.drivetrain.setMotor("left", leftPower);
        Robot.drivetrain.setMotor("right", rightPower);
        
        lastLeftPower = leftPower;
        lastRightPower = rightPower;
        
        SmartDashboard.putNumber("Right Motor Power", rightPower);
        SmartDashboard.putNumber("Left Motor Power", leftPower);
        SmartDashboard.putNumber("Current through 0", current0);
        SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoder());
        SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoder());
        SmartDashboard.putNumber("Lifter Height", Robot.pickup.getLifterHeight());
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
