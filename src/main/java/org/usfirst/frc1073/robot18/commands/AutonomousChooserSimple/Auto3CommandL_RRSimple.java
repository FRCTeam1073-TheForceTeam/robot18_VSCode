package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.AutoVars;
import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class Auto3CommandL_RRSimple extends CommandGroup {
	/** If Chooser is set to Left and FMS is RRR */
	public Auto3CommandL_RRSimple() {
		addSequential(new AdvancedDrive(.8, 150, 100));
	}
}