package org.usfirst.frc1073.robot18;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static PowerDistributionPanel pdp;

	public static Solenoid leftWrist;
	public static Solenoid rightWrist;
	public static Solenoid liftHigh;
	public static Solenoid liftLow;
	public static Solenoid gearHigh;
	public static Solenoid gearLow;
	public static Solenoid collectorLeft;
	public static Solenoid collectorRight;

	public static WPI_VictorSPX rightMotor2;
	public static WPI_TalonSRX rightMotor1;
	public static WPI_VictorSPX leftMotor2;
	public static WPI_TalonSRX leftMotor1;
	public static WPI_TalonSRX elevatorMotorRight;
	public static WPI_TalonSRX elevatorMotorLeft;
	public static WPI_TalonSRX elbowMotor;
	public static WPI_VictorSPX climberMotor;
	public static Encoder rightEnc;
	public static Encoder leftEnc;

	public static Encoder liftEncoder;
	public static DigitalInput liftSwitchBottom;
	public static DigitalInput liftSwitchTop;

	public static AnalogInput leftSensor;
	public static AnalogInput rightSensor;
	public static AnalogInput frontSensor;
	public static AnalogInput backSensor;
	public static AnalogInput clawSensor;

	public static ADXRS450_Gyro headingGyro;

	public static Encoder collectorEncoder;
	public static WPI_VictorSPX leftCollectorMotor;
	public static WPI_VictorSPX rightCollectorMotor;
	public static DigitalInput collectorSwitchBottom;
	public static DigitalInput collectorFlip;
	public static DigitalInput collectorSwitchFront;

	public static void init() {
		liftSwitchBottom = new DigitalInput(0);
		LiveWindow.addSensor("Elevator", "switchBottom", liftSwitchBottom);
		liftSwitchTop = new DigitalInput(1);
		LiveWindow.addSensor("Elevator", "switchTop", liftSwitchTop);
		collectorFlip = new DigitalInput(2);
		LiveWindow.addSensor("collector", "switchBack", collectorFlip);
		collectorSwitchFront = new DigitalInput(3);
		LiveWindow.addSensor("collector", "switchFront", collectorSwitchFront);

		// Motor init
		// Right
		//collectorEncoder = new Encoder;
		rightMotor2 = new WPI_VictorSPX(2);
		rightMotor1 = new WPI_TalonSRX(3);
		//Left
		leftMotor2 = new WPI_VictorSPX(9);
		leftMotor1 = new WPI_TalonSRX(8);
		//Elbow
		elbowMotor = new WPI_TalonSRX(19);
		//climber
		climberMotor = new WPI_VictorSPX(10);
		// Solenoids
		leftWrist = new Solenoid (1, 6);
		//fixed
		//pneumatics on the left side of the claw
		rightWrist = new Solenoid (1, 0);
		//fixed
		//pneumatics on the right side of the claw
		liftHigh = new Solenoid (1, 4);
		//fixed
		//sets the lift to high gear
		liftLow = new Solenoid (1, 2);
		//fixed
		//sets the lift to low gear
		gearHigh = new Solenoid (1, 5);
		//fixed
		//sets the drive train to high gear
		gearLow = new Solenoid (1, 7);
		//fixed
		//sets the drive train to low gear
		collectorLeft= new Solenoid (1, 1);
		//fixed
		//sets the collector Up
		collectorRight= new Solenoid (1, 3);
		//fixed
		//sets the collector Down
		// Proximity Sensors
		leftSensor = new AnalogInput(0);
		rightSensor = new AnalogInput(1);
		frontSensor = new AnalogInput(2);
		backSensor = new AnalogInput(4);

		// IR Sensors
		clawSensor = new AnalogInput(3);

		// Gyro
		headingGyro = new ADXRS450_Gyro();
		LiveWindow.addSensor("DriveTrain", "headingGyro", headingGyro);

		// Collector Motors
		leftCollectorMotor = new WPI_VictorSPX(7);
		rightCollectorMotor = new WPI_VictorSPX(6);

		// Elevator Motors
		elevatorMotorRight = new WPI_TalonSRX(4);
		elevatorMotorLeft = new WPI_TalonSRX(5);

		//Lift Switches
		//liftSwitchBottom = new DigitalInput(1);
		//liftSwitchTop = new DigitalInput(0);
	}

}
