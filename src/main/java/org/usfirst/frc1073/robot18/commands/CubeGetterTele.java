package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.commands.AutonomousTools.AdvancedDrive;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.CollectorDown;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.IsCubeIn;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.OpenClaw;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.SuckInCube;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.VisionCubeTracker;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.WasteTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1073.robot18.commands.AutonomousTools.*;

public class CubeGetterTele extends CommandGroup {
	
	public CubeGetterTele() {
		addParallel(new CollectorDown());
		addSequential(new OpenClaw());
		addParallel(new IsCubeIn());
		addSequential(new VisionCubeTracker());
		addSequential(new SuckInCube(2));
		addSequential(new WasteTime(9000));
		addSequential(new AdvancedDrive(0, 9000, 900000000)); //CubeGetter, but it will never end
	}
}