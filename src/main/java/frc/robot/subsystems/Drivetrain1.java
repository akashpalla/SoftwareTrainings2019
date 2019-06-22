/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Drivetrain1 extends Subsystem implements PIDOutput{

  private PIDController turController;
  private AHRS navX;
  private TalonSRX frontLeft, frontRight, backLeft, backRight;

  public Drivetrain1(){

    frontLeft = new TalonSRX(2);
    frontRight = new TalonSRX(3);
    backLeft = new TalonSRX(4);
    backRight = new TalonSRX(5);

    navX = new AHRS(SPI.Port.kMXP);

    turController = new PIDController(0, 0, 0, navX, this);
  }


  public void setLeftRightOutputs(double left, double right){
      frontLeft.set(ControlMode.PercentOutput, left);
      frontRight.set(ControlMode.PercentOutput, right);
      backLeft.set(ControlMode.PercentOutput, left);
      backRight.set(ControlMode.PercentOutput, right);
    }
  

  public void pidWrite(double output) {
      setLeftRightOutputs(output, -output);
  }
  

  public void initDefaultCommand() {

  }

  public PIDController getTurController(){
    return turController;
  }
}
