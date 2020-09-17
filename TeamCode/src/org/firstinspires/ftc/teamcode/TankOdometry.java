package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "tank odometry", group = "tutorial")
public class TankOdometry  extends LinearOpMode {
    TankBot bot = new TankBot();

    public void runOpMode(){
        bot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()){
            bot.updateOdometry();
            bot.setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            telemetry.addData("X = " , bot.pose.x);
            telemetry.addData("Y = ", bot.pose.y);
            telemetry.addData("Heading = ", Math.toDegrees(bot.pose.theta));
            telemetry.update();
        }
    }
}
