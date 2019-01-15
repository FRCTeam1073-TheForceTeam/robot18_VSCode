package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.robotCollector;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftElevatorToDistanceScale extends Command {
	
	double speed = 0;
	double distance;
	double target;
	double inches;
	boolean up;
	
    public LiftElevatorToDistanceScale(double _inches) {
    	inches = _inches;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	RobotMap.elevatorMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

    	target = (inches/9.42)*1440.0*2.0*(16.0/5.0);
    	distance = RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0);
    	
    	if((Math.abs(distance)) >= target){
    		up = false;
    	}
    	if((Math.abs(distance)) <= target){
    		up = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	distance = RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0);

    	
    	if((Math.abs(distance)) >= target){
			Robot.elevator.elevatorDrive.tankDrive(1, -1); //down
    	}
    	if((Math.abs(distance)) <= target){
			Robot.elevator.elevatorDrive.tankDrive(-1, 1); //up
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean finish = false;
    	if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			finish = true;
		}
    	if(up == false){
	    	if((Math.abs(distance)) <= target){
	    		finish = true;
	    	}
    	}
    	if(up == true){
	    	if((Math.abs(distance)) >= target){
	    		finish = true;
	    	}
    	}
    	if(Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true){
    		finish = true;
    	}
    	if(inches == 0 && !RobotMap.liftSwitchBottom.get()) finish = true;
    	//if(!RobotMap.liftSwitchTop.get()) finish = true;
        return finish;
        
    }

    
    // Called once after isFinished returns true
    protected void end() {
		Robot.elevator.elevatorDrive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.elevator.elevatorDrive.tankDrive(0, 0);
    }
}
