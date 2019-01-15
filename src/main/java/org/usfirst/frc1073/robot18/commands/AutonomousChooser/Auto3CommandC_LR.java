package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center and FMS is LRL */
public class Auto3CommandC_LR extends CommandGroup {
	/** If Chooser is set to Center and FMS is LRL
	 * @author Jack
	 */
	public Auto3CommandC_LR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
		case "elims":
			System.out.println("Auto3CommandC_LR"); //Places 1 cube in switch
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 5, 10));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 60, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.MiddleDist, 100));
			addSequential(new TurnToPoint(AutoVars.TurnSpeedSlow, 0));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.FinalApproach, 10));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
			break;
		case "experimental":
			System.out.println("Hello World!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 5, 10));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 60, "counterclockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.MiddleDist, 100));
			addSequential(new TurnToPoint(AutoVars.TurnSpeedSlow, 0));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.FinalApproach, 10));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			
			addSequential(new LiftElevatorWithTime(5));
			addParallel(new ElbowFlip());
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 10, 10));
			addParallel(new LiftElevatorWithTimeDown(30));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 90));
			//addSequential(new CubeGetter());
			//addSequential(new TurnToPoint(AutoVars.TurnSpeed, 90));
			addSequential(new CubeDriveAndGet());
			addParallel(new LiftElevatorWithTime(30));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 10, 10));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 180));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80));
			System.out.println("Auto Completed");
			break;
		}
	}
}
