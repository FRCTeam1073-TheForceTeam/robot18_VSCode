package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_LR extends CommandGroup {
	/** If Chooser is set to Left and FMS is LRL
	 * @author Jack
	 */
	public Auto3CommandL_LR(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
		case "elims":
			System.out.println("Auto3CommandL_LR - elims"); //Places 1 cube in switch
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideDist, 100));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "clockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideApproach, 25));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
			break;
		case "experimental":
			System.out.println("Hello World!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideDist, 100));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "clockwise"));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, AutoVars.SideApproach, 25));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			
			addSequential(new LiftElevatorWithTime(5));
			addParallel(new ElbowFlip());
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 0));
			addParallel(new LiftElevatorWithTimeDown(30));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 50, 50));
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 10));
			addSequential(new CubeDriveAndGet());
			addSequential(new TurnToPoint(AutoVars.TurnSpeed, 10));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 10, 10));
			addSequential(new SpitOutCube(1, AutoVars.SpitOutSpeed));
			System.out.println("Auto Completed");
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80)); //Gets to autoline
			System.out.println("Auto Completed");
			break;
		}
	}
}
