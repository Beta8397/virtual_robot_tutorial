package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "test while linear", group = "tutorial")
public class TestWhileLinear extends LinearOpMode {

    public void runOpMode(){

        waitForStart();

        while ( opModeIsActive() && !gamepad1.b ){
            if (gamepad1.a) {
                telemetry.addData("A is Pressed","");
            } else {
                telemetry.addData("A is NOT Pressed","");
            }
            telemetry.update();
        }

    }

}
