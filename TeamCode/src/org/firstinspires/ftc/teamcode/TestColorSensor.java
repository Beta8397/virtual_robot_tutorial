package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name="Test Color Sensor", group="tutorial")
public class TestColorSensor extends OpMode {
    ColorSensor colorSensor;

    public void init() {
        colorSensor = hardwareMap.get(ColorSensor.class,
                "sensor_color_distance");
    }

    public void loop() {
        telemetry.addData("red", colorSensor.red());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("blue", colorSensor.blue());

        float[] hsv = new float[3];
        Color.RGBToHSV(colorSensor.red(), colorSensor.green(),
                colorSensor.blue(), hsv);
        telemetry.addData("Hue", hsv[0]);
        telemetry.addData("Saturation", hsv[1]);
        telemetry.addData("Value", hsv[2]);
    }
}
