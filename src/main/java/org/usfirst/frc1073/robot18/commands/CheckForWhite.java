package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CheckForWhite extends Command{
 NetworkTable colorSenseTable;
 
 boolean seesWhite = false;
 String colorSeen  = "";
 public CheckForWhite() {

		requires(Robot.drivetrain);

		//Sets the correct Network Table to pull from the Pixy
		colorSenseTable = NetworkTable.getTable("ColorSenseTable");
	}
	protected void initialize() {
		
		
	}
	protected void execute() {
		colorSeen = colorSenseTable.getString("Color", " ");
		if(colorSeen.equalsIgnoreCase("White")){
			seesWhite = true;
		}	
		
	}

	protected boolean isFinished() {
		boolean is_finished = false;
		if(seesWhite == true) {
			is_finished = true;
		}
		SmartDashboard.putBoolean("Sees White", seesWhite);
		return is_finished;
		
	}	
	
	protected void end() {
		
	}

	protected void interrupted() {

	}
}
