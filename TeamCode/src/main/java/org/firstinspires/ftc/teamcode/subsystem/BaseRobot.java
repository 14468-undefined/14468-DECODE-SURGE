package org.firstinspires.ftc.teamcode.subsystem;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auto.util.AutoUtil;
import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;

public class BaseRobot extends UndefinedSubsystemBase {


    //public ExampleSubsytem example;
    public AutoUtil autoGenerator;
    public DriveSubsystem drive;
    public IntakeSubsystem intake;
    public TransferSubsystem transfer;
    public LEDSubsystem LED;
    public SorterSubsystem sorter;
    public ColorfulTelemetry cTelemetry;
    public Telemetry telemetry;




    public BaseRobot(HardwareMap hwMap, Pose2d startPos){
        drive = new DriveSubsystem(hwMap, startPos);
        autoGenerator = new AutoUtil(drive);
        sorter = new SorterSubsystem(hwMap, cTelemetry);
        intake = new IntakeSubsystem(hwMap, cTelemetry);
        LED = new LEDSubsystem(hwMap, cTelemetry);
        transfer = new TransferSubsystem(hwMap, cTelemetry);
        //add the rest of subsystems here


    }






    @Override
    public void printTelemetry(ColorfulTelemetry t) {
        drive.printTelemetry(t);
        //add more

    }

    @Override
    public void periodic() {
        drive.periodic();
        sorter.periodic();
        intake.periodic();

        //rest of subsystems here

    }

    public void stopAll(){
        //stop all the motors + servos, etc.

    }

    class ExampleAction implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            return false;
        }
    }


}