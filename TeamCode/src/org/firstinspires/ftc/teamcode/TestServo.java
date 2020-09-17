package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name ="test servo", group = "Tutorial")
public class TestServo extends OpMode {

    Servo sv;

    public void init(){
        sv = hardwareMap.get(Servo.class, "servo");
    }

    public void loop(){
        float pos = gamepad1.left_stick_x;
        if (pos < 0) {
            pos = 0;
        }
        sv.setPosition(pos);
    }

}
