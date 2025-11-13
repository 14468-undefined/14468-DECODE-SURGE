package org.firstinspires.ftc.teamcode.subsystem;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
public class ShooterSubsystem extends SubsystemBase {

    // --- Hardware ---
    private DcMotorEx shooterLeft;
    private DcMotorEx shooterCenter;
    private DcMotorEx shooterRight;

    // --- Dashboard ---
    private final FtcDashboard dash;

    // --- Shooter Constants ---
    public static double MOTOR_RPM = 6000;  // Motor's rated max RPM
    public static double GEAR_RATIO = 1.0;  // Gear ratio
    public static double TICKS_PER_REV = 28; // Encoder ticks per rev

    // --- Individual Target Speeds ---
    public static double TARGET_RPM_LEFT = 3500;
    public static double TARGET_RPM_CENTER = 3500;
    public static double TARGET_RPM_RIGHT = 3500;

    // --- PIDF Coefficients (per motor) ---
    public static double kP_LEFT = 20, kI_LEFT = 0.0, kD_LEFT = 5.0, kF_LEFT = 24.0;
    public static double kP_CENTER = 20, kI_CENTER = 0.0, kD_CENTER = 5.0, kF_CENTER = 24.0;
    public static double kP_RIGHT = 20, kI_RIGHT = 0.0, kD_RIGHT = 5.0, kF_RIGHT = 24.0;

    private boolean leftActive;
    private boolean rightActive;
    private boolean centerActive;

    public ShooterSubsystem(HardwareMap hardwareMap) {
        dash = FtcDashboard.getInstance();

        shooterLeft = hardwareMap.get(DcMotorEx.class, "shooterLeft");
        shooterCenter = hardwareMap.get(DcMotorEx.class, "shooterCenter");
        shooterRight = hardwareMap.get(DcMotorEx.class, "shooterRight");

        shooterLeft.setDirection(DcMotorEx.Direction.REVERSE);
        shooterCenter.setDirection(DcMotorEx.Direction.FORWARD);
        shooterRight.setDirection(DcMotorEx.Direction.FORWARD);


        shooterLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterCenter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterCenter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterCenter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        applyPIDF();
    }

    // --- PIDF Control ---
    public void applyPIDF() {
        shooterLeft.setVelocityPIDFCoefficients(kP_LEFT, kI_LEFT, kD_LEFT, kF_LEFT);
        shooterCenter.setVelocityPIDFCoefficients(kP_CENTER, kI_CENTER, kD_CENTER, kF_CENTER);
        shooterRight.setVelocityPIDFCoefficients(kP_RIGHT, kI_RIGHT, kD_RIGHT, kF_RIGHT);
    }

    public void setPIDFLeft(double kF, double kP, double kD, double kI) {
        kF_LEFT = kF; kP_LEFT = kP; kD_LEFT = kD; kI_LEFT = kI;
        shooterLeft.setVelocityPIDFCoefficients(kP_LEFT, kI_LEFT, kD_LEFT, kF_LEFT);
    }

    public void setPIDFCenter(double kF, double kP, double kD, double kI) {
        kF_CENTER = kF; kP_CENTER = kP; kD_CENTER = kD; kI_CENTER = kI;
        shooterCenter.setVelocityPIDFCoefficients(kP_CENTER, kI_CENTER, kD_CENTER, kF_CENTER);
    }

    public void setPIDFRight(double kF, double kP, double kD, double kI) {
        kF_RIGHT = kF; kP_RIGHT = kP; kD_RIGHT = kD; kI_RIGHT = kI;
        shooterRight.setVelocityPIDFCoefficients(kP_RIGHT, kI_RIGHT, kD_RIGHT, kF_RIGHT);
    }


    // -----------SET TARGET-------

    public void setLeftTargetRPM(double targetRPM) {
        TARGET_RPM_LEFT = targetRPM;
    }
    public void setCenterTargetRPM(double targetRPM) {
        TARGET_RPM_CENTER = targetRPM;
    }
    public void setRightTargetRPM(double targetRPM) {
        TARGET_RPM_RIGHT = targetRPM;
    }

    // --- Spin Control ---
    public void spinLeft() {
        double leftTargetTPS = rpmToTicksPerSec(TARGET_RPM_LEFT);
        shooterLeft.setVelocity(leftTargetTPS);
        leftActive = true;
    }
    public void spinCenter() {
        double centerTargetTPS = rpmToTicksPerSec(TARGET_RPM_CENTER);
        shooterLeft.setVelocity(centerTargetTPS);
        centerActive = true;
    }
    public void spinRight() {
        double rightTargetTPS = rpmToTicksPerSec(TARGET_RPM_RIGHT);
        shooterLeft.setVelocity(rightTargetTPS);
        rightActive = true;
    }

    public void stopLeft() {
        shooterLeft.setPower(0);
        shooterLeft.setVelocity(0);
        leftActive = false;
    }
    public void stopCenter() {
        shooterCenter.setPower(0);
        shooterCenter.setVelocity(0);
        centerActive = false;
    }
    public void stopRight() {
        shooterRight.setPower(0);
        shooterRight.setVelocity(0);
        rightActive = false;
    }

    public void stopAll(){
        stopRight();
        stopLeft();
        stopCenter();
    }

    // --- Velocity + Current ---
    private double rpmToTicksPerSec(double rpm) {
        return ((rpm / GEAR_RATIO) * TICKS_PER_REV) / 60.0;
    }

    private double ticksPerSecToRPM(double ticksPerSec) {
        return ((ticksPerSec * 60.0) / TICKS_PER_REV) * GEAR_RATIO;
    }

    public double getLeftRPM() {
        return ticksPerSecToRPM(shooterLeft.getVelocity());
    }

    public double getCenterRPM() {
        return ticksPerSecToRPM(shooterCenter.getVelocity());
    }

    public double getRightRPM() {
        return ticksPerSecToRPM(shooterRight.getVelocity());
    }

    public double getLeftAmps() {
        return shooterLeft.getCurrent(CurrentUnit.AMPS);
    }

    public double getCenterAmps() {
        return shooterCenter.getCurrent(CurrentUnit.AMPS);
    }

    public double getRightAmps() {
        return shooterRight.getCurrent(CurrentUnit.AMPS);
    }

    public boolean isAtTargetSpeed() {
        return isWithinRange(getLeftRPM(), TARGET_RPM_LEFT)
                && isWithinRange(getCenterRPM(), TARGET_RPM_CENTER)
                && isWithinRange(getRightRPM(), TARGET_RPM_RIGHT);
    }

    private boolean isWithinRange(double current, double target) {
        return (current > target - 200) && (current < target + 100);
    }

    /*public boolean isActive() {
        return active;
    }

     */
}
