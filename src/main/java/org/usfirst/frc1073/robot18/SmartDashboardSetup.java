package org.usfirst.frc1073.robot18;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/***
 * Setup and handler for DriverStation Dashboard
 * 
 * @author Nathaniel
 *
 */
public class SmartDashboardSetup {

	private boolean s1, s2, s3, s4, s5, s6, 
	turnRight, turnLeft, cancelPushed, haveCube, clawIsOpen, collectorStatus;
	private double x, y, leftInit, rightInit, headingInit;

	public SmartDashboardSetup() {
		
	}

	/**
	 * @author Cam
	 */
	public void AutoInit() {
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("Alliance", "Blue");
			SmartDashboard.putBoolean("A", true);
			SmartDashboard.putBoolean("B", true);
			SmartDashboard.putBoolean("C", false);
		}
		else if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("Alliance", "Red");
			SmartDashboard.putBoolean("A", false);
			SmartDashboard.putBoolean("B", false);
			SmartDashboard.putBoolean("C", true);
		}

		//NOTE: THE FOLLOWING CODE GIVES A LIVE UPDATE OF SWITCH AND SCALE COLORS, PLEASE DO NOT ALTER!
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			if (Robot.FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (Robot.FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
		}
		else if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)){
			if (Robot.FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (Robot.FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
		}
		SmartDashboard.putBoolean("s1", s1);
		SmartDashboard.putBoolean("s2", s2);
		SmartDashboard.putBoolean("s3", s3);
		SmartDashboard.putBoolean("s4", s4);
		SmartDashboard.putBoolean("s5", s5);
		SmartDashboard.putBoolean("s6", s6);
	}

	/**
	 * @author Cam
	 */
	public void TeleInit() {
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("AL", "Blue");
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("AL", "Red");
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			SmartDashboard.putString("Alliance", "Blue");
			SmartDashboard.putBoolean("A", true);
			SmartDashboard.putBoolean("B", true);
			SmartDashboard.putBoolean("C", false);
		}
		if(DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)) {
			SmartDashboard.putString("Alliance", "Red");
			SmartDashboard.putBoolean("A", false);
			SmartDashboard.putBoolean("B", false);
			SmartDashboard.putBoolean("C", true);
		}

		/** NOTE: THE FOLLOWING CODE GIVES A LIVE UPDATE OF SWITCH AND SCALE COLORS, PLEASE DO NOT ALTER! */
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Blue)) {
			if (Robot.FMS.equals("RRR")){
				s1 = true;
				s2 = false;
				s3 = true;
				s4 = false;
				s5 = true;
				s6 = false;
			}
			else if (Robot.FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("LRL")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("RLR")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
		}
		if (DriverStation.getInstance().getAlliance().equals(DriverStation.Alliance.Red)){
			if (Robot.FMS.equals("RRR")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("LLL")){
				s1 = false;
				s2 = true;
				s3 = false;
				s4 = true;
				s5 = false;
				s6 = true;
			}
			else if (Robot.FMS.equals("LRL")){
				s1 = true;
				s2 = false;
				s3 = false;
				s4 = true;
				s5 = true;
				s6 = false;
			}
			else if (Robot.FMS.equals("RLR")){
				s1 = false;
				s2 = true;
				s3 = true;
				s4 = false;
				s5 = false;
				s6 = true;
			}
		}
		SmartDashboard.putBoolean("s1", s1);
		SmartDashboard.putBoolean("s2", s2);
		SmartDashboard.putBoolean("s3", s3);
		SmartDashboard.putBoolean("s4", s4);
		SmartDashboard.putBoolean("s5", s5);
		SmartDashboard.putBoolean("s6", s6);
		y = 0;
		x = 0;
		leftInit = RobotMap.leftMotor1.getSelectedSensorPosition(0);
		rightInit = RobotMap.rightMotor1.getSelectedSensorPosition(0);
		headingInit = RobotMap.headingGyro.getAngle();
	}

	/**
	 * @author Cam
	 */
	public void TelePeriodic() {
		SmartDashboard.putNumber("Left Motors", Math.abs(RobotMap.leftMotor1.get()));
		SmartDashboard.putNumber("Right Motors", Math.abs(RobotMap.rightMotor1.get()));
		if(Robot.oi.driverControl.getRawAxis(4)>.05) {
			turnRight = true;
			turnLeft = false;
		}
		else if (Robot.oi.driverControl.getRawAxis(4)<-.05) {
			turnRight = false;
			turnLeft = true;
		}
		else {
			turnRight = false;
			turnLeft = false;
		}
		SmartDashboard.putNumber("Lift Speed", Math.abs(RobotMap.elevatorMotorRight.get()));
		SmartDashboard.putNumber("lift axis", Robot.oi.operatorControl.getRawAxis(1));
		SmartDashboard.putBoolean("turn Left", turnLeft);
		SmartDashboard.putBoolean("", turnRight);
		if(RobotMap.clawSensor.getAverageVoltage() > 1) {
			haveCube = true;
		}
		else {
			haveCube = false;
		}
		SmartDashboard.putBoolean("Do you have a cube?", haveCube);
		if(RobotMap.leftWrist.get() == true && RobotMap.rightWrist.get( )== false) {
			clawIsOpen = true;
		}
		else {
			clawIsOpen = false;
		}
		SmartDashboard.putBoolean("Claw Open?", clawIsOpen);

		if(RobotMap.collectorLeft.get() == false && RobotMap.collectorRight.get() == true) {
			collectorStatus = true;
			SmartDashboard.putString("Collector", "Up");
		}
		else {
			collectorStatus = false;
			SmartDashboard.putString("Collector", "Down");
		}
		SmartDashboard.putBoolean("Collector Up?", collectorStatus);
		if (RobotMap.gearLow.get() == false && RobotMap.gearHigh.get() == true){
			SmartDashboard.putString("DT Gear", "High");
		}
		else if (RobotMap.gearLow.get() == true && RobotMap.gearHigh.get() == false){
			SmartDashboard.putString("DT Gear", "Low");
		}
		else {
			SmartDashboard.putString("DT Gear", "ERROR: PLEASE SHIFT NOW!");
		}

		if (RobotMap.liftLow.get() == false && RobotMap.liftHigh.get() == true){
			SmartDashboard.putString("Lift Gear", "High");
		}
		else if (RobotMap.liftLow.get() == true && RobotMap.liftHigh.get() == false){
			SmartDashboard.putString("Lift Gear", "Low");
		}
		else {
			SmartDashboard.putString("Lift Gear", "ERROR: PLEASE SHIFT NOW!");
		}
		SmartDashboard.putNumber("Match Time1", Timer.getMatchTime());
		SmartDashboard.putNumber("Match Time1", Timer.getMatchTime());

		SmartDashboard.putNumber("Elevator Enc", RobotMap.elevatorMotorLeft.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right Enc", RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Enc", RobotMap.leftMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putBoolean("Bottom Limit", RobotMap.liftSwitchBottom.get());
		SmartDashboard.putBoolean("Top Limit", RobotMap.liftSwitchTop.get());
		SmartDashboard.putNumber("IR Voltage", RobotMap.clawSensor.getVoltage());
	}
	
	/**
	 * @author Max
	 */
	public void Nav() {
		if(!Robot.turn)
		{
			int distLeft = RobotMap.leftMotor1.getSelectedSensorPosition(0);
			int distRight = RobotMap.rightMotor1.getSelectedSensorPosition(0);
			double heading = RobotMap.headingGyro.getAngle() - headingInit;

			double distAvg = (((distLeft - leftInit) * (2799 / 1993)) - ((distRight - rightInit) * (1993 / 2799))) / 2;
			double distReal = ((distAvg * 3.9) / 1440) * Math.PI;
			double headingY = Math.cos(Math.toRadians(heading));
			double headingX = Math.sin(Math.toRadians(heading));

			y = distReal * headingY;
			x = distReal * headingX;

			SmartDashboard.putNumber("X:", x);
			SmartDashboard.putNumber("Y:", y);
			SmartDashboard.putNumber("distReal:", distReal);
			SmartDashboard.putNumber("Heading", heading);
		}
	}
}
