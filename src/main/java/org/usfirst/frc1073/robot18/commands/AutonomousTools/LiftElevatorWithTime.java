package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftElevatorWithTime extends Command {


	double timeStart = 0;
	double timeFinished;
	boolean finished = false;

	
	public LiftElevatorWithTime(double endTime) {
		this.timeFinished = endTime;
	}
	
    protected void initialize() {
    	RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0,0,0);
    	RobotMap.elevatorMotorRight.setSelectedSensorPosition(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    		Robot.elevator.elevatorDrive.tankDrive(1, -1);
    		timeStart=timeStart+1;
    		SmartDashboard.putNumber("LIFTUP", timeStart);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timeStart>= timeFinished) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
