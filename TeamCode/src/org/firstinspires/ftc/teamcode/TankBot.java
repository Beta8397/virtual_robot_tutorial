package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankBot {
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public GyroSensor gyro;

    public Pose pose = new Pose(0, 0, 0);

    int rightTicks;
    int leftTicks;

    final float TICKS_PER_ROTATION = 1120;
    final float WHEEL_CIRCUMFERENCE = 4.0f * (float)Math.PI;
    final float WIDTH = 16.0f;

    public TankBot() {

    }

    public void init(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "left_motor");
        rightMotor = hwMap.get(DcMotor.class, "right_motor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        gyro = hwMap.get(GyroSensor.class, "gyro_sensor");
        gyro.init();
        leftTicks = leftMotor.getCurrentPosition();
        rightTicks = rightMotor.getCurrentPosition();
    }

    public float getHeading() {
        return (float)gyro.getHeading();
    }

    public void setDrivePower(float fwd, float turn) {
        float leftPower = fwd - turn;
        float rightPower = fwd + turn;

        float m = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if(m > 1) {
            leftPower = leftPower / m;
            rightPower = rightPower / m;
        }

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public void updateOdometry(){
        int currentLeftTicks = leftMotor.getCurrentPosition();
        int currentRightTicks = rightMotor.getCurrentPosition();
        int newLeftTicks = currentLeftTicks - leftTicks;
        int newRightTicks = currentRightTicks - rightTicks;
        float leftDist = (newLeftTicks / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;
        float rightDist = (newRightTicks / TICKS_PER_ROTATION) * WHEEL_CIRCUMFERENCE;
        float fwd = (leftDist + rightDist) / 2.0f;
        float deltaTheta = (rightDist - leftDist) / WIDTH;
        float newHeading = pose.theta + deltaTheta;
        if (newHeading > Math.PI) {
            newHeading = newHeading - 2*(float)Math.PI;
        } else if (newHeading < -Math.PI){
            newHeading += 2*(float)Math.PI;
        }
        float newX = pose.x + fwd * (float)Math.cos(pose.theta);
        float newY = pose.y + fwd * (float)Math.sin(pose.theta);

        pose = new Pose(newX, newY, newHeading);

        leftTicks = currentLeftTicks;
        rightTicks = currentRightTicks;

    }

}
