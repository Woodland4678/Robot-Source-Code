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
	double averagePower = 0;
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
    	Robot.pickup.setLifterPower(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Get the joystick values
    	joystickX = -Robot.oi.getGamepad1().getX();
        joystickY = Robot.oi.getGamepad1().getY();
        
        //Determine the powers based on the joystick values, cubic for side to side
        leftPower = (Math.abs(joystickY) * joystickY) - (Math.abs(joystickX) * joystickX);
        rightPower = (Math.abs(joystickY) * joystickY) + (Math.abs(joystickX) * joystickX);
        
//--------------------------------------------------------------------------
//-------------------------------Steering Snap------------------------------
//--------------------------------------------------------------------------
        
        SmartDashboard.putBoolean("Drivetrain Steering Snap", Robot.oi.getButton(Robot.oi.getGamepad1(), 8));
        
        if (Robot.oi.getButton(Robot.oi.getGamepad1(), 8)) {
        	//Average the powers so you make the motors go the same speed
        	averagePower = ((Math.abs(leftPower) + Math.abs(rightPower)) / 2);
        	System.out.println("------- average power = " + averagePower);
        	//Flip the powers if necessary
        	if (leftPower > 0) {
        		leftPower = averagePower;
        	} else {
        		leftPower = -averagePower;
        	}
        	
        	//Flip the powers if necessary
        	if (rightPower > 0) {
        		rightPower = averagePower;
        	} else {
        		rightPower = -averagePower;
        	}
        }
        
//--------------------------------------------------------------------------
//--------------------------------Gentle Mode-------------------------------
//--------------------------------------------------------------------------
        
        SmartDashboard.putBoolean("Drivetrain Gentle Mode", Robot.drivetrain.getGentleMode());
        
        if (Robot.drivetrain.getGentleMode()) {
        	leftPower *= POWER_REDUCTION;
        	rightPower *= POWER_REDUCTION;
        	
        	//If the left power has changed by more than the max
        	if (Math.abs(leftPower - lastLeftPower) > MAX_DECCELERATION_SPEED) {
        		if (leftPower > lastLeftPower) {
        			//Manually set the power so the power does not change so quickly
        			leftPower = lastLeftPower + MAX_DECCELERATION_SPEED;
        			
        		} else {
        			//Manually set the power so the power does not change so quickly
        			leftPower = lastLeftPower - MAX_DECCELERATION_SPEED;
        		}
        	}
        	
        	//If the left power has changed by more than the max
        	if (Math.abs(rightPower - lastRightPower) > MAX_DECCELERATION_SPEED) {
        		if (rightPower > lastRightPower) {
        			//Manually set the power so the power does not change so quickly
        			rightPower = lastRightPower + MAX_DECCELERATION_SPEED;
        			
        		} else {
        			//Manually set the power so the power does not change so quickly
        			rightPower = lastRightPower - MAX_DECCELERATION_SPEED;
        		}
        	}
        	
        }
        	
//--------------------------------------------------------------------------
//------------------------------Power Reduction-----------------------------
//--------------------------------------------------------------------------
        
        SmartDashboard.putBoolean("Drivetrain Power Reduction", Robot.oi.getButton(Robot.oi.getGamepad1(), 5));
        
        if (Robot.oi.getButton(Robot.oi.getGamepad1(), 5)) {
        	leftPower *= POWER_REDUCTION;
        	rightPower*= POWER_REDUCTION;
        }
        
//--------------------------------------------------------------------------
//---------------------------------Set Powers-------------------------------
//--------------------------------------------------------------------------

        Robot.drivetrain.setMotor("left", -leftPower);
        Robot.drivetrain.setMotor("right", -rightPower);
        
        lastLeftPower = leftPower;
        lastRightPower = rightPower;
        
        SmartDashboard.putNumber("Right Motor Power", rightPower);
        SmartDashboard.putNumber("Left Motor Power", leftPower);
        SmartDashboard.putNumber("Right Encoder", Robot.drivetrain.getRightEncoder());
        SmartDashboard.putNumber("Left Encoder", Robot.drivetrain.getLeftEncoder());
        SmartDashboard.putNumber("Lifter Height", Robot.pickup.getLifterHeight());
        SmartDashboard.putNumber("Arm Position", Robot.arm.getArmPosition());
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
