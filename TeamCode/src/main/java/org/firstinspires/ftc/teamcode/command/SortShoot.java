package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.subsystem.BaseRobot;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SortShoot extends SequentialCommandGroup {

    public SortShoot(BaseRobot robot, String motifPattern) {
        DriveSubsystem drive = robot.drive;



        if(motifPattern.equals("PPG")){

            if(Sort)
        }
        else if (motifPattern.equals("GPP")){

        }
        else if (motifPattern.equals("PGP")){

        }
        else{

        }


        //CommandBase spinUpShooter = new RunCommand(() -> robot.shooter.spin());

        //addRequirements(robot.shooter, robot.transfer, robot.intake);

        addCommands(
                //new RunCommand(() -> robot.shooter.spinUp(), robot.shooter).alongWith(

                new SequentialCommandGroup(
                        //intake
                        //spinupshooter

                        new ParallelCommandGroup(

                        )
                )
                //)
        );


    }

}

