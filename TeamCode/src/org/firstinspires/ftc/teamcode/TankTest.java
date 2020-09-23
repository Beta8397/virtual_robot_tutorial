package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * A simple LinearOpMode to demonstrate Autonomous operation of a TankBot
 */
@Autonomous(name="Tank Test", group="tutorial")
public class TankTest extends LinearOpMode {

    /*
     * "bot" is a FIELD belonging to the TankTest class. That is, that is declared within the TankTest class, but
     * OUTSIDE of any method. Its type is TankBot. We'll assign a new TankBot object to it.
     */
    TankBot bot = new TankBot();

    //A runOpMode method is mandatory when we are extending LinearOpMode.
    public void runOpMode() {
        //Initialize the bot object
        bot.init(hardwareMap);

        //Wait for start button to be pressed
        waitForStart();

        //Turn left with turn-power of 0.7 (out of maximum of 1.0) for 1000 milliseconds
        bot.setDrivePower(0, 0.7f);
        sleep(1000);

        //Drive straight forward with power of 1 (out of maximum of 1) for 750 milliseconds
        bot.setDrivePower(1, 0);
        sleep(750);

        //Drive forward with a left-turning arc for 1000 millisecondes
        bot.setDrivePower(1, 0.5f);
        sleep(1000);

        //Stop the bot
        bot.setDrivePower(0, 0);

        //Enter a while loop and display robot's heading until STOP button is pressed.
        while(opModeIsActive()) {
            telemetry.addData("Heading", bot.getHeading());
            telemetry.update();
        }
    }
}
