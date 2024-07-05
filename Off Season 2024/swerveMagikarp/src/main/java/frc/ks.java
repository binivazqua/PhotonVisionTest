package frc;

import edu.wpi.first.math.geometry.Translation2d;

public class ks {
    
    public static final class SwerveModuleKs {
        
    }

    public static final class SwerveSubsystemKs {

        public static final double trackWidth = 20;
        public static final double wheelBase = 20;

        // Swerve Drive Kinematics Coordinates 
        public static final Translation2d frontRightModuleCoords= new Translation2d(wheelBase / 2, trackWidth / 2);
        public static final Translation2d frontLeftModuleCoords= new Translation2d(wheelBase / 2, - trackWidth / 2);
        public static final Translation2d backLeftModuleCoords= new Translation2d(- wheelBase / 2, trackWidth / 2);
        public static final Translation2d backRighttModuleCoords= new Translation2d(- wheelBase / 2, - trackWidth / 2);


        
    }

    
}
