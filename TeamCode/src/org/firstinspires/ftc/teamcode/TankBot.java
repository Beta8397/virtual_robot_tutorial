package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * TankBot is a class that represents a simple two-wheel robot with drive motors on the left and right, and a gyro
 * sensor.
 *
 * It is a blueprint to create objects of type TankBot.
 *
 * Such an object will have variables (i.e, fields) that represent the motors and the gyro, as well as several
 * other fields that we use to keep track of the robot's position on the field.
 *
 */
public class TankBot {

    /**
     * FIELDS : These are variables that are declared within the class (but outside of any method)
     */

    //MOTORS and GYRO : note that we aren't assigning any values to these fields yet
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public GyroSensor gyro;

    //A variable of type Pose that stores the robot's current position and orientation on the field
    public Pose pose = new Pose(0, 0, 0);

    //We'll store the most recent encoder readings (ticks) in these two variables
    int rightTicks;
    int leftTicks;

    //Constants to store the ticks per full rotation of each drive motor, the wheel circumference, and robot width
    final float TICKS_PER_ROTATION = 1120;
    final float WHEEL_CIRCUMFERENCE = 4.0f * (float)Math.PI;
    final float WIDTH = 16.0f;

    /**
     *  METHODS
     *
     *  These are methods (pieces of executable code) that are part of the TankBot class. We can call these
     *  methods (i.e., execute the code) from other methods WITHIN this class, OR from methods in OTHER classes. In
     *  order to call these methods from OTHER classes, we would need a variable of type TankBot, and would call
     *  the method using the "dot" operator.
     */

    /**
     * CONSTRUCTOR : This is a special method that we can call using the "new" keyword to obtain objects of
     * type TankBot
     */
    public TankBot() {

    }

    /**
     * We use this method to obtain objects that correspond to our robot's hardware. We assign these objects to the
     * corresponding fields. We also initialize the gyro, and we initialize the leftTicks and rightTicks fields.
     * @param hwMap  We have to pass a HardwareMap object in as a parameter.
     */
    public void init(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "left_motor");
        rightMotor = hwMap.get(DcMotor.class, "right_motor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        gyro = hwMap.get(GyroSensor.class, "gyro_sensor");
        gyro.init();
        leftTicks = leftMotor.getCurrentPosition();
        rightTicks = rightMotor.getCurrentPosition();
    }

    /**
     * This method gets the robot's heading from the gyro, and returns it as a float value
     * @return
     */
    public float getHeading() {
        return (float)gyro.getHeading();
    }

    /**
     * This method sets the robot moving at the specified forward power and specified turning power
     * @param fwd
     * @param turn
     */
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

    /**
     * This method checks the right and left motor ticks and uses them to update the estimate of the robot's
     * position on the field.
     */
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
