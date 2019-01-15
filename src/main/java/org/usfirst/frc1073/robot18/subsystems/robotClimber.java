package org.usfirst.frc1073.robot18.subsystems;
import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.ControllerDifferentialDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class robotClimber extends Subsystem{
	private final WPI_VictorSPX climberMotor = RobotMap.climberMotor;
	public DifferentialDrive climberDrive;
	
public robotClimber() {
	climberMotor.setSafetyEnabled(false);
	climberDrive = new DifferentialDrive(RobotMap.climberMotor, RobotMap.climberMotor);
}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
}