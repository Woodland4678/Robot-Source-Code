// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc4678.GLaDOS.subsystems;
import org.usfirst.frc4678.GLaDOS.RobotMap;
import org.usfirst.frc4678.GLaDOS.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc4678.GLaDOS.Robot;
/**
 *
 */
public class pickup extends Subsystem {
    String mode = "defence";
    int holdState = 0;
    boolean goToHold = true;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController pickupWheels = RobotMap.pickuppickupWheels;
    AnalogChannel armPot = RobotMap.pickuparmPot;
    SpeedController pickupArm = RobotMap.pickuppickupArm;
    AnalogChannel armOffset = RobotMap.pickuparmOffset;
    DigitalInput pickupLowerLimit = RobotMap.pickuppickupLowerLimit;
    DigitalInput isHoldingBall = RobotMap.pickupisHoldingBall;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    double pickupArmDefault = Robot.armDefaultValue();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new setPickupArm());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    boolean btnPressed;
    public boolean setPickupArm(double potValue) {                              //Set the pickup arm to the requested height      
        double error;
        double power;
        double margin = 0;
        error = potValue - getPickupArmAngle();                                 //Make the error the goal pot minus the current pot
        Robot.debug.print("pickup: setPickupArm pickup at " + getPickupArmAngle(), 2);
        Robot.debug.print("pickup: setPickupArm goal pickup is " + potValue, 2);
        power = error / 15;
        if (power > 1.0) {                                                      //Cap the power at 1
            power = 1.0;
        } else if (power < -1.0) {
            power = -1.0;
        }
        if (((Robot.armLowLimit() + Robot.armMinReductionValue()) > Robot.pickup.getPickupArmAngle()) && ((Robot.armLowLimit() + Robot.armMaxReductionValue()) < Robot.pickup.getPickupArmAngle()) && (Robot.pickup.getMode() == "pickup")) {
            power = power * 0.6;
            Robot.debug.print("pickup: goToDistance within range, reducing power", 2);
        }
        setPickupArmPower(power * Robot.armMaxPower());
        Robot.debug.print("pickup: goToDistance power is: " + (power * Robot.armMaxPower()), 2);
        if (Math.abs(error) < margin) {                                         //Determine if the robot has reached the goal distance
            setPickupArmPower(0);
            return true;
        } else {
            return false;
        }
    }
    public int getHoldState() {
        return holdState;
    }
    public void setHoldState(int newHoldState) {
        holdState = newHoldState;
    }
    public void setGoToHold(boolean value) {
        goToHold = value;
    }
    public boolean getGoToHold() {
        return goToHold;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String newMode) {
        mode = newMode;
    }
    public boolean getIsHoldingBall() {
        return !isHoldingBall.get();
    }
    public boolean getPickupLowerLimit() {
        return !pickupLowerLimit.get();
    }
    public double getPickupArmAngle() {
        return armPot.getValue();
    }
    public double getPickupArmOffset() {
        return armOffset.getValue();
    }
    public void setPickupArmPower(double power) {
        pickupArm.set(power);
    }
    public void setPickupWheelPower(double power) {
        pickupWheels.set(power);
    }
    public double pickupArmDefault() {
        return pickupArmDefault;
    }
    public void setPickupArmDefault(double newPickupArmDefault) {
        pickupArmDefault = newPickupArmDefault;
    }
    public double getGoalArmHeight() {
        return (Robot.armOffsetCenter() - getPickupArmOffset());
    }
}