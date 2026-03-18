package org.firstinspires.ftc.teamcode.coach.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub implements Subsystem {
    public static final SpindexerSub INSTANCE = new SpindexerSub();
    private SpindexerSub(){}

    private final MotorEx motor = new MotorEx("spindexer");

    public double getSpindexerPosition(){
        return motor.getCurrentPosition();
    }
}
