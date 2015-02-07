// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc4678.walle;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc4678.walle.commands.*;
import org.usfirst.frc4678.walle.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drivetrain drivetrain;
    public static Pickup pickup;
    public static Logger logger;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public static double lifterPower() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterPower")) {
            prefs.putDouble("lifterPower", 1);
        }
        return prefs.getDouble("lifterPower", 1);
    }
    
    public static double lifterUpperTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterUpperTarget")) {
            prefs.putDouble("lifterUpperTarget", 5.3);
        }
        return prefs.getDouble("lifterUpperTarget", 5.3);
    }
    
    public static double lifterLowerTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterLowerLimit")) {
            prefs.putDouble("lifterLowerLimit", 1);
        }
        return prefs.getDouble("lifterLowerLimit", 1);
    }
    
    public static double lifterDroppedTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterDroppedTarget")) {
            prefs.putDouble("lifterDroppedTarget", 0.7);
        }
        return prefs.getDouble("lifterDroppedTarget", 0.7);
    }
    
    public static double encoderClicksPerCentimeter() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("encoderClicksPerCentimeter")) {
            prefs.putDouble("encoderClicksPerCentimeter", 7.2);
        }
        return prefs.getDouble("encoderClicksPerCentimeter", 7.2);
    }
    
    public static int encoderChangePerTurn() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("encoderChangePerTurn")) {
            prefs.putInt("encoderChangePerTurn", 3000);
        }
        return prefs.getInt("encoderChangePerTurn", 3000);
    }
    
    public static String classLoggerEnabled() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("loggerClassEnabled")) {
            prefs.putString("loggerClassEnabled", "all");
        }
        return prefs.getString("loggerClassEnabled", "all");
    }

    public static double goToBoxTurnSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("goToBoxTurnSpeed")) {
            prefs.putDouble("goToBoxTurnSpeed", .05);
        }
        return prefs.getDouble("goToBoxTurnSpeed", .05);
    }
    
    public static int targetLightSensorValue() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("targetLightSensorValue")) {
            prefs.putInt("targetLightSensorValue", 400);
        }
        return prefs.getInt("targetLightSensorValue", 400);
    }
    
    public static int lightSensorMargin() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lightSensorMargin")) {
            prefs.putInt("lightSensorMargin", 50);
        }
        return prefs.getInt("lightSensorMargin", 50);
    }
    
    public static int autoTurnReductionSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("autoTurnReductionSpeed")) {
            prefs.putInt("autoTurnReductionSpeed", 2);
        }
        return prefs.getInt("autoTurnReductionSpeed", 2);
    }
    
    public static double maxDecelerationSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("maxDecelerationSpeed")) {
            prefs.putDouble("maxDecelerationSpeed", 0.02);
        }
        return prefs.getDouble("maxDecelerationSpeed", 0.02);
    }
    
    public static double driveMotorPowerReduction() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("driveMotorPowerReduction")) {
            prefs.putDouble("driveMotorPowerReduction", .5);
        }
        return prefs.getDouble("driveMotorPowerReduction", .5);
    }
    public static double servoLockPos() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("servoLockPos")) {
            prefs.putDouble("servoLockPos", 20);
        }
        return prefs.getDouble("servoLockPos", 20);
    }
    public static double servoRemoveLock() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("servoRemoveLock")) {
            prefs.putDouble("servoRemoveLock", 50);
        }
        return prefs.getDouble("servoRemoveLock", 50);
    }
    
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        pickup = new Pickup();
        logger = new Logger();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
