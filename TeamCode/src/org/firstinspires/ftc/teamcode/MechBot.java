package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MechBot {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    ColorSensor colorSensor;

    public void init(HardwareMap hwMap) {
        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        backRight = hwMap.get(DcMotor.class, "back_right_motor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        colorSensor = hwMap.get(ColorSensor.class, "color_sensor");
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
}
