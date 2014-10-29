// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc4678.DriveBase.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4678.DriveBase.Robot;
/**
 *
 */
public class  robotDrive extends Command {
    double joystickX;
    double joystickY;
    double leftPower;
    double rightPower;
    
    
    public robotDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.logger.info("robotDrive", "initialized");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        joystickX = Robot.oi.getgamepad1().getX();
        joystickY = Robot.oi.getgamepad1().getY();
        leftPower = (Math.abs(joystickY) * joystickY) - (joystickX * joystickX * joystickX);
        rightPower = (Math.abs(joystickY) * joystickY) + (joystickX * joystickX * joystickX);
        
        if (Robot.oi.getButton1()) {
            leftPower *= 0.7;
            rightPower *= 0.7;
        }
        
        if (Robot.invertDrive()) {
            leftPower *= -1;
            rightPower *= -1;
        }
        
        Robot.drivetrain.setLeftMotor(leftPower);
        Robot.drivetrain.setRightMotor(rightPower);
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
