package org.firstinspires.ftc.teamcode.combined.subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub2 implements Subsystem {
    public static final SpindexerSub2 Instance = new SpindexerSub2();
    private SpindexerSub2(){}
    private final double firstPos = 0;  //0 degrees
    private final double secondPos = 250.6; //120 degrees
    private final double thirdPos = 501.2;  //240 degrees
    private final double fourthPos = 751.8;
    private final double fifthPos = 1002.4;
    private final double sixPos =1253;
    private final double ShootPos = 1503.6;  // 720 degrees
    private final MotorEx motor = new MotorEx("spindexer").reversed();
    private final ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.005, 0, 0)
            .build();
    public double getSpindexerPosition() {

        return motor.getCurrentPosition();
    }

    public Command toFirstPos = new RunToPosition(controlSystem, firstPos, 10).requires(this);
    public Command toSecondPOS = new RunToPosition(controlSystem, secondPos, 10).requires(this);
    public Command toThirdPos = new RunToPosition(controlSystem, thirdPos, 10).requires(this);
    public Command toFourthPos = new RunToPosition(controlSystem, fourthPos, 10).requires(this);
    public Command toFifthPos = new RunToPosition(controlSystem, fifthPos, 10).requires(this);
    public Command toShootPos = new RunToPosition(controlSystem, ShootPos, 10).requires(this);



























































}
