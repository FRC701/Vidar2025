// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {

  //Creates a motor object
  private TalonFX FeederMotor;

  //An enum that allows us to activate a part of the code, also easy for us to see where our program goes wrong
  public static FeederEnumState mFeederEnumState;

  /** Creates a new Feeder. */
  public Feeder() {
    //Assigns a motor and state for our object and enum
    FeederMotor = new TalonFX(Constants.FeederConstants.kFeederMotor);
    mFeederEnumState = FeederEnumState.S_WaitingForIntake;
  }

  //Creates the enums and all the states it contains
  public enum FeederEnumState {
    S_WaitingForIntake,
    S_RecieveCargo,
    S_CarryingCargo,
    S_GiveShooter,
    S_Eject;
  }

  //Uses the enum and tells what method(s) to use when in a certain state
  public void RunFeederState() {
    switch (mFeederEnumState) {
      case S_WaitingForIntake:
        WaitingForIntake();
        break;
      case S_RecieveCargo:
        RecieveCargo();
      case S_CarryingCargo:
        CarryingCargo();
        break;
      case S_GiveShooter:
        GiveShooter();
        break;
      case S_Eject:
        Eject();
        break;
    }
  }

  //Keeps it not spinning as long as the intake isn't trying to give it cargo
  public void WaitingForIntake() {
    FeederMotor.setVoltage(0);
  }

  //Spins the motor a little so that the cargo can go into the feeder section and out of intake while not ramming the cargo into the shooter section
  public void RecieveCargo() {
    FeederMotor.setVoltage(1);
  }

  //Stops the motor while it just holds the cargo
  public void CarryingCargo() {
    FeederMotor.setVoltage(0);
  }

  //Speeds up the motors so that the shooter can recieve and shoot the ball
  public void GiveShooter() {
    FeederMotor.setVoltage(2);
  }

  //Launches the Cargo(Game Piece) out
  public void Eject() {
    FeederMotor.setVoltage(-6);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
