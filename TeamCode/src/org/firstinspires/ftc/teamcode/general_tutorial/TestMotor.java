package org.firstinspires.ftc.teamcode.general_tutorial;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Test Motor",group="Test")
public class TestMotor extends OpMode {
    DcMotor motor;

    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void loop() {
        gamepad1.setJoystickDeadzone(0.05f);
        motor.setPower(gamepad1.left_stick_y);
        int motorPosition = motor.getCurrentPosition();
        telemetry.addData("Position", motorPosition);
    }
}
