package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/*** Lowers Collector */
public class CollectorDown extends Command {
boolean finished = false;
	/** Lowers Collector */
	public CollectorDown() {
		
	}
	
	protected void initialize() {
		Robot.pneumatic.collectorDown();
	}
	
	protected boolean isFinished() {
		return true;
	}
}
