// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1982.Robot2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1982.Robot2016.Robot;

import com.ni.vision.NIVision;

/**
 *
 */
public class ToggleCameraComm extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ToggleCameraComm() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	///if we are currently looking at the front camera,
//    	if(Robot.currSession == Robot.sessionfront){
//    		///stop grabbing images from that front camera,
//    		NIVision.IMAQdxStopAcquisition(Robot.currSession);
//    		//set current camera to the rear camera
//    		Robot.currSession = Robot.sessionback;
//    		//tell the robot it is reversed
//    		Robot.reversed = true;
//    		//configure grab so it takes the images from the back camera
//	        NIVision.IMAQdxConfigureGrab(Robot.currSession);
//	    //if we are currently looking at the rear camera,
//    	} else if(Robot.currSession == Robot.sessionback){
//    		//do the same stuff, but switch the front camera to the back camera
//   		 	NIVision.IMAQdxStopAcquisition(Robot.currSession);
//   		 	Robot.currSession = Robot.sessionfront;
//   		 	Robot.reversed = false;
//   		 	NIVision.IMAQdxConfigureGrab(Robot.currSession);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
