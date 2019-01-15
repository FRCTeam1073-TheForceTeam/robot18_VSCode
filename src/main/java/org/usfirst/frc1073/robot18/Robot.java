package org.usfirst.frc1073.robot18;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.Bling;
import org.usfirst.frc1073.robot18.commands.*;
import org.usfirst.frc1073.robot18.commands.AutonomousChooser.Auto1Chooser;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.LidarSeeRobot;
import org.usfirst.frc1073.robot18.subsystems.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	NetworkTable lidarSendTable;
	public static Preferences robotPreferences;
	public static OI oi;
	public static AutoVars autoSetup;
	public static SmartDashboardSetup sdSetup;
	public static robotElevator elevator;
	public static robotDrivetrain drivetrain;
	public static robotCollector collector;
	public static robotPneumatic pneumatic;
	public static robotClimber climber;
	public static Bling bling;
	public static Alliance alliance;
	public static String FMS;
	public static SendableChooser<AutoObject> autonomousPosition, autonomousMatchType;
	public AutoObject left, center, right, other, quals, elims, experimental;
	public static boolean clawBool, EncoderBool, EncoderBoolSet, notClear, turn;
	public static CameraServer cameraSwitcher;
	public static boolean selectedCamera;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		//turn is false
		turn = false;
		
		RobotMap.init();
		
		System.out.println("Robot Initializing");
		
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		
		RobotMap.headingGyro.reset();
		
		robotPreferences = Preferences.getInstance();
		
		elevator = new robotElevator();
		drivetrain = new robotDrivetrain();
		pneumatic = new robotPneumatic();
		collector = new robotCollector();
		oi = new OI();
		autoSetup = new AutoVars();
		sdSetup = new SmartDashboardSetup();
		climber = new robotClimber();

		FMS = "";
		EncoderBoolSet = false;
		EncoderBool = false;
		notClear = false;

		//Instantiating Bling Class for smartbling on Robot.
		bling = new Bling();
		bling.sendRobotInit();

		//lift encoder set to 0
		RobotMap.elevatorMotorLeft.setSelectedSensorPosition(0, 0, 10);

		/* Position Objects */
		left = new AutoObject(1);
		center = new AutoObject(2);
		right = new AutoObject(3);
		other = new AutoObject(4);
		quals = new AutoObject(5);
		elims = new AutoObject(6);
		experimental = new AutoObject(7);
		
		/* The Position Chooser */
		autonomousPosition = new SendableChooser<AutoObject>();
		autonomousPosition.addDefault("None", other);
		autonomousPosition.addObject("Left", left);
		autonomousPosition.addObject("Center", center);
		autonomousPosition.addObject("Right", right);
		SmartDashboard.putData("Position", autonomousPosition);

		/* The MatchType Chooser */
		autonomousMatchType = new SendableChooser<AutoObject>();
		autonomousMatchType.addDefault("None", other);
		autonomousMatchType.addObject("Qualifications", quals);
		autonomousMatchType.addObject("Eliminations", elims);
		autonomousMatchType.addObject("Experimental", experimental);
		SmartDashboard.putData("Match Type", autonomousMatchType);

		RobotMap.leftMotor1.configOpenloopRamp(0, 10);
		RobotMap.rightMotor1.configOpenloopRamp(0, 10);
		
		/** Instantiate a the camera server for both USB webcams in a separate thread **/
		Thread cameraThread = new Thread(() -> {

			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);            
			camera1.setResolution(320, 240);
			camera1.setFPS(15);

			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}

			CvSink cvSink = CameraServer.getInstance().getVideo(camera1);
			CvSource outputStream = CameraServer.getInstance().putVideo("Video", 320, 240);
			Mat source = new Mat();
			boolean currentCamera = selectedCamera;
			while( !Thread.interrupted() ) {
				if ( currentCamera != selectedCamera ) {
					currentCamera = selectedCamera;
					if ( selectedCamera == false ) {
						cvSink.setSource(camera1);            		
						SmartDashboard.putString("Camera", "Camera 1");
					}
				}
				cvSink.grabFrame(source);

				if ( source.empty() == false ) {
					int rows = source.rows();
					int columns = source.cols();

					Point lineStart = new Point(columns/2, 0);
					Point lineEnd = new Point(columns/2, rows);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					lineStart = new Point(0,rows/2);
					lineEnd = new Point(columns, rows/2);
					Imgproc.line(source, lineStart, lineEnd, new Scalar(0,0,255), 1);

					outputStream.putFrame(source);
				}

				try{
					Thread.sleep(50);
				} catch(Exception e) {           		
				}

			}
		});

		cameraThread.start();

	}
	

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(1, 250, 2);
		Robot.oi.driverControl.rumbleTimeRep(.2, 250, 2);
		
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousInit() {
		//turn is false
		RobotMap.headingGyro.reset();
		turn = false;
		
		System.out.println("Auto Setting Up");
		Robot.pneumatic.driveTrainHighGear();

		FMS = DriverStation.getInstance().getGameSpecificMessage();

		Scheduler.getInstance().run();
		
		new LidarMiniMap();

		sdSetup.AutoInit();
		
		//Encoder resets
		
		
		/* instantiate the command used for the autonomous period */
		System.out.println("Auto Starting");
		autonomousCommand = new Auto1Chooser();
		if (autonomousCommand != null) autonomousCommand.start();
		lidarSendTable = NetworkTable.getTable("LidarSendTable");
	}
	
	

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		new LidarSeeRobot();
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
		boolean SeeObject = lidarSendTable.getBoolean("Stop", false);
		if(SeeObject == true) {
			Robot.notClear = true;
			SmartDashboard.putBoolean("SeeRobot", true);
		}
		else {
			Robot.notClear = false;
		}
		
		sdSetup.Nav();
		
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		new LidarMiniMap();
		
		if (autonomousCommand != null) autonomousCommand.cancel();
		
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		alliance = DriverStation.getInstance().getAlliance();
		SmartDashboard.putString("FMS", FMS);
		
		sdSetup.TeleInit();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
		SmartDashboard.putNumber("Left", RobotMap.leftMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Right", RobotMap.rightMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Gyro", RobotMap.headingGyro.getAngle());
		SmartDashboard.putBoolean("Flip?", RobotMap.collectorFlip.get());
		SmartDashboard.putNumber("elbow", RobotMap.elbowMotor.getSelectedSensorPosition(0));
		FMS = DriverStation.getInstance().getGameSpecificMessage();
		alliance = DriverStation.getInstance().getAlliance();
		
		sdSetup.TelePeriodic();
		sdSetup.Nav();
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		System.out.println("Test Mode");
	}
}
