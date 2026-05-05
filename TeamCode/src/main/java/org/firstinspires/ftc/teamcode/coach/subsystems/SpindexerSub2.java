package org.firstinspires.ftc.teamcode.coach.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;

public class SpindexerSub2 implements Subsystem {
    public static final SpindexerSub2 INSTANCE = new SpindexerSub2();
    private SpindexerSub2(){}

    private MotorEx motor = new MotorEx("spindexer");

    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.5, 0, 0)
            .build();

    public Command toZero = new RunToPosition(controlSystem, 0).requires(this);
    public Command toMid = new RunToPosition(controlSystem, 2000).requires(this);

    @Override
    public void initialize() {
        //reset encoder
        motor.getMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        controlSystem.setGoal(new KineticState(0));
    }

    @Override
    public void periodic() {
        motor.setPower(controlSystem.calculate(motor.getState()));
    }
}
