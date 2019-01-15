package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EncoderCheck extends Command {

	protected boolean isFinished() {
		boolean isFinished = false;
		if (Robot.EncoderBool == true) {
			isFinished = true;
		}
		return isFinished;
	}

}
