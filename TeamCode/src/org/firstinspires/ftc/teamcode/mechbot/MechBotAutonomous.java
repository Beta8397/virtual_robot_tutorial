package org.firstinspires.ftc.teamcode.mechbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class MechBotAutonomous extends LinearOpMode {

    MechBot bot;

    public abstract void runOpMode();

    public void setBot(MechBot b){
        bot = b;
    }

    public void driveStraight(float speed, float directionDegrees, float targetHeadingDegrees, float distance){

        float xStart = bot.pose.x;
        float yStart = bot.pose.y;

        while (opModeIsActive()){
            bot.updateOdometry();
            float dSquared = (bot.pose.x - xStart) * (bot.pose.x - xStart)
                    + (bot.pose.y - yStart) * (bot.pose.y - yStart);
            float d = (float)Math.sqrt(dSquared);
            if (d >= distance) {
                break;
            }


        }
        bot.setDrivePower(0, 0, 0);
    }


}
