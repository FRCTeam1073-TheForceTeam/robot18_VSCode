package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.Robot;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.TurnWithGyro;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*** If Chooser is set to Center and FMS is LLL */
public class LidarAlignWallLCG extends CommandGroup {
	/** If Chooser is set to Center and FMS is LLL */
	edu.wpi.first.networktables.NetworkTable Table;
	NetworkTableInstance lidarSendTable;
	public LidarAlignWallLCG(){
		lidarSendTable = NetworkTableInstance.getDefault();
		Table = lidarSendTable.getTable("lidarSendTable");
		double piState = lidarSendTable.getEntry("piState").getDouble(99);
		addSequential(new LidarWall());
		SmartDashboard.putString("Stat", "wall");
		addSequential(new TurnWithGyro(1, 85, "counterclockwise"));
		SmartDashboard.putString("Stat", "gyro");
		lidarSendTable.getEntry("turn").setString("left");
		piState = 2.0;
		addSequential (new LidarWall());
		piState = 3.0;
		addSequential(new LidarWallwObstacles());
		
	}
}
