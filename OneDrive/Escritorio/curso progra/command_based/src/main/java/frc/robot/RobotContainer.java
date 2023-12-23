// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeCommand;
import frc.robot.commands.Autos;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.DriveForward;
import frc.robot.commands.NeitoCommand;
import frc.robot.commands.NeitoPID;
import frc.robot.commands.NeoteCommand;
import frc.robot.commands.TankCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeitoSubsystem;
import frc.robot.subsystems.NeoSubsystem;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.simulation.JoystickSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
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
  private final DriveSubsystem m_DriveSubsystem;
  //private final NeitoSubsystem m_neitoSubsystem = new NeitoSubsystem();
  //private final NeoSubsystem m_neoteSubsystem = new NeoSubsystem();
 /*
   private final NeitoSubsystem neito; --> ya no se crea una nueva instance porque ya se hace en el subsystem.
  private final NeoSubsystem neote;
*/

  // CONTROLES: --> solo botones :(
  //private final  Joystick control_chassis = new Joystick(0);
  //private final Joystick control_neos = new Joystick(1); // OJO AL PORT

  // Command Generic HID:
  public CommandGenericHID ps3 = new CommandGenericHID(0);
  //Trigger axis1 = ps3.axisLessThan(1, 0.4);
  
  //double axis2 = ps3.getRawAxis(0);
  //double axis1 = ps3.getRawAxis(1);

  Trigger square = ps3.button(3);
  Trigger circle = ps3.button(4);

  Trigger triangle = ps3.button(1);
  Trigger cross = ps3.button(2);

  Trigger backLeft = ps3.button(5);
  Trigger backRight = ps3.button(6);

  Trigger backDownLeft = ps3.axisGreaterThan(2, 0.1);

  //toggle on() --> lo pusheas una vez, se activa, la segunda se pusheada se desactiva.
  
  
  //declarar un boton:
  //Trigger triangulito = new control_chassis.
  



  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_DriveSubsystem = DriveSubsystem.getInstance();
    //neito = NeitoSubsystem.getInstance(); --> ya se hizo en el subsystem.
    //neote = NeoSubsystem.getInstance();
    
    // DEFAULT COMMAND DE CHASSIS
   /*  m_driveSubsystem.setDefaultCommand(
      new TankCommand(m_driveSubsystem, () -> control_chassis.getRawAxis(1) * 0.6, () -> control_chassis.getRawAxis(5) * 0.6) // el () -> es una funcion rápida para jalar el joystick.
    );
  */
  
  
    m_DriveSubsystem.setDefaultCommand(
      new TankCommand(() -> ps3.getRawAxis(1) * 0.5, () -> ps3.getRawAxis(5) *0.5)
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
    /* 
    new JoystickButton(control_neos, 3).whileTrue(new NeitoCommand(0.075));
    new JoystickButton(control_neos, 4).whileTrue(new NeitoCommand(-0.075));

    new JoystickButton(control_neos, 1).whileTrue(new NeoteCommand(0.3));
    new JoystickButton(control_neos, 2).whileTrue(new NeoteCommand(-0.3));
    */
    // con PS3:
    square.whileTrue(new NeitoCommand(-0.075));
    circle.whileTrue(new NeitoCommand(0.075));

    triangle.whileTrue(new NeoteCommand(0.5));
    cross.whileTrue(new NeoteCommand(-0.5));

   // boolean enArcade = backLeft.getAsBoolean();

    /* 
    // IDEA CON UNTIL:
    new ConditionalCommand (new ArcadeCommand(() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6), new TankCommand(() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(5) * 0.6), backLeft);
    */

    /* 
    backLeft
    .onTrue(new ArcadeCommand(() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6).until(enArcade));
*/  
    // Shift de arcade:
    backLeft.toggleOnTrue(new ArcadeCommand(() -> ps3.getRawAxis(1) * 0.5, () -> ps3.getRawAxis(4) * 0.5));

    // Acelerador:
    backRight.toggleOnTrue(new TankCommand(() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(5) * 0.6));
      
    // Acelerador Arcade:
    backDownLeft.toggleOnTrue(new ArcadeCommand(() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6));
    

    /* 
    backLeft
      .whileTrue(new ArcadeCommand(m_driveSubsystem, () -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6))
      .whileFalse(new TankCommand(m_driveSubsystem, () -> ps3.getRawAxis(1) * 0.6, ()-> ps3.getRawAxis(5) * 0.6));
    */

    
  /*   // PRUEBA 2
    backLeft 
      .toggleOnTrue(new ArcadeCommand(m_driveSubsystem, () -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6))
      .toggleOnFalse(new TankCommand(m_driveSubsystem,() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(5) * 0.6));
   */ 
    /* 
    backLeft 
      .onTrue(new ArcadeCommand(m_driveSubsystem, () -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(4) * 0.6))
      .onFalse(new TankCommand(m_driveSubsystem,() -> ps3.getRawAxis(1) * 0.6, () -> ps3.getRawAxis(5) * 0.6));
*/

    // comando de arcade a tank
    /* 
     * Comando de boton simple para arcade, para tank. 
     * Hace un deffault commnand pero no le pones new command, namás le pones el command que ya tienes de arcade o tank. 
     * OPCION 2: news todos en el deffault con un condicional.
     */
    //new JoystickButton(control_chassis, 3);

    //boolean enArcade = backLeft.getAsBoolean();
    
    

    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    //new JoystickButton(control_chassis, 1).whileFalse(new TankCommand(m_driveSubsystem, () -> control_chassis.getRawAxis(1) * 0.6,  () -> control_chassis.getRawAxis(5) * 0.6));
    //new JoystickButton(control_chassis, 2).whileFalse(new ArcadeCommand(m_driveSubsystem, () -> control_chassis.getRawAxis(1) * 0.6,  () -> control_chassis.getRawAxis(4) * 0.6));

      
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // AQUÍ SE PONE EL COMMAND QUE CORRE EN AUTO.
      //return new DriveForward(1.2); //
      //return new ComplexAuto();
      //return new NeitoPID(10);
      return new ComplexAuto();

      
  }
}
