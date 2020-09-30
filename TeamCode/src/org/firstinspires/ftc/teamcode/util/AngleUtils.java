package org.firstinspires.ftc.teamcode.util;

public class AngleUtils {

    public static double normalizeRadians(double radians){
        double temp = (radians + Math.PI) / (2.0 * Math.PI);
        temp = temp - Math.floor(temp);
        return (temp - 0.5) * 2.0 * Math.PI;
    }
}
