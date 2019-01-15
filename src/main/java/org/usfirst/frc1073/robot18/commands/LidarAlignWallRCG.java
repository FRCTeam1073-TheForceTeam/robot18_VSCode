package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.TurnWithGyro;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*** If Chooser is set to Center and FMS is LLL */
public class LidarAlignWallRCG extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	edu.wpi.first.networktables.NetworkTable Table;
	NetworkTableInstance lidarSendTable;
	public LidarAlignWallRCG(){
		lidarSendTable = NetworkTableInstance.getDefault();
		Table = lidarSendTable.getTable("lidarSendTable");
		double piState = lidarSendTable.getEntry("piState").getDouble(0.0);
		boolean lidarWallFinished = lidarSendTable.getEntry("lidarWallFinished").getBoolean(false);
		lidarSendTable.getEntry("piState").setDouble(0.1);
		addSequential (new LidarWall()); 
		SmartDashboard.putString("Stat", "wall");
		addSequential(new TurnWithGyro(1, 85, "clockwise"));
		SmartDashboard.putString("Stat", "gyro");
		lidarSendTable.getEntry("Turn").setString("right");
		lidarSendTable.getEntry("piState").setDouble(2.0);
		addSequential(new LidarWall());
		lidarSendTable.getEntry("piState").setDouble(3.0);
		addSequential(new LidarWallwObstacles());
		
		}
		
	}