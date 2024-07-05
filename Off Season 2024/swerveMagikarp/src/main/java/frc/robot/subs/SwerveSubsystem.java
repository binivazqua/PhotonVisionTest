package frc.robot.subs;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.ks;

public class SwerveSubsystem extends SubsystemBase {
    AHRS navX = new AHRS(SPI.Port.kMXP);
    private swerveModule frontLeftModule = new swerveModule(1, 2, 1, false, false);
    private swerveModule frontRightModule = new swerveModule(3, 4, 1, false, false);
    private swerveModule backLeftModule = new swerveModule(5, 6, 1, false, false);
    private swerveModule backRightModule = new swerveModule(7, 8, 1, false, false);

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        ks.SwerveSubsystemKs.frontLeftModuleCoords,
        ks.SwerveSubsystemKs.frontRightModuleCoords,
        ks.SwerveSubsystemKs.backLeftModuleCoords,
        ks.SwerveSubsystemKs.backRighttModuleCoords 
    );



  /** Creates a new ExampleSubsystem. */
  public SwerveSubsystem() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double loggingStates[] = {
        0,
        3,
        45,
        3,
        90,
        3,
        120,
        3,
    };

    SmartDashboard.putNumberArray("Swerve States",loggingStates);

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
