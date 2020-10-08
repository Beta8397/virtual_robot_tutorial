package org.firstinspires.ftc.teamcode.util;


/**
 * Represents the pose (position and heading) of the robot on the field.
 *
 * TODO: Consider making this IMMUTABLE.
 */

public class Pose {
    public float x;
    public float y;
    public float theta;

    public Pose(float xx, float yy, float ttheta){
        x = xx;
        y = yy;
        theta = ttheta;
    }
}
