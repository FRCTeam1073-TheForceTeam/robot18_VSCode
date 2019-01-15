package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftElevatorWithTimeDown extends Command {

	double time;
	boolean finished = false;
	
	public LiftElevatorWithTimeDown(double time) {
		this.time = time;
	}
	
    protected void initialize() {
    	RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0,0,0);
    	RobotMap.elevatorMotorRight.setSelectedSensorPosition(0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int plus = 0;
    	while(plus < time) {
    		Robot.elevator.elevatorDrive.tankDrive(1, 1);
    		plus = plus + 1;
    		if(plus == 30) {
    			break;
    		}
    	}
    	
    	
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
