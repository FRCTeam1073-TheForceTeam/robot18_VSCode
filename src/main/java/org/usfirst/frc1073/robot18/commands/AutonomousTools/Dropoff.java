package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Dropoff extends Command {
boolean finished = false;
	public Dropoff(int i, String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean isFinished() {
		if (Robot.oi.driverCancel.get() == true || Robot.oi.operatorCancel.get() == true) {
			finished = true;
		}
		// TODO Auto-generated method stub
		return finished;
	}

}
