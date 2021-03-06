// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc4678.GLaDOS.commands;
//import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import javax.microedition.io.Connector;
import org.usfirst.frc4678.GLaDOS.Robot;
import org.usfirst.frc4678.GLaDOS.Robot.*;
/**
 *
 */
public class autonomousCommand extends Command {
    //FileConnection c;
    OutputStreamWriter writer;
    String url;
    int state;
    boolean isFinished = false;
    double armHeight;
    double leftLightSum = 0;
    double rightLightSum = 0;
    double secondLeftLightSum = 0;
    double secondRightLightSum = 0;
    int cnt = 0;
    int hotGoalSide = 1;                                                        //1 is left, 2 is right
    public autonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("Winch Mode", "High goal");                    //Have to put this somewhere
        Robot.driveTrain.setGoToDistanceState(0);
        Robot.pickup.setHoldState(0);
        isFinished = false;
        leftLightSum = 0;
        rightLightSum = 0;
        secondLeftLightSum = 0;
        secondRightLightSum = 0;
        cnt = 0;/*
        url = "file:///LightleftOutput.txt";
        try {
            FileConnection c = (FileConnection) Connector.open(url);
            OutputStreamWriter writer = new OutputStreamWriter(c
                    .openOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        Robot.debug.print("AutonomousCommand: Initialized", 1);
        state = 0;
        Robot.driveTrain.resetEncoders();
        Robot.pickup.setGoToHold(false);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.pickup.setGoToHold(false);
        cnt++;
        if (Robot.autoMode() == 0) {
            if (state == 0) {
                Robot.pickup.setMode("defence");
                if (cnt > 50) {
                    leftLightSum += Robot.driveTrain.getLightValueLeft();
                    rightLightSum += Robot.driveTrain.getLightValueRight();                       
                    if (cnt >= 60) {
                        state++;
                    }
                }
            }
            if (state == 1) {                                                   //Determine which goal is hot
                if (rightLightSum <= leftLightSum) {
                    hotGoalSide = 1;
                } else {
                    hotGoalSide = 2;
                }
                state++;
                cnt = 0;
            }
            if (state == 2) {
                if (cnt == 25) {
                    Robot.pickup.setMode("hold");
                }
                if (hotGoalSide == 2) {
                    if (Robot.driveTrain.goToDistance((Robot.shootingDistance() + 200) * Robot.autoMotorRatio(), (Robot.shootingDistance() + 200) / Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                        Robot.pickup.setMode("pickup");
                    }
                } else {
                    if (Robot.driveTrain.goToDistance((Robot.shootingDistance() + 200) / Robot.autoMotorRatio(), (Robot.shootingDistance() + 200) * Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                        Robot.pickup.setMode("pickup");
                    }
                }
            }
            if (state == 3) {
                Robot.pickup.setMode("pickup");
                if (cnt == 25) {
                    Robot.shooting.startWinch();
                    Robot.shooting.setLatchServo(Robot.latchReadyDistance());
                }
                if (cnt > 50) {
                    if (Robot.driveTrain.goToDistance(50, 50) == true) {
                        state++;
                        cnt = 0;
                    }
                }
            }
            if (state == 4) {
                if ((cnt > 75) || (Robot.pickup.getIsHoldingBall() == true)) {
                    Robot.driveTrain.stop();
                    state++;
                } else {
                    Robot.driveTrain.setPower(.15, -.15);
                }
            }
            if (state == 5) {
                if (hotGoalSide == 1) {
                    if (Robot.driveTrain.goToDistance(Robot.shootingDistance() * Robot.autoMotorRatio(), Robot.shootingDistance() / Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                        Robot.pickup.setMode("defence");
                        System.out.println("auto increaced state and shot a second time!");
                    }
                } else {
                    if (Robot.driveTrain.goToDistance(Robot.shootingDistance() / Robot.autoMotorRatio(), Robot.shootingDistance() * Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                        Robot.pickup.setMode("defence");
                        System.out.println("auto increaced state and shot a second time!");
                    }
                }
            }
            if (state == 6) {
                if (cnt == 25) {
                    Robot.shooting.startWinch();
                    Robot.shooting.setLatchServo(Robot.latchReadyDistance());
                }
                if (cnt > 25) {
                    if (Robot.driveTrain.goToDistance(0, 0) == true) {
                        isFinished = true;
                        state++;
                    }
                }
            }
        } else if (Robot.autoMode() == 1) {
            if (state == 0) {                                                   //Get light readings
                Robot.pickup.setMode("hold");
                if (cnt <= 10) {
                    leftLightSum += Robot.driveTrain.getLightValueLeft();
                }
                if (cnt >= 60) {
                    secondLeftLightSum += Robot.driveTrain.getLightValueLeft();
                }
                if (cnt >= 70) {
                    state++;
                    if ((secondLeftLightSum - leftLightSum) > Robot.hotGoalLightIncreace()) {
                        hotGoalSide = 1;
                    } else {
                        hotGoalSide = 2;
                    }
                }
            }
            if (state == 1) {
                if (((cnt > 250) && (hotGoalSide == 2)) || (hotGoalSide == 1)) {
                    if (Robot.driveTrain.goToDistance(Robot.shootingDistance(), Robot.shootingDistance()) == true) {
                        state++;
                    }
                }
            }
            if (state == 2) {
                Robot.shooting.setLatchServo(Robot.latchShootDistance());
                Robot.shooting.startWinch();
                state++;
                cnt = 0;
            }
            if (state == 3) {
                if (cnt == 25) {
                    Robot.shooting.setLatchServo(Robot.latchReadyDistance());
                    Robot.pickup.setMode("defence");
                }
                if (cnt > 50) {
                    if (Robot.driveTrain.goToDistance(0, 0) == true) {
                        isFinished = true;
                    }
                }
            }
        } else if (Robot.autoMode() == 2) {
            if (state == 0) {                                                   //Get light readings
                Robot.pickup.setMode("hold");
                if (cnt >= 10) {
                    if (cnt <= 10) {
                        rightLightSum += Robot.driveTrain.getLightValueRight();
                    }
                    if (cnt >= 60) {
                        secondRightLightSum += Robot.driveTrain.getLightValueRight();
                    }
                    if (cnt >= 70) {
                        state++;
                        if ((secondRightLightSum - rightLightSum) > Robot.hotGoalLightIncreace()) {
                            hotGoalSide = 2;
                        } else {
                            hotGoalSide = 1;
                        }
                    }
                }
            }
            if (state == 1) {
                if (((cnt > 250) && (hotGoalSide == 1)) || (hotGoalSide == 2)) {
                    if (Robot.driveTrain.goToDistance(Robot.shootingDistance(), Robot.shootingDistance()) == true) {
                        state++;
                    }
                }
            }
            if (state == 2) {
                Robot.shooting.setLatchServo(Robot.latchShootDistance());
                Robot.shooting.startWinch();
                Robot.pickup.setMode("defence");
                state++;
                cnt = 0;
            }
            if (state == 3) {
                if (cnt == 25) {
                    Robot.shooting.setLatchServo(Robot.latchReadyDistance());
                }
                if (cnt > 25) {
                    if (Robot.driveTrain.goToDistance(0, 0) == true) {
                        isFinished = true;
                    }
                }
            }
        } else if (Robot.autoMode() == 3) {
            if (state == 0) {                                                   //Get light readings
                Robot.pickup.setMode("hold");
                if (cnt > 50) {
                    leftLightSum += Robot.driveTrain.getLightValueLeft();
                    rightLightSum += Robot.driveTrain.getLightValueRight();
                    if (cnt >= 60) {
                        state++;
                    }
                }
            }
            if (state == 1) {                                                   //Determine which goal is hot
                if (rightLightSum <= leftLightSum) {
                    hotGoalSide = 1;
                } else {
                    hotGoalSide = 2;
                }
                state++;
                cnt = 0;
            }
            if (state == 2) {
                if (hotGoalSide == 2) {
                    if (Robot.driveTrain.goToDistance((Robot.shootingDistance() + 200) * Robot.autoMotorRatio(), (Robot.shootingDistance() + 200) / Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                    }
                } else {
                    if (Robot.driveTrain.goToDistance((Robot.shootingDistance() + 200) / Robot.autoMotorRatio(), (Robot.shootingDistance() + 200) * Robot.autoMotorRatio()) == true) {
                        state++;
                        cnt = 0;
                        Robot.shooting.setLatchServo(Robot.latchShootDistance());
                    }
                }
            }
            if (state == 3) {
                Robot.pickup.setMode("defence");
                if (cnt == 25) {
                    Robot.shooting.startWinch();
                    Robot.shooting.setLatchServo(Robot.latchReadyDistance());
                }
                if (cnt > 50) {
                    if (Robot.driveTrain.goToDistance(50, 50) == true) {
                        state++;
                        cnt = 0;
                    }
                }
            }
        }
    }
// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }
    // Called once after isFinished returns true
    protected void end() {
        isFinished = false;
        state = 0;
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
