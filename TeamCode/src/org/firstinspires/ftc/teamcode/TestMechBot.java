package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test Mech Bot", group="tutorial")
public class TestMechBot extends LinearOpMode {
    MechBot bot;
    public void runOpMode() {
        bot = new MechBot();
        bot.init(hardwareMap);
        gamepad1.setJoystickDeadzone(0.05f);
        waitForStart();

        while(opModeIsActive()) {
            bot.setDrivePower(-gamepad1.left_stick_y,
                    -gamepad1.right_stick_x,
                    gamepad1.left_stick_x);
        }
    }
}
