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

import org.usfirst.frc4678.walle.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton g1Button1;
    public JoystickButton g1Button2;
    public JoystickButton g1Button3;
    public JoystickButton g1Button4;
    public JoystickButton g1Button6;
    public JoystickButton g1Button7;
    public JoystickButton g1Button9;
    public Joystick gamepad1;
    public JoystickButton g2Button1;
    public JoystickButton g2Button2;
    public JoystickButton g2Button3;
    public JoystickButton g2Button5;
    public JoystickButton g2Button6;
    public JoystickButton g2Button7;
    public JoystickButton g2Button9;
    public JoystickButton g2Button10;
    public JoystickButton g2Button8;
    public JoystickButton g2Button11;
    public JoystickButton g2Button12;
    public Joystick gamepad2;
    public JoystickButton g3Button4;
    public JoystickButton g3Button1;
    public JoystickButton g3Button2;
    public JoystickButton g3Button3;
    public JoystickButton g3Button5;
    public JoystickButton g3Button6;
    public JoystickButton g3Button7;
    public Joystick gamepad3;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        gamepad3 = new Joystick(2);
        
        g3Button7 = new JoystickButton(gamepad3, 7);
        g3Button7.whenPressed(new ClawDropBin());
        g3Button6 = new JoystickButton(gamepad3, 6);
        g3Button6.whenPressed(new ClawPickDown());
        g3Button5 = new JoystickButton(gamepad3, 5);
        g3Button5.whenPressed(new clawPickUpright());
        g3Button3 = new JoystickButton(gamepad3, 3);
        g3Button3.whileHeld(new CloseArm());
        g3Button2 = new JoystickButton(gamepad3, 2);
        g3Button2.whileHeld(new OpenArm2());
        g3Button1 = new JoystickButton(gamepad3, 1);
        g3Button1.whileHeld(new ManuallyAdjustPotValues());
        g3Button4 = new JoystickButton(gamepad3, 4);
        g3Button4.whileHeld(new ManualOverrideArm());
        gamepad2 = new Joystick(1);
        
        g2Button12 = new JoystickButton(gamepad2, 12);
        g2Button12.whileHeld(new CloseArm());
        g2Button11 = new JoystickButton(gamepad2, 11);
        g2Button11.whileHeld(new OpenArm2());
        g2Button10 = new JoystickButton(gamepad2, 10);
        g2Button10.whenPressed(new ReverseIndexWheels());
        g2Button9 = new JoystickButton(gamepad2, 9);
        g2Button9.whenPressed(new RunIndexWheels());
        g2Button7 = new JoystickButton(gamepad2, 7);
        g2Button7.whileHeld(new OpenIndexWheels());
        g2Button6 = new JoystickButton(gamepad2, 6);
        g2Button6.whenPressed(new CloseIndexWheels());
        g2Button5 = new JoystickButton(gamepad2, 5);
        g2Button5.whenPressed(new CenterIndexWheels());
        g2Button3 = new JoystickButton(gamepad2, 3);
        g2Button3.whenPressed(new armDropBin());
        g2Button2 = new JoystickButton(gamepad2, 2);
        g2Button2.whenPressed(new armRest());
        g2Button1 = new JoystickButton(gamepad2, 1);
        g2Button1.whenPressed(new armPickup());
        gamepad1 = new Joystick(0);
        
        g1Button9 = new JoystickButton(gamepad1, 9);
        g1Button9.whenPressed(new toggleClawAngle());
        g1Button7 = new JoystickButton(gamepad1, 7);
        g1Button7.whileHeld(new goToScore());
        g1Button6 = new JoystickButton(gamepad1, 6);
        g1Button6.whileHeld(new toggleGentleMode());
        g1Button4 = new JoystickButton(gamepad1, 4);
        g1Button4.whenPressed(new sixTotesLIft());
        g1Button3 = new JoystickButton(gamepad1, 3);
        g1Button3.whenPressed(new Lift());
        g1Button2 = new JoystickButton(gamepad1, 2);
        g1Button2.whenPressed(new Lower());
        g1Button1 = new JoystickButton(gamepad1, 1);
        g1Button1.whenPressed(new PickTote());

	    
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("RobotDrive", new RobotDrive());

        SmartDashboard.putData("Lift", new Lift());

        SmartDashboard.putData("Lower", new Lower());

        SmartDashboard.putData("PickTote", new PickTote());

        SmartDashboard.putData("sixTotesLIft", new sixTotesLIft());

        SmartDashboard.putData("goToScore", new goToScore());

        SmartDashboard.putData("toggleGentleMode", new toggleGentleMode());

        SmartDashboard.putData("armPickup", new armPickup());

        SmartDashboard.putData("armDropBin", new armDropBin());

        SmartDashboard.putData("armRest", new armRest());

        SmartDashboard.putData("ManualOverrideArm", new ManualOverrideArm());

        SmartDashboard.putData("RunIndexWheels", new RunIndexWheels());

        SmartDashboard.putData("ReverseIndexWheels", new ReverseIndexWheels());

        SmartDashboard.putData("ManuallyAdjustPotValues", new ManuallyAdjustPotValues());

        SmartDashboard.putData("ManualOverridePickup", new ManualOverridePickup());

        SmartDashboard.putData("OpenIndexWheels", new OpenIndexWheels());

        SmartDashboard.putData("CloseIndexWheels", new CloseIndexWheels());

        SmartDashboard.putData("CenterIndexWheels", new CenterIndexWheels());

        SmartDashboard.putData("OpenArm2", new OpenArm2());

        SmartDashboard.putData("CloseArm", new CloseArm());

        SmartDashboard.putData("clawPickUpright", new clawPickUpright());

        SmartDashboard.putData("ClawPickDown", new ClawPickDown());

        SmartDashboard.putData("ClawDropBin", new ClawDropBin());

        SmartDashboard.putData("toggleClawAngle", new toggleClawAngle());

        SmartDashboard.putData("keepClawLevel", new keepClawLevel());

        SmartDashboard.putData("AutoPickTote", new AutoPickTote());

        SmartDashboard.putData("setArm", new setArm());


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getGamepad1() {
        return gamepad1;
    }

    public Joystick getGamepad2() {
        return gamepad2;
    }

    public Joystick getGamepad3() {
        return gamepad3;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    
    public boolean getButton(Joystick gamepad, int button) {
    	return gamepad.getRawButton(button);
    }
}

