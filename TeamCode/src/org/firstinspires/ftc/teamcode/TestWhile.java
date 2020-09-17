package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "test while", group = "tutorial")
public class TestWhile extends OpMode {

    public void init(){
        int x = 0;
        while (x < 10) {
            telemetry.addData("x = ", x);
            x = x +1;
        }
    }

    public void loop(){

    }
}
