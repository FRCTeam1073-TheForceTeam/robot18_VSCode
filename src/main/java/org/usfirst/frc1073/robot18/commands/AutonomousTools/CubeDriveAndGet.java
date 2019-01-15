package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.AutoVars;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CubeDriveAndGet extends CommandGroup {

    public CubeDriveAndGet() {
    	addParallel(new OpenClaw());
		addParallel(new SuckInCube(1.5, .75));
		addSequential(new AdvancedDrive(-AutoVars.ADSpeed, 10, 30));
    }
}
