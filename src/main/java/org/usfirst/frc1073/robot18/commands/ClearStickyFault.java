package org.usfirst.frc1073.robot18.commands;

import org.usfirst.frc1073.robot18.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1073.robot18.RobotMap;

/** Clears all PDP sticky faults 
 * @author Jack
 */
public class ClearStickyFault extends Command {

    public ClearStickyFault() {
    }
    
    protected void initialize() {
    	RobotMap.pdp.clearStickyFaults();
    }
    
    protected boolean isFinished() {
        return true;
    }
}
