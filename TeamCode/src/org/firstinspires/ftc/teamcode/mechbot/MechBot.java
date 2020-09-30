package org.firstinspires.ftc.teamcode.mechbot;

import android.graphics.Color;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Pose;
import org.firstinspires.ftc.teamcode.util.AngleUtils;

public class MechBot {

    public static final float WHEEL_CIRCUMFERENCE = 4 * (float)Math.PI;
    public static final float TICKS_PER_ROTATION = 1120;
    public static final float MAX_TICKS_PER_SECOND = 2500;
    public static final float WL_AVG = 15;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    ColorSensor colorSensor;

    BNO055IMU imu;

    float headingOffsetRadians = 0;

    public Pose pose = new Pose(0, 0, 0);

    int ticksBL, ticksFL, ticksFR, ticksBR;

    public void init(HardwareMap hwMap) {
        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        backRight = hwMap.get(DcMotor.class, "back_right_motor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        colorSensor = hwMap.get(ColorSensor.class, "color_sensor");

        imu = hwMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.accelerationIntegrationAlgorithm = null;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationData = null;
        parameters.calibrationDataFile = "";
        parameters.loggingEnabled = false;
        parameters.loggingTag = "Who cares.";

        imu.initialize(parameters);
    }

    public float getHeadingRadians(){
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        return headingOffsetRadians + orientation.firstAngle;
    }

    public void setHeadingDegrees(float headingDegrees){
        Orientation orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        headingOffsetRadians = (float)Math.toRadians(headingDegrees) - orientation.firstAngle;
        pose.theta = (float)Math.toRadians(headingDegrees);
        setTicks();
    }

    public void setTicks(){
        ticksBL = backLeft.getCurrentPosition();
        ticksFL = frontLeft.getCurrentPosition();
        ticksFR = frontRight.getCurrentPosition();
        ticksBR = backRight.getCurrentPosition();
    }

    public float[] getHSV() {
        float[] hsv = new float[3];
        Color.RGBToHSV(colorSensor.red(), colorSensor.green(),
                colorSensor.blue(), hsv);
        return hsv;
    }

    public void setDrivePower(float forward, float rotate, float strafe) {
        float frontLeftPower = forward - rotate + strafe;
        float frontRightPower = forward + rotate - strafe;
        float backLeftPower = forward - rotate - strafe;
        float backRightPower = forward + rotate + strafe;

        float largest = 1;
        largest = Math.max(largest, Math.abs(frontLeftPower));
        largest = Math.max(largest, Math.abs(frontRightPower));
        largest = Math.max(largest, Math.abs(backLeftPower));
        largest = Math.max(largest, Math.abs(backRightPower));

        frontLeft.setPower(frontLeftPower / largest);
        frontRight.setPower(frontRightPower / largest);
        backLeft.setPower(backLeftPower / largest);
        backRight.setPower(backRightPower / largest);
    }

    public void setDriveSpeed(float vx, float vy, float va){
        float px = (vx / WHEEL_CIRCUMFERENCE) * TICKS_PER_ROTATION / MAX_TICKS_PER_SECOND;
        float py = (vy / WHEEL_CIRCUMFERENCE) * TICKS_PER_ROTATION / MAX_TICKS_PER_SECOND;
        float pa = (WL_AVG * va / WHEEL_CIRCUMFERENCE) * TICKS_PER_ROTATION / MAX_TICKS_PER_SECOND;
        setDrivePower(py, pa, px);
    }

    public Pose updateOdometry(){
        float heading = getHeadingRadians();
        float headingChange = (float)AngleUtils.normalizeRadians(heading - pose.theta);
        float avgHeading = (float)AngleUtils.normalizeRadians(pose.theta + 0.5 * headingChange);

        int currBL = backLeft.getCurrentPosition();
        int currFL = frontLeft.getCurrentPosition();
        int currFR = frontRight.getCurrentPosition();
        int currBR = backRight.getCurrentPosition();

        int newBL = currBL - ticksBL;
        int newFL = currFL - ticksFL;
        int newFR = currFR - ticksFR;
        int newBR = currBR - ticksBR;

        ticksBL = currBL;
        ticksFL = currFL;
        ticksFR = currFR;
        ticksBR =currBR;

        float sBL = (newBL / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;
        float sFL = (newFL / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;
        float sFR = (newFR / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;
        float sBR = (newBR / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;

        float dXR = 0.25f * (-sBL + sFL - sFR + sBR);
        float dYR = 0.25f * (sBL + sFL + sFR + sBR);

        float dX = dXR * (float)Math.sin(avgHeading) + dYR * (float)Math.cos(avgHeading);
        float dY = -dXR * (float)Math.cos(avgHeading) + dYR * (float)Math.sin(avgHeading);

        pose.x = pose.x + dX;
        pose.y = pose.y + dY;
        pose.theta = heading;

        return pose;
    }
}
