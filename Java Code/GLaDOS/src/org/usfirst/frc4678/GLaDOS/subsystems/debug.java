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
import com.sun.squawk.microedition.io.FileConnection;
import org.usfirst.frc4678.GLaDOS.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;
import org.usfirst.frc4678.GLaDOS.Robot;
/**
 *
 */
public class debug extends Subsystem {
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
    public void print(String message, int debugLevel) {
        if (debugLevel <= Robot.debugLevel()) {                                 //If the current debug level is less than the message debug level
            System.out.println(message);
        }
    }
    public void writeToFile(String contents) {
        String url = "file:///LightleftOutput.txt";
        try {
            FileConnection c = (FileConnection) Connector.open(url);
            OutputStreamWriter writer = new OutputStreamWriter(c
                    .openOutputStream());
            writer.write("Val = " + Robot.driveTrain.getLightValueLeft() + "\n");
            writer.write("hello world");
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}