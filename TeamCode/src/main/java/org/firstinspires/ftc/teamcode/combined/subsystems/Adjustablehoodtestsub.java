package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class Adjustablehoodtestsub implements Subsystem {
    public static final Adjustablehoodtestsub INSTANCE = new Adjustablehoodtestsub();
    private Adjustablehoodtestsub(){}

    private final ServoEx Hood = new ServoEx("hood");
    private double currentposition = 0.5;

    public void adjustmentup(){
        currentposition = Math.min(1.0,currentposition+0.01);
    }
    public void adjustmentdown(){
        currentposition = Math.max(0.0,currentposition-0.01);
    }
    public double getHoodposition(){
        return Hood.getPosition();
    }




















}
