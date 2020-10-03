package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;

public interface ParametricFunction {
    VectorF p(float s);
    VectorF d1(float s);
    VectorF d2(float s);


    default float findClosestPt(float x0, float y0, float s0) {
        float epsilon = .0001f;
        float delta = 100;
        ElapsedTime et = new ElapsedTime();
        while(delta > epsilon && et.milliseconds() < 500) {
            VectorF p = this.p(s0);
            VectorF d1 = this.d1(s0);
            VectorF d2 = this.d2(s0);
            float f = (p.get(0) - x0) * d1.get(0) + (p.get(1) - y0) * d1.get(1);
            float fDeriv = (p.get(0) - x0) * d2.get(0) + d1.get(0) * d1.get(0) + (p.get(1) - y0) * d2.get(1) + d1.get(1) * d1.get(1);
            float s = s0 - f / fDeriv;
            delta = Math.abs(s0 - s);
            s0 = s;
        }
        return s0;
    }
}
