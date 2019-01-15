package org.usfirst.frc1073.robot18.commands.AutonomousChooserSimple;

import org.usfirst.frc1073.robot18.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*** Displays FMS and Chooser values */
public class Auto1WhereAmISimple extends Command {

	protected void initialize() {
		/* Sends data to dashboard */
		SmartDashboard.putString("FMS DATA", Robot.FMS);
		SmartDashboard.putString("Position Selected", Robot.autonomousPosition.getSelected().getString());
		SmartDashboard.putString("Match Type Selected", Robot.autonomousMatchType.getSelected().getString());
	}

	protected boolean isFinished() {
		boolean isFinished = true;
		return isFinished;
	}
}