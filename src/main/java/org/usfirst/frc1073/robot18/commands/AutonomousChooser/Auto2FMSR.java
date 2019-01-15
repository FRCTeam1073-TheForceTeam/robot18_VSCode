package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

/*** If Chooser is set to Right */
public class Auto2FMSR extends CommandGroup {
	/** If Chooser is set to Right */
	public Auto2FMSR() {
		switch(Robot.FMS) {
		case "RRR":
			addSequential(new Auto3CommandR_RR());
			break;
		case "RLR":
			addSequential(new Auto3CommandR_RL());
			break;
		case "LLL":
			addSequential(new Auto3CommandR_LL());
			break;
		case "LRL":
			addSequential(new Auto3CommandR_LR());
			break;
		default:
			/** Should never get used. Something is either very right or very wrong if this gets run */
			SmartDashboard.putString("FMS DATA", "!!!Field Fault!!!");
			addSequential(new AdvancedDrive(-.8, 75, 80));
			break;
		}
	}
}
