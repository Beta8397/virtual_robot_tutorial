package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Tank Test", group="tutorial")
public class TankTest extends LinearOpMode {
    TankBot bot = new TankBot();

    public void runOpMode() {
        bot.init(hardwareMap);
        waitForStart();

        bot.setDrivePower(0, 0.7f);
        sleep(1000);
        bot.setDrivePower(1, 0);
        sleep(750);
        bot.setDrivePower(1, 0.5f);
        sleep(1000);
        bot.setDrivePower(0, 0);
        while(opModeIsActive()) {
            telemetry.addData("Heading", bot.getHeading());
            telemetry.update();
        }
    }
}
