// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveForward;
import frc.robot.commands.NeitoCommand;
import frc.robot.commands.TankCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeitoSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final NeitoSubsystem m_neitoSubsystem = new NeitoSubsystem();

  // CONTROLES:
  private final Joystick control_chassis = new Joystick(0);
  private final Joystick control_neos = new Joystick(1); // OJO AL PORT



  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // DEFAULT COMMAND DE CHASSIS
    m_driveSubsystem.setDefaultCommand(
      new TankCommand(m_driveSubsystem, () -> control_chassis.getRawAxis(1) * 0.6, () -> control_chassis.getRawAxis(5) * 0.6) // el () -> es una funcion rápida para jalar el joystick.
    );
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {

    // CREAR UN BOTON: 
    /*
     * Solo puedes usar XBox, Play o Joystick. 
     * Haces un nuevo boton con: control id, le pones un método con: nuevo comando(subsystem, el resto de)
     */
    
    new JoystickButton(control_neos, 1).whileTrue(new NeitoCommand(m_neitoSubsystem, 0.075));
    new JoystickButton(control_neos, 2).whileTrue(new NeitoCommand(m_neitoSubsystem, -0.075));

    // comando de arcade a tank
    /*
     * Comando de boton simple para arcade, para tank. 
     * Hace un deffault commnand pero no le pones new command, namás le pones el command que ya tienes de arcade o tank. 
     * OPCION 2: news todos en el deffault con un condicional.
     */
    //new JoystickButton(control_chassis, 3);



    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    new JoystickButton(control_chassis, 1).whileFalse(getAutonomousCommand())

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // AQUÍ SE PONE EL COMMAND QUE CORRE EN AUTO.

      return new DriveForward(m_driveSubsystem, 1.2); //
      
  }
}
