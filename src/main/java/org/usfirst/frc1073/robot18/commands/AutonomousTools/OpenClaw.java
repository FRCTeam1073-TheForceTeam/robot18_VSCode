package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import javax.management.timer.Timer;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc1073.robot18.subsystems.*;
/** Opens the pneumatic claw */
public class OpenClaw extends Command {
boolean finished = false;
	protected void initialize() {
		Robot.pneumatic.openClaw();
	}
	protected boolean isFinished() {
		return true;
	}
}
