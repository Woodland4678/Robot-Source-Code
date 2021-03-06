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
    public static Arm arm;
    public static IndexWheels indexWheels;
    public static Claw claw;
    public static squeeze squeeze;
    public static Frontforks frontforks;
    public static TrashMagnet trashMagnet;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public static double pickupPotOffset() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("pickupPotOffset")) {
            prefs.putDouble("pickupPotOffset", 0);
        }
        return prefs.getDouble("pickupPotOffset", 0);
    }
    
    public static double armPotOffset() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armPotOffset")) {
            prefs.putDouble("armPotOffset", 0);
        }
        return prefs.getDouble("armPotOffset", 0);
    }
    
    public static int autoMode() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("autoMode")) {
            prefs.putInt("autoMode", 1);
        }
        return prefs.getInt("autoMode", 1);
    }
    
    public static double armZeroDegreesValue() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armZeroDegreesValue")) {
            prefs.putDouble("armZeroDegreesValue", 3.4);
        }
        return prefs.getDouble("armZeroDegreesValue", 3.4);
    }
    
    public static double armNinetyDegreesValue() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armNinetyDegreesValue")) {
            prefs.putDouble("armNinetyDegreesValue", 7.75);
        }
        return prefs.getDouble("armNinetyDegreesValue", 7.75);
    }
    
    public static double clawTicsPerDegree() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("clawTicsPerDegree")) {
            prefs.putDouble("clawTicsPerDegree", 1.38);
        }
        return prefs.getDouble("clawTicsPerDegree", 1.38);
    }
    
    //**************************************************************//
    //*********Begin Parameters relating to the DRIVETRAIN*********//
    //************************************************************//

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
    //*********************************************************//
    //*********Begin Parameters relating to the PICKUP*********//
    //*********************************************************//
    public static double servoLockPos() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("servoLockPos")) {
            prefs.putDouble("servoLockPos", 180);
        }
        return prefs.getDouble("servoLockPos", 180);
    }
    public static double servoRemoveLock() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("servoRemoveLock")) {
            prefs.putDouble("servoRemoveLock", 0);
        }
        return prefs.getDouble("servoRemoveLock", 0);
    }
    public static double lifterUpperTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterUpperTarget")) {
            prefs.putDouble("lifterUpperTarget", 5.6);
        }
        return prefs.getDouble("lifterUpperTarget", 5.6);
    }
    
    public static double lifterPickupTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterPickupTarget")) {
            prefs.putDouble("lifterPickupTarget", 2.07);
        }
        return prefs.getDouble("lifterPickupTarget", 2.07);
    }
    
    public static double lifterScoreTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterScoreTarget")) {
            prefs.putDouble("lifterScoreTarget", 1.52);
        }
        return prefs.getDouble("lifterScoreTarget", 1.52);
    }
    public static double lifterSixToteTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterSixToteTarget")) {
            prefs.putDouble("lifterSixToteTarget", 3);
        }
        return prefs.getDouble("lifterSixToteTarget", 3);
    }
    public static double lifterPower() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("lifterPower")) {
            prefs.putDouble("lifterPower", 1);
        }
        return prefs.getDouble("lifterPower", 1);
    }
    
    //********************************************************//
    //*********Begin Parameters relating to the ARM**********//
    //******************************************************//
    
    public static double armPower() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armPower")) {
            prefs.putDouble("armPower", 0.7);
        }
        return prefs.getDouble("armPower", 0.7);
    }
    public static double armPickupPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armPickupPosition")) {
            prefs.putDouble("armPickupPosition", 2);
        }
        return prefs.getDouble("armPickupPosition", 2);
    }
    public static double armSetBinPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armSetBinPosition")) {
            prefs.putDouble("armSetBinPosition", 8.3);
        }
        return prefs.getDouble("armSetBinPosition", 8.3);
    }
    public static double armRestPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armRestPosition")) {
            prefs.putDouble("armRestPosition", 7);
        }
        return prefs.getDouble("armRestPosition", 7);
    }

    public static double fourStackHeight() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("fourStackHeight")) {
            prefs.putDouble("fourStackHeight", 6.5);
        }
        return prefs.getDouble("fourStackHeight", 6.5);
    }
    public static double armMaxOpenPower() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armMaxOpenPower")) {
            prefs.putDouble("armMaxOpenPower", 0.7);
        }
        return prefs.getDouble("armMaxOpenPower", 0.7);
    }
    public static double armOpenPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armOpenPosition")) {
            prefs.putDouble("armOpenPosition", .75);
        }
        return prefs.getDouble("armOpenPosition", .75);
    }
    public static double armClosePosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("armClosePosition")) {
            prefs.putDouble("armClosePosition", 0.285);
        }
        return prefs.getDouble("armClosePosition", 0.285);
    }
    //********************************************************//
    //*********Begin Parameters relating to the CLAW*********//
    //******************************************************//
    public static double clawMaxPower() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("clawMaxPower")) {
            prefs.putDouble("clawMaxPower", 1);
        }
        return prefs.getDouble("clawMaxPower", 1);
    }
    public static double clawPickUprightPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("clawPickUprightPosition")) {
            prefs.putDouble("clawPickUprightPosition", 0);
        }
        return prefs.getDouble("clawPickUprightPosition", 0);
    }
    public static double clawPickDownPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("clawPickDownPosition")) {
            prefs.putDouble("clawPickDownPosition", 50);
        }
        return prefs.getDouble("clawPickDownPosition", 50);
    }
    public static double clawDropBinPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("clawDropBinPosition")) {
            prefs.putDouble("clawDropBinPosition", 75);
        }
        return prefs.getDouble("clawDropBinPosition", 75);
    }
    //**************************************************************//
    //*********Begin Parameters relating to the indexWheels*********//
    //**************************************************************//
    public static double indexClosePosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("indexClosePosition")) {
            prefs.putDouble("indexClosePosition", 0.2);
        }
        return prefs.getDouble("indexClosePosition", 0.2);
    }
    public static double indexOpenPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("indexOpenPosition")) {
            prefs.putDouble("indexOpenPosition", 0.8);
        }
        return prefs.getDouble("indexOpenPosition", 0.8);
    }
    public static double indexCenterPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("indexCenterPosition")) {
            prefs.putDouble("indexCenterPosition", 0.5);
        }
        return prefs.getDouble("indexCenterPosition", 0.5);
    }
  //********************************************************//
    //*********Begin Parameters relating to the garbage magnet**//
    //******************************************************//
    public static double latchOpenPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("latchOpenPosition")) {
            prefs.putDouble("latchOpenPosition", 0);
        }
        return prefs.getDouble("latchOpenPosition", 0);
    }
    public static double latchClosedPosition() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("latchClosedPosition")) {
            prefs.putDouble("latchClosedPosition", 180);
        }
        return prefs.getDouble("latchClosedPosition", 180);
    }
    public static double garbageMagnetSpeed() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("garbageMagnetSpeed")) {
            prefs.putDouble("garbageMagnetSpeed", 1);
        }
        return prefs.getDouble("garbageMagnetSpeed", 1);
    }
    public static double garbageMagnetUpperTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("garbageMagnetUpperTarget")) {
            prefs.putDouble("garbageMagnetUpperTarget", 0.84);
        }
        return prefs.getDouble("garbageMagnetUpperTarget", 0.84);
    }
    public static double garbageMagnetlowerTarget() {
        Preferences prefs = Preferences.getInstance();
        if (!prefs.containsKey("garbageMagnetlowerTarget")) {
            prefs.putDouble("garbageMagnetlowerTarget", 0.383);
        }
        return prefs.getDouble("garbageMagnetlowerTarget", 0.383);
    }
    
    
    
    public void robotInit() {
	RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drivetrain = new Drivetrain();
        pickup = new Pickup();
        logger = new Logger();
        arm = new Arm();
        indexWheels = new IndexWheels();
        claw = new Claw();
        squeeze = new squeeze();
        frontforks = new Frontforks();
        trashMagnet = new TrashMagnet();

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
