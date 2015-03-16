// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc4678.walle.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4678.walle.Robot;

/**
 *
 */
public class  AutonomousCommand extends Command {
	boolean liftUpLess = false;
	boolean finished = false;
	boolean pickingUpTote = false;
	int autoState = 0;
	int pickupState = 0; // was 0
	int armState = 0;
	int armCount = 0;
	int indexState = 0;
	int count = 0;
	int autoMode = Robot.autoMode();
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	autoState = 0;
    	autoMode = Robot.autoMode();
    	count = 0;
    	pickupState = 5; //was 5
    	armState = 5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Auto state is " + autoState);
//--------------------------------------------------------------------------
//------------------------------move 2 meters-------------------------------
//--------------------------------------------------------------------------
    	
    	if (autoMode == 0) {
    		switch(autoState) {
    		case 0://Get the pickup ready for the match and move forwards
    			pickupState = 5;
	    		if (Robot.drivetrain.goToDistance(180, 180, .7, 30, 20, 0.5, 0.2)) {
	    			autoState ++;
	    		}
	    	break;
    		}
    	

//--------------------------------------------------------------------------
//----------------------------1 tote 2 containers---------------------------
//--------------------------------------------------------------------------
    	
    	} else if (autoMode == 1) {
	    	switch(autoState) {
	    	case 0://Pick up the first bin(s) and tote (after this, the pickup will automatically check for totes and pick them up)
	    		armCount = 0;
	    		pickupState = 3;
	    		indexState = 0;
	    		liftUpLess = false;
	    		armState = 0;
	    		count = 0;
	    		autoState ++;
	    		Robot.indexWheels.setIndexMotor(1);
	    		pickupState = 2;
	    	break;
	    	case 1://Wait for the Robot to pick up the bin and tote
	    		if (pickupState == 3 || pickupState == 0) {
		    		count ++;
		    		if (count > 60) {
		    			autoState ++;
		    		}
	    		}
	    	break;
	    	case 2://Turn to face the auto zone
	    		if (Robot.drivetrain.turn(90, 0.7)) {
	    			autoState ++;
	    			count = 0;
	    		}
	    	break;
	    	case 3://Move into the auto zone
	    		if (Robot.drivetrain.goToDistance(200, 200, .85, 20, 60, 0.5, 0.2)) {
	    			autoState ++;
	    			count = 0;
	    		}
	    	break;
	    	case 4://Turn to be parallel to the scoring platform, and start dropping the totes
	    		if (Robot.drivetrain.turn(-90, 0.7)) {
	    			autoState ++;
	    			pickupState = 6;
	    			count = 0;
	    		}
	    	break;
	    	case 10://Wait for the totes to be dropped
	    		count ++;
	    		if (count > 80) {
	    			autoState ++;
	    		}
	    	break;
	    	case 11://Go forwards
	    		if (Robot.drivetrain.goToDistance(120, 120, .6, 10, 10, 0.5, 0.2)) {
	    			autoState ++;
	    		}
	    	case 12://Get the pickup ready for the match
	    		pickupState = 5;
	    	break;
	    	}
	    	
//--------------------------------------------------------------------------
//--------------------------3 totes and containers--------------------------
//--------------------------------------------------------------------------
	    	
    	} else if (autoMode == 3) {
	    	switch(autoState) {
	    	case 0://Pick up the first bin(s) and tote (after this, the pickup will automatically check for totes and pick them up)
	    		armCount = 0;
	    		pickupState = 8;
	    		indexState = 0;
	    		liftUpLess = false;
	    		armState = 0;
	    		count = 0;
	    		autoState ++;
	    		Robot.indexWheels.setIndexMotor(1);
	    		
	    	break;
	    	case 1://Wait for the Robot to pick up the bin and tote
	    		count ++;
	    		if (count > 60) {
	    			autoState ++;
	    		}
	    	break;
	    	case 2://Move forwards to the next bin
	    		if (Robot.drivetrain.goToDistance(205, 205, .5, 30, 40, 0.5, 0.15)) {
	    			autoState ++;
	    			armState = 0;
	    			count = 0;
	    		}
	    	break;
	    	case 3://Wait for the robot to pick up the container
	    		count ++;
	    		if (armState == 2) {//This ensures the arm goes to rest, and not to drop the bin
	    			armState = 5;
	    		}
	    		
	    		if (count > 70) {
	    			autoState ++;
	    		}
	    	break;
	    	case 4://Move forwards to the last tote //was 220 not 175
	    		if (Robot.drivetrain.goToDistance(175, 175, .35, 10, 10, 0.5, 0.1)) {
	    			autoState ++;
	    			armState = 5;
	    			count = 0;
	    		}
	    	break;
	    	case 5://Wait for the robot to stop pick up the container
	    		count ++;
	    		if (count > 25) {
	    			autoState ++;
	    			pickupState = 5;
	    		}
	    	break;
	    	case 6://Turn to face the platform
	    		if (Robot.drivetrain.turn(50, 0.5)) {
	    			autoState ++;
	    			count = 0;
	    		}
	    	break;
	    	case 7:
	    		count ++;
	    		if (count > 20) {
	    			autoState ++;
	    		}
	    	break;
	    	case 8://Go over the platform
	    		if (Robot.drivetrain.goToDistance(235, 235, .45, 40, 0, .4, 0)) {
	    			autoState ++;
	    		}
	    	break;
	    	case 9://Turn 45 degrees //changed right from 135 
	    		if (Robot.drivetrain.goToDistance(210, 85, .45, 30, 0, 0, 0)) {
	    			autoState ++;
	    			count = 0;
	    			Robot.indexWheels.setIndexMotor(-1);
	    			Robot.indexWheels.setIndexWheels(Robot.indexOpenPosition());
	    			indexState = 2;
	    		}
	    	break;
	    	case 10: //stop then drop
	    		count++;
	    		if(count > 35) {
	    			autoState++;
	    		}
	    		pickupState = 6;
	    		
	    	break;
	    	case 11://Turn the last 45 degrees while dropping the totes (increased right from 165 to 185)
	    		Robot.logger.debug("Autonomous", "Dropping the totes, pickup at " + Robot.pickup.getLifterHeight());
	    		if (Robot.drivetrain.goToDistance(100, 100, .5, 0, 0, 0, 0)) {
	    			autoState ++;
	    			pickupState = 6;
	    			count = 0;
	    		}
	    	break;
	    	/*case 12://Go back a bit to make sure the totes are clear
	    		if (Robot.drivetrain.goToDistance(50, 50, .85, 0, 0, 0, 0)) {
	    			autoState ++;
	    			count = 0;
	    		}
	    	break;*/
	    	case 12: 
	    		Robot.frontforks.liftForks();
	    		count++;
	    		if (count > 25) {
	    			Robot.drivetrain.setMotor("both", 0);
	    			//autoState++;
	    		}
	    		
	    		
	    	}
	    	
    	}
    	
//--------------------------------------------------------------------------
//-------------------------------Pickup setter------------------------------
//--------------------------------------------------------------------------
    	
    	//This code automatically sets the pickup height based on the pickupState
    	//States 0-3 automatically pick up a tote when it is under the robot, and
    	//State 4 makes the pickup ready to pick up
    	//State 5 lifts the pickup to the max height
    	//State 6 lowers the pickup to the minimum height
    	//State 7 lowers the pickup to the minimum height without scoring the totes
    	switch(pickupState) {
    	
    	case 0://Wait for a tote to be sensed
    		if (Robot.pickup.getDrivingOverTote()) {
    			pickupState ++;
    		}
    	break;
    	case 1://Wait for the tote to pass under the robot
    		if (!Robot.pickup.getDrivingOverTote() && Robot.pickup.getToteSensor1()) {
    			pickupState ++;
    		}
    	break;
    	case 2://Drop the pickup down
    		if (Robot.pickup.lift(Robot.lifterPickupTarget())) {
    			pickupState ++;
    			if (liftUpLess) {
    				pickupState = 7;
    			}
    		}
    	break;
    	case 3://Lift the pickup up, and then go back to state 0
    		
    		if (Robot.pickup.lift(Robot.lifterUpperTarget())) {
    			pickupState = 0;
    		}
    	break;
    	case 4://Go to the pickup target
    		Robot.pickup.lift(Robot.lifterPickupTarget());
    	break;
    	case 5://Go to the pickup max
    		Robot.pickup.lift(Robot.lifterUpperTarget());
    	break;
    	case 6://Go to the pickup min
    		Robot.pickup.lift(Robot.lifterScoreTarget());
    	break;
    	case 7://Go as low as the pickup can go without scoring
    		Robot.pickup.lift(Robot.lifterSixToteTarget());
    	break;
    	case 8:
    		if (Robot.pickup.lift(Robot.lifterPickupTarget())) {
    			pickupState = 3;
    		}
    	break;
    	}
    	
//--------------------------------------------------------------------------
//-------------------------------Arm setter------------------------------
//--------------------------------------------------------------------------
    	System.out.println("Arm state " + armState);
    	//When the pickup state is 0, it goes through the pickup loop
    	switch(armState) {
    	case 0://go to pickup position and open the claw
    		Robot.squeeze.openArm(Robot.armOpenPosition());
    		Robot.arm.setCurrentArmPosition(Robot.armPickupPosition() + 0.7);
    		if (Math.abs(Robot.arm.getArmPosition() - Robot.armPickupPosition()) < 0.75) {
    			armCount = 0;
    			armState ++;
    		}
    	break;
    	case 1://Close the claw
    		armCount ++;
    		Robot.squeeze.openArm(Robot.armClosePosition());
    		if (armCount > 10) {//Wait for the claw to close
    			Robot.claw.setClawTargetDegrees(15);
    			if (armCount > 20) {
    				armState ++;
    			}
    		}
    	break;
    	case 2://Go to set bin position
    		if (Robot.armZeroDegreesValue() < Robot.arm.getArmPosition() && Robot.claw.getClawTargetDegrees() > 0) {//Gradually reduce the bin angle
    			Robot.claw.setClawTargetDegrees(Robot.claw.getClawTargetDegrees() - 1);
    		}
    		
    		Robot.arm.setCurrentArmPosition(Robot.armSetBinPosition());
    		Robot.squeeze.openArm(Robot.armClosePosition());
    		if (Math.abs(Robot.armSetBinPosition() - Robot.arm.getArmPosition())  < 0.05) {
    			armCount = 0;
    			armState ++;
    		}
    	break;
    	case 3://Open the claw
    		armCount ++;
    		if (armCount > 15) {
				Robot.squeeze.openArm(Robot.armOpenPosition());
				if (armCount > 30) {
					armState ++;
				}
    		}
    	break;
    	case 4://Go to pickup position
    		Robot.squeeze.openArm(Robot.armOpenPosition());
    		Robot.arm.setCurrentArmPosition(Robot.armPickupPosition());
    	break;
    	case 5://Go to rest position
    		Robot.claw.setClawTargetDegrees(0);
    		Robot.squeeze.openArm(Robot.armClosePosition());
    		Robot.arm.setCurrentArmPosition(Robot.armRestPosition() - 0.5);
    	break;
    	case 6://Go to the bin setting position
    		Robot.arm.setCurrentArmPosition(Robot.armSetBinPosition());
    	break;
    	}
   //Index wheel setter
    	switch (indexState) {
    	case 0:
    		Robot.indexWheels.setIndexWheels(Robot.indexClosePosition());
    		break;
    	case 1:
    		Robot.indexWheels.setIndexWheels(Robot.indexCenterPosition());
    		break;
    	case 2:
    		Robot.indexWheels.setIndexWheels(Robot.indexOpenPosition());
    		break;
    	}

    	Robot.logger.debug("Autonomous", "Pickup state at " + pickupState + " Latch pressed is " + Robot.pickup.getDrivingOverTote());

    	Robot.logger.debug("Autonomous", "Target claw degrees at " + Robot.claw.getClawTargetDegrees());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	autoState = 0;
    	Robot.pickup.setLifterPower(0);
    	Robot.drivetrain.setMotor("both", 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	autoState = 0;
    	Robot.pickup.setLifterPower(0);
    	Robot.drivetrain.setMotor("both", 0);
    }
}
