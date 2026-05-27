package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub2 implements Subsystem {
    public static final SpindexerSub2 INSTANCE = new SpindexerSub2();
    private SpindexerSub2(){}

    private double firstPos;  //0 degrees
    private double secondPos; //120 degrees
    private double thirdPos;  //240 degrees
    private double ShootPos;  // 720 degrees
    private MotorEx motor = new MotorEx("spindexer").reversed();
    private final ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .build();
    public double getSpindexerPosition() {

        return motor.getCurrentPosition();
    }

    public Command toFirstPos = new RunToPosition(controlSystem, firstPos, 10).requires(this);
    public Command toSecondPOS = new RunToPosition(controlSystem, secondPos, 10).requires(this);
    public Command toThirdPos = new RunToPosition(controlSystem, thirdPos, 10).requires(this);
    public Command toShootPos = new RunToPosition(controlSystem, ShootPos, 10).requires(this);
@Override
    public void initialize() {
    double startPos = getSpindexerPosition();
    firstPos = startPos;
    secondPos = startPos + 250.6;
    thirdPos = startPos + 501.2;
    ShootPos = startPos + 1503.6;

}




























































}
