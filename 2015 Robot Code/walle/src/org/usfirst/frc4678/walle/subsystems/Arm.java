// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4678.walle.subsystems;

import org.usfirst.frc4678.walle.Robot;
import org.usfirst.frc4678.walle.RobotMap;
import org.usfirst.frc4678.walle.commands.*;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *
 */
public class Arm extends Subsystem {
	double currentArmPosition;
	double power;
	double error;
	int armState;
	double cnt;
	double armMaxPower;
	double armTarget;
	double lastArmTarget = -1;
	boolean armReady;
	double armPowerReduction = 0.8;
	double timedPowerReduction = 0.4;

	PowerDistributionPanel pdp = new PowerDistributionPanel();
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController armMotor = RobotMap.armArmMotor;
    AnalogPotentiometer armPosition = RobotMap.armArmPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new setArm());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setArmMotor(double power) {
    	armMotor.set(power);
    }
    
    public double getArmPosition() {
    	return armPosition.get();
    }
    
    public double getArmDegrees() {
    	double ticsPerDegree = (Robot.armNinetyDegreesValue() - Robot.armZeroDegreesValue()) / 90;
    	return (Robot.arm.getArmPosition() - Robot.armZeroDegreesValue()) / ticsPerDegree;
    }
    
    public boolean getArmReady() {
    	return armReady;	
    }
    
    public boolean setArm(double Target) {
    	power = Robot.armPower();
    	armMaxPower = Robot.armPower();
    	double armTarget = Target;
    	
    	if (lastArmTarget != Target) {
    		armReady = false;
    	}
    	lastArmTarget = Target;
    	//switch(armState) {
		//case 0:
	    	//Finds the difference between target and current position
			error = armTarget - armPosition.get();
			//if the difference is greater than 0 power should be full
			if (error > 1) {
				power = armMaxPower;
			}
			//if the difference is less than 0, power should be full in reverse
			else if (error < -1) {
				power = -armMaxPower;
			}
			else {
				power = error * 0.9;
				if (power > armMaxPower){
					power = armMaxPower;
				}
				else if (power < -armMaxPower) {
					power = -armMaxPower;
				}
			}
			if (Robot.oi.getGamepad2().getPOV() == -1) {
				if (getArmPosition() < Robot.armZeroDegreesValue() && power < 0) {
					power *= armPowerReduction;
				} else if (getArmPosition() > (Robot.armNinetyDegreesValue() - 1.5) && power > 0) {
					double reduction = (Robot.armSetBinPosition() - getArmPosition()) / 3;
					if (reduction > 1) {
						reduction = 1;
					} else if (reduction < 0.4) {
						reduction = 0.4;
					}
					power *= reduction;
				} else if (getArmPosition() < Robot.armZeroDegreesValue() && power > 0) {
					timedPowerReduction += 0.01;
					if (timedPowerReduction > 1) {
						timedPowerReduction = 1;
					}
					power *= timedPowerReduction;
				} else {
					timedPowerReduction = 0.2;
				}
			}
			
			
			armMotor.set(power);
			
			
			if (Math.abs(error) < 0.1) {
				System.out.println("its true");
				armReady = true;
				return true;
			}
			//break;
		//}
		return false;
    	
    }
    
    //Get and set the current arm position
    public void setCurrentArmPosition(double value) { currentArmPosition = value; }
    
    public double getCurrentArmPosition() { return currentArmPosition; }
    
    public void setClawArm() {
    	
    }
    public double getArmTarget() {
    	return armTarget;
    }
    public double getArmPower() {
    	return power;
    }
   
    public void setOpenPower(double power) {
    	//set the power
    }
    
   
    
}


