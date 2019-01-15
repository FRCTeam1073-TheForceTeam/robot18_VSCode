package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.*;
import org.usfirst.frc1073.robot18.subsystems.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class robotDrivetrain extends Subsystem {
	
    private final WPI_TalonSRX rightMotor1 = RobotMap.rightMotor1;
    private final WPI_VictorSPX rightMotor2 = RobotMap.rightMotor2;
    private final WPI_TalonSRX leftMotor1 = RobotMap.leftMotor1;
    private final WPI_VictorSPX leftMotor2 = RobotMap.leftMotor2;
    private final Encoder rightEnc = RobotMap.rightEnc;
    private final Encoder leftEnc = RobotMap.leftEnc;
    
    private boolean leftInverted = false;
    private boolean rightInverted = false;
    
    public DifferentialDrive difDrive;
    
	public robotDrivetrain() {
	    	leftMotor1.setInverted(leftInverted);
	    	leftMotor2.setInverted(leftInverted);
	    	rightMotor1.setInverted(rightInverted);
	    	rightMotor2.setInverted(rightInverted);
	    	
	    	leftMotor2.follow(leftMotor1);
	    	rightMotor2.follow(rightMotor1);
	    	
	    	rightMotor1.setSafetyEnabled(false);
	    	rightMotor2.setSafetyEnabled(false);
	    	leftMotor1.setSafetyEnabled(false);
	    	leftMotor2.setSafetyEnabled(false);
	    	
	    	difDrive = new DifferentialDrive(RobotMap.leftMotor1, RobotMap.rightMotor1);
	    	}
    
    @Override
    public void initDefaultCommand() {
    	setDefaultCommand(new ControllerDifferentialDrive());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    /** Basic non-PID drive 
     * @author Nathaniel
     * @param left Speed
     * @param right Speed
     * @category Drive Command
     */
    public void basicDrive(double left, double right) {
    	
    	if (left > 1) {
    		left = 1;
    	}
    	if (right > 1) {
    		right = 1;
    	}
    	if (left < -1) {
    		left = -1;
    	}
    	if (right < -1) {
    		right = -1;
    	}
    	Robot.bling.sendDrive();
    	rightMotor1.set(ControlMode.PercentOutput, right);
    	leftMotor1.set(ControlMode.PercentOutput, left);
    }
    /** Basic drive that stops after a set time
     * @author Eben, Xander, and Nathaniel
     * @param left speed
     * @param right speed
     * @param time in milliseconds (1000 milliseconds in 1 second)
     * @category Drive Command
     */
    public void basicDriveTimed(double left, double right, int time) { 
    	
    	basicDrive(left, right);
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	basicDrive(0, 0);
    }
    
    // @ToDo comment on this 
    public void arcadeDrive(double left, double right) {
        
    	//double tempLeft = cubicScale(deadzone(left,DEADZONE_VALUE),CUBIC_SCALE)-cubicScale(deadzone(right,DEADZONE_VALUE*3),CUB);
    	//double tempRight = cubicScale(deadzone(left,DEADZONE_VALUE),CUBIC_SCALE)+cubicScale(deadzone(right,DEADZONE_VALUE*3),CUB);
    	
    	double tempLeft = left;
    	double tempRight = right;
    	double difL,difR = 0;
    	
    	if(tempLeft > 1)
    	{
    		difL = tempLeft-1;
    		tempRight -= difL;
    		tempLeft = 1;
    	}
    	if(tempRight > 1)
    	{
    		difR = tempRight-1;
    		tempLeft -= difR;
    		tempRight = 1;
    	}
    	if(tempLeft < -1)
    	{
    		difL = tempLeft+1;
    		tempRight -= difL;
    		tempLeft = -1;
    	}
    	if(tempRight < -1)
    	{
    		difR = tempRight+1;
    		tempLeft -= difR;
    		tempRight = -1;
    	}
    	
//    	if(isPrecision)
//    	{
//    		//Should change when testing for max ease for driver to line up
//    		tempLeft /= 3;
//    		tempRight /= 3;
//    	}
    	
    	    	
    		
    	RobotMap.rightMotor1.set(ControlMode.PercentOutput, tempRight);
        RobotMap.leftMotor1.set(ControlMode.PercentOutput, tempLeft);
    }

}

