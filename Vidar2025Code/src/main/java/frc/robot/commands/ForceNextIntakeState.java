// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
 * This command forces the enum state to switch so that in case of an emergency we can operate the robot
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeEnumState;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ForceNextIntakeState extends InstantCommand {
  //Instantializing an Intake object
  private Intake mIntake;

  public ForceNextIntakeState(Intake intake) {
    //Gets all the subsystem attributes
    this.mIntake = intake;
    addRequirements(mIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Allows for an emergency change of the intake state
    switch (Intake.mIntakeEnumState) {
      case S_WaitingOnCargo:
        Intake.mIntakeEnumState = IntakeEnumState.S_CarryingCargo;
        break;
      case S_CarryingCargo:
      Intake.mIntakeEnumState = IntakeEnumState.S_IntakeFeed;
        break;
    }
  }
}
