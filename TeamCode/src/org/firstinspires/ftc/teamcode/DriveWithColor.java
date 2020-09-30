package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.mechbot.MechBot;

@Autonomous(name="Drive With Color", group="tutorial")
public class DriveWithColor extends LinearOpMode {
    MechBot bot;

    public void runOpMode() {
        bot = new MechBot();
        bot.init(hardwareMap);
        waitForStart();

        while(opModeIsActive()) {
            bot.setDrivePower(1, 0, 0);
            float[] hsv = bot.getHSV();
            System.out.println("Hue: " + hsv[0]);
            System.out.println("Saturation: " + hsv[1]);
            if ((hsv[0] < 30 || hsv[0] > 330) && hsv[1] > 0.5) {
                bot.setDrivePower(0, 0, 0);
                break;
            }
        }
        sleep(1000);
        while(opModeIsActive()) {
            float[] hsv = bot.getHSV();
            System.out.println("Hue: " + hsv[0]);
            System.out.println("Saturation: " + hsv[1]);
            bot.setDrivePower(0, 0, -1);
            if (hsv[0] > 200 && hsv[0] < 260 && hsv[1] > 0.5) {
                bot.setDrivePower(0, 0, 0);
                break;
            }
        }
    }
}
