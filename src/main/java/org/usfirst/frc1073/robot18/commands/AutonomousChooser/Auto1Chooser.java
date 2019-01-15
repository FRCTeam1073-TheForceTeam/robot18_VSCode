package org.usfirst.frc1073.robot18.commands.AutonomousChooser;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/***
 * Fully Autonomous Auto System
 * @author Nathaniel
 * @version 2.0.1
 */
public class Auto1Chooser extends CommandGroup {

	/* NOTE:  You can only use a conditional in a Command group if you do it in AutonomousInit() and is
	 *        you only use information that is available when AutonomousInit() is called. 
    */
	 
	private String FMS;
	public Auto1Chooser() {
		addSequential(new Auto1WhereAmI());
		FMS = Robot.FMS;

		/*  First version */
		switch(Robot.autonomousPosition.getSelected().getString()) {
		case "left":
			addSequential(new Auto2FMSL());
			break;
		case "center":
			addSequential(new Auto2FMSC());
			break;
		case "right":
			addSequential(new Auto2FMSR());
			break;
		default:
			SmartDashboard.putString("Chooser", "!!!Chooser Not Set!!!");
			addSequential(new AdvancedDrive(-.8, 80, 80));
			break;
		}
	}
}