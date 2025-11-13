package org.firstinspires.ftc.teamcode.subsystem;




import com.arcrobotics.ftclib.command.SubsystemBase;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import org.firstinspires.ftc.teamcode.util.Constants;

public class TransferSubsystem extends SubsystemBase {




    private final Servo gateLeft;
    private final Servo gateCenter;
    private final Servo gateRight;
    double transferPower = .6;
    double reverseTransferPower = 1;

    private ColorfulTelemetry cTelemetry;
    private HardwareMap hardwareMap;
    public TransferSubsystem(HardwareMap hardwareMap, ColorfulTelemetry telemetry) {

        // ================== MOTORS ================== \\
        this.cTelemetry = telemetry;

        gateLeft = hardwareMap.get(Servo.class, "gateLeft");
        gateCenter = hardwareMap.get(Servo.class, "gateCenter");
        gateRight = hardwareMap.get(Servo.class, "gateRight");


    }

    public void openLeftGate(){
        gateLeft.setPosition(Constants.transferConstants.GATE_LEFT_OPEN);
    }
    public void closeLeftGate(){
        gateLeft.setPosition(Constants.transferConstants.GATE_LEFT_CLOSED);
    }
    public void openRightGate(){
        gateRight.setPosition(Constants.transferConstants.GATE_RIGHT_OPEN);
    }
    public void closeRightGate() {
        gateRight.setPosition(Constants.transferConstants.GATE_RIGHT_CLOSED);
    }

    public void openCenterGate(){
        gateCenter.setPosition(Constants.transferConstants.GATE_CENTER_OPEN);
    }
    public void closeCenterGate(){
        gateCenter.setPosition(Constants.transferConstants.GATE_CENTER_CLOSED);
    }

    public void closeAllGates(){
        closeLeftGate();
        closeCenterGate();
        closeRightGate();
    }
    public void openAllGates(){
        openLeftGate();
        openCenterGate();
        openRightGate();
    }


    public void printTelemetry(ColorfulTelemetry t) {

        t.reset(); // reset any previous styles


        t.setColor(ColorfulTelemetry.Black).bold();
        t.addLine("INTAKE SUBSYSTEM");






        t.update();

    }

    @Override
    public void periodic() {



    }


}