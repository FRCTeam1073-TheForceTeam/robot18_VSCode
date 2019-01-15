package org.usfirst.frc1073.robot18.commands.AutonomousTools;

import org.usfirst.frc1073.robot18.TimeWaster.towerRenderer;

import edu.wpi.first.wpilibj.command.Command;

public class WasteTime extends Command {

	private double time, timer, timeEnd;
	private boolean timeBool;

	public WasteTime() {
		timeBool = false;
	}

	public WasteTime(double time) {
		this.time = time;
		if (time == 9000) {
			timeBool = false;
		}
		else {
			timeBool = true;
		}
	}

	protected void intitialize() {
		if (timeBool == false) {
			//towerRenderer.meme.solve();
		}
		
		timer = 0;
		timeEnd = time * 20;
	}

	protected void execute() {
		timer++;
	}

	protected boolean isFinished() {
		boolean finished = false;
		if (timeBool) {
			if (timer > timeEnd) {
				finished = true;
			}
		}
		return finished;
	}

}
