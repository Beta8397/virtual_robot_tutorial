package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "tank auto 1", group = "tutorial")
public class TankAuto1 extends LinearOpMode {

    TankBot bot = new TankBot();

    public void runOpMode(){

        bot.init(hardwareMap);

        bot.setDrivePower(0.5f, 0);
        while (opModeIsActive()){
            bot.updateOdometry();
            if (bot.pose.x > 48) {
                break;
            }
        }

        bot.setDrivePower(0, 0.5f);
        while (opModeIsActive()){
            bot.updateOdometry();
            if (bot.pose.theta > Math.PI/2){
                break;
            }
        }

        bot.setDrivePower(0.5f, 0);
        while (opModeIsActive()){
            bot.updateOdometry();
            if (bot.pose.y > 48){
                break;
            }
        }

        bot.setDrivePower(0,0);
    }
}
