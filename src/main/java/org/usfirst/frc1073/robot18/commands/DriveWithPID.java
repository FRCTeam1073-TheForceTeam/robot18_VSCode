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
public class DriveWithPID extends Command {

	double target;
	double errorleft, errorright;
	double rightspeed, leftspeed;
	boolean rightInverted, leftInverted;
	
	double p = 1;
	double i = 0;
	double d = 0;
	
	/** Drive Command that moves an exact distance with PID
	 * @param inches Exact distance you want to go in inches
	 * @author Sreekar Chilakapati and Anderson Steckler
	 * @category Drive Command
	 */
    public DriveWithPID(double inches) {
    	this.target = (inches/(Math.PI*3.9))*1440;
    	
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 0);
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 0);
        
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    }

    protected void initialize() {   
    	//set left  motors
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 10);
        
        RobotMap.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        RobotMap.leftMotor1.setSensorPhase(true);

        RobotMap.leftMotor1.configPeakOutputForward(.9, 10);
        RobotMap.leftMotor1.configPeakOutputReverse(-.9, 10);
        
        RobotMap.leftMotor1.configAllowableClosedloopError(0, 0, 10);

        RobotMap.leftMotor1.config_kF(0, 0.0, 10);
        RobotMap.leftMotor1.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        RobotMap.leftMotor1.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        RobotMap.leftMotor1.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
        
        //set right motors
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 10);
        
        RobotMap.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        RobotMap.rightMotor1.setSensorPhase(true);

        RobotMap.rightMotor1.configPeakOutputForward(.9, 10);
        RobotMap.rightMotor1.configPeakOutputReverse(-.9, 10);

        RobotMap.rightMotor1.configAllowableClosedloopError(0, 0, 10);
        
        RobotMap.rightMotor1.config_kF(0, 0.0, 10);
        RobotMap.rightMotor1.config_kP(0, SmartDashboard.getNumber("P", 1), 10);
        RobotMap.rightMotor1.config_kI(0, SmartDashboard.getNumber("I", 0), 10);
        RobotMap.rightMotor1.config_kD(0, SmartDashboard.getNumber("D", 0), 10);
        
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
    }

    protected void execute() {
    	RobotMap.leftMotor1.set(ControlMode.Position, -target);
        RobotMap.rightMotor1.set(ControlMode.Position, target);
    	
    	errorleft = Math.abs(RobotMap.leftMotor1.getClosedLoopError(0));
    	errorright = Math.abs(RobotMap.rightMotor1.getClosedLoopError(0));
    }
    
    protected boolean isFinished() {
    	boolean finish = false;
		if(Math.abs(errorright) < 100 || Math.abs(errorleft) < 100 || Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true){
	        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
	        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    		finish = true;
    	}
    	else {
    		finish = false;
    	}
		return finish;
    }

    protected void end() {
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 0);
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 0);
        
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    }

    protected void interrupted() {
        RobotMap.leftMotor1.setSelectedSensorPosition(0, 0, 0);
        RobotMap.rightMotor1.setSelectedSensorPosition(0, 0, 0);
        
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, 0);
        RobotMap.rightMotor1.set(ControlMode.PercentOutput, 0);
    }
}
