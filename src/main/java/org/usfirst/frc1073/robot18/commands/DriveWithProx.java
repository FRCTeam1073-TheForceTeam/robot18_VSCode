package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.subsystems.*;

import com.ctre.phoenix.motorcontrol.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

//THIS COMMAND IS MADE FOR IF THE PROX IS ON THE LEFT SIDE OF THE ROBOT

public class DriveWithProx extends Command {
	double voltage;
	double distance;
	double total;
	double distFromWall;
	double leftSpeed;
	double rightSpeed;
    public DriveWithProx(double distFromWall) {
    	this.distFromWall = distFromWall;
    }

    protected void initialize() { 
    	leftSpeed = .2;
    	rightSpeed = .2;
    }

    protected void execute() {
		voltage = RobotMap.leftSensor.getVoltage();
		distance = (voltage - 0.0399)/0.0234;
		
		SmartDashboard.putNumber("Dist From Wall", distance);
		SmartDashboard.putNumber("left Speed", leftSpeed);
		SmartDashboard.putNumber("right Speed", rightSpeed);
		
		if((distance+5) < distFromWall){
			leftSpeed = .25;
			rightSpeed = .2;
		}
		if((distance+10) < distFromWall){
			leftSpeed = .3;
			rightSpeed = .2;
		}
		if((distance-5)> distFromWall){
			rightSpeed = .25;
			leftSpeed = .2;
		}
		if((distance-10)> distFromWall){
			rightSpeed = .3;
			leftSpeed = .2;
		}
		
    	RobotMap.leftMotor1.set(ControlMode.Position, leftSpeed);
    	RobotMap.rightMotor1.set(ControlMode.Position, rightSpeed);
    }

    protected boolean isFinished() {
    	boolean isFinished = false;
    	return isFinished;
    }

    protected void end() {
    	RobotMap.leftMotor1.set(ControlMode.Position, 0.0);
    	RobotMap.rightMotor1.set(ControlMode.Position, 0.0);
    }

    protected void interrupted() {
    	RobotMap.leftMotor1.set(ControlMode.Position, 0.0);
    	RobotMap.rightMotor1.set(ControlMode.Position, 0.0);
    }
}
