package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/** Puts up pneumatic collector */
public class CollectorUp extends Command {
boolean finished = false;
	protected void initialize() {
		Robot.pneumatic.collectorUp();
	}
	protected boolean isFinished() {
	return true;
	}
}
