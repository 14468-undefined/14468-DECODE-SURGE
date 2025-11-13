package org.firstinspires.ftc.teamcode.subsystem;




import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.ColorfulTelemetry;
import com.qualcomm.hardware.rev.RevColorSensorV3;

public class SorterSubsystem extends SubsystemBase {

    /*
    this controls the goBilda PWM indicator lights - there's a few methods for different colors
     */



    private RevColorSensorV3 colorLeft;
    private RevColorSensorV3 colorCenter;
    private RevColorSensorV3 colorRight;


    private ColorfulTelemetry cTelemetry;
    private HardwareMap hardwareMap;
    public SorterSubsystem(HardwareMap hardwareMap, ColorfulTelemetry telemetry) {

        // ================== MOTORS ================== \\
        this.cTelemetry = telemetry;

        colorLeft = hardwareMap.get(RevColorSensorV3.class, "colorLeft");
        colorLeft.enableLed(true);

        colorCenter = hardwareMap.get(RevColorSensorV3.class, "colorCenter");
        colorCenter.enableLed(true);

        colorRight = hardwareMap.get(RevColorSensorV3.class, "colorRight");
        colorRight.enableLed(true);







    }

    public String getColorLeft() {
        int r = colorLeft.red();
        int g = colorLeft.green();
        int b = colorLeft.blue();
        return getColorArtifact(r, g, b);
    }
    public String getColorCenter() {
        int r = colorCenter.red();
        int g = colorCenter.green();
        int b = colorCenter.blue();
        return getColorArtifact(r, g, b);
    }
    public String getColorRight() {
        int r = colorRight.red();
        int g = colorRight.green();
        int b = colorRight.blue();
        return getColorArtifact(r, g, b);
    }

    public String getColorArtifact(int red, int green, int blue){
        String color;
        if(red >1 ){
            color = "Purple";
        }
        else if(blue >1 ){
            color = "Green";
        }
        else{
            color = "Empty";
        }
        return color;
    }



    public void disableSensors(){
        if (colorLeft != null) colorLeft.close();
        if (colorCenter != null) colorCenter.close();
        if (colorRight != null) colorRight.close();
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