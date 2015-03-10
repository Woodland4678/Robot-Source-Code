// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc4678.walle.subsystems;

import org.usfirst.frc4678.walle.Robot;
import org.usfirst.frc4678.walle.RobotMap;
import org.usfirst.frc4678.walle.commands.*;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Drivetrain extends Subsystem {
	//start ramping down when you are this many centimeters from the target or from the start
	double powerReduction = 1;
	boolean robotDriving = false;
	double GO_TO_DISTANCE_CORRECTION_SPEED = 50;
	int AUTO_DRIVE_RAMP_DISTANCE = 30;
	int ENCODER_DIFFERENCE_PER_TURN = Robot.encoderChangePerTurn();
	int LIGHT_SENSOR_MARGIN = Robot.lightSensorMargin();
	double GO_TO_BOX_TURN_SPEED = Robot.goToBoxTurnSpeed();
	int TARGET_LIGHT_SENSOR_VALUE = Robot.targetLightSensorValue();
	double AUTO_TURN_MARGIN = .05;//This is a percentage
	double AUTO_TURN_REDUCTION_SPEED = Robot.autoTurnReductionSpeed();
	double AUTO_TURN_REDUCTION_DISTANCE = 0.6;//Starts reducing the speed when it is x percent of the way to the target distance
	long goalTime;
	int timedDriveState = 0;
	int goToDistanceState = 0;
	int turnState = 0;
	int startingLeftDistance;
	int startingRightDistance;
	boolean gentleModeOn = true;
	//GoToDistance variables
	double targetLeft;
	double targetRight;
	double currentLeft;
	double currentRight;
	double currentLeftCentimeters;
	double currentRightCentimeters;
	double leftPercentThere;
	double rightPercentThere;
	double leftMotorMultiplier;
	double rightMotorMultiplier;
	double powerOffset;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController leftMotor = RobotMap.drivetrainLeftMotor;
    SpeedController rightMotor = RobotMap.drivetrainRightMotor;
    Encoder leftEncoder = RobotMap.drivetrainLeftEncoder;
    Encoder rightEncoder = RobotMap.drivetrainRightEncoder;
    AnalogInput frontDistanceSensor = RobotMap.drivetrainFrontDistanceSensor;
    AnalogInput backDistanceSensor = RobotMap.drivetrainBackDistanceSensor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new RobotDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getPowerReduction() { return powerReduction; }
    
    public void setPowerReduction(double value) { powerReduction = value; }
    
    public void setMotor(String motor, double power) {
    	if (motor.equals("left") || motor.equals("Left")) {
    		//This is negative because the left gearbox is facing in the opposite direction
    		leftMotor.set(-power);
    	} else if (motor.equals("right") || motor.equals("Right")) {
    		rightMotor.set(power);
    	} else if (motor.equals("both") || motor.equals("Both")) {
    		//This is negative because the left gearbox is facing in the opposite direction
    		leftMotor.set(-power);
    		rightMotor.set(power);
    	}
    }
    
  //To use this method, you keep calling it until it returns true
    public boolean timedDrive(double leftPower, double rightPower, int milliseconds) {
    	Robot.logger.debug("Drivetrain", "Timed drive " + (goalTime - milliseconds) + " milliseconds from target");
    	//Goal time is -1 if it has not been set
    	if (timedDriveState == 0) {
    		goalTime = System.currentTimeMillis() + milliseconds;
    		timedDriveState ++;
    	}
    	
    	if (goalTime > System.currentTimeMillis()) {
    		Robot.drivetrain.setMotor("left", leftPower);
    		Robot.drivetrain.setMotor("right", rightPower);
    		
    	} else {
    		Robot.drivetrain.setMotor("both", 0);
    		timedDriveState = 0;
    		Robot.logger.info("Drivetrain", "Timed drive completed");
    		return true;
    	}
    	
    	return false;
    }
    
    //To use this method, you keep calling it until it returns true
    public boolean goToDistance(double rightCentimeters, double leftCentimeters, double power, int rampUpDistance, int rampDownDistance, double startingPower, double endingPower) {

//--------------------------------------------------------------------------
//-----------------------Reset variables if necessary-----------------------
//--------------------------------------------------------------------------
    	
    	//If this method is being called for the first time since it last finished, you want to record the initial encoder values
    	if (goToDistanceState == 0) {
    		goToDistanceState ++;
    		startingLeftDistance = getLeftEncoder();
    		startingRightDistance = getRightEncoder();
    		Robot.logger.info("Drivetrain", "goToDistance starting encoder values are " + getRightEncoder() + ", " + getLeftEncoder());
    	}

//--------------------------------------------------------------------------
//---------------------Get target and current distances---------------------
//--------------------------------------------------------------------------
    	
    	//Get target distance in centimeters
    	targetLeft = leftCentimeters * Robot.encoderClicksPerCentimeter();
    	targetRight = rightCentimeters * Robot.encoderClicksPerCentimeter();
    	
    	//Get the current distance in centimeters
    	currentLeft = Math.abs(getLeftEncoder() - startingLeftDistance);
    	currentRight = Math.abs(getRightEncoder() - startingRightDistance);
    	currentLeftCentimeters = currentLeft / Robot.encoderClicksPerCentimeter();
    	currentRightCentimeters = currentRight / Robot.encoderClicksPerCentimeter();
    	
    	//Find the percentage the left and right are to their target
    	leftPercentThere = Math.abs(currentLeft / targetLeft);
    	rightPercentThere = Math.abs(currentRight / targetRight);
    	//Robot.logger.debug("Drivetrain", "gpToDistance Percentages At " + rightPercentThere + ", " + leftPercentThere);
    	
    	//Initially set the powers to their default values
        leftMotorMultiplier = 1;
        rightMotorMultiplier = 1;

//--------------------------------------------------------------------------
//----------------Adjust powers if one side has gone farther----------------
//--------------------------------------------------------------------------
        
        //Difference between how far the left and right have gone
        powerOffset = GO_TO_DISTANCE_CORRECTION_SPEED * Math.abs(leftPercentThere - rightPercentThere);
        
        //Only start adjusting the powers once the motors have gone 2 percent of the target distance, to avoid calculation errors
        if (currentRight >= (targetRight * 0.02) && (currentLeft >= (targetLeft * 0.02))) {
            //If the right is closer than the left, increase the left power and decrease the right power
            if (rightPercentThere > (leftPercentThere + 0.001)) {
            	leftMotorMultiplier *= 1 + powerOffset;
                rightMotorMultiplier *= 1 - powerOffset;
            }
            
            //If the left is closer than the right, increase the right power, and decrease the left power
            if ((rightPercentThere + 0.001) < leftPercentThere) {
            	leftMotorMultiplier *= 1 - powerOffset;
                rightMotorMultiplier *= 1 + powerOffset;
            }
        }
        Robot.logger.debug("Drivetrain", "goToDistance percentages at " + (int)(rightPercentThere * 100) + ", " + (int)(leftPercentThere * 100) + " Power Offset At " + (((int)(1000 * powerOffset)) / 1000.0));

//--------------------------------------------------------------------------
//-----------------------Flip the powers if necessary-----------------------
//--------------------------------------------------------------------------
        
        //We use the absolute values for setting the powers, so we have to flip the powers based on what direction the robot is going
        if (targetRight < 0) {
        	//If the robot is trying to go backwards and has not passed the target
        	if (getRightEncoder() - startingRightDistance > targetRight) {
        		rightMotorMultiplier *= -1;
        	}
        } else {
        	//If the robot is trying to go forwards and has passed the target
        	if (getRightEncoder() - startingRightDistance > targetRight) {
        		rightMotorMultiplier *= -1;
        	}
        }
        
        if (targetLeft < 0) {
        	//If the robot is trying to go backwards and has not passed the target
        	if (getLeftEncoder() - startingLeftDistance > targetLeft) {
        		leftMotorMultiplier *= -1;
        	}
        } else {
        	//If the robot is trying to go forwards and has passed the target
        	if (getLeftEncoder() - startingLeftDistance > targetLeft) {
        		leftMotorMultiplier *= -1;
        	}
        }
        
//--------------------------------------------------------------------------
//-----------------------------Ramp Down Speeds-----------------------------
//--------------------------------------------------------------------------
        
        double rampDownPercentage = 1;
        if (currentRightCentimeters < rampUpDistance) {
        	rampDownPercentage = ((currentRightCentimeters / rampUpDistance) * (1 - startingPower)) + startingPower;
        	Robot.logger.info("Drivetrain", "goToDistance ramping down " + (int)(rampDownPercentage * 100) + "%");
        } else if (currentRightCentimeters > Math.abs(rightCentimeters) - rampDownDistance) {
        	rampDownPercentage = (((Math.abs(rightCentimeters) - currentRightCentimeters) / rampDownDistance) * (1 - endingPower)) + endingPower;
        	Robot.logger.info("Drivetrain", "goToDistance ramping down " + (int)(rampDownPercentage * 100) + "%");
        }
        
        Robot.logger.debug("Drivetrain", "goToDistance target is " + rightCentimeters + ", " + leftCentimeters + " current is " + (-(int)((getRightEncoder() - startingRightDistance) / Robot.encoderClicksPerCentimeter())) + ", " + (-(int)((getLeftEncoder() - startingLeftDistance) / Robot.encoderClicksPerCentimeter())));
    	
        Robot.drivetrain.setMotor("left", -leftMotorMultiplier * power * rampDownPercentage);
        Robot.drivetrain.setMotor("right", -rightMotorMultiplier * power * rampDownPercentage);
        Robot.logger.debug("Drivetrain", "goToDistance target is " + rightCentimeters + ", " + leftCentimeters + " current is " + (-(int)((getRightEncoder() - startingRightDistance) / Robot.encoderClicksPerCentimeter())) + ", " + (-(int)((getLeftEncoder() - startingLeftDistance) / Robot.encoderClicksPerCentimeter())));
    	
        //If the left and the right both have gone far enough stop the motors, and reset the goToDistanceState so that the next time
        //the method is called, it will record the starting encoder values again
        if (rightPercentThere >= 1 && leftPercentThere >= 1) {
        	setMotor("both", 0);
        	goToDistanceState = 0;
        	Robot.logger.info("Drivetrain", "goToDistance at target");
        	Robot.logger.info("Drivetrain", "goToDistance final encoder values are " + getRightEncoder() + ", " + getLeftEncoder());
        	return true;
        }
        
        return false;
    }
    
    //This function has to be constantly called until it returns true
    public boolean goToBox(double power) {
    	double leftPower = power;
    	double rightPower = power;
    	
    	Robot.logger.debug("Drivetrain", "goToBox light sensors at " + getRightLightSensor() + ", " + getLeftLightSensor());
    	//If the left is sensing and the right is not, turn left
    	if (getLeftLightSensor() > getRightLightSensor() + LIGHT_SENSOR_MARGIN) {
    		leftPower -= GO_TO_BOX_TURN_SPEED;
    		rightPower += GO_TO_BOX_TURN_SPEED;
    		
    	//If the right is sensing, and the left is not, turn right
    	} else if (getRightLightSensor() > getLeftLightSensor() + LIGHT_SENSOR_MARGIN) {
    		leftPower += GO_TO_BOX_TURN_SPEED;
    		rightPower -= GO_TO_BOX_TURN_SPEED;
    	}
    	
    	//Return true if the sensors are close enough to the target
    	if (getRightLightSensor() > TARGET_LIGHT_SENSOR_VALUE || getLeftLightSensor() > TARGET_LIGHT_SENSOR_VALUE) {
    		setMotor("both", 0);
    		Robot.logger.info("Drivetrain", "goToBox at box");
    		return true;
    	}
    	
    	Robot.logger.debug("Drivetrain", "goToDistance setting powers to " + rightPower + ", " + leftPower);
    	setMotor("left", leftPower);
    	setMotor("right", rightPower);
    	return false;
    }
    
    //This function has to be constantly called until it returns true
    public boolean turn(int degrees, double power) {
    	double leftPower = power;
    	double rightPower = power;
    	//Set the starting values if this is the first time the function is being called
    	if (turnState == 0) {
    		startingLeftDistance = getLeftEncoder();
    		startingRightDistance = getRightEncoder();
    		turnState ++;
    	}
    	
    	//Determine the difference there should be between the encoders when the robot has completed the turn
    	int goalDifference = (ENCODER_DIFFERENCE_PER_TURN * degrees);
    	goalDifference = goalDifference / 360;
    	
    	if (goalDifference == 0) {
    		goalDifference = 1;
    		Robot.logger.warning("Drivetrain", "turn goalDifference is 0");
    	}
    	
    	int currentDifference = Math.abs((getLeftEncoder() - startingLeftDistance) - (getRightEncoder() - startingRightDistance));
    	int percentThere = (currentDifference / goalDifference);
    	Robot.logger.debug("Drivetrain", "turn " + percentThere + "% through the turn");
    	
    	//If the robot has overshot, do not start increasing the speed
    	if (percentThere > 1) {
    		percentThere = 1 - (percentThere - 1);
    	}
    	
    	//Start reducing speed if the robot has passed the reduction distance
    	if (percentThere > AUTO_TURN_REDUCTION_DISTANCE) {
    		double reduction = (1 - ((percentThere - AUTO_TURN_REDUCTION_DISTANCE) * AUTO_TURN_REDUCTION_SPEED));
    		if (reduction > 1) {
    			reduction = 1;
    		} else if (reduction < 0.2) {
    			reduction = 0.2;
    		}
    		
    		leftPower *= reduction;
    		rightPower *= reduction;
    		Robot.logger.debug("Drivetrain", "turn reducing power by %" + (int)(reduction * 100));
    	}
    	
    	//If the robot is within the margin, stop the motors and return true
    	if (Math.abs(percentThere - 1) < AUTO_TURN_MARGIN) {
    		setMotor("both", 0);
    		turnState = 0;
    		Robot.logger.info("Drivetrain", "turn completed turn");
    		return true;
    	}
    	
    	//Invert the powers if necessary
    	if (degrees > 0) {
    		if (currentDifference < goalDifference) {
    			//If the robot is turning right and has not hit the target
    			leftPower *= -1;
    		} else {
    			//If the robot is turning right and has gone too far
    			rightPower *= -1;
    		}
    	} else {
    		if (currentDifference > goalDifference) {
    			//If the robot is turning left and has not hit the target
    			rightPower *= -1;
    		} else {
    			//If the robot is turning left and has gone too far
    			leftPower *= -1;
    		}
    	}
    	
    	setMotor("left", -leftPower);
    	setMotor("right", -rightPower);
    	Robot.logger.debug("Drivetrain", "turn setting powers to " + rightPower + ", " + leftPower);
    	
    	return false;
    }
    
    public boolean getGentleMode() {return gentleModeOn; }
    
    public void setGentleMode(boolean value) {gentleModeOn = value; }  
    
    public int getLeftLightSensor() {return 0;}
    
    public int getRightLightSensor() {return 0;}
    
    public int getLeftEncoder() {return -leftEncoder.get();}
    
    public int getRightEncoder() {return rightEncoder.get();}
    
    public boolean isDriving() {return robotDriving; }
    
    public void setIsDriving(boolean value) {robotDriving = value; }
    
    public double getFrontDistanceSensor() { return frontDistanceSensor.getVoltage();}
    
    public double getBackDistanceSensor() { return backDistanceSensor.getVoltage();}
    
    
}

