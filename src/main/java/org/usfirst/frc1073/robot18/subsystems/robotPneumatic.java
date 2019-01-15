package org.usfirst.frc1073.robot18.subsystems;
import org.usfirst.frc1073.robot18.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
public class robotPneumatic {
	private final Solenoid rightWrist = RobotMap.rightWrist;
	private final Solenoid leftWrist = RobotMap.leftWrist;
	private final Solenoid liftHigh = RobotMap.liftHigh;
	private final Solenoid liftLow = RobotMap.liftLow;
	private final Solenoid gearHigh = RobotMap.gearHigh;
	private final Solenoid gearLow = RobotMap.gearLow;
	private final Solenoid collectorLeft = RobotMap.collectorLeft;
	private final Solenoid collectorRight = RobotMap.collectorRight;
	//private final boolean out = false;
	//private final boolean in = true;

	public void openClaw() {
		leftWrist.set(false);
		rightWrist.set(true);
	}
	public void closeClaw() {
		leftWrist.set(true);
		rightWrist.set(false);
	}
	public void driveTrainHighGear() {
		gearLow.set(false);
		gearHigh.set(true);
	}
	public void driveTrainLowGear() {
		gearLow.set(true);
		gearHigh.set(false);
	}
	public void collectorUp() {
		collectorLeft.set(false);
		collectorRight.set(true);
	}
	public void collectorDown() {
		collectorLeft.set(true);
		collectorRight.set(false);
	}
}
