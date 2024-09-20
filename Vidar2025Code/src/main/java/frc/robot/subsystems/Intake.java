// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  
  //Creates a motor object
  private TalonFX IntakeMotor;

  //A boolean so we are able to turn off the motor
  public static boolean IntakeActive;

  //An enum that allows us to activate a part of the code, also easy for us to see where our program goes wrong
  public static IntakeEnumState mIntakeEnumState;

  /** Creates a new Intake. */
  public Intake() {
    //Assigns a motor and state for our object and enum
    IntakeMotor = new TalonFX(Constants.IntakeConstants.kIntakeMotor);
    mIntakeEnumState = IntakeEnumState.S_WaitingOnCargo;
  }

  //Creates the enums and all the states it contains
  public enum IntakeEnumState {
    S_WaitingOnCargo,
    S_CarryingCargo,
    S_IntakeFeed,
    S_Eject
  }

  //Uses the enum and tells what method(s) to use when in a certain state
  public void RunIntakeState() {
    switch (mIntakeEnumState) {
      case S_WaitingOnCargo:
        WaitingOnCargo();
        break;
      case S_CarryingCargo:
        CarryingCargo();
        break;
      case S_IntakeFeed:
        IntakeFeed();
        break;
      case S_Eject:
        Eject();
        break;
    }
  }
  
  /*
  * Below are all the functions that the enum states calls
  */

  //If the intake is activated it will spin to bring in the Cargo(Game Piece)
  public void WaitingOnCargo() {
    if (IntakeActive) {
      IntakeMotor.setVoltage(-4);
    } else {
      IntakeMotor.setVoltage(0);
    }
  }

  /*
   * Code currently doesnt have a way to change to other enums
   */

  //Method is used/called to make sure the intake stops spinning after Cargo(Game Piece) enters
  public void CarryingCargo() {
    IntakeMotor.setVoltage(0);
  }

  //Pushes the Cargo(Game Piece) further so it goes in the feeder
  public void IntakeFeed() {
    IntakeMotor.setVoltage(-8);
  }

  //Launches the Cargo(Game Piece) out
  public void Eject() {
    IntakeMotor.setVoltage(6);
  }
  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Runs the state that the intake('s enum) is at
    RunIntakeState();
    //Allows the driver and others to see if Intake is active and its state
    SmartDashboard.putBoolean("IntakeActive", IntakeActive);
    SmartDashboard.putString("IntakeState", mIntakeEnumState.toString());
  }
}
