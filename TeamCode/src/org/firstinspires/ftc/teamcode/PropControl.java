package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="PropControl",group="tutorial")
public class PropControl extends LinearOpMode {
    TankBot bot;
    final float ROTATE_COEFFICIENT = 1 / 90.0f;

    public void turnToHeading(float targetHeading) {
        while(opModeIsActive()) {
            float currentHeading = bot.getHeading();
            float angleDiff = targetHeading - currentHeading;
            if (angleDiff > 180) {
                angleDiff -= 360;
            } else if (angleDiff < -180) {
                angleDiff += 360;
            }
            if(Math.abs(angleDiff) < 0.5) {
                bot.setDrivePower(0, 0);
                break;
            } else {
                bot.setDrivePower(0, ROTATE_COEFFICIENT * angleDiff);
            }
        }
    }

    public void runOpMode() {
        bot = new TankBot();
        bot.init(hardwareMap);
        waitForStart();

        turnToHeading(90);
    }
}
