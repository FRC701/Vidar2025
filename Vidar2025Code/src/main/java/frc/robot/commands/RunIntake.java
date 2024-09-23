// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
 * This command allows the user to turn on the intake motors so they aren't spinning constantly wasting battery
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunIntake extends InstantCommand {
  //Instantializing an Intake object
  private Intake mIntake;
  
  public RunIntake(Intake intake) {
    //Gets all the subsystem attributes
    this.mIntake = intake;
    addRequirements(mIntake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Allows Intake to spins
    Intake.IntakeActive = !Intake.IntakeActive;
  }
}
