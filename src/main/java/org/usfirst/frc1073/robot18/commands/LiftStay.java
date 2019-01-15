package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.robotCollector;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftStay extends Command {
	
	double speed = 0;
	double distance;
	double target;
	double inches;
	
    public LiftStay() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.elevatorMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    	distance = RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0) >= (distance + 100))
    	{
    		RobotMap.elevatorMotorLeft.set(0.25);
    	}
    	else if (RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0) <= (distance - 100))
    	{
    		RobotMap.elevatorMotorLeft.set(-0.25);
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
