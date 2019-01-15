package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.commands.HighGearDT;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandC_LLSimple extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	public Auto3CommandC_LLSimple() {
		addSequential(new TurnWithGyro(.8, 35, "counterclockwise"));
		addSequential(new AdvancedDrive(.8, 100, 90));
		addSequential(new TurnWithGyro(.8, 32, "clockwise"));
		addSequential(new SpitOutCube(.5, .5));
	}
}