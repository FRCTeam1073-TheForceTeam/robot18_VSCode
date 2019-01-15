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
public class ElbowUpDown extends Command {
	double distance;
	
	boolean up;
	
    public ElbowUpDown(boolean up) {
    	this.up = up;

    }

    protected void initialize() {
    	RobotMap.elbowMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    	distance = RobotMap.elbowMotor.getSelectedSensorPosition(0);
    }

    protected void execute() {
    	
    	if (up)
    	{
    		if (RobotMap.elbowMotor.getSelectedSensorPosition(0) >= distance) {
        		RobotMap.elbowMotor.set(0.01);
        	}
    	}
    	else
    	{
    		if (RobotMap.elbowMotor.getSelectedSensorPosition(0) <= distance) {
        		RobotMap.elbowMotor.set(-0.01);
        	}
    	}
    	
    }

    protected boolean isFinished() {
    	
    	return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
