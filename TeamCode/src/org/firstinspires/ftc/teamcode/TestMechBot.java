package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechbot.MechBot;

@TeleOp(name="Test Mech Bot", group="tutorial")
public class TestMechBot extends LinearOpMode {
    MechBot bot;
    public void runOpMode() {
        bot = new MechBot();
        bot.init(hardwareMap);
        gamepad1.setJoystickDeadzone(0.05f);
        waitForStart();

        while(opModeIsActive()) {
            bot.updateOdometry();
            telemetry.addData("X = ", bot.pose.x);
            telemetry.addData("Y = ", bot.pose.y);
            telemetry.addData("Theta = ", Math.toDegrees(bot.pose.theta));
            telemetry.update();
            bot.setDrivePower(-gamepad1.left_stick_y,
                    -gamepad1.right_stick_x,
                    gamepad1.left_stick_x);
        }
    }
}
