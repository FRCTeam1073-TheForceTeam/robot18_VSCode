package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Right */
public class Auto2FMSRSimple extends CommandGroup {
	/** If Chooser is set to Right */
	public Auto2FMSRSimple() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new Auto3CommandR_RRSimple());
			break;
		case "RLR":
			addSequential(new Auto3CommandR_RLSimple());
			break;
		case "LLL":
			addSequential(new Auto3CommandR_LLSimple());
			break;
		case "LRL":
			addSequential(new Auto3CommandR_LRSimple());
			break;
			/** Should never get used. Something is either very right or very wrong if this gets run */
		default:
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			addSequential(new AdvancedDrive(-.8, 75, 80));
			break;
		}
	}
}
