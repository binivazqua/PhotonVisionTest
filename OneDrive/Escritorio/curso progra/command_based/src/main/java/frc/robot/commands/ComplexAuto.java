package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ComplexAuto extends SequentialCommandGroup {



    public ComplexAuto() {


        addCommands(    
            new DriveForward(1.2), // --> avanza 1.2m
            
            new NeitoPID(10).andThen(            
                
                    new DriveForward(0.5).alongWith(new NeitoPID(5)) // --> avanza 0.5 metros mientras gira 5 veces.
                    
            ).andThen(new DriveForward(0.2))
            // --> luego gira 10 veces.
             // --> avanza otros 0.2 metros.

        );  
    }


    
}
