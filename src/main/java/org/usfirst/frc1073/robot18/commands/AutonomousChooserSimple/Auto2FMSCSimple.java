package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Center */
public class Auto2FMSCSimple extends CommandGroup {
	/** If Chooser is set to Center */
	public Auto2FMSCSimple() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new Auto3CommandC_RRSimple());
			break;
		case "RLR":
			addSequential(new Auto3CommandC_RLSimple());
			break;
		case "LLL":
			addSequential(new Auto3CommandC_LLSimple());
			break;
		case "LRL":
			addSequential(new Auto3CommandC_LRSimple());
			break;
			/** Should never get used. Something is either very right or very wrong if this gets run */
		default:
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			addSequential(new AdvancedDrive(-.8, 40, 40));
			addSequential(new TurnWithGyro(.8, 65, "clockwise"));
			addSequential(new AdvancedDrive(-.8, 60, 60));
			break;
		}
	}
}