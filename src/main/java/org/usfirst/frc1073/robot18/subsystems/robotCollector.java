package org.usfirst.frc1073.robot18.subsystems;

import org.usfirst.frc1073.robot18.RobotMap;
import org.usfirst.frc1073.robot18.commands.SpinCollectorTele;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class robotCollector extends Subsystem {
	
    private final WPI_VictorSPX rightCollectorMotor = RobotMap.rightCollectorMotor;
    private final WPI_VictorSPX leftCollectorMotor = RobotMap.leftCollectorMotor;
    private final Encoder collectorEncoder = RobotMap.collectorEncoder;
    private final DigitalInput collectorSwitchBottom = RobotMap.collectorSwitchBottom;
    private final DigitalInput collectorSwitchBack = RobotMap.collectorFlip;
    private final DigitalInput collectorSwitchFront = RobotMap.collectorSwitchFront;
    
    public DifferentialDrive collectDrive;
    
    
    public robotCollector() {
    	rightCollectorMotor.setSafetyEnabled(false);
    	leftCollectorMotor.setSafetyEnabled(false);
    	collectDrive = new DifferentialDrive(RobotMap.leftCollectorMotor, RobotMap.rightCollectorMotor);
    	
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new SpinCollectorTele());
    }
}
