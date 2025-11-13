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
import org.firstinspires.ftc.teamcode.subsystem.LEDSubsystem;
import org.firstinspires.ftc.teamcode.util.Constants;

public class SortShoot extends SequentialCommandGroup {

    public SortShoot(BaseRobot robot, String motifPattern) {
        DriveSubsystem drive = robot.drive;

        int greenShot = 0;
        int purpleShot = 0;

        boolean leftUsed = false;
        boolean centerUsed = false;
        boolean rightUsed = false;

        String ball1 = null;
        String ball2 = null;
        String ball3 = null;
        if(motifPattern.equals("PPG")){
            ball1 = "Purple";
            ball2 = "Purple";
            ball3 = "Green";

        }
        else if (motifPattern.equals("GPP")){
            ball1 = "Green";
            ball2 = "Purple";
            ball3 = "Purple";
        }
        else if (motifPattern.equals("PGP")){
            ball1 = "Purple";
            ball2 = "Green";
            ball3 = "Purple";
        }
        else{

        }

//LOGIC HERE=========================================
        //check first purple - shoot it
        if(robot.sorter.getColorLeft().equals(ball1)) {
            //shoot left
            leftUsed = true;
        }
        else if(robot.sorter.getColorCenter().equals(ball1)){
            //shoot center
            centerUsed = true;
        }
        else if(robot.sorter.getColorRight().equals(ball1)){
            //shoot right
            rightUsed = true;
        }
        else{
            //set all lights to red to indicate the pattern can't be completed
            robot.LED.setAllColors(LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED);
        }
        //FINISH BALL 1

        if(robot.sorter.getColorLeft().equals(ball2) && !leftUsed){
            //shoot left
            leftUsed = true;
        }
        else if(robot.sorter.getColorCenter().equals(ball2) && !centerUsed){
            //shoot center
            centerUsed = true;
        }
        else if(robot.sorter.getColorRight().equals(ball2) && !rightUsed){
            //shoot right
            rightUsed = true;
        }
        else{
            //set all lights to red to indicate the pattern can't be completed
            robot.LED.setAllColors(LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED);
        }

        if(robot.sorter.getColorLeft().equals(ball3) && !leftUsed){
            //shoot left
            leftUsed = true;
        }
        else if(robot.sorter.getColorCenter().equals(ball3) && !centerUsed){
            //shoot center
            centerUsed = true;
        }
        else if(robot.sorter.getColorRight().equals(ball3) && !rightUsed){
            //shoot right
            rightUsed = true;
        }
        else{
            //set all lights to red to indicate the pattern can't be completed
            robot.LED.setAllColors(LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED, LEDSubsystem.LEDColor.RED);
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

