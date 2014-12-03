// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc4678.DriveBase.subsystems;
import org.usfirst.frc4678.DriveBase.RobotMap;
import org.usfirst.frc4678.DriveBase.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class logger extends Subsystem {
    
    //This string determines what print statements are shown
    String level = "info";
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //You call this function to print out a debug print statement
    public void debug (String className, String message) {
        
        //Print if the current level is debug
        if (level.equals("debug")) {
            System.out.println("D:" + System.currentTimeMillis() + " : " + className + " : " + message);
        }
    }
    
    //You call this function to print out a info print statement
    public void info (String className, String message) {
        
        //Print if the current level is info
        if (level.equals("info")) {
            System.out.println("I:" + System.currentTimeMillis() + " : " + className + " : " + message);
        }
    }
    
    //You call this function to print out a warning print statement
    public void warning (String className, String message) {
        
        //Print if the current level is warning
        if (level.equals("warning")) {
            System.out.println("W:" + System.currentTimeMillis() + " : " + className + " : " + message);
        }
    }
    
    //You call this function to print out a error print statement
    public void error (String className, String message) {
        
        //Print if the current level is error
        if (level.equals("error")) {
            System.out.println("E:" + System.currentTimeMillis() + " : " + className + " : " + message);
        }
    }
    
    public void setLevel (String level) {
        
    }
}
