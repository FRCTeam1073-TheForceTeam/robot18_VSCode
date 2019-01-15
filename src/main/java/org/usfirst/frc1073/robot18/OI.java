
package org.usfirst.frc1073.robot18;

import org.usfirst.frc1073.robot18.XboxController;
import org.usfirst.frc1073.robot18.commands.*;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public double dropoffSpeed = Robot.robotPreferences.getDouble("Dropoff Speed", 1);
    //public double collectorSpeed = Robot.robotPreferences.getDouble("Collector Speed", 1);
	public double isDone = 0;
    public double isDoneLift = 0;
	public boolean turnRight;
	public boolean turnLeft;
	public boolean cancelPushed;
	public boolean haveCube;
	public boolean clawIsOpen;
	public boolean collectorStatus;
	
	public XboxController driverControl;
	public XboxController operatorControl;
	
	public JoystickButton RobotTeleInit;
	public JoystickButton visionButton;
	public JoystickButton lidarButton;
	public JoystickButton driverCancel;
	public JoystickButton operatorCancel;
	public JoystickButton intake;
	public JoystickButton purge;
	public JoystickButton suckInButton;
	public JoystickButton suckOutButton;
    public JoystickButton highGearDT;
    public JoystickButton lowGearDT;
    public JoystickButton clawOpen;
    public JoystickButton collectorUD;
    public JoystickButton collectorUp;
    public JoystickButton collectorDown;
    public JoystickButton LiftToSwitch;
    public JoystickButton LiftTo4FtScale;
    public JoystickButton LiftTo5FtScale;
    public JoystickButton LiftTo6FtScale;
    public JoystickButton LiftToStay;
    public JoystickButton climbUp;
    public JoystickButton climbDown;
    public double collectorIntake;
    public double collectorPurge;
   
	
    public OI() {
    	
    	driverControl = new XboxController(0);
    	
    	RobotTeleInit = driverControl.start;
    	
    	visionButton = driverControl.b;
    	visionButton.whenPressed(new VisionCubeTracker());
    	
    	driverCancel = driverControl.a;
    	
        //shifts drive train to high gear
        highGearDT = driverControl.leftBumper;
        highGearDT.whenPressed(new HighGearDT());

        //shifts drive train to low gear
        lowGearDT = driverControl.rightBumper;
        lowGearDT.whenPressed(new LowGearDT());
    	
    	operatorControl = new XboxController(1);
    	
    	//lift buttons
    	/*LiftToSwitch = operatorControl.a;
    	LiftToSwitch.whenPressed(new LiftElevatorToDistanceScale(24.0));
    	*/
    	
    	collectorDown = operatorControl.a;
    	collectorDown.whenPressed(new CollectorDown());
    	
    	//LiftTo4FtScale = operatorControl.x;
    	//LiftTo4FtScale.whenPressed(new LiftElevatorToDistanceScale(48.0));
    	
    	//LiftTo5FtScale = operatorControl.b;
    	//LiftTo5FtScale.whenPressed(new LiftElevatorToDistanceScale(60.0));
    	
    	/*LiftTo6FtScale = operatorControl.y;
    	LiftTo6FtScale.whenPressed(new LiftElevatorToDistanceScale(72.0));
    	*/
    	
    	collectorUp = operatorControl.y;
    	collectorUp.whenPressed(new CollectorUp());
    	
    	LiftToStay = operatorControl.start;
    	LiftToStay.whileHeld(new LiftStay());
    	
    	operatorCancel = operatorControl.select;
    	
        //opens and closes the claw
        clawOpen = operatorControl.rightBumper;
        clawOpen.whileHeld(new CloseClaw());
        clawOpen.whenReleased(new OpenClaw());
        
        //Climb
    	climbUp = operatorControl.y;
    	climbUp.whileHeld(new spinClimberUp());
    	
    	climbDown = operatorControl.a;
    	climbDown.whileHeld(new spinClimberDown());
    	

        //Makes the collector go up or down
        collectorUD = operatorControl.leftBumper;
        if (collectorUD.equals(1)) {
            isDone =+ 1;
        }
        if (isDone%2 == 1 && collectorUD.equals(1)) {
            collectorUD.whenPressed(new CollectorDown());
        }
        if (isDone%2 == 0 && collectorUD.equals(1)) {
            collectorUD.whenPressed(new CollectorUp());
        }
        //low gear lift
        
        //high gear lift

        
        
        //intake = operatorControl.rightBumper;
    	//intake.whenPressed(new SpinCollectorTelev2());
    	
    	//purge = operatorControl.leftBumper;
    	//purge.whenPressed(new SpinCollectorTelev3());
    	


        // SmartDashboard Buttons
        SmartDashboard.putData("Drive", new ControllerDifferentialDrive());
        SmartDashboard.putData("Lidar Align", new LidarAlignAuto());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID(10));
        SmartDashboard.putData("lidaralign360", new LidarAlign360());
        SmartDashboard.putData("LidarMoveAway", new LidarMoveAway());
        SmartDashboard.putData("LidarMoveAway360", new LidarMoveAway360());
        SmartDashboard.putData("LidarWallTurnRight", new LidarAlignWallRCG());
        SmartDashboard.putData("LidarWallTurnLeft", new LidarAlignWallLCG());
        SmartDashboard.putData("FLIP!", new ElbowFlip());
        SmartDashboard.putData("openClaw", new OpenClaw());
        SmartDashboard.putData("closeClaw", new CloseClaw());
        SmartDashboard.putData("HighDT", new HighGearDT());
        SmartDashboard.putData("LowDT", new LowGearDT());
        SmartDashboard.putData("ClawUp", new CollectorUp());
        SmartDashboard.putData("ClawDown", new CollectorDown());
        SmartDashboard.putNumber("Left Motors", RobotMap.leftMotor1.get());
        SmartDashboard.putNumber("Right Motors", RobotMap.rightMotor1.get());
        SmartDashboard.putData("SeeRobot?", new LidarSeeRobot());
        SmartDashboard.putData("Start Sees White", new CheckForWhite());
        SmartDashboard.putData("Turn off Bling", new BlingOff());
        SmartDashboard.putData("Auto Test", new AutoTest());
        SmartDashboard.putData("VisionCube", new VisionCubeTracker());
        SmartDashboard.putData("Lift", new DropElevatorWithTime(150));
        SmartDashboard.putData("VISION!!!!", new CubeGetter());
        
        
        if(RobotMap.leftMotor1.get() > RobotMap.rightMotor1.get()) {
        	turnRight = false;
        	turnLeft = true;
        }
        else if (RobotMap.rightMotor1.get() > RobotMap.leftMotor1.get()) {
        	turnRight = true;
        	turnLeft = false;
        }
        else {
        	turnRight = false;
        	turnLeft = false;
        }
        SmartDashboard.putNumber("Lift Speed", RobotMap.elevatorMotorRight.get());
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
        
      
        
        
       // SmartDashboard.putNumber("Percent up",((RobotMap.liftEncoder.get()/9.42)/1440.0/2.0/(16.0/5.0)));
        //NOTE: 72 is a rough estimate for inches that the lift can go up
        
        	//SmartDashboard.putNumber("left drivetrain", Robot.drivetrain.difDrive.arcadeDrive(xSpeed, zRotation);
        	//SmartDashboard.putString("Robot Name" , Robot.robotName.OK_Puzzles);

    }


	private Sendable CubeGetter() {
		// TODO Auto-generated method stub
		return null;
	}
}


