package org.firstinspires.ftc.teamcode.general_tutorial;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "test if", group = "tutorial")
public class TestIf extends OpMode {

    public void init(){

    }

    public void loop(){

        if (gamepad1.a) {
            telemetry.addData("A is pressed","");
        } else {
            telemetry.addData("A is NOT pressed","");
        }
    }

}
