package org.firstinspires.ftc.teamcode.general_tutorial;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "test linear", group = "tutorial")
public class TestLinear extends LinearOpMode {

    public void runOpMode(){
        telemetry.addData("Initializing","");
        telemetry.update();

        waitForStart();

        while (    opModeIsActive()   ){

        }


    }

}
