package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.LowGearDT;
import org.usfirst.frc1073.robot18.commands.NeverEndingSpit;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Left and FMS is RLR
	 * @author Jack
	 */
public class Auto3CommandL_RL extends CommandGroup {
	/** If Chooser is set to Left and FMS is RLR */
	public Auto3CommandL_RL(){
		switch(Robot.autonomousMatchType.getSelected().getString()) {
		case "quals":
			System.out.println("Auto3CommandL_RL - quals"); //Passes Autoline
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 50, 51));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed/2, 50, 51));
			addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 60, 70));
			addSequential(new TurnToPoint(.8,90));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 145, 80));
			addSequential(new TurnToPoint(.8,0));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 40, 45));
			addSequential(new NeverEndingSpit());
			break;
		case "elims":
			System.out.println("Auto3CommandL_RL - elims"); //Places 1 cube on scale
			addSequential(new LowGearDT());
			addParallel(new LiftElevatorWithTime(AutoVars.LiftDistScale));
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 260, 0));
			addSequential(new TurnWithGyro(AutoVars.TurnSpeed, 90, "clockwise"));
			addSequential(new SpitOutCube(1, 0));
			System.out.println("Auto Completed");
			break;
		default:
			SmartDashboard.putString("MatchType", "!!!Chooser Not Set!!!");
			addSequential(new LowGearDT());
			addSequential(new AdvancedDrive(AutoVars.ADSpeed, 80, 80)); //Gets to autoline
			System.out.println("Auto Completed");
			break;
		}
	}
}
