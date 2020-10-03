package org.firstinspires.ftc.teamcode.general_tutorial;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * The TankOdometry class is a LinearOpMode that tests the odometry capability of the TankBot class.
 */

@TeleOp(name = "tank odometry", group = "tutorial")
public class TankOdometry  extends LinearOpMode {

    /**
     * The TankOdometry class has a single field: bot. The Type of this field is TankBot.
     * We can declare this field (i.e., variable) and assign a new TankBot object to it all in one statement.
     * To create the new object, use the "new" keyword, followed by a call to the constructor method.
     */
    TankBot bot = new TankBot();

    /**
     * Because this class extends LinearOpMode, it must define a "runOpMode" method.
     */
    public void runOpMode(){

        /*
         * Initialize the bot field by calling its "init" method, passing the hardwareMap as a parameter.
         * Note use of the "dot" operator.
         * This would be pronounced "bot-dot-init".
         */
        bot.init(hardwareMap);

        //Wait for start button to be pressed.
        waitForStart();

        /*
         * Main control loop.
         *
         * For the while loop "condition", we call the "opModeIsActive" method of LinearOpMode. It will return true
         * UNTIL the STOP button is pressed, after which it returns false.
         */
        while(opModeIsActive()){
            //Call bot.updateOdometry to do one iteration of the odometry process
            bot.updateOdometry();

            //Set the drive power to the bot, based on the status of the gamepad joysticks
            bot.setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

            /*
             * Sent the bot's position and orientation information to telemetry, so the driver can see it.
             *
             * What is this "bot.pose.x" business?
             *
             * Remember that the TankBot class has a FIELD (i.e. variable belonging to the class) named "pose".
             * The DOT operator gets us access to either a method or a field that belongs to an object.
             *
             * bot.pose (pronounced "bot-dot-pose" gets us the pose field of the bot object.
             *
             * So, bot.pose is an object of type "Pose". Now we'll use the DOT operator again, to get the "X" field
             * of the Pose object:  bot.pose.x
             */
            telemetry.addData("X = " , bot.pose.x);
            telemetry.addData("Y = ", bot.pose.y);
            telemetry.addData("Heading = ", Math.toDegrees(bot.pose.theta));
            telemetry.update();
        }
    }
}
