package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NeitoSubsystem;

public class NeitoPID extends CommandBase{

    private final NeitoSubsystem neitoSubsystem;
    PIDController pidNeo;

    public NeitoPID(double goal) { // --> no le cambies el nombre al command pq sino SE MUERE TODO.
        neitoSubsystem = NeitoSubsystem.getInstance();
        this.pidNeo = new PIDController(.005, 0, 0);
        pidNeo.setSetpoint(goal);
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(neitoSubsystem);
      }

      // PID MENSO
      /*
      public double getMovementApprox () {
        return neitoSubsystem.getNeitoEncoder();
      }
    
      public boolean ReallyAtSetpoint () {
        double error = getMovementApprox() - 
      }
      */
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        System.out.println("Neito Subsystem Started!");
        neitoSubsystem.resetEncoders();
        pidNeo.setTolerance(10);
      }
    
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
        NeitoSubsystem.setMotor(pidNeo.calculate(neitoSubsystem.getNeitoEncoder())); // --> se tuvo que cambiar a static el método de setMotor()
      }
    
      
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
        System.out.println("Neito Subsystem Ended Succesfully!");
        NeitoSubsystem.setMotor(0); // --> se acabó so, lo paramos.
    
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() { // respuesta a la pregunta isFinished?
       
        /* 
        if (pidNeo.atSetpoint() == true) {
            return true;
        } else{
            return false; // no está pusheado -> continua 
        }
        */
        return pidNeo.atSetpoint();
      }
    
    
}
